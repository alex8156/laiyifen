package com.com.activity;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.com.FragmentTab;
import com.com.R;
import com.com.Welcome;
import com.com.service.UserService;
import com.com.util.BaseUrl;
import com.com.util.FileOperateTool;
import com.com.util.HttpUtil;
import com.com.util.ImageUtil;
import com.com.util.PathUtil;
import com.cxl.shoppingcar.CommitOrderActivity;
import com.cxl.shoppingcar.ShoppingCar;
import com.google.gson.Gson;

public class CartFragment extends Fragment {

	public static final String BASE_URL = BaseUrl.BASE_URL;

	public static final String DOWN_IAMGE_URL = BASE_URL + "/downloadServlet05";
	public static final String initShoppingCar_URL = BASE_URL
			+ "/shoppingCarInitServlet";
	public static final String shoppingCarSyncServlet_URL = BASE_URL
			+ "/shoppingCarSyncServlet";
	private final String imagesDir = PathUtil
			.getExternalStorageDirectory(getActivity()) + "/serverImages/";
	private View mView;
	private Button editAndsave_btn;
	private ListView shoppingcar_lv;
	private TextView totalprice_tv02;
	private ShoppingCarBasAdapter adapter;
	private ArrayList<ShoppingCar> shoppingCars = new ArrayList<ShoppingCar>();
	private int foodCount; // 购买数量
	private String strUnitPrice;// 单价
	public int shoppingCarfoodCount;// 购物车数量
	private Button settlement_btn;// 结算按钮
	public static final int UPDATE_IAMGE_RESULT = 10010;
	private ImageUtil imageUtil = new ImageUtil(getActivity());
	private double strTotalPrice;// 总价
	private DecimalFormat decimalFormat;
	boolean c = false;
	private UserService userService;
	Handler handler02;
	Handler handler01 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1989:
				 
				// 设置总价
				decimalFormat = new DecimalFormat("0.00");
				totalprice_tv02.setText("￥"
						+ decimalFormat.format(strTotalPrice));			
				break;
			case 1990:
				adapter.notifyDataSetChanged();
				break;
			case 1991:			
				Bundle bundle=new Bundle();
				bundle=msg.getData();
				currentUserId= bundle.getInt("userId");
				System.out.println("222222"+ currentUserId);
				getShoppingCarList(currentUserId);	
				break;	
			case 1992:			
				linearLayout_shopcart_empty.setVisibility(View.VISIBLE);				
				break;	
			case 1993:			
				linearLayout_shopcart_empty.setVisibility(View.GONE);
				relativeLayout04.setVisibility(View.VISIBLE);	
				break;		
			}
		};
	};
	
	private int currentUserId;
	private LinearLayout linearLayout_shopcart_empty;
	private RelativeLayout relativeLayout04;
	// 保存拖动过程中 的handler对象
	private HashMap<Integer, Handler> handlerMap = new HashMap<Integer, Handler>();
	// 异步加载图片的线程池
	private ExecutorService loadImageThreadTool;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);		
		mView = inflater.inflate(R.layout.cart, container, false);

		// 初始化线程池
		loadImageThreadTool = Executors.newFixedThreadPool(6);
		userService = Welcome.userService;
		editAndsave_btn = (Button) mView.findViewById(R.id.editAndsave_btn);
		settlement_btn = (Button) mView.findViewById(R.id.settlement_btn);
		shoppingcar_lv = (ListView) mView.findViewById(R.id.shoppingcar_lv);
		totalprice_tv02 = (TextView) mView.findViewById(R.id.totalprice_tv02);
		linearLayout_shopcart_empty=(LinearLayout) mView.findViewById(R.id.linearLayout_shopcart_empty);
		relativeLayout04=(RelativeLayout) mView.findViewById(R.id.relativeLayout04);
		// 设置一些初始化的属性
		shoppingcar_lv.setCacheColorHint(Color.TRANSPARENT); // 拖动的时候 背景不变色
		// 设置间距的颜色和高度
		shoppingcar_lv.setDivider(new ColorDrawable(R.color.blue_bg));
		shoppingcar_lv.setDividerHeight(2);
		shoppingcar_lv.setItemsCanFocus(true);			
		shoppingcar_lv.setItemsCanFocus(true);
		
		currentUserId=userService.getUser_id();
		System.out.println("userId:"+currentUserId);				
			// get请求获取购物车list数据
		getShoppingCarList(currentUserId);		

		// 绑定适配器
		adapter = new ShoppingCarBasAdapter(getActivity());
		shoppingcar_lv.setAdapter(adapter);
		// 编辑及保存按钮点击事件
		editAndsave_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String b = editAndsave_btn.getText().toString();
				if (b.equals("编辑")) {
					editAndsave_btn.setText("保存");
					for (ShoppingCar shoppingCar : shoppingCars) {
						shoppingCar.setSelected(true);
					}
				}
				if (b.equals("保存")) {
					editAndsave_btn.setText("编辑");
					for (ShoppingCar shoppingCar : shoppingCars) {
						shoppingCar.setSelected(false);
					}
				}
				dataChange();
			}
		});

		// 跳转订单提交页面
		settlement_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("strTotalPrice",strTotalPrice);
				intent.setClass(getActivity(), CommitOrderActivity.class);
				startActivity(intent);
			}
		});

		return mView;
	}
	
	 void initShoppingCarfoodCount01(int userId){
		 Message message =new Message();
		 message.what=1991;
		 Bundle bundle=new Bundle();
		 System.out.println(userId+"333333");
		 bundle.putInt("userId", userId);
		 message.setData(bundle);
		handler01.sendMessage(message);
	}
	/**
	 * 刷新标题 和 listView的显示
	 */
	public void dataChange() {
		// 通知listView刷新
		adapter.notifyDataSetChanged();

		// for (ShoppingCar shoppingCar : shoppingCars) {
		// //System.out.println(shoppingCar);
		// }
	}

	public void initShoppingCar(Handler handler) {
		Message message = new Message();
		message.what = 50001;
		Bundle bundle = new Bundle();
		// System.out.println(shoppingCarfoodCount + "0000");
		bundle.putInt("shoppingCarfoodCount", shoppingCarfoodCount);
		message.setData(bundle);
		handler.sendMessage(message);
	}

	// 初始化总价&购物车数量
	private void initShoppingCarfoodCount() {
		if (shoppingCars != null) {
			for (int i = 0; i < shoppingCars.size(); i++) {
				shoppingCarfoodCount = shoppingCarfoodCount
						+ shoppingCars.get(i).getFoodCount();
				strTotalPrice = strTotalPrice
						+ shoppingCars.get(i).getFoodCount()
						* Double.valueOf(shoppingCars.get(i).getUnitPrice()
								.substring(1));
//				 System.out.println(strTotalPrice + "AAAAAAAAAAA");
			}
			Message message = new Message();
			message.what = 1989;
			Bundle bundle = new Bundle();
			bundle.putDouble("strTotalPrice", strTotalPrice);
			//更新总价
			handler01.sendMessage(message);
			 System.out.println("shoppingCarfoodCount:" + shoppingCarfoodCount);
			 //更新总数
			 ((FragmentTab)getActivity()).initShoppingCar02();
		}
	}

	public void getShoppingCarList(final int userId) {
		new Thread() {
			public void run() {
				try {
					if(userId==0){
						linearLayout_shopcart_empty.setVisibility(View.VISIBLE);
						return;
					}else if(userId!=0){
					System.out.println("userId:"+userId);
					String strJson;
					String url=initShoppingCar_URL+"?userId="+ userId;
					strJson = HttpUtil.getRequest(url);
					// Thread.sleep(10000);
					 System.out.println("strJson:" + strJson);
					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<ArrayList<ShoppingCar>>() {
					}.getType();
					shoppingCars = gson.fromJson(strJson, type);
					System.out.println("shoppingCars:"+shoppingCars.size());
					if(shoppingCars.size()==0){
						
						handler01.sendEmptyMessage(1992);
					}else if(shoppingCars.size()!=0){
						handler01.sendEmptyMessage(1993);
					
					
					strTotalPrice = 0;// 清空总价
					shoppingCarfoodCount = 0;// 清空数量
					// 初始化购物车数量&总价
					initShoppingCarfoodCount();
//					 System.out.println(shoppingCars + "asdsadsdaasd");
					}
					}	
				} catch (Exception e) {
					e.printStackTrace();
//					Toast.makeText(getActivity(), "服务器异常", 1);
				}
			};
		}.start();
	}

	public class ShoppingCarBasAdapter extends BaseAdapter implements
			OnScrollListener {
		private Context context;
		private ShoppingCar shoppingCar;

		public ShoppingCarBasAdapter(Context context) {
			this.context = context;
			shoppingcar_lv.setOnScrollListener(this);
		}

		@Override
		public int getCount() {

			return shoppingCars.size();
		}

		@Override
		public Object getItem(int position) {

			return shoppingCars.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		public class ViewHolder {
			public ImageView delete_iv;
			public ImageView foodImage_iv;
			public TextView foodInfo_tv;
			public TextView unitPrice_tv;
			public Button decrease_btn;
			public EditText foodCount_et;
			public Button increase_btn;
			public int position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.cart_item, null);
				viewHolder.delete_iv = (ImageView) convertView
						.findViewById(R.id.delete_iv);
				viewHolder.foodImage_iv = (ImageView) convertView
						.findViewById(R.id.foodImage_iv);
				viewHolder.foodInfo_tv = (TextView) convertView
						.findViewById(R.id.foodInfo_tv);
				viewHolder.unitPrice_tv = (TextView) convertView
						.findViewById(R.id.unitPrice_tv);
				viewHolder.decrease_btn = (Button) convertView
						.findViewById(R.id.decrease_btn);
				viewHolder.foodCount_et = (EditText) convertView
						.findViewById(R.id.foodCount_et);
				viewHolder.increase_btn = (Button) convertView
						.findViewById(R.id.increase_btn);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.position = position;
			final ShoppingCar shoppingCar = shoppingCars.get(position);
			String imagePath = shoppingCar.getImagePath();
			viewHolder.foodImage_iv.setImageDrawable(getResources()
					.getDrawable(R.drawable.t3));
			viewHolder.foodInfo_tv.setText(shoppingCar.getFoodInfo());
			viewHolder.unitPrice_tv.setText(shoppingCar.getUnitPrice());
			viewHolder.decrease_btn.setText(shoppingCar.getDecrease());
			viewHolder.foodCount_et.setText(shoppingCar.getFoodCount() + "");
			viewHolder.increase_btn.setText(shoppingCar.getIncrease());
			File dir = new File(imagesDir);
			if (!dir.exists()) {
				dir.getParentFile().mkdirs();
			}
			String imagePath01 = imagesDir + imagePath;
			// System.out.println(imagePath01+"客户端图片路径");
			viewHolder.foodImage_iv.setTag(imagePath01); // 设置标识

			final Handler handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					Bundle bundle = msg.getData();
					switch (msg.what) {
					case UPDATE_IAMGE_RESULT:
						String imagePath01 = bundle.getString("imagePath01");
						String tag = (String) viewHolder.foodImage_iv.getTag();
						if (tag.equals(imagePath01)) {
							Bitmap bitmap = imageUtil
									.loadSdcardItemImage(imagePath01);
							if (null != bitmap) {
								viewHolder.foodImage_iv.setImageBitmap(bitmap);
							}
						}
						break;
					}
				}
			};
			handlerMap.put(position, handler);
			// 删除点击事件
			if (shoppingCar.isSelected()) {
				viewHolder.delete_iv.setVisibility(View.VISIBLE);
				viewHolder.delete_iv
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								int position;
								position = viewHolder.position;
								shoppingCars.remove(position);
								adapter.notifyDataSetChanged();
								int shoppingCarID = shoppingCar
										.getShoppingCarID();
								final int foodCount = Integer
										.valueOf(viewHolder.foodCount_et
												.getText().toString());
//								 final String unitPrice =
//								 viewHolder.unitPrice_tv.getText()
//								 .toString();
								String url = shoppingCarSyncServlet_URL
										+ "?operate=delete&shoppingCarID="
										+ shoppingCarID + "&foodCount="
										+ foodCount;
								System.out.println(url+"1234569");
								shoppingCarfoodCountChange(url);
								handler02 = new Handler() {
									public void handleMessage(Message msg) {
										System.out.println("666666");
										getShoppingCarList(currentUserId);
//										 changeShoppingCarfoodCount("delete",
//										 foodCount, unitPrice);
									};
								};

							}
						});
			} else {
				viewHolder.delete_iv.setVisibility(View.GONE);
			}

			// 数量减少
			viewHolder.decrease_btn
					.setOnClickListener(new View.OnClickListener() {
						int position;

						@Override
						public void onClick(View v) {
							String c = editAndsave_btn.getText().toString();
							if(c.equals("保存")){
								Toast toast=Toast.makeText(getActivity(), "编辑状态不能操作", 1);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();
								return;
							}
//							position = viewHolder.position;
//							System.out.println("position:" + position);
//							int shoppingCarID = shoppingCar.getShoppingCarID();
//							final int foodCount = Integer
//									.valueOf(viewHolder.foodCount_et.getText()
//											.toString());
//							// final String unitPrice =
//							// viewHolder.unitPrice_tv.getText()
//							// .toString();
//							String url = shoppingCarSyncServlet_URL
//									+ "?operate=decrease&shoppingCarID="
//									+ shoppingCarID + "&foodCount=" + foodCount;
//							//数量改变请求
//							shoppingCarfoodCountChange(url);
//							handler02 = new Handler() {
//								public void handleMessage(Message msg) {
//									switch (msg.what) {
//									case 1997:
//										//重新获取list
//										getShoppingCarList(currentUserId);
//										System.out.println("sdasassadsa");
//										viewHolder.foodCount_et
//												.setText(shoppingCar
//														.getFoodCount() + "");
//										// changeShoppingCarfoodCount("decrease",
//										// foodCount, unitPrice);
//										break;
//									}
//									;
//								};
//							};
							position = viewHolder.position;
							System.out.println(position);
							int shoppingCarID = shoppingCar.getShoppingCarID();
							foodCount = Integer.valueOf(viewHolder.foodCount_et
									.getText().toString());
							strUnitPrice = viewHolder.unitPrice_tv.getText()
									.toString();
							if (foodCount == 1) {
								return;
							}
							foodCount--;
							viewHolder.foodCount_et.setText(foodCount + "");
							changeShoppingCarfoodCount("decrease", foodCount, strUnitPrice);
							String url = shoppingCarSyncServlet_URL
									+ "?operate=decrease&shoppingCarID="
									+ shoppingCarID + "&foodCount=" + foodCount;
							System.out.println("----url"+url);
							shoppingCarfoodCountChange(url); 
						}
					});
			// 数量增加
			viewHolder.increase_btn
					.setOnClickListener(new View.OnClickListener() {
						int position;
						
						@Override
						public void onClick(View v) {
							String d = editAndsave_btn.getText().toString();
							if(d.equals("保存")){
								Toast toast=Toast.makeText(getActivity(), "编辑状态不能操作", 1);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();
								return;
							}
//							position = viewHolder.position;
//							System.out.println(position);
							int shoppingCarID = shoppingCar.getShoppingCarID();
//							final int foodCount = Integer
//									.valueOf(viewHolder.foodCount_et.getText()
//											.toString());
//							// final String unitPrice =
//							// viewHolder.unitPrice_tv.getText()
//							// .toString();
//							String url = shoppingCarSyncServlet_URL
//									+ "?operate=increase&shoppingCarID="
//									+ shoppingCarID + "&foodCount=" + foodCount;
//							shoppingCarfoodCountChange(url);
//							getShoppingCarList(currentUserId);
//							handler02 = new Handler() {
//								public void handleMessage(Message msg) {
//									getShoppingCarList(currentUserId);
//									viewHolder.foodCount_et.setText(shoppingCar
//											.getFoodCount() + "");
//									// changeShoppingCarfoodCount("increase",
//									// foodCount, unitPrice);
//								};
//							};
							position = viewHolder.position;
							System.out.println(position);
							foodCount = Integer.valueOf(viewHolder.foodCount_et
									.getText().toString());
							strUnitPrice = viewHolder.unitPrice_tv.getText()
									.toString();
							foodCount++;
							// ShoppingCar.setFoodCount(foodCount);
							viewHolder.foodCount_et.setText(foodCount + "");
							changeShoppingCarfoodCount("increase", foodCount, strUnitPrice);
							String url = shoppingCarSyncServlet_URL
									+ "?operate=increase&shoppingCarID="
									+ shoppingCarID + "&foodCount=" + foodCount;
							System.out.println("++++url"+url);
							shoppingCarfoodCountChange(url);
						}
					});

			viewHolder.foodCount_et
					.setOnFocusChangeListener(new View.OnFocusChangeListener() {
						@Override
						public void onFocusChange(final View view,
								boolean hasFocus) {
							view.post(new Runnable() {
								@Override
								public void run() {
//									view.requestFocus();
//									view.requestFocusFromTouch();
//									Message message=new Message();
//									message.what=1998;
//									handler02.sendMessage(message);
								}
							});
						}
					});

			viewHolder.foodCount_et.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(final CharSequence s, int start,
						int before, int count) {
//					handler02=new Handler(){
//						@Override
//						public void handleMessage(Message msg) {
//							switch (msg.what) {
//							case 1998:
//								if (viewHolder.foodCount_et != null) {
//									viewHolder.foodCount_et.setText(s);
//								}
//
//								break;								
//							}
//						}
//					};
					
				}
				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					
				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

			return convertView;
		}

		// 请求返回shoppingCar
		private void shoppingCarfoodCountChange(final String url) {
			new Thread() {
				public void run() {
					String strJson;
					try {
						strJson = HttpUtil.getRequest(url);
						System.out.println(strJson);
						 //把json字符串 转换成 java对象
						Gson gson = new Gson();
						java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<ShoppingCar>() {
						}.getType();
						shoppingCar = gson.fromJson(strJson, type);
						if (shoppingCar != null) {
							Message message = new Message();
							message.what = 1997;
							handler02.sendMessage(message);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			}.start();
		}

		// 只加载一屏的图片： 第一个item的位置
		private int mfirstVisibleItem;
		// 只加载一屏的图片： 一屏中所有的item的总个数
		private int mVisibleItemCount;

		private boolean isFirstEnter = true;

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// 当gridView静止下来的时候才去加载图片， 滑动的时候 就不加载图片
			if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
				showImages(mfirstVisibleItem, mVisibleItemCount);
			}

		}

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
							String imagepath = shoppingCars.get(position)
									.getImagePath();
							String imageName = imagepath;
							String imagePath01 = imagesDir + imageName;
							// System.out.println(imagePath01+"22222222");
							File imageFile = new File(imagePath01);
							if (!imageFile.exists()) {
								String url = DOWN_IAMGE_URL + "?imageName="
										+ imageName;
								// 下载服务器的图片
								InputStream inputStream = HttpUtil
										.getDownFile(url);
								// 保存到本地
								FileOperateTool
										.saveFileByFileInputAndOutputStream(
												inputStream, imagePath01);
							}
							// 下载完以后，加载一遍，放入缓存
							imageUtil.loadSdcardItemImage(imagePath01);

							// 与UI主线程通讯
							Message message = new Message();
							message.what = UPDATE_IAMGE_RESULT;
							Bundle bundle = new Bundle();
							bundle.putString("imagePath01", imagePath01);
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

	protected void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		builder.create().show();
	}
	

	 //购物车Item数量改变&购物蓝Item删除
	 protected void changeShoppingCarfoodCount(String opreate, int foodCount,
	 String unitPrice) {
	 if (opreate.equals("decrease")) {
	 shoppingCarfoodCount = shoppingCarfoodCount -1;
	 strTotalPrice = strTotalPrice
	 - Double.valueOf(unitPrice.substring(1));
	 System.out.println("changeShoppingCarfoodCount方法被调用");
	 } else if (opreate.equals("increase")) {
	 shoppingCarfoodCount = shoppingCarfoodCount +1;
	 strTotalPrice = strTotalPrice
	 + Double.valueOf(unitPrice.substring(1));
	 } else if (opreate.equals("delete")) {
	 System.out.println(foodCount+"3254654564");
	 shoppingCarfoodCount = shoppingCarfoodCount - foodCount;
	 strTotalPrice = strTotalPrice - foodCount
	 * Double.valueOf(unitPrice.substring(1));
	 }
	 // 总价改变
	 totalprice_tv02.setText("￥" + decimalFormat.format(strTotalPrice));
	 // 购物车数量改变
	 ((FragmentTab) getActivity()).initShoppingCar02();
//	for (ShoppingCar shoppingCar : shoppingCars) {
//		System.out.println(shoppingCar+"AAA");
//	}
	 }

}
