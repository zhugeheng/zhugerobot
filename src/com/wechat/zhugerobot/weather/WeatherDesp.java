package com.wechat.zhugerobot.weather;

import java.util.Arrays;

/**
 * 天气描述信息
 * 
 * @author zhuge
 * @date 2014-12-07
 */
public class WeatherDesp {
	private String City;
	private String PM25;

	private WeatherDetail[] Details;
	private WeatherData[] Datas;

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getPM25() {
		return PM25;
	}

	public void setPM25(String pM25) {
		PM25 = pM25;
	}

	public WeatherDetail[] getDetails() {
		return Details;
	}

	public void setDetails(WeatherDetail[] details) {
		Details = details;
	}

	public WeatherData[] getDatas() {
		return Datas;
	}

	public void setDatas(WeatherData[] datas) {
		Datas = datas;
	}

	@Override
	public String toString() {
		return "WeatherDesp [City=" + City + ", PM25=" + PM25 + ", Details="
				+ Arrays.toString(Details) + ", Datas="
				+ Arrays.toString(Datas) + "]";
	}

}
