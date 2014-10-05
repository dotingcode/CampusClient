package com.campus.prime.ui.user;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupItem;
import com.campus.prime.core.GroupPage;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.ui.PagedCardFragment;
import com.campus.prime.ui.PagedItemFragment;

public abstract class GroupCardPageFragment extends PagedCardFragment<Group> {
	
	
	/**
	 * service for loading data
	 */
	protected GroupService service = new GroupService();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCurrentPage(new GroupPage());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Group> load() {
		// TODO Auto-generated method stub
		List<Group> result = null;
		currentPage = service.getGroups();
		if(currentPage != null)
			result = (List<Group>)currentPage.getResults();
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Group> next() {
		// TODO Auto-generated method stub
		if(hasNext()){
			currentPage = service.getNext(currentPage.getNext());
			if(currentPage != null)
				return (List<Group>)currentPage.getResults();
			else
				return null;
		}
		return null;
	}

	

}
