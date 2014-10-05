package com.campus.prime.ui.app;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.campus.prime.R;
import com.campus.prime.ui.BaseActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 日历显示activity
 * 
 *
 */
public class SchoolCalendarActivity extends BaseActivity
{

	private CalendarAdapter calV = null;
	private GridView gridView = null;
	LinearLayout view = null;
	TextView topView = null;
	private TextView weekText = null;
	private TextView dateText = null;
	private TextView messageText = null;
	private static int jumpMonth = 0;      //每次滑动，增加或减去一个月,默认为0（即显示当前月）
	private static int jumpYear = 0;       //滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private String currentDate = "";
	
	public SchoolCalendarActivity() {

		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    	currentDate = sdf.format(date);  //当期日期
    	year_c = Integer.parseInt(currentDate.split("-")[0]);
    	month_c = Integer.parseInt(currentDate.split("-")[1]);
    	day_c = Integer.parseInt(currentDate.split("-")[2]);   	
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		view = new LinearLayout(this);
		topView = new TextView(this);
		/**
		toPre = (LinearLayout) this.findViewById(R.id.btn_prev_month);
		toPre.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int gvFlag = 0; 
				addGridView();   //添加一个gridView
				jumpMonth--;     //上一个月		
				calV = new CalendarAdapter(SchoolCalendarActivity.this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
		        gridView.setAdapter(calV);
		        gvFlag++;
		        addTextToTopTextView(topText);
		        weekText.setText("");
		        dateText.setText("");
		        messageText.setText("");
		        
			}});
		toNext = (LinearLayout) this.findViewById(R.id.btn_next_month);
		toNext.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int gvFlag = 0;
				addGridView();   //添加一个gridView
				jumpMonth++;     //下一个月
				
				calV = new CalendarAdapter(SchoolCalendarActivity.this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
		        gridView.setAdapter(calV);
		        addTextToTopTextView(topText);
		        gvFlag++;
		        weekText.setText("");
		        dateText.setText("");
		        messageText.setText("");
			}});
			*/
        calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
        addGridView();
        gridView.setAdapter(calV);
		
		weekText = (TextView) this.findViewById(R.id.week_text);
		weekText.setText(new CalendarSimulateData().getWeek(year_c+"", month_c+"", day_c+"")!=-1?"第"+ (new CalendarSimulateData().getWeek(year_c+"", month_c+"", day_c+"")+1) +"周":"暂无数据");
		dateText = (TextView) this.findViewById(R.id.date_text);
		dateText.setText(month_c+"月"+day_c+"日" +" 农历  "+ CalendarToLauar.getSimpleLunar(year_c+"", month_c+"", day_c+""));
		messageText = (TextView) this.findViewById(R.id.message_text);
		messageText.setText(new CalendarSimulateData().getMessage(year_c+"", month_c+"", day_c+""));
		topView.setText(year_c+"."+month_c+"."+day_c+"  ");
	}

	
	//创建菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		ActionBar actionBar = this.getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		showProgress(false);
		menu.add(0, 0, 0, "上月");
		menu.add(0, 1, 1, "下月");
		menu.add(0, 2, 2, "今天");
		actionBar.setTitle("校历");
		
		//topView.setText("2014.12.01  ");
		topView.setTextSize(24);
		topView.setGravity(Gravity.CENTER);
		topView.setTextColor(0xffe4e4e4);
		view.setGravity(Gravity.CENTER);
		view.addView(topView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		ActionBar.LayoutParams params = new ActionBar.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		params.gravity=Gravity.CENTER_VERTICAL;
		params.gravity=Gravity.RIGHT;
		actionBar.setCustomView(view, params);
		return true;
	}
	
	//选择菜单	 
	@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        
        case android.R.id.home:
        	this.finish();
        	break;
        case 0:
			addGridView();   //添加一个gridView
			jumpMonth--;     //上一个月		
			calV = new CalendarAdapter(SchoolCalendarActivity.this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calV);
	        weekText.setText("未选择日期");
	        dateText.setText("");
	        messageText.setText("");
	        topView.setText(calV.getShowYear()+"."+calV.getShowMonth()+"  ");
        	break;
        case 1:
        	Log.i("测试", 2+"++++++");
			addGridView();   //添加一个gridView
			jumpMonth++;     //下一个月
			
			calV = new CalendarAdapter(SchoolCalendarActivity.this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calV);
	        weekText.setText("未选择日期");
	        dateText.setText("");
	        messageText.setText("");
	        topView.setText(calV.getShowYear()+"."+calV.getShowMonth()+"  ");
	        break;
        case 2:
        	//跳转到今天
        	int xMonth = jumpMonth;
        	int xYear = jumpYear;
        	int gvFlag =0;
        	jumpMonth = 0;
        	jumpYear = 0;
        	addGridView();   //添加一个gridView
        	year_c = Integer.parseInt(currentDate.split("-")[0]);
        	month_c = Integer.parseInt(currentDate.split("-")[1]);
        	day_c = Integer.parseInt(currentDate.split("-")[2]);
        	calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calV);
	        //addTextToTopTextView(topText);
	        gvFlag++;
	        weekText.setText(new CalendarSimulateData().getWeek(year_c+"", month_c+"", day_c+"")!=-1?"第"+ (new CalendarSimulateData().getWeek(year_c+"", month_c+"", day_c+"")+1) +"周":"暂无数据");			
	        dateText.setText(month_c+"月"+day_c+"日" +" 农历  "+ CalendarToLauar.getSimpleLunar(year_c+"", month_c+"", day_c+""));
			messageText.setText(new CalendarSimulateData().getMessage(year_c+"", month_c+"", day_c+""));
        	topView.setText(year_c +"."+month_c+"."+day_c+"  ");
			break;
        }
        
		return true;
	}
	
	
	//添加头部的年份 闰哪月等信息
	public void addTextToTopTextView(TextView view){
		StringBuffer textDate = new StringBuffer();
		textDate.append(calV.getShowYear()).append("年").append(
				calV.getShowMonth()).append("月").append("\t");
		view.setText(textDate);
		view.setTextColor(Color.WHITE);
		view.setTypeface(Typeface.DEFAULT_BOLD);
	}
	
	//添加gridview
	private void addGridView() {
		
		gridView =(GridView)findViewById(R.id.gridview);
		gridView.setOnItemClickListener(new OnItemClickListener() {
            //gridView中的每一个item的点击事件
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				  //点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
				  int startPosition = calV.getStartPositon();
				  int endPosition = calV.getEndPosition();
				  if(startPosition <= position+7  && position <= endPosition-7){
					  String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0];  //这一天的阳历
					  String scheduleLunarDay = calV.getDateByClickItem(position).split("\\.")[1];  //这一天的阴历
	                  String scheduleYear = calV.getShowYear();
	                  String scheduleMonth = calV.getShowMonth();
	                  weekText.setText(new CalendarSimulateData().getWeek(scheduleYear, scheduleMonth, scheduleDay)!=-1?"第"+ (new CalendarSimulateData().getWeek(scheduleYear, scheduleMonth, scheduleDay)+1) +"周":"暂无数据");
	          	 	  dateText.setText( scheduleMonth + "月" + scheduleDay + "日" + " 农历  " + CalendarToLauar.getSimpleLunar(scheduleYear, scheduleMonth, scheduleDay));
	          		  messageText.setText(new CalendarSimulateData().getMessage(scheduleYear, scheduleMonth, scheduleDay));  
	          		  topView.setText(scheduleYear+"."+scheduleMonth+"."+scheduleDay+"  ");
				  }
				  }
			
			
		});
	}
	
}