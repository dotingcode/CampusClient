package com.campus.prime.ui.app;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreListAdapter extends BaseAdapter{

	Context context;
	List <Score> data = new ArrayList();
	int list_item_layout;
	
	public ScoreListAdapter(Context context,int list_item_layout,List <Score> data)
	{
		this.context = context;
		this.data = data;
		this.list_item_layout = list_item_layout;
	}
	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(list_item_layout, null);
		}
		final TextView mScoreId = (TextView) convertView.findViewById(R.id.si_position);
		final TextView mCourseName = (TextView) convertView.findViewById(R.id.si_course_name);
		final TextView mCourseType = (TextView) convertView.findViewById(R.id.si_course_type);
		final TextView mCourseSorce = (TextView) convertView.findViewById(R.id.si_course_score);
		final TextView mCoursePass = (TextView) convertView.findViewById(R.id.si_course_ispass);
		mScoreId.setText(position+1+"");
		mCourseName.setText(data.get(position).getCourseName());
		mCourseType.setText(data.get(position).getCourseType());
		
		if(data.get(position).getFirstScore() >= 60)
		{
			mCoursePass.setText("通过");
			mCourseSorce.setText(data.get(position).getFirstScore()+"");
		}
		else
		{
			if(data.get(position).getSecondScore() >= 60)
			{
				mCoursePass.setText("通过");
				mCourseSorce.setText("60.0分");
			}
			else
			{
				mCoursePass.setText("未通过");

				mCourseSorce.setText(data.get(position).getFirstScore()+"");
			}
		}
			
		
		return convertView;
	}

}