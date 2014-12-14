package com.cate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javabean.Cate;
import javabean.ShoppingSeckIll;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxl.util.UserService;
import com.google.gson.Gson;

public class ShoppingSeckIllServlet extends HttpServlet {

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
		//执行业务逻辑的service层
		UserService userService = new UserService();
		ArrayList<ShoppingSeckIll> shoppingSeckIllList=new ArrayList<ShoppingSeckIll>();
		try {
			shoppingSeckIllList = userService.querryShoppingSeckIll();
			strJson = gson.toJson(shoppingSeckIllList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println(strJson);
		outer.write(strJson);
		outer.flush();
		outer.close();
		
	}
}
