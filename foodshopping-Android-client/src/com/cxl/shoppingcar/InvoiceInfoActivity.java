package com.cxl.shoppingcar;

import com.com.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InvoiceInfoActivity extends Activity{
private Button editAndsave_btn;
private EditText editText_invoice_title,editText_remark;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.invoice);
	
	editAndsave_btn=(Button) findViewById(R.id.editAndsave_btn);
	editText_invoice_title=(EditText) findViewById(R.id.editText_invoice_title);
	editText_remark=(EditText) findViewById(R.id.editText_remark);
	
	editAndsave_btn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {			
			String invoice_title=editText_invoice_title.getText().toString();
			String remark=editText_remark.getText().toString();
			Intent intent=new Intent();
			intent.setClass(InvoiceInfoActivity.this, CommitOrderActivity.class);
			startActivity(intent);
			
			Message message=new Message();
			message.what=2001;
			Bundle bundle=new Bundle();
			bundle.putString("invoice_title", invoice_title);
			bundle.putString("remark", remark);						
			message.setData(bundle);
			CommitOrderActivity.handler01.sendMessage(message);
			
		}
	});
}
}
