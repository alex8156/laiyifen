package com.com.activity;

import java.util.ArrayList;

import com.baidu.mapapi.map.Text;
import com.com.FragmentTab;
import com.com.FragmentTabAdapter;
import com.com.R;
import com.com.activity.GuessingFragment;
import com.com.activity.RankingFragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class FavoriteActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener {
    private TextView  textViewTitle;
    private RadioButton buttonGuessing;
    private RadioButton buttonRanking;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private FavoriteFragment guessingFragment;
    private RankingFragment rankingFragment;
    private ViewPager viewPager;
    


	public FavoriteActivity() {
    	
    }
    
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.product);
    	init();
    }



	public void init() {
		// TODO Auto-generated method stub
		textViewTitle = (TextView)findViewById(R.id.textView_title);
		textViewTitle.setText("我的爱吃");
		buttonGuessing = (RadioButton)findViewById(R.id.button_guessing);
		buttonRanking = (RadioButton)findViewById(R.id.button_ranking);
		buttonGuessing.setText("我的爱吃");
		buttonRanking.setText("美味足迹");
		buttonGuessing.setOnClickListener(this);
		buttonRanking.setOnClickListener(this);
		viewPager = (ViewPager)findViewById(R.id.viewPager_product);
		guessingFragment = new FavoriteFragment();
		rankingFragment = new RankingFragment();
		fragments.add(guessingFragment);
		fragments.add(rankingFragment);
		viewPager.setAdapter(new FragmentTabAdapter(getSupportFragmentManager(), fragments));
		viewPager.setOnPageChangeListener(this);
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		System.out.println("");
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println("");
	}


	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			buttonGuessing.setChecked(true);
			buttonRanking.setChecked(false);
			break;
		case 1:
			buttonGuessing.setChecked(false);
			buttonRanking.setChecked(true);
			break;

		
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_guessing:
			viewPager.setCurrentItem(0);
			break;

		case R.id.button_ranking:
			viewPager.setCurrentItem(1);
			break;
		}
	}

}
