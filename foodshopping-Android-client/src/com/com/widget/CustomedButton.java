package com.com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class CustomedButton extends Button {

	public CustomedButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
	}

	public CustomedButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
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
