package com.xiaohe.model.event;

/**
 * <xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName><
 * ![CDATA[FromUser]
 * ]></FromUserName><CreateTime>123456789</CreateTime><MsgType>< ![CDATA[event]
 * ]></MsgType><Event>< ![CDATA[subscribe] ]></Event></xml>
 * 
 * @author zongx
 *
 */
public class Event {
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	private String event;
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
}
