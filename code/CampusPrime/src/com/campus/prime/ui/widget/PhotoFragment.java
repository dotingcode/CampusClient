package com.campus.prime.ui.widget;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.campus.prime.R;
import com.campus.prime.ui.IUploadImage;
import com.campus.prime.utils.StringUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;



public class PhotoFragment extends DialogFragment{
	public static Uri uri;
	public static Intent data;
	private IUploadImage mIUploadImage;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		PhotoDialog mDialog = new PhotoDialog(this.getActivity(),R.style.my_dialog);
		mDialog.setListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2 == 0){
					onTake();
				}else if(arg2 == 1){
					onAlbum();
				}
				PhotoFragment.this.dismiss();
			}
		});
		return mDialog;
	}
	
	public PhotoFragment setIUploadImage(IUploadImage i){
		this.mIUploadImage = i;
		return this;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK){
			if(requestCode == IUploadImage.SELECT_BY_ALBUM){
				mIUploadImage.onResultByAlbum(data);
			}else if(requestCode == IUploadImage.IMAGE_CROP){
				mIUploadImage.onResultByCrop(data);
			}else if(requestCode == IUploadImage.SELECT_BY_TAKE_PHOTO){
				mIUploadImage.onResultByTake(data);
			}
		}
	}
	
	
	/**
	 * On taking a photo
	 */
	private void onTake(){
		String savePath = "";
		//判断是否挂载了SD卡
		String storageState = Environment.getExternalStorageState();		
		if(storageState.equals(Environment.MEDIA_MOUNTED)){
			savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/OSChina/Camera/";//存放照片的文件夹
			File savedir = new File(savePath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		}
						
		//没有挂载SD卡，无法保存文件
		if(StringUtils.isEmpty(savePath)){
		Toast.makeText(getActivity(), "无法保存图片，请检查SD卡师傅挂载", Toast.LENGTH_SHORT).show();
		return;
		}

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "campus_" + timeStamp + ".jpg";//照片命名
		File out = new File(savePath, fileName);
		Uri uri = Uri.fromFile(out);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (hasImageCaptureBug()) {
		    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/tmp")));
		} else {
		    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		}
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		PhotoFragment.uri = uri;
		PhotoFragment.data = intent;
		getActivity().startActivityForResult(intent, IUploadImage.SELECT_BY_TAKE_PHOTO);
	}
	
	
	/**
	 * On select album
	 */
	private void onAlbum(){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		getActivity().startActivityForResult(intent,IUploadImage.SELECT_BY_ALBUM);
	}
	
	
	public static boolean hasImageCaptureBug() {

	    // list of known devices that have the bug
	    ArrayList<String> devices = new ArrayList<String>();
	    devices.add("android-devphone1/dream_devphone/dream");
	    devices.add("generic/sdk/generic");
	    devices.add("vodafone/vfpioneer/sapphire");
	    devices.add("tmobile/kila/dream");
	    devices.add("verizon/voles/sholes");
	    devices.add("google_ion/google_ion/sapphire");
	
	    return devices.contains(android.os.Build.BRAND + "/" + android.os.Build.PRODUCT + "/"
	            + android.os.Build.DEVICE);
	
	}
}