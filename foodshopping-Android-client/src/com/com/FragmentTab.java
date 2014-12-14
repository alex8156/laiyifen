package com.com;

import java.io.File;
import java.util.ArrayList;

import android.app.Notification;
import android.app.Notification.Builder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RadioButton;
import android.widget.Toast;

import com.com.activity.BadgeView;
import com.com.activity.CartFragment;
import com.com.activity.CategoryFragment;
import com.com.activity.MainFragment;
import com.com.activity.PersonalFragment;
import com.com.activity.ProductFragment;
import com.com.activity.ShoppingCar;
import com.com.db.DBManager;
import com.com.util.Manage;
import com.com.util.PathUtil;

public class FragmentTab extends FragmentActivity {
	private ViewPager viewPager;
	private  ArrayList<Fragment> fragmentList; // 装载显示内容
	private int currentIndex = 0; // 当前页卡编号
	private RadioButton t1, t2, t3, t4, t5; // 标签
	public static CartFragment cartFragment;
	private final String imagesDir = PathUtil.getExternalStorageDirectory(this) + "/serverImages/";
	private BadgeView badge;
	public static DBManager dbManager;
	
	public  ArrayList<Fragment> getFragmentList() {
		return fragmentList;
	}
	public static int a ;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			switch (msg.what) {
			case 50001:
				int shoppingCarSize = bundle.getInt("shoppingCarfoodCount");
				System.out.println("FragmentTab:"+shoppingCarSize);
				a = shoppingCarSize;
				badge.setText(a+"");				
				badge.show();
				break;
			case 50002:
				badge.setText(++a+"");				
				break;
			};
		};
	};
	private BasketBroadcastReceiver basketBroadcastReceiver;
	private NotificationManager notificationManager;
	private Notification.Builder builder;
	private Notification notification;
	private ProductFragment productFragment;
	private PersonalFragment personalFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		Manage.getInstance().addActivity(this);
		// 获取显示屏幕的宽度 和高度
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(displayMetrics);
		// ============ 3 给标签增加监听器===========
		t1 = (RadioButton) findViewById(R.id.main);
		t2 = (RadioButton) findViewById(R.id.category);
		t3 = (RadioButton) findViewById(R.id.product);
		t4 = (RadioButton) findViewById(R.id.personal);
		t5 = (RadioButton) findViewById(R.id.cart);
		t1.setOnClickListener(new MyOnclickListener(0));
		t2.setOnClickListener(new MyOnclickListener(1));
		t3.setOnClickListener(new MyOnclickListener(2));
		t4.setOnClickListener(new MyOnclickListener(3));
		t5.setOnClickListener(new MyOnclickListener(4));
		fragmentList = new ArrayList<Fragment>();
		// viewpager 和 适配器bnnbvhv
		viewPager = (ViewPager) findViewById(R.id.viewpagelayout);
		MainFragment mainFragment = new MainFragment();
//		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//		transaction.replace(R.id.fragement_main, mainFragment);
		fragmentList.add(mainFragment);
		
		CategoryFragment categoryFragment = new CategoryFragment();
//		transaction.replace(R.id.fragement_category, categoryFragment);
		fragmentList.add(categoryFragment);
		
		productFragment = new ProductFragment(handler);
//		transaction.replace(R.id.fragement_product, productFragment);
		fragmentList.add(productFragment);
		
		personalFragment = new PersonalFragment();
//		transaction.replace(R.id.fragment_personal, personalFragment);
		fragmentList.add(personalFragment);
		
		cartFragment = new CartFragment();
//		transaction.replace(R.id.fragment_cart, cartFragment);
		fragmentList.add(cartFragment);
		
//		transaction.commit();
		//初始化菜单
		menuInflater = new MenuInflater(this);
		View target = findViewById(R.id.cart);
		badge = new BadgeView(FragmentTab.this, target);
		
		//创建sqlite数据库
		dbManager = new DBManager(this);
		a = dbManager.querryBasketCount();//查询购物车数量
		badge.setText(a+"");
		badge.show();

		viewPager.setAdapter(new FragmentTabAdapter(
				getSupportFragmentManager(), fragmentList));
		// 设置缓存页面，当前页面的相邻N个页面都会被缓存
		viewPager.setOffscreenPageLimit(5);
		viewPager.setCurrentItem(0); // 设置默认的当前页
		// ============ 2 设置页面切换监听器===========
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				Animation animation = new TranslateAnimation(currentIndex,
						position, 0, 0);
				animation.setFillAfter(true);
				animation.setDuration(300);
				currentIndex = position;

				switch (position) {
				case 0:
					t1.setBackgroundResource(R.drawable.main_bottom_tab_home_focus);
					t2.setBackgroundResource(R.drawable.main_bottom_tab_category_normal);
					t3.setBackgroundResource(R.drawable.main_bottom_tab_product_normal);
					t4.setBackgroundResource(R.drawable.main_bottom_tab_personal_normal);
					t5.setBackgroundResource(R.drawable.main_bottom_tab_cart_normal);
					break;
				case 1:
					t1.setBackgroundResource(R.drawable.main_bottom_tab_home_normal);
					t2.setBackgroundResource(R.drawable.main_bottom_tab_category_focus);
					t3.setBackgroundResource(R.drawable.main_bottom_tab_product_normal);
					t4.setBackgroundResource(R.drawable.main_bottom_tab_personal_normal);
					t5.setBackgroundResource(R.drawable.main_bottom_tab_cart_normal);
					break;
				case 2:
					t1.setBackgroundResource(R.drawable.main_bottom_tab_home_normal);
					t2.setBackgroundResource(R.drawable.main_bottom_tab_category_normal);
					t3.setBackgroundResource(R.drawable.main_bottom_tab_product_focus);
					t4.setBackgroundResource(R.drawable.main_bottom_tab_personal_normal);
					t5.setBackgroundResource(R.drawable.main_bottom_tab_cart_normal);
					break;
				case 3:
					t1.setBackgroundResource(R.drawable.main_bottom_tab_home_normal);
					t2.setBackgroundResource(R.drawable.main_bottom_tab_category_normal);
					t3.setBackgroundResource(R.drawable.main_bottom_tab_product_normal);
					t4.setBackgroundResource(R.drawable.main_bottom_tab_personal_focus);
					t5.setBackgroundResource(R.drawable.main_bottom_tab_cart_normal);
					break;
				case 4:
					t1.setBackgroundResource(R.drawable.main_bottom_tab_home_normal);
					t2.setBackgroundResource(R.drawable.main_bottom_tab_category_normal);
					t3.setBackgroundResource(R.drawable.main_bottom_tab_product_normal);
					t4.setBackgroundResource(R.drawable.main_bottom_tab_personal_normal);
					t5.setBackgroundResource(R.drawable.main_bottom_tab_cart_focus);
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
		
		//注册广播接受者
		IntentFilter filter = new IntentFilter();
		filter.addAction("ADD_TO_BASKET");
		filter.addAction("ENTER_INTO_BASKET");
		filter.addAction("MY_FAVORITE");
		basketBroadcastReceiver = new BasketBroadcastReceiver();
		registerReceiver(basketBroadcastReceiver, filter);
		
		//通知栏
		notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, FragmentTab.class), 0);
		builder = new Notification.Builder(this);
		builder.setSmallIcon(R.drawable.logo).  
		setTicker("天天来伊份,营养又美味").setContentText("天天来伊份,营养又美味").setContentIntent(pendingIntent);
		notification = builder.build();
		notificationManager.notify(110, notification);
		Manage.mNotificationManager = notificationManager; 
		Manage.getInstance().addNotification(110);
	}
	
	private MenuInflater menuInflater;
    /**
     * 创建菜单： 必须重写的一个方法
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menuInflater.inflate(R.menu.menu01, menu);
    	return true;
    }
    
    /**
     * 选中菜单的选项触发的事件 
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.delete:
			new Thread(){
				public void run() {
					String [] dirs = new String[]{imagesDir, imagesDir+"cache"};
					for(String dir : dirs) {
						File subDir = new File(dir);
						if(subDir.exists()) {
							 File[]  files = subDir.listFiles();
							 for (int i = 0; i < files.length; i++) {
								System.out.println("删除缓存图片:" + files[i].getAbsolutePath());
								files[i].delete();
							}
						}
					}
				}
			}.start();
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
	
	
	public void initShoppingCar02() {
		 new Thread(){
			 public void run() {
				 cartFragment.initShoppingCar(handler);
			 };
			 }.start();

	}
	
	class MyOnclickListener implements View.OnClickListener {
		int index = 0;

		public MyOnclickListener(int i) {
			this.index = i;
		}

		@Override
		public void onClick(View v) {
			// 设置为当前的选项卡， 会引起 页面切换监听
			viewPager.setCurrentItem(index);
		}
	}

	// 以下是重写返回键，按两次才返回
	boolean isExit;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			Manage.getInstance().exit();
			dbManager.closeDB();
			unregisterReceiver(basketBroadcastReceiver);
		}
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};
	public static FragmentTab fragmentTab;
	public static FragmentTab getInstance() {
		return fragmentTab;
	}
	
	class BasketBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if(action.equals("ADD_TO_BASKET")) {
				badge.setText(++a+"");
			} else if (action.equals("ENTER_INTO_BASKET")) {
				viewPager.setCurrentItem(4);
			} 
		}
		
	}
	
}
