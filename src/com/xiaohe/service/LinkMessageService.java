package com.xiaohe.service;

import com.xiaohe.model.LinkMessage;
import com.xiaohe.util.XmlUtil;

public class LinkMessageService implements MessageService {

	@Override
	public String DealMsg(String content) {
		String xmlContent = "";
		LinkMessage lm = XmlUtil.getLinkMessage(content);
		System.out.println(lm.getTitle());
		System.out.println(lm.getDescription());
		System.out.println(lm.getUrl());
		
		
		//回复用户，已上传成功
		xmlContent = XmlUtil.createXmlTextMessage(content,"恭喜你：链接发送成功");
		return xmlContent;
	}

}
