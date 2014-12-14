package com.com.activity.personal;

import java.util.HashMap;

import com.com.R;
import com.com.Welcome;
import com.com.service.UserService;
import com.com.service.UserService.User;
import com.com.util.HttpUtil;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class AccountsUpdataActivity extends Activity {
	private EditText name, regions, address, zip, emails;
	private TextView ucard, mobile, keep;
	private UserService userService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_detail);
		userService = Welcome.userService;
		name = (EditText) findViewById(R.id.name);
		regions = (EditText) findViewById(R.id.region);
		address = (EditText) findViewById(R.id.address);
		zip = (EditText) findViewById(R.id.zip);
		emails = (EditText) findViewById(R.id.email);
		ucard = (TextView) findViewById(R.id.ucard);
		mobile = (TextView) findViewById(R.id.mobile);
		keep = (TextView) findViewById(R.id.keep);

		ucard.setText(String.valueOf(userService.getUser_id()));
		name.setText(userService.getUsername());
		mobile.setText(userService.getTelephone());
		regions.setText(userService.getRegion());
		address.setText(userService.getStreet_address());
		zip.setText(userService.getPostcode());
		emails.setText(userService.getEmail());

		keep.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String user_id = String.valueOf(userService.getUser_id());
				String username = name.getText().toString();
				String region = regions.getText().toString();
				String street_address = address.getText().toString();
				String postcode = zip.getText().toString();
				String email = emails.getText().toString();
				String url = UserService.getUrl()
						+ "?operate=updateAccounts&user_id=" + user_id
						+ "&username=" + username + "&region=" + region
						+ "&street_address=" + street_address + "&postcode="
						+ postcode + "&email=" + email;
				updateAccounts(url, user_id, username, region, street_address,
						postcode, email);
			}
		});

	}

	public void updateAccounts(final String url, final String user_id,
			final String username, final String region,
			final String street_address, final String postcode,
			final String email) {
		new Thread() {
			@Override
			public void run() {
				try {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("user_id", user_id);
					map.put("username", username);
					map.put("region", region);
					map.put("street_address", street_address);
					map.put("postcode", postcode);
					map.put("email", email);
					// String strJson = HttpUtil.getRequest(url);
					String strJson = HttpUtil.postRequest(url, map);
					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<User>() {
					}.getType();
					User u = gson.fromJson(strJson, type);
					// 把对象 保存到Message
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putParcelable("user", u);
					message.setData(bundle);
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
					showDialog("服务器异常");
				}
			}
		}.start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {
				Bundle bundle = msg.getData();
				User u = bundle.getParcelable("user");
				userService.setUsername(u.getUsername());
				userService.setEmail(u.getEmail());
				userService.setRegion(u.getRegion());
				userService.setStreet_address(u.getStreet_address());
				userService.setPostcode(u.getPostcode());
				finish();
			} catch (Exception e) {
				e.printStackTrace();
				showDialog("修改失败");
			}

		};
	};

	public void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		builder.create().show();
	}
}
