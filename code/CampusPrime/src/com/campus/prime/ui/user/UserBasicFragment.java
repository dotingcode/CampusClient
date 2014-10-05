package com.campus.prime.ui.user;

import Database.DAOHelper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.User;
import com.campus.prime.db.UserDB;
import com.campus.prime.ui.AsyncLoader;
import com.campus.prime.ui.home.LoginActivity;
import com.campus.prime.ui.view.LabelTextView;
import com.campus.prime.ui.view.ThemeImageView;
import com.campus.prime.ui.view.ThemeTextView;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.SharedPreferenceUtil;

public class UserBasicFragment extends Fragment implements LoaderCallbacks<User>{
	
	ThemeImageView mAvatarView;
	ThemeTextView mNickNameView;
	LabelTextView mRealNameView;
	LabelTextView mLoveStateView;
	TextView mDescriptionView;
	LabelTextView mSchoolView;
	LabelTextView mAcademyView;
	LabelTextView mGradeView;
	LabelTextView mGender;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.user_basic, container,false);
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		setHasOptionsMenu(true);
		getView(view);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		getLoaderManager().initLoader(0, null, this);
	}
	
	/**
	 * get Views and set textViews' label
	 * @param view
	 */
	public void getView(View view){
		mRealNameView = (LabelTextView) view.findViewById(R.id.ub_real_name);
		mLoveStateView = (LabelTextView) view.findViewById(R.id.ub_love_state);
		mDescriptionView = (TextView) view.findViewById(R.id.ub_description);
		mSchoolView = (LabelTextView) view.findViewById(R.id.ub_school);
		mAcademyView = (LabelTextView) view.findViewById(R.id.ub_academy);
		mGradeView = (LabelTextView) view.findViewById(R.id.ub_grade);
		mGender = (LabelTextView) view.findViewById(R.id.ub_sex);
	}
	
	
	/**
	 * Bind data with textView
	 */
	private void bindData(){
		setText(mRealNameView,((UserActivity)getActivity()).user.getRealName());
		setText(mLoveStateView,((UserActivity)getActivity()).user.getLoveState());
		setText(mSchoolView,((UserActivity)getActivity()).user.getSchool());
		setText(mAcademyView,((UserActivity)getActivity()).user.getAcademy());
		setText(mGradeView,((UserActivity)getActivity()).user.getGrade());
		setText(mDescriptionView,((UserActivity)getActivity()).user.getDescription());
		if(((UserActivity)getActivity()).user.getSex() == 'M')
		{
			setText(mGender,"ÄÐ");
		}
		else if(((UserActivity)getActivity()).user.getSex() == 'F')
		{
			setText(mGender,"Å®");
		}
		
		
	}
	
	/**
	 * TextView set text
	 * @param view
	 * @param text
	 */
	private void setText(TextView view,String text){
		if(text != null)
			view.setText(text);
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		if(((UserActivity)getActivity()).mUserId == -1)
			inflater.inflate(R.menu.auth_user_basic, menu);
		else
			inflater.inflate(R.menu.user_basic, menu);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.user_edit:
			if(((UserActivity)getActivity()).user != null)
				IntentUtil.startActivity(this.getActivity(), EditUserActivity.class, "user", ((UserActivity)getActivity()).user);
			return true;
		case android.R.id.home:
			((UserActivity)getActivity()).finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void cancellation(){
		SharedPreferences infos = ((UserActivity)getActivity()).getSharedPreferences(AppConstant.SETTING_INFOS, 0);
		infos.edit().remove("token").remove("username").commit();
		UserDB db = (UserDB)DAOHelper.getInstance().getTable(UserDB.TABLE);
		db.clearAll();
		((UserActivity)getActivity()).finish();
		IntentUtil.startActivity(((UserActivity)getActivity()), LoginActivity.class, null);
		
	}

	@Override
	public AsyncLoader<User> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new AsyncLoader<User>(((UserActivity)getActivity())) {

			@Override
			protected User loadData() {
				// TODO Auto-generated method stub
				User result = null;
				int userId = ((UserActivity)getActivity()).getUserId();
				Log.i("tag",userId + " ");
				if(userId != -1)
					result = ((UserActivity)getActivity()).service.getProfile(userId);
				else{
					if(Auth.user == null)
						result = ((UserActivity)getActivity()).service.getProfile();
					else
						result = Auth.user;
				}
				return result;
			}
		};
	}
	
	@Override
	public void onLoadFinished(Loader<User> arg0, User arg1) {
		// TODO Auto-generated method stub
		((UserActivity)getActivity()).user = arg1;
		if(((UserActivity)getActivity()).user == null)
			Log.i("GetUser","NUll");
		else{
			// Bind data with textView
			bindData();
			((UserActivity)getActivity()).bindData();
		}
		
	}

	@Override
	public void onLoaderReset(Loader<User> arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
