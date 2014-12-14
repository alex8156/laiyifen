package com.cate.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javabean.ShoppingCate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxl.util.UserService;
import com.google.gson.Gson;


public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_FAILED = "登陆失败，请检查用户名或密码！";
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		int maxRow = Integer.parseInt(request.getParameter("maxRow"));  //查询终止行
		int minRow = Integer.parseInt(request.getParameter("minRow"));  //查询起始行
		//执行业务逻辑的service层
		UserService userService = new UserService();
		ArrayList<ShoppingCate> shoppingCates;
		try {
			shoppingCates = userService.querryFavorite(maxRow,minRow);
			Gson gson = new Gson();
			String strJson = gson.toJson(shoppingCates);
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
