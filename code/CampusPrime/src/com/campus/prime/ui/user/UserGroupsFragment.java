package com.campus.prime.ui.user;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardListView;
import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupItem;
import com.campus.prime.core.GroupPage;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.group.GroupActivity;
import com.campus.prime.ui.view.ObservableScrollView;
import com.campus.prime.utils.IntentUtil;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class UserGroupsFragment extends GroupCardPageFragment{
	
	/**
	 * UserId   auth user's default id is -1
	 */
	protected int userId = -1;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No Groups");
		

		listView.setOnCardClickListener(new CardListView.CardClickListener() {
			
			@Override
			public void onCardClick(int index, CardBase card, View view) {
				// TODO Auto-generated method stub
				int groupId = ((Group)adapter.getItem(index)).getId();
				Map<String,Serializable> params = new HashMap<String,Serializable>();
				params.put("group", items.get(index));
				IntentUtil.startActivity(UserGroupsFragment.this.getActivity(), GroupActivity.class, params);
			}
		});
		
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
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Group> load() {
		// TODO Auto-generated method stub
		int userId = getUserId();
		List<Group> result = null;
		if(userId == -1)
			currentPage = (GroupPage) service.getGroups();
		else
			currentPage = (GroupPage) service.getGroupsByUserid(userId);
		if(currentPage != null)
			result = (List<Group>) currentPage.getResults();
		if(Auth.user.getCreatedGroup() != null && !Auth.user.getCreatedGroup().isEmpty()){
			if(result == null)
				result = new ArrayList<Group>();
			result.add(0, Auth.user.getCreatedGroup().get(0));
		}
		return result;
	}

	@Override
	protected CardAdapter createCardAdapter(List<Group> items) {
		// TODO Auto-generated method stub
		return new GroupCardAdapter(this.getActivity());
	}
	
	protected int getUserId(){
		if(getActivity() instanceof UserActivity)
			return ((UserActivity)getActivity()).getUserId();
		else 
			return -1;
	}
	
	
}
