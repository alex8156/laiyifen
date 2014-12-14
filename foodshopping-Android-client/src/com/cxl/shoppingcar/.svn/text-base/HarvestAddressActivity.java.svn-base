package com.cxl.shoppingcar;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

import org.apache.http.protocol.HTTP;

import com.com.R;
import com.com.Welcome;
import com.com.activity.CartFragment.ShoppingCarBasAdapter.ViewHolder;
import com.com.service.UserService;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.com.util.Manage;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HarvestAddressActivity extends Activity {
	public static final String TAG = "HarvestAddressActivity";
	private RelativeLayout addressAdd_rl;
	private LinearLayout linearLayout06;
	private UserService userService;
	private static int currentUserId;
	private ListView consigneeAddress_lv;
	private Button editAndsave_btn;
	private static ArrayList<Address> addresss = new ArrayList<Address>();;
	public static final String BASE_URL = BaseUrl.BASE_URL;
	private static final String getAddressList_URL = BASE_URL+ "/addAddressServlet";
	private static final String AddressOperateServlet_URL=BASE_URL+"/addressOperateServlet";
	private static MyAddressBaseAdapter adapter;
	private String b;
	private String addressName;
	private String addressRegion;
	private String addressAddress;
	private String addressPostcode;
	private String addressPhone;
	
	private static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100:
				adapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_list);
		// Manage.getInstance().addActivity(this);
		userService = Welcome.userService;
		currentUserId = userService.getUser_id();
		getAddressList();
		System.out.println(TAG + addresss);
		addressAdd_rl = (RelativeLayout) findViewById(R.id.addressAdd_rl);
		consigneeAddress_lv = (ListView) findViewById(R.id.consigneeAddress_lv);
		editAndsave_btn = (Button) findViewById(R.id.editAndsave_btn);
		linearLayout06=(LinearLayout) findViewById(R.id.linearLayout06);
		
		// 设置一些初始化的属性
		System.out.println(TAG + consigneeAddress_lv + "11");
		consigneeAddress_lv.setCacheColorHint(Color.TRANSPARENT); // 拖动的时候 背景不变色
		// 设置间距的颜色和高度
		consigneeAddress_lv.setDivider(new ColorDrawable(R.color.blue_bg));
		consigneeAddress_lv.setDividerHeight(1);
		consigneeAddress_lv.setItemsCanFocus(true);
		consigneeAddress_lv.setItemsCanFocus(true);

		adapter = new MyAddressBaseAdapter(this);
		consigneeAddress_lv.setAdapter(adapter);
		
		// 编辑及保存按钮点击事件
		editAndsave_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				b = editAndsave_btn.getText().toString();
				if (b.equals("编辑")) {
					editAndsave_btn.setText("保存");
					for (Address address : addresss) {
						address.setSelected(false);
						address.setIsdeleted(true);
						address.setModificationed(true);
					}
				}else if (b.equals("保存")) {
					System.out.println("SSSSS");
					editAndsave_btn.setText("编辑");
					for (Address address : addresss) {
						address.setSelected(true);
						address.setIsdeleted(false);
						address.setModificationed(false);
					}
				}
				dataChange();
			}
		});

		addressAdd_rl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HarvestAddressActivity.this,
						HarvestAddressaddressDetailActivity.class);
				startActivity(intent);
			}
		});
		linearLayout06.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(HarvestAddressActivity.this, CommitOrderActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

	protected void dataChange() {
		// 通知listView刷新
		adapter.notifyDataSetChanged();
		// for (Address address : addresss) {
		// //System.out.println(addresss);
		// }
	}

	public static void getAddressList() {
		new Thread() {
			public void run() {
				try {
					String strJson;
					String url = getAddressList_URL + "?userId="
							+ currentUserId;
					strJson = HttpUtil.getRequest(url);
					System.out.println("TAG:" + strJson);
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<ArrayList<Address>>() {
					}.getType();
					addresss = gson.fromJson(strJson, type);
					handler.sendEmptyMessage(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();	
	}
	
	public class MyAddressBaseAdapter extends BaseAdapter {
		private Context context;
		
		public MyAddressBaseAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return addresss.size();
		}

		@Override
		public Object getItem(int position) {

			return addresss.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}
		public class ViewHolder {
			public ImageView delete;
			public ImageView select;
			public TextView content;
			public TextView edit;
			public int position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.address_item, null);
				viewHolder.delete = (ImageView) convertView
						.findViewById(R.id.delete);
				viewHolder.select = (ImageView) convertView
						.findViewById(R.id.select);
				viewHolder.content = (TextView) convertView
						.findViewById(R.id.content);
				viewHolder.edit = (TextView) convertView.findViewById(R.id.edit);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();			
			}
			viewHolder.position=position;
			final Address address = addresss.get(position);

			
			String a = address.getAddressRegion() + address.getAddressAddress()
					+ " " + address.getAddressName() + " "
					+ address.getAddressPhone();
			System.out.println(TAG + a);
			viewHolder.content.setText(a);

			if (address.isIsdeleted() && address.isModificationed()) {
				viewHolder.delete.setVisibility(View.VISIBLE);
				viewHolder.select.setVisibility(View.GONE);
				viewHolder.edit.setVisibility(View.VISIBLE);
				viewHolder.delete.setOnClickListener(new MyOnClickListener(address,viewHolder));
				viewHolder.edit.setOnClickListener(new MyOnClickListener(address,viewHolder));
			} else {
				viewHolder.delete.setVisibility(View.GONE);
				viewHolder.select.setVisibility(View.VISIBLE);
				viewHolder.edit.setVisibility(View.GONE);
				viewHolder.select.setOnClickListener(new MyOnClickListener(address,viewHolder));
			}
			
			consigneeAddress_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					viewHolder.position=position;
					final Address address=addresss.get(position);
					System.out.println(viewHolder.position+"viewHolder.position");
					b = editAndsave_btn.getText().toString();
					new Thread(){
						public void run() {
							if (b.equals("编辑")) {
								Intent intent=new Intent();
//								Bundle bundle=new Bundle();
//								bundle.putParcelable("address", address);
//								intent.putExtra("bundle", bundle);
								intent.setClass(HarvestAddressActivity.this, CommitOrderActivity.class);
								startActivity(intent);
//								viewHolder.select.setBackgroundResource(R.drawable.address_select);
								Message message=new Message();
								message.what=2000;
								Bundle bundle=new Bundle();
								bundle.putParcelable("address",address);
								message.setData(bundle);
								System.out.println("123321321");
								CommitOrderActivity.handler01.sendMessage(message);
								
							}else if(b.equals("保存")){
								Toast toast=Toast.makeText(context, "编辑状态不能进行本操作", 1);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();						
							}
						};
					}.start();
										
				}
		
			});
			
			return convertView;
		}

	}
	
	public class MyOnClickListener implements View.OnClickListener {
		Address address;
		com.cxl.shoppingcar.HarvestAddressActivity.MyAddressBaseAdapter.ViewHolder viewHolder;
		public MyOnClickListener(Address address,com.cxl.shoppingcar.HarvestAddressActivity.MyAddressBaseAdapter.ViewHolder viewHolder) {
			this.address=address; 
			this.viewHolder=viewHolder;
		}
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.delete:
				addresss.remove(v);
				adapter.notifyDataSetChanged();
				int AddressId=address.getAddressId();
				String url = AddressOperateServlet_URL
						+ "?operate=delete&AddressId="
						+ AddressId;
				AddressOperate(url) ;
				
				break;
			case R.id.select:
				viewHolder.select.setBackgroundResource(R.drawable.address_select);
				Intent intent=new Intent();
				 addressName=address.getAddressName();
				 addressRegion=address.getAddressRegion();
				 addressAddress=address.getAddressAddress();
				 addressPostcode=address.getAddressPostcode()+"";
				 addressPhone=address.getAddressPhone()+"";
				intent.setClass(HarvestAddressActivity.this,CommitOrderActivity.class);
				startActivity(intent);
				viewHolder.select.setBackgroundResource(R.drawable.address_normal);
				break;
			case R.id.edit:
				intent=new Intent();
				 getAddressDate(address,intent);
				 intent.setClass(HarvestAddressActivity.this, ModifyAddessActivity.class);
					startActivity(intent);
				break;

			default:
				break;
			}

		}
	}

	public void AddressOperate(final String url) {
		new Thread(){
			public void run() {
				String strJson;
				try {
					strJson = HttpUtil.getRequest(url);
					System.out.println(strJson);
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Integer>() {
					}.getType();
					int result = gson.fromJson(strJson, type);				
				} catch (Exception e) {
					e.printStackTrace();
				}								
			};
		}.start();
		
	}
	public void getAddressDate(Address address, Intent intent){
		String addressName=address.getAddressName();
		String addressRegion=address.getAddressRegion();
		String addressAddress=address.getAddressAddress();
		String addressPostcode=address.getAddressPostcode()+"";
		String addressPhone=address.getAddressPhone()+"";
		
		intent.putExtra("addressName", addressName);
		intent.putExtra("addressRegion", addressRegion);
		intent.putExtra("addressAddress", addressAddress);
		intent.putExtra("addressPostcode", addressPostcode);
		intent.putExtra("addressPhone", addressPhone);
	}
}
