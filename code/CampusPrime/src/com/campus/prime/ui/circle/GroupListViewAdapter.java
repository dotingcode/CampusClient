package com.campus.prime.ui.circle;

import android.content.Context;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.utils.BitmapManager;

public class GroupListViewAdapter extends SingleTypeAdapter<Group>{
	BitmapManager mBitmapManager;
	
	public GroupListViewAdapter(Context context){
		super(context,R.layout.group_item);
		mBitmapManager = BitmapManager.getInstance(context);
		this.context = context;
	}


	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.gi_avatar,R.id.gi_name};
	}

	@Override
	protected void update(int position, Group item) {
		// TODO Auto-generated method stub
		mBitmapManager.avatarLoader(item.getAvatar(), imageView(0), 0, 0,BitmapManager.SQUARE);
		
		setText(1,item.getName());
	}

	
	
}

