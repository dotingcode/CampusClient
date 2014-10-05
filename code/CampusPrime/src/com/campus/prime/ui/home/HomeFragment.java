package com.campus.prime.ui.home;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Database.DAOHelper;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.Message;
import com.campus.prime.core.User;
import com.campus.prime.db.MessageDB;
import com.campus.prime.ui.MessageCardPageFragment;
import com.campus.prime.ui.message.MessageEditActivity;
import com.campus.prime.utils.IntentUtil;

public class HomeFragment extends MessageCardPageFragment{
	
		
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		//currentGroup = onMessageUpdateListener.update();
		List<Message> result = null;
		currentPage = service.getPublic();
		if(currentPage != null){
			result = (List<Message>) currentPage.getResults();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onLoadFinished(Loader<List<Message>> arg0, List<Message> arg1) {
		// TODO Auto-generated method stub
		super.onLoadFinished(arg0, arg1);
		if(arg1 != null){
			MessageDB db = (MessageDB)DAOHelper.getInstance().getTable(MessageDB.TABLE);
			db.clearAll();
			for(Message m:arg1)
				m.saveToDB();
		}

	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No Message");
		List<Message> cache = Message.readFromDB();
		items = cache;
		adapter.set(cache);
	}
	
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		if(Auth.user != null){
			if(Auth.user.getCreatedGroup() != null && !Auth.user.getCreatedGroup().isEmpty()){
				inflater.inflate(R.menu.home, menu);
			}
		}
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_refresh:
			refresh();
			return true;
		case R.id.home_edit:
			List<Group> groups = Auth.user.getCreatedGroup();
			Map<String,Serializable> params = new HashMap<String, Serializable>();
			params.put("groups", (Serializable) groups);
			params.put("type",0);
			IntentUtil.startActivity(getActivity(), MessageEditActivity.class, params);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
}
