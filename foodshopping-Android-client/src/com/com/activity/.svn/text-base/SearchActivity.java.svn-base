package com.com.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.com.FragmentTab;
import com.com.R;
import com.com.bean.ShoppingCate;
import com.com.service.UserService;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.com.util.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchActivity extends Activity implements OnClickListener {
	public static final String BASE_URL =BaseUrl.BASE_URL;
	public static final String initSearchList_URL = BASE_URL
			+ "/SearchShoppingCateServlet";
	public static final String initCateList_URL = BASE_URL
			+ "/queryCateServlet";
	public static final int UPDATE_IAMGE_RESULT = 1001;
	
	private TextView search,searchTag,searchTitle;
	private ImageView searchRow,delete;
	private EditText searchText;
	private ListView listView;
	private FragmentTab fragmentTab;
	private ImageUtil imageUtil;
	
	ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
	ArrayList<ShoppingCate> cateList=new ArrayList<ShoppingCate>();
	SearchAdapter adapter=null;
	// 保存拖动过程中 的handler对象
	private HashMap<Integer, Handler> handlerMap = new HashMap<Integer, Handler>();
	private UserService userService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		initView();
		initData();
	}
	private void initData() {
		new Thread(){
			public void run() {
				Gson gson=new Gson();
				Type type=new TypeToken<ArrayList<ShoppingCate>>(){}.getType();
				String strJson="";
				try {
					strJson=HttpUtil.getRequest(initCateList_URL);
					cateList.clear();
					cateList=gson.fromJson(strJson, type);
//					likeShoppingCate(cateList);
//					shoppingCates.clear();
//					shoppingCates.addAll(cateList);
					System.out.println("热门商品："+strJson);
					System.out.println("热门商品数量："+cateList.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	/**
	 * 排序
	 * @param cateList
	 */
	protected void likeShoppingCate(ArrayList<ShoppingCate> cateList) {
		shoppingCates.clear();
		List<Integer> list = new ArrayList<Integer>();
		for (ShoppingCate shoppingCate : cateList) {
			list.add(shoppingCate.getCate_sale_count());
		}
		// 使用升序排列,涉及到 内部类, Comparator是一个比较器， 匿名内部类
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int i1 = o1.intValue();
				int i2 = o2.intValue();
				if(i1 < i2){
					return -1;
				}
				if(i1 > i2) {
					return 1;
				}
				return 0;
			}
		});
		
		
	}
	private void initView() {
		search=(TextView)findViewById(R.id.search);
		searchTag=(TextView)findViewById(R.id.searchTag);
		searchTitle=(TextView)findViewById(R.id.searchTitle);
		searchRow=(ImageView)findViewById(R.id.searchRow);
		delete=(ImageView)findViewById(R.id.delete);
		searchText=(EditText)findViewById(R.id.searchText);
		listView=(ListView)findViewById(R.id.listView);
		search.setOnClickListener(this);
		searchRow.setOnClickListener(this);
		searchTitle.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search:
			if (searchText.getText().toString()!=null) {
				searchShoppingCate(searchText.getText().toString());
				System.out.println(searchText.getText().toString());
			} else {
				Toast.makeText(SearchActivity.this, "请输入美食名称后再搜索", 500).show();
			}
			break;
		case R.id.searchRow:
			
			break;
		case R.id.searchTitle:
			adapter=new SearchAdapter(SearchActivity.this,cateList);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent=new  Intent(SearchActivity.this,ProductDetailActivity.class);
					intent.putExtra("cate_id", cateList.get(position).getCate_id());
					startActivity(intent);
				}
			});
			break;
		}
		
	}
	private void searchShoppingCate(final String cate_name) {
		new Thread(){
			@Override
			public void run() {
				Gson gson=new Gson();
				Type type=new TypeToken<ArrayList<ShoppingCate>>(){}.getType();
				String strJson="";
				try {
					strJson=HttpUtil.getRequest(initSearchList_URL+"?cate_name="+cate_name);
					shoppingCates.clear();
					shoppingCates=gson.fromJson(strJson, type);
					adapter=new SearchAdapter(SearchActivity.this,shoppingCates);
					
					listView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					System.out.println("搜索商品："+strJson);
					System.out.println("搜索商品数量："+shoppingCates.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	class SearchAdapter extends BaseAdapter{
		Context context;
		ArrayList<ShoppingCate> shoppingCates;
		public SearchAdapter(Context context, ArrayList<ShoppingCate> shoppingCates) {
			this.context=context;
			this.shoppingCates=shoppingCates;
		}
		@Override
		public int getCount() {
			return shoppingCates.size();
		}
		@Override
		public Object getItem(int position) {
			return shoppingCates.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		class ViewHolder {
			ImageView imageViewPic;
			TextView textViewName;
			TextView textViewDesc;
			TextView textViewPrice;
			ImageButton buttonAddCart;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder viewHolder;
			if (convertView==null) {
				convertView=LayoutInflater.from(context).inflate(R.layout.item_product_list, null);
				viewHolder = new ViewHolder();
				viewHolder.imageViewPic = (ImageView)convertView.findViewById(R.id.imageview_product_list);
				viewHolder.textViewName = (TextView)convertView.findViewById(R.id.textview_product_name);
				viewHolder.textViewDesc = (TextView)convertView.findViewById(R.id.textview_product_desc);
				viewHolder.textViewPrice = (TextView)convertView.findViewById(R.id.textview_product_price);
				viewHolder.buttonAddCart = (ImageButton)convertView.findViewById(R.id.button_addToCart);
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
			viewHolder.buttonAddCart.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new Thread() {
						public void run() {
							try {
								String url = BaseUrl.BASE_URL + "/AddToBasketServlet?cate_name="+shoppingCate.getCate_name()+"&cate_price="+shoppingCate.getCate_price()+"&user_id="+42;
								HttpUtil.getRequest(url);
								fragmentTab.cartFragment.getShoppingCarList(userService.getUser_id());
//								handler.sendEmptyMessage(ADD_TO_BASKET);
							} catch (Exception e) {
								Toast.makeText(SearchActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
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
	}
	
	
}
