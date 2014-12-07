package com.wechat.zhugerobot.service;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wechat.zhugerobot.constant.Constant;
import com.wechat.zhugerobot.resp.TextMessage;
import com.wechat.zhugerobot.util.MessageUtil;
import com.wechat.zhugerobot.util.NetworkUtil;
import com.wechat.zhugerobot.weather.WeatherParser;

/**
 * ���ķ�����
 * 
 * @author zhugeheng
 * @date 2014-11-30
 */
public class CoreService {
	
	private static String lastMsg = "";
	
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		TextMessage textMessage = new TextMessage();
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			// ��Ϣ����
			String msgContent = requestMap.get("Content");

			// �ظ��ı���Ϣ
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// respContent = "��������δ���꣬��ʱ����������˵�Ļ�������ʱ��������~";
				if (msgContent.contains("����")) {
					String requestUrl = Constant.BAUDU_WEATHER_API_URL.replace(
							"city", URLEncoder.encode(msgContent.substring(0,2), "UTF-8")).replace(
							"key", Constant.BAIDU_WEATHER_API_KEY);
//					System.out.println("request url is : " + requestUrl);
					respContent = WeatherParser.weather2String(NetworkUtil
							.httpRequest(requestUrl, "GET", null));
				} else {
					respContent = "��������δ���꣬��ʱ����������˵�Ļ�������ʱ��������~";
				}
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "��������δ���꣬��ʱ������������ͼƬ������ʱ��������~";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "��������δ���꣬��ʱ�������������ģ�����ʱ��������~";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "��������δ���꣬����˵����������˷��������ӣ�����ʱ��������~";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "��������δ���꣬��ʱ������������˵ʲô������ʱ��������~";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "��ӭ��ע�������ˣ����ֹ������ڿ����У������ڴ�����";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CoreService Error: " + e.getMessage());
			textMessage.setContent("��Ǹ����ѯʧ�ܣ����Ժ�����");
			respMessage = MessageUtil.textMessageToXml(textMessage);
		}

		return respMessage;
	}
}
