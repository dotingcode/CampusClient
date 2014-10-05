package com.campus.prime.ui.circle;

import java.util.List;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.campus.prime.R;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupItem;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.group.GroupActivity;
import com.campus.prime.ui.home.HomeActivity;
import com.campus.prime.ui.home.RegisterActivity;
import com.campus.prime.utils.IntentUtil;
import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;


public class AllGroupFragment extends GroupPageFragment{
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		if(getActivity() instanceof AllGroupActivity)
			((AllGroupActivity)getActivity()).setTitle("所有社团");
		else{
			((RegisterActivity)getActivity()).setTitle("加入社团");
			((RegisterActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
		mListView.setDivider(new ColorDrawable(android.R.color.transparent));
		mListView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		mListView.setPadding(15, 15, 15, 15);
		mListView.setScrollingCacheEnabled(true);
		mListView.setSmoothScrollbarEnabled(true);
		mListView.setDrawSelectorOnTop(true);
		mListView.setHeaderDividersEnabled(true);
		mListView.setFooterDividersEnabled(true);
		mListView.setDividerHeight(50);
		
		
		AnimationAdapter animationAdapter = new CardsAnimationAdapter(mAdapter);
        animationAdapter.setListView(mListView);
        mListView.setAdapter(animationAdapter);
        
	}
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(getActivity() instanceof RegisterActivity){
			switch(item.getItemId()){
			case R.id.action_next:
				IntentUtil.startActivity(this.getActivity(), HomeActivity.class,null);
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}else if(getActivity() instanceof AllGroupActivity){
			switch(item.getItemId()){
			case R.id.action_refresh:
				refresh();
				return true;
			case android.R.id.home:
				((AllGroupActivity)getActivity()).finish();
				return true;
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Group> load() {
		// TODO Auto-generated method stub
		List<Group> result = null;
		mCurrentPage = mService.getAllGroup();
		if(mCurrentPage != null)
			result = (List<Group>) mCurrentPage.getResults();
		return result;
			
	}
	
	@Override
	protected SingleTypeAdapter<Group> createAdapter() {
		// TODO Auto-generated method stub
		SingleTypeAdapter<Group> adapter =  new GroupDetailListViewAdapter(this.getActivity());
        return adapter;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		IntentUtil.startActivity(getActivity(), GroupActivity.class,"group",mItems.get(position));
	}
	
	
}