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

public class PersonalCardVolumeActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
	
	private ImageView buttonBack;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private ViewPager viewPager;
	private RadioButton RadioButton11;
	private RadioButton RadioButton12;
	private RadioButton RadioButton13;
	
	private int cateId;  //商品id;
	
	public int getCateId() { 
		return cateId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab2);
		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		buttonBack = (ImageView)findViewById(R.id.back4);
		RadioButton11 = (RadioButton)findViewById(R.id.RadioButton11);
		RadioButton12 = (RadioButton)findViewById(R.id.RadioButton12);
		RadioButton13 = (RadioButton)findViewById(R.id.RadioButton13);

		
		buttonBack.setOnClickListener(this);
		RadioButton11.setOnClickListener(this);
		RadioButton12.setOnClickListener(this);
		RadioButton13.setOnClickListener(this);
		
		PointCardFragment pointCardFragment = new PointCardFragment();
		CoiponsFragment coiponsFragment = new CoiponsFragment();
		CargoVolumeFragment cargoVolumeFragment = new CargoVolumeFragment();
		fragments.add(pointCardFragment);
		fragments.add(coiponsFragment);
		fragments.add(cargoVolumeFragment);
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

			
		case R.id.RadioButton11:
			viewPager.setCurrentItem(0);
			break;
		case R.id.RadioButton12:
			viewPager.setCurrentItem(1);
			break;
		case R.id.RadioButton13:
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
			RadioButton11.setChecked(true);
			break;
		case 1:
			RadioButton12.setChecked(true);
			break;
		case 2:
			RadioButton13.setChecked(true);
			break;
		
		}
	}
	
}
