package com.example.mycustomview.indicatordialog;

/**
 * 点击条目
 * 
 * @author XUE
 * 
 */
public class ActionItem {
	private String mTitle;
	private int mDrawableId;

	public ActionItem(String title, int drawableId) {
		mTitle = title;
		mDrawableId = drawableId;
	}

	public String getTitle() {
		return mTitle;
	}

	public ActionItem setTitle(String title) {
		mTitle = title;
		return this;
	}

	public int getDrawableId() {
		return mDrawableId;
	}

	public ActionItem setDrawableId(int drawableId) {
		mDrawableId = drawableId;
		return this;
	}

}
