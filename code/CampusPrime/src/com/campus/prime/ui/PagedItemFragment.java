package com.campus.prime.ui;

import java.util.List;


import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.campus.prime.R;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.PageBase;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public abstract class PagedItemFragment<E> extends ItemListFragment<E>{
	protected CommonLog log = LogFactory.createLog(AppConstant.TAG);
	
	/**
	 * Entity Page
	 */
	protected PageBase mCurrentPage;
	
	/**
	 *  sign for prompt the direction refresh
	 *  true  down
	 *  false up
	 */
	protected boolean mSign = false;
	
	private PullToRefreshAttacher mPullToRefreshAttacher;
	
	private PullToRefreshLayout mPtrLayout;
	
	
	protected PageBase getCurrentPage(){
		return mCurrentPage;
	}
	
	protected void setCurrentPage(PageBase page){
		this.mCurrentPage = page;
	}
	
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		mPullToRefreshAttacher = ((BaseActivity)this.getActivity()).getPullToRefreshAttacher();	
		mPtrLayout = (PullToRefreshLayout)view.findViewById(R.id.ptr_layout);
		mPtrLayout.setPullToRefreshAttacher(mPullToRefreshAttacher,new PullToRefreshAttacher.OnRefreshListener() {
	
			@Override
			public void onRefreshStarted(View view) {
				// TODO Auto-generated method stub
				((BaseActivity)getActivity()).setRefreshActionButtonState(true);
				new GetDataTask().execute();
			}
		});

		mListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if(view.getLastVisiblePosition() == view.getCount() - 1 && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
					mSign= true;
					if(hasNext()){
						new GetDataTask().execute();
						((BaseActivity)getActivity()).setRefreshActionButtonState(true);
					}

				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	
	/**
	 * Set pullToRefresh listView enable pull to refresh
	 * @param enable
	 */
	public void setRefreshEnable(boolean enable){
		mPullToRefreshAttacher.setEnabled(enable);
	}


	@Override
	public Loader<List<E>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		// intialize MessageService					
		((BaseActivity)getActivity()).setRefreshActionButtonState(true);
		if(mPullToRefreshAttacher != null)
			if(!mPullToRefreshAttacher.isRefreshing())
				mPullToRefreshAttacher.setRefreshing(true);
		return new AsyncLoader<List<E>>(getActivity()) {

			@Override
			protected List<E> loadData() {
				// TODO Auto-generated method stub
				return load();
			}
		};
	}
	

	@Override
	public void onLoadFinished(Loader<List<E>> arg0, List<E> arg1) {
		// TODO Auto-generated method stub
		super.onLoadFinished(arg0, arg1);
		if(!isUsable())
			return;
		this.mItems = arg1;
		getListAdapter().setItems(mItems);
		if(mPullToRefreshAttacher != null)
			if(mPullToRefreshAttacher.isRefreshing())
				mPullToRefreshAttacher.setRefreshing(false);
	}
	
	public class GetDataTask extends AsyncTask<Void,Void,List<E>>{

		@Override
		protected List<E> doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			if(!mSign){
				return first();
			}
			else{
				return next();
			}
		}
			
		@Override
		protected void onPostExecute(List<E> result) {
			// TODO Auto-generated method stub
			if(!isUsable())
				return;
			if(!mSign){
				mItems = result;
				mPullToRefreshAttacher.setRefreshComplete();
			}
			else{
				if(result != null){
					mItems.addAll(result);
				}
				mSign = false;
			}
			getListAdapter().setItems(mItems);
			((BaseActivity)getActivity()).setRefreshActionButtonState(false);
			super.onPostExecute(result);
		}
	}
	
	public void setRefreshState(boolean state){
		if(mPullToRefreshAttacher != null)
			mPullToRefreshAttacher.setRefreshing(state);
	}
	
	/**
	 * async load data
	 * @return
	 */
	protected abstract List<E> load();
	/**
	 * get first page
	 */
	protected List<E> first(){
		return load();
	}
	
	/**
	 * get next page
	 */
	protected abstract List<E> next();
	
	/**
	 * has next page
	 */
	protected boolean hasNext(){
			return (mCurrentPage == null || mCurrentPage.getNext() == null) ? false : true;
	}
	
	
}
