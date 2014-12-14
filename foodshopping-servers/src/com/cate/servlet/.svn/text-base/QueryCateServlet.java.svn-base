package com.cate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javabean.Cate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.user.servlet.JdbcUtil;

public class QueryCateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("html/txt");
		PrintWriter outer=response.getWriter();
		String strJson="";
		Gson gson=new Gson();
		Connection connection=null;
		ResultSet resultSet=null;
		ArrayList<Cate> cateList=new ArrayList<Cate>();
		try {
			connection=JdbcUtil.getConnection();
			String querySQL="select * from shopping_cate";
			resultSet=JdbcUtil.executeQuery(connection, querySQL);
			cateList=getCateList(cateList,resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(connection);
		}
		
		strJson=gson.toJson(cateList);
		System.out.println("查询商品："+strJson);
		outer.write(strJson);
		outer.flush();
		outer.close();
	}
	private ArrayList<Cate> getCateList(ArrayList<Cate> cateList, ResultSet resultSet) throws Exception {
		if (resultSet==null) {
			cateList=null;
		}
		while (resultSet!=null&&resultSet.next()) {
			int cate_id=resultSet.getInt("cate_id");
			String cate_name=resultSet.getString("cate_name");
			int cate_inventory=resultSet.getInt("cate_inventory");
			int cate_sale_count=resultSet.getInt("cate_sale_count");
			String cate_description=resultSet.getString("cate_description"); 
			float cate_oldprice=resultSet.getInt("cate_oldprice");
			float cate_price=resultSet.getInt("cate_price");
			int cate_image_id=resultSet.getInt("cate_image_id");
			int category_id=resultSet.getInt("category_id");
			int flavor_category_id=resultSet.getInt("flavor_category_id");
			String cate_sale_info=resultSet.getString("cate_sale_info");
			Cate cate=new Cate(cate_id, cate_name, cate_inventory, cate_sale_count,
					cate_description, cate_oldprice, cate_price, cate_image_id, 
					category_id, flavor_category_id, cate_sale_info);
			cateList.add(cate);
		}
		return cateList;
	}
}



