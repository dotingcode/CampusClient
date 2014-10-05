package com.campus.prime.ui.app.course;

import java.util.Calendar;
import java.util.Date;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;


import com.campus.prime.R;
import com.campus.prime.core.Course;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.indicator.PageIndicator;
import com.campus.prime.ui.indicator.TabPageIndicator;

public class CourseActivity extends BaseActivity{
	/**
	 * pager Adapter
	 */
	protected CoursePagerAdapter mAdapter;
	/**
	 * View Pager
	 */
	protected ViewPager mPager;
	
	/*
	 * View Indicator
	 */
	protected TabPageIndicator mIndicator;
	
	public int current;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		mOpenRefresh = false;
		showProgress(false);
		
		mAdapter = new CoursePagerAdapter(getSupportFragmentManager(),this);
		mPager = (ViewPager)findViewById(R.id.ac_pager);
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(getWeekDay());
		mIndicator = (TabPageIndicator)findViewById(R.id.ac_indicator);
		mIndicator.setViewPager(mPager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
	}

	public int getWeekDay(){
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());  

		calendar.setTime(date);  
	    int weekDay=calendar.get(Calendar.DAY_OF_WEEK)-1;  
	    return weekDay;   
		
	}

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 // TODO Auto-generated method stub
		 switch(item.getItemId()){
		 
		 case android.R.id.home:
			 this.finish();
			 break;					   
				   
		 default:
			 return super.onOptionsItemSelected(item);
			 
		 }
		 return false;
	 }
	
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	
	

}


