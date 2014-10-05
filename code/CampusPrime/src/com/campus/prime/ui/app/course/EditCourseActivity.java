package com.campus.prime.ui.app.course;

import net.simonvt.numberpicker.NumberPicker;
import net.simonvt.numberpicker.NumberPicker.OnValueChangeListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.core.Course;
import com.campus.prime.ui.BaseActivity;

public class EditCourseActivity extends BaseActivity{
	
	private int m2 = 0;
    private int m3 = 0;
    static Dialog mDialog;
    
    
    
    private LinearLayout mLinearLayout2;
    private LinearLayout mLinearLayout3;

    private ActionMode mActionMode;
   
    private NumberPicker mNumberPicker1;
	private NumberPicker mNumberPicker2;
	private NumberPicker mNumberPicker3;
	private NumberPicker mNumberPicker4;
	
	private TextView mTextViewAddCourse;
    private TextView mTextViewAddCourse2;
    private TextView mTextViewAddCourse3;
	private TextView mTextViewOk;
	private TextView mTextViewCancel;
	
	private EditText mEditTextCourseName;
	private EditText mEditTextCourseTeacher;
	private EditText mEditTextWeekStart;
	private EditText mEditTextWeekEnd;
	
	private EditText mEditTextClassroom;
	private EditText mEditTextClassroom2;
	private EditText mEditTextClassroom3;
	
	
	
	int startNumInit = 1;
	int endNumInit = 1;
	int singleDoubleInit = 2;
	int dayOfWeekInit = 1;
	
	private int index;
	private int week;
	private Course course;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_course);
		
		index = getIntent().getIntExtra("index", -1);
		week = getIntent().getIntExtra("week", -1);
		course = Constant.sheet.get(week).get(index);
		
		
		mEditTextCourseName = (EditText)findViewById(R.id.aac_course_name);
		mEditTextCourseTeacher = (EditText)findViewById(R.id.aac_course_teacher);
		mEditTextWeekStart = (EditText)findViewById(R.id.aac_add_weekstart);
		mEditTextWeekEnd = (EditText)findViewById(R.id.aac_add_weekend);
		
		mEditTextClassroom = (EditText)findViewById(R.id.aac_add_classroom);
	//	mEditTextClassroom2 = (EditText)findViewById(R.id.aac_add_classroom2);
	  //  mEditTextClassroom3 = (EditText)findViewById(R.id.aac_add_classroom3);
	    
	    
		


	
			mTextViewAddCourse = (TextView)findViewById(R.id.aac_add_time);
			 mTextViewAddCourse.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 show();//����Dialog 
					 }
					 
			 });
			
	
	 
		 
		 
		/**
		mTextViewAddCourse2 = (TextView)findViewById(R.id.aac_add_time2);
	    mTextViewAddCourse2.setOnClickListener(new OnClickListener(){

			 @Override
		 public void onClick(View v) {
				// TODO Auto-generated method stub
				  show();//����Dialog
				    	
			}
					
	   });
		
	 
	  mTextViewAddCourse3 = (TextView)findViewById(R.id.aac_add_time3);
	  mTextViewAddCourse3.setOnClickListener(new OnClickListener(){

			@Override
		public void onClick(View v) {
				// TODO Auto-generated method stub
				  show();//����Dialog
				    	
		   }
					
	 });
	
	mLinearLayout2 = (LinearLayout)findViewById(R.id.aac_course_time_layout2);	
	 mLinearLayout2.setOnLongClickListener(new OnLongClickListener() {
		 @Override
		 public boolean onLongClick(View v) { 
			 if(mActionMode != null) {  
                   return false;  
               }   
			 mActionMode = startSupportActionMode(mActionModeCallBack);
			 v.setSelected(true);  
			 return true; 
		 }
		 });
	 
	 
	 mLinearLayout3 = (LinearLayout)findViewById(R.id.aac_course_time_layout3);
	 mLinearLayout3.setOnLongClickListener(new OnLongClickListener() {
		 @Override
		 public boolean onLongClick(View v) { 
			 if(mActionMode != null) {  
                   return false;  
               }   
			 mActionMode = startSupportActionMode(mActionModeCallBack);
			 v.setSelected(true);  
			 return true; 
		 }
		 });
	**/
	
	 mEditTextCourseName.setText(course.getCourseName());
	 mEditTextCourseTeacher.setText(course.getCourseTeacher());
	 mEditTextWeekStart.setText(course.getStartWeek());
	 mEditTextWeekEnd.setText(course.getEndWeek());
	 mEditTextClassroom.setText(course.getCourseClassroom());
	// mTextViewAddCourse.setText(course.getSingleDoubleWeek()+ " "+ course.getDayOfWeek()+ " " +course.getStartClassNum() + "-" + course.getEndClassNum() + "��");
	 
	 
	 if(course.getDayOfWeek() == 0){
		 mTextViewAddCourse.setText("�Ͽ�ʱ��");
		 mTextViewAddCourse.setClickable(true);
	 }
	 
	 else if(course.getDayOfWeek()!=0 && course.getSingleDoubleWeek()== 0){
		 mTextViewAddCourse.setText("���� "+ " "+ "��"+ course.getDayOfWeek()+ ", " +course.getStartClassNum() + "-" + course.getEndClassNum() + "��");
	     mTextViewAddCourse.setClickable(false);
	 }else if(course.getDayOfWeek()!=0 && course.getSingleDoubleWeek()== 1){
		 mTextViewAddCourse.setText("ÿ�� "+ " "+ "��"+ course.getDayOfWeek()+ ", " +course.getStartClassNum() + "-" + course.getEndClassNum() + "��");
		 mTextViewAddCourse.setClickable(false);
	 }else if(course.getDayOfWeek()!=0 && course.getSingleDoubleWeek() == 2){
		 mTextViewAddCourse.setText("˫�� "+ " "+ "��"+ course.getDayOfWeek()+ ", " +course.getStartClassNum() + "-" + course.getEndClassNum() + "��");
		 mTextViewAddCourse.setClickable(false);
	 }
	 
	 
}
	
	//��ʾdialog���ݵķ���
	   public void show(){
		// TODO Auto-generated method stub
				final Dialog mDialog =  new Dialog(EditCourseActivity.this);
				
				mDialog.setTitle("�����Ͽ�ʱ��");
				mDialog.setContentView(R.layout.npicker_dialog);
		
				/*
				 * Dialog�е�4��NumberPicker
				 */
			
				
				
				//��ʾ��˫�ܵ�NumberPicker
				
				   
				 String[] singleDoubleWeek = new String[]{"����","ÿ��","˫��"};
				    mNumberPicker1 = (NumberPicker) mDialog.findViewById(R.id.npicker_single_double);
			        mNumberPicker1.setMaxValue(3);
			        mNumberPicker1.setMinValue(1);
			        mNumberPicker1.setValue(singleDoubleInit);
			        mNumberPicker1.setDisplayedValues(singleDoubleWeek);
			        mNumberPicker1.setOnValueChangedListener(new OnValueChangeListener(){
			        	
						@Override
						public void onValueChange(NumberPicker picker, int oldVal,
								int newVal) {
							// TODO Auto-generated method stub
							singleDoubleInit = newVal;
						//	showClassNum();
						}
			        });
					 	
				
				//��ʾ��һ�����յ�NumberPicker
			       
			        String[] dayOfWeek= new String[]{"��һ","�ܶ�","����","����","����","����","����"};
			        
			        mNumberPicker2 = (NumberPicker)mDialog.findViewById(R.id.npicker_day_week);  
			        mNumberPicker2.setMinValue(1);
					mNumberPicker2.setMaxValue(7);
					mNumberPicker2.setValue(dayOfWeekInit);
					mNumberPicker2.setDisplayedValues(dayOfWeek);
	                mNumberPicker2.setOnValueChangedListener(new OnValueChangeListener(){
	                	
						@Override
						public void onValueChange(NumberPicker picker, int oldVal,
								int newVal) {
							// TODO Auto-generated method stub
							dayOfWeekInit = newVal;
						//	showClassNum();
							
						} 
				    });    
				
		   
	            //��ʾһ�ڿο�ʼ�ڵ�NumberPicker
	                
	                String[] startClassNum = new String[]{"��1��","��2��","��3��","��4��","��5��","��6��","��7��","��8��","��9��","��10��","��11��","��12��"};
	        		mNumberPicker3 = (NumberPicker)mDialog.findViewById(R.id.npicker_class_start);
	        		mNumberPicker3.setMinValue(1);
	        		mNumberPicker3.setMaxValue(12);
	        		mNumberPicker3.setValue(startNumInit);
	        		mNumberPicker3.setDisplayedValues(startClassNum);
	        		mNumberPicker3.setOnValueChangedListener(new OnValueChangeListener(){

	        				@Override
	        				public void onValueChange(NumberPicker picker, int oldVal,
	        						int newVal) {
	        					// TODO Auto-generated method stub
	        					startNumInit = newVal;
	        				//	showClassNum();
	        				} 
	        		    	
	        		    });  
					
	        		
	          //��ʾһ�ڿν����ڵ�NumberPicker
	        		String[] endClassNum = new String[]{"��1��","��2��","��3��","��4��","��5��","��6��","��7��","��8��","��9��","��10��","��11��","��12��"};
	        		mNumberPicker4 = (NumberPicker)mDialog.findViewById(R.id.npicker_class_end);
	        		mNumberPicker4.setMinValue(1);
	        		mNumberPicker4.setMaxValue(12);
	        		mNumberPicker4.setValue(endNumInit);
	        	    mNumberPicker4.setDisplayedValues(endClassNum);
	        	    mNumberPicker4.setOnValueChangedListener(new OnValueChangeListener(){

	        			@Override
	        			public void onValueChange(NumberPicker picker, int oldVal,
	        					int newVal) {
	        				// TODO Auto-generated method stub
	        				endNumInit = newVal;
	        			//	showClassNum();
	        			} 
	        	    	
	        	    });
	        		
				
			
				 
				//Dialog�е�������ť		
				 mTextViewOk = (TextView) mDialog.findViewById(R.id.nd_btn_ok);
		         mTextViewCancel = (TextView) mDialog.findViewById(R.id.nd_btn_cancel);
		        
		        //Dialog��OK��ť�ĵ���¼�
		         mTextViewOk.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mTextViewAddCourse.setText(String.valueOf(mNumberPicker1.getValue())); //set the value to textview
						
						 
						 if(singleDoubleInit == 2){
							 mTextViewAddCourse.setText("ÿ��"+ "  " + "��" + dayOfWeekInit+", "+startNumInit+"-"+endNumInit+"��");
			        		 }
			        		 else if(singleDoubleInit == 1){
			        			 mTextViewAddCourse.setText("����"+ "  "+ "��" + dayOfWeekInit+", "+startNumInit+"-"+endNumInit+"��");
			            		 }
			            		 else if(singleDoubleInit == 3){
			            			 mTextViewAddCourse.setText("˫��"+ "  "+ "�� " + dayOfWeekInit+", "+startNumInit+"-"+endNumInit+"��");
			                		 };
			               mDialog.dismiss();	
			                		 
						
					
					
					} 
		         });
		       //Dialog��CANCEL��ť�ĵ���¼� 
		         mTextViewCancel.setOnClickListener(new OnClickListener()
		         {
		          @Override
		          public void onClick(View v) {
		              mDialog.dismiss(); // dismiss the dialog
		           }    
		          });
		        
		         
		       //��ʾdialog����ͷϷ
		         mDialog.show();
		        
		        
	}
	   
	   protected void showClassNum() {
			// TODO Auto-generated method stub
				//Toast.makeText(this,"��"+startNumInit+"�ڵ���"+endNumInit+"��"
		          //      , Toast.LENGTH_SHORT).show();
				//mTextViewAddCourse.setText(String.valueOf(mNumberPicker1.getValue()));
	
		
		
	   }
	
	   
		
		 @Override
		 public boolean onOptionsItemSelected(MenuItem item) {
			 // TODO Auto-generated method stub
			 switch(item.getItemId()){
			 
			 case R.id.action_add_save:
				 //Constant.day1.get(index).setCourseClassroom("fdldf");
				 
				 course.setCourseName(mEditTextCourseName.getText().toString());
				 course.setCourseTeacher(mEditTextCourseTeacher.getText().toString());
				 course.setCourseClassroom(mEditTextClassroom.getText().toString());
				 course.setStartWeek(mEditTextWeekStart.getText().toString());
				 course.setEndWeek(mEditTextWeekEnd.getText().toString());
				 course.setSingleDoubleWeek(singleDoubleInit);
				 course.setDayOfWeek(dayOfWeekInit);
				 course.setStartClassNum(startNumInit);
				 course.setEndClassNum(endNumInit);
				 finish();
				 break;
			 case android.R.id.home:
				 this.finish();
				 break;					   
					   
			 default:
				 return super.onOptionsItemSelected(item);
				 
			 }
			 return false;
		 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_course, menu);
		ActionBar actionBar = this.getSupportActionBar();
		actionBar.setTitle("��ϸ��Ϣ");
		
		return true;
	}

}
		
		
		
		
		
		
		
		
		
		
		