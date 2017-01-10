package com.asionbo.cal.domain;

/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2017-1-9 下午4:54:14
 * 
 * 包含总值、平均值、制耳率
 * ========================================================================
 */
public class EaringResultData {

	private double totalTop;//总峰高
	private double averTop;//平均峰高
	private double totalBottom;//总谷高
	private double averBottom;//平均谷高
	private double earing;//制耳率
	
	public double getEaring() {
		return earing;
	}
	public void setEaring(double earing) {
		this.earing = earing;
	}
	public double getTotalTop() {
		return totalTop;
	}
	public void setTotalTop(double totalTop) {
		this.totalTop = totalTop;
	}
	public double getAverTop() {
		return averTop;
	}
	public void setAverTop(double averTop) {
		this.averTop = averTop;
	}
	public double getTotalBottom() {
		return totalBottom;
	}
	public void setTotalBottom(double totalBottom) {
		this.totalBottom = totalBottom;
	}
	public double getAverBottom() {
		return averBottom;
	}
	public void setAverBottom(double averBottom) {
		this.averBottom = averBottom;
	}
	public EaringResultData(double totalTop, double averTop,
			double totalBottom, double averBottom, double earing) {
		super();
		this.totalTop = totalTop;
		this.averTop = averTop;
		this.totalBottom = totalBottom;
		this.averBottom = averBottom;
		this.earing = earing;
	}
	
}
