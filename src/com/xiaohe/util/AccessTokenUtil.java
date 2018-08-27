package com.xiaohe.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class AccessTokenUtil {
	public static String token;
	
	public static String getTokenFromWeiXin() {
		if(token==null) {
		StringBuffer sb=new StringBuffer();
		try {
			URL url=new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx4335762a63962e9e&secret=9838d5db806c10837ae786b0f5e038fc");
			Scanner s=new Scanner(url.openStream());
			while(s.hasNextLine()) {
				sb.append(s.nextLine());
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		JSONObject job=JSON.parseObject(sb.toString());
		token=(String)job.get("access_token");
		System.out.println("token:"+token);
		}else {
			return token;
		}
		return token;
	}
	
}
