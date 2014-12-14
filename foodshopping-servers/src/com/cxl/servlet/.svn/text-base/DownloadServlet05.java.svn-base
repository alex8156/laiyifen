/**
 * 
 */
package com.cxl.servlet;

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

import com.cxl.util.PathTool;

/**
 * 文件下载，
 * 传参数名称 是 fileName
		http://localhost/studyJsp/downloadServlet?fileName=Blue.jpg
		http://192.168.1.242/Android11-02Web/downloadServlet?imageName=a.jpg
		http://192.168.1.242/Android11-02Web/imagesServlet
 * @author panzhipeng
 */
public class DownloadServlet05 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String uploadPath = PathTool.getWebRootPath() + "upload/";
	
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
//  	response.setContentType("html/txt");
		response.setContentType("application/x-msdownload");// 设置返回类型 为文件下载
		
		// 创建输入流和 输出流
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		
		try {
			// 获取请求参数， 转换为实际存储路径
			String imageName = request.getParameter("imageName");
			String fullPath = uploadPath + imageName;
			
			// 判断文件是否存在,存在就返回给客户端
			File file = new File(fullPath);
			if(file.exists() && file.length()>0) {
				// 设置 下载弹出框 保存的默认名称
				String name = URLEncoder.encode(file.getName(), encoding);
				response.setHeader("Content-Disposition", "attachment;filename=\"" + name + "\"");
				
				// 设置响应的长度
				int fileLength = (int)file.length();
				response.setContentLength(fileLength);
				
				// 初始化 输入流 和 输出流
				inputStream = new FileInputStream(file);
				servletOutputStream = response.getOutputStream();
				
				byte[] buffer = new byte[4096];
				int count = 0;
				while( (count= inputStream.read(buffer))!=-1) {
					servletOutputStream.write(buffer, 0, count);
				}
			}
			System.out.println(imageName + " 文件下载成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
			servletOutputStream.flush();
			servletOutputStream.close();
		}
	}
}
