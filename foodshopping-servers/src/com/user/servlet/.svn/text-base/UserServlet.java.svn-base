package com.user.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.EmailAddress;

import com.cxl.util.PathTool;
import com.google.gson.Gson;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 3948185951128442127L;
	private String strJson = "";
	private Gson gson = new Gson();
	private Connection connection = null;

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String operate = request.getParameter("operate");
		String id = request.getParameter("user_id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		connection = JdbcUtil.getConnection();
		List<Orders> orders = null;
		List<Commodity> commodity = null;
		Address address = null;
		User user = null;

		try {
			if (operate.equals("regist")) {
				String telephone = request.getParameter("telephone");
				String email = request.getParameter("email");
				String insertSql = "insert into SHOPPING_USERS(user_name,user_password,user_phone,user_email) values(?,?,?,?)";
				Object[] params = new Object[] { username, password, telephone,
						email };
				JdbcUtil.executeUpdate2(connection, insertSql, params);
				user = new User(username, password, telephone, email);
				strJson = gson.toJson(user);
			} else if (operate.equals("login")) {
				user = login(username, password);
				User accounts = SearchAccounts(user.getUser_id());
				User userss = new User(user.getUser_id(), username, password,
						user.getTelephone(), user.getRegion(),
						user.getStreet_address(), user.getPostcode(),
						user.getEmail(), accounts.getAccount_balance(),
						accounts.getAccount_integral());
				strJson = gson.toJson(userss);
			} else if (operate.equals("updateAccounts")) {
				String region = request.getParameter("region");
				String street_address = request.getParameter("street_address");
				String postcode = request.getParameter("postcode");
				String email = request.getParameter("email");
				String sql = "update SHOPPING_USERS set user_name=?, user_region=?, user_street_address=?,  user_postcode=? , user_email=? where user_id=?";
				Object[] params = new Object[] { username, region,
						street_address, postcode, email, id };
				JdbcUtil.executeUpdate2(connection, sql, params);
				user = new User(username, region, street_address, postcode,
						email);
				strJson = gson.toJson(user);
			} else if (operate.equals("all")) {
				String sql = "SELECT * FROM SHOPPING_ORDERS WHERE user_id="
						+ Integer.valueOf(id);
				orders = SearchOrders(sql);
				strJson = gson.toJson(orders);
			} else if (operate.equals("payment")) {
				String sql = "SELECT * FROM SHOPPING_ORDERS WHERE user_id="
						+ Integer.valueOf(id) + " and orders_status='未支付'";
				orders = SearchOrders(sql);
				strJson = gson.toJson(orders);
			} else if (operate.equals("delivery")) {
				String sql = "SELECT * FROM SHOPPING_ORDERS WHERE user_id="
						+ Integer.valueOf(id) + " and orders_status='已支付'";
				orders = SearchOrders(sql);
				strJson = gson.toJson(orders);
			} else if (operate.equals("address")) {
				String address_id = request.getParameter("address_id");
				String sql = "select * from SHOPPING_RECIEVER_ADDRESS where reciever_address_id ="
						+ address_id;
				address = SearchAddress(sql);
				strJson = gson.toJson(address);
			} else if (operate.equals("commodity")) {
				String orders_id = request.getParameter("orders_id");
				String sql = "SELECT  a.* ,b.* from SHOPPING_ORDERS a,SHOPPING_SHOPPING_BASKET b where "
						+ "a.orders_id=" + orders_id + " and b.user_id=" + id;
				commodity = SearchCommodity(sql);
				strJson = gson.toJson(commodity);
			} else if (operate.equals("cateid")) {
				String foodInfo = request.getParameter("cate_id");
				String sql = "SELECT * FROM SHOPPING_CATE WHERE cate_name='"
						+ foodInfo + "'";
				Cate i = SearchCateId(sql);
				strJson = gson.toJson(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JdbcUtil.closeConnection(connection);

		if (user != null) {
			System.out.println(strJson);
		} else if (orders != null) {
			for (Orders orders2 : orders) {
				System.out.println(orders2);
			}
		} else if (commodity != null) {
			for (Commodity commodity2 : commodity) {
				System.out.println(commodity2);
			}

		}

		out.write(strJson);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		this.doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

	public User login2(String querySql, String username, String password)
			throws Exception {
		User user = null;
		Object[] params = new Object[] { username, password };
		Connection connection = JdbcUtil.getConnection();
		ResultSet resultSet = JdbcUtil.executeQuery(connection, querySql,
				params);
		if (null != resultSet) {
			if (resultSet.next()) {
				user = new User(username, password);
			}
		}
		return user;
	}

	public User login(String username, String password) throws Exception {
		User U = null;
		String sql = "SELECT * FROM SHOPPING_USERS WHERE user_name=? AND user_password=?";
		Object[] params = new Object[] { username, password };
		try {
			ResultSet rs = JdbcUtil.executeQuery(connection, sql, params);

			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String username2 = rs.getString(2);
				String password2 = rs.getString(3);
				String telephone = rs.getString(4);
				String region = rs.getString(5);
				String street_address = rs.getString(6);
				String postcode = rs.getString(7);
				String email = rs.getString(8);
				U = new User(user_id, username2, password2, telephone, region,
						street_address, postcode, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return U;
	}

	private User SearchAccounts(int user_id) throws Exception {
		User U = new User();
		String sql = "SELECT * FROM SHOPPING_ACCOUNT WHERE user_id=?";
		Object[] params = new Object[] { user_id };
		try {
			ResultSet rs = JdbcUtil.executeQuery(connection, sql, params);
			while (rs.next()) {
				float account_balance = Float.valueOf(rs.getString(3));
				float account_integral = Float.valueOf(rs.getString(4));
				U = new User(account_balance, account_integral);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return U;
	}

	private List<Orders> SearchOrders(String sql) throws Exception {
		List<Orders> orderss = new ArrayList<Orders>();
		Orders orders = null;
		try {
			ResultSet rs = JdbcUtil.executeQuery(connection, sql);
			while (rs.next()) {
				int orders_id = rs.getInt("orders_id");
				int reciever_address_id = rs.getInt("reciever_address_id");
				String orders_status = rs.getString("orders_status");
				int orders_goods_item_count = rs
						.getInt("orders_goods_item_count");
				float orders_goods_amount = rs.getFloat("orders_goods_amount");
				float orders_mail_charge = rs.getFloat("orders_mail_charge");
				String orders_pay_mode = rs.getString("orders_pay_mode");
				String orders_date = rs.getString("orders_date");
				String orders_mail_mode = rs.getString("orders_mail_mode");
				String orders_bill = rs.getString("orders_bill");
				orders = new Orders(orders_id, reciever_address_id,
						orders_status, orders_goods_item_count,
						orders_goods_amount, orders_mail_charge,
						orders_pay_mode, orders_date, orders_mail_mode,
						orders_bill);
				orderss.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderss;
	}

	private Address SearchAddress(String sql) throws Exception {
		Address address = new Address();
		try {
			ResultSet rs = JdbcUtil.executeQuery(connection, sql);
			while (rs.next()) {
				String reciever_address_name = rs
						.getString("reciever_address_name");
				String reciever_address_region = rs
						.getString("reciever_address_region");
				String reciever_address_address = rs
						.getString("reciever_address_address");
				int reciever_address_postcode = rs
						.getInt("reciever_address_postcode");
				long reciever_address_phone = rs
						.getLong("reciever_address_phone");
				address = new Address(reciever_address_name,
						reciever_address_region, reciever_address_address,
						reciever_address_postcode, reciever_address_phone);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	private List<Commodity> SearchCommodity(String sql) throws Exception {
		List<Commodity> commodity = new ArrayList<Commodity>();
		Commodity commodity2 = null;
		try {
			ResultSet rs = JdbcUtil.executeQuery(connection, sql);
			while (rs.next()) {
				int shoppingCarID = rs.getInt("shopping_basket_id");
				String imagePath = rs.getString("shopping_basket_image");
				String foodInfo = rs.getString("shopping_basket_name");
				String unitPrice = rs.getString("shopping_basket_price");
				int foodCount = rs.getInt("shopping_basket_count");
				commodity2 = new Commodity(shoppingCarID, imagePath, foodInfo,
						unitPrice, foodCount);
				commodity.add(commodity2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commodity;
	}

	private Cate SearchCateId(String sql) throws Exception {
		Cate cate_id = null;
		try {
			ResultSet rs = JdbcUtil.executeQuery(connection, sql);
			while (rs.next()) {
			int	cate_id2 = rs.getInt("cate_id");
			cate_id = new Cate(cate_id2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cate_id;
	}

	public class Cate {
		int cate_id;
		

		public Cate() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Cate(int cate_id) {
			super();
			this.cate_id = cate_id;
		}

		public int getCate_id() {
			return cate_id;
		}

		public void setCate_id(int cate_id) {
			this.cate_id = cate_id;
		}
		
	}
}
