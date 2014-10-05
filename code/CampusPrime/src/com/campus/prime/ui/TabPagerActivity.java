package com.campus.prime.ui;

import com.campus.prime.R;
import com.campus.prime.ui.indicator.PageIndicator;
import com.campus.prime.ui.indicator.TitlePageIndicator;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public abstract class TabPagerActivity<V extends FragmentPagerAdapter> 
		extends BaseActivity{
	/**
	 * pager Adapter
	 */
	protected V mAdapter;
	/**
	 * View Pager
	 */
	protected ViewPager mPager;
	
	/*
	 * View Indicator
	 */
	protected PageIndicator mIndicator;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		View view = getLayoutInflater().inflate(R.layout.tab_pager, null);
		setContentView(view);
		mAdapter = createAdapter();
		mPager = (ViewPager)view.findViewById(R.id.user_pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (TitlePageIndicator)findViewById(R.id.user_indicator);
		mIndicator.setViewPager(mPager);
		
		int page = getIntent().getIntExtra("page", -1);
		if(page != -1)
			mIndicator.setCurrentItem(page);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	protected abstract V createAdapter();
	
	
	
}
