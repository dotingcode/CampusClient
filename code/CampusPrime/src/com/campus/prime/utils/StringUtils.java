package com.campus.prime.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class StringUtils {
	
	/**
	 * �жϸ����ַ��Ƿ��ǿհ��ַ�
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input){
		if(input == null)
			return true;
		for(int i = 0; i< input.length();i++){
			char c = input.charAt(i);
			if(c != ' ' && c != '\t' && c != '\r' && c != '\n'){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ���ַ���תΪ��������
	 * @param sdate
	 * @return
	 */
	
	  private final static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
        
    private final static SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd"); 
    		
    		
    		
    	

    	/**
    	 * ���ַ���תΪ��������
    	 * @param sdate
	      * @return
    	 */	
    /**
	public static Date mDate(String sdate){
		try{
			return  mSimpleDateFormat.parse(sdate);
		}catch(ParseException e){
			return null;
		}
	}
	**/

	/**
	 * ���Ѻõķ�ʽ��ʾʱ��
	 * @param sdate
	 * @return
	 */
	  
	public static String friendly_time(Date time){
		//Date time = mDate(sdate);
		if(time == null){
			return "Unknown";
			
		}
		String f_time = "";
		Calendar cal = Calendar.getInstance();
		
		
		//currentDate
		//parameterDate
		//����ͬһ��
		
		String curDate =  mSimpleDateFormat2.format(cal.getTime());
		Log.d("test date  :" , curDate);
		String paramDate =  mSimpleDateFormat2.format(time);
		
		if(curDate.equals(paramDate)){	
		   int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);//һСʱ��3600000����
            if(hour == 0 ){
            	f_time = Math.max((cal.getTimeInMillis() - time.getTime())/60000, 1)+"����ǰ";	
            }else{
            	f_time = hour+"Сʱǰ";
            }
            return f_time;
		}
	
	//������ͬһ��
		
     long l_time = time.getTime()/86400000; //һ����86400000����
     long c_time = cal.getTimeInMillis()/86400000;
     
     int days = (int)(c_time - l_time);
     if(days == 0){
    	 
    	 int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
        
          if(hour == 0){
        	  f_time = Math.max((cal.getTimeInMillis() - time.getTime())/600000,1)+"����ǰ";
            }else{
            	f_time = hour+"Сʱǰ";
             }
         }
     else if(days == 1){
    	 f_time = "����";
     }
     else if(days == 2){
    	 f_time = "ǰ��";
     }
     else if(days > 2 && days <= 10){
    	 f_time = days + "��ǰ";
     }
     else if(days > 10){
    	 f_time = mSimpleDateFormat2.format(time);
     }
     return f_time;
}


	}


	


	  

	 


        		 
         
    	 
     
	
	
	
	
	
