package com.xuexiang.view.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;

public class LoadingAnimatorView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
	private SurfaceHolder mHolder;
	private Canvas canvas = null;
	// 绘制的线程
	private Thread mThread;
	private PaintFlagsDrawFilter pfd;
	private Bitmap bitmap;
	private Paint mFillpaint; // 填充画笔
	private Paint mBackgroundpaint; // 背景画笔
	private Paint mLoadingTextpaint; // loading文字画笔
	public boolean isLoading = true;
	private int y = 100;

	private int mloadingAnimatorPicture;

	public void setLoadingAnimatorPicture(int loadingAnimatorPicture) {
		mloadingAnimatorPicture = loadingAnimatorPicture;
	}

	private String mLoadText;

	public void setLoadText(String loadText) {
		mLoadText = loadText;
	}

	public LoadingAnimatorView(Context context) {
		super(context);
		mloadingAnimatorPicture = RUtils.getDrawable(getContext(), "ic_launcher"); // 默认值
		mLoadText = "正在加载中"; // 默认值
		init();
	}

	public LoadingAnimatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "LoadingAnimatorView"));
		mLoadText = typedArray.getString(MResource.getIdByName(context, "styleable", "LoadingAnimatorView_loadingAnimatorText"));
		mloadingAnimatorPicture = typedArray.getResourceId(MResource.getIdByName(context, "styleable", "LoadingAnimatorView_loadingAnimatorPicture"), RUtils.getDrawable(getContext(), "ic_launcher"));

		typedArray.recycle();

		init();
	}

	public LoadingAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {

		setFocusable(true);
		setFocusableInTouchMode(true);
		setZOrderOnTop(true);

		mHolder = this.getHolder();
		mHolder.addCallback(this);

		pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

		mFillpaint = new Paint();
		mFillpaint.setColor(Color.RED);
		mFillpaint.setAntiAlias(true);
		mFillpaint.setStyle(Paint.Style.STROKE);

		mBackgroundpaint = new Paint();
		mBackgroundpaint.setColor(Color.GRAY);
		mBackgroundpaint.setAntiAlias(true);
		mBackgroundpaint.setStyle(Paint.Style.STROKE);

		mLoadingTextpaint = new Paint();
		mLoadingTextpaint.setColor(Color.BLACK);
		mLoadingTextpaint.setTextSize(25f);
		mLoadingTextpaint.setTextAlign(Paint.Align.CENTER);
		Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/huawenxingkai.ttf");
		mLoadingTextpaint.setTypeface(font);
		mLoadingTextpaint.setAntiAlias(true);

		Bitmap bmp = BitmapFactory.decodeStream(getResources().openRawResource(mloadingAnimatorPicture));
		bmp = ChangeSize(bmp, bmp.getWidth(), bmp.getHeight());
		bitmap = bmp.extractAlpha();// 获取一个透明图片
		y = bitmap.getHeight();// 初始化y轴坐标
	}

	private static Bitmap ChangeSize(Bitmap bitmap, float widtn, float height) {
		Matrix matrix = new Matrix();
		matrix.postScale(100 / widtn, 100 / height); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}

	// 改变裁剪区域
	private void playAnimator() {
		if (y > 0) {
			y -= 3;
		}
	}

	private void drawLoadingAnimator() {
		try {
			canvas = mHolder.lockCanvas();
			if (canvas != null) {
				if (y <= 0) {
					y = bitmap.getHeight();
					invalidate();
				}
				canvas.drawColor(Color.WHITE);
				canvas.setDrawFilter(pfd);

				Rect targetRect = new Rect(0, 150, getWidth(), getHeight());
				FontMetricsInt fontMetrics = mLoadingTextpaint.getFontMetricsInt();
				int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
				// 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
				canvas.drawText(mLoadText, targetRect.centerX(), baseline, mLoadingTextpaint);

				canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, getHeight() / 2 - bitmap.getHeight() * 0.8f, mBackgroundpaint);
				canvas.save();
				// 裁剪
				canvas.clipRect(getWidth() / 2 - bitmap.getWidth() / 2, y + getHeight() / 2 - bitmap.getHeight() * 0.8f, bitmap.getWidth() + getWidth() / 2, bitmap.getHeight() + getHeight() / 2);
				canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, getHeight() / 2 - bitmap.getHeight() * 0.8f, mFillpaint);
				canvas.restore();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (canvas != null) {
					mHolder.unlockCanvasAndPost(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread = new Thread(this);// 开启绘制线程
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		close();
	}

	// 绘制动画线程
	@Override
	public void run() {
		while (isLoading) {
			drawLoadingAnimator();
			playAnimator();
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		if (isLoading) {
			isLoading = false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			close();
			return false;
		}
		return false;
	}

}
