package com.campus.prime.ui.message;

import android.content.Context;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.utils.BitmapManager;

public class GroupsDropDownListAdapter extends SingleTypeAdapter<Group>{
	

	private final BitmapManager mBitmapManager;
	
	
	public GroupsDropDownListAdapter(Context context) {
		super(context, R.layout.org_item);
		// TODO Auto-generated constructor stub
		mBitmapManager = BitmapManager.getInstance(context);
	}
	
	
	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.tv_org_name,R.id.iv_avatar};
	}
		
	
	@Override
	protected void update(int position, Group item) {
		// TODO Auto-generated method stub
		setText(0,item.getName());
		mBitmapManager.avatarLoader(item.getAvatar(), imageView(1), 0, 0);
	}
}
