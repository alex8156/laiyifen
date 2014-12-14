package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javabean.ShoppingCate;
import javabean.ShoppingCategory;
import javabean.ShoppingFlavor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class FootPrintDeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String imagePath = PathTool.getWebRootPath() + "images/";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int cate_id = Integer.parseInt(request.getParameter("cate_id"));
		
//		
		//执行业务逻辑的service层
		UserService01 userService01 = new UserService01();
		ArrayList<ShoppingCate> shoppingCates;
		try{
			shoppingCates = userService01.deleteFootCate(cate_id);
			Gson gson = new Gson();
			String strJson = gson.toJson(shoppingCates);
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
