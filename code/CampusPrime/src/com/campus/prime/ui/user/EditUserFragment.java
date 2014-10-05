package com.campus.prime.ui.user;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.Academy;
import com.campus.prime.core.User;
import com.campus.prime.core.service.UserService;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.IUploadImage;
import com.campus.prime.ui.view.ItemDialog;
import com.campus.prime.ui.view.LabelTextView;
import com.campus.prime.ui.view.ThemeImageView;
import com.campus.prime.ui.widget.PhotoFragment;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.ImageUtils;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.LogFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EditUserFragment extends Fragment implements IUploadImage{
	//Image 
	CommonLog log = LogFactory.createLog(AppConstant.TAG);
	
	private EditUserActivity mActivity;
	//Data
	private BitmapManager mBitmapManager;
	
	//Declare view for data
	private ImageView mAvatarView;
	private ImageView changeAvactar;
	private TextView mRealnameView;
	private TextView mNickNameView;
	private TextView mGenderView;
	private TextView mLoveStateView;
	private TextView mDescriptionView;
	private TextView mSchoolView;
	private TextView mAcademyView;
	private TextView mGradeView;
	private ItemDialog mDialog;
	
	//Declare view for event
	private LinearLayout mEditRealnameView;
	private LinearLayout mEditNicknameView;
	private LinearLayout mEditGenderView;
	private LinearLayout mEditLoveStateView;
	private LinearLayout mEditDescriptionView;
	private LinearLayout mEditSchoolView;
	private LinearLayout mEditAcademyView;
	private LinearLayout mEditGradeView;
	
	//Update
	private static UserService mService = new UserService();
	private UpdateProfileTask mUpdateProfileTask;
	private ImageUploadTask mImageUploadTask;
	
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState)
	{	
		//Get views from layout	
		mActivity = (EditUserActivity)getActivity();
		final ActionBar actionBar = mActivity.getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | 
									ActionBar.DISPLAY_SHOW_HOME|
				                    ActionBar.DISPLAY_SHOW_TITLE);
		mBitmapManager = BitmapManager.getInstance(this.getActivity());
		View rootView= inflater.inflate(R.layout.edit_user,null);
		mAvatarView = (ImageView) rootView.findViewById(R.id.fue_avatar);
		changeAvactar = (ImageView) rootView.findViewById(R.id.fue_change_avatar);
		mRealnameView = (TextView) rootView.findViewById(R.id.fue_real_name);
		mNickNameView = (TextView) rootView.findViewById(R.id.fue_nick_name);
		mGenderView = (TextView) rootView.findViewById(R.id.fue_gender);
		mLoveStateView = (TextView) rootView.findViewById(R.id.fue_love);
		mDescriptionView = (TextView) rootView.findViewById(R.id.fue_description);
		mSchoolView = (TextView) rootView.findViewById(R.id.fue_school);
		mAcademyView = (TextView) rootView.findViewById(R.id.fue_academy);
		mGradeView = (TextView) rootView.findViewById(R.id.fue_grade);
		
		mEditRealnameView = (LinearLayout) rootView.findViewById(R.id.fue_real_name_layout);
		mEditNicknameView = (LinearLayout) rootView.findViewById(R.id.fue_nick_name_layout);
		mEditGenderView = (LinearLayout) rootView.findViewById(R.id.fue_gender_layout);
		mEditLoveStateView = (LinearLayout) rootView.findViewById(R.id.fue_love_layout);
		mEditDescriptionView = (LinearLayout) rootView.findViewById(R.id.fue_description_layout);
		mEditSchoolView = (LinearLayout) rootView.findViewById(R.id.fue_school_layout);
		mEditAcademyView = (LinearLayout) rootView.findViewById(R.id.fue_academy_layout);
		mEditGradeView = (LinearLayout) rootView.findViewById(R.id.fue_grade_layout);
		
		this.bindData();
		this.setViewEvents();
		return rootView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}	
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.save, menu);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_save:
			mUpdateProfileTask = new UpdateProfileTask();
			mUpdateProfileTask.execute();
			return true;
		case android.R.id.home:
			getActivity().finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	/**
	 * Bind data to view
	 * 
	 */
	public void bindData()
	{
		mBitmapManager.avatarLoader(Auth.user.getAvatar(), mAvatarView, 0, 0);
		
		mRealnameView.setText(Auth.user.getRealName());
		mNickNameView.setText(Auth.user.getNickName());
		if(Auth.user.getSex() == 'F')
			mGenderView.setText("女");
		else
			mGenderView.setText("男");
		mLoveStateView.setText(Auth.user.getLoveState());
		mDescriptionView.setText(Auth.user.getDescription());
		mSchoolView.setText(Auth.user.getSchool());
		mAcademyView.setText(Auth.user.getAcademy());
		mGradeView.setText(Auth.user.getGrade());
		mGradeView.setText(Auth.user.getGrade());	
	}

	/**
	 * Set view events
	 * 
	 */
	public void setViewEvents(){
		
		changeAvactar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActivity.photoFragment = new PhotoFragment();
				mActivity.photoFragment.setIUploadImage(EditUserFragment.this);
				mActivity.photoFragment.show(mActivity.getSupportFragmentManager(), null);
				
			}
		});
		mEditRealnameView.setOnClickListener(new OnClickListener() {
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ft.replace(R.id.aue_fragment, new EditRealNameFragment()).addToBackStack(null).commit();
			}
		});
		mEditNicknameView.setOnClickListener(new OnClickListener(){
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			ft.replace(R.id.aue_fragment, new EditNickNameFragment()).addToBackStack(null).commit();
				
			}});
		mEditGenderView.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub				

				final List<String> genders = new ArrayList<String>();
				genders.add("男");
				genders.add("女");
				mDialog = new ItemDialog(arg0.getContext(),R.style.my_dialog,genders);
				mDialog.setHeader("性别");
				mDialog.show();
				mDialog.setItemListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						mGenderView.setText(genders.get(arg2));
						mDialog.dismiss();
						EditUserActivity mActivity = (EditUserActivity) getActivity();
						if(genders.get(arg2) == "女")
							Auth.user.setSex('F');
						else
							Auth.user.setSex('M');
					}});
			}});
		
		mEditLoveStateView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final List<String> loveStates = new ArrayList<String>();
				
				loveStates.add("单身");
				loveStates.add("寻找");		
				loveStates.add("追求");
				loveStates.add("热恋");
				mDialog = new ItemDialog(v.getContext(),R.style.my_dialog,loveStates);
				mDialog.setHeader("恋爱状态");
				mDialog.show();
				mDialog.setItemListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						mLoveStateView.setText(loveStates.get(arg2));
						Auth.user.setLoveState(loveStates.get(arg2));
						mDialog.dismiss();
					}});
			}});
		mEditDescriptionView.setOnClickListener(new OnClickListener(){
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ft.replace(R.id.aue_fragment, new EditDescriptionFragment()).addToBackStack(null).commit();
			}});
		mEditSchoolView.setOnClickListener(new OnClickListener(){
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ft.replace(R.id.aue_fragment, new EditSchoolFragment()).addToBackStack(null).commit();
			}});
		mEditAcademyView.setOnClickListener(new OnClickListener(){
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ft.replace(R.id.aue_fragment, new EditAcademyFragment()).addToBackStack(null).commit();
			}});
		mEditGradeView.setOnClickListener(new OnClickListener(){
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ft.replace(R.id.aue_fragment, new EditGradeFragment()).addToBackStack(null).commit();
			}});
		
	}
	
	/**
	 * picture zooming
	 * 
	 */
	private void cropPhoto(Uri uri){
		Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        mActivity.startActivityForResult(intent,IUploadImage.IMAGE_CROP);
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
                     Log.i("logMarker", "Failed to delete " + fi);
                 }
			} catch (FileNotFoundException e) {
                 e.printStackTrace();
            }
		} else {
			u = PhotoFragment.uri;
		}
		cropPhoto(u);
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
				mAvatarView.setImageBitmap(ImageUtils.roundBitmap(photo));
				String imageName;
				try {
					// generate a unique name
					imageName = UUID.randomUUID().toString();
					Auth.user.setAvatar(imageName);
					ImageUtils.saveImage(getActivity(), imageName, photo);
				}
				catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getActivity(), "文件保存失败", Toast.LENGTH_SHORT).show();
					return;
				}
				Auth.user.setAvatar(imageName);
				mImageUploadTask = new ImageUploadTask();
				mImageUploadTask.execute(imageName);
				mUpdateProfileTask = new UpdateProfileTask();
				mUpdateProfileTask.execute();
			}
		}
	}
	
	/**
	 * update user profile
	 * 
  	 */
	
	public class UpdateProfileTask extends AsyncTask<Void,Void,User>{
		@Override
		protected User doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			User updated = mService.update(Auth.user);
			return updated;
		}
		
		@Override
		protected void onPostExecute(User result) {
			// TODO Auto-generated method stub
			if(result != null){
				Toast.makeText(getActivity(), "资料更新成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getActivity(), "资料更新失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	/**
	 * update image
	 * 
	 */
	public class ImageUploadTask extends AsyncTask<String, Void, Boolean>{
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			File file = getActivity().getFileStreamPath(params[0]);
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
	
	
}