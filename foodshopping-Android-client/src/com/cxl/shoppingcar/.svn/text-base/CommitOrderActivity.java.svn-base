package com.cxl.shoppingcar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.com.R;
import com.com.Welcome;
import com.com.service.UserService;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.com.util.Manage;
import com.google.gson.Gson;

public class CommitOrderActivity extends Activity{
	
	public static final String BASE_URL =BaseUrl.BASE_URL;
	public static final String commitOrder_URL = BASE_URL
			+ "/commitOrderServlet";
	private Button orderCommit_btn;
	private static TextView sendMethodDes_tv;
	private TextView paymentMethod_tv;
	private static TextView payMoney_tv;
	private TextView sppedDistribution_tv;//快速配送
	private TextView StoresToThe_tv;//门店自提
	private static TextView goodTotalPrice_tv;
	private TextView couponDeduction_tv;
	private static TextView invoiceRemark_tv;
	private static TextView invoiceTitle_tv;
	private TextView integralDeduction_tv;
	private TextView expressCost_tv;
	private ImageView addConsigneeAddr_iv, paymentMethod_iv, Coupon_iv,
			invoice_iv, invoiceRemark_iv;
	private LinearLayout linearLayout01, linearLayout02, linearLayout04,linearLayout05;
	private TableLayout coupon_layout;
	private String value;
	private CharSequence[] payItems = { "支付宝即时到账", "银联支付", "悠点卡支付", "伊点卡支付" };
	private Order order;//用于保存订单提交的信息
	String orderNo;// 订单号
	int userId;
	String orderTime; //下单时间
	static String amountPay;// 应付金额
	String deliveryMethod="快速配送";// 配送方式
	static int recieverAddressId;
	static String deliveryAdress;// 配送地址
	String paymentMethod;// 支付方式
	String coupon;// 优惠券
	boolean usable;// 可用
	static String invoiceTitle;// 发票抬头
	static String invoiceRemark;// 发票备注
	static String tatal_price;// 商品总价
	String couponsDeduction="-￥0.00";// 优惠抵扣券
	String deductibleRewards="-￥0.00";// 积分抵扣
	String deliveryCosts="￥8.00";// 快递费用;
	
	private UserService userService;
//	@Override
//	protected void onResume() {		
//		super.onResume();
//	System.out.println("2112321");
//	Intent intent=getIntent();
//	if(intent =null){
//	Bundle bundle=intent.getBundleExtra("bundle");
//	Address address=bundle.getParcelable("address");	
//	recieverAddressId=Integer.valueOf(address.getAddressId());
//	deliveryAdress=address.getAddressName() + "  " +address.getAddressPhone()
//			+ " " +address.getAddressAddress() + " " + address.getAddressRegion() + " "
//			+ address.getAddressPostcode();	
//	System.out.println(deliveryAdress+"45466546");
//	System.out.println(sendMethodDes_tv+"32321321231");
//		sendMethodDes_tv.setText(deliveryAdress+"sgfds");
//	}
//	}
	public static Handler handler01=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {			
			case 2000:	
				Bundle bundle=msg.getData();
				Address address=bundle.getParcelable("address");
				System.out.println(address+"address;;;;");
				recieverAddressId=Integer.valueOf(address.getAddressId());
				deliveryAdress=address.getAddressName() + "  " +address.getAddressPhone()
						+ " " +address.getAddressAddress() + " " + address.getAddressRegion() + " "
						+ address.getAddressPostcode();	
				System.out.println(deliveryAdress+"45466546");
				System.out.println(sendMethodDes_tv+"32321321231");
					sendMethodDes_tv.setText(deliveryAdress+"sgfds");					
				break;

			case 2001:
				Bundle bundle01=msg.getData();
				invoiceTitle=bundle01.getString("invoice_title");
				invoiceRemark=bundle01.getString("remark");
				System.out.println(invoiceTitle+invoiceRemark+"99999");
				invoiceTitle_tv.setText(invoiceTitle);
				invoiceRemark_tv.setText(invoiceRemark);
				break;
			case 2002:
				Bundle bundle02=msg.getData();
				Appconstant.orderId=bundle02.getString("orderId");

				break;	
			}
		};
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settlement);
		Manage.getInstance().addActivity(this);
//		actionBar.setDisplayHomeAsUpEnabled(true);
		userService = Welcome.userService;
		userId=userService.getUser_id();
		orderCommit_btn = (Button) findViewById(R.id.orderCommit_btn);
		linearLayout01 = (LinearLayout) findViewById(R.id.linearLayout01);
		linearLayout02 = (LinearLayout) findViewById(R.id.linearLayout02);
		coupon_layout = (TableLayout) findViewById(R.id.coupon_layout);
		linearLayout04 = (LinearLayout) findViewById(R.id.linearLayout04);
		linearLayout05=(LinearLayout) findViewById(R.id.linearLayout05);
		sendMethodDes_tv = (TextView) findViewById(R.id.sendMethodDes_tv);
		payMoney_tv = (TextView) findViewById(R.id.payMoney_tv);
		sppedDistribution_tv = (TextView) findViewById(R.id.sppedDistribution_tv);
		StoresToThe_tv = (TextView) findViewById(R.id.StoresToThe_tv);
		goodTotalPrice_tv = (TextView) findViewById(R.id.goodTotalPrice_tv);
		System.out.println("goodTotalPrice_tv 执行");
		couponDeduction_tv = (TextView) findViewById(R.id.couponDeduction_tv);
		integralDeduction_tv = (TextView) findViewById(R.id.integralDeduction_tv);
		expressCost_tv = (TextView) findViewById(R.id.expressCost_tv);
		paymentMethod_tv = (TextView) findViewById(R.id.paymentMethod_tv);
		addConsigneeAddr_iv = (ImageView) findViewById(R.id.addConsigneeAddr_iv);
		paymentMethod_iv = (ImageView) findViewById(R.id.paymentMethod_iv);
		Coupon_iv = (ImageView) findViewById(R.id.Coupon_iv);
		invoice_iv = (ImageView) findViewById(R.id.invoice_iv);
		invoiceRemark_iv = (ImageView) findViewById(R.id.invoiceRemark_iv);
		invoiceTitle_tv = (TextView) findViewById(R.id.invoiceTitle_tv);
		invoiceRemark_tv= (TextView) findViewById(R.id.invoiceRemark_tv);
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		Intent intent = getIntent();
		tatal_price="￥"+decimalFormat.format(intent.getDoubleExtra("strTotalPrice", 0.00d));
		goodTotalPrice_tv.setText(tatal_price);
		
		amountPay="￥"+decimalFormat.format((Double.valueOf(tatal_price.substring(1))+8.00d));				
		payMoney_tv.setText(amountPay);
		
		linearLayout01.setOnClickListener(new MyOnClickListener());
		linearLayout02.setOnClickListener(new MyOnClickListener());
		coupon_layout.setOnClickListener(new MyOnClickListener());
		linearLayout04.setOnClickListener(new MyOnClickListener());
		linearLayout05.setOnClickListener(new MyOnClickListener());
		orderCommit_btn.setOnClickListener(new MyOnClickListener());
		StoresToThe_tv.setOnClickListener(new MyOnClickListener());
	}

	protected static void postIntent(String orderId2) {
		
		
	}

	public class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.linearLayout01:
				Intent intent = new Intent();
				intent.setClass(CommitOrderActivity.this,
						HarvestAddressActivity.class);
				startActivity(intent);
				break;
			case R.id.linearLayout02:
				// System.out.println("点击了支付");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						CommitOrderActivity.this);
				builder.setTitle("选择支付方式:");
				builder.setItems(payItems,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									paymentMethod_tv.setText(payItems[0]);
									paymentMethod=payItems[0].toString();
									break;
								case 1:
									paymentMethod_tv.setText(payItems[1]);
									paymentMethod=payItems[1].toString();
									break;
								case 2:
									paymentMethod_tv.setText(payItems[2]);
									paymentMethod=payItems[2].toString();
									break;
								case 3:
									paymentMethod_tv.setText(payItems[3]);
									paymentMethod=payItems[3].toString();
									break;
								}
							}
						});
				builder.show();
				break;
			case R.id.coupon_layout:
				//System.out.println("优惠券被点击了");
				Intent intent01 = new Intent();
				intent01.setClass(CommitOrderActivity.this,
						UseCouponActivity.class);
				startActivity(intent01);
				break;
			case R.id.linearLayout04:
				Intent intent02 = new Intent();
				intent02.setClass(CommitOrderActivity.this,
						InvoiceInfoActivity.class);
				startActivity(intent02);
				break;
			case R.id.linearLayout05:
				finish();
				Intent intent04 = new Intent("ENTER_INTO_BASKET");
				sendBroadcast(intent04);
				break;	
				
			case R.id.orderCommit_btn:
				SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");       
//				Date  curDate=   new Date(System.currentTimeMillis());//获取当前时间
//				System.out.println(curDate+"1321321213213");
//				String  orderTime = formatter.format(curDate); 
			    order=new Order(orderNo,userId, orderTime, amountPay, deliveryMethod, recieverAddressId, deliveryAdress, paymentMethod, coupon, usable, invoiceTitle, invoiceRemark, tatal_price, couponsDeduction, deductibleRewards, deliveryCosts);
			    if(deliveryAdress !=null&deliveryAdress !=null){
			    	 System.out.println(order+"订单生成！");						

//						String url = commitOrder_URL+ "?userId="+ userId+"&recieverAddressId="+recieverAddressId+"&amountPay="+amountPay+"&deliveryCosts="+deliveryCosts+"&paymentMethod="+paymentMethod+"&orderTime="+orderTime+"&deliveryMethod="+deliveryMethod;
			    	 String url = commitOrder_URL+ "?userId="+ userId+"&recieverAddressId="+recieverAddressId+"&amountPay="+amountPay+"&deliveryCosts="+deliveryCosts+"&paymentMethod="+paymentMethod+"&deliveryMethod="+deliveryMethod;
						System.out.println(url+"DDDDD");		
						commitOrder(url);
						Intent intent03 = new Intent();
						intent03.setClass(CommitOrderActivity.this,
								PaymentResultActivity.class);
						startActivity(intent03);
						
			    }else if(deliveryAdress==null & deliveryAdress ==null){
			    	Toast toast=Toast.makeText(CommitOrderActivity.this, "收货地址或支付方式不能为空", 1);
			    	toast.setGravity(Gravity.CENTER, 0, 0);
			    	toast.show();
			    }
			   
				break;
			case R.id.StoresToThe_tv:
				StoresToThe_tv.setBackground(getResources().getDrawable(R.drawable.butrightb));
				deliveryMethod="门店自提";
				break;
			case R.id.sppedDistribution_tv:	
				sppedDistribution_tv.setBackground(getResources().getDrawable(R.drawable.butrightb));
				deliveryMethod="快速配送";
			break;	
			}
		}
	}

	public void commitOrder(final String url) {
		
		new Thread(){
			public void run() {
				try {
					System.out.println("PPPPPPPPPPPP");
					String strJson = HttpUtil.getRequest(url);
					System.out.println(strJson+"sTRONKLJKLJLJKJKLKLJJKL");
					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Integer>(){}.getType();
					Appconstant.orderId = gson.fromJson(strJson, type)+"";
								
					Message message=new Message();
					message.what=2002;
					Bundle bundle=new Bundle();
					bundle.putString("orderId", Appconstant.orderId);
					message.setData(bundle);					
					handler01.sendMessage(message);
				} catch (Exception e) {
					
					e.printStackTrace();
				}	
			};
		}.start();
		
		
	}

}
