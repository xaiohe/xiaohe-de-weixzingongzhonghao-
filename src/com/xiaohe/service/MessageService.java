package com.xiaohe.service;

public interface MessageService {
	/**
	 * 处理消息
	 * @param content 客户端传递过来的xml文件
	 * @return 响应用户的xml
	 */
	public String DealMsg(String content);
}
