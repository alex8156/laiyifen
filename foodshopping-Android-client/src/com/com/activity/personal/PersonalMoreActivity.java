package com.com.activity.personal;

import com.com.R;
import com.com.Welcome;
import com.com.service.UserService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PersonalMoreActivity extends Activity {
	private ImageView back2;
	private RelativeLayout logoutRelative;
	private static  UserService userService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		userService = Welcome.userService;
		back2 = (ImageView)findViewById(R.id.back2);
		logoutRelative = (RelativeLayout)findViewById(R.id.logoutRelative);
		back2.setOnClickListener(onClickListener);
		logoutRelative.setOnClickListener(onClickListener);
	}
	
	
	OnClickListener onClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.back2:
				finish();
				break;
			case R.id.logoutRelative:
				userService.cancel();
				finish();
				break;
			default:
				break;
			}
			
		}
		
	};
}
