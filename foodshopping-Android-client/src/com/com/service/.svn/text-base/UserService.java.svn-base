package com.com.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.com.util.BaseUrl;
import com.com.util.HttpUtil;
import com.com.util.Manage;
import com.google.gson.Gson;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class UserService extends Service {
	public static final String BASE_URL = BaseUrl.BASE_URL;
	public static final String URL = BASE_URL + "/servlet/UserServlet";
	public static final String SERVER_IAMGES_URL = BASE_URL
			+ "/DownLoadImageServlet";
	public static final String DOWN_IAMGE_URL = BASE_URL
			+ "/DownLoadImageServlet";
	private String username;// 用户名
	private String password;// 密码
	private int user_id;// 用户ID
	private String telephone;// 手机
	private String email;// 邮箱
	private String region;// 地区
	private String street_address;// 街道地址
	private String postcode;// 邮编
	float account_balance;// 余额
	float account_integral;// 积分

	@Override
	public void onCreate() {
		Manage.getInstance().addService(this);
		super.onCreate();
	}

	Binder binder;
	private Handler handler;
	public ArrayList<Orders> orders;
	public ArrayList<Commodity> commodits;
	public Address address;
	public User user;
	public int position;

	@Override
	public IBinder onBind(Intent intent) {
		if (null == binder) {
			binder = new MyBinder();
		}
		return binder;
	}

	// 继承Binder 的类
	public class MyBinder extends Binder {
		// 自定义一个 获取服务的公共类
		public UserService getService() {
			return UserService.this;
		}
	}

	/**
	 * 登录
	 */
	public void login() {
		Message message = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("user", "login");
		bundle.putString("username", username);
		bundle.putFloat("balance", account_balance);
		bundle.putFloat("integral", account_integral);
		message.setData(bundle);
		handler.sendMessage(message);
	}

	/**
	 * 注销
	 */
	public void cancel() {
		Message message = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("user", "cancel");
		message.setData(bundle);
		handler.sendMessage(message);
	}

	public static String getBaseUrl() {
		return BASE_URL;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getUrl() {
		return URL;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public float getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(float account_balance) {
		this.account_balance = account_balance;
	}

	public float getAccount_integral() {
		return account_integral;
	}

	public void setAccount_integral(float account_integral) {
		this.account_integral = account_integral;
	}

	public ArrayList<Orders> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Orders> orders) {
		this.orders = orders;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<Commodity> getCommodits() {
		return commodits;

	}

	public void setCommodits(ArrayList<Commodity> commodits) {
		this.commodits = commodits;
	}

	/**
	 * 用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	public class User implements Parcelable {
		private String username;// 用户名
		private String password;// 密码
		private int user_id;// 用户ID
		private String telephone;// 手机
		private String email;// 邮箱
		private String region;// 地区
		private String street_address;// 街道地址
		private String postcode;// 邮编
		float account_balance;// 余额
		float account_integral;// 积分
		int[] ii;
		String[] ss;

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		// 对象在Parcelable中的编码格式
		@Override
		public int describeContents() {
			return 0;
		}

		// 把JavaBean中的数据写到Parcel
		@Override
		public void writeToParcel(Parcel parcel, int flags) {
			parcel.writeInt(user_id);
			parcel.writeString(username);
			parcel.writeString(password);
			parcel.writeString(telephone);
			parcel.writeString(email);
			parcel.writeString(region);
			parcel.writeString(street_address);
			parcel.writeString(postcode);
			parcel.writeFloat(account_balance);
			parcel.writeFloat(account_integral);

		}

		// 添加静态成员， 名称是CREATOR,该对象 实现了 Parcelable.Creator接口
		public final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
			@Override
			public User createFromParcel(Parcel source) {
				return new User(source.readInt(), source.readString(),
						source.readString(), source.readString(),
						source.readString(), source.readString(),
						source.readString(), source.readString(),
						source.readFloat(), source.readFloat());
			}

			@Override
			public User[] newArray(int size) {
				return new User[size];
			}
		};

		public User(int user_id, String username, String password,
				String telephone, String email, String region,
				String street_address, String postcode, float account_balance,
				float account_integral) {
			super();
			this.user_id = user_id;
			this.username = username;
			this.password = password;
			this.telephone = telephone;
			this.email = email;
			this.region = region;
			this.street_address = street_address;
			this.postcode = postcode;
			this.account_balance = account_balance;
			this.account_integral = account_integral;

		}

		@Override
		public String toString() {
			return "User [用户ID=" + user_id + ", 用户名=" + username + ", 密码="
					+ password + ", 手机=" + telephone + ", 邮箱=" + email
					+ ", 地区=" + region + ", 街道地址=" + street_address + ", 邮编="
					+ postcode + ", 积分=" + account_balance + ", 余额="
					+ account_integral + "]";
		}

		public int getUser_id() {
			return user_id;
		}

		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getStreet_address() {
			return street_address;
		}

		public void setStreet_address(String street_address) {
			this.street_address = street_address;
		}

		public String getPostcode() {
			return postcode;
		}

		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}

		public float getAccount_balance() {
			return account_balance;
		}

		public void setAccount_balance(float account_balance) {
			this.account_balance = account_balance;
		}

		public float getAccount_integral() {
			return account_integral;
		}

		public void setAccount_integral(float account_integral) {
			this.account_integral = account_integral;
		}
	}

	/**
	 * 订单
	 * 
	 * @author Administrator
	 * 
	 */
	public class Orders implements Parcelable {
		int orders_id;// 订单号
		int reciever_address_id;// 地址ID
		String orders_status;// 状态（已支付、未支付）
		int orders_goods_item_count;// 商品数量
		float orders_goods_amount;// 商品总额
		float orders_mail_charge;// 邮费
		String orders_pay_mode;// 支付模式
		String orders_date;//
		String orders_mail_mode;// 邮递方式
		String orders_bill;// 发票抬头

		public Orders() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Orders(int orders_id, int reciever_address_id,
				String orders_status, int orders_goods_item_count,
				float orders_goods_amount, float orders_mail_charge,
				String orders_pay_mode, String orders_date,
				String orders_mail_mode, String orders_bill) {
			super();
			this.orders_id = orders_id;
			this.reciever_address_id = reciever_address_id;
			this.orders_status = orders_status;
			this.orders_goods_item_count = orders_goods_item_count;
			this.orders_goods_amount = orders_goods_amount;
			this.orders_mail_charge = orders_mail_charge;
			this.orders_pay_mode = orders_pay_mode;
			this.orders_date = orders_date;
			this.orders_mail_mode = orders_mail_mode;
			this.orders_bill = orders_bill;
		}

		public String toString() {
			return "Orders2 [订单ID=" + orders_id + ",地址ID+"
					+ reciever_address_id + ", 状态=" + orders_status + ", 数量="
					+ orders_goods_item_count + ", 总额=" + orders_goods_amount
					+ ", 邮费=" + orders_mail_charge + ", 支付模式="
					+ orders_pay_mode + ", 时间=" + orders_date + ", 邮递方式="
					+ orders_mail_mode + ", 发票抬头=" + orders_bill + "]";
		}

		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(orders_id);
			dest.writeInt(reciever_address_id);
			dest.writeString(orders_status);
			dest.writeInt(orders_goods_item_count);
			dest.writeFloat(orders_goods_amount);
			dest.writeFloat(orders_mail_charge);
			dest.writeString(orders_pay_mode);
			dest.writeString(orders_date);
			dest.writeString(orders_mail_mode);
			dest.writeString(orders_bill);
		}

		// 添加静态成员， 名称是CREATOR,该对象 实现了 Parcelable.Creator接口
		public final Parcelable.Creator<Orders> CREATOR = new Parcelable.Creator<Orders>() {

			@Override
			public Orders createFromParcel(Parcel source) {
				return new Orders(source.readInt(), source.readInt(),
						source.readString(), source.readInt(),
						source.readInt(), source.readInt(),
						source.readString(), source.readString(),
						source.readString(), source.readString());
			}

			@Override
			public Orders[] newArray(int size) {
				return new Orders[size];
			}
		};

		public int getOrders_id() {
			return orders_id;
		}

		public void setOrders_id(int orders_id) {
			this.orders_id = orders_id;
		}

		public int getReciever_address_id() {
			return reciever_address_id;
		}

		public void setReciever_address_id(int reciever_address_id) {
			this.reciever_address_id = reciever_address_id;
		}

		public String getOrders_status() {
			return orders_status;
		}

		public void setOrders_status(String orders_status) {
			this.orders_status = orders_status;
		}

		public int getOrders_goods_item_count() {
			return orders_goods_item_count;
		}

		public void setOrders_goods_item_count(int orders_goods_item_count) {
			this.orders_goods_item_count = orders_goods_item_count;
		}

		public float getOrders_goods_amount() {
			return orders_goods_amount;
		}

		public void setOrders_goods_amount(float orders_goods_amount) {
			this.orders_goods_amount = orders_goods_amount;
		}

		public float getOrders_mail_charge() {
			return orders_mail_charge;
		}

		public void setOrders_mail_charge(float orders_mail_charge) {
			this.orders_mail_charge = orders_mail_charge;
		}

		public String getOrders_pay_mode() {
			return orders_pay_mode;
		}

		public void setOrders_pay_mode(String orders_pay_mode) {
			this.orders_pay_mode = orders_pay_mode;
		}

		public String getOrders_date() {
			return orders_date;
		}

		public void setOrders_date(String orders_date) {
			this.orders_date = orders_date;
		}

		public String getOrders_mail_mode() {
			return orders_mail_mode;
		}

		public void setOrders_mail_mode(String orders_mail_mode) {
			this.orders_mail_mode = orders_mail_mode;
		}

		public String getOrders_bill() {
			return orders_bill;
		}

		public void setOrders_bill(String orders_bill) {
			this.orders_bill = orders_bill;
		}

	}

	/**
	 * 地址
	 * 
	 * @author Administrator
	 * 
	 */
	public class Address implements Parcelable {
		String reciever_address_name;// 收件人
		String reciever_address_region;// 地区
		String reciever_address_address;// 街道地址
		int reciever_address_postcode;// 邮编
		long reciever_address_phone;// 收件人电话

		public Address() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Address(String reciever_address_name,
				String reciever_address_region,
				String reciever_address_address, int reciever_address_postcode,
				long reciever_address_phone) {
			super();
			this.reciever_address_name = reciever_address_name;
			this.reciever_address_region = reciever_address_region;
			this.reciever_address_address = reciever_address_address;
			this.reciever_address_postcode = reciever_address_postcode;
			this.reciever_address_phone = reciever_address_phone;
		}

		@Override
		public String toString() {
			return "Address [收件人=" + reciever_address_name + ", 地区="
					+ reciever_address_region + ", 街道地址="
					+ reciever_address_address + ", 邮编="
					+ reciever_address_postcode + ", 收件人电话="
					+ reciever_address_phone + "]";
		}

		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(reciever_address_name);
			dest.writeString(reciever_address_region);
			dest.writeString(reciever_address_address);
			dest.writeInt(reciever_address_postcode);
			dest.writeLong(reciever_address_phone);
		}

		// 添加静态成员， 名称是CREATOR,该对象 实现了 Parcelable.Creator接口
		public final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {

			@Override
			public Address createFromParcel(Parcel source) {
				return new Address(source.readString(), source.readString(),
						source.readString(), source.readInt(),
						source.readLong());
			}

			@Override
			public Address[] newArray(int size) {
				return new Address[size];
			}
		};

		public String getReciever_address_name() {
			return reciever_address_name;
		}

		public void setReciever_address_name(String reciever_address_name) {
			this.reciever_address_name = reciever_address_name;
		}

		public String getReciever_address_region() {
			return reciever_address_region;
		}

		public void setReciever_address_region(String reciever_address_region) {
			this.reciever_address_region = reciever_address_region;
		}

		public String getReciever_address_address() {
			return reciever_address_address;
		}

		public void setReciever_address_address(String reciever_address_address) {
			this.reciever_address_address = reciever_address_address;
		}

		public int getReciever_address_postcode() {
			return reciever_address_postcode;
		}

		public void setReciever_address_postcode(int reciever_address_postcode) {
			this.reciever_address_postcode = reciever_address_postcode;
		}

		public long getReciever_address_phone() {
			return reciever_address_phone;
		}

		public void setReciever_address_phone(long reciever_address_phone) {
			this.reciever_address_phone = reciever_address_phone;
		}

	}

	/**
	 * 购物蓝
	 * 
	 * @author Administrator
	 * 
	 */
	public class Commodity implements Parcelable {
		int shoppingCarID;// 购物蓝ID
		String imagePath; // 食物图片
		String foodInfo;// 食物信息
		String unitPrice;// 单价
		int foodCount;// 食物数量

		public Commodity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Commodity(int shoppingCarID, String imagePath, String foodInfo,
				String unitPrice, int foodCount) {
			super();
			this.shoppingCarID = shoppingCarID;
			this.imagePath = imagePath;
			this.foodInfo = foodInfo;
			this.unitPrice = unitPrice;
			this.foodCount = foodCount;
		}

		@Override
		public String toString() {
			return "Commodit [购物蓝ID=" + shoppingCarID + ", 食物图片=" + imagePath
					+ ", 食物信息=" + foodInfo + ", 单价=" + unitPrice + ", 食物数量="
					+ foodCount + "]";
		}

		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(shoppingCarID);
			dest.writeString(imagePath);
			dest.writeString(foodInfo);
			dest.writeString(unitPrice);
			dest.writeInt(foodCount);
		}

		// 添加静态成员， 名称是CREATOR,该对象 实现了 Parcelable.Creator接口
		public final Parcelable.Creator<Commodity> CREATOR = new Parcelable.Creator<Commodity>() {

			@Override
			public Commodity createFromParcel(Parcel source) {
				return new Commodity(source.readInt(), source.readString(),
						source.readString(), source.readString(),
						source.readInt());
			}

			@Override
			public Commodity[] newArray(int size) {
				return new Commodity[size];
			}
		};

		public int getShoppingCarID() {
			return shoppingCarID;
		}

		public void setShoppingCarID(int shoppingCarID) {
			this.shoppingCarID = shoppingCarID;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public String getFoodInfo() {
			return foodInfo;
		}

		public void setFoodInfo(String foodInfo) {
			this.foodInfo = foodInfo;
		}

		public String getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(String unitPrice) {
			this.unitPrice = unitPrice;
		}

		public int getFoodCount() {
			return foodCount;
		}

		public void setFoodCount(int foodCount) {
			this.foodCount = foodCount;
		}
	}

	public class Cate implements Parcelable {
		int cate_id;

		public Cate() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Cate(int cate_id) {
			super();
			this.cate_id = cate_id;
		}

		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(cate_id);

		}

		// 添加静态成员， 名称是CREATOR,该对象 实现了 Parcelable.Creator接口
		public final Parcelable.Creator<Cate> CREATOR = new Parcelable.Creator<Cate>() {

			@Override
			public Cate createFromParcel(Parcel source) {
				return new Cate(source.readInt());
			}

			@Override
			public Cate[] newArray(int size) {
				return new Cate[size];
			}
		};

		public int getCate_id() {
			return cate_id;
		}

		public void setCate_id(int cate_id) {
			this.cate_id = cate_id;
		}

	}

}
