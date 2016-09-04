package com.xuexiang.view.Gesture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;



public class GestureDrawline extends View {

	private int startX;
	private int startY;
	private int moveX;
	private int moveY;
	private Paint paint;
	private List<GesturePoint> points;
	private List<Pair<GesturePoint, GesturePoint>> lines;
	private Map<String, GesturePoint> autoCheckPointMap;
	private boolean isDrawEnable = true;
	private GesturePoint currentPoint;
	private GestureCallBack callBack;
	private StringBuilder passWordSb;
	private boolean isVerify;
	private String passWord;
	private Context mContext;

	public GestureDrawline(Context context, List<GesturePoint> list, boolean isVerify,
			String passWord, GestureCallBack callBack) {
		super(context);
		mContext = context;
		paint = new Paint(Paint.DITHER_FLAG);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(6);
		paint.setColor(Color.rgb(245, 142, 33));
		paint.setAntiAlias(true);

		this.points = list;
		this.lines = new ArrayList<Pair<GesturePoint, GesturePoint>>();
		initAutoCheckPointMap();
		this.callBack = callBack;
		this.isVerify = isVerify;
		this.passWordSb = new StringBuilder();
		this.passWord = passWord;
	}
	
	private void initAutoCheckPointMap() {
		autoCheckPointMap = new HashMap<String,GesturePoint>();
		autoCheckPointMap.put("1,3", getGesturePointByNum(2));
		autoCheckPointMap.put("1,7", getGesturePointByNum(4));
		autoCheckPointMap.put("1,9", getGesturePointByNum(5));
		autoCheckPointMap.put("2,8", getGesturePointByNum(5));
		autoCheckPointMap.put("3,7", getGesturePointByNum(5));
		autoCheckPointMap.put("3,9", getGesturePointByNum(6));
		autoCheckPointMap.put("4,6", getGesturePointByNum(5));
		autoCheckPointMap.put("7,9", getGesturePointByNum(8));
	}
	
	private GesturePoint getGesturePointByNum(int num) {
		for (GesturePoint point : points) {
			if (point.getNum() == num) {
				return point;
			}
		}
		return null;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (Pair<GesturePoint, GesturePoint> line : lines) {
			canvas.drawLine(line.first.getCenterX(), line.first.getCenterY(),
					line.second.getCenterX(), line.second.getCenterY(), paint);
		}
		
		canvas.drawLine(startX, startY, moveX, moveY, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isDrawEnable == false) {
			return true;
		}
		paint.setColor(Color.rgb(245, 142, 33));
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) event.getX();
			startY = (int) event.getY();
			moveX=(int)event.getX();
			moveY=(int)event.getY();
			currentPoint = getPointAt(startX, startY);
			if (currentPoint != null) {
				currentPoint.setPointState(mContext, States.POINT_STATE_SELECTED);
				passWordSb.append(currentPoint.getNum());
			}
			break;
		case MotionEvent.ACTION_MOVE:
			GesturePoint pointAt = getPointAt((int) event.getX(), (int) event.getY());
			if (currentPoint == null && pointAt == null) {
				return true;
			} else {
				if (currentPoint == null) {
					currentPoint = pointAt;
					currentPoint.setPointState(mContext, States.POINT_STATE_SELECTED);
					passWordSb.append(currentPoint.getNum());
				}
			}
			if (pointAt == null || currentPoint.equals(pointAt) || States.POINT_STATE_SELECTED == pointAt.getPointState()) {
				startX=currentPoint.getCenterX();
				startY=currentPoint.getCenterY();
				moveX=(int)event.getX();
				moveY=(int)event.getY();
				invalidate();
			} else {
				startX=currentPoint.getCenterX();
				startY=currentPoint.getCenterY();
				moveX=pointAt.getCenterX();
				moveY=pointAt.getCenterY();
			    invalidate();
				pointAt.setPointState(mContext, States.POINT_STATE_SELECTED);
				
				GesturePoint betweenPoint = getBetweenCheckPoint(currentPoint, pointAt);
				if (betweenPoint != null && States.POINT_STATE_SELECTED != betweenPoint.getPointState()) {
					Pair<GesturePoint, GesturePoint> line1 = new Pair<GesturePoint, GesturePoint>(currentPoint, betweenPoint);
					lines.add(line1);
					passWordSb.append(betweenPoint.getNum());
					Pair<GesturePoint, GesturePoint> line2 = new Pair<GesturePoint, GesturePoint>(betweenPoint, pointAt);
					lines.add(line2);
					passWordSb.append(pointAt.getNum());
					betweenPoint.setPointState(mContext, States.POINT_STATE_SELECTED);
					currentPoint = pointAt;
				} else {
					Pair<GesturePoint, GesturePoint> line = new Pair<GesturePoint, GesturePoint>(currentPoint, pointAt);
					lines.add(line);
					passWordSb.append(pointAt.getNum());
					currentPoint = pointAt;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if(currentPoint==null)
				return true;
			startX=currentPoint.getCenterX();
			startY=currentPoint.getCenterY();
			if(moveX!=currentPoint.getCenterX()||moveY!=currentPoint.getCenterY()){
				moveX=currentPoint.getCenterX();
				moveY=currentPoint.getCenterY();
			}
			invalidate();
			if (isVerify) {
				if (passWord.equals(passWordSb.toString())) {
					callBack.checkSuccess();
				} else {
					callBack.checkFail();
				}
			} else {
				callBack.onGestureCodeInput(passWordSb.toString());
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	public void clearDrawlineState(long delayTime) {
		if (delayTime > 0) {
			isDrawEnable = false;
			drawErrorPathTip();
		}
		new Handler().postDelayed(new clearStateRunnable(), delayTime);
	}
	
	final class clearStateRunnable implements Runnable {
		public void run() {
			passWordSb = new StringBuilder();
			lines.clear();
			for (GesturePoint point : points) {
				point.setPointState(mContext, States.POINT_STATE_NORMAL);
			}
			invalidate();
			isDrawEnable = true;
		}
	}

	private GesturePoint getPointAt(int x, int y) {
		for (GesturePoint point : points) {
			int leftX = point.getLeftX();
			int rightX = point.getRightX();
			if (!(x >= leftX && x < rightX)) {
				continue;
			}
			int topY = point.getTopY();
			int bottomY = point.getBottomY();
			if (!(y >= topY && y < bottomY)) {
				continue;
			}
			return point;
		}
		return null;
	}
	
	private GesturePoint getBetweenCheckPoint(GesturePoint pointStart, GesturePoint pointEnd) {
		int start = pointStart.getNum();
		int end = pointEnd.getNum();
		String key = null;
		if (start < end) {
			key = start + "," + end;
		} else {
			key = end + "," + start;
		}
		return autoCheckPointMap.get(key);
	}


	private void drawErrorPathTip() {
		paint.setColor(Color.rgb(154, 7, 21));
		for (Pair<GesturePoint, GesturePoint> line : lines) {
			line.first.setPointState(mContext, States.POINT_STATE_WRONG);
			line.second.setPointState(mContext, States.POINT_STATE_WRONG);
		}
		invalidate();
	}


	public interface GestureCallBack {

		public abstract void onGestureCodeInput(String inputCode);

		public abstract void checkSuccess();

		public abstract void checkFail();
	}

}
