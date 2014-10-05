package com.campus.prime.ui.home;

import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.db.UserDB;
import com.campus.prime.utils.IntentUtil;

import Database.DAOHelper;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class SettingFragment extends Fragment implements OnClickListener{
	
	
	TextView logOut;
	TextView clean;
	CheckBox notify;
	TextView update;
	TextView about;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.setting, null);
		return view;
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		logOut = (TextView) view.findViewById(R.id.as_log_out);
		clean = (TextView ) view.findViewById(R.id.as_clean);
		update = (TextView) view.findViewById(R.id.as_update);
		about = (TextView) view.findViewById(R.id.as_about);
		notify = (CheckBox) view.findViewById(R.id.as_is_notify);
		logOut.setOnClickListener(this);
		clean.setOnClickListener(this);		
		update.setOnClickListener(this);
		about.setOnClickListener(this);
		notify.setChecked(true);
		notify.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(notify.isChecked()== true)
				{
					AppContext.isNotified = true;
				}
				else
				{
					AppContext.isNotified = false;
				}
			}});
	}
	
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
			case R.id.as_update: Toast.makeText(getActivity(), "已经是最新版本，无需更新!", Toast.LENGTH_SHORT).show();break;
			case R.id.as_log_out:{
				if(!Auth.isAuthed())
				{
					Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_LONG).show();
				}
				else
				{	
					SharedPreferences infos = getActivity().getSharedPreferences(AppConstant.SETTING_INFOS, 0);
					infos.edit().remove("token").remove("username").remove("isSchoolAuth").commit();
					UserDB db = (UserDB)DAOHelper.getInstance().getTable(UserDB.TABLE);
					db.clearAll();
					Auth.user = null;
					Auth.username = null;
					Auth.token = null;
					IntentUtil.startActivity(getActivity(), LoginActivity.class, null);
					getActivity().finish();
				}
				break;
			}
			case R.id.as_clean:
				Toast.makeText(getActivity(), "缓存已清除", Toast.LENGTH_SHORT).show();
				break;
			case R.id.as_about:
			{
				AlertDialog mDialog = new AlertDialog.Builder(getActivity())
													.setMessage("版本号0.1.3 beta\n\n")
													.setTitle("关于")
													.create();
				mDialog.show();
				break;
			}
		}
	}


}
