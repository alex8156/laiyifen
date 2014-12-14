package com.com.util;  
  
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.app.Service;

public class Manage extends Application{
	public static NotificationManager mNotificationManager;	
	
	/**
	 * 结束所有Activity
	 * @author activity
	 *
	 */
    //运用list来保存们每一个activity是关键  
    private List<Activity> mList = new LinkedList<Activity>(); 
    private List<Service> services = new LinkedList<Service>(); 
    private List<Integer> notificationIds  = new ArrayList<Integer>();
	// private List<Service> mService = new LinkedList<Service>(); 
	 
	 
    //为了实现每次使用该类时不创建新的对象而创建的静态对象  
    private static Manage instance;   
    //构造方法  
    private Manage(){}  
    //实例化一次  
    public synchronized static Manage getInstance(){   
        if (null == instance) {   
            instance = new Manage();   
        }   
        return instance;   
    }   
    // add Activity    
    public void addActivity(Activity activity) {   
        mList.add(activity);   
    }   
    // add Activity    
   public void addService(Service service) {   
	   services.add(service);   
   }  
   
   // add Notification    
   public void addNotification(int notificationId) {   
	   notificationIds.add(notificationId);   
   } 
	   
    //关闭每一个list内的activity  
    public void exit() {   
        try {   
            for (Activity activity:mList) {   
                if (activity != null)   
                    activity.finish();   
            }   
            for (Service service:services) {   
	               if (service != null)       	   
	            	   service.stopSelf(); 
            }   
            for (int notificationId : notificationIds) {
            	if(notificationIds.size()>0 && mNotificationManager!=null) 
                 	mNotificationManager.cancel(notificationId);
            }
        }catch (Exception e) {   
        	e.printStackTrace();   
        	} finally {   
        		System.exit(0);   
        	}   
    	}   
    //杀进程  
    public void onLowMemory() {   
        super.onLowMemory();       
        System.gc();   
    }    

}
