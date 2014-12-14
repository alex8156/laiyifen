package com.com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class CustomedGallery extends Gallery {

	public CustomedGallery(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public CustomedGallery(Context context, AttributeSet attrs) {
		 super(context, attrs);
	 }

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		return super.onTouchEvent(event);
	}
	
}
