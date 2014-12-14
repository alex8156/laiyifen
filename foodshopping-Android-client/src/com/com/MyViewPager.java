package com.com;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
	
	//mViewTouchMode表示ViewPager是否全权控制滑动事件，默认为false，即不控制
	private boolean mViewTouchMode = false;
	
	public boolean ismViewTouchMode(){
		return mViewTouchMode;
	}

	public void MyViewPager(boolean mViewTouchMode) {
		this.mViewTouchMode = mViewTouchMode;
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	private void setViewTouchMode(boolean b) {
		if(b && !isFakeDragging()){
			//全权滑动事件
			beginFakeDrag();
		}else if(!b && isFakeDragging()){
			//终止控制滑动事件
			endFakeDrag();
		}
		mViewTouchMode = b;
	}
	
	/**
	 * 
	 * 在mViewTouchMode为true的时候，ViewPager不拦截点击事件，点击事件将由子View处理
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if(mViewTouchMode){
			return false;
		}
		return super.onInterceptTouchEvent(event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		try{
			return super.onTouchEvent(ev); 
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	/**
	 * 在ViewTouchMode为true或者滑动昂想不是左右的时候，ViewPager将放弃控制点击事件
	 * 这样利于在ViewPager中加入ListView等可以滑动的控件，否则两者之间的滑动将有冲突
	 */
	@Override
	public boolean arrowScroll(int direction) {
		if(mViewTouchMode) return false;
		if(direction != FOCUS_LEFT && direction != FOCUS_RIGHT) return false;
		return super.arrowScroll(direction);
	}
}
