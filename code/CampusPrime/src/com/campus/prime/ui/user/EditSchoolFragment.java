package com.campus.prime.ui.user;

import java.util.ArrayList;
import java.util.List;
import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Academy;
import com.campus.prime.core.School;

import android.app.ActionBar;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EditSchoolFragment extends Fragment
{
	
	ListView mListView;
	private List<String> mItems;
	@Override 
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		View parent = inflater.inflate(R.layout.edit_school, null);
		mListView = (ListView) parent.findViewById(R.id.fse_list_view);
		mItems = new ArrayList<String>();
		mItems.add("河北经贸大学");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.edit_item,R.id.edit_item,mItems);
		mListView.setAdapter(adapter);
		this.setViewEvent();
		return parent;
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
		FragmentTransaction ft = fm.beginTransaction();
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			fm.popBackStack();
			break;
		case R.id.action_next:
			ft.replace(R.id.aue_fragment, new EditAcademyFragment()).addToBackStack(null).commit();
			break;

		default:
			break;
		}
		return true;
		
	}
	//Bind data to View
	
	//Set views'events
	public void setViewEvent()
	{
		mListView.setOnItemClickListener(new OnItemClickListener(){
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Auth.user.setSchool(mItems.get(arg2));
				ft.replace(R.id.aue_fragment, new EditAcademyFragment()).addToBackStack(null).commit();
			}});
	}

	
}
