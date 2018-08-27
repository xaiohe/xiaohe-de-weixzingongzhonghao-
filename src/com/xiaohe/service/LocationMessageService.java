package com.xiaohe.service;

import com.xiaohe.model.LocationMessage;
import com.xiaohe.util.XmlUtil;

public class LocationMessageService implements MessageService {

	@Override
	public String DealMsg(String content) {
		LocationMessage lm  = XmlUtil.getLocationMessage(content);
		System.out.println(lm.getLocation_X());
		System.out.println(lm.getLocation_Y());
		String xmlContent = XmlUtil.createXmlTextMessage(content,"连接发送成功");
		return xmlContent;
	}

}
