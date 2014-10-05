package com.campus.prime.ui;

import java.util.List;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import com.campus.prime.R;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.Message;
import com.campus.prime.core.UserItem;
import com.campus.prime.ui.home.HomeActivity;
import com.campus.prime.ui.message.MessageDetailActivity;

import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.ui.view.ThemeTextView;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;
import com.campus.prime.utils.StringUtils;

public class MessageListViewAdapter extends SingleTypeAdapter<Message>{
	private CommonLog log = LogFactory.createLog(AppConstant.TAG);
	private BitmapManager mBitmapManager;
	private ImageView imageView;
	
	
	public MessageListViewAdapter(Context context,int layoutResourceId){
		super(LayoutInflater.from(context),layoutResourceId);
		this.context = context;
		mBitmapManager = BitmapManager.getInstance(context);
	}

	public MessageListViewAdapter(Context context,final List<Message> messages, int layoutResourceId) {
		this(context,layoutResourceId);
		setItems(messages);
	}
/**
	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.message_listitem_commentCount,
				R.id.message_listitem_content,R.id.message_listitem_date,R.id.message_listitem_username};
		
	}

		
	
	@Override
	protected void update(int position, Message item) {
		// TODO Auto-generated method stub
		mBitmapManager.loadBitmap(item.getUser().getAvatar(), imageView(0), ((BitmapDrawable)context.getApplicationContext().getResources().getDrawable(R.drawable.avatar)).getBitmap(), 0, 0);
		imageView(0).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(context, UserActivity.class);
				context.startActivity(intent);
				
			}});
		
		setText(1, item.getUser().getNickName());
		
		setText(2,StringUtils.friendly_time(item.getCreated()));
		
		setText(3,item.getContent());
		

	}
}
	
**/

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void update(int position, Message item) {
		// TODO Auto-generated method stub
		
	}	
	
}
