package com.com.activity;


	import java.io.File;
	import java.io.InputStream;
	import java.lang.reflect.Type;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.HashMap;
	import java.util.concurrent.ExecutorService;
	import java.util.concurrent.Executors;

	import android.content.Intent;
	import android.graphics.Bitmap;
	import android.os.Bundle;
	import android.os.Handler;
	import android.os.Message;
	import android.support.v4.app.Fragment;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.view.ViewGroup;
	import android.widget.AbsListView;
	import android.widget.AbsListView.OnScrollListener;
	import android.widget.AdapterView;
	import android.widget.AdapterView.OnItemClickListener;
	import android.widget.BaseAdapter;
	import android.widget.ImageButton;
	import android.widget.ImageView;
	import android.widget.TextView;
	import android.widget.Toast;

	import com.com.FragmentTab;
	import com.com.R;
import com.com.Welcome;
	import com.com.bean.ShoppingCate;
	import com.com.bean.ShoppingCateImage;
import com.com.service.UserService;
	import com.com.util.BaseUrl;
	import com.com.util.FileOperateTool;
	import com.com.util.HttpUtil;
	import com.com.util.ImageUtil;
	import com.com.util.PathUtil;
	import com.com.widget.XListView;
	import com.com.widget.XListView.IXListViewListener;
	import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

	public class DefaultFragment extends Fragment implements OnItemClickListener, IXListViewListener {
		private View mView;
		private XListView xListView;
		private ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		private ShoppingCateImage shoppingCateImage;
		private CategoryFlavorFragmentActivity fragmentTab;
		private FragmentTab fragmentvv;
		private ProductAdapter productAdapter;
		private CartFragment cartFragment;
		private UserService userService;
//		public static final String BASE_URL = "http://192.168.1.193/foodshopping-servers/";
		private int maxRow = 1;  
		private int minRow = 1;
		private int countRow = 5;
		private int categoryflavor_id=CategoryFlavorFragmentActivity.getCategoryflavor_id();
		String url = null;
		private ImageUtil imageUtil;
		
		public static final int RESULT = 1001;
		public static final int UPDATE_IAMGE_RESULT = 10010;
		public static final int ADD_TO_BASKET = 10011;
		public static final int ADD_TO_BASKET_FAILED = 10012;
		// 异步加载图片的线程池
		private ExecutorService executorService;
		// 保存拖动过程中 的handler对象
		private HashMap<Integer, Handler> handlerMap = new HashMap<Integer, Handler>();
		private HashMap<Integer, String> imageNameHash = new HashMap<Integer, String>();
		private HashMap<ShoppingCate,ShoppingCateImage> cateImageMap = new HashMap<ShoppingCate, ShoppingCateImage>();
		private Handler addBasketHandler;
		private int number;
		
		public DefaultFragment(Handler handler,int number) {
			this.addBasketHandler = handler;
			this.number = number;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			mView = inflater.inflate(R.layout.fragment_guessing, container, false);
			init();
			return mView;
		}


		public void init() {
			// TODO Auto-generated method stub
			fragmentTab = (CategoryFlavorFragmentActivity) getActivity();
			executorService = Executors.newFixedThreadPool(6); //初始化线程池
			imageUtil =  new ImageUtil(fragmentTab);
			userService = Welcome.userService;
			xListView = (XListView)mView.findViewById(R.id.listview_productList);
			xListView.setPullLoadEnable(true);
			productAdapter = new ProductAdapter();
			xListView.setAdapter(productAdapter);
			xListView.setOnItemClickListener(this);
			xListView.setXListViewListener(this);
			
			new GetDataThread(1,false).start();  //连接网络线程
			
		}
		
		class GetDataThread extends Thread {
			private boolean isRefresh;
			
			public GetDataThread(int minRow,boolean isRefresh) {
				DefaultFragment.this.minRow = minRow;
				maxRow = minRow + countRow -1;
				this.isRefresh = isRefresh;
			}
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					System.out.println("默认："+categoryflavor_id);
					
					if (number == 1) {
						url = BaseUrl.BASE_URL + "/servlet/DefaultServlet?categoryflavor_id=" + categoryflavor_id ;
					} else if (number == 2){
						url = BaseUrl.BASE_URL + "/servlet/DefaultServlet2?categoryflavor_id=" + categoryflavor_id ;
					}
					
					System.out.println("走哪个url啊"+ url);
					String strJson = HttpUtil.getRequest(url);
					Gson gson = new Gson();
					Type type = new TypeToken<ArrayList<ShoppingCate>>(){}.getType();
					ArrayList<ShoppingCate> cates = gson.fromJson(strJson, type);
					
					Collections.shuffle(cates);
					
					if (isRefresh) { 
						//如果为下拉刷新，先清空
						shoppingCates.clear();
					}
					if (shoppingCates.size() !=0) {
						shoppingCates.clear();
					}
					shoppingCates.addAll(cates);
//					for (ShoppingCate s : shoppingCates) {
//						System.out.println(s);
//					}
					handler.sendEmptyMessage(RESULT);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		private Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == RESULT) {
					productAdapter.notifyDataSetChanged();
					xListView.setSelection(minRow-1);
					xListView.stopRefresh();
					xListView.stopLoadMore();
					xListView.setRefreshTime("刚刚");
					productAdapter.showProductImage(0, 4);
				} else if(msg.what == ADD_TO_BASKET) {
					Toast.makeText(fragmentTab, "加入购物车成功", Toast.LENGTH_SHORT).show();
				}else if(msg.what == ADD_TO_BASKET){
					Toast.makeText(fragmentTab, "服务器异常", Toast.LENGTH_SHORT).show();
				}
				
			};
		};
		
		
		class ProductAdapter extends BaseAdapter implements OnScrollListener {
			private boolean isFirstEnter = true;  //第一次进入
			public void setFirstEnter(boolean isFirstEnter) {
				this.isFirstEnter = isFirstEnter;
			}

			private int mfirstVisibleItem;  //用于listview滑动的时候记录当前屏幕的第一条
			private int mVisibleItemCount;  //用于listview滑动的时候记录当前屏幕的总数
			
			
			public ProductAdapter() {
				xListView.setOnScrollListener(this);
			}
			
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return shoppingCates.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return shoppingCates.get(position);
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			class ViewHolder {
				ImageView imageViewPic;
				TextView textViewName;
				TextView textViewDesc;
				TextView textViewPrice;
				ImageButton button_addToCart;
			}

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				final ViewHolder viewHolder;
				if (convertView == null) {
					convertView = LayoutInflater.from(fragmentTab).inflate(R.layout.item_product_list, null);
					viewHolder = new ViewHolder();
					viewHolder.imageViewPic = (ImageView)convertView.findViewById(R.id.imageview_product_list);
					viewHolder.textViewName = (TextView)convertView.findViewById(R.id.textview_product_name);
					viewHolder.textViewDesc = (TextView)convertView.findViewById(R.id.textview_product_desc);
					viewHolder.textViewPrice = (TextView)convertView.findViewById(R.id.textview_product_price);
					viewHolder.button_addToCart = (ImageButton)convertView.findViewById(R.id.button_addToCart);
					convertView.setTag(viewHolder);
				}else {
					viewHolder = (ViewHolder) convertView.getTag();
				}
				
				final ShoppingCate shoppingCate = shoppingCates.get(position);
				
				viewHolder.imageViewPic.setImageDrawable(getResources().getDrawable(R.drawable.t3));
				viewHolder.textViewName.setText(shoppingCate.getCate_name());
				viewHolder.textViewDesc.setText(shoppingCate.getCate_description());
				viewHolder.textViewPrice.setText("￥"+shoppingCate.getCate_price());
				viewHolder.imageViewPic.setTag(shoppingCate.getCate_image_id());
				viewHolder.button_addToCart.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new Thread() {
							public void run() {
								try {
									//获取用户id
									int user_id = userService.getUser_id();
									//获取商品名称
									String shopping_basket_name = shoppingCate.getCate_name();
									//商品价格
									String shopping_basket_price = "￥"+shoppingCate.getCate_price();
									//商品图片名称
									String shopping_basket_image = imageNameHash.get(position);

									getActivity().sendBroadcast(new Intent("ADD_TO_BASKET"));
									addBasketHandler.sendEmptyMessage(50002);
									if (user_id == 0) {
										
										//如果用户未登录，则加入本地sqlite ，handle更新购物车图标的数量
										fragmentvv.dbManager.insertBasket(shopping_basket_name,shopping_basket_price,shopping_basket_image);
										
										
									} else{
										//登录 则加入远程数据库 更新购物车的数量
										String url = BaseUrl.BASE_URL + "/AddToBasketServlet?cate_name="+shopping_basket_name+"&cate_price="+shoppingCate.getCate_price()+"&user_id="+userService.getUser_id();
										HttpUtil.getRequest(url);
										cartFragment.getShoppingCarList(user_id);
										
									}
									//显示toast
									handler.sendEmptyMessage(ADD_TO_BASKET);
								} catch (Exception e) {
									// TODO: handle exception
									handler.sendEmptyMessage(ADD_TO_BASKET_FAILED);
									e.printStackTrace();
								}
								
							};
						}.start();
						
					}
				});
				final Handler handler = new Handler(){
					public void handleMessage(android.os.Message msg) {
						Bundle bundle = msg.getData();
						switch (msg.what) {
						case UPDATE_IAMGE_RESULT:
							String imagePath = bundle.getString("imagePath");
							int Cate_image_id = bundle.getInt("Cate_image_id");
							int tag = (Integer)viewHolder.imageViewPic.getTag();
							if(tag == Cate_image_id) {
								Bitmap bitmap = imageUtil.loadSdcardItemImage(imagePath);
								if(null != bitmap) {
									viewHolder.imageViewPic.setImageBitmap(bitmap);
								}
							}
							break;
						}
					}
				};
				handlerMap.put(position, handler);
				
				return convertView;
			}

			

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				mfirstVisibleItem = firstVisibleItem;
				mVisibleItemCount = visibleItemCount;
				System.out.println("可见item总数"+mVisibleItemCount+"第一次进入："+isFirstEnter);
				if(isFirstEnter && mVisibleItemCount>2) {
					// 显示一屏之内的用户图片
					showProductImage(mfirstVisibleItem, mVisibleItemCount);
					isFirstEnter = false;
				}
			}

			

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
					showProductImage(mfirstVisibleItem, mVisibleItemCount);
					System.out.println("空闲了");
				}
			}
			
			public void showProductImage(int firstVisibleItem,
					int visibleItemCount) {
				// TODO Auto-generated method stub
				for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
					executorService.execute(downloadImage(i));
				}
			}
			
			public Runnable downloadImage(final int position) {
				return new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							
								//获取食品图片名称
								ShoppingCate shoppingCate = shoppingCates.get(position);
								String url = BaseUrl.BASE_URL + "/ProductImagePathServlet?cate_image_id="+shoppingCate.getCate_image_id();
								String strJson = HttpUtil.getRequest(url);
								Gson gson = new Gson();
								Type type = new TypeToken<ShoppingCateImage>(){}.getType();
								shoppingCateImage = gson.fromJson(strJson, type);
								
								initCateImageMap(shoppingCate,shoppingCateImage);
								
								String imageName = shoppingCateImage.getCate_image_path01();
								
								String imagesDir = PathUtil.getExternalStorageDirectory(fragmentvv) + "/laiyifen/images/";
								String imagePath = imagesDir + imageName;
								File imageFile = new File(imagePath);
								if(!imageFile.exists()) {
									String downLoadImageUrl = BaseUrl.BASE_URL + "/DownLoadImageServlet?imageName="+imageName;
									InputStream inputStream = HttpUtil.getDownFile(downLoadImageUrl);
									// 保存到本地
									FileOperateTool.saveFileByFileInputAndOutputStream(inputStream, imagePath);
								}
								// 下载完以后，加载一遍，放入缓存
								imageUtil.loadSdcardItemImage(imagePath);
								
								// 与UI主线程通讯
								Message message = new Message();
								 message.what = UPDATE_IAMGE_RESULT;
								 Bundle bundle = new Bundle();
								 bundle.putString("imagePath", imagePath);
								 bundle.putInt("Cate_image_id", shoppingCateImage.getCate_image_id());
								 message.setData(bundle);
								 handlerMap.get(position).sendMessage(message);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
			}


			public void initCateImageMap(ShoppingCate shoppingCate,
					ShoppingCateImage shoppingCateImage) {
				// TODO Auto-generated method stub
				if (!cateImageMap.containsKey(shoppingCate)) {
					cateImageMap.put(shoppingCate, shoppingCateImage);
				}
			}
			
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			ShoppingCate shoppingCate = shoppingCates.get((int) id);
			
			url = BaseUrl.BASE_URL + "/servlet/FootPrintInsertServlet?cate_id=" + shoppingCate.getCate_id() ;
			new Thread(){
				public void run() {
					try{
						String strJson = HttpUtil.getRequest(url);
					}catch (Exception e) {
						// TODO: handle exception
						e.getStackTrace();
					}
				};
			}.start();
			Intent intent = new Intent(fragmentTab, ProductDetailActivity.class);
			intent.putExtra("cate_id", shoppingCate.getCate_id());
			startActivity(intent);
		}

		//加载更多
		
		@Override
		public void onLoadMore() {
			// TODO Auto-generated method stub
			int minRow = maxRow + 1;
			System.out.println("上拉加载更多起始行："+ minRow);
			new GetDataThread(minRow,false).start();
		}

		//下拉刷新
		@Override
		public void onRefresh() {
			// TODO Auto-generated method stub
			productAdapter.setFirstEnter(true);
			new GetDataThread(1,true).start();
		}
		
		
	}

