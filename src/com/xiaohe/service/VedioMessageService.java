package com.xiaohe.service;

import com.xiaohe.model.VedioMessage;
import com.xiaohe.util.AccessTokenUtil;
import com.xiaohe.util.VedioUtil;
import com.xiaohe.util.XmlUtil;

public class VedioMessageService implements MessageService {

	@Override
	public String DealMsg(String content) {
		VedioMessage vm = XmlUtil.getVideoMessage(content);
		String mediaId = vm.getMediaId();
		System.out.println("mediaId====="+mediaId);
		System.out.println("mediaId:    ="+mediaId);
		String token = AccessTokenUtil.getTokenFromWeiXin();
		VedioUtil.downloadVedio(token, mediaId);
		//回复用户，已上传成功
		String xmlContent = XmlUtil.createXmlTextMessage(content,"恭喜你：视频上传成功");
		return xmlContent;
	}

}
