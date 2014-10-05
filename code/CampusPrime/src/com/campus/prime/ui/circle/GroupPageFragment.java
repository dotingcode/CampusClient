package com.campus.prime.ui.circle;

import java.util.List;

import android.os.Bundle;

import com.campus.prime.core.Group;
import com.campus.prime.core.GroupPage;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.ui.PagedItemFragment;
import com.campus.prime.ui.SingleTypeAdapter;

public class GroupPageFragment extends PagedItemFragment<Group>{


	/**
	 * service for loading data
	 */
	protected GroupService mService = new GroupService();
	
	
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
		mCurrentPage = mService.getGroups();
		if(mCurrentPage != null)
			result = (List<Group>)mCurrentPage.getResults();
		/**
		if(Auth.user.getCreatedGroup() != null && !Auth.user.getCreatedGroup().isEmpty()){
			if(result == null)
				result = new ArrayList<Group>();
			result.add(0, Auth.user.getCreatedGroup().get(0));
		}
		**/
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Group> next() {
		// TODO Auto-generated method stub
		if(hasNext()){
			mCurrentPage = mService.getNext(mCurrentPage.getNext());
			if(mCurrentPage != null)
				return (List<Group>)mCurrentPage.getResults();
			else
				return null;
		}else
			return null;
	}

	@Override
	protected SingleTypeAdapter<Group> createAdapter(List<Group> items) {
		// TODO Auto-generated method stub
		return new GroupListViewAdapter(this.getActivity());
	}



}
