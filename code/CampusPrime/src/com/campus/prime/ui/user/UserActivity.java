package com.campus.prime.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.core.User;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.indicator.TabPageIndicator;
import com.campus.prime.ui.view.ObservableScrollView;
import com.campus.prime.utils.BitmapManager;

public class UserActivity extends BaseActivity{
	public int mUserId;
	private static BitmapManager mBitmapManager;
	private ImageView mAvatarView;
	private TextView mNicknameView;
	
	public User user;
	
	public UserService service = new UserService();
	
	
	private ObservableScrollView mScrollView;
	
	/**
	 * pager Adapter
	 */
	protected UserPagerAdapter mAdapter;
	/**
	 * View Pager
	 */
	protected ViewPager mPager;
	
	/*
	 * View Indicator
	 */
	protected TabPageIndicator mIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		Intent intent = getIntent();
		mUserId = intent.getIntExtra("userId", -1);
		int page = getIntent().getIntExtra("page", -1);
		mBitmapManager = BitmapManager.getInstance(this);
		mAdapter = new UserPagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager)findViewById(R.id.au_pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (TabPageIndicator)findViewById(R.id.au_indicator);
		mIndicator.setViewPager(mPager);
		if(page != -1)
			mIndicator.setCurrentItem(page);
		
		mScrollView = (ObservableScrollView)findViewById(R.id.au_scroll);
		mAvatarView = (ImageView)findViewById(R.id.au_avatar);
		mBitmapManager.avatarLoader(null, mAvatarView, 0, 0);
		mNicknameView = (TextView)findViewById(R.id.au_nickname);
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == android.R.id.home){
			finish();
			return true;
		}else
			return super.onOptionsItemSelected(item);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
	}

	public int getUserId(){
		return mUserId;
	}
	
	public void bindData(){
		mBitmapManager.avatarLoader(user.getAvatar(), mAvatarView, 0, 0);
		mNicknameView.setText(user.getNickName());
	}
	
	
	public ObservableScrollView getScrollView(){
		return mScrollView;
	}
	
	
	
}
