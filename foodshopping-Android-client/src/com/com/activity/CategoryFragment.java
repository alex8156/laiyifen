package com.com.activity;

import java.util.ArrayList;
import com.com.FragmentTabAdapter;
import com.com.MyViewPager;
import com.com.R;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryFragment extends Fragment {
    private View mView;
    private TextView select1_name,select2_name;
    private ImageView roll,select1_image,select2_image;
    private LinearLayout t1,t2;//标签
    private int imageWidth;
    private int offset = 0;//滚动图片偏移量
    private int currentIndex = 0;//当前页卡编号
    private MyViewPager myViewPager;
    private ArrayList<Fragment> fragmentList;//装载显示内容
    
    public ArrayList<Fragment> getFragmentList(){
    	return fragmentList;
    }
    
	   @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView = inflater.inflate(R.layout.category, container,false);
		
		
		
		//=================设定动画滚动图片的初始位置==========
		roll = (ImageView)mView.findViewById(R.id.cursor);
		 // 获取显示屏幕的宽度 和高度
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        display.getMetrics(displayMetrics);
//        displayMetrics.widthPixels  , displayMetrics.heightPixels
        // 计算偏移量
        offset = (displayMetrics.widthPixels /2 -imageWidth) /2 ; 
        // 使用Matrix 去移动图片
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        roll.setImageMatrix(matrix);
        
        // ============ 3 给标签增加监听器===========
        t1 = (LinearLayout)mView.findViewById(R.id.text1);
        t2 = (LinearLayout)mView.findViewById(R.id.text2);
        t1.setOnClickListener(new MyOnclickListener(0));
        t2.setOnClickListener(new MyOnclickListener(1));
        
        select1_name = (TextView)mView.findViewById(R.id.select1_name);
        select2_name = (TextView)mView.findViewById(R.id.select2_name);
        select1_image = (ImageView)mView.findViewById(R.id.select1_image);
        select2_image = (ImageView)mView.findViewById(R.id.select2_image);
        
       
        
        fragmentList = new ArrayList<Fragment>();
        
        //viewPager和适配器
        myViewPager = (MyViewPager)mView.findViewById(R.id.vPager);
        CategoryFragment01 fragment01 = new CategoryFragment01();
        CategoryFragment02 fragment02 = new CategoryFragment02();
        fragmentList.add(fragment01);
        fragmentList.add(fragment02);
        myViewPager.setAdapter(new FragmentTabAdapter(getActivity().getSupportFragmentManager(),fragmentList));
//        viewPager.setOnPageChangeListener((OnPageChangeListener) this);
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
					select1_image.setBackgroundResource(R.drawable.category_select1_focus);
					select1_name.setText("经典");
					select1_name.setTextColor(Color.rgb(195, 60, 0));
					select2_image.setBackgroundResource(R.drawable.category_select2_normal);
					select2_name.setText("口味");
					select2_name.setTextColor(Color.rgb(167,1167, 167));
					break;
				case 1:
					select1_image.setBackgroundResource(R.drawable.category_select1_normal);
					select1_name.setText("经典");
					select1_name.setTextColor(Color.rgb(167,167,167));
					select2_image.setBackgroundResource(R.drawable.category_select2_focus);
					select2_name.setText("口味");
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
        
        
		return mView;
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
	
	
}
