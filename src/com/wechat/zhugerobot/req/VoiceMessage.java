package com.wechat.zhugerobot.req;


/**
 * ��Ƶ��Ϣ
 * 
 * @author zhugeheng
 * @date 2014-11-30
 */
public class VoiceMessage extends BaseMessage {
	// ý��ID
	private String MediaId;
	// ������ʽ
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
}

