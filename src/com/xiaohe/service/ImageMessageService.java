package com.xiaohe.service;


import com.xiaohe.model.ImageMessage;
import com.xiaohe.util.ImageUtil;
import com.xiaohe.util.XmlUtil;

public class ImageMessageService implements MessageService {

	@Override
	public String DealMsg(String content) {
		ImageMessage im=XmlUtil.getImageMessage(content);
		System.out.println(im.getMediaId());
		System.out.println(im.getPicUrl());
		ImageUtil.downloadPic(im.getPicUrl());
		//回复用户，已上传成功
		String xmlContent = XmlUtil.createXmlTextMessage(content,"你完了，小伙子，图片被我保存下来了");
		return xmlContent;
		
		
	}

}
