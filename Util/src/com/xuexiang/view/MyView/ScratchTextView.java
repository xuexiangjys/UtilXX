package com.xuexiang.view.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 刮刮卡类
 * 
 * @author http://yecaoly.taobao.com
 * 
 */
public class ScratchTextView extends TextView {

	private float TOUCH_TOLERANCE;
	private Bitmap mBitmap;// 盖在字上面的背景图
	private Canvas mCanvas;// 用于在mBitmap上画线
	private Paint mPaint;// 用来画线的
	private Path mPath;// 线
	private float mX, mY;
	/**用于判断是否需要盖住下面的文字*/
	private boolean isInited = false;

	public ScratchTextView(Context context) {
		super(context);

	}

	public ScratchTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScratchTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isInited) {
			mCanvas.drawPath(mPath, mPaint);// 把线画到mCanvas上,mCanva会把线画到mBitmap
			canvas.drawBitmap(mBitmap, 0, 0, null);// 把mBitmap画到textview上
		}
	}

	/**
	 * 初始化刮刮卡
	 * 
	 * @param bgColor
	 *            刮刮卡背景色，用于盖住下面的字
	 * @param paintStrokeWidth
	 *            擦除线宽
	 * @param touchTolerance
	 *            画线容差
	 */
	public void initScratchCard(final int bgColor, final int paintStrokeWidth, float touchTolerance) {
		TOUCH_TOLERANCE = touchTolerance;
		mPaint = new Paint();

		mPaint.setAlpha(240);// alpha值：0表示完全透明，255表示完全不透明
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));//
		mPaint.setAntiAlias(true);// 抗锯齿
		mPaint.setDither(true);// 防抖动
		mPaint.setStyle(Paint.Style.STROKE);// 画笔类型： STROKE空心 FILL实心 FILL_AND_STROKE用契形填充
		mPaint.setStrokeJoin(Paint.Join.ROUND);// 画笔接洽点类型
		mPaint.setStrokeCap(Paint.Cap.ROUND);// 画笔笔刷类型
		mPaint.setStrokeWidth(paintStrokeWidth);// 画笔笔刷宽度
		mPath = new Path();

		//建立一张空白的图片用于盖住下面的文字
		mBitmap = Bitmap.createBitmap(getLayoutParams().width, getLayoutParams().height, Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		//为这个图片绘制颜色
		Paint paint=new Paint();
		paint.setColor(Color.parseColor("#A79F9F"));
		paint.setTextSize(30);
		mCanvas.drawColor(bgColor);
		mCanvas.drawText("刮开此图层",getLayoutParams().width/ 4, getLayoutParams().height/2+15, paint);
		isInited = true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!isInited) {
			return true;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchDown(event.getX(), event.getY());
			//更新界面
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touchMove(event.getX(), event.getY());
			//更新界面
			invalidate();
			break;
//		case MotionEvent.ACTION_UP:
//			//没什么效果
//			touchUp(event.getX(), event.getY());
//			//更新界面
//			invalidate();
//			break;
		}
		return true;
	}

	private void touchDown(float x, float y) {
		// 重置绘制路线，即隐藏之前绘制的轨迹
		mPath.reset();
		// mPath绘制的绘制起点
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touchMove(float x, float y) {
		//x和y移动的距离
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		//x,y移动的距离大于画线容差
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			// 二次贝塞尔，实现平滑曲线；mX, mY为操作点，(x + mX) / 2, (y + mY) / 2为终点
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			// 第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
			mX = x;
			mY = y;
		}

	}

//	private void touchUp(float x, float y) {
//		mPath.lineTo(x, y);
//		mCanvas.drawPath(mPath, mPaint);
//		// 重置绘制路线，即隐藏之前绘制的轨迹
//		mPath.reset();
//	}

}
