<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录系统界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/css.css">
    </head>
  <body>
  
 	 <center>
   		<table cellpadding="1" background="ff0000" border="0" cellspacing="0" style="width:100%;height:100%;">
   		<tr>
   			<td style="width:100%;height:100%;" align="center" valign="middle">
 			 <form action="<%=path %>/servlet/userServlet" method="post">
	           	<table cellpadding="0" cellspacing="1" border="1"  bgcolor="3fac1f">
	           		<tr>
	             	  	<td colspan="2" bgcolor="#80ffc0" >
	                		<input type="hidden" name="operate" value="login">登陆
	                	</td>
	           		 </tr>
	           		 <tr>
	             	  	<td >
	                		<input type="hidden" name="url" value="">用户名：
	               		</td>
	             		<td >
	                    	<input  type="text" name="loginName" value="cy" style="width:200px;" > &nbsp;
	                	</td>
	           		 </tr>
	           		 <tr>
		            	 <td align="right">
		              		<input type="hidden" name="url" value=""> 密&nbsp;&nbsp;码：
		           		 </td>
	                	<td >
	                   		 <input  type="password" name="loginPwd" value="1234567" style="width:200px;" > &nbsp;
	                	</td>
	           		 </tr>
	           		 <tr>
	               		 <td colspan="2" align="center"></td>
	            	 </tr>
	            	<tr>
	               		 <td colspan="2" align="center"><input  type="submit" value="登 陆"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="2" align="center">
	            		${message }
	            		</td>
	            	</tr>
	   			 </table>
  			</form>
 			</td>
 		 </tr>
 	 </table>
	</center>
  </body>
</html>
