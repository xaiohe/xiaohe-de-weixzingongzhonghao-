package com.xiaohe.service.event;

import com.xiaohe.model.event.LocationEvent;
import com.xiaohe.model.event.ScanEvent;
import com.xiaohe.util.XmlUtil;

public class LocationEventService implements EventService {

	@Override
	public String DealMsg(String content) {
		LocationEvent le= this.getLocationEvent(content);
		System.out.println(le.getLatitude());
		System.out.println(le.getLongitude());
		String xmlContent=XmlUtil.createXmlTextMessage(content,"您的地理位置纬度是："+le.getLatitude()+"你的地理位置经度是："+le.getLongitude());
		return xmlContent;
	}
	public LocationEvent getLocationEvent(String content) {
		LocationEvent le=new LocationEvent();
		le.setToUserName(XmlUtil.getTagValue(content, "ToUserName"));
		le.setFromUserName(XmlUtil.getTagValue(content, "FromUserName"));
		le.setCreateTime(XmlUtil.getTagValue(content, "CreateTime"));
		le.setMsgType(XmlUtil.getTagValue(content, "MsgType"));
		le.setEvent(XmlUtil.getTagValue(content, "Event"));
		le.setLatitude(XmlUtil.getTagValue(content, "Latitude"));
		le.setLongitude(XmlUtil.getTagValue(content, "Longitude"));
		le.setPrecision(XmlUtil.getTagValue(content, "Precision"));
		return le;
	}
}
