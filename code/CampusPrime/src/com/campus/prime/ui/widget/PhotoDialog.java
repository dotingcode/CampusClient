package com.campus.prime.ui.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.ui.view.DialogHeader;
import com.campus.prime.ui.view.Header;
import com.campus.prime.ui.view.ThemeDialog;
import com.campus.prime.ui.view.ThemeTextView;


public class PhotoDialog extends ThemeDialog{
	
	public PhotoDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.dia_list, null);
		DialogHeader header = (DialogHeader) layout.findViewById(R.id.dia_desc);
		header.setLineColor(255,50,188,182);		
		header.setLineWidth(3);
		header.setTextColor(0xff32bcb6);
		header.setText("—°‘ÒÕº∆¨");
		mList = (ListView) layout.findViewById(R.id.dia_list);
		PhotoListAdapter adapter = new PhotoListAdapter(context);
		mList.setAdapter(adapter);
		this.setContentView(layout);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
	}
	public PhotoDialog(final Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}	
	private ListView mList;
		
	public PhotoDialog setListener(OnItemClickListener listener){
		mList.setOnItemClickListener(listener);
		return this;
	}
	
	public static class PhotoListAdapter extends BaseAdapter{
		
		List<String> items = Collections.emptyList();
		private Context context;
		
		public PhotoListAdapter(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
			items = new ArrayList<String>();
			items.add("≈ƒ’’");
			items.add("œ‡≤·");
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return items.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if(arg1 == null){
				LayoutInflater inflater = LayoutInflater.from(context);
				arg1 = inflater.inflate(R.layout.dia_item, null);
			}
			final TextView textView = (TextView)arg1.findViewById(R.id.dia_item);
			textView.setText(items.get(arg0));
			return arg1;
		}
		
	}
	
	
	
}
