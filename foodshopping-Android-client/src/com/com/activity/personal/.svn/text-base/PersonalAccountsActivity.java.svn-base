package com.com.activity.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.com.R;
import com.com.Welcome;
import com.com.service.UserService;
import com.cxl.shoppingcar.HarvestAddressaddressDetailActivity;

public class PersonalAccountsActivity extends Activity {
	private TextView usercardNum,balance,integral;
	private RelativeLayout account,recharge,integralHistory,payHistory,address,soundPay;
	private ImageView back;
	private static UserService userService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_account);
		userService = Welcome.userService;
		mView();
		
		
	}
	
	
	private void mView() {
		usercardNum = (TextView)findViewById(R.id.usercardNum);
		balance = (TextView)findViewById(R.id.balance);
		integral = (TextView)findViewById(R.id.integral);
		back = (ImageView)findViewById(R.id.back);
		account = (RelativeLayout)findViewById(R.id.account);
		recharge = (RelativeLayout)findViewById(R.id.recharge);
		integralHistory = (RelativeLayout)findViewById(R.id.integralHistory);
		payHistory = (RelativeLayout)findViewById(R.id.payHistory);
		address = (RelativeLayout)findViewById(R.id.address);
		soundPay = (RelativeLayout)findViewById(R.id.soundPay);
		
		usercardNum.setText(String.valueOf(userService.getUser_id()));
		
		back.setOnClickListener(onClickListener);
		account.setOnClickListener(onClickListener);
		recharge.setOnClickListener(onClickListener);
		integralHistory.setOnClickListener(onClickListener);
		payHistory.setOnClickListener(onClickListener);
		address.setOnClickListener(onClickListener);
		soundPay.setOnClickListener(onClickListener);
		
		balance.setText("余额：" + userService.getAccount_balance());
		integral.setText("积分：" + userService.getAccount_integral());
	}


	OnClickListener onClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.account:
				startActivity(new Intent(PersonalAccountsActivity.this, AccountsUpdataActivity.class));
				break;
			case R.id.recharge:
				startActivity(new Intent(PersonalAccountsActivity.this, AccountsBalanceActivity.class));
				break;
			case R.id.integralHistory:
				startActivity(new Intent(PersonalAccountsActivity.this, AccountsIntegralActivity.class));
				break;
			case R.id.payHistory:
				startActivity(new Intent(PersonalAccountsActivity.this, AccountsConsumptionActivity.class));
				break;
			case R.id.address:
				startActivity(new Intent(PersonalAccountsActivity.this, HarvestAddressaddressDetailActivity.class));
				break;
			case R.id.soundPay:
				startActivity(new Intent(PersonalAccountsActivity.this, AccountsPayActivity.class));
				break;
			default:
				break;
			}
		}};

}
