package com.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.com.FragmentTab;
import com.com.R;
import com.com.Welcome;
import com.com.activity.personal.PersonalAccountsActivity;
import com.com.activity.personal.PersonalBalanceActivity;
import com.com.activity.personal.PersonalCardVolumeActivity;
import com.com.activity.personal.PersonalFocusOnActivity;
import com.com.activity.personal.PersonalLoginActivity;
import com.com.activity.personal.PersonalMoreActivity;
import com.com.activity.personal.PersonalMyloveActivity;
import com.com.activity.personal.PersonalRegisterActivity;
import com.com.activity.personal.PersonalTheOrderActivity;
import com.com.service.UserService;

public class PersonalFragment extends Fragment {
    private View mView;
    private TextView register,login,loginuser,evenmore,point;
    private RelativeLayout myAccounts,myCardVolume,myTheOrder,myFocusOn,myLove,accountBalance;
    private LinearLayout noLoginLinear,loginLinear;
	public static int View1 = View.GONE;
	public static int View2 = View.VISIBLE;
	private static UserService userService;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView = inflater.inflate(R.layout.personal, container,false);
		userService = Welcome.userService;
		userService.setHandler(handler);
		mView();
	
		return mView;
	}
	
	//适配器
	private void mView() {
		noLoginLinear = (LinearLayout)mView.findViewById(R.id.noLoginLinear);
		loginLinear = (LinearLayout)mView.findViewById(R.id.loginLinear);
		//用户登录注册的的按钮的显示与隐藏
		System.out.println("用户"+userService.getUsername());
		noLoginLinear.setVisibility(View2);
		loginLinear.setVisibility(View1);
		
		evenmore = (TextView)mView.findViewById(R.id.evenmore);
		register = (TextView)mView.findViewById(R.id.register);
		login = (TextView)mView.findViewById(R.id.login);
		loginuser = (TextView)mView.findViewById(R.id.loginuser);
		point = (TextView)mView.findViewById(R.id.point);
		myAccounts = (RelativeLayout)mView.findViewById(R.id.my);
		myCardVolume = (RelativeLayout)mView.findViewById(R.id.coupon);
		myTheOrder = (RelativeLayout)mView.findViewById(R.id.order);
		myFocusOn = (RelativeLayout)mView.findViewById(R.id.see);
		myLove = (RelativeLayout)mView.findViewById(R.id.more);
		accountBalance = (RelativeLayout)mView.findViewById(R.id.feet);
		evenmore.setOnClickListener(onClickListener);
		register.setOnClickListener(onClickListener);
		login.setOnClickListener(onClickListener);
		myAccounts.setOnClickListener(onClickListener);
		myCardVolume.setOnClickListener(onClickListener);
		myTheOrder.setOnClickListener(onClickListener);
		myFocusOn.setOnClickListener(onClickListener);
		myLove.setOnClickListener(onClickListener);
		accountBalance.setOnClickListener(onClickListener);
	}
	
	OnClickListener onClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.evenmore:
				startActivity(new Intent(getActivity(),PersonalMoreActivity.class));
				break;
			case R.id.register:
				startActivity(new Intent(getActivity(),PersonalRegisterActivity.class));
				break;
			case R.id.login:
				startActivity(new Intent(getActivity(),PersonalLoginActivity.class));
				break;
			case R.id.my:
				startActivity(new Intent(getActivity(),PersonalAccountsActivity.class));
				break;
			case R.id.coupon:
				startActivity(new Intent(getActivity(),PersonalCardVolumeActivity.class));
				break;
			case R.id.order:
				startActivity(new Intent(getActivity(),PersonalTheOrderActivity.class));
				break;
			case R.id.see:
				startActivity(new Intent(getActivity(),PersonalFocusOnActivity.class));
				break;
			case R.id.more:
				if (userService.getUser_id()==0) {
					startActivity(new Intent(getActivity(),PersonalLoginActivity.class));
				} else {
					startActivity(new Intent(getActivity(),PersonalMyloveActivity.class));
				}
				break;
			case R.id.feet:
				startActivity(new Intent(getActivity(),PersonalBalanceActivity.class));
				break;	
			default:
				break;
			}
		}};
		
		//登录
		 Handler handler = new Handler(){
				public void handleMessage(android.os.Message msg) {
						Bundle bundle = msg.getData();
						String login = bundle.getString("user");
						String username = bundle.getString("username");
						float balance = bundle.getFloat("balance");
						float integral = bundle.getFloat("integral");
						if (login.equals("login")) {
							noLoginLinear.setVisibility(View1);
							loginLinear.setVisibility(View2);
							loginuser.setText(username);
							point.setText("积分:"+ integral +"\t"+ "余额：" + balance);
							int userId=userService.getUser_id();
							FragmentTab fragmentTab=(FragmentTab)getActivity();
							CartFragment cartFragment=(CartFragment)(fragmentTab.getFragmentList().get(4));
							cartFragment.initShoppingCarfoodCount01(userId);
						}else if (login.equals("cancel")){
							noLoginLinear.setVisibility(View2);
							loginLinear.setVisibility(View1);
							userService.setUser_id(0);
							userService.setUsername(null);
							userService.setPassword(null);
							userService.setTelephone(null);
							userService.setEmail(null);
							userService.setRegion(null);
							userService.setStreet_address(null);
							userService.setPostcode(null);	
						}
				};
			};
}
