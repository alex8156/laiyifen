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

import oracle.jdbc.proxy.annotation.GetProxy;

import com.google.gson.Gson;
import com.user.servlet.JdbcUtil;


public class AddressOperateServlet extends HttpServlet {
	public static final String TAG="AddressOperateServlet";
	private static final long serialVersionUID = 1L;
	private static ArrayList<Address> addresss;
	private int result;
	
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
		String operate=request.getParameter("operate");
		String AddressId=request.getParameter("AddressId");
		System.out.println(AddressId);
		
			Connection connection = null;	
			try {
				// 获取一个连接对象
				connection =JdbcUtil.getConnection();
				if(operate.equals("delete")){
					String deleteSql = "delete FROM SHOPPING_RECIEVER_ADDRESS WHERE reciever_address_id="+AddressId;		
					result=JdbcUtil.executeUpdate(connection,deleteSql);
				
				}else if(operate.equals("Modify")){
					String addressName=request.getParameter("addressName");
					String addressRegion=request.getParameter("addressRegion");
					String addressAddress=request.getParameter("addressAddress");
					String addressPostcode=request.getParameter("addressPostcode");
					String addressPhone=request.getParameter("addressPhone");
					String updateSql="update SHOPPING_RECIEVER_ADDRESS set reciever_address_name="+addressName+",set reciever_address_region="+addressRegion
							+",set reciever_address_address="+addressAddress+",set reciever_address_postcode="+addressPostcode+
							",set reciever_address_phone="+addressPhone+"where AddressId="+AddressId;					
					// 执行查询
					result = JdbcUtil.executeUpdate(connection, updateSql);								
				}else if(operate.equals("add")){
					String addressName=request.getParameter("addressName");
					String addressRegion=request.getParameter("addressRegion");
					String addressAddress=request.getParameter("addressAddress");
					String addressPostcode=request.getParameter("addressPostcode");
					String addressPhone=request.getParameter("addressPhone");
					String userId=request.getParameter("userId");
					
					 String updateSql="INSERT INTO SHOPPING_RECIEVER_ADDRESS(user_id, reciever_address_name, reciever_address_region,reciever_address_address,reciever_address_postcode,reciever_address_phone) VALUES("
					+userId+",'"+ addressName+"','"+addressRegion+"','"+addressAddress+"',"+addressPostcode+","+addressPhone+")";
					 System.out.println("新增地址sql："+updateSql);
					// 执行查询
					result = JdbcUtil.executeUpdate(connection, updateSql); 
				}
				System.out.println(result);	
				Gson gson=new Gson();
				String strJson=gson.toJson(result);				
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
