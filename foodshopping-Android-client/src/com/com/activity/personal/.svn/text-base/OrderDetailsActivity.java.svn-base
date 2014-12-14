package com.com.activity.personal;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.com.R;
import com.com.Welcome;
import com.com.activity.ProductDetailActivity;
import com.com.service.UserService;
import com.com.service.UserService.Address;
import com.com.service.UserService.Cate;
import com.com.service.UserService.Commodity;
import com.com.service.UserService.Orders;
import com.com.util.FileOperateTool;
import com.com.util.HttpUtil;
import com.com.util.ImageUtil;
import com.com.util.PathUtil;
import com.google.gson.Gson;

public class OrderDetailsActivity extends Activity {
	private TextView order_id, goods_item_count, goods_amount, pay_mode,
			orders_date, mail_mode, orders_bill, Reciever_address,
			Reciever_people, mail_charge, Reciever_phone;
	private ImageView back;
	private GridView gridView;
	private ImagesAdapter adapter;
	private ArrayList<Orders> orders;
	public ArrayList<Commodity> commodity;
	private Address address;
	private UserService userService;
	private int positions;
	private int cate_id;

	public int getCate_id() {
		return cate_id;
	}

	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}

	public static final int SERVER_IAMGES_RESULT = 10009;
	public static final int UPDATE_IAMGE_RESULT = 10010;

	private ImageUtil imageUtil = new ImageUtil(this);
	private final String imagesDir = PathUtil
			.getExternalStorageDirectory(OrderDetailsActivity.this)
			+ "/serverImages/";
	// 保存拖动过程中 的handler对象
	private HashMap<Integer, Handler> handlerMap = new HashMap<Integer, Handler>();
	// 异步加载图片的线程池
	private ExecutorService loadImageThreadTool;

	// http://192.168.1.222/foodshopping-servers/DownLoadImageServlet?imageName=fengweiyabo.png

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);

		loadImageThreadTool = Executors.newFixedThreadPool(6);

		userService = Welcome.userService;
		orders = userService.getOrders();
		positions = userService.getPosition();
		mView();
		String url = UserService.getUrl() + "?operate=address&address_id="
				+ orders.get(positions).getReciever_address_id();
		String url2 = UserService.getUrl() + "?operate=commodity&=orders_id="
				+ orders.get(positions).getReciever_address_id();
		SearchAddress(url, orders.get(positions).getReciever_address_id());
		SearchCommodity(url2, userService.getUser_id(), orders.get(positions)
				.getOrders_id());

		// new Thread() {
		// public void run() {
		// try {
		// HttpUtil.getRequest(UserService.SERVER_IAMGES_URL);
		// handler.sendEmptyMessage(SERVER_IAMGES_RESULT);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// }.start();
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.back111:
					finish();
					break;
				}
			}

		});

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int select,
					long arg3) {
				SearchCate(select);

			}
		});
	}

	private void mView() {
		order_id = (TextView) findViewById(R.id.order_id2);
		back = (ImageView) findViewById(R.id.back111);
		goods_item_count = (TextView) findViewById(R.id.orders_goods_item_count);
		goods_amount = (TextView) findViewById(R.id.orders_goods_amount);
		pay_mode = (TextView) findViewById(R.id.orders_pay_mode);
		orders_date = (TextView) findViewById(R.id.orders_date);
		mail_mode = (TextView) findViewById(R.id.orders_mail_mode);
		mail_charge = (TextView) findViewById(R.id.orders_mail_charge);
		orders_bill = (TextView) findViewById(R.id.orders_bill);
		Reciever_address = (TextView) findViewById(R.id.Reciever_address);
		Reciever_people = (TextView) findViewById(R.id.people);
		Reciever_phone = (TextView) findViewById(R.id.phone);
		gridView = (GridView) findViewById(R.id.gallery);
		order_id.setText(String.valueOf(orders.get(positions).getOrders_id()));
		goods_item_count.setText(String.valueOf(orders.get(positions)
				.getOrders_goods_item_count()));
		goods_amount.setText(String.valueOf(orders.get(positions)
				.getOrders_goods_amount()));
		pay_mode.setText(orders.get(positions).getOrders_pay_mode());
		orders_date.setText(orders.get(positions).getOrders_date());
		mail_mode.setText(orders.get(positions).getOrders_mail_mode());
		mail_charge.setText(String.valueOf(orders.get(positions)
				.getOrders_mail_charge()));
		orders_bill.setText(orders.get(positions).getOrders_bill());
	}

	public void SearchAddress(final String url, final int address_id) {
		new Thread() {
			@Override
			public void run() {
				try {
					address = userService.new Address();
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("address_id", String.valueOf(address_id));
					String strJson = HttpUtil.postRequest(url, map);

					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Address>() {
					}.getType();
					address = gson.fromJson(strJson, type);
					// 把对象 保存到Message
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putParcelable("address", address);
					message.setData(bundle);
					handler.sendMessage(message);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void SearchCommodity(final String url, final int user_id,
			final int orders_id) {
		new Thread() {
			@Override
			public void run() {
				try {
					commodity = new ArrayList<Commodity>();
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("user_id", String.valueOf(user_id));
					map.put("orders_id", String.valueOf(orders_id));
					String strJson = HttpUtil.postRequest(url, map);

					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<ArrayList<Commodity>>() {
					}.getType();
					commodity = gson.fromJson(strJson, type);
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList("commodity", commodity);
					message.setData(bundle);
					handler2.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void SearchCate(final int positions) {
		new Thread() {
			@Override
			public void run() {
				try {
					String foodInfo = commodity.get(positions).getFoodInfo();
					String url = UserService.getUrl()
							+ "?operate=cateid&foodInfo=";
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("cate_id", foodInfo);
					String strJson = HttpUtil.postRequest(url, map);
					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Cate>() {
					}.getType();
					Cate cate = gson.fromJson(strJson, type);
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putParcelable("Cate", cate);
					message.setData(bundle);
					handler3.sendMessage(message);
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
				Address address = bundle.getParcelable("address");
				Reciever_address.setText(address.getReciever_address_address());
				Reciever_people.setText(address.getReciever_address_name());
				Reciever_phone.setText(String.valueOf(address
						.getReciever_address_phone()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	};
	Handler handler2 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {
				Bundle bundle = msg.getData();
				ArrayList<Commodity> commodity = bundle
						.getParcelableArrayList("commodity");
				adapter = new ImagesAdapter(OrderDetailsActivity.this,
						commodity);
				gridView.setAdapter(adapter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

	};
	Handler handler3 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			Cate cate = bundle.getParcelable("Cate");
			Intent intent = new Intent(OrderDetailsActivity.this,
					ProductDetailActivity.class);
			intent.putExtra("cate_id", cate.getCate_id());
			startActivity(intent);

		};

	};

	public class ImagesAdapter extends BaseAdapter implements OnScrollListener {
		private Context context;
		private ArrayList<Commodity> commodity;

		public ImagesAdapter(Context context, ArrayList<Commodity> commodity) {
			this.context = context;
			this.commodity = commodity;
			// 设置 滚动监听器
			gridView.setOnScrollListener(this);
		}

		@Override
		public int getCount() {
			return commodity.size();
		}

		@Override
		public Object getItem(int position) {
			return commodity.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		// ===========控件========
		class viewHolder {
			ImageView imageView;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final viewHolder viewHolder;

			if (convertView == null) {
				viewHolder = new viewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.gallery_item, null);
				viewHolder.imageView = (ImageView) convertView
						.findViewById(R.id.dir_iamge);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (viewHolder) convertView.getTag();
			}

			// 获取图片信息，显示
			final Commodity commodity1 = commodity.get(position);
			// 设置默认图片
			viewHolder.imageView.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.ic_launcher));
			String imagePath = imagesDir + commodity1.getImagePath();
			viewHolder.imageView.setTag(imagePath); // 设置标识

			final Handler handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					Bundle bundle = msg.getData();
					switch (msg.what) {
					case UPDATE_IAMGE_RESULT:
						String imagePath = bundle.getString("imagePath");
						String tag = (String) viewHolder.imageView.getTag();
						if (tag.equals(imagePath)) {
							Bitmap bitmap = imageUtil
									.loadSdcardItemImage(imagePath);
							if (null != bitmap) {
								viewHolder.imageView.setImageBitmap(bitmap);
							}
						}
						break;
					}
				}
			};
			handlerMap.put(position, handler);

			// new Thread() {
			// public void run() {
			// // 去服务器下载图片
			// synchronized (viewHolder) {
			// try {
			// String imagePath = imagesDir + imageInfo.getFileName();
			// File imageFile = new File(imagePath);
			// if(!imageFile.exists()) {
			// String url = DOWN_IAMGE_URL + "?imageName=" +
			// imageInfo.getFileName();
			// // 下载服务器的图片
			// InputStream inputStream = HttpUtil.getDownFile(url);
			// // 保存到本地
			// FileOperateTool.saveFileByFileInputAndOutputStream(inputStream,
			// imagePath);
			// }
			// // 下载完以后，加载一遍，放入缓存
			// imageUtil.loadSdcardItemImage(imagePath);
			//
			// // 与UI主线程通讯
			// Message message = new Message();
			// message.what = UPDATE_IAMGE_RESULT;
			// Bundle bundle = new Bundle();
			// bundle.putString("imagePath", imagePath);
			// message.setData(bundle);
			// handler.sendMessage(message);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
			// }
			// }.start();

			return convertView;
		}

		// 只加载一屏的图片： 第一个item的位置
		private int mfirstVisibleItem;
		// 只加载一屏的图片： 一屏中所有的item的总个数
		private int mVisibleItemCount;

		private boolean isFirstEnter = true;

		/**
		 * 当gridView滚动的时候 调用， 第一显示的时候也会调用
		 */
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			mfirstVisibleItem = firstVisibleItem;
			mVisibleItemCount = visibleItemCount;
			if (isFirstEnter && visibleItemCount > 0) {
				// 显示一屏之内的图片
				showImages(mfirstVisibleItem, mVisibleItemCount);
				isFirstEnter = false;
			}
		}

		/**
		 * 当gridView滚动状态改变的时候 调用
		 */
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// 当gridView静止下来的时候才去加载图片， 滑动的时候 就不加载图片
			if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
				showImages(mfirstVisibleItem, mVisibleItemCount);
			}
		}

		/**
		 * 显示图片
		 * 
		 * @param mfirstVisibleItem
		 * @param mVisibleItemCount
		 */
		private void showImages(int mfirstVisibleItem, int mVisibleItemCount) {
			for (int i = mfirstVisibleItem; i < mfirstVisibleItem
					+ mVisibleItemCount; i++) {
				// 循环 显示 一屏之内的图片
				loadImageThreadTool.execute(new LoadImageThread().loadImage(i));
			}
			Handler handler = null;
			for (Integer position : handlerMap.keySet()) {
				handler = handlerMap.get(position);
				handler = null;
				handlerMap.remove(handler);
			}
		}

		/**
		 * 加载图片的线程，加载完之后 与 UI主线程 通讯，设置图片
		 * 
		 * @author panzhipeng
		 */
		public class LoadImageThread {
			public Runnable loadImage(final int position) {
				Thread thread = new Thread() {
					public void run() {
						try {
							String imageName = commodity.get(position)
									.getImagePath();
							String imagePath = imagesDir + imageName;
							File imageFile = new File(imagePath);
							if (!imageFile.exists()) {
								String url = UserService.DOWN_IAMGE_URL
										+ "?imageName=" + imageName;
								// 下载服务器的图片
								InputStream inputStream = HttpUtil
										.getDownFile(url);
								// 保存到本地
								FileOperateTool
										.saveFileByFileInputAndOutputStream(
												inputStream, imagePath);
							}
							// 下载完以后，加载一遍，放入缓存
							imageUtil.loadSdcardItemImage(imagePath);

							// 与UI主线程通讯
							Message message = new Message();
							message.what = UPDATE_IAMGE_RESULT;
							Bundle bundle = new Bundle();
							bundle.putString("imagePath", imagePath);
							message.setData(bundle);
							handlerMap.get(position).sendMessage(message);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				return thread;
			}
		}
	}

}
