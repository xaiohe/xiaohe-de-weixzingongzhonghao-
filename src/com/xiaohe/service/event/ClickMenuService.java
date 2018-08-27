package com.xiaohe.service.event;

import com.xiaohe.model.event.ClickMenuEvent;
import com.xiaohe.model.event.ScanEvent;
import com.xiaohe.util.XmlUtil;

public class ClickMenuService implements EventService {

	@Override
	public String DealMsg(String content) {
		ClickMenuEvent ce=this.getClickMenuEvent(content);
		System.out.println(ce.getEventKey());
		String xmlContent="";
		if(ce.getMsgType().equals("CLICK")) {
			xmlContent=XmlUtil.createXmlTextMessage(content,"您点击的菜单是："+ce.getEventKey());
		}else {
			xmlContent=XmlUtil.createXmlTextMessage(content,"您点击的链接是："+ce.getEventKey());
		}
		
		return xmlContent;
	}
	public ClickMenuEvent getClickMenuEvent(String content) {
		ClickMenuEvent ce=new ClickMenuEvent();
		ce.setToUserName(XmlUtil.getTagValue(content, "ToUserName"));
		ce.setFromUserName(XmlUtil.getTagValue(content, "FromUserName"));
		ce.setCreateTime(XmlUtil.getTagValue(content, "CreateTime"));
		ce.setMsgType(XmlUtil.getTagValue(content, "MsgType"));
		ce.setEvent(XmlUtil.getTagValue(content, "Event"));
		ce.setEventKey(XmlUtil.getTagValue(content, "EventKey"));
		return ce;
	}
}
