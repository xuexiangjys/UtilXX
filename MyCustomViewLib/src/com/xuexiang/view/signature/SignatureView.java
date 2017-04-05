package com.xuexiang.view.signature;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xuexiang.R;

/**
 * 签名画板自定义view
 * 
 * @author xx
 */
public class SignatureView extends View {
	/**
	 * 笔画X坐标起点
	 */
	private float mX;
	/**
	 * 笔画Y坐标起点
	 */
	private float mY;
	/**
	 * 手写画笔
	 */
	private Paint mGesturePaint = new Paint();
	/**
	 * 路径
	 */
	private Path mPath = new Path();
	/**
	 * 背景画布
	 */
	private Canvas mCacheCanvas;
	/**
	 * 背景Bitmap缓存
	 */
	private Bitmap mCacheBitmap;

	/**
	 * 保存图片的宽
	 */
	private int mPictureWidth = 300;

	/**
	 * 保存图片的高
	 */
	private int mPictureHeight = 150;

	/**
	 * 是否已经签名
	 */
	private boolean mIsTouched = false;

	/**
	 * 画笔宽度 px；
	 */
	private float mPenSize = 10F;

	/**
	 * 画笔颜色
	 */
	private int mPenColor = Color.BLACK;

	/**
	 * 画板背景底色
	 */
	private int mBackColor = Color.TRANSPARENT;

	public SignatureView(Context context) {
		super(context);
		init(context);
	}

	public SignatureView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAttributeSet(context, attrs);
	}

	public SignatureView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setAttributeSet(context, attrs);
	}

	private void setAttributeSet(Context context, AttributeSet attrs) {
		if (isInEditMode()) {
			return;
		}
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.signatureView);
		mPenSize = typedArray.getDimension(R.styleable.signatureView_PenSize, 10F);
		mPenColor = typedArray.getColor(R.styleable.signatureView_PenColor, Color.BLACK);
		mBackColor = typedArray.getColor(R.styleable.signatureView_BackColor, Color.TRANSPARENT);
		typedArray.recycle();
		init(context);
	}

	public void init(Context context) {
		mGesturePaint.setAntiAlias(true);
		mGesturePaint.setStyle(Style.STROKE);
		mGesturePaint.setStrokeCap(Paint.Cap.ROUND);
		mGesturePaint.setStrokeWidth(mPenSize);
		mGesturePaint.setColor(mPenColor);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mCacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
		mCacheCanvas = new Canvas(mCacheBitmap);
		mCacheCanvas.drawColor(mBackColor);
		mIsTouched = false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchDown(event);
			break;
		case MotionEvent.ACTION_MOVE:
			mIsTouched = true;
			touchMove(event);
			break;
		case MotionEvent.ACTION_UP:
			mCacheCanvas.drawPath(mPath, mGesturePaint);
			mPath.reset();
			break;
		}
		// 更新绘制
		invalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(mCacheBitmap, 0, 0, mGesturePaint);
		// 通过画布绘制多点形成的图形
		canvas.drawPath(mPath, mGesturePaint);
	}

	// 手指点下屏幕时调用
	private void touchDown(MotionEvent event) {

		// mPath.rewind();
		// 重置绘制路线，即隐藏之前绘制的轨迹
		mPath.reset();
		float x = event.getX();
		float y = event.getY();

		mX = x;
		mY = y;
		// mPath绘制的绘制起点
		mPath.moveTo(x, y);
	}

	// 手指在屏幕上滑动时调用
	private void touchMove(MotionEvent event) {
		final float x = event.getX();
		final float y = event.getY();

		final float previousX = mX;
		final float previousY = mY;

		final float dx = Math.abs(x - previousX);
		final float dy = Math.abs(y - previousY);

		// 两点之间的距离大于等于3时，生成贝塞尔绘制曲线
		if (dx >= 3 || dy >= 3) {
			// 设置贝塞尔曲线的操作点为起点和终点的一半
			float cX = (x + previousX) / 2;
			float cY = (y + previousY) / 2;

			// 二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
			mPath.quadTo(previousX, previousY, cX, cY);

			// 第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
			mX = x;
			mY = y;
		}
	}

	/**
	 * 清除画板
	 */
	public void clear() {
		if (mCacheCanvas != null) {
			mIsTouched = false;
			mGesturePaint.setColor(mPenColor);
			if (mBackColor == Color.TRANSPARENT) {
				mCacheCanvas.drawColor(mBackColor, PorterDuff.Mode.CLEAR);
			} else {
				mCacheCanvas.drawColor(mBackColor);
			}
			mGesturePaint.setColor(mPenColor);
			invalidate();
		}
	}

	/**
	 * 保存画板
	 * 
	 * @param path
	 *            保存到路劲
	 */

	public Bitmap save(String path) throws IOException {
		return save(path, false, 0);
	}

	/**
	 * 保存画板
	 * 
	 * @param path
	 *            保存到路劲
	 * @param clearBlank
	 *            是否清除空白区域
	 * @param blank
	 *            边缘空白区域
	 */
	public Bitmap save(String path, boolean clearBlank, int blank) throws IOException {

		Bitmap bitmap = zoom(mCacheBitmap, mPictureWidth, mPictureHeight);
		if (clearBlank) {
			bitmap = clearBlank(bitmap, blank);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
		byte[] buffer = bos.toByteArray();
		if (buffer != null) {
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			OutputStream outputStream = new FileOutputStream(file);
			outputStream.write(buffer);
			outputStream.close();
		}
		return bitmap;
	}
	
	/**
	 * 放大缩小图片
	 * 
	 * @param bitmap
	 *            源Bitmap
	 * @param w
	 *            宽
	 * @param h
	 *            高
	 * @return 目标Bitmap
	 */
	public static Bitmap zoom(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidht = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidht, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newbmp;
	}

	/**
	 * 获取画板的bitmap
	 * 
	 * @return
	 */
	public Bitmap getBitMap() {
		setDrawingCacheEnabled(true);
		buildDrawingCache();
		Bitmap bitmap = getDrawingCache();
		setDrawingCacheEnabled(false);
		return bitmap;
	}

	/**
	 * 逐行扫描 清楚边界空白。
	 * 
	 * @param bp
	 * @param blank
	 *            边距留多少个像素
	 * @return
	 */
	private Bitmap clearBlank(Bitmap bp, int blank) {
		int HEIGHT = bp.getHeight();
		int WIDTH = bp.getWidth();
		int top = 0, left = 0, right = 0, bottom = 0;
		int[] pixs = new int[WIDTH];
		boolean isStop;
		for (int y = 0; y < HEIGHT; y++) {
			bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
			isStop = false;
			for (int pix : pixs) {
				if (pix != mBackColor) {
					top = y;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		for (int y = HEIGHT - 1; y >= 0; y--) {
			bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
			isStop = false;
			for (int pix : pixs) {
				if (pix != mBackColor) {
					bottom = y;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		pixs = new int[HEIGHT];
		for (int x = 0; x < WIDTH; x++) {
			bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
			isStop = false;
			for (int pix : pixs) {
				if (pix != mBackColor) {
					left = x;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		for (int x = WIDTH - 1; x > 0; x--) {
			bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
			isStop = false;
			for (int pix : pixs) {
				if (pix != mBackColor) {
					right = x;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		if (blank < 0) {
			blank = 0;
		}
		left = left - blank > 0 ? left - blank : 0;
		top = top - blank > 0 ? top - blank : 0;
		right = right + blank > WIDTH - 1 ? WIDTH - 1 : right + blank;
		bottom = bottom + blank > HEIGHT - 1 ? HEIGHT - 1 : bottom + blank;
		return Bitmap.createBitmap(bp, left, top, right - left, bottom - top);
	}

	/**
	 * 设置画笔宽度 默认宽度为10px
	 * 
	 * @param penSize
	 */
	public void setPenSize(int penSize) {
		penSize = penSize > 0 ? penSize : 10;
		mPenSize = penSize;
		mGesturePaint.setStrokeWidth(penSize);

	}

	/**
	 * 设置签名板的背景颜色
	 * 
	 * @param backColor
	 */
	public void setBackColor(int backColor) {
		mBackColor = backColor;
		mCacheCanvas.drawColor(mBackColor);
	}

	/**
	 * 设置画笔颜色
	 * 
	 * @param penColor
	 */
	public void setPenColor(int penColor) {
		mPenColor = penColor;
		mGesturePaint.setColor(penColor);
	}

	/**
	 * 是否有签名
	 * 
	 * @return
	 */
	public boolean getTouched() {
		return mIsTouched;
	}

	/**
	 * 资源回收
	 */
	public void recycle() {
		if (mCacheBitmap != null) {
			mCacheBitmap.recycle();
			mCacheBitmap = null;
		}
		if (mCacheCanvas != null) {
			mCacheCanvas.drawColor(mBackColor, PorterDuff.Mode.CLEAR);
			mCacheCanvas = null;
		}
		mGesturePaint = null;
		mPath = null;
	}

	/**
	 * 设置保存图片的尺寸
	 * @param pictureWidth
	 * @param pictureHeight
	 */
	public void setPictureSize(int pictureWidth, int pictureHeight) {
		mPictureWidth = pictureWidth;
		mPictureHeight = pictureHeight;
	}

	public int getPictureWidth() {
		return mPictureWidth;
	}

	public void setPictureWidth(int pictureWidth) {
		mPictureWidth = pictureWidth;
	}

	public int getPictureHeight() {
		return mPictureHeight;
	}

	public void setPictureHeight(int pictureHeight) {
		mPictureHeight = pictureHeight;
	}

}
