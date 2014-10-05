package com.campus.prime.ui;

import static com.campus.prime.constant.AppConstant.TAG;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;

import com.campus.prime.R;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 
 * @author absurd
 *
 */
public class BaseActivity extends ActionBarActivity{
	
	protected CommonLog log = LogFactory.createLog(TAG);
	
	private PullToRefreshAttacher mPullToRefreshAttacher;
	
	public PullToRefreshAttacher getPullToRefreshAttacher(){
		return this.mPullToRefreshAttacher;
	}
	
	protected ActionBar mActionBar;
	
	protected Menu mOptionsMenu;
	
	private boolean mFirst = true;
	protected boolean mOpenRefresh = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		//setProgressBarIndeterminateVisibility(false);
		super.onCreate(savedInstanceState);
		
		mActionBar = getSupportActionBar();
		configureActionBar(mActionBar);
		
		mPullToRefreshAttacher = PullToRefreshAttacher.get(this);
	}
	
	private void configureActionBar(ActionBar actionBar){
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	/**
	 * Set activity current title
	 * @param title
	 */
	public void setTitle(String title){
    	getSupportActionBar().setTitle(title);
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.refresh, menu);
		this.mOptionsMenu = menu;
		if(mOpenRefresh && mFirst){
			setRefreshActionButtonState(true);
			mFirst = false;
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * Set the state of the actionbar progressBar
	 * @param refreshing
	 */
	public void setRefreshActionButtonState(final boolean refreshing) {
	    if (mOptionsMenu != null) {
	        final MenuItem refreshItem = mOptionsMenu
	            .findItem(R.id.action_refresh);
	        if (refreshItem != null) {
	            if (refreshing) {
	                refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
	            } else {
	                refreshItem.setActionView(null);
	            }
	        }
	    }
	}
	
	/**
	 * Set actionBar progress visibility
	 * @param visibility
	 */
	public void showProgress(final boolean visibility){
		if(mOptionsMenu != null){
			final MenuItem refreshItem = mOptionsMenu.findItem(R.id.action_refresh);
			if(refreshItem != null){
				if(visibility){
					refreshItem.setVisible(true);
					refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
				}else{
					refreshItem.setVisible(false);
				}
			}
			
		}
	}
	
}
