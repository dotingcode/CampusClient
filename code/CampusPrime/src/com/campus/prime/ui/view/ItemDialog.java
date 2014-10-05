package com.campus.prime.ui.view;

import java.util.List;

import com.campus.prime.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemDialog extends ThemeDialog
{
	ListView listView;
	DialogHeader mDialogHeader;
	protected ItemDialog(Context context) 
	{
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	protected ItemDialog(Context context, boolean cancelable,OnCancelListener cancelListener) 
	{
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}
	
	public ItemDialog(Context context, int theme,List <String> data) 
	{
		super(context,theme);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = this.getLayoutInflater();
		View parent = inflater.inflate(R.layout.dia_list, null);
		mDialogHeader = (DialogHeader) parent.findViewById(R.id.dia_desc);
		mDialogHeader.setLineColor(255,50,188,182);		
		mDialogHeader.setLineWidth(3);
		mDialogHeader.setTextColor(0xff32bcb6);
		listView = (ListView) parent.findViewById(R.id.dia_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.dia_item, R.id.dia_item, data);
		listView.setAdapter(adapter);
		this.setCancelable(true);
		this.setCanceledOnTouchOutside(true);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		this.setContentView(parent,params);
	}
	public void setItemListener(OnItemClickListener onItemClickListener)
	{
		listView.setOnItemClickListener(onItemClickListener);
	}
	public void setHeader(String text)
	{
		mDialogHeader.setText(text);
	}
	


}
