package com.campus.prime.ui.circle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupItem;
import com.campus.prime.core.Message;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.core.service.MessageService;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.IUploadImage;
import com.campus.prime.ui.group.GroupActivity;
import com.campus.prime.ui.home.RegisterActivity;
import com.campus.prime.ui.widget.PhotoFragment;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.ImageUtils;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.StringUtils;

public class CreateGroupActivity extends BaseActivity implements IUploadImage{
	private Group mCreated;
	private BitmapManager mBitmapManager;
	private ImageView mAvatarView;
	private EditText mNameView;
	private PhotoFragment mPhotoFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_group);
		mBitmapManager = BitmapManager.getInstance(this);
		mCreated = new Group();
		mOpenRefresh = false;
		mAvatarView = (ImageView)findViewById(R.id.acg_change_avatar);
		mNameView = (EditText)findViewById(R.id.acg_name);
		mBitmapManager.avatarLoader(null, mAvatarView, 0, 0);
		mAvatarView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPhotoFragment = new PhotoFragment();
				mPhotoFragment.setIUploadImage(CreateGroupActivity.this);
				mPhotoFragment.show(getSupportFragmentManager(),"show");
			}

		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.next, menu);
		showProgress(false);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			return true;
		case R.id.action_next:
			String name = mNameView.getText().toString();
			if(StringUtils.isEmpty(name)){
				Toast.makeText(CreateGroupActivity.this, "名称不能为空",Toast.LENGTH_SHORT).show();
			}else{
				mCreated.setName(name);
				CreateGroupTask task = new CreateGroupTask();
				task.execute();
				showProgress(true);
				setRefreshActionButtonState(true);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(mPhotoFragment != null){
			mPhotoFragment.onActivityResult(arg0,arg1,arg2);
		}
	}
	
	

	private void cropPhoto(Uri uri){
		Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, IMAGE_CROP);
	}
	
	@Override
	public void onResultByAlbum(Intent data) {
		// TODO Auto-generated method stub
		if(data == null){
			Toast.makeText(this, "选择图片出错", Toast.LENGTH_SHORT).show();
			return;
		}
		Uri photoUri = data.getData();
		cropPhoto(photoUri);
	}


	
	@Override
	public void onResultByTake(Intent data) {
		// TODO Auto-generated method stub
		Uri u = null;
		if (PhotoFragment.hasImageCaptureBug()) {
			File fi = new File("/sdcard/tmp");
			try {
				u = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(this.getContentResolver(), fi.getAbsolutePath(), null, null));
				if (!fi.delete()) {
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			u = PhotoFragment.uri;
		}
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(u, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 80);
		intent.putExtra("outputY", 80);
		intent.putExtra("return-data", true);
		this.startActivityForResult(intent,IUploadImage.IMAGE_CROP);
	}


	@Override
	public void onResultByCrop(Intent data) {
		// TODO Auto-generated method stub
		Bundle extras = data.getExtras(); 
		if (extras != null) 
		{  
			Bitmap photo = extras.getParcelable("data");  
			if(photo != null)
			{
				ByteArrayOutputStream stream = new ByteArrayOutputStream();  
				photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				mAvatarView.setImageBitmap(ImageUtils.roundBitmap(photo));
				String imageName;
				try {
					// generate a unique name
					imageName = UUID.randomUUID().toString();
					mCreated.setAvatar(imageName);
					ImageUtils.saveImage(this, imageName, photo);
				}
				catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(this, "文件保存失败", Toast.LENGTH_SHORT).show();
					return;
				}
				ImageUploadTask mImageUploadTask = new ImageUploadTask();
				mImageUploadTask.execute(imageName);
			}
		}
	}
	
	
	
	public class ImageUploadTask extends AsyncTask<String, Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			File file = getFileStreamPath(params[0]);
			String bcsPath = "/" + BitmapManager.AVATAR + "/"
										+ params[0];
			Boolean result = BCSUtils.PutObjectByFile(file,bcsPath);
			if(result)
				return true;
			else
				return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result)
				Toast.makeText(CreateGroupActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(CreateGroupActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	public class CreateGroupTask extends AsyncTask<Void, Void, Group>{

		@Override
		protected Group doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Group result;
			GroupService service = new GroupService();
			result = service.createGroup(mCreated);
			return result;
		}
		
		@Override
		protected void onPostExecute(Group result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			CreateGroupActivity.this.showProgress(false);
			setRefreshActionButtonState(false);
			mCreated = result;
			if(result != null){
				Toast.makeText(CreateGroupActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
				finish();
				int count = Auth.user.getCreatedGroupCount() + 1;
				Auth.user.setCreatedGroupCount(count);
				int count1 = Auth.user.getJoinedGroupCount()  + 1;
				Auth.user.setJoinedGroupCount(count1);
				CreateMessageTask task1 = new CreateMessageTask();
				task1.execute(0);
				CreateMessageTask task2 = new CreateMessageTask();
				task2.execute(1);
				IntentUtil.startActivity(CreateGroupActivity.this, GroupActivity.class, "group",mCreated);
				
			}else{
				Toast.makeText(CreateGroupActivity.this, "创建失败", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	private int flag = 0;
	
	
	public  class CreateMessageTask extends AsyncTask<Integer, Void, Message>{
		
		private MessageService mService = new MessageService();
		
		
		@Override
		protected Message doInBackground(Integer... group) {
			// TODO Auto-generated method stub
			Message m = new Message();
			if(group[0] == 0){
				m.setContent("欢迎来到" + mCreated.getName() + "!");
				m.setGroup(mCreated.toGroupItem());
				return mService.createMessage(m);
			}else{
				m.setContent("欢迎加入" + mCreated.getName() + "!");
				List<GroupItem> gs = new ArrayList<GroupItem>();
				gs.add(mCreated.toGroupItem());
				m.setGroups(gs);
				return mService.createMessage(m);
			}
		}
		
		@Override
		protected void onPostExecute(Message result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result != null){
				log.i("create message success");
				int count = Auth.user.getMessageCount();
				count = count + 1;
				Auth.user.setMessageCount(count);
				//Toast.makeText(CreateGroupActivity.this, "create message success", Toast.LENGTH_SHORT).show();
				//MessageEditActivity.this.finish();
			}else{
				log.i("create message failed");
				//Toast.makeText(CreateGroupActivity.this, "create message failed", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	

}
