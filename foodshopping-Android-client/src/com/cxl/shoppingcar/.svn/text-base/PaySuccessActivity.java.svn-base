package com.cxl.shoppingcar;

import com.com.R;
import com.com.activity.personal.PersonalTheOrderActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PaySuccessActivity extends Activity{
	private TextView submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paysuccess);
		submit=(TextView) findViewById(R.id.submit);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(PaySuccessActivity.this, PersonalTheOrderActivity.class);
				startActivity(intent);
			}
		});
		
	}
}
