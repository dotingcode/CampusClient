package com.campus.prime.ui.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.app.Auth;

public class EditNickNameFragment extends Fragment
{
	private FragmentManager fm;
	EditText mNickName;
	TextView mNickNameNum;
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		View parent = inflater.inflate(R.layout.edit_nick_name, null);
		EditUserActivity mActivity = (EditUserActivity) this.getActivity();
		mNickName= (EditText) parent.findViewById(R.id.fnne_nick_name);
		mNickNameNum = (TextView) parent.findViewById(R.id.fnne_nick_name_num);
		if(Auth.user.getNickName() != null)
		{
			mNickName.setText(Auth.user.getNickName());		
			mNickNameNum.setText((15-Auth.user.getNickName().toString().length())+"");
		}
		this.disposeNumberOfNickName();
		return parent;
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		fm = ((EditUserActivity)getActivity()).getSupportFragmentManager();
		
		setHasOptionsMenu(true);
		final ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
                    ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME|
                    ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		View actionBarButtons = getActivity().getLayoutInflater().inflate(R.layout.actionbar_custom,
                    new LinearLayout(getActivity()), false);
		View cancelActionView = actionBarButtons.findViewById(R.id.action_cancel);
		cancelActionView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fm.popBackStack();
			}
		});
		View doneActionView = actionBarButtons.findViewById(R.id.action_done);
		doneActionView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Auth.user.setNickName(mNickName.getText().toString());
				fm.popBackStack();
			}
		});
		actionBar.setCustomView(actionBarButtons);
		
	}
	
	/**
	 * Dispose numbers of nick name
	 */
	public void disposeNumberOfNickName()
	{
		mNickName.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				mNickNameNum.setText((15-s.length())+"");
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}});
	}
	
	
	
	
	
	
}
