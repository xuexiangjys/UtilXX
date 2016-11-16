package com.xuexiang.util.observer.tag;

/**  
 * 创建时间：2016-6-5 下午2:18:06  
 * @author xuexiang
 **/
public class Event {
    private String mTag;
    private String mMessage;

	/**
	 * @param tag 消息事件标志
	 * @param msg 消息事件内容
	 */
	public Event (String tag, String msg) {
		mTag = tag;
		mMessage = msg;
	}
	
	public String getTag() {
		return mTag;
	}
	
	public String getMessage() {
		return mMessage;
	}
}
