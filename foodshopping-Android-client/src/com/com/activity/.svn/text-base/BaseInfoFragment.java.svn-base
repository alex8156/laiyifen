package com.com.activity;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.com.FragmentTab;
import com.com.R;
import com.com.Welcome;
import com.com.bean.ShoppingCate;
import com.com.bean.ShoppingCateDetail;
import com.com.bean.ShoppingCateImage;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.com.widget.CustomedButton;
import com.com.widget.CustomedGallery;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BaseInfoFragment extends Fragment implements OnClickListener, OnItemClickListener {
	
	private View mView;
	private ShoppingCateDetail shoppingCateDetail;
	private ProductDetailActivity productDetailActivity;
	private ArrayList<ShoppingCate> shoppingCates;
	
	private TextView tvTitle,tvStock,tvOldPrice,tvNewPrice,tvDiscountAmount; 
	private Button btAdd;  //
	private CheckBox checkBoxFavorite;
	private int cate_id;  
	
	private CustomedGallery topGallery; //顶部的gallery;
	private CustomedGallery recommendGallery;  //底部推荐的gallery;
	private GalleryAdapter galleryAdapter; 
	private RecommendGalleryAdapter recommendGalleryAdapter;
	
	public String[] imageNames;
	
	public static final String TAG = "BaseInfoFragment";
	
	private FragmentTab fragmentTab;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment__base_info, container, false);
		init();
		return mView;
	}

	private void init() {
		// TODO Auto-generated method stub
		fragmentTab = FragmentTab.getInstance();
		productDetailActivity = (ProductDetailActivity) getActivity();
		tvTitle = (TextView) mView.findViewById(R.id.title);
		tvStock = (TextView) mView.findViewById(R.id.stock);
		tvOldPrice = (TextView) mView.findViewById(R.id.mprice);
		tvNewPrice = (TextView) mView.findViewById(R.id.dprice);
		tvDiscountAmount = (TextView) mView.findViewById(R.id.discountAmount);
		btAdd = (Button)mView.findViewById(R.id.add);
		checkBoxFavorite = (CheckBox)mView.findViewById(R.id.checkbox_favorite);
		
		btAdd.setOnClickListener(this);
		
		topGallery = (CustomedGallery)mView.findViewById(R.id.topGallery);
		recommendGallery = (CustomedGallery)mView.findViewById(R.id.recommend);
		
		recommendGallery.setOnItemClickListener(this);
		
		cate_id = productDetailActivity.getCate_id();
		//AsyncTask发送请求
		GetDateAsyncTask asyncTask = new GetDateAsyncTask();
		String url = BaseUrl.BASE_URL + "/ShoppingCateDetailServlet?cate_id="+cate_id;
		asyncTask.execute(url);
		
		new AsyncTask<String, Void, ArrayList<ShoppingCate>>() {

			@Override
			protected ArrayList<ShoppingCate> doInBackground(String... params) {
				// TODO Auto-generated method stub
				try {
					String strJson = HttpUtil.getRequest(params[0]);
					Gson gson = new Gson();
					Type type = new TypeToken<ArrayList<ShoppingCate>>(){}.getType();
					shoppingCates = gson.fromJson(strJson, type);
					Collections.shuffle(shoppingCates);
					for (ShoppingCate string : shoppingCates) {
						Log.i(TAG, "1-10  "+string.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
				return shoppingCates;
			}
			
			protected void onPostExecute(ArrayList<ShoppingCate> result) {
				recommendGalleryAdapter = new RecommendGalleryAdapter();
				recommendGallery.setAdapter(recommendGalleryAdapter);
				
			};
		}.execute(BaseUrl.BASE_URL + "/ProductListServlet?maxRow=" + 10 +"&minRow=" + 1);
		
		
	}
	
	private class GetDateAsyncTask extends AsyncTask<String, Void, ShoppingCateDetail> {
		
		@Override
		protected ShoppingCateDetail doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				String strJson = HttpUtil.getRequest(params[0]);
				Gson gson = new Gson();
				Type type = new TypeToken<ShoppingCateDetail>(){}.getType();
				shoppingCateDetail = gson.fromJson(strJson, type);
				Log.i(TAG, "doInBackground  "+shoppingCateDetail);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return shoppingCateDetail;
		}
		

		@Override
		protected void onPostExecute(ShoppingCateDetail shoppingCateDetail) {
			// TODO Auto-generated method stub
			if (shoppingCateDetail!=null) {
				
				Log.i(TAG, "onPostExecute  "+shoppingCateDetail);
				tvTitle.setText(shoppingCateDetail.getCate_name());
				tvStock.setText(String.valueOf(shoppingCateDetail.getCate_inventory()));
				tvOldPrice.setText("￥"+shoppingCateDetail.getCate_oldprice());
				tvNewPrice.setText("￥"+String.valueOf(shoppingCateDetail.getCate_price()));
				tvDiscountAmount.setText(shoppingCateDetail.getCate_sale_info());
				ShoppingCateImage cateImage = shoppingCateDetail.getShoppingCateImage();
				imageNames = new String[] {cateImage.getCate_image_path01(),cateImage.getCate_image_path02(),cateImage.getCate_image_path03(),cateImage.getCate_image_path04()};
				
				galleryAdapter = new GalleryAdapter();
				topGallery.setAdapter(galleryAdapter);
			}
			
		}
	}
	
	private class GalleryAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageNames.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return imageNames[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
//			if (convertView != null) {
				final ImageView imageView = new ImageView(productDetailActivity);
				imageView.setAdjustViewBounds(true);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageView.setImageResource(R.drawable.t3);
				new AsyncTask<String, Void, Bitmap>() {
					@Override
					protected Bitmap doInBackground(String... params) {
						// TODO Auto-generated method stub
						Bitmap bitmap = null;
						try {
							InputStream inputStream = HttpUtil.getDownFile(params[0]);
							bitmap = BitmapFactory.decodeStream(inputStream);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return bitmap;
					}
					protected void onPostExecute(Bitmap result) {
						imageView.setImageBitmap(result);
						
					};
					
				}.execute(BaseUrl.BASE_URL+"/DownLoadImageServlet?imageName="+imageNames[position]);
				
				
				convertView = imageView;
				
//			}
			
			
			return convertView;
		}
		
	}
	
	private class RecommendGalleryAdapter extends BaseAdapter {
		
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final ViewHolder viewHolder ;
			if (convertView == null) {
				convertView = LayoutInflater.from(productDetailActivity).inflate(R.layout.item_product_gallery, null);
				viewHolder = new ViewHolder();
				viewHolder.ivProduct = (ImageView) convertView.findViewById(R.id.imageView_product_pic);
				viewHolder.tvOldPrice = (TextView)convertView.findViewById(R.id.textview_old_price);
				viewHolder.tvNewPrice = (TextView)convertView.findViewById(R.id.textview_new_price);
				viewHolder.tvName = (TextView)convertView.findViewById(R.id.textView_product_name);
				viewHolder.btAdd = (CustomedButton)convertView.findViewById(R.id.button_addToCart);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			final ShoppingCate shoppingCate = shoppingCates.get(position);
			viewHolder.ivProduct.setImageResource(R.drawable.t3);
			viewHolder.tvOldPrice.setText("￥"+shoppingCate.getCate_oldprice());
			viewHolder.tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); //设置文字中划线、抗锯齿
			viewHolder.tvNewPrice.setText("￥"+shoppingCate.getCate_price());
			viewHolder.tvName.setText("￥"+shoppingCate.getCate_name());
			viewHolder.btAdd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new AsyncTask<String, Void, Void>() {

						@Override
						protected Void doInBackground(String... params) {
							// TODO Auto-generated method stub
							try {
								HttpUtil.getRequest(params[0]);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return null;
						}
						protected void onPostExecute(Void result) {
							Toast.makeText(getActivity(), "加入购物车成功", Toast.LENGTH_SHORT).show();
							
						};
						
					}.execute(BaseUrl.BASE_URL+"/AddToBasketServlet?user_id=" + 42 + "&cate_name="+shoppingCate.getCate_name()+"&cate_price="+shoppingCate.getCate_price());
				}
			});
			
			new AsyncTask<String, Void, ShoppingCateImage>(){
				
				@Override
				protected ShoppingCateImage doInBackground(String... params) {
					// TODO Auto-generated method stub
					try {
						String strJson = HttpUtil.getRequest(params[0]);
						Gson gson = new Gson();
						Type type = new TypeToken<ShoppingCateImage>(){}.getType();
						ShoppingCateImage cateImage = gson.fromJson(strJson, type);
						new AsyncTask<String, Void, Bitmap>() {
							@Override
							protected Bitmap doInBackground(String... params) {
								// TODO Auto-generated method stub
								Bitmap bitmap = null;
								try {
									InputStream inputStream = HttpUtil.getDownFile(params[0]);
									bitmap = BitmapFactory.decodeStream(inputStream);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								return bitmap;
							}
							
							protected void onPostExecute(Bitmap result) {
								viewHolder.ivProduct.setImageBitmap(result);
							}
						}.execute(BaseUrl.BASE_URL+"/DownLoadImageServlet?imageName="+cateImage.getCate_image_path01());
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return null;
				}
				
			}.execute(BaseUrl.BASE_URL + "/ProductImagePathServlet?cate_image_id="+shoppingCate.getCate_image_id());
			
			
			return convertView;
		}
		
	}
	class ViewHolder {
		ImageView ivProduct;
		TextView tvOldPrice;
		TextView tvNewPrice;
		TextView tvName;
		CustomedButton btAdd;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add:
			final int user_id = Welcome.userService.getUser_id();
			final String shopping_basket_name = shoppingCateDetail.getCate_name();
			final String shopping_basket_price = "￥"+shoppingCateDetail.getCate_price();
			String shopping_basket_image = imageNames[0];
			if (user_id == 0) {
				//如果用户不登陆 加入购物车在本地数据库
				FragmentTab.dbManager.insertBasket(shopping_basket_name,shopping_basket_price,shopping_basket_image);
			} else{ 
				//如果用户登录 则加入远程数据库
				new AsyncTask<String, Void, Void>() {
					@Override
					protected Void doInBackground(String... params) {
						// TODO Auto-generated method stub
						try {
							HttpUtil.getRequest(params[0]);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return null;
					}
					protected void onPostExecute(Void result) {
					     try {
					    	 String url = BaseUrl.BASE_URL + "/AddToBasketServlet?cate_name="+shopping_basket_name+"&cate_price="+shoppingCateDetail.getCate_price()+"&user_id="+user_id;
					    	 HttpUtil.getRequest(url);
					    	 FragmentTab.cartFragment.getShoppingCarList(user_id);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					};
					
				}.execute(BaseUrl.BASE_URL+"/AddToBasketServlet?user_id=" + user_id+ "&cate_name="+shoppingCateDetail.getCate_name()+"&cate_price="+shoppingCateDetail.getCate_price());
			}
			
			Toast.makeText(getActivity(), "加入购物车成功", Toast.LENGTH_SHORT).show();
			//发送广播
			Intent intent = new Intent("ADD_TO_BASKET");
			productDetailActivity.sendBroadcast(intent);
			
			break;

		case R.id.checkbox_favorite:
			
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(),ProductDetailActivity.class);
		intent.putExtra("cate_id", shoppingCates.get(position).getCate_id());
		startActivity(intent);
	}
}
