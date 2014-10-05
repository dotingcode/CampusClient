package com.campus.prime.ui;

import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.utils.ViewUtils;

public abstract class ItemListFragment<E> extends Fragment implements
	 	LoaderCallbacks<List<E>>{
	/*
	 *  Force refersh
	 */
	public static final String FORCE_REFRESH = "force_refresh";
	
	public static final int FORCE = 1;
		
	protected SingleTypeAdapter<E> mAdapter;
	/**
	 * ListView items
	 */
	protected List<E> mItems = Collections.emptyList();
	
	/**
	 * ListView
	 */
	protected ListView mListView;
	
	/**
	 * Empty TextView
	 */
	protected TextView mEmptyText;
	protected View mEmptyView;
	
	/**
	 * ProgressBar
	 */
	protected ProgressBar mProgressBar;
	
	/**
	 * Is the list currently shown
	 */
	protected boolean mListShown;
	
	
	
	public ListView getListView(){
		return this.mListView;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		getLoaderManager().initLoader(0, null, this);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.item_list,null);
		mListView = (ListView) view.findViewById(R.id.item_list);
		return view;
		
	}
	
	
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		mListShown = false;
		mEmptyText = null;
		mProgressBar = null;
		mListView = null;
		
		super.onDestroyView();
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				onListItemClick((ListView)arg0,arg1,arg2,arg3);
				
			}
		});
		
		
		this.mAdapter = createAdapter();
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				return onListItemLongClick((ListView)arg0,arg1,arg2,arg3);
			}
		});
		//progressBar = (ProgressBar)view.findViewById(R.id.pb_loading);
		mEmptyText = (TextView)view.findViewById(R.id.empty_text);
		mEmptyView = view.findViewById(R.id.empty_view);
	}
	
	protected void setEmptyViewShow(boolean visibility){
		if(visibility)
			mEmptyView.setVisibility(View.VISIBLE);
		else
			mEmptyView.setVisibility(View.INVISIBLE);
	}
	
	/**
	 * is fragment usable in UI-Thread
	 * @return
	 */
	protected boolean isUsable(){
		return getActivity() != null;
	}
	
	
	/**
	 * configure mListView
	 */
	protected void configureList(){
		this.mAdapter = createAdapter();
		mListView.setAdapter(mAdapter);
	}
	
	/**
	 * create mAdapter for mListView
	 * @return
	 */
	protected SingleTypeAdapter<E> createAdapter(){
		SingleTypeAdapter<E> wrapped = createAdapter(mItems);
		return wrapped;
	}
	protected abstract SingleTypeAdapter<E> createAdapter(final List<E> items);
	
	
	
	
	/**
	 * force to refresh
	 */
	protected void forceRefresh(){
		Bundle bundle = new Bundle();
		bundle.putInt(FORCE_REFRESH,FORCE);
		refresh(bundle);
	}
	
	
	public void refresh(){
		refresh(null);
	}
	
	protected void refresh(final Bundle args){
		((BaseActivity)getActivity()).setRefreshActionButtonState(true);
		
		getLoaderManager().restartLoader(0, args, this);
	}
	
	
	
	@Override
	public void onLoaderReset(Loader<List<E>> arg0) {
		// TODO Auto-generated method stub
		getListAdapter().setItems(Collections.emptyList());
	}
	
	
	@SuppressWarnings("unchecked")
	protected SingleTypeAdapter<E> getListAdapter(){
		return mAdapter;
	}
	
	
	protected ItemListFragment<E> notifyDataSetChanged(){
		SingleTypeAdapter<E> root = getListAdapter();
		if(root != null){
			root.notifyDataSetChanged();
		}
		return this;
	}
	
	
	 /**
     * Set list mAdapter to use on list view
     *
     * @param mAdapter
     * @return this fragment
     */
    protected ItemListFragment<E> setListAdapter(final ListAdapter mAdapter) {
        if (mListView != null)
            mListView.setAdapter(mAdapter);
        return this;
    }
	
    protected ItemListFragment<E> setEmptyText(final String message){
    	if(mEmptyText != null)
    		mEmptyText.setText(message);
    	return this;
    }
	
	@Override
	public void onLoadFinished(Loader<List<E>> arg0, List<E> arg1) {
		// TODO Auto-generated method stub
		((BaseActivity)getActivity()).setRefreshActionButtonState(false);
	}
	
	 /**
     * Callback when a list view item is clicked
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

    /**
     * Callback when a list view item is clicked and held
     *
     * @param l
     * @param v
     * @param position
     * @param id
     * @return true if the callback consumed the long click, false otherwise
     */
    public boolean onListItemLongClick(ListView l, View v, int position, long id) {
        return false;
    }
}
