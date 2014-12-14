package com.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 服务器请求工具类
 * HttpConnection对象, 原生态，需要手动封装很多数据
 * HttpClient  apache封装的，可以减少手动封装，简单
 * @author panzhipeng
 *
 */
public class HttpUtil {

	// 创建HttpClient对象
	private static HttpClient httpClient = new DefaultHttpClient();
	
	private static HttpClient init() {
		return new DefaultHttpClient();
		
	}
	
	// 关闭
	public static void close(HttpClient httpClient) {
		if(null != httpClient ){
			httpClient.getConnectionManager().shutdown();
			httpClient = null;
		}
	}
		
	
	/**
	 * get请求 
	 * @param url
	 * @return
	 */
	public static String getRequest(String url) throws Exception {
		HttpClient httpClient = init();
		// 创建 httpGet对象
		HttpGet httpGet= new HttpGet(url);
		// 发送get请求
		HttpResponse httpResponse = httpClient.execute(httpGet);
		// 如果 服务器成功的响应
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			// 获取服务器响应字符串
			String result = EntityUtils.toString(httpResponse.getEntity());
			return result;
		}
//		close();
		return null;
	}

	/**
	 * post请求
	 * @param url
	 * @param map
	 * @return
	 */
	public static String postRequest(String url, HashMap<String, String> map)   throws Exception{
		HttpClient httpClient = init();
		// 创建 HttpPost
		HttpPost httpPost = new HttpPost(url);
		
		// 封装参数， 传递多个参数的时候，进行参数封装
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			parameters.add(new BasicNameValuePair(key, map.get(key)));
		}
		// 设置请求参数
		httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
		// 发送post请求	
		HttpResponse httpResponse =  httpClient.execute(httpPost);
		// 如果 服务器成功的响应
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			// 获取服务器响应字符串
			String result = EntityUtils.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}

	
	
	
	
	public static InputStream getDownloadImage(String url) throws Exception{
		// TODO Auto-generated method stub
		InputStream inputStream = null;
		HttpEntity  entity = null;
		HttpClient httpClient = init();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse =httpClient.execute(httpGet);
		//获取相应状态代码
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			//返回实体内容
			entity = httpResponse.getEntity();
			inputStream = entity.getContent();
			return inputStream;
		}
//		close();
		return null;
	}
	/**
	 * 下载文件
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static InputStream getDownFile(String url) throws Exception {
		InputStream inputStream = null;
		HttpClient httpClient = init();
		// 创建 httpGet对象
		HttpGet httpGet= new HttpGet(url);
		// 发送get请求
		HttpResponse httpResponse = httpClient.execute(httpGet);
		// 如果 服务器成功的响应
		if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  // HttpStatus.SC_OK = 200
			// 获取服务器响应字符串
			HttpEntity entity = httpResponse.getEntity();
			inputStream = entity.getContent();
			return inputStream;
		}
		return null;
	}
}
