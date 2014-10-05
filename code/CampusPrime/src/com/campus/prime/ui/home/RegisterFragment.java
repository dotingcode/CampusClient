package com.campus.prime.ui.home;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.Token;
import com.campus.prime.core.User;
import com.campus.prime.core.client.RequestException;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.circle.AllGroupFragment;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;
import com.sina.weibo.sdk.constant.Constants.Msg;

import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterFragment extends Fragment{
	static CommonLog log = LogFactory.createLog();
	private UserRegisterTask mAuthTask = null;
	private RegisterActivity mActivity;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.register, null);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		setHasOptionsMenu(true);
		final ActionBar actionBar = ((RegisterActivity)getActivity()).getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		mActivity = (RegisterActivity)getActivity();
		// Set up the login form.
		mEmailView = (EditText) view.findViewById(R.id.ar_email);
		mEmailView.setText(mEmail);

		mPasswordView = (EditText)view.findViewById(R.id.ar_password);
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
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_next:
			attemptLogin();
			return true;
		case android.R.id.home:
			((RegisterActivity)getActivity()).finish();
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
		mEmail = mEmailView.getText().toString().trim();
		mPassword = mPasswordView.getText().toString().trim();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError("«Î ‰»Î√‹¬Î");
			focusView = mPasswordView;
			cancel = true;
		}else if(this.checkPassword(mPasswordView.getText().toString()) == false) {
			mPasswordView.setError("√‹¬Î÷–≤ªƒ‹∞¸∫¨Ãÿ ‚◊÷∑˚");
			focusView = mPasswordView;
			cancel = true;
		}
		else if (mPassword.length() < 4) {
			mPasswordView.setError("√‹¬ÎÃ´∂Ã¡À£¨«◊");
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError("«Î ‰»Î” œ‰");
			focusView = mEmailView;
			cancel = true;
		} else if (!checkEmail(mEmail)) {
			mEmailView.setError("” œ‰∏Ò Ω≤ª’˝»∑");
			focusView = mEmailView;
			cancel = true;
		}
		
		// Check for a valid username
		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mAuthTask = new UserRegisterTask();
			((RegisterActivity)getActivity()).showProgress(true);
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
	public class UserRegisterTask extends AsyncTask<Void, Void, User> {
		
		RegisterActivity activity = (RegisterActivity)getActivity();
		@Override
		protected User doInBackground(Void... params) {
			User user = null;
			try {
				user = mActivity.mService.register(mEmail, mEmail, mPassword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(e instanceof IOException){
					if(((IOException) e).getMessage().equals("Unknown error occured 400")){
						User u = new User();
						u.setAvatar("400");
						return u;
					}
				}
			} 
			return user;
		}

		@Override
		protected void onPostExecute(final User user) {
			mAuthTask = null;
			if (user != null) {
				if(user.getAvatar() != "400"){
					activity.mRegister = user;
					Auth.user = user;
					log.i(user.toString());
					UserLoginTask task = new UserLoginTask();
					task.execute();
					//Toast.makeText(RegisterFragment.this.getActivity(), "logining", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(RegisterFragment.this.getActivity(), "ƒ˙ ‰»Îµƒ” œ‰“—±ª◊¢≤·", Toast.LENGTH_SHORT).show();
					activity.showProgress(false);
				}
			} else {
				Toast.makeText(RegisterFragment.this.getActivity(),"◊¢≤· ß∞‹",Toast.LENGTH_SHORT).show();
				activity.showProgress(false);
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
		}
	}
	
	
	public class UserLoginTask extends AsyncTask<Void, Void, Token>{
		
		RegisterActivity activity = (RegisterActivity)getActivity();
		@Override
		protected Token doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Token token = mActivity.mService.login(mEmail, mPassword);

			return token;
		}
		
		@Override
		protected void onPostExecute(Token token) {
			// TODO Auto-generated method stub
			mAuthTask = null;
			activity.showProgress(false);
			if (token != null) {
				SharedPreferences infos = activity.getSharedPreferences(AppConstant.SETTING_INFOS, 0);
				infos.edit().putString(AppConstant.TOKEN,token.getToken()).putString(AppConstant.USERNAME,mEmail).commit();
				Auth.token = token.getToken();
				Auth.username = mEmail;
				//AllGroupFragment fragment = new AllGroupFragment();
				RegisterBasicFragment fragment = new RegisterBasicFragment();
				activity.mFragmentManager.beginTransaction()
							.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out)
							.replace(R.id.container,fragment)
							.commit();
			} else {
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}
		
		@Override
		protected void onCancelled() {
			mAuthTask = null;
			activity.showProgress(false);
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
	
}
