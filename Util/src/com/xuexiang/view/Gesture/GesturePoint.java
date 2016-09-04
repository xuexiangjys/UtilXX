package com.xuexiang.view.Gesture;

import android.content.Context;
import android.widget.ImageView;

import com.xuexiang.util.resource.ResourceUtils;

public class GesturePoint {
	
	private int leftX;
	private int rightX;
	private int topY;
	private int bottomY;
	private int centerX;
	private int centerY;
	private ImageView image;
	private int pointState;
	private int num;
	
	public GesturePoint(int leftX,int rightX,int topY,int bottomY,ImageView image,int num){
		this.leftX=leftX;
		this.rightX=rightX;
		this.topY=topY;
		this.bottomY=bottomY;
		this.centerX=(this.leftX+this.rightX)/2;
		this.centerY=(this.topY+this.bottomY)/2;
		this.image=image;
		this.num=num;
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	public int getRightX() {
		return rightX;
	}

	public void setRightX(int rightX) {
		this.rightX = rightX;
	}

	public int getTopY() {
		return topY;
	}

	public void setTopY(int topY) {
		this.topY = topY;
	}

	public int getBottomY() {
		return bottomY;
	}

	public void setBottomY(int bottomY) {
		this.bottomY = bottomY;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public int getPointState() {
		return pointState;
	}
	
	public void setPointState(Context context, int state) {
		pointState = state;
		switch (state) {
		case States.POINT_STATE_NORMAL:
			//this.image.setBackgroundResource(RUtils.getDrawable(context, "gesture_node_normal"));
			image.setImageBitmap(ResourceUtils.getImageFromAssets(context, "gesture_node_normal.png"));
			break;
		case States.POINT_STATE_SELECTED:
			//this.image.setBackgroundResource(RUtils.getDrawable(context, "gesture_node_pressed"));
			image.setImageBitmap(ResourceUtils.getImageFromAssets(context, "gesture_node_pressed.png"));
			break;
		case States.POINT_STATE_WRONG:
			//this.image.setBackgroundResource(RUtils.getDrawable(context, "gesture_node_wrong"));
			image.setImageBitmap(ResourceUtils.getImageFromAssets(context, "gesture_node_wrong.png"));
			break;
		default:
			break;
		}
	}

}
