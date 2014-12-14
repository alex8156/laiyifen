package com.com.mainfragment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.com.R;

public class PromotionActivity extends Activity {
	private GridView gridView;
	TextView TextView01,textView3;
	List<String> list=new ArrayList<String>();
	int column=2,count=0;
	int replacePosition=0;
	int WIDTH=100;
	int itemWidth=100;
	int time=60;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.promotion);
		gridView=(GridView)findViewById(R.id.gridView1);
		TextView01=(TextView)findViewById(R.id.TextView01);
		textView3=(TextView)findViewById(R.id.textView3);
		
		 // 获取显示屏幕的宽度 和高度
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(displayMetrics);
        WIDTH=displayMetrics.widthPixels;
        initData();
		
		gridView.setAdapter(new GridViewAdapter(list));
		if (time>0) {
			gridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if (position==replacePosition) {
						column=column+1;
						count++;
						TextView01.setText(String.valueOf(count));
						initData();
						gridView.setAdapter(new GridViewAdapter(list));
					}
				}
			});
		}else{
			MessageDialog();
		}
		
	}
	
	
	private void initData() {
		itemWidth=WIDTH/column;
		list.clear();
		for (int i = 0; i < column*column; i++) {
			list.add("皀");
		}
		replacePosition=(int)(Math.random()*list.size());
		list.set(replacePosition, "皂");
		for (int i = 0; i < column*column; i++) {
			System.out.println(list.get(i).toString());
		}
	}
	// 定时执行任务
	private ScheduledExecutorService scheduledExecutorService;
	// 用于判断是不是 定时任务 执行的 
	private boolean isAuto = false;
	// 当Activity显示出来后，执行的方法，配合 Handler 线程处理
	@Override
	public void onStart() {
		// 执行定时任务
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(
			new Runnable() {
				public void run() {
						isAuto = true;
						Bundle bundle =  new Bundle();
						time=time-1;
						System.out.println("时间："+time);
						if (time<=0) {
							time=0;
						}
						bundle.putLong("time", time);
						Message message = new Message();
						message.setData(bundle);
						// 通过Handler切换图片
						handler.sendMessage(message);
				}
			}, 
			1, 	// 第一次执行 延时的时间
			1, 	// 每1秒钟执行一次
			TimeUnit.SECONDS);
		super.onStart();
	}
	protected void MessageDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(PromotionActivity.this);
		builder.setTitle("天天捡肥皂")
				.setPositiveButton("确定",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent=new Intent(PromotionActivity.this, PromotionActivity.class);
						startActivity(intent);
					}
				});
	}
	String strTime="";
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			long time = bundle.getLong("time");
			if (time==0) {
				MessageDialog();
			}
			formatTime(time);
			System.out.println("时间见时间爱你："+time);
			textView3.setText(strTime);
		};
	};
	public void formatTime(long time) {
		if (time<10) {
			strTime=String.valueOf("0"+time);
		}else{
			strTime=String.valueOf(time);
		}
	}
	@Override
	public void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	class GridViewAdapter extends BaseAdapter{
		List<String> list;
		public GridViewAdapter(List<String> list) {
			this.list=list;
		}
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return list.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView==null) {
				convertView=LayoutInflater.from(PromotionActivity.this).inflate(R.layout.promotion_item, null);
			}
			LinearLayout linearLayout=(LinearLayout)convertView.findViewById(R.id.gridView_item);
			TextView textView=(TextView)convertView.findViewById(R.id.textView);
//			LayoutParams lp=linearLayout.getLayoutParams();
//			lp.width=100;
//			lp.height=100;
//			linearLayout.setLayoutParams(lp);
//			textView.setWidth(itemWidth);
//			textView.setHeight(textView.getMeasuredWidth());
			textView.setText(list.get(position).toString());
			return convertView;
		}
		
	}
}
