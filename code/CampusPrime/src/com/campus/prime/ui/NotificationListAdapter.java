package com.campus.prime.ui;

import android.content.Context;
import android.view.View;

import com.campus.prime.R;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.push.client.Notification;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public class NotificationListAdapter extends SingleTypeAdapter<Notification>{

	private BitmapManager mBitmapManager;
	
	public NotificationListAdapter(Context context) {
		super(context, R.layout.notification_item);
		mBitmapManager = BitmapManager.getInstance(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.ni_avatar,R.id.ni_description,R.id.ni_detail,R.id.ni_detail_icon};
	}

	@Override
	protected void update(int position, Notification item) {
		// TODO Auto-generated method stub
		CommonLog log = LogFactory.createLog(AppConstant.TAG);
		//log.i(item.getMessage().toString());
		String description = "";
		switch(item.getType()){
		case 1:
			description = item.getFromUserName() + "赞了你的消息";
			textView(1).setText(description);
			textView(2).setText(item.getMessage().getContent());
			imageView(3).setVisibility(View.VISIBLE);
			break;
		case 2:
			description = item.getFromUserName() + "评论了你的消息";
			textView(1).setText(description);
			textView(2).setText(item.getMessage().getContent());
			imageView(3).setVisibility(View.VISIBLE);
			break;
		case 3:
			description = item.getFromUserName() + "加入了你的社团";
			textView(1).setText(description);
			imageView(3).setVisibility(View.INVISIBLE);
			break;
		}
		mBitmapManager.avatarLoader(item.getFromUserAvatar(), imageView(0), 0, 0);
	}

}
