package com.campus.prime.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class StringUtils {
	
	/**
	 * 判断给定字符是否是空白字符
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
	 * 将字符串转为日期类型
	 * @param sdate
	 * @return
	 */
	
	  private final static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
        
    private final static SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd"); 
    		
    		
    		
    	

    	/**
    	 * 将字符串转为日期类型
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
	 * 以友好的方式显示时间
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
		//是在同一天
		
		String curDate =  mSimpleDateFormat2.format(cal.getTime());
		Log.d("test date  :" , curDate);
		String paramDate =  mSimpleDateFormat2.format(time);
		
		if(curDate.equals(paramDate)){	
		   int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);//一小时是3600000毫秒
            if(hour == 0 ){
            	f_time = Math.max((cal.getTimeInMillis() - time.getTime())/60000, 1)+"分钟前";	
            }else{
            	f_time = hour+"小时前";
            }
            return f_time;
		}
	
	//若不在同一天
		
     long l_time = time.getTime()/86400000; //一天是86400000毫秒
     long c_time = cal.getTimeInMillis()/86400000;
     
     int days = (int)(c_time - l_time);
     if(days == 0){
    	 
    	 int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
        
          if(hour == 0){
        	  f_time = Math.max((cal.getTimeInMillis() - time.getTime())/600000,1)+"分钟前";
            }else{
            	f_time = hour+"小时前";
             }
         }
     else if(days == 1){
    	 f_time = "昨天";
     }
     else if(days == 2){
    	 f_time = "前天";
     }
     else if(days > 2 && days <= 10){
    	 f_time = days + "天前";
     }
     else if(days > 10){
    	 f_time = mSimpleDateFormat2.format(time);
     }
     return f_time;
}


	}


	


	  

	 


        		 
         
    	 
     
	
	
	
	
	
