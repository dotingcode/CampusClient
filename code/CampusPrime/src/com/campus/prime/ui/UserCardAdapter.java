package com.campus.prime.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.cardsui.CardAdapter;
import com.campus.prime.R;
import com.campus.prime.core.UserItem;
import com.campus.prime.utils.BitmapManager;

public class UserCardAdapter extends CardAdapter<UserItem>{
	
	private BitmapManager mBitmapManager;

	public UserCardAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mBitmapManager = BitmapManager.getInstance(context);
	}
	
	@Override
	public View onViewCreated(int index, View recycled, UserItem item) {
		// TODO Auto-generated method stub
		
		ImageView avatarView = (ImageView)recycled.findViewById(R.id.user_avatar);
		mBitmapManager.avatarLoader(item.getAvatar(), avatarView, 0,0);
		TextView nameView = (TextView)recycled.findViewById(R.id.user_name);
		nameView.setText(item.getNickName());
		TextView descriptionView = (TextView)recycled.findViewById(R.id.user_description);
		mBitmapManager.avatarLoader(item.getAvatar(), avatarView, 0, 0);
		return super.onViewCreated(index, recycled, item);
	}
	
	
}
