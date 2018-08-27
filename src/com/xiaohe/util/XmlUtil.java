package com.xiaohe.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import com.xiaohe.model.ImageMessage;
import com.xiaohe.model.LinkMessage;
import com.xiaohe.model.LocationMessage;
import com.xiaohe.model.TextMessage;
import com.xiaohe.model.VedioMessage;
import com.xiaohe.model.VoiceMessage;
public class XmlUtil {
	/**
	 * 从一个xml文本内容里获取一个对象TextMessage出来 内部使用dom4j
	 * 
	 * @param xmlContent
	 *            xml内容
	 * @return
	 */
	public static TextMessage getTextMessage(InputStream is) {
		TextMessage tm = new TextMessage();
		// 使用dom4j来读取内容

		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			tm.setToUserName(root.element("ToUserName").getStringValue());
			tm.setFromUserName(root.element("FromUserName").getStringValue());
			tm.setCreateTime(root.element("CreateTime").getStringValue());
			tm.setMsgType(root.element("MsgType").getStringValue());
			tm.setContent(root.element("Content").getStringValue());
			tm.setMsgId(root.element("MsgId").getStringValue());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return tm;
	}
	public static TextMessage getTextMessage(String content) {
		TextMessage tm = new TextMessage();
		// 使用dom4j来读取内容
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			tm.setToUserName(root.element("ToUserName").getStringValue());
			tm.setFromUserName(root.element("FromUserName").getStringValue());
			tm.setCreateTime(root.element("CreateTime").getStringValue());
			tm.setMsgType(root.element("MsgType").getStringValue());
			tm.setContent(root.element("Content").getStringValue());
			tm.setMsgId(root.element("MsgId").getStringValue());
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		return tm;
	}
	public static String createXmlTextMessage(TextMessage tm) {
		String xmlContent="";
		Document doc=DocumentHelper.createDocument();
		Element root=DocumentHelper.createElement("xml");
		Element toUserName=DocumentHelper.createElement("ToUserName");
		CDATA toUserNameCData=DocumentHelper.createCDATA(tm.getToUserName());
		toUserName.add(toUserNameCData);
		root.add(toUserName);
		doc.add(root);//这里只需要执行一次
		//以此类推,接收其他的元素
		Element fromUserName=DocumentHelper.createElement("FromUserName");
		CDATA fromUserNameCData=DocumentHelper.createCDATA(tm.getFromUserName());
		fromUserName.add(fromUserNameCData);
		root.add(fromUserName);
		
		Element createTime=DocumentHelper.createElement("CreateTime");
		Element aa=createTime.addText(tm.getCreateTime());
		root.add(aa);
		
		Element msgType=DocumentHelper.createElement("MsgType");
		CDATA msgTypeCData=DocumentHelper.createCDATA(tm.getMsgType());
		msgType.add(msgTypeCData);
		root.add(msgType);
		
		Element content=DocumentHelper.createElement("Content");
		CDATA contentCData=DocumentHelper.createCDATA(tm.getContent());
		content.add(contentCData);
		root.add(content);
			
		try {
			//把内容写到StringWriter中
			StringWriter sw = new StringWriter();
			XMLWriter writer = new XMLWriter(sw,OutputFormat.createPrettyPrint());
			writer.write(doc);
			System.out.println(sw.toString());
			xmlContent = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return xmlContent;
	}
	public static String createXmlTextMessage(String content,String msg) {
		TextMessage tm = new TextMessage();
		String toUserName = XmlUtil.getToUserName(content);
		String fromUserName = XmlUtil.getFromUserName(content);
		tm.setContent(msg);
		tm.setFromUserName(toUserName);
		tm.setToUserName(fromUserName);
		tm.setMsgType("text");
		tm.setCreateTime(String.valueOf(System.currentTimeMillis()/1000));
		return createXmlTextMessage(tm);
	}
	public static String getMsgType(String content) {
		String msgType = "";
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			msgType = root.element("MsgType").getStringValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return msgType;
	}
	public static String getToUserName(String content) {
		String str = "";
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			str = root.element("ToUserName").getStringValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return str;
	}
	public static String getFromUserName(String content) {
		String str = "";
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			str = root.element("FromUserName").getStringValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return str;
	}
	public static ImageMessage getImageMessage(String content) {
		ImageMessage im = new ImageMessage();
		// 使用dom4j来读取内容
		SAXReader reader=new SAXReader();
		try {
			Document doc=reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root=doc.getRootElement();
			im.setToUserName(root.element("ToUserName").getStringValue());
			im.setFromUserName(root.element("FromUserName").getStringValue());
			im.setCreateTime(root.element("CreateTime").getStringValue());
			im.setMsgType(root.element("MsgType").getStringValue());
			im.setPicUrl(root.element("PicUrl").getStringValue());
			im.setMediaId(root.element("MediaId").getStringValue());
			im.setMsgId(root.element("MsgId").getStringValue());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return im;
	}
	public static VoiceMessage getVoiceMessage(String content) {
		VoiceMessage vm = new VoiceMessage();
		
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			vm.setToUserName(root.element("ToUserName").getStringValue());
			vm.setFromUserName(root.element("FromUserName").getStringValue());
			vm.setCreateTime(root.element("CreateTime").getStringValue());
			vm.setMsgType(root.element("MsgType").getStringValue());
			vm.setMsgId(root.element("MsgId").getStringValue());
			vm.setFormat(root.element("Format").getStringValue());
			vm.setMediaId(root.element("MediaId").getStringValue());
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		
		return vm;
	}
	public static  VedioMessage getVideoMessage(String content) {
		VedioMessage dm = new VedioMessage();

		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			dm.setToUserName(root.element("ToUserName").getStringValue());
			dm.setFromUserName(root.element("FromUserName").getStringValue());
			dm.setCreateTime(root.element("CreateTime").getStringValue());
			dm.setMsgType(root.element("MsgType").getStringValue());
			dm.setMsgId(root.element("MsgId").getStringValue());
			dm.setThumbMediaId(root.element("ThumbMediaId").getStringValue());
			dm.setMediaId(root.element("MediaId").getStringValue());
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		
		return dm;
	}
	public static  LocationMessage getLocationMessage(String content) {
		LocationMessage tm = new LocationMessage();
		
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			tm.setToUserName(root.element("ToUserName").getStringValue());
			tm.setFromUserName(root.element("FromUserName").getStringValue());
			tm.setCreateTime(root.element("CreateTime").getStringValue());
			tm.setMsgType(root.element("MsgType").getStringValue());
			tm.setMsgId(root.element("MsgId").getStringValue());
			tm.setLocation_X(root.element("Location_X").getStringValue());
			tm.setLocation_Y(root.element("Location_Y").getStringValue());
			tm.setScale(root.element("Scale").getStringValue());
			tm.setLabel(root.element("Label").getStringValue());
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
		return tm;
	}
	public static  LinkMessage getLinkMessage(String content) {
		LinkMessage lm = new LinkMessage();
		
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			lm.setToUserName(root.element("ToUserName").getStringValue());
			lm.setFromUserName(root.element("FromUserName").getStringValue());
			lm.setCreateTime(root.element("CreateTime").getStringValue());
			lm.setMsgType(root.element("MsgType").getStringValue());
			lm.setMsgId(root.element("MsgId").getStringValue());
			lm.setTitle(root.element("Title").getStringValue());
			lm.setDescription(root.element("Description").getStringValue());
			lm.setUrl(root.element("Url").getStringValue());
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
		return lm;
	}
	public static String getTagValue(String content,String tagName) {
		String event = "";
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			if(root.element(tagName)!=null) {
				event = root.element(tagName).getStringValue();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return event;
	}
	public static String getEvent(String content) {
		String event = "";
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = doc.getRootElement();
			if(root.element("Event")!=null) {
				event = root.element("Event").getStringValue();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return event;
}
}