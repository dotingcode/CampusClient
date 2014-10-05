package com.campus.prime.ui.group;

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

public class EditInformationFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		View parent = inflater.inflate(R.layout.edit_group_information, null);
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
	
}
