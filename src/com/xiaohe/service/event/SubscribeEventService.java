package com.xiaohe.service.event;

import com.xiaohe.model.event.SubscribeEvent;
import com.xiaohe.util.XmlUtil;

public class SubscribeEventService implements EventService {

	@Override
	public String DealMsg(String content) {
		SubscribeEvent se=this.getSubscribeEvent(content);
		System.out.println("关注事件:"+se.getEvent());
		String xmlContent="";
		if(se.getEvent().equals("subscribe")) {
			xmlContent=XmlUtil.createXmlTextMessage(content, "恭喜你：关注成功，送你一句话：我与杀戮之中盛放，亦如黎明中的花朵");
		}else {
			xmlContent=XmlUtil.createXmlTextMessage(content, "取消关注成功");
		}
		return xmlContent;
	}
	public SubscribeEvent getSubscribeEvent(String content) {
		SubscribeEvent se=new SubscribeEvent();
		se.setCreateTime(XmlUtil.getTagValue(content, "CreateTime"));
		se.setEvent(XmlUtil.getTagValue(content, "Event"));
		se.setFromUserName(XmlUtil.getTagValue(content, "FromUserName"));
		se.setToUserName(XmlUtil.getTagValue(content, "ToUserName"));
		se.setMsgType(XmlUtil.getTagValue(content, "MsgType"));
		return se;
	}
}
