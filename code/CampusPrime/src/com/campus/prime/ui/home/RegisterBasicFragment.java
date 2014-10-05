package com.campus.prime.ui.home;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.campus.prime.R;
import com.campus.prime.core.User;
import com.campus.prime.core.service.UserService;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.IUploadImage;
import com.campus.prime.ui.circle.AllGroupFragment;
import com.campus.prime.ui.widget.PhotoFragment;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.ImageUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterBasicFragment extends Fragment implements IUploadImage{
	private BitmapManager mBitmapManager;
	private EditText mNicknameView;
	private ImageView mAvatar;
	private RegisterActivity mActivity;
	private ImageView mAvatarChange;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.register_basic, null);
		return view;
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		setHasOptionsMenu(true);
		((RegisterActivity)getActivity()).setTitle("完善资料");
		((RegisterActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		final ActionBar actionBar = ((RegisterActivity)getActivity()).getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		mBitmapManager = BitmapManager.getInstance(this.getActivity());
		mActivity = (RegisterActivity)getActivity();
		mNicknameView = (EditText)view.findViewById(R.id.rb_nickname);
		mAvatar = (ImageView)view.findViewById(R.id.rb_avatar);
		mActivity.mRegister.setNickName(mNicknameView.getText().toString());
		mBitmapManager.avatarLoader(null, mAvatar, 0, 0);
		mAvatarChange = (ImageView) view.findViewById(R.id.rb_change_avatar);
		mAvatarChange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActivity.mPhotoFragment = new PhotoFragment();
				mActivity.mPhotoFragment.setIUploadImage(RegisterBasicFragment.this);
				mActivity.mPhotoFragment.show(mActivity.getSupportFragmentManager(),"show");
			}
		});
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_next:
			String nickName = mNicknameView.getText().toString();
			if(TextUtils.isEmpty(nickName))
				Toast.makeText(this.getActivity(), "昵称不能为空", Toast.LENGTH_SHORT).show();
			else{
				mActivity.mRegister.setNickName(nickName);
				mActivity.showProgress(true);
				UpdateProfileTast task  = new UpdateProfileTast();
				task.execute();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
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
        mActivity.startActivityForResult(intent, IMAGE_CROP);
	}
	
	@Override
	public void onResultByAlbum(Intent data) {
		// TODO Auto-generated method stub
		if(data == null){
			Toast.makeText(getActivity(), "选择图片出错", Toast.LENGTH_SHORT).show();
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
				u = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), fi.getAbsolutePath(), null, null));
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
		mActivity.startActivityForResult(intent,IUploadImage.IMAGE_CROP);
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
				mAvatar.setImageBitmap(ImageUtils.roundBitmap(photo));
				String imageName;
				try {
					// generate a unique name
					imageName = UUID.randomUUID().toString();
					mActivity.mRegister.setAvatar(imageName);
					ImageUtils.saveImage(getActivity(), imageName, photo);
				}
				catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getActivity(), "文件保存失败", Toast.LENGTH_SHORT).show();
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
			File file = mActivity.getFileStreamPath(params[0]);
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
				Toast.makeText(getActivity(), "图片上传成功", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(getActivity(), "图片上传失败", Toast.LENGTH_SHORT).show();
		}
	}

	
	public class UpdateProfileTast extends AsyncTask<Void, Void, User>{
		
		@Override
		protected User doInBackground(Void... params) {
			// TODO Auto-generated method stub
			User updated = mActivity.mService.update(mActivity.mRegister);
			return updated;
		}
		
		@Override
		protected void onPostExecute(User result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mActivity.showProgress(false);
			if(result != null){
				//Toast.makeText(getActivity(), "Update success", Toast.LENGTH_SHORT).show();
				AllGroupFragment fragment = new AllGroupFragment();
				mActivity.mFragmentManager.beginTransaction()
					.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out)
					.replace(R.id.container,fragment)
					.commit();
			}
			else{
				//Toast.makeText(getActivity(), "Update failed", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	
}