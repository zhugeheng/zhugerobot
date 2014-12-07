package com.wechat.zhugerobot.weather;

/**
 * 天气信息基础数据
 * 
 * @author zhuge
 * @date 2014-12-07
 * 
 */
public class Weather {
	private int Error;
	private String Status;
	private String Date;

	private WeatherDesp Desp;

	public int getError() {
		return Error;
	}

	public void setError(int error) {
		Error = error;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public WeatherDesp getDesp() {
		return Desp;
	}

	public void setDesp(WeatherDesp desp) {
		Desp = desp;
	}

	@Override
	public String toString() {
		return "Weather [Error=" + Error + ", Status=" + Status + ", Date="
				+ Date + ", Desp=" + Desp + "]";
	}

}
