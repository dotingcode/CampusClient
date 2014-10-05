package com.campus.prime.ui.message;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupItem;
import com.campus.prime.core.Message;
import com.campus.prime.core.service.MessageService;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.IUploadImage;
import com.campus.prime.ui.group.GroupFragment.ImageUploadTask;
import com.campus.prime.ui.group.GroupFragment.UpdateProfileTask;
import com.campus.prime.ui.widget.CountTextWatcher;
import com.campus.prime.ui.widget.PhotoFragment;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.ImageUtils;
import com.campus.prime.utils.StringUtils;

public class MessageEditActivity extends BaseActivity implements IUploadImage{
	
	private static MessageService mService = new MessageService();
	
	private EditText mContentView;
	private TextView mCountView;
	private ImageView mMediaView;
	private Spinner mGroupSelectorView;
	
	private Message mMessage = new Message();
	private Group mSelectedGroup;
	private List<Group> mGroups;
	private int type;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_edit);
		mSelectedGroup = (Group)getIntent().getSerializableExtra("selectedGroup");
		mGroups = (List<Group>)getIntent().getSerializableExtra("groups");
		type = getIntent().getIntExtra("type", -1);
		if(type == 1){
			if(mSelectedGroup != null){
				List<GroupItem> toGroups = new ArrayList<GroupItem>();
				toGroups.add(mSelectedGroup.toGroupItem());
				mMessage.setGroups(toGroups);
			}
		}else if(type == 0){
			mMessage.setGroup(mGroups.get(0).toGroupItem());
		}
		mContentView = (EditText)findViewById(R.id.ame_edit_message);
		mCountView = (TextView)findViewById(R.id.ame_count);
		mContentView.addTextChangedListener(new CountTextWatcher(mContentView, mCountView));
		mMediaView = (ImageView)findViewById(R.id.ame_media);
		mGroupSelectorView = (Spinner)findViewById(R.id.ame_selector);
		GroupsDropDownListAdapter adapter = new GroupsDropDownListAdapter(this);
		adapter.setItems(mGroups);
		mGroupSelectorView.setAdapter(adapter);
		if(type == 1){
			int position = 0;
			int i;
			for(i = 0; i < mGroups.size();i++){
				if(mGroups.get(i).getId() == mSelectedGroup.getId()){
					position = i;
					break;
				}
			}
			mGroupSelectorView.setSelection(position);
		}
		mGroupSelectorView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(type == 1){
					if(mSelectedGroup != null){
						List<GroupItem> toGroups = new ArrayList<GroupItem>();
						toGroups.add(mGroups.get(arg2).toGroupItem());
						mMessage.setGroups(toGroups);
					}
				}else if(type == 0){
					mMessage.setGroup(mGroups.get(0).toGroupItem());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		final ActionBar actionBar = this.getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
                    ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME|
                    ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		View actionBarButtons = this.getLayoutInflater().inflate(R.layout.actionbar_custom,
                    new LinearLayout(this), false);
		View cancelActionView = actionBarButtons.findViewById(R.id.action_cancel);
		cancelActionView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		View doneActionView = actionBarButtons.findViewById(R.id.action_done);
		ImageView iconView = (ImageView) actionBarButtons.findViewById(R.id.action_done_icon);
		iconView.setImageDrawable(AppContext.getContext().getResources().getDrawable(R.drawable.ic_action_send));
		TextView textView = (TextView)actionBarButtons.findViewById(R.id.action_done_text);
		textView.setText("发送");
		doneActionView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				postMessage();
			}
		});
		actionBar.setCustomView(actionBarButtons);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode == Activity.RESULT_OK){
			if(requestCode == SELECT_BY_ALBUM){
				onResultByAlbum(data);
			}else if(requestCode == IMAGE_CROP){
				onResultByCrop(data);
			}else if(requestCode == SELECT_BY_TAKE_PHOTO){
				onResultByTake(data);
			}
			
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
		
	public void mediaClicked(View view){
		if(view.getId() == R.id.ame_btn_media){
			new PhotoFragment().setIUploadImage(this).show(getSupportFragmentManager(),null);
		}
	}
	
	
	private void postMessage(){
		String content = mContentView.getText().toString();
		if(StringUtils.isEmpty(content) && mMessage.getMedia() == null)
			Toast.makeText(this, "消息内容不能为空", Toast.LENGTH_SHORT).show();
		else{
			mMessage.setContent(content);
			CreateMessageTask task = new CreateMessageTask();
			task.execute();
			finish();
		}
	}
		

	
	
	public class CreateMessageTask extends AsyncTask<Void, Void, Message>{

		@Override
		protected Message doInBackground(Void... content) {
			// TODO Auto-generated method stub
			return mService.createMessage(mMessage);
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
				Toast.makeText(MessageEditActivity.this, "发布消息成功", Toast.LENGTH_SHORT).show();
				//MessageEditActivity.this.finish();
			}else{
				log.i("create message failed");
				Toast.makeText(MessageEditActivity.this, "发布消息失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	

	public class ImageUploadTask extends AsyncTask<String, Void, Boolean>{
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			File file = MessageEditActivity.this.getFileStreamPath(params[0]);
			String bcsPath = "/" + BitmapManager.MEDIA + "/"
										+ params[0];
			Boolean result = BCSUtils.PutObjectByFile(file,bcsPath);
			return result;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result){
				Toast.makeText(MessageEditActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(MessageEditActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
				mMessage.setMedia("");
			}
		}
	}
	
	


	@Override
	public void onResultByAlbum(Intent data) {
		// TODO Auto-generated method stub
		if(data == null){
			Toast.makeText(this, "选择图片出错", Toast.LENGTH_SHORT).show();
			return;
		}
		Uri photoUri = data.getData();
		// Get Bitmap from Uri
		String[] filePathColumn = {MediaStore.Images.Media.DATA};
		Cursor cursor = getContentResolver().query(photoUri, filePathColumn, null,null,null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String photoPath = cursor.getString(columnIndex);
		cursor.close();
		Bitmap media = BitmapFactory.decodeFile(photoPath);
		// Get the thumbnail bitmap
		//Bitmap thumbBitmap = ThumbnailUtils.extractThumbnail(media, 100, 100);
		mMediaView.setImageBitmap(media);
		
		String imageName;
		try {
			// generate a unique name
			imageName = UUID.randomUUID().toString();
			mMessage.setMedia(imageName);
			ImageUtils.saveImage(this, imageName, media);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "文件保存失败", Toast.LENGTH_SHORT).show();
			return;
		}
		mMessage.setMedia(imageName);
		ImageUploadTask task = new ImageUploadTask();
		task.execute(imageName);
	}

	@Override
	public void onResultByTake(Intent data) {
		// TODO Auto-generated method stub

		Uri u = null;
		if (PhotoFragment.hasImageCaptureBug()) {
			File fi = new File("/sdcard/tmp");
			try {
				u = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(), fi.getAbsolutePath(), null, null));
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
		intent.putExtra("return-data", true);
		startActivityForResult(intent,IUploadImage.IMAGE_CROP);
	}

	@Override
	public void onResultByCrop(Intent data) {
		// TODO Auto-generated method stub
		Bundle extras = data.getExtras(); 
		if (extras != null) 
		{  
			Bitmap photo = extras.getParcelable("data");  
			if(photo != null){
				ByteArrayOutputStream stream = new ByteArrayOutputStream();  
				photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				mMediaView.setImageBitmap(photo);
				String imageName;
				try {
					// generate a unique name
					imageName = UUID.randomUUID().toString();
					mMessage.setMedia(imageName);
					ImageUtils.saveImage(this, imageName, photo);
				}
				catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(this, "文件保存失败", Toast.LENGTH_SHORT).show();
					return;
				}
				mMessage.setMedia(imageName);
				ImageUploadTask imageUploadTask = new ImageUploadTask();
				imageUploadTask.execute(imageName);
			}
		}
	}
	
	
}
