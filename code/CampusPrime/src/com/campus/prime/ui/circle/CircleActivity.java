package com.campus.prime.ui.circle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.ui.DrawerActivity;
import com.campus.prime.ui.message.MessageEditActivity;
import com.campus.prime.ui.slidingmenu.SlidingMenu;
import com.campus.prime.ui.slidingmenu.SlidingMenu.OnCloseListener;
import com.campus.prime.ui.slidingmenu.SlidingMenu.OnClosedListener;
import com.campus.prime.ui.slidingmenu.SlidingMenu.OnOpenListener;
import com.campus.prime.ui.slidingmenu.SlidingMenu.OnOpenedListener;
import com.campus.prime.utils.IntentUtil;

public class CircleActivity extends DrawerActivity{
	
	private GroupTimelineFragment mTimelineFragment;
	
	private GroupJoinedFragment mJoinedFragment;
	
	public SlidingMenu menu;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// set the Above View
		mTimelineFragment = new GroupTimelineFragment();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mTimelineFragment)
		.commit();

		// configure the SlidingMenu
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.RIGHT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		//menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.behind_slidingmenu);
		
		mJoinedFragment = new GroupJoinedFragment();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.slide_layer, mJoinedFragment)
		.commit();
		// Get state,check whether the first time create the activity
		/**
		if(null != savedInstanceState){
			boolean isFirstCreate = savedInstanceState.getBoolean("isFirstCreate",true);
			if(isFirstCreate)
				menu.toggle();
		}else
			menu.toggle();
			**/
		
		if(null != savedInstanceState){
			boolean isFirstCreate = savedInstanceState.getBoolean("isFirstCreate",true);
			if(isFirstCreate)
				setTitle("我的社团");
		}
		
		menu.setOnOpenedListener(new OnOpenedListener() {
			
			@Override
			public void onOpened() {
				// TODO Auto-generated method stub
				setTitle("我的社团");
				getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
			}
		});
		
		menu.setOnClosedListener(new OnClosedListener() {
			
			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				Group currentGroup = mJoinedFragment.getSelectedGroup();
				if(currentGroup != null)
					setTitle(currentGroup.getName());
					getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
			}
		});
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mJoinedFragment != null){
			//mJoinedFragment.refresh();
		}
		if(mTimelineFragment != null){
			Group currentGroup = mJoinedFragment.getSelectedGroup();
			if(currentGroup != null)
				setTitle(currentGroup.getName());
			if(currentGroup != null){
				mTimelineFragment.setCurrentGroup(currentGroup.getId());
				mTimelineFragment.refresh();
			}
		}
		menu.toggle();
	}	
	
	@Override
	protected void onDrawerClose() {
		// TODO Auto-generated method stub
		super.onDrawerClose();
		menu.setSlidingEnabled(true);
	}
	
	@Override
	protected void onDrawerOpen() {
		// TODO Auto-generated method stub
		super.onDrawerOpen();
		if(menu.isMenuShowing())
			menu.toggle();
		menu.setSlidingEnabled(false);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		if(this.menu.isMenuShowing()){
			menu.clear();
			inflater.inflate(R.menu.all_group,menu);
			showProgress(false);
		}else{
			inflater.inflate(R.menu.circle, menu);
		}
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_refresh:
			mTimelineFragment.refresh();
			return true;
		case R.id.action_add_message:
			Group g = mJoinedFragment.getSelectedGroup();
			if(g != null){
				Map<String,Serializable> params = new HashMap<String,Serializable>();
				params.put("selectedGroup", mJoinedFragment.getSelectedGroup());
				params.put("groups", (Serializable) mJoinedFragment.getItems());
				params.put("type", 1);
				IntentUtil.startActivity(this, MessageEditActivity.class, params);
				
			}
			else
				IntentUtil.startActivity(this, MessageEditActivity.class, null);
			break;
			/**
		case R.id.action_open_circle:
			LinearLayout view = (LinearLayout)findViewById(R.id.left_drawer);
			if(mDrawerLayout.isDrawerOpen(view))
				mDrawerLayout.closeDrawer(view);
			if(!menu.isSlidingEnabled())
				menu.setSlidingEnabled(true);
			menu.toggle();
			break;
			**/
		case R.id.action_all_group:
			IntentUtil.startActivity(this, AllGroupActivity.class, null);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putBoolean("isFirstCreate", false);
	}
	
	public GroupTimelineFragment getTimelineFragment(){
		return this.mTimelineFragment;
	}
	
	
		
}
