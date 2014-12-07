package com.wechat.zhugerobot.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * �������󹤾���
 * 
 * @author zhuge
 * 
 */
public class NetworkUtil {

	/**
	 * ����http���󲢻�ȡ���
	 * 
	 * @param requestUrl
	 *            �����ַ
	 * @param requestMethod
	 *            ����ʽ��GET��POST��
	 * @param outputStr
	 *            �ύ������
	 * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {

		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
//			requestUrl = URLEncoder.encode(requestUrl, "UTF-8");
			
			System.out.println("Request url is " + requestUrl);
			System.out.println("Request Meather is " + requestMethod);
			
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// ����������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			System.out.println("Request result is " + buffer.toString());
			System.out.println("before JsonObj is " + jsonObject);
//			jsonObject = JSONObject.fromObject(buffer.toString());
			jsonObject = new JSONObject(buffer.toString());
			System.out.println("JsonObj is " + jsonObject.toString());
		} catch (ConnectException ce) {
			 System.out.println("Weixin server connection timed out.");
			 System.out.println(ce.getMessage());
		} catch (Exception e) {
			System.out.println("Http request error");
			System.out.println(e.getMessage());
		}
		return jsonObject;
	}
}
