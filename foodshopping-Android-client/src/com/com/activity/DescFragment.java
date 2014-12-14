package com.com.activity;

import java.io.InputStream;
import java.lang.reflect.Type;

import com.com.R;
import com.com.bean.ShoppingCateDetail;
import com.com.bean.ShoppingCateImage;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class DescFragment extends Fragment {
	private View mView;
	private ImageView imageView1,imageView2,imageView3,imageView4;
	private String url = BaseUrl.BASE_URL+"/DownLoadImageServlet";
	private ProductDetailActivity productDetailActivity;
	private int cate_id;
	private ShoppingCateDetail shoppingCateDetail;
	protected ShoppingCateImage shoppingCateImage;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment__product_desc, container, false);
		productDetailActivity = (ProductDetailActivity) getActivity();
		imageView1 = (ImageView)mView.findViewById(R.id.imagaView1);
		imageView2 = (ImageView)mView.findViewById(R.id.imageView2);
		imageView3 = (ImageView)mView.findViewById(R.id.imageView3);
		imageView4 = (ImageView)mView.findViewById(R.id.imageView4);
		
		cate_id = productDetailActivity.getCate_id();
		new AsyncTask<String, Void, Void>() {

			@Override
			protected Void doInBackground(String... params) {
				// TODO Auto-generated method stub
				
					try {
						String strJson = HttpUtil.getRequest(params[0]);
						Gson gson = new Gson();
						Type type = new TypeToken<ShoppingCateDetail>(){}.getType();
						shoppingCateDetail = gson.fromJson(strJson, type);
						shoppingCateImage = shoppingCateDetail.getShoppingCateImage();
						
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
								imageView1.setImageBitmap(result);
								
							};
							
						}.execute(BaseUrl.BASE_URL+"/DownLoadImageServlet?imageName="+shoppingCateImage.getCate_image_path01());
						
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
								imageView2.setImageBitmap(result);
								
							};
							
						}.execute(BaseUrl.BASE_URL+"/DownLoadImageServlet?imageName="+shoppingCateImage.getCate_image_path02());
						
						
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
								imageView3.setImageBitmap(result);
								
							};
							
						}.execute(BaseUrl.BASE_URL+"/DownLoadImageServlet?imageName="+shoppingCateImage.getCate_image_path03());
						
						
						
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
								imageView4.setImageBitmap(result);
								
							};
							
						}.execute(BaseUrl.BASE_URL+"/DownLoadImageServlet?imageName="+shoppingCateImage.getCate_image_path04());
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					return null;
				}
			
			
		}.execute(BaseUrl.BASE_URL+"/ShoppingCateDetailServlet?cate_id="+cate_id);
		
		



		
		
		return mView;
	}
}
