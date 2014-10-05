package com.campus.prime.ui.circle;

import android.content.Context;
import android.view.View;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.utils.BitmapManager;

public class CircleListViewAdapter extends SingleTypeAdapter<Group>{

	private static BitmapManager mBitmapManager;
	
	/**
	 * Current selected item
	 * default 0 stands for the first group in the list
	 */
	private int mSelected = 0;
	
	public CircleListViewAdapter(Context context) {
		super(context, R.layout.circle_item);
		// TODO Auto-generated constructor stub
		mBitmapManager = BitmapManager.getInstance(context);
	}

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[] {R.id.ci_avatar,R.id.ci_name,R.id.check_flag};
	}

	@Override
	protected void update(int position, Group item) {
		// TODO Auto-generated method stub
		setText(1, item.getName());
		mBitmapManager.avatarLoader(item.getAvatar(), imageView(0), 0, 0);
		if(mSelected == position)
			imageView(2).setVisibility(View.VISIBLE);
		else
			imageView(2).setVisibility(View.INVISIBLE);
	}

	public void setSelected(int i){
		this.mSelected = i;
	}
	
	public Group getSlectedGroup(){
		if(getItems() != null && !getItems().isEmpty())
			return getItem(mSelected);
		return null;
	}
	

}
