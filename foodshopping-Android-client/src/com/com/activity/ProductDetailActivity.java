package com.com.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.com.FragmentTab;
import com.com.FragmentTabAdapter;
import com.com.R;
import com.com.bean.ShoppingCate;
import com.com.bean.ShoppingCateDetail;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProductDetailActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
	
	private ImageButton buttonBack;
	private ImageButton buttonShare;
	private ImageButton buttonFresh;
	private ImageButton buttonProductcar;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private ViewPager viewPager;
	private Fragment baseInfoFragment;
	private Fragment descFragment;
	private Fragment commentFragment;
	private RadioButton buttonProductInfo;
	private RadioButton buttonProductDesc;
	private RadioButton buttonProductComment;
	
	private ImageButton btBasketCar;
	private BadgeView badgeView;
	private FragmentTabAdapter adapter;
	private int cate_id;
	public int shoppingCarNum;
	private BroadcastReceiver basketBroadcastReceiver;
	
	public int getCate_id() {
		return cate_id;
	}



	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		buttonBack = (ImageButton)findViewById(R.id.button_productinfo_back);
		buttonShare = (ImageButton)findViewById(R.id.button_product_share);
		buttonFresh = (ImageButton)findViewById(R.id.button_product_fresh);
		buttonProductcar = (ImageButton)findViewById(R.id.product_car);
		buttonProductInfo = (RadioButton)findViewById(R.id.button_baseinfo);
		buttonProductDesc = (RadioButton)findViewById(R.id.button_desc);
		buttonProductComment  = (RadioButton)findViewById(R.id.button_comment);
		
		buttonBack.setOnClickListener(this);
		buttonShare.setOnClickListener(this);
		buttonFresh.setOnClickListener(this);
		buttonProductInfo.setOnClickListener(this);
		buttonProductDesc.setOnClickListener(this);
		buttonProductComment.setOnClickListener(this);
		
		baseInfoFragment = new BaseInfoFragment();
		descFragment = new DescFragment();
		commentFragment = new CommentFragment();
		fragments.add(baseInfoFragment);
		fragments.add(descFragment);
		fragments.add(commentFragment);
		viewPager = (ViewPager)findViewById(R.id.viewPager_product);
		adapter = new FragmentTabAdapter(getSupportFragmentManager(), fragments);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(this);
		
		Intent intent = getIntent();
		cate_id = intent.getIntExtra("cate_id", 0);
		
		btBasketCar = (ImageButton)findViewById(R.id.product_car);
		btBasketCar.setOnClickListener(this);
		badgeView = new BadgeView(this,btBasketCar);
//		if (FragmentTab.a != null) {
//			shoppingCarNum = Integer.parseInt(FragmentTab.a);
//		}
		shoppingCarNum = FragmentTab.a;
		badgeView.setText(shoppingCarNum+"");
		badgeView.show();
		
		//注册广播接受者
		IntentFilter filter = new IntentFilter();
		filter.addAction("ADD_TO_BASKET");
		basketBroadcastReceiver = new BasketBroadcastReceiver();
		registerReceiver(basketBroadcastReceiver, filter);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button_productinfo_back:
			finish();
			break;
		case R.id.button_product_share:
			
			break;
		case R.id.button_product_fresh:
			
			break;
			
		case R.id.button_baseinfo:
			viewPager.setCurrentItem(0);
			break;
		case R.id.button_desc:
			viewPager.setCurrentItem(1);
			break;
		case R.id.button_comment:
			viewPager.setCurrentItem(2);
			break;
	
		case R.id.product_car:
			sendBroadcast(new Intent("ENTER_INTO_BASKET"));
			finish();
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
			buttonProductInfo.setChecked(true);
			break;
		case 1:
			buttonProductDesc.setChecked(true);
			break;
		case 2:
			buttonProductComment.setChecked(true);
			break;
		
		}
	}
	
	class BasketBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if(action.equals("ADD_TO_BASKET")) {
				badgeView.setText(++shoppingCarNum+"");
			}
		}
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(basketBroadcastReceiver);
	}
	
}
