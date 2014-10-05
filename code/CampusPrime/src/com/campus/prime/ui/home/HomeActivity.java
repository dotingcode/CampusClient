package com.campus.prime.ui.home;

import static com.campus.prime.constant.AppConstant.SETTING_INFOS;
import static com.campus.prime.constant.AppConstant.TOKEN;
import static com.campus.prime.constant.AppConstant.USERNAME;
import Database.DAOHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.User;
import com.campus.prime.core.service.UserService;
import com.campus.prime.db.MessageDB;
import com.campus.prime.db.NotificationDB;
import com.campus.prime.db.UserDB;
import com.campus.prime.ui.AsyncLoader;
import com.campus.prime.ui.DrawerActivity;
import com.campus.prime.utils.SharedPreferenceUtil;


/**
 * HomeActivity
 * @author absurd
 *
 */

public class HomeActivity extends DrawerActivity 
		implements LoaderCallbacks<User>{
	
	protected HomeFragment mFragment;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		infos = getSharedPreferences(SETTING_INFOS, 0);
		initDB();
		PushManager.startWork(this, PushConstants.LOGIN_TYPE_API_KEY, "X5QlEt746l0sXLmuTqKo5a7u");
		
		mFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
		
		getInfos();
		if(Auth.isAuthed()){
			User read =  User.readFromDB();
			if(read != null){
				if(Auth.user != null)
					bindDrawerData(Auth.user);
			}
		}
		getSupportLoaderManager().initLoader(0, null, this);

	}
	
	/**
	 * Get infos from sharedPreference
	 */
	private void getInfos(){
		if(!Auth.isAuthed()){
			if(infos == null)
				return;
			Auth.token = infos.getString(TOKEN,null);
			Auth.username = infos.getString(USERNAME,null);
			mLog.i("on start app get user infos " + Auth.token);
			mLog.i("on start app get user infos " + Auth.username);
		}
	}
	
	private void initDB(){
		DAOHelper.createInstance(AppContext.getContext(),AppConstant.DB_NAME,AppConstant.DB_VERSION);
		DAOHelper dbHelper = DAOHelper.getInstance();
		if(dbHelper != null){
			dbHelper.addDBTable(MessageDB.TABLE, new MessageDB());
			dbHelper.addDBTable(UserDB.TABLE,new UserDB());
			dbHelper.addDBTable(NotificationDB.TABLE,new NotificationDB());
		}
	}
	
	@Override
	public Loader<User> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new AsyncLoader<User>(HomeActivity.this) {

			@Override
			protected User loadData() {
				// TODO Auto-generated method stub
				UserService service = new UserService();
				User result = service.getProfile(Auth.username);
				return result;
			}
			
		};
	}

	
	@Override
	public void onLoadFinished(Loader<User> arg0, User result) {
		// TODO Auto-generated method stub
		if(result != null){
			UserDB db = (UserDB)DAOHelper.getInstance().getTable(UserDB.TABLE);
			db.clearAll();
			result.saveToDB();
			Auth.user = result;
			bindDrawerData(result);
			// check if pushId same
			SharedPreferenceUtil util = SharedPreferenceUtil.getInstance();
			if(!util.getUserId().equals(Auth.user.getPushId())){
				// update user profile
				Auth.user.setPushId(util.getUserId());
				UpdatePushIdTask task = new UpdatePushIdTask();
				task.execute();
			}
		}
		mIsNotifyClickable = true;
	}

	@Override
	public void onLoaderReset(Loader<User> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mFragment.refresh();
	}
	
	/**
	 * Bind drawer's user info data
	 * @param user
	 */
	private void bindDrawerData(User user){
		mBitmapManager.avatarLoader(user.getAvatar(), mAvatarView, 0, 0);
		mUsernameView.setText(user.getNickName());
		mMyGroupCountView.setText(user.getCreatedGroupCount() + "");
		mMyTweetCountView.setText(user.getMessageCount() + "");
		mMyJoinedCountView.setText(user.getJoinedGroupCount() + "");
	}
	
	public class UpdatePushIdTask extends AsyncTask<Void, Void, User>{

		@Override
		protected User doInBackground(Void... params) {
			// TODO Auto-generated method stub
			UserService service = new UserService();
			User result = service.update(Auth.user);
			return result;
		}
		
		@Override
		protected void onPostExecute(User result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result != null){
				//Toast.makeText(HomeActivity.this,"update push id success",Toast.LENGTH_SHORT).show();
			}else{
				//Toast.makeText(HomeActivity.this, "update push id fail", Toast.LENGTH_SHORT).show();
			}
		}
		
		
	}
	
}
