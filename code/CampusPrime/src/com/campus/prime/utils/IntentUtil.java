package com.campus.prime.utils;

import java.io.Serializable;
import java.util.Map;


import com.campus.prime.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtil {
	public static void startActivity(Activity activity,Class<?> cls,Map<String,? extends Serializable> params)
	{
		Intent intent=new Intent();
		intent.setClass(activity,cls);
		if(params != null){
			for(Map.Entry<String,?> entry : params.entrySet()){
				intent.putExtra(entry.getKey(),(Serializable)entry.getValue());
			}
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	
	
	
	public static void startActivity(Activity activity,Class<?> cls,String key,Serializable value){
		Intent intent = new Intent();
		intent.setClass(activity,cls);
		Bundle bundle = new Bundle();
		bundle.putSerializable(key, value);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		
	}
	

}
