package com.xuexiang.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xuexiang.util.resource.ResourceUtils;
import com.xuexiang.view.Gesture.GestureDrawline;
import com.xuexiang.view.Gesture.GestureDrawline.GestureCallBack;
import com.xuexiang.view.Gesture.GesturePoint;


public class GestureContentView extends ViewGroup {

	private int baseNum = 6;
	private int viewWidth;
	private int viewHeight;
	private int blockWidth;

	private List<GesturePoint> mList;
	private Context mContext;
	private GestureDrawline mGestureDrawline;
	
	
	public GestureContentView(Context context, int viewWidth, boolean isVerify, String passWord,GestureCallBack callback) {
		super(context);
		this.viewWidth = viewWidth;
		this.viewHeight = viewWidth;
		blockWidth = viewWidth/3;
		mList = new ArrayList<GesturePoint>();
		mContext = context;
		addChild();
		mGestureDrawline = new GestureDrawline(mContext, mList, isVerify, passWord, callback);
	}
	
	private void addChild(){
		for (int i = 0; i < 9; i++) {
			ImageView image = new ImageView(mContext);
			//image.setBackgroundResource(RUtils.getDrawable(mContext, "gesture_node_normal"));
			image.setImageBitmap(ResourceUtils.getImageFromAssets(mContext, "gesture_node_normal.png"));
			this.addView(image);
			invalidate();
			int row = i / 3;
			int col = i % 3;
			int leftX = col*blockWidth+blockWidth/baseNum;
			int topY = row*blockWidth+blockWidth/baseNum;
			int rightX = col*blockWidth+blockWidth-blockWidth/baseNum;
			int bottomY = row*blockWidth+blockWidth-blockWidth/baseNum;
			GesturePoint p = new GesturePoint(leftX, rightX, topY, bottomY, image,i+1);
			this.mList.add(p);
		}
	}
	
	public void setParentView(ViewGroup parent){
		LayoutParams layoutParams = new LayoutParams(viewWidth, viewHeight);
		this.setLayoutParams(layoutParams);
		mGestureDrawline.setLayoutParams(layoutParams);
		parent.addView(mGestureDrawline);
		parent.addView(this);
	}
	
	/*
	 * 设置子view的位置
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i < getChildCount(); i++) {
			int row = i/3;
			int col = i%3;
			View v = getChildAt(i);
			v.layout(col*blockWidth+blockWidth/baseNum, row*blockWidth+blockWidth/baseNum, 
					col*blockWidth+blockWidth-blockWidth/baseNum, row*blockWidth+blockWidth-blockWidth/baseNum);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            v.measure(widthMeasureSpec, heightMeasureSpec);
        }
	}

	public void clearDrawlineState(long delayTime) {
		mGestureDrawline.clearDrawlineState(delayTime);
	}

}
