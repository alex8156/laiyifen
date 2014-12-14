package com.com.mainfragment.view;

import com.com.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class BreakfastActivity extends Activity {
	
	ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.breakfast);
		back=(ImageView)findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BreakfastActivity.this.finish();
			}
		});
		
		
	}
	
}
