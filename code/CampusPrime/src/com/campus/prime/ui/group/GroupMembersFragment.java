package com.campus.prime.ui.group;



import java.util.List;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.campus.prime.app.Auth;
import com.campus.prime.core.UserItem;
import com.campus.prime.ui.view.ObservableScrollView;

public class GroupMembersFragment extends UserPageFragment{
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if(listView.getFirstVisiblePosition() == 0){
					GroupActivity activity = (GroupActivity)getActivity();
					ObservableScrollView scrollView = activity.groupFragment.getScrollView();
					scrollView.requestDisallowInterceptTouchEvent(false);
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				GroupActivity activity = (GroupActivity)getActivity();
				ObservableScrollView scrollView = activity.groupFragment.getScrollView();
				boolean isBottom = scrollView.isBottom();
				boolean isTop = listView.getFirstVisiblePosition() == 0 ? true : false;
				if(isBottom){
					if(isTop && event.getAction() == MotionEvent.ACTION_DOWN)
						scrollView.requestDisallowInterceptTouchEvent(false);
					else
						scrollView.requestDisallowInterceptTouchEvent(true);
				}
	            // Handle ListView touch events.
	            v.onTouchEvent(event);
	            return true;
			}
		});
		
	}
	
	@Override
	public void onLoadFinished(Loader<List<UserItem>> arg0, List<UserItem> items) {
		// TODO Auto-generated method stub
		super.onLoadFinished(arg0, items);
		GroupActivity activity = (GroupActivity)getActivity();
		activity.groupFragment.isMember = isMember(items);
		activity.getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
	}
	
	
	private boolean isMember(List<UserItem> items){
		if(items != null){
			if(Auth.user != null){
				for(UserItem item:items){
					if(Auth.user.getId() == item.getId())
						return true;
				}	
				return false;
			}else
				return false;
		}
		return false;
	}
	
	
}
