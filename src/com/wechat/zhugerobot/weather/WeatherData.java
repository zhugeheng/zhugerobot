package com.wechat.zhugerobot.weather;

/**
 * 天气温度相关数据
 * 
 * @author zhuge
 * @date 2014-12-07
 */
public class WeatherData {
	private String Date;
	private String Weather;
	private String Wind;
	private String Temp;

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getWeather() {
		return Weather;
	}

	public void setWeather(String weather) {
		Weather = weather;
	}

	public String getWind() {
		return Wind;
	}

	public void setWind(String wind) {
		Wind = wind;
	}

	public String getTemp() {
		return Temp;
	}

	public void setTemp(String temp) {
		Temp = temp;
	}

	@Override
	public String toString() {
		return "WeatherData [Date=" + Date + ", Weather=" + Weather + ", Wind="
				+ Wind + ", Temp=" + Temp + "]";
	}

}
