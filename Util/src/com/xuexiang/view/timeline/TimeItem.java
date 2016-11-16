package com.xuexiang.view.timeline;

/**
 * @author dengyalan
 * 
 */
public class TimeItem {
	/**
	 * 时间
	 */
	public String mTime;
	/**
	 * 内容
	 */
	public String mContent;

	public TimeItem(String time) {
		super();
		this.mTime = time;
	}

	public TimeItem(String time, String content) {
		super();
		this.mTime = time;
		this.mContent = content;
	}

	public String getTime() {
		return mTime;
	}

	public void setTime(String time) {
		this.mTime = time;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

}
