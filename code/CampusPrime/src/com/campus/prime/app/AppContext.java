
package com.campus.prime.app;


import android.content.Context;
import com.baidu.frontia.FrontiaApplication;


public class AppContext extends FrontiaApplication{
	
	
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
    
    public static boolean isNotified = true;
    

}
