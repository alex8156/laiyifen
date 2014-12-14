package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javabean.ShoppingCategory;
import javabean.ShoppingFlavor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class CategoryListServt extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String imagePath = PathTool.getWebRootPath() + "images/";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		
		//执行业务逻辑的service层
		UserService01 userService01 = new UserService01();
		ArrayList<ShoppingCategory> shoppingCategories;
		try{
			shoppingCategories = userService01.querryCategory();
			Gson gson = new Gson();
			String strJson = gson.toJson(shoppingCategories);
			System.out.println(strJson);
			out.write(strJson);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		
		ArrayList<ShoppingFlavor> shoppingFlavors;
		try{
			shoppingFlavors = userService01.querryCategoryFlavor();
			Gson gson = new Gson();
			String strJson = gson.toJson(shoppingFlavors);
			System.out.println(strJson);
			out.write(strJson);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
