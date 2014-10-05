package com.campus.prime.ui.app.course;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.campus.prime.core.Course;
import com.campus.prime.ui.app.course.CourseFragment.DayCourseListAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CoursePagerAdapter extends FragmentPagerAdapter{
	
	private Context context;
	
	protected static final String[] CONTENT = new String[]{"Mon","Tue","Wen","Thu","Fri","Sat","Sun"};
	
	public CoursePagerAdapter(FragmentManager fm,Context context) {
		super(fm);
		this.context = context;
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0){
		case 0:
			CourseFragment fragment = new CourseFragment();
			fragment.courses = Constant.day1;
			fragment.week = 0;
			((CourseActivity)context).current = 0;
			return fragment;
		case 1:
			CourseFragment fragment1 = new CourseFragment();
			fragment1.courses = Constant.day2;
			fragment1.week = 1;
			((CourseActivity)context).current = 1;
			return fragment1;
		case 2:
			CourseFragment fragment2 = new CourseFragment();
			fragment2.courses = Constant.day3;
			fragment2.week = 3;
			((CourseActivity)context).current = 2;
			return fragment2;
		case 3:
			CourseFragment fragment3 = new CourseFragment();
			fragment3.courses = Constant.day4;
			fragment3.week = 4;
			((CourseActivity)context).current = 3;
			return fragment3;
		case 4:
			CourseFragment fragment4 = new CourseFragment();
			fragment4.courses = Constant.day5;
			((CourseActivity)context).current = 4;
			fragment4.week = 5;
			return fragment4;
		case 5:
			CourseFragment fragment5 = new CourseFragment();
			fragment5.courses = Constant.day6;
			fragment5.week = 6;
			((CourseActivity)context).current = 5;
			return fragment5;
		case 6:
			CourseFragment fragment6 = new CourseFragment();
			 fragment6.courses = Constant.day7;
				fragment6.week = 7;
			((CourseActivity)context).current = 6;
			return fragment6;
		}
		return null;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return CoursePagerAdapter.CONTENT[position % CONTENT.length];
	}
	

	
	
	
}
