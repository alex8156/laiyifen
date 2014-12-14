package com.com.activity;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.com.FragmentTab;
import com.com.R;
import com.com.util.BaseUrl;
import com.com.util.FileOperateTool;
import com.com.util.HttpUtil;
import com.com.util.ImageUtil;
import com.com.util.PathUtil;
import com.com.widget.FlavorListView;
import com.com.widget.FlavorListView.IXListViewListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weibin.javabean.ShoppingCategory;
import com.weibin.javabean.ShoppingCategoryImage;
import com.weibin.javabean.ShoppingFlavor;

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
import android.widget.ImageView;
import android.widget.TextView;


public class CategoryFragment02 extends Fragment implements OnItemClickListener,IXListViewListener{
	
	private int maxRow = 0;
	private int CountROw = 5;
	private View mView;
	private ShoppingCategoryImage shoppingCategoryImage;
	private FlavorListView flavorListView;
	private FragmentTab fragmentTab;
	private ArrayList<ShoppingFlavor> shoppingFlavors = new ArrayList<ShoppingFlavor>();
	public static final String BASEURL = BaseUrl.BASE_URL;
	private FlavorAdapter flavorAdapter;
	private ImageUtil imageUtil;
	private int images[];
	public static final int RESULT = 1001;
	public static final int UPDATE_IAMGE_RESULT = 10010;
	
	//异步加载图片线程
	private ExecutorService executorService;
	//保存拖动过程中的handler对象
	private HashMap<Integer, Handler> handlerMap = new HashMap<Integer, Handler>();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.categoryfragment02_flavor, container,false);
		
		init();
		
		return mView;
	}
	private void init() {
		fragmentTab = (FragmentTab) getActivity();
		//初始化线程池
		executorService = Executors.newFixedThreadPool(6);
		imageUtil = new ImageUtil(fragmentTab);
		
		flavorListView = (FlavorListView)mView.findViewById(R.id.flavor_listview);
		flavorListView.setPullLoadEnable(true);
		flavorAdapter = new FlavorAdapter();
		flavorListView.setAdapter(flavorAdapter);
		flavorListView.setOnItemClickListener(this);
		flavorListView.setFlavorListViewListener(this);
		//本地保存的口味的图片7张
		images = new int[] {R.drawable.xianwei,R.drawable.yuanwei,R.drawable.xiangwei,
							R.drawable.duowei,R.drawable.tianwei,R.drawable.lawei,R.drawable.suanwei};
		new GetDataThread(1,false).start();//连接网络线程
		

		flavorListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ShoppingFlavor shoppingFlavor = shoppingFlavors.get((int)id);
				String flavor_category_name = shoppingFlavor.getFlavor_category_name();
				int categoryflavor_id = shoppingFlavor.getFlavor_category_id();
				System.out.println(flavor_category_name);
				Intent intent  = new Intent();
				intent.putExtra("id", categoryflavor_id);
				intent.putExtra("name",flavor_category_name );
				intent.putExtra("CategoryFragment",2);
				intent.setClass(getActivity(),CategoryFlavorFragmentActivity.class);
				startActivity(intent); 
				}
			});
		
	}
	
	
	//-0---------------------------------------------
	
	class GetDataThread extends Thread{
		private int minRow;
		private boolean isRefresh;
		
		public GetDataThread(int minRow,boolean isRefresh){
			this.minRow = minRow;
			maxRow = minRow + CountROw -1;
			this.isRefresh = isRefresh;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			try {
				String url = BaseUrl.BASE_URL + "/servlet/FlavorListServt";
				String strJson = HttpUtil.getRequest(url);
				Gson gson = new Gson();
				Type type = new TypeToken<ArrayList<ShoppingFlavor>>(){}.getType();
				ArrayList<ShoppingFlavor> categories = gson.fromJson(strJson, type);
//				Collections.shuffle(categories);
				if (isRefresh) { 
					//如果为下拉刷新，先清空
					shoppingFlavors.clear();
				}
				if (categories.size() !=0) {
					shoppingFlavors.clear();
				}
				shoppingFlavors.addAll(categories);
				for (ShoppingFlavor s : shoppingFlavors) {
					System.out.println(s);
				}
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
					flavorAdapter.notifyDataSetChanged();
					flavorListView.stopRefresh();
					flavorListView.stopLoadMore();
					flavorListView.setRefreshTime("刚刚");
				}
				
			};
		};
		
	class FlavorAdapter extends BaseAdapter implements OnClickListener,OnScrollListener{

		private boolean isFirstEnter = true;//第一次进入
		private int mfirstVisibleItem;//用于listView滑动时记录当前屏幕的第一条
		private int mVisibleItemCount;//用于lisView滑动时记录当前屏幕的总数
		private ViewHolder viewHolder;
		
		public FlavorAdapter(){
			flavorListView.setOnScrollListener(this);
		}
		@Override
		public int getCount() {
			return shoppingFlavors.size();
		}
		@Override
		public Object getItem(int position) {
			return shoppingFlavors.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final Handler handler = new Handler(){
				public void handleMessage(android.os.Message msg) {
					Bundle bundle = msg.getData();
					switch (msg.what) {
					case UPDATE_IAMGE_RESULT:
						String imagePath = bundle.getString("imagePath");
						int tag = (Integer)viewHolder.imageViewPic.getTag();
//						if(tag == position) {
							Bitmap bitmap = imageUtil.loadSdcardItemImage(imagePath);
							if(null != bitmap) {
								viewHolder.imageViewPic.setImageBitmap(bitmap);
							}
//						}
						break;
					}
				}
			};
			ShoppingFlavor shoppingFlavor = shoppingFlavors.get(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(fragmentTab).inflate(R.layout.category_item, null);
				viewHolder = new ViewHolder();
				viewHolder.imageViewPic = (ImageView)convertView.findViewById(R.id.categoryImage);
				viewHolder.textViewName = (TextView)convertView.findViewById(R.id.mainTitle);
				viewHolder.textViewDesc = (TextView)convertView.findViewById(R.id.subTitle);
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.imageViewPic.setImageDrawable(getResources().getDrawable(images[position]));
			viewHolder.textViewName.setText(shoppingFlavor.getFlavor_category_name());
			viewHolder.textViewDesc.setText(shoppingFlavor.getFlavor_category_description());
			viewHolder.imageViewPic.setTag(position);
			handlerMap.put(position, handler);
			
			
			return convertView;
		}
		
//------------------------

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			mfirstVisibleItem = firstVisibleItem;
			mVisibleItemCount = visibleItemCount;
			if(isFirstEnter && visibleItemCount > 2){
				//显示一屏之内的用户图片
				showCategoryImage(mfirstVisibleItem,mVisibleItemCount);
				isFirstEnter = false;
			}
			
		}
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
				showCategoryImage(mfirstVisibleItem,mVisibleItemCount);
			}
		}
		
		public void showCategoryImage(int firstVisibleItem,
				int visibleItemCount) {
			// TODO Auto-generated method stub
			for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
				executorService.execute(downloadImage(i));
			}
		}
		
	
	private Runnable downloadImage(final int position) {
		return new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					
						
						//获取食品图片名称
						 
						String url = BASEURL + "ProductImagePathServlet?cate_image_id="+shoppingFlavors.get(position).getFlavor_category_image_id();
						String strJson = HttpUtil.getRequest(url);
						Gson gson = new Gson();
						Type type = new TypeToken<ShoppingCategoryImage>(){}.getType();
						shoppingCategoryImage = gson.fromJson(strJson, type);
						
						String imageName = shoppingCategoryImage.getCategory_image_path01();
						
						String imagesDir = PathUtil.getExternalStorageDirectory(fragmentTab) + "/laiyifen/images/";
						String imagePath = imagesDir + imageName;
						File imageFile = new File(imagePath);
						if(!imageFile.exists()) {
							String downLoadImageUrl = BASEURL + "DownLoadImageServlet?imageName="+imageName;
							InputStream inputStream = HttpUtil.getDownloadImage(downLoadImageUrl);
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
						 bundle.putInt("position", position);
						 message.setData(bundle);
						 handlerMap.get(position).sendMessage(message);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
}


	class ViewHolder{
		ImageView imageViewPic;
		TextView textViewName,textViewDesc;
	}
	

	//下拉刷新
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new GetDataThread(1, true).start();
	}
	//加载更多
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		int minRow = maxRow + 1;
		System.out.println("上拉加载更多起始行：" + minRow);
		new GetDataThread(minRow, false).start();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
}
	


