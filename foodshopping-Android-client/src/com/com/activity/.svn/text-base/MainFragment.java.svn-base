package com.com.activity;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.com.R;
import com.com.bean.ShoppingCate;
import com.com.bean.ShoppingCateDetail;
import com.com.bean.ShoppingMainInfo;
import com.com.bean.ShoppingSeckIll;
import com.com.mainfragment.view.BreakfastActivity;
import com.com.mainfragment.view.GroupPurchaseActivity;
import com.com.mainfragment.view.LocateMapActivity;
import com.com.mainfragment.view.PromotionActivity;
import com.com.mainfragment.view.ScanCodeActivity;
import com.com.mainfragment.view.SeckIllActivity;
import com.com.util.BaiduMapTool;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainFragment extends Fragment implements OnClickListener{
	public static final String BASE_URL =BaseUrl.BASE_URL;
	public static final String SeckIll_URL = BASE_URL + "/ShoppingSeckIllServlet";
	public static final String MAININFO_URL = BASE_URL + "/MainInfoServlet";
	public static final String CATES_URL =BASE_URL + "/ShoppingCateDetailServlet?cate_id=";
    private View mainView;
    private TextView left,erwei;
    com.handmark.pulltorefresh.library.PullToRefreshScrollView pullToRefreshScrollView;
    private EditText search;
    private SlidingMenu slidingMenu;
    private LinearLayout grouppurchase_LinearLayout,seckill_LinearLayout,
    			breakfast_LinearLayout;
    private ImageView activity_image,discount_image,week_image,week_privilege_imageView,
    		new_cate01_image,new_cate02_image,new_cate03_image,new_cate04_image,
    		new_cate_recommend_image,new_cate_try_image,meat_title_image,
    		meat01_image,meat02_image,eatmeat_party_image,fruit_faction_image,
    		fruit_title_image,fruit01_image,fruit02_image,fry_title_image,
    		fry01_image,fry02_image,frygoods_customer_image;
    
    private TextView time_hour_TextView,time_minute_TextView,time_second_TextView,
    		breakfast_time_textView,week_preference,new_cate01_name_textView,
    		new_cate01_discription_textView,new_cate02_name_textView,
    		new_cate02_discription_textView,new_cate03_name_textView,
    		new_cate03_discription_textView,new_cate04_name_textView,
    		new_cate04_discription_textView,new_cate_recommend_textView,
    		new_cate_try_textView,meat01_textView,meat02_textView,
    		eatmeat_party_textView,fruit_faction_textView,fruit01_textView,
    		fruit02_textView,fry01_textView,fry02_textView,frygoods_customer_textView;
    
    private Button button1,button2,button3,button4,button5,button6;
    ArrayList<ShoppingSeckIll> shoppingSeckIllList=new ArrayList<ShoppingSeckIll>();
    ArrayList<ShoppingMainInfo> shoppingMainInfoList=new ArrayList<ShoppingMainInfo>();
    ArrayList<Integer> shoppingCateIdList=new ArrayList<Integer>();
    private ArrayList<ShoppingCateDetail> shoppingCates = new ArrayList<ShoppingCateDetail>();
    private long time=10000;//限时抢购的时间
	private String strhour="00",strminute="00",strsecond="00";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mainView = inflater.inflate(R.layout.main, container,false);
		initView();
		initSlidingmenu();
		initData();
		return mainView;
	}
	
	private void initData() {
		new Thread(){
			@Override
			public void run() {
				Gson gson=new Gson();
				Type type=new TypeToken<ArrayList<ShoppingSeckIll>>(){}.getType();
				Type type1=new TypeToken<ArrayList<ShoppingMainInfo>>(){}.getType();
				Type type2=new TypeToken<ShoppingCateDetail>(){}.getType();
				ShoppingCateDetail shoppingCateDetail=new ShoppingCateDetail();
				String strSeckIllJson="",strMainInfoJson="",strCatesJson="";
				try {
					strSeckIllJson=HttpUtil.getRequest(SeckIll_URL);
					strMainInfoJson=HttpUtil.getRequest(MAININFO_URL);
					
					shoppingSeckIllList.clear();
					shoppingMainInfoList.clear();
					shoppingCates.clear();
					shoppingSeckIllList=gson.fromJson(strSeckIllJson, type);
					shoppingMainInfoList=gson.fromJson(strMainInfoJson, type1);
					for (ShoppingMainInfo shoppingMainInfo : shoppingMainInfoList) {
						strCatesJson=HttpUtil.getRequest(CATES_URL+shoppingMainInfo.getCate_id());
						shoppingCateIdList.add(shoppingMainInfo.getCate_id());
						shoppingCateDetail=gson.fromJson(strCatesJson, type2);
						System.out.println("详情："+strCatesJson+"---"+shoppingCateDetail+"==="+shoppingMainInfo.getCate_id());
						shoppingCates.add(shoppingCateDetail);
					}
					System.out.println("查询商品："+strSeckIllJson+"=="+shoppingSeckIllList.get(0)+"---"+shoppingSeckIllList.get(0).getSeckill_time());
					if (!shoppingSeckIllList.isEmpty()) {
						time=shoppingSeckIllList.get(0).getSeckill_time();
						System.out.println("查询后：：：：：："+time+"|||"+shoppingSeckIllList.get(0).getSeckill_time());
					}else{
						time=0;
					}
					System.out.println("查询后更改：：：：：："+time);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void initSlidingmenu() {
		slidingMenu=new SlidingMenu(MainFragment.this.getActivity());
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.main_slidingmenu);
		slidingMenu.setBehindOffsetRes(R.dimen.mainactivity_offset);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setFadeDegree(0.35f);
		Button locatebutton=(Button)slidingMenu.findViewById(R.id.LocateButton);
		Button exitbutton=(Button)slidingMenu.findViewById(R.id.exitButton);
		locatebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(), LocateMapActivity.class);
				intent.putExtra("latitude", BaiduMapTool.latitude);
				intent.putExtra("longitude", BaiduMapTool.longitude);
				startActivity(intent);
			}
		});
		exitbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (slidingMenu.isMenuShowing()) {
					slidingMenu.showContent(true);
				} else {
					slidingMenu.showMenu(true);
				}
			}
		});
	}

	private void initView() {
		left=(TextView)mainView.findViewById(R.id.left);
		erwei=(TextView)mainView.findViewById(R.id.erwei);
	    search=(EditText)mainView.findViewById(R.id.search);
	    pullToRefreshScrollView=(PullToRefreshScrollView)mainView.findViewById(R.id.main_scrollview); 
		grouppurchase_LinearLayout=(LinearLayout)mainView.findViewById(R.id.grouppurchase_LinearLayout);
		seckill_LinearLayout=(LinearLayout)mainView.findViewById(R.id.seckill_LinearLayout);
		breakfast_LinearLayout=(LinearLayout)mainView.findViewById(R.id.breakfast_LinearLayout);
		
		activity_image=(ImageView)mainView.findViewById(R.id.activity_image);
		discount_image=(ImageView)mainView.findViewById(R.id.discount_image);
		week_image=(ImageView)mainView.findViewById(R.id.week_image);
		week_privilege_imageView=(ImageView)mainView.findViewById(R.id.week_privilege_imageView);
		new_cate01_image=(ImageView)mainView.findViewById(R.id.new_cate01_image);
		new_cate02_image=(ImageView)mainView.findViewById(R.id.new_cate02_image);
		new_cate03_image=(ImageView)mainView.findViewById(R.id.new_cate03_image);
		new_cate04_image=(ImageView)mainView.findViewById(R.id.new_cate04_image);
		new_cate_recommend_image=(ImageView)mainView.findViewById(R.id.new_cate_recommend_image);
		new_cate_try_image=(ImageView)mainView.findViewById(R.id.new_cate_try_image);
		meat_title_image=(ImageView)mainView.findViewById(R.id.meat_title_image);
		meat01_image=(ImageView)mainView.findViewById(R.id.meat01_image);
		meat02_image=(ImageView)mainView.findViewById(R.id.meat02_image);
		eatmeat_party_image=(ImageView)mainView.findViewById(R.id.eatmeat_party_image);
		fruit_faction_image=(ImageView)mainView.findViewById(R.id.fruit_faction_image);
		fruit_title_image=(ImageView)mainView.findViewById(R.id.fruit_title_image);
		fruit01_image=(ImageView)mainView.findViewById(R.id.fruit01_image);
		fruit02_image=(ImageView)mainView.findViewById(R.id.fruit02_image);
		fry_title_image=(ImageView)mainView.findViewById(R.id.fry_title_image);
		fry01_image=(ImageView)mainView.findViewById(R.id.fry01_image);
		fry02_image=(ImageView)mainView.findViewById(R.id.fry02_image);
		frygoods_customer_image=(ImageView)mainView.findViewById(R.id.frygoods_customer_image);
		
		time_hour_TextView=(TextView)mainView.findViewById(R.id.time_hour_TextView);
		time_minute_TextView=(TextView)mainView.findViewById(R.id.time_minute_TextView);
		time_second_TextView=(TextView)mainView.findViewById(R.id.time_second_TextView);
		breakfast_time_textView=(TextView)mainView.findViewById(R.id.breakfast_time_textView);
		week_preference=(TextView)mainView.findViewById(R.id.week_preference);
		new_cate01_name_textView=(TextView)mainView.findViewById(R.id.new_cate01_name_textView);
		new_cate01_discription_textView=(TextView)mainView.findViewById(R.id.new_cate01_discription_textView);
		new_cate02_name_textView=(TextView)mainView.findViewById(R.id.new_cate02_name_textView);
		new_cate02_discription_textView=(TextView)mainView.findViewById(R.id.new_cate02_discription_textView);
		new_cate03_name_textView=(TextView)mainView.findViewById(R.id.new_cate03_name_textView);
		new_cate03_discription_textView=(TextView)mainView.findViewById(R.id.new_cate03_discription_textView);
		new_cate04_name_textView=(TextView)mainView.findViewById(R.id.new_cate04_name_textView);
		new_cate04_discription_textView=(TextView)mainView.findViewById(R.id.new_cate04_discription_textView);
		new_cate_recommend_textView=(TextView)mainView.findViewById(R.id.new_cate_recommend_textView);
		new_cate_try_textView=(TextView)mainView.findViewById(R.id.new_cate_try_textView);
		meat01_textView=(TextView)mainView.findViewById(R.id.meat01_textView);
		meat02_textView=(TextView)mainView.findViewById(R.id.meat02_textView);
		eatmeat_party_textView=(TextView)mainView.findViewById(R.id.eatmeat_party_textView);
		fruit_faction_textView=(TextView)mainView.findViewById(R.id.fruit_faction_textView);
		fruit01_textView=(TextView)mainView.findViewById(R.id.fruit01_textView);
		fruit02_textView=(TextView)mainView.findViewById(R.id.fruit02_textView);
		fry01_textView=(TextView)mainView.findViewById(R.id.fry01_textView);
		fry02_textView=(TextView)mainView.findViewById(R.id.fry02_textView);
		frygoods_customer_textView=(TextView)mainView.findViewById(R.id.frygoods_customer_textView);
		
		button1=(Button)mainView.findViewById(R.id.button1);
		button2=(Button)mainView.findViewById(R.id.button2);
		button3=(Button)mainView.findViewById(R.id.button3);
		button4=(Button)mainView.findViewById(R.id.button4);
		button5=(Button)mainView.findViewById(R.id.button5);
		button6=(Button)mainView.findViewById(R.id.button6);
		
		left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (slidingMenu.isMenuShowing()) {
					slidingMenu.showContent(true);
				} else {
					slidingMenu.showMenu(true);
				}
			}
		});
		erwei.setOnClickListener(this);
		search.setOnClickListener(this);
		grouppurchase_LinearLayout.setOnClickListener(this);
		seckill_LinearLayout.setOnClickListener(this);
		breakfast_LinearLayout.setOnClickListener(this);
		activity_image.setOnClickListener(this);
		discount_image.setOnClickListener(this);
		week_image.setOnClickListener(this);
		week_privilege_imageView.setOnClickListener(this);
		new_cate01_image.setOnClickListener(this);
		new_cate02_image.setOnClickListener(this);
		new_cate03_image.setOnClickListener(this);
		new_cate04_image.setOnClickListener(this);
		new_cate_recommend_image.setOnClickListener(this);
		new_cate_try_image.setOnClickListener(this);
		meat_title_image.setOnClickListener(this);
		meat01_image.setOnClickListener(this);
		meat02_image.setOnClickListener(this);
		eatmeat_party_image.setOnClickListener(this);
		fruit_faction_image.setOnClickListener(this);
		fruit_title_image.setOnClickListener(this);
		fruit01_image.setOnClickListener(this);
		fruit02_image.setOnClickListener(this);
		fry_title_image.setOnClickListener(this);
		fry01_image.setOnClickListener(this);
		fry02_image.setOnClickListener(this);
		frygoods_customer_image.setOnClickListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		formatTime(time);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent=null; 
		System.out.println("点击："+v.getId());
		switch (v.getId()) {
		case R.id.erwei:
			intent=new Intent(getActivity(), ScanCodeActivity.class);
			break;
		case R.id.search:
			intent=new Intent(getActivity(), SearchActivity.class);
			break;
		case R.id.grouppurchase_LinearLayout:
			System.out.println("响应："+R.id.grouppurchase_LinearLayout);
			intent=new Intent(getActivity(), GroupPurchaseActivity.class);
			break;
		case R.id.seckill_LinearLayout:
			intent=new Intent(getActivity(), SeckIllActivity.class);
			break;
		case R.id.breakfast_LinearLayout:
			intent=new Intent(getActivity(), BreakfastActivity.class);
			break;
		case R.id.activity_image:
			intent=new Intent(getActivity(), PromotionActivity.class);
			break;
		case R.id.discount_image:
			intent=new Intent(getActivity(), CategoryFlavorFragmentActivity.class);
			intent.putExtra("id", 8);
			intent.putExtra("name","全场九五折" );
			intent.putExtra("CategoryFragment",1);
			break;
		case R.id.week_privilege_imageView:
			intent=new Intent(getActivity(), CategoryFlavorFragmentActivity.class);
			intent.putExtra("id", 9);
			intent.putExtra("name","一周特惠" );
			intent.putExtra("CategoryFragment",1);
			break;
		case R.id.new_cate01_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1121);
			break;
		case R.id.new_cate02_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1121);
			break;
		case R.id.new_cate03_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1121);
			break;
		case R.id.new_cate04_image:
			
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1121);
			break;
		case R.id.new_cate_recommend_image:
			intent=new Intent(getActivity(), CategoryFlavorFragmentActivity.class);
			intent.putExtra("id", 3);
			intent.putExtra("name","新品推荐" );
			intent.putExtra("CategoryFragment",1);
			break;
		case R.id.new_cate_try_image:
			intent=new Intent(getActivity(), CategoryFlavorFragmentActivity.class);
			intent.putExtra("id", 4);
			intent.putExtra("name","我要尝鲜" );
			intent.putExtra("CategoryFragment",1);
			break;
		case R.id.meat_title_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.meat01_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.meat02_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.eatmeat_party_image:
			intent=new Intent(getActivity(), CategoryFlavorFragmentActivity.class);
			intent.putExtra("id", 1);
			intent.putExtra("name","食肉党" );
			intent.putExtra("CategoryFragment",1);
			break;
		case R.id.fruit_faction_image:
			intent=new Intent(getActivity(), CategoryFlavorFragmentActivity.class);
			intent.putExtra("id", 6);
			intent.putExtra("name","果干派" );
//			intent.putExtra("CategoryFragment",1);
			break;
		case R.id.fruit_title_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.fruit01_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.fruit02_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.fry_title_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.fry01_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.fry02_image:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.frygoods_customer_image:
			intent=new Intent(getActivity(), CategoryFlavorFragmentActivity.class);
			intent.putExtra("id", 2);
			intent.putExtra("name","炒货客" );
			intent.putExtra("CategoryFragment",1);
			break;
		case R.id.button1:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1042);
			break;
		case R.id.button2:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1083);
			break;
		case R.id.button3:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1047);
			break;
		case R.id.button4:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1043);
			break;
		case R.id.button5:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1041);
			break;
		case R.id.button6:
			intent=new Intent(getActivity(), ProductDetailActivity.class);
			intent.putExtra("cate_id", 1081);
			break;
		}
		startActivity(intent);
	}
	// 定时执行任务
	private ScheduledExecutorService scheduledExecutorService;
	// 用于判断是不是 定时任务 执行的 
	private boolean isAuto = false;
	// 当Activity显示出来后，执行的方法，配合 Handler 线程处理
	@Override
	public void onStart() {
		// 执行定时任务
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(
			new Runnable() {
				public void run() {
						isAuto = true;
						Bundle bundle =  new Bundle();
						time=time-1;
						System.out.println("限时时间："+time);
						if (time<0) {
							time=0;
						}
						bundle.putLong("time", time);
						Message message = new Message();
						message.setData(bundle);
						// 通过Handler切换图片
						handler.sendMessage(message);
				}
			}, 
			1, 	// 第一次执行 延时的时间
			1, 	// 每1秒钟执行一次
			TimeUnit.SECONDS);
		super.onStart();
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			long time = bundle.getLong("time");
			formatTime(time);
			time_hour_TextView.setText(strhour);
			time_minute_TextView.setText(strminute);
			time_second_TextView.setText(strsecond);
		};
	};
	@Override
	public void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 格式化时间，将其变成00:00的形式
	 */
	
	public void formatTime(long time) {
		long hour=time/3600;
		long minute=(time / 60) % 60;
		long second=time % 60;
		if (hour<10) {
			strhour=String.valueOf("0"+hour);
		}else{
			strhour=String.valueOf(hour);
		}
		if (minute<10) {
			strminute=String.valueOf("0"+minute);
		}else{
			strminute=String.valueOf(minute);
		}
		if (second<10) {
			strsecond=String.valueOf("0"+second);
		}else{
			strsecond=String.valueOf(second);
		}
	}
	
}
