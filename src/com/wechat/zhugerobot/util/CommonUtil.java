package com.wechat.zhugerobot.util;

import java.io.UnsupportedEncodingException;

/**
 * ͨ�ù��߷�װ��
 * @author zhugeheng
 * @date 2014-11-30
 */
public class CommonUtil {
	/**
	 * �������utf-8���뷽ʽʱ�ַ�����ռ�ֽ���
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// ���ֲ���utf-8����ʱռ3���ֽ�
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
}
