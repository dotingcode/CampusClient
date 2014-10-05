package com.campus.prime.ui;

import java.util.List;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.push.client.Notification;
import com.campus.prime.ui.message.MessageDetailActivity;
import com.campus.prime.utils.IntentUtil;

public class NotificationFragment extends PagedItemFragment<Notification>{

	
	
	public List<Notification> mNotifications  = null;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		setRefreshEnable(false);
		setEmptyText("没有通知");
		mListView.setDivider(AppContext.getContext().getResources().getDrawable(R.drawable.notification_item_divider));
		mListView.setDividerHeight(2);
		mListView.setSelector(AppContext.getContext().getResources().getDrawable(R.drawable.sel_linearlayout_bg));
		
		mNotifications = Notification.readFromDB();
		if(mNotifications == null || mNotifications.isEmpty()){
			setEmptyViewShow(true);
		}else{
			setEmptyViewShow(false);
		}

	}

	
	
	@Override
	protected List<Notification> load() {
		// TODO Auto-generated method stub
		return mNotifications;
	}

	@Override
	protected List<Notification> next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected SingleTypeAdapter<Notification> createAdapter(
			List<Notification> items) {
		// TODO Auto-generated method stub
		return new NotificationListAdapter(getActivity());
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(mItems.get(position).getMessage() != null)
			IntentUtil.startActivity(getActivity(), MessageDetailActivity.class, "message",mItems.get(position).getMessage());
		
	}
	
	
}
