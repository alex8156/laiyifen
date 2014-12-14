package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownLoadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 图片在工程项目中的目录
	public static final String imagePath = PathTool.getWebRootPath() + "images/";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload");	// 设置返回类型
		
		// 创建输入流 和  输出流
		InputStream inStream = null;
		ServletOutputStream servletOS = null;
		
		try {
			String imageName = request.getParameter("imageName");	// 获取请求参数
			String fullFilePath = imagePath + imageName;
			System.out.println("======下载=====" + fullFilePath);
			File file = new File(fullFilePath);
			
			// 如果文件存在 ，并且 文件长度大于0
			if (file.exists() &&  file.length()>0) {
				String filename = URLEncoder.encode(file.getName(), "UTF-8");
				response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				int fileLength = (int) file.length();
				response.setContentLength(fileLength);
				
				//  初始化 输入流  和 输出流 
				inStream = new FileInputStream(file);
				servletOS = response.getOutputStream();
				
				byte[] buf = new byte[4096];
				int readLength = 0;
				while (((readLength = inStream.read(buf)) != -1)) {
					servletOS.write(buf, 0, readLength);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (servletOS != null) {
					servletOS.flush();
					servletOS.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
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
