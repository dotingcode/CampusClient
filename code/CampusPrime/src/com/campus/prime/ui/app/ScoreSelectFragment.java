package com.campus.prime.ui.app;

import com.campus.prime.R;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ScoreSelectFragment extends Fragment
{
	private static final String [] grades = {"第一学年","第二学年","第三学年","第四学年","全部"};
	private static final String [] semesters = {"第一学期","第二学期","全部"};
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstance)
	{
		View parent = inflater.inflate(R.layout.score_select, null);
		Spinner mGrade = (Spinner) parent.findViewById(R.id.fss_grade);
		ArrayAdapter gradeAdapter  = new ArrayAdapter(getActivity(),R.layout.spinner_item,R.id.si_item,grades);
		mGrade.setAdapter(gradeAdapter); 
		mGrade.setSelection(0);
		mGrade.setBackgroundResource(R.drawable.sel_score_spinner);
		mGrade.setOnItemSelectedListener( new OnItemSelectedListener(){
			ScoreActivity mActivity = (ScoreActivity) getActivity();
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mActivity.currentGrade = arg2 +1;			
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			});
		Spinner mSemester = (Spinner) parent.findViewById(R.id.fss_semester);
		ArrayAdapter semesterAdapter  = new ArrayAdapter(getActivity(),R.layout.spinner_item,R.id.si_item,semesters);
		mSemester.setAdapter(semesterAdapter);
		mSemester.setSelection(0);
		mSemester.setBackgroundResource(R.drawable.sel_score_spinner);
		mSemester.setOnItemSelectedListener( new OnItemSelectedListener(){
			ScoreActivity mActivity = (ScoreActivity) getActivity();
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mActivity.currentSemester = arg2 +1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
		Button button = (Button) parent.findViewById(R.id.fss_search);
		button.setOnClickListener(new OnClickListener(){
			FragmentManager fm = getActivity().getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ScoreActivity mActivity = (ScoreActivity) getActivity();
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in,
						R.anim.push_right_out);
				ft.addToBackStack(null).replace(R.id.ass_fragment, new ScoreListFragment()).commit();
			}});
		
		return parent;
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onPrepareOptionsMenu(Menu menu) 
	{
		// TODO Auto-generated method stub
		
		
		ScoreActivity mActivity =  (ScoreActivity) getActivity();
		ActionBar actionBar = mActivity.getActionBar();	
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		FragmentManager fm = getActivity().getSupportFragmentManager();
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{	
			case android.R.id.home:
				getActivity().finish();
				return true;
		}
		return false;
	}	
}
