package com.campus.prime.ui.user;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;
import com.campus.prime.app.Auth;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditGradeFragment extends Fragment
{
	ListView mListView;
	List<String> mItems = new ArrayList<String>();
	
	@Override 
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		View parent = inflater.inflate(R.layout.edit_grade, null);
		mListView = (ListView) parent.findViewById(R.id.fge_list_view);
		this.setData();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.edit_item,R.id.edit_item,mItems);
		mListView.setAdapter(adapter);
		
		View view = this.getActivity().findViewById(R.layout.edit_item);
		int totalHeight=0;
		Log.i("viewheight",adapter.getCount()+"");		
		for(int i=0, len = adapter.getCount();i<len;i++)
		{
			View listItem = adapter.getView(i, view, mListView);		
			listItem.measure(0,0);
			totalHeight += listItem.getMeasuredHeight();

		}
		 ViewGroup.LayoutParams params =mListView.getLayoutParams(); 
		 params.height = totalHeight + (mListView.getDividerHeight() * (adapter.getCount() - 1)); 	
		 params.height=params.height;
		 mListView.setLayoutParams(params);
		
		return parent;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		this.setViewEvent();
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.next, menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		FragmentManager fm = getActivity().getSupportFragmentManager();
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{	
			case android.R.id.home:
				fm.popBackStack();
				break;
			case R.id.action_next:
				fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				break;
		}
		return false;
	}
	
	//Bind data to View
	public void setData()
	{
		mItems.add("2013");
		mItems.add("2012");
		mItems.add("2011");
		mItems.add("2010");	
		mItems.add("2009");
		mItems.add("2008");			
	}
	
	//Set views'events
	public void setViewEvent()
	{
		
		mListView.setOnItemClickListener(new OnItemClickListener(){
			FragmentManager fm = getActivity().getSupportFragmentManager();
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Auth.user.setGrade(mItems.get(arg2));
				fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});
	}
	
	
}