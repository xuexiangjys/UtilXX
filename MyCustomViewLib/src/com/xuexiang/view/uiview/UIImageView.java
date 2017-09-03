/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 drakeet (http://drakeet.me)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.xuexiang.view.uiview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.xuexiang.R;

/**
 * Created by drakeet on 3/27/15.
 */
public class UIImageView extends ImageView {

	private int mWidth;
	private int mHeight;
	private int mPaintAlpha = 48;

	private int mPressedColor;
	private Paint mPaint;
	private int mShapeType;
	private int mRadius;

	public UIImageView(Context context) {
		super(context);
	}

	public UIImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public UIImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(final Context context, final AttributeSet attrs) {
		if (isInEditMode())
			return;
		final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UIButton);
		mPressedColor = typedArray.getColor(R.styleable.UIButton_UB_color_pressed, getResources().getColor(R.color.color_pressed));
		mPaintAlpha = typedArray.getInteger(R.styleable.UIButton_UB_alpha_pressed, mPaintAlpha);
		mShapeType = typedArray.getInt(R.styleable.UIButton_UB_shape_type, 1);
		mRadius = typedArray.getDimensionPixelSize(R.styleable.UIButton_UB_radius, getResources().getDimensionPixelSize(R.dimen.ui_radius));
		typedArray.recycle();
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(mPressedColor);
		this.setWillNotDraw(false);
		mPaint.setAlpha(0);
		mPaint.setAntiAlias(true);
		this.setDrawingCacheEnabled(true);
		this.setClickable(true);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mPaint == null)
			return;
		if (mShapeType == 0) {
			canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2.1038f, mPaint);
		} else {
			RectF rectF = new RectF();
			rectF.set(0, 0, mWidth, mHeight);
			canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPaint.setAlpha(mPaintAlpha);
			invalidate();
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mPaint.setAlpha(0);
			invalidate();
			break;
		}
		return super.onTouchEvent(event);
	}

	public int getPressedColor() {
		return mPressedColor;
	}

	/**
	 * Set the pressed color.
	 * 
	 * @param pressedColor
	 *            pressed color
	 */
	public void setPressedColor(int pressedColor) {
		mPaint.setColor(mPressedColor);
		invalidate();
	}
}
