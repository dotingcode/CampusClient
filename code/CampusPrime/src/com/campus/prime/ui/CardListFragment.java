

package com.campus.prime.ui;

import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardListView;
import com.campus.prime.R;
import com.campus.prime.core.Message;

public abstract class CardListFragment<E extends CardBase<E>> extends Fragment implements
	 	LoaderCallbacks<List<E>>{
	/*
	 *  Force refersh
	 */
	public static final String FORCE_REFRESH = "force_refresh";
	
	public static final int FORCE = 1;
		
	protected CardAdapter<E> adapter;
	/**
	 * ListView items
	 */
	protected List<E> items = Collections.emptyList();
	
	/**
	 * ListView
	 */
	protected CardListView listView;
	
	/**
	 * Empty TextView
	 */
	protected TextView emptyView;
	
	/**
	 * ProgressBar
	 */
	protected ProgressBar progressBar;
	
	/**
	 * Is the list currently shown
	 */
	protected boolean listShown;
	
	
	
	public ListView getListView(){
		return this.listView;
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
		//((BaseActivity)getActivity()).setRefreshActionButtonState(true);
		getLoaderManager().initLoader(0, null, this);
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.card_item_list,null);
		listView = (CardListView) view.findViewById(R.id.card_item_list);
		return view;
		
	}
	
	
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		listShown = false;
		emptyView = null;
		progressBar = null;
		listView = null;
		
		super.onDestroyView();
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		this.adapter = createCardAdapter(items);
		listView.setAdapter(adapter);
		
		listView.setOnCardClickListener(new CardListView.CardClickListener() {
			
			@Override
			public void onCardClick(int index, CardBase card, View view) {
				// TODO Auto-generated method stub
				onItemClick(index,card,view);
			}
		});
		
		
		listView.setOnCardLongClickListener(new CardListView.CardLongClickListener() {
			
			@Override
			public boolean onCardLongClick(int index, CardBase card, View view) {
				// TODO Auto-generated method stub
				return onItemLongClick(index,card, view);

			}
		});
		
		emptyView = (TextView)view.findViewById(R.id.empty);
		
		
	}
	
	/**
	 * is fragment usable in UI-Thread
	 * @return
	 */
	protected boolean isUsable(){
		return getActivity() != null;
	}
	
	
	/**
	 * configure listView
	 */
	protected void configureList(){
		this.adapter = createCardAdapter(items);
		listView.setAdapter(adapter);
	}
	
	/**
	 * create adapter for listView
	 * @return
	 */
	protected abstract CardAdapter<E> createCardAdapter(final List<E> items);
	
	
	
	
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
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onLoaderReset(Loader<List<E>> arg0) {
		// TODO Auto-generated method stub
		getListAdapter().set((List<E>) Collections.emptyList());
	}
	
	
	@SuppressWarnings("unchecked")
	protected CardAdapter<E> getListAdapter(){
		return adapter;
	}
	
	
	protected CardListFragment<E> notifyDataSetChanged(){
		CardAdapter<E> root = getListAdapter();
		if(root != null){
			root.notifyDataSetChanged();
		}
		return this;
	}
	
	
	 /**
     * Set list adapter to use on list view
     *
     * @param adapter
     * @return this fragment
     */
    protected CardListFragment<E> setListAdapter(final CardAdapter<E> adapter) {
        if (listView != null)
            listView.setAdapter(adapter);
        return this;
    }
	
    protected CardListFragment<E> setEmptyText(final String message){
    	if(emptyView != null)
    		emptyView.setText(message);
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
    public void onItemClick(int index, CardBase card,View view) {
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
    public boolean onItemLongClick(int index,CardBase card,View view) {
        return false;
    }
}
