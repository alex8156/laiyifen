package com.com.mainfragment.view;

import com.baidu.mapapi.SDKInitializer;
import com.com.FragmentTab;

import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		SDKInitializer.initialize(getApplicationContext());
	}
}
