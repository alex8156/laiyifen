package com.com.activity.personal;

import java.util.ArrayList;

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

import com.com.FragmentTab;
import com.com.FragmentTabAdapter;
import com.com.MyViewPager;
import com.com.R;
import com.com.activity.BadgeView;
import com.com.activity.CategoryFragment01;
import com.com.activity.CategoryFragment02;

public class PersonalMyloveActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
	
	 private View mView;
	    private TextView select1_name,select2_name;
	    private ImageView roll,select1_image,select2_image;
	    private ImageButton backButton,product_car;
	    private LinearLayout t1,t2;//标签
	    private int imageWidth;
	    private int offset = 0;//滚动图片偏移量
	    private int currentIndex = 0;//当前页卡编号
	    private MyViewPager myViewPager;
	    private ArrayList<Fragment> fragmentList;//装载显示内容
	    private BadgeView badgeView;
	    private int shoppingCarNum;
	    public ArrayList<Fragment> getFragmentList(){
	    	return fragmentList;
	    }
	    private Handler addBaskethandler = new Handler() {
	   		 public void handleMessage(android.os.Message msg) {
	   			 switch (msg.what) {
	   				case 50002:
	   					badgeView.setText(++shoppingCarNum+"");		
	   					break;
	   				}
	   		 };
	   	 };
//	   	public PersonalMyloveActivity(Handler addBaskethandler) {
//			this.addBaskethandler = addBaskethandler;
//		}
	   	
	   	
	   	
		   @Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.my_love);
			init();
		}

			
		private void init() {	
			badgeView = new BadgeView(this,product_car);
			shoppingCarNum = FragmentTab.a;
			badgeView.setText(shoppingCarNum+"");
			badgeView.show();
			backButton = (ImageButton)findViewById(R.id.backButton);
			backButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
					// TODO Auto-generated method stub
					backButton.setBackgroundResource(R.drawable.left_focus);
					finish();
				}
			});
			
			//=================设定动画滚动图片的初始位置==========
			roll = (ImageView)findViewById(R.id.cursor);
			 // 获取显示屏幕的宽度 和高度
	        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
	        Display display = this.getWindowManager().getDefaultDisplay();
	        display.getMetrics(displayMetrics);
//	        displayMetrics.widthPixels  , displayMetrics.heightPixels
	        // 计算偏移量
	        offset = (displayMetrics.widthPixels /2 -imageWidth) /2 ; 
	        // 使用Matrix 去移动图片
	        Matrix matrix = new Matrix();
	        matrix.postTranslate(offset, 0);
	        roll.setImageMatrix(matrix);
	        
	        // ============ 3 给标签增加监听器===========
	        t1 = (LinearLayout)findViewById(R.id.text1);
	        t2 = (LinearLayout)findViewById(R.id.text2);
	        t1.setOnClickListener(new MyOnclickListener(0));
	        t2.setOnClickListener(new MyOnclickListener(1));
	        
	        select1_name = (TextView)findViewById(R.id.select1_name);
	        select2_name = (TextView)findViewById(R.id.select2_name);
	        select1_image = (ImageView)findViewById(R.id.select1_image);
	        select2_image = (ImageView)findViewById(R.id.select2_image);
	        
	        fragmentList = new ArrayList<Fragment>();
	        
	        
	        
	        //viewPager和适配器
	        myViewPager = (MyViewPager)findViewById(R.id.vPager);
	        MyFlavorEatFragment fragment01 = new MyFlavorEatFragment();
	        FlavorFootprintFragment fragment02 = new FlavorFootprintFragment(addBaskethandler);
	        fragmentList.add(fragment01);
	        fragmentList.add(fragment02);
	        myViewPager.setAdapter(new FragmentTabAdapter(this.getSupportFragmentManager(),fragmentList));
//	        viewPager.setOnPageChangeListener((OnPageChangeListener) this);
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
					roll.startAnimation(animation);
					
					switch (position) {
					case 0:
						select1_image.setBackgroundResource(R.drawable.like_facus);
						select1_name.setText("我的爱吃");
						select1_name.setTextColor(Color.rgb(195, 60, 0));
						select2_image.setBackgroundResource(R.drawable.browse_normal);
						select2_name.setText("美味足迹");
						select2_name.setTextColor(Color.rgb(167,1167, 167));
						break;
					case 1:
						select1_image.setBackgroundResource(R.drawable.like_normal);
						select1_name.setText("我的爱吃");
						select1_name.setTextColor(Color.rgb(167,167,167));
						select2_image.setBackgroundResource(R.drawable.browse_focus);
						select2_name.setText("美味足迹");
						select2_name.setTextColor(Color.rgb(195, 60, 0));
						break;
					}
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// 当前页面被滑动时调用
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					// 当前状态改变时调用
				}
			});
		}
		
		//===========================================
		class MyOnclickListener implements  View.OnClickListener{
			   int index = 0;
			   
				public MyOnclickListener(int i) {
					this.index = i;
				}

				@Override
				public void onClick(View v) {
					// 设置为当前的选项卡，  会引起 页面切换监听
					myViewPager.setCurrentItem(index);
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
			
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	
}
