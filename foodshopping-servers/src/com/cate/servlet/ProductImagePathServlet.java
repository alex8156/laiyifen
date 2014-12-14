package com.cate.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javabean.ShoppingCateImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxl.util.UserService;
import com.google.gson.Gson;


public class ProductImagePathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		int cate_image_id = Integer.parseInt(request.getParameter("cate_image_id"));  
		//执行业务逻辑的service层
		UserService userService = new UserService();
		try {
			ShoppingCateImage shoppingCateImage = userService.querryShoppingCateImage(cate_image_id);
			Gson gson = new Gson();
			String strJson = gson.toJson(shoppingCateImage);
			out.write(strJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
}
