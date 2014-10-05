package com.campus.prime.ui.message;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.core.Comment;
import com.campus.prime.core.service.CommentService;
import com.campus.prime.core.utils.BCSUtils;
import com.campus.prime.ui.PagedItemFragment;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.group.GroupActivity;
import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.utils.BitmapManager;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.StringUtils;

public class CommentPageFragment extends PagedItemFragment<Comment>{
		/**
	 * Service for loding data
	 */
	private static CommentService service = new CommentService();
	
	private BitmapManager mBitmapManager;
	private ImageView mAvatarView;
	private TextView mNicknameView;
	private TextView mCreatedView;
	private TextView mContentView;
	private ImageView mMediaView;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mBitmapManager = BitmapManager.getInstance(this.getActivity());
		View headerView = getLayoutInflater(null).inflate(R.layout.message_detail_header, null);
		mAvatarView = (ImageView)headerView.findViewById(R.id.amd_avatar);
		mAvatarView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(((MessageDetailActivity)getActivity()).message.getGroup() == null)
					IntentUtil.startActivity(((MessageDetailActivity)getActivity()), UserActivity.class, "userId",((MessageDetailActivity)getActivity()).message.getUser().getId());
				else
					IntentUtil.startActivity(((MessageDetailActivity)getActivity()), GroupActivity.class, "groupItem",((MessageDetailActivity)getActivity()).message.getGroup());

				
			}
		});
		mMediaView = (ImageView)headerView.findViewById(R.id.amd_media);		
		if(((MessageDetailActivity)getActivity()).message.getMedia() == null){
			mMediaView.setVisibility(View.INVISIBLE);
		}else{
			mMediaView.setVisibility(View.VISIBLE);
			((MessageDetailActivity)getActivity()).mFinalBitmap.display(mMediaView, BCSUtils.generateUrl("/media/" + ((MessageDetailActivity)getActivity()).message.getMedia()));
		}
		
		if(((MessageDetailActivity)getActivity()).message.getGroup() == null)
			mBitmapManager.avatarLoader(((MessageDetailActivity)getActivity()).message.getUser().getAvatar(), mAvatarView, 0, 0);
		else
			mBitmapManager.avatarLoader(((MessageDetailActivity)getActivity()).message.getGroup().getAvatar(), mAvatarView, 0, 0);
		
		mNicknameView = (TextView)headerView.findViewById(R.id.amd_username);
		if(((MessageDetailActivity)getActivity()).message.getGroup() == null)
			mNicknameView.setText(((MessageDetailActivity)getActivity()).message.getUser().getNickName());
		else
			mNicknameView.setText(((MessageDetailActivity)getActivity()).message.getGroup().getName());
		mCreatedView = (TextView)headerView.findViewById(R.id.amd_created);
		mCreatedView.setText(StringUtils.friendly_time(((MessageDetailActivity)getActivity()).message.getCreated()));
		mContentView = (TextView)headerView.findViewById(R.id.amd_content);
		mContentView.setText(((MessageDetailActivity)getActivity()).message.getContent());
		mListView.addHeaderView(headerView);
		super.onViewCreated(view, savedInstanceState);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//setEmptyText("no Comments");
		
		mListView.setDividerHeight(0);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Comment> load() {
		// TODO Auto-generated method stub
		int messageId = ((MessageDetailActivity)getActivity()).message.getId();
		List<Comment> result = null;
		mCurrentPage = service.getMessage(messageId);
		if(mCurrentPage != null)
			result = (List<Comment>)mCurrentPage.getResults();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Comment> next() {
		// TODO Auto-generated method stub
		if(hasNext()){
			mCurrentPage = service.getNext(mCurrentPage.getNext());
			if(mCurrentPage != null)
				return (List<Comment>)mCurrentPage.getResults();
			else
				return null;
		}
		return null;
	}

	@Override
	protected SingleTypeAdapter<Comment> createAdapter(List<Comment> items) {
		// TODO Auto-generated method stub
		return new CommentListViewAdapter(this.getActivity());
	}
	
	@Override
	public void onLoadFinished(Loader<List<Comment>> arg0, List<Comment> arg1) {
		// TODO Auto-generated method stub
		super.onLoadFinished(arg0, arg1);
	}
	
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Activity activity = getActivity();
		if(mItems != null && !mItems.isEmpty()){
			if(activity instanceof MessageDetailActivity){
				if(position > 0)
					((MessageDetailActivity)activity).setReply(mItems.get(position-1).getUser());
			}
		}
		
	}

	
}
