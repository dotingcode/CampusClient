package com.campus.prime.ui.app;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ScoreDetailFragment extends Fragment
{
	
	TextView courseName;
	TextView courseType;
	TextView courseTeacther;
	TextView courseCredit;
	TextView courseGpa;	
	TextView courseSorce;
	TextView courseSecondScore;
	TextView courseReexamination;
	TextView courseIsPass;
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		
		View parent = inflater.inflate(R.layout.score_detail, null);
		courseName = (TextView) parent.findViewById(R.id.fsd_course_name);
		courseType = (TextView) parent.findViewById(R.id.fsd_course_type);
		courseTeacther = (TextView) parent.findViewById(R.id.fsd_course_teacther);
		courseCredit = (TextView) parent.findViewById(R.id.fsd_course_credit);
		courseGpa = (TextView) parent.findViewById(R.id.fsd_course_gpa);
		courseSorce = (TextView) parent.findViewById(R.id.fsd_first_score);
		courseSecondScore = (TextView) parent.findViewById(R.id.fsd_second_score);
		courseReexamination = (TextView) parent.findViewById(R.id.fsd_second_score);
		courseIsPass = (TextView) parent.findViewById(R.id.fsd_course_ispass);
		this.setViewData();
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
				fm.popBackStackImmediate();
				return true;
		}
		return false;
	}
	/**
	 * Bind data to view
	 * @author zhangzhen
	 */
	public void setViewData()
	{
		ScoreActivity mActivity =  (ScoreActivity) getActivity();
		courseName.setText(mActivity.currentData.get(mActivity.currentPosition-1).getCourseName());
		courseType.setText(mActivity.currentData.get(mActivity.currentPosition-1).getCourseType());
		courseTeacther.setText(mActivity.currentData.get(mActivity.currentPosition-1).getCourseTeacther());
		courseCredit.setText(mActivity.currentData.get(mActivity.currentPosition-1).getCredit()+"");
		courseGpa.setText(mActivity.currentData.get(mActivity.currentPosition-1).getGpa()+"");
		courseSorce.setText(mActivity.currentData.get(mActivity.currentPosition-1).getFirstScore()+"");
		
		if(mActivity.currentData.get(mActivity.currentPosition-1).getSecondScore()>= 60)
			courseSecondScore.setText(60.0+"");
		else
			courseSecondScore.setText(mActivity.currentData.get(mActivity.currentPosition-1).getSecondScore()+"");
		if(isPass(mActivity.currentData.get(mActivity.currentPosition-1).getFirstScore(),mActivity.currentData.get(mActivity.currentPosition-1).getSecondScore()) == true)
		{
			courseIsPass.setBackgroundColor(0xffbfffbf);
			courseIsPass.setText("通过");
		}
		else
		{
			courseIsPass.setBackgroundColor(Color.RED);
			courseIsPass.setText("未通过");
		}
	}
	
	/**
	 * Judge isPass
	 * @param firstScore
	 * @param secondScore
	 * @return
	 */
	public Boolean isPass(float firstScore , float secondScore)
	{
		if( firstScore >= 60 )
		{
			return true;
		}
		else
		{
			if( secondScore >= 60 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}