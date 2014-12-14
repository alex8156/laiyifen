package com.cxl.shoppingcar;

import java.util.ArrayList;

import com.com.R;
import com.com.Welcome;
import com.com.service.UserService;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.com.util.Manage;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HarvestAddressaddressDetailActivity extends Activity{
	private UserService userService;
	private TextView keep;
	private EditText name,address,addressDetail,code,telephone;
	public static final String BASE_URL = BaseUrl.BASE_URL;
	private static final String AddressOperateServlet_URL=BASE_URL+"/addressOperateServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_detail);
		Manage.getInstance().addActivity(this);
		userService = Welcome.userService;
		keep=(TextView) findViewById(R.id.keep);
		name=(EditText) findViewById(R.id.name);
		address=(EditText) findViewById(R.id.address);
		addressDetail=(EditText) findViewById(R.id.addressDetail);
		code=(EditText) findViewById(R.id.code);
		telephone=(EditText) findViewById(R.id.telephone);
		
		
		keep.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String addressName=name.getText().toString();
				String addressRegion=address.getText().toString();
				String addressAddress=addressDetail.getText().toString();
				int addressPostcode=Integer.valueOf(code.getText().toString());
				long addressPhone=Long.valueOf(telephone.getText().toString());
				int userId=userService.getUser_id();
				Address address=new Address(0, false, false, addressName, addressRegion, addressAddress, addressPostcode, addressPhone, false);
				String url=AddressOperateServlet_URL+"?operate=add"+"&addressName="+addressName+"&addressRegion="+addressRegion
						+"&addressAddress="+addressAddress+"&addressPostcode="+addressPostcode+"&addressPhone="+addressPhone+"&userId="+userId;
				System.out.println("新增地址URL:"+url);
				addAddress(url);				
				Toast.makeText(HarvestAddressaddressDetailActivity.this,"地址添加成功" , 1).show();	
				Intent intent=new Intent();
				intent.setClass(HarvestAddressaddressDetailActivity.this, HarvestAddressActivity.class);
				startActivity(intent);
			}
		});
	}
	protected void addAddress(final String url) {
		new Thread() {
			public void run() {
				try {
					String strJson;
					strJson = HttpUtil.getRequest(url);
					System.out.println("TAG:" + strJson);
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Integer>() {
					}.getType();
				int	result= gson.fromJson(strJson, type);
				System.out.println(result);
				if(result==1){
					
					System.out.println("gggggg");
					HarvestAddressActivity.getAddressList();
				}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();	
		
	}

	
}
