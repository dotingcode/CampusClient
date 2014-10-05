package com.campus.prime.ui;

import java.util.HashMap;
import java.util.Map;

import net.tsz.afinal.FinalBitmap;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.cardsui.CardAdapter;
import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Message;
import com.campus.prime.core.service.MessageService;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.group.GroupActivity;
import com.campus.prime.ui.home.HomeActivity;
import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.StringUtils;

public class MessageCardAdapter extends CardAdapter<Message>{
	
	private static BitmapManager mBitmapManager;
	private Context context;
	
	private FinalBitmap mFinalBitmap;
	
	private static MessageService service = new MessageService();
	
	public MessageCardAdapter(Context context){
		super(context);
		mBitmapManager = BitmapManager.getInstance(context);
		this.context = context;
		mFinalBitmap = FinalBitmap.create(context);
		mFinalBitmap.configLoadingImage(R.drawable.media_loading);
	}
	
	
	@Override
	public View onViewCreated(int index, View recycled, final Message item) {
		// TODO Auto-generated method stub
		onProcessUsername(recycled, item);
		TextView createdView = (TextView)recycled.findViewById(R.id.mi_created);
		createdView.setText(StringUtils.friendly_time(item.getCreated()));
		TextView contentView = (TextView)recycled.findViewById(R.id.mi_content1);
		contentView.setText(item.getContent());
		
		final TextView praiseNumView = (TextView)recycled.findViewById(R.id.mi_praise_num);
		praiseNumView.setText(item.getPraiseCount() + "");
		/**
		recycled.findViewById(R.id.mi_praise_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(item.isPraiser(Auth.user.getId())){
					Toast.makeText(context,"depraise click",Toast.LENGTH_SHORT).show();
					item.setPraiseCount(item.getPraiseCount() - 1);
					praiseNumView.setText(item.getPraiseCount() + "");
				}else{
					Toast.makeText(context,"praise click",Toast.LENGTH_SHORT).show();
					item.setPraiseCount(item.getPraiseCount() + 1);
					praiseNumView.setText(item.getPraiseCount() + "");
				}
				PraiseMessageTask task = new PraiseMessageTask();
				task.execute(item);
			}
		});
		**/
		
		TextView commentNumView = (TextView)recycled.findViewById(R.id.mi_comment_num);
		commentNumView.setText(item.getCommentCount() + "");
		/**
		recycled.findViewById(R.id.mi_comment_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context,"comment click",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context,MessageDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("message",item);
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		**/
		onProcessAvatar(recycled, item);
		onProcessMedia(recycled,item);
		
		return super.onViewCreated(index, recycled, item);
	}
	
	
	public class PraiseMessageTask extends AsyncTask<Message, Void, Message>{
		Message item;
		@Override
		protected Message doInBackground(Message... params) {
			// TODO Auto-generated method stub
			item = params[0];
			Message result = service.praiseMessage(item.getId());
			return result;
		}
		
		@Override
		protected void onPostExecute(Message result) {
			// TODO Auto-generated method stub
			if(result != null){
				Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
				 item.setPraisers(result.getPraisers());
				 
			}else
				Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void onProcessMedia(View view,Message item){
		ImageView imageView = (ImageView)view.findViewById(R.id.mi_media);
		//Bitmap bitmap = ((BitmapDrawable)context.getApplicationContext().getResources().getDrawable(R.drawable.test)).getBitmap();
		//Bitmap b = ThumbnailUtils.extractThumbnail(bitmap, 700, 380);
		//mBitmapManager.mediaLoader(item.getMedia(), imageView, 0, 0);
		//ImageDownloader imageDownloader = new ImageDownloader(context);
		//imageDownloader.download(item.getMedia(), imageView);
		//imageView.setImageBitmap(b);
		/**
		String media = item.getMedia();
		if(item.getMedia() == null || StringUtils.isEmpty(item.getMedia())){
			imageView.setVisibility(View.VISIBLE);
		}else{
			imageView.setVisibility(View.VISIBLE);
			String url = BCSUtils.generateUrl("/media/" + item.getMedia());
			mFinalBitmap.display(imageView, url);
		}
		**/
		if(item.getMedia() == null || StringUtils.isEmpty(item.getMedia())){
			imageView.setVisibility(View.GONE);
		}else{
			imageView.setVisibility(View.VISIBLE);
			String url = BCSUtils.generateUrl("/media/" + item.getMedia());
			mFinalBitmap.display(imageView, url);
		}
	}
	
	private void onProcessUsername(View view,Message item) {
		// TODO Auto-generated method stub
		TextView usernameView = (TextView)view.findViewById(R.id.mi_username);
		if(!item.isPublic())
			usernameView.setText(item.getUser().getNickName());
		else
			usernameView.setText(item.getGroup().getName());
	}
	
	private void onProcessAvatar(View view,final Message item){
		ImageView avatarView = (ImageView)view.findViewById(R.id.mi_avatar);
		avatarView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Auth.isAuthed()){
					Map<String,Integer> param = new HashMap<String, Integer>();
					param.put("userId", item.getUser().getId());
					if(item.getGroup() != null)
						IntentUtil.startActivity((Activity)context, GroupActivity.class, "groupItem",item.getGroup());
					else
						IntentUtil.startActivity((Activity)context, UserActivity.class,"userId",item.getUser().getId());
				}else{
					Toast.makeText(context, "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();
				}
			}
		});
		if(item.getGroup() != null)
			mBitmapManager.avatarLoader(item.getGroup().getAvatar(), avatarView, 0, 0);
		else
			mBitmapManager.avatarLoader(item.getUser().getAvatar(), avatarView, 0, 0);

	}
}

