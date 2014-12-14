package com.com.activity.personal;

import java.util.ArrayList;







import com.com.FragmentTabAdapter;
import com.com.R;
import com.com.util.Manage;

import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountsConsumptionActivity extends FragmentActivity {
	private TextView t1,t2;	// 标签
	private int imageWidth;
	private int currentIndex = 0;	// 当前页卡编号
	private ViewPager viewPager;
//	private ArrayList<View> list;	// 装载显示内容
	private ArrayList<Fragment> fragmentList;	// 装载显示内容
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.tab);
	        Manage.getInstance().addActivity(this);

	        
	        fragmentList = new ArrayList<Fragment>();
	        
	     // ============ 3 给标签增加监听器===========
	        t1 = (TextView)findViewById(R.id.textView1);
	        t2 = (TextView)findViewById(R.id.textView2);
	        
	        t1.setOnClickListener(new MyOnclickListener(0));
	        t2.setOnClickListener(new MyOnclickListener(1));
	        
	        // viewpager  和 适配器
	        viewPager = (ViewPager)findViewById(R.id.viewpagelayout);
	        
	        PayFragment payActivity = new PayFragment();
	        fragmentList.add(payActivity);
	        
	        PurchaseFragment purchaseActivity = new PurchaseFragment();
	        fragmentList.add(purchaseActivity);
	        

	        
	        viewPager.setAdapter(new FragmentTabAdapter(getSupportFragmentManager(),fragmentList));
	        // 设置缓存页面，当前页面的相邻N个页面都会被缓存
	        viewPager.setOffscreenPageLimit(2);
	        viewPager.setCurrentItem(0); // 设置默认的当前页
	        
	        // ============ 2 设置页面切换监听器===========
	        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
				@Override
				public void onPageSelected(int position) {
					Animation animation = new TranslateAnimation(currentIndex, position, 0, 0);
					animation.setFillAfter(true);
					animation.setDuration(300);

					currentIndex = position;
					
					switch (position) {
					case 0:
						t1.setTextColor(getResources().getColor(R.color.blue));
						t2.setTextColor(getResources().getColor(R.color.black));
						break;
					case 1:
						t1.setTextColor(getResources().getColor(R.color.black));
						t2.setTextColor(getResources().getColor(R.color.blue));
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
	    
		class MyOnclickListener implements  View.OnClickListener{
			int index = 0;
		   
			public MyOnclickListener(int i) {
				this.index = i;
			}

			@Override
			public void onClick(View v) {
				// 设置为当前的选项卡，  会引起 页面切换监听
				viewPager.setCurrentItem(index);
			}
		}
		
		
		
}
