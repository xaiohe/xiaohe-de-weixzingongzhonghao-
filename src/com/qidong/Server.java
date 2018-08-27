package com.qidong;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;

import com.xiaohe.model.event.ClickMenuEvent;
import com.xiaohe.service.ImageMessageService;
import com.xiaohe.service.MessageService;
import com.xiaohe.service.MessageServiceFactory;
import com.xiaohe.service.TextMessageService;
import com.xiaohe.service.event.ClickMenuService;
import com.xiaohe.service.event.EventService;
import com.xiaohe.service.event.LocationEventService;
import com.xiaohe.service.event.ScanEventService;
import com.xiaohe.service.event.SubscribeEventService;
import com.xiaohe.util.StringUtil;
import com.xiaohe.util.XmlUtil;



/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String echostr=request.getParameter("echostr");
		response.getWriter().append(echostr);
		System.out.println(echostr);
		
		
//		Enumeration<String> enu = request.getParameterNames();
//		while (enu.hasMoreElements()) {
//			String name = enu.nextElement();
//			String value = request.getParameter(name);
//			System.out.println(name + "=" + value);
//		}
//		String echostr = request.getParameter("echostr");
//		response.getWriter().append(echostr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从用户的输入里获取一个内容对象
				// 输出一个xml给微信公众号的服务器
				// 把tousername和fromusename反转一下
				String content = StringUtil.getContentFromInputStream(request.getInputStream());
				System.out.println(content);
				String event = XmlUtil.getEvent(content);
				String msgType = XmlUtil.getMsgType(content);
				String xmlContent = "";
		if(msgType.equals("event")) {
			EventService es=null;
			if(event.equals("subscribe")||event.equals("unsubscribe")) {
				es=new SubscribeEventService();
			}else if(event.equals("SCAN")) {
				es=new ScanEventService();
			}else if(event.equals("LOCATION")) {
				es=new LocationEventService();
			}else if(event.equals("CLICK")||event.equals("VIEW")) {
				es=new ClickMenuService();
			}
			xmlContent=es.DealMsg(content);
		}else {
			MessageService ms= MessageServiceFactory.createMesaageService(msgType);
			xmlContent = ms.DealMsg(content);
		}
		System.out.println("--------------------------------------------");
		System.out.println("xmlContent==========" + xmlContent);
		response.getWriter().append(new String(xmlContent.getBytes(), "ISO-8859-1"));
		response.getWriter().flush();
	}

}
