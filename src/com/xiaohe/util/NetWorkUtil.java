package com.xiaohe.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;



public class NetWorkUtil {
	public static String getContentFromUrlByPost(String weburl,String params) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(weburl);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
			conn.setDoOutput(true);//是否允许使用输出流输出内容到目标地址
			conn.setDoInput(true);
			PrintWriter  pw    = new PrintWriter(conn.getOutputStream());
			pw.write(params);
			pw.flush();
			Scanner s = new Scanner(conn.getInputStream());
			
			while(s.hasNextLine()) {
				sb.append(s.nextLine());
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	public static String updateFile(String weburl,InputStream is,String filepath) {
		File file = new File(filepath);
		long  filelength =file.length();
		String boundary = "----------";
		String end = "\r\n";
		String twoHyphens = "--";
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(weburl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary+";filelength="+String.valueOf(filelength));
			conn.setRequestProperty("Charset", "UTF-8");
			 StringBuilder sb2 = new StringBuilder();
			 sb2.append("--"); // 必须多两道线
			 sb2.append(boundary);
			 sb2.append("\r\n");
			 sb2.append("Content-Disposition: form-data;name=\"media\";filename=\"a.jpg\"\r\n");
			 sb2.append("Content-Type:application/octet-stream\r\n\r\n");
			 
			//PrintWriter  pw    = new PrintWriter(conn.getOutputStream());
			OutputStream os  = conn.getOutputStream();
			os.write(sb2.toString().getBytes("UTF-8"));
			BufferedInputStream bis =new BufferedInputStream(is);
			byte[] cache = new byte[1024*1024];
			int len = 0;
			
			os.write((twoHyphens+boundary+end).getBytes("UTF-8"));
			
			while((len=bis.read(cache))!=-1) {
				os.write(cache,0,len);
			}
			os.write((twoHyphens+boundary+end).getBytes("UTF-8"));
			os.flush();
			os.close();
			
			Scanner s = new Scanner(conn.getInputStream());
			
			while(s.hasNextLine()) {
				sb.append(s.nextLine());
			}
			s.close();
			bis.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	public static String getContentFromUrlByGet(String weburl) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(weburl);
			URLConnection conn = url.openConnection();
			Scanner s = new Scanner(conn.getInputStream());
			while(s.hasNextLine()) {
				sb.append(s.nextLine());
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
}
