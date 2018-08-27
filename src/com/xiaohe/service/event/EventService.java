package com.xiaohe.service.event;

public interface EventService {
	/**
	 * 处理消息
	 * @param content 客户端传过来的xml文件
	 * @return 响应用户的xml
	 */
	public String DealMsg(String content);
}
