package com.cxl.shoppingcar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.Window;

/**
 * ActionBar: ActionBar 是 3.0版本以上才能使用， 低于3.0的版本 要做很多改变才能使用
 * 
 *  第一个常用功能： 返回主页， 是否带一个返回箭头
 * 
 * @author pzp
 *
 */
public class ActionBarBaseActivity extends Activity {
	public static final String TAG = "ActionBarActivity";
	
	protected ActionBar actionBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();
//        actionBar.hide();
//        actionBar.show();
        if(actionBar != null) {
        	actionBar.setDisplayHomeAsUpEnabled(false);
        	actionBar.setDisplayShowTitleEnabled(true);
        }

    }
}
