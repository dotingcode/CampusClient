package com.campus.prime.ui.group;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import net.tsz.afinal.FinalBitmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.AsyncLoader;
import com.campus.prime.ui.IUploadImage;
import com.campus.prime.ui.indicator.TabPageIndicator;
import com.campus.prime.ui.view.ObservableScrollView;
import com.campus.prime.ui.widget.PhotoFragment;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.ImageUtils;

public class GroupFragment extends Fragment implements LoaderCallbacks<Group>{

	private GroupActivity mActivity;
	
	private static BitmapManager mBitmapManager;
	private FinalBitmap mFinalBitmap;
	
	public boolean isMember = false;
	protected boolean mIsBottom = false;
	
	private ObservableScrollView mScrollView;
	private ImageView mAvatarView;
	private TextView mNameView;
	private TextView mDescriptionView;
	private ImageView mCoverView;
	private View mEditNameBtn;
	private View mEditDescriptionBtn;
	
	/**
	 * pager Adapter
	 */
	protected GroupPagerAdapter mAdapter;
	/**
	 * View Pager
	 */
	protected ViewPager mPager;
	
	/*
	 * View Indicator
	 */
	protected TabPageIndicator mIndicator;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.group,null);
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mActivity = (GroupActivity)getActivity();
		mActivity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | 
									ActionBar.DISPLAY_SHOW_HOME|
				                    ActionBar.DISPLAY_SHOW_TITLE);
		mActivity.showProgress(false);
		mAdapter = new GroupPagerAdapter(mActivity.fm);
		mPager = (ViewPager)view.findViewById(R.id.ag_pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (TabPageIndicator)view.findViewById(R.id.ag_indicator);
		mIndicator.setViewPager(mPager);
		
		mBitmapManager = BitmapManager.getInstance(mActivity);
		mScrollView = (ObservableScrollView)view.findViewById(R.id.ag_scroll);
		mAvatarView = (ImageView)view.findViewById(R.id.ag_avatar);
		mNameView = (TextView)view.findViewById(R.id.ag_name);
		mDescriptionView = (TextView)view.findViewById(R.id.ag_description);
		mCoverView = (ImageView)view.findViewById(R.id.ag_cover);
		mFinalBitmap = FinalBitmap.create(getActivity());
		String path = null;
		if(mActivity.group != null){
			if(mActivity.group.getCover() == null || mActivity.group.getCover().isEmpty()){
				//path = "/cover/" + "group_cover.jpg";
				mCoverView.setBackgroundDrawable(getResources().getDrawable(R.drawable.group_cover));
			}
			else{
				path = "/media/" + mActivity.group.getCover();
				String url = BCSUtils.generateUrl(path);
				mFinalBitmap.display(mCoverView, url);
			}
		}else{
			
		}
		mEditNameBtn = view.findViewById(R.id.ag_name_edit);
		mEditNameBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mActivity.group != null)
					mActivity.fm.beginTransaction()
					.replace(R.id.container, new EditNameFragment())
					.addToBackStack(null)
					.commit();
			}
		});
		mEditDescriptionBtn = view.findViewById(R.id.ag_description_edit);
		mEditDescriptionBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mActivity.group != null)
					mActivity.fm.beginTransaction()
					.replace(R.id.container, new EditDescriptionFragment())
					.addToBackStack(null)
					.commit();
			}
		});
		mAvatarView.setClickable(false);
		mAvatarView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActivity.tag = 1;
				mActivity.photoFragment1.setIUploadImage(new IUploadImage() {
					
					
					
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
							if(photo != null){
								ByteArrayOutputStream stream = new ByteArrayOutputStream();  
								photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
								mAvatarView.setImageBitmap(ImageUtils.roundBitmap(photo));
								String imageName;
								try {
									// generate a unique name
									imageName = UUID.randomUUID().toString();
									mActivity.group.setAvatar(imageName);
									ImageUtils.saveImage(getActivity(), imageName, photo);
								}
								catch(IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(getActivity(), "文件保存失败", Toast.LENGTH_SHORT).show();
									return;
								}
								mActivity.group.setAvatar(imageName);
								ImageUploadTask imageUploadTask = new ImageUploadTask();
								imageUploadTask.execute(imageName);
								UpdateProfileTask updateProfileTask = new UpdateProfileTask();
								updateProfileTask.execute();
							}
						}
					}
					
					@Override
					public void onResultByAlbum(Intent data) {
						// TODO Auto-generated method stub
						if(data == null){
							Toast.makeText(getActivity(), "选择图片出错", Toast.LENGTH_SHORT).show();
							return;
						}
						Uri photoUri = data.getData();
						Intent intent = new Intent("com.android.camera.action.CROP");
				        intent.setDataAndType(photoUri, "image/*");
				        intent.putExtra("crop", "true");
				        intent.putExtra("aspectX", 1);
				        intent.putExtra("aspectY", 1);
				        intent.putExtra("outputX", 80);
				        intent.putExtra("outputY", 80);
				        intent.putExtra("return-data", true);
				        mActivity.startActivityForResult(intent,IUploadImage.IMAGE_CROP);
						
					}
				}).show(mActivity.fm,null);
			}
		});
		mCoverView.setClickable(false);
		mCoverView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActivity.tag = 2;
				mActivity.photoFragment2.setIUploadImage(new IUploadImage() {
					
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
				        intent.putExtra("aspectX",mCoverView.getWidth());
				        intent.putExtra("aspectY",mCoverView.getHeight());
				        intent.putExtra("return-data", true);
				        mActivity.startActivityForResult(intent,IUploadImage.IMAGE_CROP);
						onResultByCrop(data);
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
								mCoverView.setImageBitmap(photo);
								String imageName;
								try {
									// generate a unique name
									imageName = UUID.randomUUID().toString();
									mActivity.group.setCover(imageName);
									ImageUtils.saveImage(getActivity(), imageName, photo);
								}
								catch(IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(getActivity(), "文件保存失败", Toast.LENGTH_SHORT).show();
									return;
								}
								mActivity.group.setCover(imageName);
								ImageUploadTask imageUploadTask = new ImageUploadTask();
								imageUploadTask.execute(imageName);
								UpdateProfileTask updateProfileTask = new UpdateProfileTask();
								updateProfileTask.execute();
							}
						}
					}
					
					@Override
					public void onResultByAlbum(Intent data) {
						// TODO Auto-generated method stub
						if(data == null){
							Toast.makeText(getActivity(), "选择图片出错", Toast.LENGTH_SHORT).show();
							return;
						}
						Uri photoUri = data.getData();
						Intent intent = new Intent("com.android.camera.action.CROP");
				        intent.setDataAndType(photoUri, "image/*");
				        intent.putExtra("crop", "true");
				        intent.putExtra("aspectX",mCoverView.getWidth());
				        intent.putExtra("aspectY",mCoverView.getHeight());
				        intent.putExtra("return-data", true);
				        mActivity.startActivityForResult(intent,IUploadImage.IMAGE_CROP);
						
					}
					
				}).show(mActivity.fm,null);
			}
		});
		
		if(mActivity.group == null){
			if(mActivity.groupItem != null){
				mBitmapManager.avatarLoader(mActivity.groupItem.getAvatar(), mAvatarView, 0, 0);
				mNameView.setText(mActivity.groupItem.getName());
				mDescriptionView.setText(mActivity.groupItem.getDescription());
				mActivity.getSupportLoaderManager().initLoader(0,null,this);
			}
		}else{
			bindData();
			invalidateView();
		}
	}
	
	
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		if(mActivity.group != null){
			if(Auth.user != null){
				if(mActivity.group.getFounder() != null){
					if(mActivity.group.getFounder().getId() == Auth.user.getId()){
						//menu.clear();
						//mActivity.getMenuInflater().inflate(R.menu.save,menu);
					}else{
						menu.clear();
						mActivity.getMenuInflater().inflate(R.menu.add_group, menu);
						if(isMember)
							menu.findItem(R.id.action_add_group).setIcon(R.drawable.ic_menu_cancel);
						else
							menu.findItem(R.id.action_add_group).setIcon(R.drawable.ic_circle);
					}
				}
			}else{
				menu.clear();
				mActivity.getMenuInflater().inflate(R.menu.add_group, menu);
				menu.findItem(R.id.action_add_group).setIcon(R.drawable.ic_circle);
			}
		}
	}
	


	private void invalidateView(){
		if(Auth.user != null){
			if(mActivity.group.getFounder() != null){
				if(mActivity.group.getFounder().getId() == Auth.user.getId()){
					mEditDescriptionBtn.setVisibility(View.VISIBLE);
					mEditNameBtn.setVisibility(View.VISIBLE);
					mAvatarView.setClickable(true);
					mCoverView.setClickable(true);
				}else{
					mEditNameBtn.setVisibility(View.INVISIBLE);
					mEditDescriptionBtn.setVisibility(View.INVISIBLE);
					mAvatarView.setClickable(false);
					mCoverView.setClickable(false);
				}
			}
		}else{
			mEditNameBtn.setVisibility(View.INVISIBLE);
			mEditDescriptionBtn.setVisibility(View.INVISIBLE);
			mAvatarView.setClickable(false);
			mCoverView.setClickable(false);
		}
		//mActivity.getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
	}
	
	
	private void bindData(){
		mBitmapManager.avatarLoader(mActivity.group.getAvatar(), mAvatarView, 0, 0);
		String path = "/media/" + mActivity.group.getCover();
		String url = BCSUtils.generateUrl(path);
		mFinalBitmap.display(mCoverView, url);
		//mBitmapManager.mediaLoader(mActivity.group.getCover(), mCoverView, 0, 0);
		mNameView.setText(mActivity.group.getName());
		mDescriptionView.setText(mActivity.group.getDescription());
	}


	public class ImageUploadTask extends AsyncTask<String, Void, Boolean>{
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			File file = mActivity.getFileStreamPath(params[0]);
			String bcsPath;
			if(mActivity.tag == 1)
				bcsPath = "/" + BitmapManager.AVATAR + "/"
										+ params[0];
			else
				bcsPath = "/" + BitmapManager.MEDIA + "/"
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
				Toast.makeText(mActivity, "图片上传成功", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mActivity, "图片上传失败", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	public class UpdateProfileTask extends AsyncTask<Void,Void,Group>{
		@Override
		protected Group doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Group updated = mActivity.service.update(mActivity.group);
			return updated;
		}
		
		@Override
		protected void onPostExecute(Group result) {
			// TODO Auto-generated method stub
			if(result != null){
				Toast.makeText(mActivity, "资料更新成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(mActivity, "资料更新失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	@Override
	public Loader<Group> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new AsyncLoader<Group>(mActivity) {

			@Override
			protected Group loadData() {
				// TODO Auto-generated method stub
				return mActivity.service.getDetail(mActivity.groupItem.getId());
			}
		};
	}

	@Override
	public void onLoadFinished(Loader<Group> arg0, Group arg1) {
		// TODO Auto-generated method stub
		mActivity.group = arg1;
		if(mActivity.group!= null){
			bindData();
			invalidateView();
		}
	}

	@Override
	public void onLoaderReset(Loader<Group> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean getScrollViewState(){
		return mIsBottom;
	}
	
	public ObservableScrollView getScrollView(){
		return mScrollView;
	}
	
	
	
	
}
