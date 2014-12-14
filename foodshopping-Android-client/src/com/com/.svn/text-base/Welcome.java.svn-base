package com.com;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.com.service.UserService;
import com.com.service.UserService.MyBinder;
import com.com.util.BaiduMapTool;
import com.com.util.Manage;

public class Welcome extends Activity {

	private Intent intent;
	public static UserService userService = null;
	// 广播步骤1： 定义  验证SDK key 的 广播监听器
	private SdkCheckReceiver sdkCheckReceiver;

	/**
	 * 广播步骤2： 定义 验证 SDK key 以及网络异常 的 广播监听类
	 */
	public class SdkCheckReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			System.out.println("行为action: " + action);
			if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
//				Toast.makeText(Welcome.this,  "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置", Toast.LENGTH_SHORT).show();
			} else if (action.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
//				Toast.makeText(Welcome.this,  "网络出错", Toast.LENGTH_SHORT).show();
			}  
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		Manage.getInstance().addActivity(this);
		intent = new Intent();
	    intent.setAction("com.com.app.action.USERSERVICE");
	    Welcome.this.bindService(intent, conn, Service.BIND_AUTO_CREATE);
	    // 广播步骤3： 注册 SDK 广播监听者
 		IntentFilter iFilter = new IntentFilter();
 		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
 		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
 		sdkCheckReceiver = new SdkCheckReceiver();
 		registerReceiver(sdkCheckReceiver, iFilter);
 		
 		BaiduMapTool.initLocationPosition(this);
	 		
		Timer mTimer = new Timer();
		//����
		TimerTask mTask = new TimerTask()
		{
			@Override
			public void run()
			{
				Intent intent = new Intent();
				intent.putExtra("latitude", BaiduMapTool.latitude);
				intent.putExtra("longitude", BaiduMapTool.longitude);
				intent.setClass(Welcome.this, FragmentTab.class);
				startActivity(intent);
//				Welcome.this.finish();
			}
		};
		//��ʱ2m֮����ת����ҳ��
		mTimer.schedule(mTask, 2000);
	}
	ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("连接服务()");
			userService = ((MyBinder)service).getService();
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("断开服务()");
		}
	};

    protected void onDestroy() {
    	userService.unbindService(conn);
		super.onDestroy();
		// 广播步骤4： 取消监听 SDK 广播
		unregisterReceiver(sdkCheckReceiver);
    };	  
	
}
