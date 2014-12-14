package com.com.activity.personal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.com.FragmentTab;
import com.com.R;
import com.com.Welcome;
import com.com.activity.PersonalFragment;
import com.com.service.UserService;
import com.com.service.UserService.Orders;
import com.com.service.UserService.User;
import com.com.util.HttpUtil;
import com.google.gson.Gson;

public class PersonalLoginActivity extends Activity {
	private EditText username2, password2;
	private Button login;
	private static UserService userService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		userService = Welcome.userService;
		username2 = (EditText) findViewById(R.id.username2);
		password2 = (EditText) findViewById(R.id.password2);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = username2.getText().toString();
				String password = password2.getText().toString();
				String url = UserService.getUrl() + "?operate=login&username="
						+ username + "&password=" + password;
				login(url, username, password);
			}
		});
	}

	public void login(final String url, final String username,
			final String password) {
		new Thread() {
			@Override
			public void run() {
				try {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("username", username);
					map.put("password", password);
					// String strJson = HttpUtil.getRequest(url);
					String strJson = HttpUtil.postRequest(url, map);

					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					// java.lang.reflect.Type type = new
					// com.google.gson.reflect.TypeToken<List<User>>(){}.getType();
					// ArrayList<User> users = gson.fromJson(strJson, type);
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<User>() {
					}.getType();
					User users = gson.fromJson(strJson, type);
					// 把对象 保存到Message
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putParcelable("users", users);
					bundle.putString("username", username);
					bundle.putString("password", password);
					message.setData(bundle);
					handler.sendMessage(message);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {
				Bundle bundle = msg.getData();
				String username = bundle.getString("username");
				String password = bundle.getString("password");
				User user = bundle.getParcelable("users");
				if (user.getUsername().equals(username)
						&& user.getPassword().equals(password)) {
					userService.setUser_id(user.getUser_id());
					userService.setUsername(username);
					userService.setPassword(password);
					userService.setTelephone(user.getTelephone());
					userService.setEmail(user.getEmail());
					userService.setRegion(user.getRegion());
					userService.setStreet_address(user.getStreet_address());
					userService.setPostcode(user.getPostcode());
					userService.setAccount_balance(user.getAccount_balance());
					userService.setAccount_integral(user.getAccount_integral());
					userService.login();
					finish();
				} else {
					showDialog("用户名或密码错误");
				}
			} catch (Exception e) {
				e.printStackTrace();
				showDialog("服务器异常");
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
