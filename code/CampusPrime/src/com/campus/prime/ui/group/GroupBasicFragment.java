package com.campus.prime.ui.group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.ui.AsyncLoader;
import com.campus.prime.utils.BitmapManager;



public class GroupBasicFragment extends Fragment implements LoaderCallbacks<Group>{

	private GroupActivity mActivity;
	
	private static BitmapManager mBitmapManager;
	private ImageView mAvatarView;
	private TextView mNameView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mBitmapManager = BitmapManager.getInstance(this.getActivity());
		mActivity = (GroupActivity)getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.group_basic, container,false);
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mAvatarView = (ImageView)view.findViewById(R.id.gb_avatar);
		mNameView = (TextView)view.findViewById(R.id.gb_name);

	}
	
	private void bindData(){
		mBitmapManager.avatarLoader(mActivity.group.getAvatar(), mAvatarView, 0, 0);
		mNameView.setText(mActivity.group.getName());
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}
	
	
	

	@Override
	public Loader<Group> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new AsyncLoader<Group>(getActivity()) {

			@Override
			protected Group loadData() {
				// TODO Auto-generated method stub
				return mActivity.service.getDetail(mActivity.group.getId());
			}
		};
	}
	

	@Override
	public void onLoadFinished(Loader<Group> arg0, Group arg1) {
		// TODO Auto-generated method stub
		mActivity.group = arg1;
		if(mActivity.group!= null){
			bindData();
		}
	}
	@Override
	public void onLoaderReset(Loader<Group> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}