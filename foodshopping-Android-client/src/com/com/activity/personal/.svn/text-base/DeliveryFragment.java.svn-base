package com.com.activity.personal;

import java.util.ArrayList;
import java.util.HashMap;

import com.com.R;
import com.com.Welcome;
import com.com.service.UserService;
import com.com.service.UserService.Orders;
import com.com.util.HttpUtil;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DeliveryFragment extends Fragment {
	private View mView;
	private ListView listview_Orders;
	private OrdersAdapter adapter;
	private UserService userService;
	private Handler handler2;
	protected ArrayList<Orders> orders;
	OrderDetailsActivity orderDetailsActivity = new OrderDetailsActivity();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView=inflater.inflate(R.layout.listview2, container, false);
		listview_Orders = (ListView) mView.findViewById(R.id.listview_Orders);
		
		userService = Welcome.userService;
		String url = UserService.getUrl() + "?operate=delivery&user_id="+ userService.getUser_id();
		SearchOrders(url, userService.getUser_id());

		listview_Orders.setOnItemClickListener( new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent in = new Intent(getActivity(),OrderDetailsActivity.class);
				userService.setOrders(orders);
				userService.setPosition(position);
				startActivity(in);
			}
		});
		return mView;
	}
	public void SearchOrders(final String url, final int user_id) {
		new Thread() {
			@Override
			public void run() {
				try {
					orders = new ArrayList<Orders>();
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("user_id", String.valueOf(user_id));
					String strJson = HttpUtil.postRequest(url, map);

					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<ArrayList<Orders>>() {
					}.getType();
					orders = gson.fromJson(strJson, type);
					// 把对象 保存到Message
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList("orders", orders);
					message.setData(bundle);
					handler.sendMessage(message);
					for (Orders orders2 : orders) {
						System.out.println(orders2);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {
				Bundle bundle = msg.getData();
				ArrayList<Orders> orders = bundle
						.getParcelableArrayList("orders");
				adapter = new OrdersAdapter(getActivity(), orders);
				listview_Orders.setAdapter(adapter);
			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	};
	

	public Handler getHandler2() {
		return handler2;
	}
	public void setHandler2(Handler handler2) {
		this.handler2 = handler2;
	}}
