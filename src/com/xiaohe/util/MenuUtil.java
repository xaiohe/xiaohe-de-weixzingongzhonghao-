package com.xiaohe.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xiaohe.model.menu.Button;

public class MenuUtil {
	public static void createMenu() {
		List<Button> list = new ArrayList<>();
		Map map = new HashMap();
		Button button = new Button();
		button.setName("clickme");
		button.setType("click");
		button.setKey("button_01");
		button.setSub_button(null);
		// 第二个一级菜单
		Button button2 = new Button();
		button2.setName("百度主页");
		button2.setType("view");
		button2.setKey("button_02");
		button2.setUrl("http://www.qq.com");
		button2.setSub_button(null);

		// 第三个一级菜单
		Button button3 = new Button();
		button3.setName("扫码");
		button3.setType("scancode_push");
		button3.setKey("button_03");
		List<Button> subList=new ArrayList<>();
		
		//第一个子菜单
		Button sub1=new Button();
		sub1.setName("扫码2");
		sub1.setType("scancode_waitmsg");
		sub1.setKey("sub_01");
		sub1.setSub_button(null);
		//第二个子菜单
		Button sub2=new Button();
		sub2.setName("相机");
		sub2.setType("pic_sysphoto");
		sub2.setKey("sub_02");
		sub2.setSub_button(null);
		//第三个子菜单
		Button sub3=new Button();
		sub3.setName("相册");
		sub3.setType("pic_photo_or_album");
		sub3.setKey("sub_03");
		sub3.setSub_button(null);
		//第四个子菜单
		Button sub4=new Button();
		sub4.setName("相册2");
		sub4.setType("pic_weixin");
		sub4.setKey("sub_04");
		sub4.setSub_button(null);
		//第五个子菜单
		Button sub5=new Button();
		sub5.setName("位置");
		sub5.setType("location_select");
		sub5.setKey("sub_05");
		sub5.setSub_button(null);
		
		subList.add(sub1);
		subList.add(sub2);
		subList.add(sub3);
		subList.add(sub4);
		subList.add(sub5);
		
		button3.setSub_button(subList);
		list.add(button2);
		list.add(button3);
		list.add(button);
		map.put("button", list);
		String jsonString = JSON.toJSONString(map);
		String weburl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ AccessTokenUtil.getTokenFromWeiXin();
		String content = NetWorkUtil.getContentFromUrlByPost(weburl, jsonString);
		System.out.println(content);
	}
}
