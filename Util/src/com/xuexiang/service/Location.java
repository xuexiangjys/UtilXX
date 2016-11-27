package com.xuexiang.service;
/** 
 * 悬浮控件的位置信息
 * @author xx
 * @Date 2016-11-23 上午10:11:11 
 */
public class Location {
	/** 记录当前手指位置在屏幕上的横坐标*/
  	public float mXInScreen;
  	/** 记录当前手指位置在屏幕上的纵坐标*/
  	public float mYInScreen;
  	/** 记录手指按下时在屏幕上的横坐标,用来判断单击事件*/
  	public float mXDownInScreen;
	/** 记录手指按下时在屏幕上的纵坐标,用来判断单击事件*/
  	public float mYDownInScreen;
  	/** 记录手指按下时在小悬浮窗的View上的横坐标*/
  	public float mXInView;
  	/** 记录手指按下时在小悬浮窗的View上的纵坐标*/
  	public float mYInView;
 	
	public float getXInScreen() {
		return mXInScreen;
	}
	public void setXInScreen(float mXInScreen) {
		this.mXInScreen = mXInScreen;
	}
	public float getYInScreen() {
		return mYInScreen;
	}
	public void setYInScreen(float mYInScreen) {
		this.mYInScreen = mYInScreen;
	}
	public float getXDownInScreen() {
		return mXDownInScreen;
	}
	public void setXDownInScreen(float mXDownInScreen) {
		this.mXDownInScreen = mXDownInScreen;
	}
	public float getYDownInScreen() {
		return mYDownInScreen;
	}
	public void setYDownInScreen(float mYDownInScreen) {
		this.mYDownInScreen = mYDownInScreen;
	}
	public float getXInView() {
		return mXInView;
	}
	public void setXInView(float mXInView) {
		this.mXInView = mXInView;
	}
	public float getYInView() {
		return mYInView;
	}
	public void setYInView(float mYInView) {
		this.mYInView = mYInView;
	}
}
