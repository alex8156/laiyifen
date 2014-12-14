package com.cxl.shoppingcar;

import com.com.R;
import com.com.service.UserService;
import com.com.util.Manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyAddessActivity extends Activity{
	
	private TextView keep;
	private EditText name,address,addressDetail,code,telephone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifyaddress);
		//Manage.getInstance().addActivity(this);
				
		keep=(TextView) findViewById(R.id.keep);
		name=(EditText) findViewById(R.id.name);
		address=(EditText) findViewById(R.id.address);
		addressDetail=(EditText) findViewById(R.id.addressDetail);
		code=(EditText) findViewById(R.id.code);
		telephone=(EditText) findViewById(R.id.telephone);
		
		Intent intent=getIntent();
		String addressName=intent.getStringExtra("addressName");
		System.out.println("addressName"+addressName);
		String addressRegion=intent.getStringExtra("addressRegion");
		String addressAddress=intent.getStringExtra("addressAddress");
		String addressPostcode=intent.getStringExtra("addressPostcode");
		String addressPhone=intent.getStringExtra("addressPhone");
		name.setText(addressName);
		address.setText(addressRegion);
		addressDetail.setText(addressAddress);
		code.setText(addressPostcode);
		telephone.setText(addressPhone);
		
		
		keep.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Toast.makeText(ModifyAddessActivity.this,"保存成功" , 1).show();				
			}
		});
	}

	
}
