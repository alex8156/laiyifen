package com.com.activity.personal;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.com.FragmentTabAdapter;
import com.com.R;

public class PersonalTheOrderActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
	
	private ImageView buttonBack;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private ViewPager viewPager;
	private RadioButton RadioButton21;
	private RadioButton RadioButton22;
	private RadioButton RadioButton23;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);
		init();

	}

	public void init() {
		// TODO Auto-generated method stub
		buttonBack = (ImageView)findViewById(R.id.back4);
		RadioButton21 = (RadioButton)findViewById(R.id.RadioButton21);
		RadioButton22 = (RadioButton)findViewById(R.id.RadioButton22);
		RadioButton23 = (RadioButton)findViewById(R.id.RadioButton23);

		
		buttonBack.setOnClickListener(this);
		RadioButton21.setOnClickListener(this);
		RadioButton22.setOnClickListener(this);
		RadioButton23.setOnClickListener(this);
		
		PaymentFragment paymentFragment = new PaymentFragment();
		DeliveryFragment deliveryFragment = new DeliveryFragment();
		AllFragment allFragment = new AllFragment();
		fragments.add(paymentFragment);
		fragments.add(deliveryFragment);
		fragments.add(allFragment);
		viewPager = (ViewPager)findViewById(R.id.viewpage2);
		FragmentTabAdapter adapter = new FragmentTabAdapter(getSupportFragmentManager(), fragments);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(this);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.back4:
			finish();
			break;

			
		case R.id.RadioButton21:
			viewPager.setCurrentItem(0);
			break;
		case R.id.RadioButton22:
			viewPager.setCurrentItem(1);
			break;
		case R.id.RadioButton23:
			viewPager.setCurrentItem(2);
			break;
			
		
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			RadioButton21.setChecked(true);
			break;
		case 1:
			RadioButton22.setChecked(true);
			break;
		case 2:
			RadioButton23.setChecked(true);
			break;
		
		}
	}
	
}
