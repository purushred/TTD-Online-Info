package com.smart.ttddarshan.vo;

public class DayNameValuePair {
	
	private String cssClass;
	private String dayStr;

	public DayNameValuePair(String s, String day) {
		this.cssClass = s;
		this.dayStr = day;
	}

	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getDayStr() {
		return dayStr;
	}
	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	
}
