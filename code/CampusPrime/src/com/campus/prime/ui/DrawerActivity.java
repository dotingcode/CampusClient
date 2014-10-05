package com.campus.prime.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.push.client.BaiduPush;
import com.campus.prime.ui.app.SchoolCalendarActivity;
import com.campus.prime.ui.app.ScoreActivity;
import com.campus.prime.ui.app.course.CourseActivity;
import com.campus.prime.ui.circle.CircleActivity;
import com.campus.prime.ui.circle.CreateGroupActivity;
import com.campus.prime.ui.group.GroupActivity;
import com.campus.prime.ui.home.HomeActivity;
import com.campus.prime.ui.home.LoginActivity;
import com.campus.prime.ui.home.RegisterActivity;
import com.campus.prime.ui.home.SettingActivity;
import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.LogFactory;
import com.campus.prime.utils.SharedPreferenceUtil;

public class DrawerActivity extends BaseActivity {

	protected DrawerLayout mDrawerLayout;
	protected ActionBarDrawerToggle mDrawerToggle;
	protected CharSequence mDrawTitle;
	
	protected static SharedPreferences infos;
	protected static BitmapManager mBitmapManager;
	
	
	protected ImageView mAvatarView;
	protected TextView mUsernameView;
	protected TextView mMyGroupCountView;
	protected TextView mMyTweetCountView;
	protected TextView mMyJoinedCountView;
	
	
	protected ExpandableListView appListView;
	/**
	 * log
	 */
	protected CommonLog mLog = LogFactory.createLog();
	
	protected boolean mIsNotifyClickable = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mBitmapManager = BitmapManager.getInstance(this);
		
		mAvatarView = (ImageView)findViewById(R.id.drawer_user_avatar);
		mUsernameView = (TextView)findViewById(R.id.drawer_username);
		mMyGroupCountView = (TextView)findViewById(R.id.drawer_my_group_count);
		mMyTweetCountView = (TextView)findViewById(R.id.drawer_my_tweet_count);
		mMyJoinedCountView = (TextView)findViewById(R.id.drawer_my_circle_count);
		
		initDrawer();
		appListView = (ExpandableListView) findViewById(R.id.drawer_menu_groups);
		MenuGroupAdapter adapter = new MenuGroupAdapter(this);
		appListView.setAdapter(adapter);
		appListView.setOnGroupClickListener(new OnGroupClickListener(){

				@Override
				public boolean onGroupClick(ExpandableListView parent, View v,
						int groupPosition, long id) {
					// TODO Auto-generated method stu
					switch(groupPosition)
					{
						case 0:
							if(Auth.isAuthed()){
								if(!(DrawerActivity.this instanceof HomeActivity)){
									IntentUtil.startActivity(DrawerActivity.this, HomeActivity.class, null);
									DrawerActivity.this.finish();
								}else
									mDrawerLayout.closeDrawers();
							}else{
								Toast.makeText(DrawerActivity.this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();	
							}
						break;
						case 1:
							if(Auth.isAuthed()){
								if(!(DrawerActivity.this instanceof CircleActivity)){
									IntentUtil.startActivity(DrawerActivity.this, CircleActivity.class, null);
									DrawerActivity.this.finish();
								}
								else
									mDrawerLayout.closeDrawers();
							}else{
								Toast.makeText(DrawerActivity.this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();
							}
							break;
						case 2:
							if(Auth.isAuthed()){
								IntentUtil.startActivity(DrawerActivity.this, NotificationActivity.class, null);
								SharedPreferenceUtil util = SharedPreferenceUtil.getInstance();
								util.setNotificationCount(0);
								DrawerActivity.this.finish();
							}else{			
								Toast.makeText(DrawerActivity.this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();	
							}
							break;
						case 4:
							if(Auth.isAuthed()){
								IntentUtil.startActivity(DrawerActivity.this, SettingActivity.class, null);
								DrawerActivity.this.finish();
							}else{
								Toast.makeText(DrawerActivity.this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();	
							}
							break;
						default:
							if(Auth.isAuthed()){
								SharedPreferences sp = AppContext.getContext().getSharedPreferences(AppConstant.SETTING_INFOS,Context.MODE_PRIVATE);
								if(sp.getBoolean("isSchoolAuth", false)){
									return false;
								}else{
									IntentUtil.startActivity(DrawerActivity.this, ImportSchoolInfoActivity.class, null);
									return true;
								}
							}else{
								Toast.makeText(DrawerActivity.this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();
							}
							break;
					}
					return true;
					
				}
		});
		appListView.setOnChildClickListener(new OnChildClickListener(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				
				if(groupPosition == 3 && childPosition == 0){
					IntentUtil.startActivity(DrawerActivity.this, SchoolCalendarActivity.class, null);
				}
				else if(groupPosition == 3 && childPosition == 1){
					IntentUtil.startActivity(DrawerActivity.this, CourseActivity.class, null);
					
				}
				else if(groupPosition == 3 && childPosition == 2){
					IntentUtil.startActivity(DrawerActivity.this, ScoreActivity.class, null);
				}
				
				return false;
			}});
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(mDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public static boolean isAppMenuOpen = false;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		bindData();
		if(isAppMenuOpen){
			appListView.expandGroup(3);
			DrawerActivity.isAppMenuOpen =false;
		}
		appListView.invalidateViews();
	}
	
	

	private void initDrawer(){
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		 //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, 
				mDrawerLayout, 
				R.drawable.ic_drawer, 
				R.string.drawer_open, 
				R.string.drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				invalidateOptionsMenu();
				onDrawerClose();
			}
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(mDrawTitle);
				invalidateOptionsMenu();
				onDrawerOpen();
			}
			
			
		};

		if(!Auth.isAuthed()){
			mBitmapManager.avatarLoader(null, mAvatarView, 0, 0);
			mUsernameView.setText("Çëµã»÷µÇÂ¼");
		}
		
		
		bindData();
	}
	
	private void bindData(){
		if(Auth.user != null){
			mBitmapManager.avatarLoader(Auth.user.getAvatar(), mAvatarView, 0, 0);
			mUsernameView.setText(Auth.user.getNickName());
			mMyGroupCountView.setText(Auth.user.getCreatedGroupCount() + "");
			mMyTweetCountView.setText(Auth.user.getMessageCount() + "");
			mMyJoinedCountView.setText(Auth.user.getJoinedGroupCount() + "");
		}
	}
	
	protected void onDrawerClose(){
		
	}
	
	
	protected void onDrawerOpen(){
		
	}
	
	public void menuClicked(View view){
	}

	public void tabClicked(View view){
		switch(view.getId()){
		case R.id.drawer_user_info:
			if(Auth.isAuthed()){
				IntentUtil.startActivity(this, UserActivity.class, null);
			}else{
				finish();
				IntentUtil.startActivity(this, LoginActivity.class, null);
			}
			break;
		case R.id.drawer_tab_my_group:
			if(Auth.isAuthed()){
				if(Auth.user.getCreatedGroupCount() == 0){
					IntentUtil.startActivity(this, CreateGroupActivity.class, "page",2);
				}else{
					IntentUtil.startActivity(this, GroupActivity.class, "group",Auth.user.getCreatedGroup().get(0));
				}
			}else{
				Toast.makeText(this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.drawer_tab_my_tweet:
			if(Auth.isAuthed()){
				IntentUtil.startActivity(this, UserActivity.class, "page",1);
			}else{
				Toast.makeText(this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.drawer_tab_my_circle:
			if(Auth.isAuthed()){
				IntentUtil.startActivity(this, UserActivity.class, null);
			}else{
				Toast.makeText(this, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	 /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    
    public class PushMessageTask extends AsyncTask<Void, Void, Void>{
		BaiduPush push = BaiduPush.getInstance();
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String recieve = push.PushNotify("enheng", "fdjkfdj", "630024774972968197");
			log.d(recieve);
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}
    
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(keyCode == KeyEvent.KEYCODE_BACK) {
		        // ¼à¿Ø·µ»Ø¼ü
		        new Builder(DrawerActivity.this).setTitle("ÌáÊ¾")
		                .setIconAttribute(android.R.attr.alertDialogIcon)
		                .setMessage("È·¶¨ÒªÍË³öÂð?")
		                .setPositiveButton("È·ÈÏ", new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which) {
		                        DrawerActivity.this.finish();
		                    }})
		                .setNegativeButton("È¡Ïû", null)
		                .create().show();
		        return false;
		    } 
		}
		return super.onKeyDown(keyCode, event);
    }
    
}
