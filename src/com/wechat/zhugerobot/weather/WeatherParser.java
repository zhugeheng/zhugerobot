package com.wechat.zhugerobot.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 天气信息JSON解析类
 * @author zhuge
 * @date 2014-12-07
 */
public class WeatherParser {
	
	/**
	 * 
	 * @param jsonObjSrc
	 * @return
	 * @throws JSONException 
	 */
	public static String weather2String(JSONObject jsonObjSrc) throws JSONException {
		StringBuffer result = new StringBuffer();
		Weather weather = parseWeather(jsonObjSrc);
		WeatherDesp desp = weather.getDesp();
//		WeatherDetail[] details = desp.getDetails();
		WeatherData[] datas = desp.getDatas();
		
		result.append("城市: ").append(desp.getCity())
				  .append("  PM2.5: ").append(desp.getPM25())
				  .append("\n");
				 
//		for (int i = 0; i < details.length; i++) {
//			result.append(details[i].getTip()).append("：").append(details[i].getZS()).append("\n");
//			result.append(details[i].getTitle()).append("：").append(details[i].getDes()).append("\n");
//		}
		
		for (int i = 0; i < datas.length; i++) {
			result.append(datas[i].getDate()).append(" ").append(datas[i].getWind()).append("\n");
			result.append(datas[i].getWeather()).append(" ").append(datas[i].getTemp()).append("\n");
			result.append("\n");
		}
		
		result.append("回复数字查看天气指数：").append("\n");
		result.append("1、穿衣指数\n");
		result.append("2、洗车指数\n");
		result.append("3、旅游指数\n");
		result.append("4、感冒指数\n");
		result.append("5、运动指数\n");
		result.append("6、紫外线指数\n");
		
//		System.out.println("Weather result is " + result.toString());
		
		return result.toString();
	}
	
	/**
	 * 获取天气指数
	 * @param jsonObj
	 * @param index
	 * @return
	 * @throws JSONException
	 */
	public static String parseWeatherIndex(JSONObject jsonObj, int index) throws JSONException {
		StringBuffer result = new StringBuffer();
		Weather weather = parseWeather(jsonObj);
		WeatherDesp desp = weather.getDesp();
		WeatherDetail[] details = desp.getDetails();
		
		for (int i = 0; i < details.length; i++) {
			if(i == index) {
				result.append(details[i].getTip()).append("：").append(details[i].getZS()).append("\n");
				result.append(details[i].getTitle()).append("：").append(details[i].getDes()).append("\n");
				break;
			}
	}
		
		return result.toString();
	}

	/**
	 * 获取天气实例
	 * @param jsonObjSrc
	 * @return
	 * @throws JSONException 
	 */
	private static Weather parseWeather(JSONObject jsonObjSrc) throws JSONException {
		Weather result = new Weather();
		result.setError(jsonObjSrc.getInt("error"));
		result.setStatus(jsonObjSrc.getString("status"));
		result.setDate(jsonObjSrc.getString("date"));
		result.setDesp(parseDesp(jsonObjSrc.getJSONArray("results")));
		return result;
	}
	
	private static WeatherDesp parseDesp(JSONArray jsonArrSrc) throws JSONException {
		WeatherDesp result = new WeatherDesp();
		JSONObject jsonObj = jsonArrSrc.getJSONObject(0);
		result.setCity(jsonObj.getString("currentCity"));
		result.setPM25(jsonObj.getString("pm25"));
		result.setDetails(parseDetails(jsonObj.getJSONArray("index")));
		result.setDatas(parseDatas(jsonObj.getJSONArray("weather_data")));
		return result;
	}
	
	private static WeatherDetail[] parseDetails(JSONArray jsonArr) throws JSONException {
		WeatherDetail[] results = new WeatherDetail[jsonArr.length()];
		for (int i = 0; i < results.length; i++) {
			WeatherDetail detail = new WeatherDetail();
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			detail.setTitle(jsonObj.getString("title"));
			detail.setZS(jsonObj.getString("zs"));
			detail.setTip(jsonObj.getString("tipt"));
			detail.setDes(jsonObj.getString("des"));
			results[i] = detail;
		}
		return results;
	}
	
	private static WeatherData[] parseDatas(JSONArray jsonArr) throws JSONException {
		WeatherData[] results = new WeatherData[jsonArr.length()];
		for (int i = 0; i < results.length; i++) {
			WeatherData data = new WeatherData();
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			data.setDate(jsonObj.getString("date"));
			data.setWeather(jsonObj.getString("weather"));
			data.setWind(jsonObj.getString("wind"));
			data.setTemp(jsonObj.getString("temperature"));
			results[i] = data;
		}
		return results;
	}
}
