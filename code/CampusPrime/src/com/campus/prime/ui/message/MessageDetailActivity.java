package com.campus.prime.ui.message;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Messenger;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Comment;
import com.campus.prime.core.Message;
import com.campus.prime.core.UserItem;
import com.campus.prime.core.service.CommentService;
import com.campus.prime.core.service.MessageService;
import com.campus.prime.core.utils.JsonUtil;
import com.campus.prime.push.client.BaiduPush;
import com.campus.prime.push.client.Notification;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.utils.StringUtils;

public class MessageDetailActivity extends BaseActivity{
	
	public Message message;
	private Comment mComment = new Comment();
	
	private View mBottomTabView;
	private View mReplyView;
	private EditText mCommentEditView;
	private TextView mPraiseCountView;
	public TextView commentCountView;
	private TextView mBtnPraiseView;
	
	public FinalBitmap mFinalBitmap;
	
	public CommentPageFragment commentPageFragment = new CommentPageFragment();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_detail);
		getSupportFragmentManager().beginTransaction().add(R.id.amd_container, commentPageFragment).commit();

		message = (Message)getIntent().getSerializableExtra("message");
		if(message != null){
			mComment.setMessage(message.getId());
			mComment.setUser(Auth.getUserItem());
		}
		mFinalBitmap = FinalBitmap.create(this);
		if(message.getMedia() == null)
			mFinalBitmap.configLoadingImage(R.drawable.media_loading);
		
		mBottomTabView	= findViewById(R.id.amd_bottom_bar);
		mReplyView = findViewById(R.id.amd_layout_reply);
		mCommentEditView = (EditText)findViewById(R.id.amd_edittext_reply);
		mPraiseCountView = (TextView)findViewById(R.id.amd_praise_count);
		commentCountView = (TextView)findViewById(R.id.amd_comment_count);
		mBtnPraiseView = (TextView)findViewById(R.id.amd_btn_praise_text);
		if(Auth.user != null){
			if(message.isPraiser(Auth.user.getId())){
				mBtnPraiseView.setText("已赞");
			}else{
				mBtnPraiseView.setTag("赞");
			}
		}else{
			mBtnPraiseView.setText("赞");
		}
		mPraiseCountView.setText(message.getPraiseCount() +"" );
		commentCountView.setText(message.getCommentCount() + "");
		
	}
	
	public void tabClicked(View view){
		switch(view.getId()){
		case R.id.amd_btn_praise:
			PraiseMessageTask task = new PraiseMessageTask();
			task.execute(message);
			break;
		case R.id.amd_btn_comment:
			showReplyView();
			mCommentEditView.requestFocus();
			break;
		default:
			break;
		}
	}
	
	public void sendClicked(View view){
		if(view.getId() == R.id.amd_btn_send){
			String strComment = mCommentEditView.getText().toString();
			if(StringUtils.isEmpty(strComment))
				Toast.makeText(this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
			else{
				mComment.setContent(strComment);
				PostCommentTask task = new PostCommentTask();
		   		task.execute();
				commentPageFragment.setRefreshState(true);
				
			}
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
			case android.R.id.home:
				this.finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	
	public class PostCommentTask extends AsyncTask<Void, Void, Comment>{
		@Override
		protected Comment doInBackground(Void... params) {
			// TODO Auto-generated method stub
			CommentService service = new CommentService();
			return service.createComment(mComment);
		}
		@Override
		protected void onPostExecute(Comment result) {
			// TODO Auto-generated method stub
			mCommentEditView.clearFocus();
			if(result != null){
				Toast.makeText(MessageDetailActivity.this,"发表评论成功",Toast.LENGTH_SHORT).show();
				mCommentEditView.setText("");
				commentPageFragment.refresh();
				int count = Integer.parseInt(commentCountView.getText().toString()) + 1;
				commentCountView.setText(count + "");
				collapseReplyView();
				// baidu push
				if(AppContext.isNotified){
					BaiduPushTask task = new BaiduPushTask();
					task.execute(2);
				}
				
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE) ;  
				imm.hideSoftInputFromWindow(mCommentEditView.getWindowToken(), 0) ;  
				
			}else{
				Toast.makeText(MessageDetailActivity.this, "发表评论失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	public class PraiseMessageTask extends AsyncTask<Message, Void, Message>{
		@Override
		protected Message doInBackground(Message... params) {
			// TODO Auto-generated method stub
			MessageService service = new MessageService();
			Message result = service.praiseMessage(message.getId());
			return result;
		}
		
		@Override
		protected void onPostExecute(Message result) {
			// TODO Auto-generated method stub
			if(result != null){
				//Toast.makeText(MessageDetailActivity.this, "", Toast.LENGTH_SHORT).show();
				message = result;
				if(message.isPraiser(Auth.user.getId()))
					mBtnPraiseView.setText("已赞");
				else
					mBtnPraiseView.setText("赞");
				mPraiseCountView.setText(message.getPraiseCount() + "");
				
				if(AppContext.isNotified){
					BaiduPushTask task = new BaiduPushTask();
					task.execute(1);
				}
			}
			else{
				//Toast.makeText(MessageDetailActivity.this, "failed", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	public class BaiduPushTask extends AsyncTask<Integer, Void, Void>{

		@Override
		protected Void doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			BaiduPush push = BaiduPush.getInstance();
			Notification notification = new Notification();
			log.i(message.toString());
			notification.setMessage(message);
			notification.setType(params[0]);
			notification.setFromUserId(Auth.user.getId());
			notification.setFromUserName(Auth.user.getNickName());
			notification.setFromUserAvatar(Auth.user.getAvatar());
			if(params[0] == 2)
				push.PushNotify("您的消息有新评论", message.getContent(),message.getUser().getPushId() );
			else if(params[0] == 1)
				push.PushNotify("您的消息有了新的赞", message.getContent(), message.getUser().getPushId());
			push.PushMessage(JsonUtil.toJson(notification), message.getUser().getPushId());
			return null;
		}
		
	}
	
	public void showReplyView(){
		if(mComment.getReply() != null){
			String label = "回复 " + mComment.getReply().getNickName() + " : ";
			mCommentEditView.setText(label);
		}
		
		TranslateAnimation animation1 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0,
				Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
		animation1.setDuration(300);
		TranslateAnimation animation2 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1,
				Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
		animation2.setDuration(300);
		mReplyView.setVisibility(View.VISIBLE);
		mReplyView.startAnimation(animation1);
		mBottomTabView.startAnimation(animation2);
		animation1.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mBottomTabView.setVisibility(View.INVISIBLE);
			}
		});
	}
	
	public void collapseReplyView(){
		TranslateAnimation animation1 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,1,
				Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
		animation1.setDuration(300);
		TranslateAnimation animation2 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,-1,Animation.RELATIVE_TO_SELF,0,
				Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
		animation2.setDuration(300);
		mBottomTabView.setVisibility(View.VISIBLE);
		mReplyView.startAnimation(animation1);
		mBottomTabView.startAnimation(animation2);
		animation1.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mReplyView.setVisibility(View.INVISIBLE);
			}
		});
	}
	
	public boolean isReplyViewVisibility(){
		if(mReplyView.getVisibility() == View.VISIBLE)
			return true;
		else
			return false;
	}
	
	public void setReply(UserItem user){
		mComment.setReply(user);
	}
	
}
