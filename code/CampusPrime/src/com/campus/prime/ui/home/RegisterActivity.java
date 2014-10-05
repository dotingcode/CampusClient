package com.campus.prime.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.campus.prime.R;
import com.campus.prime.core.User;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.widget.PhotoFragment;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class RegisterActivity extends BaseActivity {
	
	private RegisterFragment mRegisterFragment = new RegisterFragment();
	public FragmentManager mFragmentManager;
	public PhotoFragment mPhotoFragment;
	public UserService mService = new UserService();
	public User mRegister = new User();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container);
		mOpenRefresh = false;
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mFragmentManager = getSupportFragmentManager();
		mFragmentManager.beginTransaction()
				.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out)
				.add(R.id.container, mRegisterFragment)
				.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.next, menu);
		showProgress(false);
		return true;
	}
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(mPhotoFragment != null){
			mPhotoFragment.onActivityResult(arg0, arg1, arg2);
		}
	}
	
}