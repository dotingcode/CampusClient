package com.campus.prime.ui.user;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.cardsui.CardAdapter;
import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.utils.BitmapManager;

public class GroupCardAdapter extends CardAdapter<Group>{
	
	private BitmapManager mBitmapManager;

	public GroupCardAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mBitmapManager = BitmapManager.getInstance(context);
	}
	
	@Override
	public View onViewCreated(int index, View recycled, Group item) {
		// TODO Auto-generated method stub
		ImageView avatarView = (ImageView)recycled.findViewById(R.id.gci_avatar);
		mBitmapManager.avatarLoader(item.getAvatar(), avatarView, 0, 0);
		TextView nameView = (TextView)recycled.findViewById(R.id.gci_name);
		nameView.setText(item.getName());
		TextView descriptionView = (TextView)recycled.findViewById(R.id.gci_description);
		descriptionView.setText(item.getDescription());
		return super.onViewCreated(index, recycled, item);
	}

}
