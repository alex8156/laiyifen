package com.cxl.shoppingcar;

import com.com.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PaymentResultActivity extends Activity{
	private TextView submit,orderId_tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paymentresult);
		submit=(TextView) findViewById(R.id.submit);
		orderId_tv=(TextView) findViewById(R.id.orderId);
		Intent intent=getIntent();
		orderId_tv.setText(Appconstant.orderId);
		submit.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(PaymentResultActivity.this, PaySuccessActivity.class);
				startActivity(intent);
			}
		});
	}
}
