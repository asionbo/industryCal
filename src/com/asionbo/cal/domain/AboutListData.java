package com.asionbo.cal.domain;

/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2017-1-4 下午7:32:53
 * 
 * 关于页面，详情基类
 * ========================================================================
 */
public class AboutListData {

	/**
	 * 图标资源id
	 */
	public int icon;
	
	/**
	 * 图标相应描述信息
	 */
	public String text;

	public AboutListData(int icon, String text) {
		super();
		this.icon = icon;
		this.text = text;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
