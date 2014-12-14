/**
 * 
 */
package com.cxl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.user.servlet.JdbcUtil;


public class CommitOrderServlet extends HttpServlet {
	public static final String TAG="CommitOrderServlet";
	private static final long serialVersionUID = 1L;
	private static ArrayList<Address> addresss;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String encoding = "UTF-8";
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("html/text;charset=UTF-8");

		PrintWriter out=response.getWriter();
		int ordersId=53431324;
		int userId=Integer.parseInt(request.getParameter("userId"));
		int recieverAddressId=Integer.parseInt(request.getParameter("recieverAddressId"));
		String ordersStatus="未支付";
		int goodsCount=40;
		Double amountPay=Double.valueOf(StrConvert.toCn(request.getParameter("amountPay")).substring(1));
		Double deliveryCosts=Double.valueOf((StrConvert.toCn(request.getParameter("deliveryCosts"))).substring(1));
		String paymentMethod=StrConvert.toCn(request.getParameter("paymentMethod"));		
//		String orderTime=StrConvert.toCn(request.getParameter("orderTime"));
		SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");       
		Date  curDate=   new Date(System.currentTimeMillis());//获取当前时间
		String  orderTime = formatter.format(curDate); 
		String deliveryMethod=StrConvert.toCn(request.getParameter("deliveryMethod"));
		String ordersBill="0312";
		System.out.println(deliveryMethod+"111111111111111"+amountPay+userId+recieverAddressId+orderTime);
		String insertSql="INSERT INTO SHOPPING_ORDERS( USER_ID, RECIEVER_ADDRESS_ID,ORDERS_STATUS,ORDERS_GOODS_ITEM_COUNT,ORDERS_GOODS_AMOUNT,ORDERS_MAIL_CHARGE,ORDERS_PAY_MODE,ORDERS_DATE,ORDERS_MAIL_MODE,ORDERS_BILL) VALUES(" +userId+","+recieverAddressId+",'"+ ordersStatus+"',"+goodsCount+","+amountPay+","+deliveryCosts+",'"+paymentMethod+"',to_timestamp('"+orderTime+"','yyyy-MM-dd HH24:mi:ss:ff'),'"+deliveryMethod+"','"+ordersBill+"')";	   
//		String insertSql="INSERT INTO SHOPPING_ORDERS( USER_ID, RECIEVER_ADDRESS_ID,ORDERS_STATUS,ORDERS_GOODS_ITEM_COUNT,ORDERS_GOODS_AMOUNT,ORDERS_MAIL_CHARGE,ORDERS_PAY_MODE,ORDERS_DATE,ORDERS_MAIL_MODE,ORDERS_BILL) VALUES(42,21,'未支付',40,7.85,8.00,'支付宝即时到账',to_timestamp('2014-11-0821:12:43','yyyy-MM-dd HH24:mi:ss:ff' ),'快速配送','0312')";	   

//		String insertSql="INSERT INTO SHOPPING_ORDERS(USER_ID, " +
//				"RECIEVER_ADDRESS_ID,ORDERS_STATUS,ORDERS_GOODS_ITEM_COUNT," +
//				"ORDERS_GOODS_AMOUNT,ORDERS_MAIL_CHARGE,ORDERS_PAY_MODE," +
//				"ORDERS_DATE,ORDERS_MAIL_MODE,ORDERS_BILL) VALUES(?,?,?,?,?,?,?,?,?,?);";	   
//		Object[] pramas=new Object[]{userId,recieverAddressId,ordersStatus,goodsCount,amountPay,deliveryCosts,paymentMethod,null,deliveryMethod,ordersBill};
		System.out.println("insertSql:"+insertSql);                                                                                                                                                                                                                                                                                                                                                                 
		String Sql= "select *from  SHOPPING_ORDERS where ORDERS_DATE=to_timestamp('"+orderTime+"','yyyy-MM-dd HH24:mi:ss:ff')";
		
		System.out.println("sql:"+Sql);
			Connection connection = null;	
			try {
				// 获取一个连接对象
				connection =JdbcUtil.getConnection();
				// 执行更新
				int resultSet = JdbcUtil.executeUpdate(connection, insertSql);
				ResultSet resultSet01 =JdbcUtil.executeQuery(connection, Sql);
				ordersId=getId(resultSet01);
				Gson gson=new Gson();
				String strJson=gson.toJson(ordersId);				
				System.out.println(strJson+"999999999");
				out.write(strJson);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				// 关闭连接
				JdbcUtil.closeConnection(connection);
				out.flush();
				out.close();
			}
			}

	private int getId(ResultSet resultSet01) throws Exception{
		int ordersId01=0;
		if(null  == resultSet01) {			
			return ordersId01;
		}
		while(resultSet01 !=null && resultSet01.next()) {
			ordersId01=resultSet01.getInt("ORDERS_ID");									
		}
		return ordersId01;
	}

	private static ArrayList<Address>  getAddresss(ResultSet resultSet) throws Exception{
		ArrayList<Address> addresss=new ArrayList<Address>();
		Address address = null;

		if(null  == resultSet) {
		
			return null;
		}
		while(resultSet !=null && resultSet.next()) {
			int addressId=resultSet.getInt("reciever_address_id");
			String addressName= resultSet.getString("reciever_address_name");
			String addressRegion = resultSet.getString("reciever_address_region");		
			String addressAddress= resultSet.getString("reciever_address_address");
			int addressPostcode=resultSet.getInt("reciever_address_postcode");
			long addressPhone=resultSet.getLong("reciever_address_phone");
			address = new Address(addressId, false, false, addressName, addressRegion, addressAddress, addressPostcode, addressPhone, false);
			//System.out.println("address:"+address);
			addresss.add(address);
		}
		return addresss;
	}
}
