package com.campus.prime.push.reciever;

import java.util.List;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.baidu.frontia.api.FrontiaPushMessageReceiver;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.utils.JsonUtil;
import com.campus.prime.push.client.Notification;
import com.campus.prime.ui.NotificationActivity;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;
import com.campus.prime.utils.SharedPreferenceUtil;

public class CampusPushMessageReciever extends FrontiaPushMessageReceiver{
	
	private CommonLog log = LogFactory.createLog(AppConstant.TAG);
	
	private static Context mContext;
	
	

	@Override
	public void onBind(Context arg0, int arg1, String appid, String userId,
			String channelId, String requestId) {
		// TODO Auto-generated method stub
		mContext = arg0;
		String bindMessage = " code = " + arg1 + " appid = " + appid + " userId = " + userId
				+ " channelId = " + channelId;
		log.d("onBind" + bindMessage);
		SharedPreferenceUtil util = SharedPreferenceUtil.getInstance();
		util.setAppId(appid);
		util.setUserId(userId);
		util.setChannelId(channelId);
	}
	
	

	@Override
	public void onDelTags(Context arg0, int arg1, List<String> arg2,
			List<String> arg3, String arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListTags(Context arg0, int arg1, List<String> arg2,
			String arg3) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onMessage(Context context, String message, String customMessage) {
		// TODO Auto-generated method stub
		String messageString = "recieved message " + message;
		log.d(messageString);
		Notification notification = JsonUtil.fromJson(message, Notification.class);
		notification.saveToDB();
		SharedPreferenceUtil util = SharedPreferenceUtil.getInstance();
		util.addNotificationCount();
		log.i("notificationCount = " + util.getNotificationCount());
		log.i(util.getNotificationCount() + "");
	}

	@Override
	public void onNotificationClicked(Context arg0, String title, String description,
			String customContentString) {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, "notification clicked", Toast.LENGTH_SHORT).show();
		log.i("notification clicked");
		
		/**
		Intent intent = new Intent();
		intent.setClass(mContext, NotificationActivity.class);
		mContext.startActivity(intent);
		**/
	}

	@Override
	public void onSetTags(Context arg0, int arg1, List<String> arg2,
			List<String> arg3, String arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnbind(Context arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

}
