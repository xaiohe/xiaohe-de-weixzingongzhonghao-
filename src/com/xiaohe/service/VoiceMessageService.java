package com.xiaohe.service;

import com.xiaohe.model.VoiceMessage;
import com.xiaohe.util.AccessTokenUtil;
import com.xiaohe.util.VoiceUtil;
import com.xiaohe.util.XmlUtil;

public class VoiceMessageService implements MessageService {

	@Override
	public String DealMsg(String content) {
		//获取声音对象
				VoiceMessage vm = XmlUtil.getVoiceMessage(content);
				String mediaId = vm.getMediaId();
				System.out.println("mediaId:    ="+mediaId);
				String token = AccessTokenUtil.getTokenFromWeiXin();
				VoiceUtil.downloadVoice(token, mediaId);
				//回复用户，已上传成功
				String xmlContent = XmlUtil.createXmlTextMessage(content,"你的声音太魔性了，不小心就永久保存了");
				return xmlContent;
		
	}

}
