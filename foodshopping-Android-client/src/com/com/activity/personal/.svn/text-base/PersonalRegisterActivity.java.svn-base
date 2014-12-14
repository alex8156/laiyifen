package com.com.activity.personal;


import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.com.R;
import com.com.service.UserService;
import com.com.service.UserService.User;
import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.google.gson.Gson;

public class PersonalRegisterActivity extends Activity{
	private EditText usernameText,passwordText,confirmPasswordText,telephoneText,emailText;
	private Button registers;
	public static final String BASE_URL = BaseUrl.BASE_URL;
	public static final String REGIST_URL = BASE_URL + "/servlet/UserServlet";
	public static final String LOGIN_URL = BASE_URL + "/servlet/UserServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		usernameText = (EditText)findViewById(R.id.username);
		passwordText = (EditText)findViewById(R.id.password); 
		confirmPasswordText = (EditText)findViewById(R.id.confirmPassword);
		telephoneText = (EditText)findViewById(R.id.telephone); 
		emailText = (EditText)findViewById(R.id.email); 
		registers = (Button)findViewById(R.id.registers);
		registers.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				String username = usernameText.getText().toString();
				String password = passwordText.getText().toString();
				String confirmPassword = confirmPasswordText.getText().toString();
				String telephone = telephoneText.getText().toString();
				String email = emailText.getText().toString();
				String url = UserService.getUrl() + "?operate=regist&username=" + username + "&password=" + password
						+ "&telephone=" + telephone+ "&email=" + email;
					if (password.equals(confirmPassword)) {
						if (!username.equals("")||!telephone.equals("")||!email.equals("")) {
							if (usernameText.getText().length()<=20&&usernameText.getText().length()>=6&&
								passwordText.getText().length()<=20&&passwordText.getText().length()>=6&&
								telephoneText.getText().length()==11&&telephone.matches(telephone)&&
								email.lastIndexOf("@")>0) {
								regist(url, username, password);
										} else {
									Toast.makeText(getApplicationContext(), "输入格式不对", Toast.LENGTH_SHORT).show(); 
									}
								}else {								
						  Toast.makeText(getApplicationContext(), "输入内容不能为空", Toast.LENGTH_SHORT).show(); 
							}	
						}else {
					Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();  
					}
			}	
		});	
	}
	public  void regist(final String url,final String username,final String  password) {
		
		new Thread(){
			@Override
			public void run() {
				try {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("username", username);
					map.put("password", password);
//					String strJson = HttpUtil.getRequest(url);
					String strJson = HttpUtil.postRequest(url, map);
					// 把json字符串 转换成 java对象
					Gson gson = new Gson();
					java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<User>(){}.getType();
					User user = gson.fromJson(strJson, type);

					// 把对象 保存到Message
					Message message = new Message();
					Bundle bundle = new Bundle();
					// 第一种 传递基本类型
	//						bundle.putString("username", user.getUsername());
	//						bundle.putString("password", user.getPassword());
					
					// 第二种 传递 自定义对象, 这里的user对象 必须是 一个 实现 Parcelable接口的bean
					bundle.putParcelable("user", user);	
					message.setData(bundle);
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
   
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				Bundle bundle = msg.getData();
				User user1 = bundle.getParcelable("user");
				showDialog("注册成功！ " + user1.getUsername() +  "\n");

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