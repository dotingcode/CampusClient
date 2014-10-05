package com.campus.prime.ui;

import com.campus.prime.R;
import com.campus.prime.db.NotificationDB;

import Database.DAOHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NotificationActivity extends DrawerActivity{
	
	private NotificationFragment mNotificationFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mNotificationFragment = new NotificationFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.content_frame, mNotificationFragment).commit();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		getMenuInflater().inflate(R.menu.clear_all, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_clear_all:
			NotificationDB db = (NotificationDB) DAOHelper.getInstance().getTable(NotificationDB.TABLE);
			db.clearAll();
			mNotificationFragment.mNotifications = null;
			mNotificationFragment.refresh();
			mNotificationFragment.setEmptyViewShow(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
