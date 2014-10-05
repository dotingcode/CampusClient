
package com.campus.prime.ui.home;

import static com.campus.prime.constant.AppConstant.SETTING_INFOS;
import static com.campus.prime.constant.AppConstant.TOKEN;
import static com.campus.prime.constant.AppConstant.USERNAME;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.campus.prime.R;
import com.campus.prime.core.Token;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.app.SchoolCalendarActivity;
import com.campus.prime.utils.IntentUtil;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends BaseActivity {
	
	/**
	 * UserService
	 */
	private UserService service;
	
	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		service = new UserService();
		mEmailView = (EditText) findViewById(R.id.email);
		mEmailView.setText(mEmail);
		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});
		

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
		
		findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				IntentUtil.startActivity(LoginActivity.this, RegisterActivity.class,	null);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		showProgress(false);	
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_register:
			IntentUtil.startActivity(LoginActivity.this, RegisterActivity.class,	null);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError("请输入密码");
			focusView = mPasswordView;
			cancel = true;
		} else if(checkPassword(mPasswordView.getText().toString()) == false)
		{
			mPasswordView.setError("密码中不能包含特殊字符");
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError("密码长度太短");
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError("请输入邮箱");
			focusView = mEmailView;
			cancel = true;
		} else if (!checkEmail(mEmail)) {
			mEmailView.setError("邮箱格式不正确");
			focusView = mEmailView;
			cancel = true;
		}
		
		
		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}
	
	
	private boolean checkEmail(String email){
		String strPattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(email);
		return m.find();
	}


	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Token> {
		@Override
		protected Token doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			Token token = service.login(mEmail, mPassword);

			// TODO: register the new account here.
			return token;
		}

		@Override
		protected void onPostExecute(final Token token) {
			mAuthTask = null;
			showProgress(false);

			if (token != null) {
				SharedPreferences infos = getSharedPreferences(SETTING_INFOS, 0);
				infos.edit().putString(TOKEN,token.getToken()).putString(USERNAME,mEmail).commit();
				IntentUtil.startActivity(LoginActivity.this, HomeActivity.class, null);
				LoginActivity.this.finish();
			} else {
				mPasswordView
						.setError("密码不正确");
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
	/**
	 * Check password is contained particular character
	 * @param password
	 * @return
	 */
	public boolean checkPassword( String password)
	{
		for(int i=0; i<password.length();i++ )
		{
			if((Character.isLetter(password.charAt(i)) == false)&&(Character.isDigit(password.charAt(i)) == false))
			{
				return false;
			}
		}
		return true;
	}
	@Override
	public void onBackPressed() {
        
		this.finish();
    }

}
