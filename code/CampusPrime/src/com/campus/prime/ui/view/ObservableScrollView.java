package com.campus.prime.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView{
	
	private ScrollViewListener mScrollViewListener;
	
	private boolean mIsBottom = false;
	
	public void setScrollViewListener(ScrollViewListener listener){
		this.mScrollViewListener = listener;
	}

	public ObservableScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ObservableScrollView(Context context,AttributeSet attrs) {
		// TODO Auto-generated constructor stub
		super(context,attrs);
	}
	
	public ObservableScrollView(Context context,AttributeSet attrs,int typeStyle) {
		// TODO Auto-generated constructor stub
		super(context,attrs,typeStyle);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		View view = (View) getChildAt(getChildCount()-1);
		// Calculate the scrolldiff
		int diff = (view.getBottom()-(getHeight()+getScrollY()));
		        
        // if diff is zero, then the bottom has been reached
		if( diff == 0 ){
			// notify that we have reached the bottom
			mIsBottom = true;
        }else
        	mIsBottom = false;
		if(mScrollViewListener != null){
			mScrollViewListener.onScrollChanged(this,l,t,oldl,oldt);
		}
	}
	
	
	public interface ScrollViewListener{
		void onScrollChanged(ObservableScrollView scrollView,int x,int y,int oldx,int oldy);
	}
	
	public boolean isBottom(){
		return mIsBottom;
	}
}


