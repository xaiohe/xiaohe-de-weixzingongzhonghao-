package com.xiaohe.service;

public class MessageServiceFactory {
	public static MessageService createMesaageService(String msgType) {
		MessageService ms = null;
		switch (msgType) {
		case "text":
			ms = new TextMessageService();
			break;
		case "image":
			ms = new ImageMessageService();
			break;
		case "voice":
			ms = new VoiceMessageService();
			break;
		case "video":
			ms = new VedioMessageService();
			break;
		case "shortvideo":
			ms = new VedioMessageService();
			break;
		case "link":
			ms = new LinkMessageService();
			break;
		case "location":
			ms = new LocationMessageService();
			break;

		default:
			break;
		}
		return ms;
	}
	
}
