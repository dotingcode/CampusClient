package com.campus.prime.ui.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Comment;
import com.campus.prime.core.service.CommentService;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.StringUtils;

public class CommentListViewAdapter extends SingleTypeAdapter<Comment>{
	
	private BitmapManager mBitmapManager;
	
	private static CommentService mService = new CommentService();
	
	public CommentListViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super(context,R.layout.comment_item);
		this.context = context;
		mBitmapManager = BitmapManager.getInstance(context);
	}

	@Override
	protected int[] getChildViewIds() {
		// TODO Auto-generated method stub
		return new int[]{R.id.ci_avatar,R.id.ci_username,R.id.ci_created,
				R.id.ci_content,R.id.ci_reply_icon,R.id.ci_delete_icon};
	}

	@Override
	protected void update(int position, final Comment item) {
		// TODO Auto-generated method stub
		mBitmapManager.avatarLoader(item.getUser().getAvatar(), imageView(0),0,0);
		imageView(0).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,UserActivity.class);
				intent.putExtra("userId", item.getUser().getId());
				context.startActivity(intent);
			}
		});
		setText(1,item.getUser().getNickName() + ":");
		setText(2,StringUtils.friendly_time(item.getCreated()));
		setText(3,formatContent(item));
		imageView(4).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MessageDetailActivity activity = (MessageDetailActivity)context;
				activity.setReply(item.getUser());
				if(!activity.isReplyViewVisibility())
					activity.showReplyView();
				
			}
		});
		imageView(5).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DeleteCommentTask task = new DeleteCommentTask();
				task.execute(item.getId());
			}
		});
		if(Auth.user.getId() == item.getUser().getId()){
			imageView(4).setVisibility(View.INVISIBLE);
			imageView(5).setVisibility(View.VISIBLE);
		}else{
			imageView(4).setVisibility(View.VISIBLE);
			imageView(5).setVisibility(View.INVISIBLE);
		}
	}
	
	
	
	
	
	public class DeleteCommentTask extends AsyncTask<Integer, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			return mService.deleteComment(params[0]);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result){
				Toast.makeText(context, "delete comment success", Toast.LENGTH_SHORT).show();
				((MessageDetailActivity)context).commentPageFragment.refresh();
				int count = Integer.parseInt(((MessageDetailActivity)context).commentCountView.getText().toString()) - 1;
				((MessageDetailActivity)context).commentCountView.setText(count + "");
			}
			else
				Toast.makeText(context, "delete comment failed", Toast.LENGTH_SHORT).show();
		}
	}
	
	private String formatContent(Comment item){
		if(item.getReply() == null)
			return item.getContent();
		Pattern pattern = Pattern.compile("»Ø¸´[\\s][[A-Z]{1}(\\w*|\\W*|[\\u4e00-\\u9fa5]*)*[¡£]{1}]+[\\s]:");
		Matcher matcher = pattern.matcher(item.getContent());
		String content = matcher.replaceFirst("");
		String formated = "»Ø¸´" + item.getReply().getNickName() + " : " + content;
		
		return formated;
	}
   	
   	
  
}
