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


public class ShoppingCarInitServlet extends HttpServlet {

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
			String userId=request.getParameter("userId");	
			String sql = "SELECT * FROM SHOPPING_SHOPPING_BASKET WHERE user_id="+userId;
			
			Connection connection = null;
			try {
				// 获取一个连接对象
				connection =JdbcUtil.getConnection();
				// 执行查询
				ResultSet resultSet = JdbcUtil.executeQuery(connection, sql);
				System.out.println("resultSet:"+resultSet);
				shoppingCars = getShoppingCars(resultSet);
				for (ShoppingCar shoppingCar : shoppingCars) {
					System.out.println(shoppingCar+"1111");
				}
				Gson gson=new Gson();
				String strJson=gson.toJson(shoppingCars);
				
				System.out.println(strJson);
				out.write(strJson);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭连接
				JdbcUtil.closeConnection(connection);
				out.flush();
				out.close();
			}
	}
	
	private static ArrayList<ShoppingCar> getShoppingCars(ResultSet resultSet) throws Exception{
		ArrayList<ShoppingCar> shoppingCars = new ArrayList<ShoppingCar>();
		ShoppingCar shoppingCar = null;
		if(null  == resultSet) {
			return shoppingCars;
		}
		while(resultSet !=null && resultSet.next()) {
			int shoppingCarID=resultSet.getInt("shopping_basket_id");
			String imagePath= resultSet.getString("shopping_basket_image");
			String foodInfo = resultSet.getString("shopping_basket_name");		
			String unitPrice= resultSet.getString("shopping_basket_price");
			int foodCount=resultSet.getInt("shopping_basket_count");
			shoppingCar = new ShoppingCar(shoppingCarID, false, imagePath, foodInfo, unitPrice, "-", foodCount, "+");
			shoppingCars.add(shoppingCar);
		}
		return shoppingCars;
	}
}
