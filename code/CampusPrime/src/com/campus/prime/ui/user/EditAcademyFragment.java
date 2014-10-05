package com.campus.prime.ui.user;

import java.util.ArrayList;
import java.util.List;
import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Academy;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditAcademyFragment extends Fragment
{
	ListView mListView;
	List<String> mItems = new ArrayList<String>();
	
	@Override 
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		View parent = inflater.inflate(R.layout.edit_academy, null);
		mListView = (ListView) parent.findViewById(R.id.fae_list_view);
		this.setData();
		this.setViewEvent();
		return parent;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
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
		inflater.inflate(R.menu.next, menu);
		super.onCreateOptionsMenu(menu, inflater);
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
			ft.replace(R.id.aue_fragment, new EditGradeFragment()).addToBackStack(null).commit();
			break;
		default:
			break;
		}
		return false;
		
	}
	//Bind data to View
	public void setData()
	{
		mItems.add("信息技术学院");
		mItems.add("人文学院");
		mItems.add("艺术学院");
		mItems.add("工商管理学院");
		mItems.add("商学院");
		mItems.add("体育学院");
		mItems.add("公共管理学院");
		mItems.add("金融学院");
		mItems.add("信息技术学院");
		mItems.add("人文学院");
		mItems.add("艺术学院");
		mItems.add("工商管理学院");
		mItems.add("商学院");
		mItems.add("体育学院");
		mItems.add("公共管理学院");
		mItems.add("金融学院");
		mItems.add("信息技术学院");
		mItems.add("人文学院");
		mItems.add("艺术学院");
		mItems.add("工商管理学院");
		mItems.add("商学院");
		mItems.add("体育学院");
		mItems.add("公共管理学院");
		mItems.add("金融学院");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.edit_item,R.id.edit_item,mItems);
		mListView.setAdapter(adapter);	
	}
	//Set views'events
	public void setViewEvent()
	{
		mListView.setOnItemClickListener(new OnItemClickListener(){
			FragmentTransaction ft  = getActivity().getSupportFragmentManager().beginTransaction();
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Auth.user.setAcademy(mItems.get(arg2));
				ft.replace(R.id.aue_fragment, new EditGradeFragment()).addToBackStack(null).commit();
			}});

	}
	
	
}
