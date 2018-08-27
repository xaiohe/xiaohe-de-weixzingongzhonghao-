package com.xiaohe.service;

import com.xiaohe.model.TextMessage;
import com.xiaohe.util.XmlUtil;

public class TextMessageService implements MessageService {

	@Override
	public String DealMsg(String content) {
		TextMessage tm = XmlUtil.getTextMessage(content);
		String textContent = tm.getContent();
		String xmlContent = XmlUtil.createXmlTextMessage(content,"惊喜吧！我也会说："+textContent);
		return xmlContent;
		
	}

}
