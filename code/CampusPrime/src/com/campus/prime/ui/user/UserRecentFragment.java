package com.campus.prime.ui.user;


import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.campus.prime.core.Message;
import com.campus.prime.core.MessagePage;
import com.campus.prime.ui.MessageCardPageFragment;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.view.ObservableScrollView;


public class UserRecentFragment extends MessageCardPageFragment{
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		int userId = ((UserActivity)getActivity()).getUserId();
		if(userId == -1)
			currentPage = (MessagePage) service.getUser();
		else{
			try {
				currentPage = (MessagePage)service.getUserById(userId);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		if(currentPage != null)
			return (List<Message>) currentPage.getResults();
		return null;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No user Recent");
		
		listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
					if(listView.getFirstVisiblePosition() == 0){
						UserActivity activity = (UserActivity)getActivity();
						ObservableScrollView scrollView = activity.getScrollView();
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
		
		
		listView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				UserActivity activity = (UserActivity)getActivity();
				ObservableScrollView scrollView = activity.getScrollView();
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
