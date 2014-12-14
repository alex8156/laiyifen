package com.com;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 重写一个FragmentPagerAdapter，最少要重写2个方法
 * getItem()
 * getCount()
 * @author panzhipeng
 */

public class FragmentTabAdapter extends FragmentPagerAdapter{
	
	ArrayList<Fragment> list;
	
	public FragmentTabAdapter(FragmentManager fm, ArrayList<Fragment> list) {
		super(fm);		// 必须调用
		this.list = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	
}