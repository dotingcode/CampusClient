package com.campus.prime.ui.group;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupItem;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.core.utils.JsonUtil;
import com.campus.prime.push.client.BaiduPush;
import com.campus.prime.push.client.Notification;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.group.GroupFragment.UpdateProfileTask;
import com.campus.prime.ui.widget.PhotoFragment;

public class GroupActivity extends BaseActivity{
	
	public boolean isMember;
	public GroupItem groupItem;
	public Group group;
	public GroupService service = new GroupService();
	public FragmentManager fm;
	public PhotoFragment photoFragment1 = new PhotoFragment();
	public PhotoFragment photoFragment2 = new PhotoFragment();
	public int tag;
	
	
	
	public GroupFragment groupFragment = new GroupFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container);
		
		fm =getSupportFragmentManager();
		fm.beginTransaction().add(R.id.container, groupFragment).commit();
		Intent intent = getIntent();
		groupItem = (GroupItem)intent.getSerializableExtra("groupItem");
		group = (Group)intent.getSerializableExtra("group");
		if(group == null){
			group = new Group();
			group.setId(groupItem.getId());
			group.setName(groupItem.getName());
			group.setAvatar(groupItem.getAvatar());
			group.setDescription(groupItem.getDescription());
			group.setTotal(groupItem.getTotal());
			group.setCover(null);
		}
		
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(tag == 1)
			photoFragment1.onActivityResult(arg0,arg1,arg2);
		else if(tag == 2)
			photoFragment2.onActivityResult(arg0, arg1, arg2);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		groupFragment.onPrepareOptionsMenu(menu);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_add_group:
			AddGroupTask task = new AddGroupTask();
			task.execute();
			return true;
		case R.id.action_save:
			UpdateProfileTask updateTask = new UpdateProfileTask();
			updateTask.execute();
			return true;
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	public class AddGroupTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			boolean result;
			int groupId;
			if(groupItem == null)
				groupId = group.getId();
			else
				groupId = groupItem.getId();
			if(groupFragment.isMember)
				result = service.exitGroup(groupId);
			else
				result = service.addGroup(groupId);
			return result;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(groupFragment.isMember){
				if(result){
					Toast.makeText(GroupActivity.this,"退出社团", Toast.LENGTH_SHORT).show();
					groupFragment.isMember = false;
				}else
					Toast.makeText(GroupActivity.this, "退出社团失败", Toast.LENGTH_SHORT).show();
			}else{
				if(result){
					Toast.makeText(GroupActivity.this,"加入社团", Toast.LENGTH_SHORT).show();
					groupFragment.isMember = true;
					if(AppContext.isNotified){
						BaiduPushTask task = new BaiduPushTask();
						task.execute();
					}
				}else
					Toast.makeText(GroupActivity.this,"加入社团失败", Toast.LENGTH_SHORT).show();
			}
			GroupActivity.this.getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
		}
	}
	
	
	public class UpdateProfileTask extends AsyncTask<Void,Void,Group>{
		@Override
		protected Group doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Group updated = service.update(group);
			return updated;
		}
		
		@Override
		protected void onPostExecute(Group result) {
			// TODO Auto-generated method stub
			if(result != null){
				Toast.makeText(GroupActivity.this, "资料更新成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(GroupActivity.this, "资料更新失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	
	public class BaiduPushTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			BaiduPush push = BaiduPush.getInstance();
			Notification notification = new Notification();
			notification.setType(3);
			notification.setFromUserId(Auth.user.getId());
			notification.setFromUserName(Auth.user.getNickName());
			notification.setFromUserAvatar(Auth.user.getAvatar());
			push.PushNotify("您的社团群组有个新的成员加入", " ",group.getFounder().getPushId());
			push.PushMessage(JsonUtil.toJson(notification),group.getFounder().getPushId());
			return null;
		}
		
	}
	
	
}
