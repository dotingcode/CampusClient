package com.campus.prime.ui.app.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.campus.prime.R;
import com.campus.prime.core.Course;
import com.campus.prime.utils.IntentUtil;




import Database.DAOHelper;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class CourseFragment extends Fragment{
	
	 public static int mSelected = -1;
	 int classNum;
	 int startClassNum;
	 int endClassNum;
	 public int week;
	
	 public ListView mListView;
	 public DayCourseListAdapter mDCLAdapter;
	 public List<Course> courses = new ArrayList<Course>();	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.course_list, null);
		mListView = (ListView)view.findViewById(R.id.cd_listview);
		mDCLAdapter = new DayCourseListAdapter(getActivity(),courses);
		mListView.setAdapter(mDCLAdapter);
		mDCLAdapter.notifyDataSetChanged();
		mListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Map<String,Integer> params = new HashMap<String,Integer>();
				params.put("index",arg2);
				params.put("week",week);
			    IntentUtil.startActivity(getActivity(), EditCourseActivity.class, params);			
			} 
			
			
		});	
		return view;   
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mDCLAdapter != null)
			mDCLAdapter.notifyDataSetChanged();
	}
public class DayCourseListAdapter extends BaseAdapter {
	    	
		private LayoutInflater mInflater;
		List<Course> items;
		
		int num;
		int num2;
		
		
		public DayCourseListAdapter(Context context,List<Course> items){
			
			 this.mInflater = LayoutInflater.from(context); 
			//this.mContext = context;
			 this.items = items;
		}	
		
		public void setItems(List<Course> items){
			this.items = items;
			notifyDataSetChanged();
		}
		
		
		@Override
		public int getCount() {
			
		
		return items.size();
		}

		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			 
			return items.get(position);
			
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			 
			//ViewHolder holder = null;
		    num = position+1;
		   // num2 = ((AddCourseActivity)getActivity()).a;
			
			
			if(convertView == null){
					
				    //holder = new ViewHolder();
				    
				convertView = mInflater.inflate(R.layout.course_list_item,null);
			}			
			TextView mDayNum = (TextView)convertView.findViewById(R.id.cdl_num);
			mDayNum.setText(position+1+"");
			TextView mCourseTime = (TextView)convertView.findViewById(R.id.cdl_course_time);
			TextView mCourseName = (TextView)convertView.findViewById(R.id.cdl_course_name);
			TextView mClassroom = (TextView)convertView.findViewById(R.id.cdl_classroom);
			TextView mClassPlace = (TextView) convertView.findViewById(R.id.cdl_place);
			//TextView mNotice = (TextView)convertView.findViewById(R.id.cdl_notice);
			
			
		//	convertView.setLayoutParams(new AbsListView.LayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 200)));
		//	convertView.setBackgroundColor(Color.WHITE);
				
		//	mCourseTime.setText(items.get(position).getStartClassNum() + "-" + items.get(position).getEndClassNum()+"节");
			if(items.get(position).getCourseName() != null)
				mCourseName.setText(items.get(position).getCourseName());
			else
				mCourseName.setText(" ");
			if(items.get(position).getCourseClassroom() != null)
				mClassroom.setText(items.get(position).getCourseClassroom());
			else
				mClassroom.setText(" ");
			if(items.get(position).getCoursePlace() != null)
				mClassPlace.setText(items.get(position).getCoursePlace());
			else
				mClassPlace.setText(" ");
			if(items.get(position).getStartClassNum() == 0){
				mCourseTime.setText(" ");
			}
			else if(items.get(position).getStartClassNum()<=9 && items.get(position).getEndClassNum()<=9){
				
		        mCourseTime.setText("0"+items.get(position).getStartClassNum() + "-" + "0"+items.get(position).getEndClassNum()+"节");
			
			}else if(items.get(position).getStartClassNum()<=9 && items.get(position).getEndClassNum()>9){
				
				 mCourseTime.setText("0"+items.get(position).getStartClassNum() + "-" +items.get(position).getEndClassNum()+"节");
			}
			else if(items.get(position).getStartClassNum()>9 && items.get(position).getEndClassNum()>9){
				
				mCourseTime.setText(items.get(position).getStartClassNum() + "-" +items.get(position).getEndClassNum()+"节");
			}
			return convertView;
			
		}
		
	}

	
}
     