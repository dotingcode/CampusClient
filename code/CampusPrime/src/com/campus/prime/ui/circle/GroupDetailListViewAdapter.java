package com.campus.prime.ui.circle;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupItem;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.utils.BitmapManager;

public class GroupDetailListViewAdapter extends SingleTypeAdapter<Group>{
	private static BitmapManager mBitmapManager;
	private FinalBitmap mFinalBitmap;
	
	public GroupDetailListViewAdapter(Context context){
		super(context,R.layout.group_detail_item);
		mBitmapManager = BitmapManager.getInstance(context);
		mFinalBitmap = FinalBitmap.create(context);
		this.context = context;
	}

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.gdi_image,R.id.gdi_avatar,R.id.gdi_text_comment_count,
				R.id.gdi_text_like_count,R.id.gdi_text_view_count,R.id.gdi_description,
				R.id.gdi_name};
	}

	@Override
	protected void update(int position, Group item) {
		// TODO Auto-generated method stub
		//mBitmapManager.mediaLoader(null, imageView(0), 0, 0);
		String path = null;
		if(item.getCover() == null || item.getCover().isEmpty())
			path = "/cover/" + "group_cover.jpg";
		else
			path = "/media/" + item.getCover();
		String url = BCSUtils.generateUrl(path);
		mFinalBitmap.display(imageView(0), url);
		mBitmapManager.avatarLoader(item.getAvatar(), imageView(1), 0, 0);
		setText(2, "1");
		setText(3, "1");
		setText(4, item.getTotal() + "");
		setText(5,item.getDescription());
		setText(6,item.getName());
	}
	
	
}
