package com.cate.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxl.util.UserService;
import com.google.gson.Gson;


public class AddToBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String cate_name = request.getParameter("cate_name");
		double cate_price = Double.parseDouble(request.getParameter("cate_price"));
		
		//执行业务逻辑的service层
		UserService userService = new UserService();
		try {
			int count = userService.addToBasket(user_id,cate_name,cate_price);
			Gson gson = new Gson();
			String strJson = gson.toJson(count);
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
