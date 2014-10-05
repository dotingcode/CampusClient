package com.campus.prime.ui.group;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.core.service.GroupService;

public class EditDescriptionFragment extends Fragment
{
	private GroupActivity mActivity;
	EditText mDesCriptionView;
	TextView mNumView;
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		
		View parent = inflater.inflate(R.layout.edit_group_description, null);
		mDesCriptionView= (EditText) parent.findViewById(R.id.egd_description);
		mNumView = (TextView) parent.findViewById(R.id.egd_num);
		mDesCriptionView.setText(mActivity.group.getDescription());
		String description = mActivity.group.getDescription();
		if(description != null)
			mNumView.setText((30-description.toString().length())+"");
		else
			mNumView.setText(30 + "");
		this.disposeNumberOfNickName();
		return parent;
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mActivity = (GroupActivity)getActivity();
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
				mActivity.fm.popBackStack();
			}
		});
		View doneActionView = actionBarButtons.findViewById(R.id.action_done);
		doneActionView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActivity.group.setDescription(mDesCriptionView.getText().toString());
				UpdateProfileTask task = new UpdateProfileTask();
				task.execute();
				mActivity.fm.popBackStack();
			}
		});
		actionBar.setCustomView(actionBarButtons);
		
	}
	
	
	
	/**
	 * Dispose numbers of nick name
	 */
	public void disposeNumberOfNickName()
	{
		mDesCriptionView.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				mNumView.setText((30-s.length())+"");
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
	
	public class UpdateProfileTask extends AsyncTask<Void,Void,Group>{
		private GroupService service = new GroupService();
		@Override
		protected Group doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Group updated = service.update(mActivity.group);
			return updated;
		}
		
		@Override
		protected void onPostExecute(Group result) {
			// TODO Auto-generated method stub
			if(result != null){
				Toast.makeText(mActivity, "���ϸ��³ɹ�", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(mActivity, "���ϸ���ʧ��", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
}
