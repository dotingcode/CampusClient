package com.campus.prime.ui;

import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.constant.AppConstant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ImportSchoolInfoActivity extends BaseActivity{
	
	private EditText mSchoolNumView;
	private EditText mPasswordView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import_school_info);
		mOpenRefresh = false;
		mSchoolNumView = (EditText)findViewById(R.id.school_num);
		mPasswordView = (EditText)findViewById(R.id.school_password);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.confirm, menu);
		showProgress(false);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_confirm:
			ImportInfoTask task = new ImportInfoTask();
			task.execute();
			showProgress(true);
			setRefreshActionButtonState(true);
			return true;
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
				
		}

	}
	
	
	
	
	public class ImportInfoTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			showProgress(false);
			setRefreshActionButtonState(false);
			String num = mSchoolNumView.getText().toString();
			String pass = mPasswordView.getText().toString();
			if(num.equals("201107111059") && pass.equals("223344")){
				SharedPreferences sp = AppContext.getContext().getSharedPreferences(AppConstant.SETTING_INFOS,Context.MODE_PRIVATE);
				sp.edit().putBoolean("isSchoolAuth", true).commit();
				Toast.makeText(ImportSchoolInfoActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
				DrawerActivity.isAppMenuOpen = true;
				finish();
			}else{
				Toast.makeText(ImportSchoolInfoActivity.this, "密码有误", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
}
