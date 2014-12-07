package com.wechat.zhugerobot.weather;

/**
 * ÃÏ∆¯œÍ«È
 * 
 * @author zhuge
 * @date 2014-12-07
 */
public class WeatherDetail {
	private String Title;
	private String ZS;
	private String Tip;
	private String Des;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getZS() {
		return ZS;
	}

	public void setZS(String zS) {
		ZS = zS;
	}

	public String getTip() {
		return Tip;
	}

	public void setTip(String tip) {
		Tip = tip;
	}

	public String getDes() {
		return Des;
	}

	public void setDes(String des) {
		Des = des;
	}

	@Override
	public String toString() {
		return "WeatherDetail [Title=" + Title + ", ZS=" + ZS + ", Tip=" + Tip
				+ ", Des=" + Des + "]";
	}

}
