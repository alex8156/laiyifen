package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javabean.ShoppingCate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SalesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String imagePath = PathTool.getWebRootPath() + "images/";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
//		int maxRow = Integer.parseInt(request.getParameter("maxRow"));  //查询终止行
//		int minRow = Integer.parseInt(request.getParameter("minRow"));  //查询起始行
		int categoryflavor_id = Integer.parseInt(request.getParameter("categoryflavor_id"));
		
//		
		//执行业务逻辑的service层
		UserService01 userService01 = new UserService01();
		ArrayList<ShoppingCate> shoppingCates;
		try{
			shoppingCates = userService01.querrySales(categoryflavor_id);
			Gson gson = new Gson();
			String strJson = gson.toJson(shoppingCates);
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
