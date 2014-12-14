package com.com.activity;

import java.util.ArrayList;

import com.com.FragmentTab;
import com.com.FragmentTabAdapter;
import com.com.MyViewPager;
import com.com.R;
import com.com.activity.CategoryFragment.MyOnclickListener;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CategoryFlavorFragmentActivity extends FragmentActivity implements OnClickListener{
	
	private ImageButton backButton,product_car;
	private TextView cftext,select1_name,select2_name,select3_name;
	private ImageView select1_image,select2_image,select3_image,cursor;
	private LinearLayout t1,t2,t3;
	private int imageWidth;
	private int offset = 0;//滚动图片偏移量
	private int currentIndex = 0;//当前页卡编号
	private static int categoryflavor_id;
	private FragmentTab fragmentTab;

//	public static void setFlavor_category_id(int flavor_category_id) {
//		CategoryFlavorFragmentActivity.flavor_category_id = flavor_category_id;
//	}

	private MyViewPager myViewPager;
	
	 private Handler addBaskethandler = new Handler() {
		 public void handleMessage(android.os.Message msg) {
			 switch (msg.what) {
				case 50002:
					badgeView.setText(++shoppingCarNum+"");		
					break;
				}
		 };
	 };
			
	 
	
	public static int getCategoryflavor_id() {
		return categoryflavor_id;
	}

	public static void setCategoryflavor_id(int categoryflavor_id) {
		CategoryFlavorFragmentActivity.categoryflavor_id = categoryflavor_id;
	}

	public CategoryFlavorFragmentActivity() {
		super();
	}

	public CategoryFlavorFragmentActivity(Handler addBaskethandler) {
		this.addBaskethandler = addBaskethandler;
	}

	private ArrayList<Fragment> fragmentList;//装载显示内容
	private BadgeView badgeView;
	private int shoppingCarNum;
	public ArrayList<Fragment> getFragmentList(){
		return fragmentList;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoryflavor);
		init();
	}

	private void init() {
		
		backButton = (ImageButton)findViewById(R.id.backButton);
		product_car = (ImageButton)findViewById(R.id.product_car);
		product_car.setOnClickListener(this);
		cftext = (TextView)findViewById(R.id.cftext);
		select1_name = (TextView)findViewById(R.id.select1_name);
		select2_name = (TextView)findViewById(R.id.select2_name);
		select3_name = (TextView)findViewById(R.id.select3_name);
		select1_image = (ImageView)findViewById(R.id.select1_image);
		select2_image = (ImageView)findViewById(R.id.select2_image);
	 	select3_image = (ImageView)findViewById(R.id.select3_image);
		cursor = (ImageView)findViewById(R.id.cursor);
		Intent intent = getIntent();
		//name对应的值等于category_name或者flavor_category_name
		cftext.setText(intent.getStringExtra("name"));
		//id的的值等于category_id或者flavor_category_id
		categoryflavor_id=intent.getIntExtra("id", categoryflavor_id);	
		int number = intent.getIntExtra("CategoryFragment", 1);
//		flavor_category_id = intent.getIntExtra("id", flavor_category_id);	 
		
		badgeView = new BadgeView(this,product_car);
//		if (FragmentTab.a != null) {
//			shoppingCarNum = Integer.parseInt(FragmentTab.a);
//		}
		shoppingCarNum = FragmentTab.a;
		badgeView.setText(shoppingCarNum+"");
		badgeView.show();
		
		
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				backButton.setBackgroundResource(R.drawable.left_focus);
				finish();
			}
		});
		
		
		// 获取显示屏幕的宽度 和高度
       DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
       Display display = this.getWindowManager().getDefaultDisplay();
       display.getMetrics(displayMetrics);
//       displayMetrics.widthPixels  , displayMetrics.heightPixels
       // 计算偏移量
       offset = (displayMetrics.widthPixels /3 -imageWidth) /2 ; 
       // 使用Matrix 去移动图片
       Matrix matrix = new Matrix();
       matrix.postTranslate(offset, 0);
       cursor.setImageMatrix(matrix);
       // ============ 3 给标签增加监听器===========
       t1 = (LinearLayout)findViewById(R.id.text1);
	   t2 = (LinearLayout)findViewById(R.id.text2);
	   t3 = (LinearLayout)findViewById(R.id.text3);
		
       t1.setOnClickListener(new MyOnclickListener(0));
       t2.setOnClickListener(new MyOnclickListener(1));
       t3.setOnClickListener(new MyOnclickListener(2));
       
       fragmentList = new ArrayList<Fragment>();
       
       //viewpager 和适配器
       myViewPager = (MyViewPager)findViewById(R.id.vPager);
       
       DefaultFragment fragment01 = new DefaultFragment(addBaskethandler,number);
       PriceFragment fragment02 = new PriceFragment(addBaskethandler,number);
       SalesFragment fragment03 = new SalesFragment(addBaskethandler,number);
       fragmentList.add(fragment01);
       fragmentList.add(fragment02);
       fragmentList.add(fragment03);
       
       myViewPager.setAdapter(new FragmentTabAdapter(this.getSupportFragmentManager(),fragmentList));
     //设置缓存页面，当前页面的相邻N个页面都会被缓存
     myViewPager.setOffscreenPageLimit(2);
     myViewPager.setCurrentItem(0);//设置默认的当前页
     
     // ============ 2 设置页面切换监听器===========
     myViewPager.setOnPageChangeListener(new OnPageChangeListener() {
     	int one = offset * 2 + imageWidth;	// 一个页卡占的偏移量
			@Override
			public void onPageSelected(int position) {
				Animation animation = new TranslateAnimation(one*currentIndex, one*position, 0, 0);
				currentIndex = position;
				animation.setFillAfter(true);
				animation.setDuration(300);
				cursor.startAnimation(animation);
				
				switch (position) {
				case 0:
					select1_image.setBackgroundResource(R.drawable.default_facus);
					select1_name.setText("默认");
					select1_name.setTextColor(Color.rgb(195, 60, 0));
					select2_image.setBackgroundResource(R.drawable.price_normal);
					select2_name.setText("价格");
					select2_name.setTextColor(Color.rgb(167,1167, 167));
					select3_name.setText("销量");
					select3_name.setTextColor(Color.rgb(167,1167, 167));
					break;
				case 1:
					select1_image.setBackgroundResource(R.drawable.default_normal);
					select1_name.setText("默认");
					select1_name.setTextColor(Color.rgb(167,1167, 167));
					select2_image.setBackgroundResource(R.drawable.price_facus);
					select2_name.setText("价格");
					select2_name.setTextColor(Color.rgb(195, 60, 0));
					select3_name.setText("销量");
					select3_name.setTextColor(Color.rgb(167,1167, 167));
					break;
				case 2:
					select1_image.setBackgroundResource(R.drawable.default_normal);
					select1_name.setText("默认");
					select1_name.setTextColor(Color.rgb(167,1167, 167));
					select2_image.setBackgroundResource(R.drawable.price_normal);
					select2_name.setText("价格");
					select2_name.setTextColor(Color.rgb(167,1167, 167));
					select3_name.setText("销量");
					select3_name.setTextColor(Color.rgb(195, 60, 0));
					break;	
				}
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				//当前页面被滑动时调用
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				//当前状态改变时调用
			}
     	});
	}
	
	//=================================================
	class MyOnclickListener implements View.OnClickListener{
		int index = 0;
		public MyOnclickListener(int i){
			this.index = i;
		}
		@Override
		public void onClick(View v) {
			//设置为当前的选项卡，会引起页面切换监听
			myViewPager.setCurrentItem(index);
			}
		}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		sendBroadcast(new Intent("ENTER_INTO_BASKET"));
		finish();
	}
	
	
}	

