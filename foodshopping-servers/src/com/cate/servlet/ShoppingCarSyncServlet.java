/**
 * 
 */
package com.cate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javabean.ShoppingCar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.user.servlet.JdbcUtil;


public class ShoppingCarSyncServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArrayList<ShoppingCar> shoppingCars;
	
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
			String operate = request.getParameter("operate");
			Connection connection = null;
			ShoppingCar shoppingCar;
			
			try {
			if(operate.equals("decrease")) {
				int shoppingCarID = Integer.parseInt(request.getParameter("shoppingCarID"));
				int foodCount = Integer.parseInt(request.getParameter("foodCount"));			
				if (foodCount == 1) {
				return;
				}
				foodCount--;				
				// 获取一个连接对象
				connection = JdbcUtil.getConnection();
				String sql = "UPDATE SHOPPING_SHOPPING_BASKET SET shopping_basket_count="+foodCount+"WHERE shopping_basket_id="+shoppingCarID;
				String querySql = "SELECT * FROM SHOPPING_SHOPPING_BASKET where shopping_basket_id="+shoppingCarID;	
					// 执行更新
					int result = JdbcUtil.executeUpdate(connection, sql);
					System.out.println(result);
					// 执行查询
					ResultSet resultSet = JdbcUtil.executeQuery(connection, querySql);
					System.out.println("resultSet:"+resultSet);
					shoppingCar = getShoppingCar(resultSet);
//					for (ShoppingCar shoppingCar : shoppingCars) {
//						System.out.println(shoppingCar+"1111");
//					}				
					Gson gson=new Gson();
					String strJson=gson.toJson(shoppingCar);					
					System.out.println(strJson);
					out.write(strJson);
			}else if(operate.equals("increase")){
				int shoppingCarID = Integer.parseInt(request.getParameter("shoppingCarID"));
				int foodCount = Integer.parseInt(request.getParameter("foodCount"));
				foodCount++;
				// 获取一个连接对象
				connection = JdbcUtil.getConnection();
				String sql = "UPDATE SHOPPING_SHOPPING_BASKET SET shopping_basket_count="+foodCount+"WHERE shopping_basket_id="+shoppingCarID;
				String querySql = "SELECT * FROM SHOPPING_SHOPPING_BASKET where shopping_basket_id="+shoppingCarID;	
					// 执行更新
					int result = JdbcUtil.executeUpdate(connection, sql);
//					System.out.println(result);
					// 执行查询
					ResultSet resultSet = JdbcUtil.executeQuery(connection, querySql);
					System.out.println("resultSet:"+resultSet);
					shoppingCar = getShoppingCar(resultSet);			
					Gson gson=new Gson();
					String strJson=gson.toJson(shoppingCar);					
					System.out.println(strJson);
					out.write(strJson);			
			}else if(operate.equals("delete")){
				int shoppingCarID = Integer.parseInt(request.getParameter("shoppingCarID"));
				int foodCount = Integer.parseInt(request.getParameter("foodCount"));

				// 获取一个连接对象
				connection = JdbcUtil.getConnection();
				String sql = "delete from SHOPPING_SHOPPING_BASKET WHERE shopping_basket_id="+shoppingCarID;
				String querySql = "SELECT * FROM SHOPPING_SHOPPING_BASKET where shopping_basket_id="+shoppingCarID;	
					// 执行查询
				ResultSet resultSet = JdbcUtil.executeQuery(connection, querySql);
				System.out.println("resultSet:"+resultSet);
				shoppingCar = getShoppingCar(resultSet);	
				//执行更新
				int result = JdbcUtil.executeUpdate(connection, sql);
				System.out.println(result);
							
					Gson gson=new Gson();
					String strJson=gson.toJson(shoppingCar);					
					System.out.println(strJson);
					out.write(strJson);			
			}
				} catch (Exception e) {					
					e.printStackTrace();							
				}finally {
				// 关闭连接
				JdbcUtil.closeConnection(connection);
				out.flush();
				out.close();
			} 
			
			
	}

	private static ShoppingCar getShoppingCar(ResultSet resultSet) throws Exception{
		ShoppingCar shoppingCar = null;
		if(null  == resultSet) {
			return shoppingCar;
		}
		while(resultSet !=null && resultSet.next()) {
			int shoppingCarID=resultSet.getInt("shopping_basket_id");
			String imagePath= resultSet.getString("shopping_basket_image");
			String foodInfo = resultSet.getString("shopping_basket_name");		
			String unitPrice= resultSet.getString("shopping_basket_price");
			int foodCount=resultSet.getInt("shopping_basket_count");
			shoppingCar = new ShoppingCar(shoppingCarID, false, imagePath, foodInfo, unitPrice, "-", foodCount, "+");		
		}
		return shoppingCar;
	}
}
