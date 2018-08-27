package com.xiaohe.service.event;

import com.xiaohe.model.event.ScanEvent;
import com.xiaohe.util.XmlUtil;

public class ScanEventService implements EventService {

	@Override
	public String DealMsg(String content) {
		//要把客户端传过来的xml解析出来
		ScanEvent scan=this.getScanEvent(content);
		System.out.println("扫码的msgtype"+scan.getMsgType());
		String xmlContent=XmlUtil.createXmlTextMessage(content,"扫码成功");
		return xmlContent;
	}
	public ScanEvent getScanEvent(String content) {
		ScanEvent se=new ScanEvent();
		se.setToUserName(XmlUtil.getTagValue(content, "ToUserName"));
		se.setFromUserName(XmlUtil.getTagValue(content, "FromUserName"));
		se.setCreateTime(XmlUtil.getTagValue(content, "CreateTime"));
		se.setMsgType(XmlUtil.getTagValue(content, "MsgType"));
		se.setEvent(XmlUtil.getTagValue(content, "Event"));
		se.setEventKey(XmlUtil.getTagValue(content, "EventKey"));
		se.setTicket(XmlUtil.getTagValue(content, "Ticket"));
		return se;
	}
}
