package com.campus.prime.ui.group;

import java.util.List;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ScrollView;

import com.afollestad.cardsui.CardAdapter;
import com.campus.prime.core.Message;
import com.campus.prime.ui.MessageCardPageFragment;
import com.campus.prime.ui.view.ObservableScrollView;

public class GroupRecentFragment extends MessageCardPageFragment{
	
	private boolean direction = false;
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		List<Message> result = null;
		GroupActivity activity = (GroupActivity)getActivity();
		int groupId;
		if(activity.group == null)
			groupId = activity.groupItem.getId();
		else
			groupId = activity.group.getId();
		currentPage = service.getGroup(groupId);
		if(currentPage != null)
			result = (List<Message>)currentPage.getResults();
		return result;
	}
	
	@Override
	protected CardAdapter<Message> createCardAdapter(List<Message> items) {
		// TODO Auto-generated method stub
		return super.createCardAdapter(items);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No Recent Message");
		SetRefreshEnable(false);
		
		listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
					if(listView.getFirstVisiblePosition() == 0){
						GroupActivity activity = (GroupActivity)getActivity();
						ObservableScrollView scrollView = activity.groupFragment.getScrollView();
						scrollView.requestDisallowInterceptTouchEvent(false);
					}
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listView.setOnTouchListener(new ListView.OnTouchListener() {
			
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
}
