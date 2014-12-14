package com.cxl.servlet;

import java.io.UnsupportedEncodingException;

public class StrConvert {
	 public static String toCn(String str){
	 String strcn=null;
	 try{
	 strcn = new String(str.getBytes("iso-8859-1"),"UTF-8"); 
	 }catch(UnsupportedEncodingException e){
	 e.printStackTrace();
	 }
	 return strcn;
	 }
	}
