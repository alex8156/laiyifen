package com.com.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.com.R;
import com.com.bean.ShoppingCateComment;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CommentFragment extends Fragment {
	
	private ListView listView;
	private ArrayList<ShoppingCateComment> shoppingCateComments;
	private ProductDetailActivity productDetailActivity;
	private View mView;
	private CommentAdapter commentAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment__product_comment, container, false);
		productDetailActivity = (ProductDetailActivity) getActivity();
		listView = (ListView) mView.findViewById(R.id.listView_comment);
		commentAdapter = new CommentAdapter();
		new AsyncTask<String, Void, ArrayList<ShoppingCateComment>>() {

			@Override
			protected ArrayList<ShoppingCateComment> doInBackground(
					String... params) {
				// TODO Auto-generated method stub
				try {
					String strJson = HttpUtil.getRequest(params[0]);
					Gson gson = new Gson();
					Type type = new TypeToken<ArrayList<ShoppingCateComment>>(){}.getType();
					shoppingCateComments = gson.fromJson(strJson, type);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return shoppingCateComments;
			}
			
			protected void onPostExecute(ArrayList<ShoppingCateComment> result) {
				
				listView.setAdapter(commentAdapter);
			};
			
		}.execute(BaseUrl.BASE_URL + "/ShoppingCateCommentServlet?cate_id="+productDetailActivity.getCate_id());
		
		return mView;
	}
	
	class CommentAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return shoppingCateComments.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return shoppingCateComments.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ShoppingCateComment cateComment = shoppingCateComments.get(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(productDetailActivity).inflate(R.layout.item_comment, null);
			}
			RatingBar ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);
			TextView tvUserName = (TextView)convertView.findViewById(R.id.textView_userName);
			TextView tvDate = (TextView)convertView.findViewById(R.id.textView_date);
			TextView tvContent = (TextView)convertView.findViewById(R.id.textView_content);
			
			ratingBar.setRating(cateComment.getCate_star_level());
			tvUserName.setText(cateComment.getCate_user_name());
			tvDate.setText(cateComment.getCate_comment_date());
			tvContent.setText(cateComment.getCate_comment_context());
			
			
			return convertView;
		}
		
	}
}
