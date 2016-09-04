package com.xuexiang.view.expandableview.entity;


/**
 * @author yangyu
 *	功能描述：弹窗内部子类项（绘制标题和图标）
 */
public class IconItem {
	//定义图片对象
	public int mDrawableId;
	//定义文本对象
	public CharSequence mTitle;
	
	public IconItem(int drawableId, CharSequence title){
		this.mDrawableId = drawableId;
		this.mTitle = title;
	}
	
	public IconItem(int drawableId){
		this.mDrawableId = drawableId;
	}
	
	public IconItem(CharSequence title){
		this.mTitle = title;
	}
	
}
