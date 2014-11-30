package com.wechat.zhugerobot.req;


/**
 * 文本消息
 * 
 * @author zhugeheng
 * @date 2014-11-30
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
