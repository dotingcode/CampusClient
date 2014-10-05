package com.campus.prime.ui.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.campus.prime.R;
import com.campus.prime.core.User;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.widget.PhotoFragment;

public class EditUserActivity extends BaseActivity
{
	public PhotoFragment photoFragment;
	
	@Override
	public  void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		getSupportFragmentManager().beginTransaction().replace(R.id.aue_fragment, new EditUserFragment()).commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
	}
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(photoFragment != null)
			photoFragment.onActivityResult(arg0,arg1, arg2);
	}
	
	
	
}
