package com.campus.prime.ui.user;

import com.campus.prime.R;
import com.campus.prime.app.Auth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditDescriptionFragment extends Fragment
{
	EditText mDescriptionView;
	TextView mDescriptionNum;
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		View parent = inflater.inflate(R.layout.edit_description, null);		
		mDescriptionView = (EditText) parent.findViewById(R.id.fde_description);
		mDescriptionNum = (TextView) parent.findViewById(R.id.fde_description_num);
		mDescriptionView.setText(Auth.user.getDescription());
		String description = Auth.user.getDescription();
		if(description != null)
			mDescriptionNum.setText((30-description.toString().length())+"");
		else
			mDescriptionNum.setText(30 + "");
		this.disposeNumbersOfDescription();
		return parent;		
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final FragmentManager fm = ((EditUserActivity)getActivity()).getSupportFragmentManager();
		
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
				Auth.user.setDescription(mDescriptionView.getText().toString());
				fm.popBackStack();
			}
		});
		actionBar.setCustomView(actionBarButtons);
	}
	
	
	/**
	 * Dispose numbers of description
	 */
	public void disposeNumbersOfDescription()
	{
		mDescriptionView.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				mDescriptionNum.setText((30-arg0.length())+"");
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
			}});
	}
}
