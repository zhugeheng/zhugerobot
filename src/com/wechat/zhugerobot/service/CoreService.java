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
 * 核心服务类
 * 
 * @author zhugeheng
 * @date 2014-11-30
 */
public class CoreService {
	
	private static String lastMsg = "";
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		TextMessage textMessage = new TextMessage();
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String msgContent = requestMap.get("Content");

			// 回复文本消息
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// respContent = "机器人尚未成年，暂时还看不懂您说的话，过段时间再来吧~";
				if (msgContent.contains("天气")) {
					String requestUrl = Constant.BAUDU_WEATHER_API_URL.replace(
							"city", URLEncoder.encode(msgContent.substring(0,2), "UTF-8")).replace(
							"key", Constant.BAIDU_WEATHER_API_KEY);
//					System.out.println("request url is : " + requestUrl);
					respContent = WeatherParser.weather2String(NetworkUtil
							.httpRequest(requestUrl, "GET", null));
				} else {
					respContent = "机器人尚未成年，暂时还看不懂您说的话，过段时间再来吧~";
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "机器人尚未成年，暂时还看不懂您的图片，过段时间再来吧~";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "机器人尚未成年，暂时还看不懂您在哪，过段时间再来吧~";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "机器人尚未成年，麻麻说不能随便点别人发来的连接，过段时间再来吧~";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "机器人尚未成年，暂时还听不懂您在说什么，过段时间再来吧~";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "欢迎关注诸葛机器人，各种功能正在开发中，敬请期待！！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CoreService Error: " + e.getMessage());
			textMessage.setContent("抱歉，查询失败，请稍后再试");
			respMessage = MessageUtil.textMessageToXml(textMessage);
		}

		return respMessage;
	}
}
