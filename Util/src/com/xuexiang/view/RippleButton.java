package com.xuexiang.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

/**
 * Created by Administrator on 15-7-27.
 */
public class RippleButton extends Button {
	private float minRadius = 0f;
	private float maxRadius;
	private ValueAnimator rippleAnimator;
	private Paint paint;
	private boolean isShowRipple = false;
	private float radius = 0f;
	private ValueAnimator rightShowAnimator;
	private TranslateAnimation errorAnimator;
	private float textDrawY = 0;
	private static final int DURATION = 500;
	private boolean isShowRight = false;
	private Handler handler;

	public RippleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStrokeWidth(3);
		paint.setTextAlign(Paint.Align.CENTER);
		handler = new Handler();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);
		initAnimator();
		paint.setTextSize(getTextSize());
		Drawable drawable = getBackground();
		drawable.setBounds(0, 0, getWidth(), getHeight());
		drawable.draw(canvas);
		paint.setColor(getCurrentTextColor());
		if (isShowRight) {
			canvas.drawText(getText().toString(), getWidth() / 2, getHeight()
					/ 2 + getTextSize() / 2 - textDrawY, paint);
			canvas.drawText("√", getWidth() / 2, getHeight() + getTextSize()
					* 3 / 2 - textDrawY, paint);
		} else {
			canvas.drawText(getText().toString(), getWidth() / 2, getHeight()
					/ 2 + getTextSize() / 2, paint);
		}
		if (isShowRipple) {
			paint.setColor(Color.parseColor("#7fffffff"));
			canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
		}

	}

	private void initAnimator() {
		if (rippleAnimator == null) {
			maxRadius = getWidth() / 2;
			rippleAnimator = ValueAnimator.ofFloat(minRadius, maxRadius);
			rippleAnimator.setDuration(DURATION);
			rippleAnimator
					.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							radius = (Float) animation.getAnimatedValue();
							if (radius >= maxRadius) {
								isShowRipple = false;
							}
							invalidate();
						}
					});
			rightShowAnimator = ValueAnimator.ofFloat(0, getHeight() / 2
					+ getTextSize());
			rightShowAnimator.setDuration(DURATION);
			rightShowAnimator
					.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							textDrawY = (Float) animation.getAnimatedValue();
							if (textDrawY >= getHeight() / 2 + getTextSize()) {
								isShowRight = false;
								handler.postDelayed(new Runnable() {
									@Override
									public void run() {
										rightShowAnimator.reverse();
									}
								}, 500);
							} else {
								isShowRight = true;
								invalidate();
							}
						}
					});
			errorAnimator = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
					0, Animation.RELATIVE_TO_SELF, 0.05f,
					Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
					0);
			errorAnimator.setDuration(100);
			errorAnimator.setRepeatCount(4);
			errorAnimator.setRepeatMode(Animation.REVERSE);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		isShowRipple = true;
		rippleAnimator.start();
		return super.onTouchEvent(event);
	}

	public void showRight() {
		rightShowAnimator.start();
	}

	public void showError() {
		startAnimation(errorAnimator);
	}
}
