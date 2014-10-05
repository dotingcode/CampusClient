package com.campus.prime.ui.home;

import java.util.ArrayList;
import java.util.List;

import Database.DAOHelper;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.db.UserDB;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.DrawerActivity;
import com.campus.prime.ui.user.EditUserActivity;
import com.campus.prime.ui.view.ItemDialog;
import com.campus.prime.utils.IntentUtil;

public class SettingActivity extends DrawerActivity
{
	
	private SettingFragment mFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		mFragment = new SettingFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.content_frame, mFragment).commit();
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
	}
	
	

}
