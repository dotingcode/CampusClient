package com.campus.prime.ui.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardListView;
import com.campus.prime.core.UserItem;
import com.campus.prime.core.UserPage;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.PagedCardFragment;
import com.campus.prime.ui.PagedItemFragment;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.UserCardAdapter;
import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.utils.IntentUtil;


public class UserPageFragment extends PagedCardFragment<UserItem>{
	
	/**
	 * service for loading user page data
	 */
	protected UserService service = new UserService(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCurrentPage(new UserPage());
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		listView.setOnCardClickListener(new CardListView.CardClickListener() {
			
			@Override
			public void onCardClick(int index, CardBase card, View view) {
				// TODO Auto-generated method stub
				int userId = adapter.getItem(index).getId();
				Map<String,Integer> params = new HashMap<String,Integer>();
				params.put("userId",userId);
				IntentUtil.startActivity(UserPageFragment.this.getActivity(), UserActivity.class, params);
						
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<UserItem> load() {
		// TODO Auto-generated method stub
		List<UserItem> result = null;
		//currentPage = service.getUsersByGroup(((GroupActivity) this.getActivity()).getGroupId());
		GroupActivity activity = (GroupActivity)getActivity();
		int groupId;
		if(activity.group == null)
			groupId = activity.groupItem.getId();
		else
			groupId = activity.group.getId();
		currentPage = service.getUsersByGroup(groupId);
		if(currentPage != null)
			result = (List<UserItem>)currentPage.getResults();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<UserItem> next() {
		// TODO Auto-generated method stub
		if(hasNext()){
			currentPage = service.getNext(currentPage.getNext());
			if(currentPage != null)
				return (List<UserItem>)currentPage.getResults();
			else
				return null;
		}
		return null;
	}

	@Override
	protected CardAdapter<UserItem> createCardAdapter(List<UserItem> items) {
		// TODO Auto-generated method stub
		return new UserCardAdapter(this.getActivity());
	}


}
