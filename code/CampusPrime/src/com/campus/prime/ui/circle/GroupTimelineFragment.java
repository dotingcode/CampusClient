package com.campus.prime.ui.circle;

import java.util.List;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.campus.prime.core.Message;
import com.campus.prime.ui.MessageCardPageFragment;

public class GroupTimelineFragment extends MessageCardPageFragment{
		
	private int mGroupId = -1;
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		//currentGroup = onMessageUpdateListener.update();
		List<Message> result = null;
		if(mGroupId != -1){
			currentPage = service.getGroupPublic(mGroupId);
		}
		if(currentPage != null)
			result = (List<Message>) currentPage.getResults();
		return result;
	}
	
	@Override
	public void onLoaderReset(Loader<List<Message>> arg0) {
		// TODO Auto-generated method stub
		super.onLoaderReset(arg0);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		setEmptyText("No Message");
		SetRefreshEnable(true);
	}
	
	public void setCurrentGroup(int id){
		this.mGroupId = id;
	}
}
