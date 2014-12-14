/**
 * 
 */
package com.cxl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.user.servlet.JdbcUtil;


public class AddAddressServlet extends HttpServlet {
	public static final String TAG="AddAddressServlet";
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
		response.setContentType("html/txt");
		
		PrintWriter out=response.getWriter();
		String userId=request.getParameter("userId");
		System.out.println(userId);
		String sql = "SELECT * FROM SHOPPING_RECIEVER_ADDRESS WHERE user_id="+userId;		
			Connection connection = null;	
			try {
				// 获取一个连接对象
				connection =JdbcUtil.getConnection();
				// 执行查询
				ResultSet resultSet = JdbcUtil.executeQuery(connection, sql);
				addresss = getAddresss(resultSet);
				Gson gson=new Gson();
				String strJson=gson.toJson(addresss);				
				System.out.println(strJson);
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
