package com.campus.prime.ui.circle;

import java.util.List;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.campus.prime.R;
import com.campus.prime.app.AppContext;
import com.campus.prime.core.Group;

public class GroupJoinedFragment extends GroupPageFragment{
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		setEmptyText("您还没有添加任何群组，请点击右上角图标选择您的群组 :)");
		setRefreshEnable(true);
		mListView.setDivider(AppContext.getContext().getResources().getDrawable(R.drawable.group_item_divider));
		mListView.setDividerHeight(2);
		mListView.setSelector(AppContext.getContext().getResources().getDrawable(R.drawable.sel_drawer_tab));
	} 

	
	@Override
	public void onLoadFinished(Loader<List<Group>> arg0,
			List<Group> arg1) {
		// TODO Auto-generated method stub
		super.onLoadFinished(arg0, arg1);
		CircleActivity activity = ((CircleActivity)getActivity());
		GroupTimelineFragment timeLineFragment = activity.getTimelineFragment();
		if(arg1 != null && !arg1.isEmpty()){
			activity.setTitle(arg1.get(0).getName());
			timeLineFragment.setCurrentGroup(arg1.get(0).getId());
			timeLineFragment.getLoaderManager().restartLoader(0, null, timeLineFragment);
			activity.menu.setSlidingEnabled(true);
			this.setEmptyViewShow(false);
		}else{
			activity.menu.setSlidingEnabled(false);
			this.setEmptyViewShow(true);
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(mAdapter instanceof CircleListViewAdapter)
			((CircleListViewAdapter)mAdapter).setSelected(position);
		mAdapter.notifyDataSetChanged();
		if(getActivity() instanceof CircleActivity){
			CircleActivity circle = (CircleActivity)getActivity();
			circle.menu.toggle();
			Group group = mItems.get(position);
			circle.setTitle(group.getName());
			circle.getTimelineFragment().setCurrentGroup(group.getId());
			circle.getTimelineFragment().refresh();
		}
	}
	
	@Override
	protected CircleListViewAdapter createAdapter() {
		// TODO Auto-generated method stub
		return new CircleListViewAdapter(this.getActivity());
	}

	public Group getSelectedGroup(){
		return ((CircleListViewAdapter)this.mAdapter).getSlectedGroup();
	}
	
	public List<Group> getItems(){
		return mItems;
	}
	
}
