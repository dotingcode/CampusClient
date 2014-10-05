package com.campus.prime.ui.group;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditTypeFragment extends Fragment
{
	ListView listView;
	List <String> types;
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		View parent = inflater.inflate(R.layout.edit_group_type, null);
		listView = (ListView) parent.findViewById(R.id.fgte_list_view);
		setData();
		setEvent();
		return parent;
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public void onPrepareOptionsMenu(Menu menu) 
	{
		// TODO Auto-generated method stub
		GroupActivity mActivity = (GroupActivity) getActivity();
		ActionBar actionBar = mActivity.getActionBar();		
		menu.add(0,1,0,"save").setIcon(R.drawable.ic_action_save).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);	
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		FragmentManager fm = getActivity().getSupportFragmentManager();
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case 1:
			return true;
		case android.R.id.home:
			fm.popBackStack();
			return true;	
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Bind data to view
	 * 
	 */
	
	public void setData()
	{
		types = new ArrayList<String>();
		types.add("学习小组");
		types.add("社团");
		types.add("学生会");
		types.add("兴趣小组");
		types.add("班级");
		types.add("组织");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.dia_item,R.id.dia_item,types);
		listView.setAdapter(adapter);
	}
	
	/**
	 * Set view event
	 * 
	 */
	
	public void setEvent()
	{
		
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				getActivity().getSupportFragmentManager().popBackStack();
			}});
	}
}

