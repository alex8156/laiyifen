package com.com.mainfragment.view;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.com.R;
import com.com.activity.ProductDetailActivity;
import com.com.bean.ShoppingCate;
import com.com.bean.ShoppingCateImage;
import com.com.util.BaseUrl;
import com.com.util.FileOperateTool;
import com.com.util.HttpUtil;
import com.com.util.ImageUtil;
import com.com.util.PathUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GroupPurchaseActivity extends Activity {
	
	public static final String BASE_URL =BaseUrl.BASE_URL;
	public static final String DOWN_IAMGE_URL = BASE_URL + "/downloadServlet05";
	public static final String initCateList_URL = BASE_URL
			+ "/queryCateServlet";
	public static final String initCateImage_URL = BASE_URL
			+ "/ProductImagePathServlet";
	private final String imagesDir = PathUtil
			.getExternalStorageDirectory(GroupPurchaseActivity.this) + "/serverImages/";
	private static final int UPDATE_IAMGE=1001;
	private ImageUtil imageUtil = new ImageUtil(this); 
	private ShoppingCateImage shoppingCateImage;
	ImageView back;
	private ListView listView;
	private ArrayList<ShoppingCate> cateList=new ArrayList<ShoppingCate>();
	
	// 保存拖动过程中 的handler对象
	private HashMap<Integer, Handler> handlerMap = new HashMap<Integer, Handler>();		
		// 异步加载图片的线程池
	private ExecutorService loadImageThreadTool;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grouppurchase);
		back=(ImageView)findViewById(R.id.back);
		listView=(ListView)findViewById(R.id.listView);
		loadImageThreadTool = Executors.newFixedThreadPool(6); //初始化线程池
		initCateData();
		listView.setCacheColorHint(Color.TRANSPARENT); // 拖动的时候 背景不变色
		GroupPurchaseAdapter adapter=new GroupPurchaseAdapter(GroupPurchaseActivity.this);
		listView.setAdapter(adapter);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GroupPurchaseActivity.this.finish();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(GroupPurchaseActivity.this, ProductDetailActivity.class);
				intent.putExtra("cate_id", cateList.get(position).getCate_id());
				startActivity(intent);
			}
		});
	}
	private void initCateData() {
		new Thread(){
			public void run() {
				Gson gson=new Gson();
				Type type=new TypeToken<ArrayList<ShoppingCate>>(){}.getType();
				String strJson="";
				try {
					strJson=HttpUtil.getRequest(initCateList_URL);
					cateList.clear();
					cateList=gson.fromJson(strJson, type);
					System.out.println("查询商品："+strJson);
					System.out.println("商品数量："+cateList.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	class GroupPurchaseAdapter extends BaseAdapter implements OnScrollListener{
		Context context;	
		public GroupPurchaseAdapter(Context context) {
			this.context=context;
			listView.setOnScrollListener(this);
		}
		@Override
		public int getCount() {
			return cateList.size();
		}
		@Override
		public Object getItem(int position) {
			return cateList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		class ViewHolder{
			ImageView groupStatue;
			TextView groupPeople;
			ImageView prductImage;
			TextView qgTime;
			TextView productName;
			TextView qgPrice;
			TextView productprice;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder viewHolder;
			if (convertView==null) {
				convertView=LayoutInflater.from(context).inflate(R.layout.grouppurchase_item, null);
				viewHolder=new ViewHolder();
				viewHolder.groupStatue=(ImageView)convertView.findViewById(R.id.groupStatue);
				viewHolder.groupPeople=(TextView)convertView.findViewById(R.id.groupPeople);
				viewHolder.prductImage=(ImageView)convertView.findViewById(R.id.prductImage);
				viewHolder.qgTime=(TextView)convertView.findViewById(R.id.qgTime);
				viewHolder.productName=(TextView)convertView.findViewById(R.id.productName);
				viewHolder.qgPrice=(TextView)convertView.findViewById(R.id.qgPrice);
				viewHolder.productprice=(TextView)convertView.findViewById(R.id.productprice);
				convertView.setTag(viewHolder);
			}else {
				viewHolder=(ViewHolder)convertView.getTag();
			}
			final ShoppingCate shoppingCate =cateList.get(position);
			viewHolder.groupStatue.setBackground(getResources().getDrawable(R.drawable.purchase_ok));
			viewHolder.groupPeople.setText("已参团"+(position+1)+"人/"+cateList.size()+"人成团");
			viewHolder.qgTime.setText((position+1)+"0天后结束");
			viewHolder.productName.setText(shoppingCate.getCate_name());
			viewHolder.qgPrice.setText("￥"+String.valueOf(shoppingCate.getCate_price()));
			viewHolder.productprice.setText("￥"+String.valueOf(shoppingCate.getCate_oldprice()));
			viewHolder.prductImage.setTag(shoppingCate.getCate_image_id());
			final Handler handler = new Handler(){
				public void handleMessage(android.os.Message msg) {
					Bundle bundle = msg.getData();
					switch (msg.what) {
					case UPDATE_IAMGE:
						String imagePath = bundle.getString("imagePath");
						int Cate_image_id = bundle.getInt("Cate_image_id");
						int tag = (Integer)viewHolder.prductImage.getTag();
						if(tag == Cate_image_id) {
							Bitmap bitmap = imageUtil.loadSdcardItemImage(imagePath);
							if(null != bitmap) {
								viewHolder.prductImage.setImageBitmap(bitmap);
							}
						}
						break;
					}
				}
			};
			handlerMap.put(position, handler);
			return convertView;
		}
		private int mfirstVisibleItem;  //用于listview滑动的时候记录当前屏幕的第一条
		private int mVisibleItemCount;  //用于listview滑动的时候记录当前屏幕的总数
		private boolean isFirstEnter = true;  //第一次进入
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
				showImages(mfirstVisibleItem, mVisibleItemCount);
			}
		}
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			mfirstVisibleItem = firstVisibleItem;
			mVisibleItemCount = visibleItemCount;
			if(isFirstEnter && mVisibleItemCount>0) {
				// 显示一屏之内的用户图片
				showImages(mfirstVisibleItem, mVisibleItemCount);
				isFirstEnter = false;
			}
		}
		/**
		 * 显示图片
		 * @param mfirstVisibleItem
		 * @param mVisibleItemCount
		 */
		private void showImages(int mfirstVisibleItem, int mVisibleItemCount) {
			for (int i = mfirstVisibleItem; i < mfirstVisibleItem + mVisibleItemCount; i++) {
				// 循环 显示 一屏之内的图片
				loadImageThreadTool.execute(new LoadImageThread().loadImage(i));
			}
		}
		/**
		 * 加载图片的线程，加载完之后 与 UI主线程 通讯，设置图片
		 * @author panzhipeng
		 */
		public class LoadImageThread {
			public Runnable  loadImage(final int position) {
				Thread thread = new Thread(){
					public void run() {
						try {
							//获取食品图片名称
							ShoppingCate shoppingCate = cateList.get(position);
							String url = BASE_URL + "/ProductImagePathServlet?cate_image_id="+shoppingCate.getCate_image_id();
							String strJson = HttpUtil.getRequest(url);
							Gson gson = new Gson();
							Type type = new TypeToken<ShoppingCateImage>(){}.getType();
							shoppingCateImage = gson.fromJson(strJson, type);
							
							String imageName = shoppingCateImage.getCate_image_path01();
							
							String imagePath = imagesDir + imageName;
							File imageFile = new File(imagePath);
							if(!imageFile.exists()) {
								String downLoadImageUrl = BASE_URL + "/DownLoadImageServlet?imageName="+imageName;
								InputStream inputStream = HttpUtil.getDownFile(downLoadImageUrl);
								// 保存到本地
								FileOperateTool.saveFileByFileInputAndOutputStream(inputStream, imagePath);
							}
							// 下载完以后，加载一遍，放入缓存
							imageUtil.loadSdcardItemImage(imagePath);
							
							// 与UI主线程通讯
							 Message message = new Message();
							 message.what = UPDATE_IAMGE;
							 Bundle bundle = new Bundle();
							 bundle.putString("imagePath", imagePath);
							 bundle.putInt("Cate_image_id", shoppingCateImage.getCate_image_id());
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
