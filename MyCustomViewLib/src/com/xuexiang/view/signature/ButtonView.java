package com.xuexiang.view.signature;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.xuexiang.R;

/**
 * ButtonView 自定义按钮控件
 * 
 * @author xx
 */
public class ButtonView extends TextView {

	private GradientDrawable gradientDrawable;
	private int mNormalSolidColor, mSelectedSolidColor;

	public ButtonView(Context context) {
		super(context);
	}

	public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setAttributeSet(context, attrs);
	}

	public ButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAttributeSet(context, attrs);
	}

	private void setAttributeSet(Context context, AttributeSet attrs) {
		if (isInEditMode()) {
			return;
		}
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customTextView);
		mNormalSolidColor = a.getColor(R.styleable.customTextView_textSolidColor, Color.TRANSPARENT);
		mSelectedSolidColor = a.getColor(R.styleable.customTextView_textSelectedSolidColor, Color.TRANSPARENT);
		int strokeColor = a.getColor(R.styleable.customTextView_textStrokeColor, Color.TRANSPARENT);
		int radius = a.getDimensionPixelSize(R.styleable.customTextView_textRadius, 0);
		int leftTopRadius = a.getDimensionPixelSize(R.styleable.customTextView_textLeftTopRadius, 0);
		int leftBottomRadius = a.getDimensionPixelSize(R.styleable.customTextView_textLeftBottomRadius, 0);
		int rightTopRadius = a.getDimensionPixelSize(R.styleable.customTextView_textRightTopRadius, 0);
		int rightBottomRadius = a.getDimensionPixelSize(R.styleable.customTextView_textRightBottomRadius, 0);
		int strokeWidth = a.getDimensionPixelSize(R.styleable.customTextView_textStrokeWidth, 0);
		Drawable textDrawable = a.getDrawable(R.styleable.customTextView_textDrawable);
		int normalTextColor = a.getColor(R.styleable.customTextView_textNormalTextColor, Color.TRANSPARENT);
		int selectedTextColor = a.getColor(R.styleable.customTextView_textSelectedTextColor, Color.TRANSPARENT);

		a.recycle();

		gradientDrawable = new GradientDrawable();
		gradientDrawable.setStroke(strokeWidth, strokeColor);
		gradientDrawable.setColor(mNormalSolidColor);

		if (radius > 0) {
			gradientDrawable.setCornerRadius(radius);
		} else if (leftTopRadius > 0 || leftBottomRadius > 0 || rightTopRadius > 0 || rightBottomRadius > 0) {
			gradientDrawable.setCornerRadii(new float[] { leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius });
		}

		setBackgroundDrawable(gradientDrawable);

		if (textDrawable != null) {
			BitmapDrawable bd = (BitmapDrawable) textDrawable;
			ImageSpan imageSpan = new ImageSpan(getContext(), bd.getBitmap());

			String text = "[icon]";
			SpannableString ss = new SpannableString("[icon]");

			ss.setSpan(imageSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			setText(ss);
		}

		if (normalTextColor != 0 && selectedTextColor != 0) {
			// 设置state_selected状态时，和正常状态时文字的颜色
			int[][] states = new int[3][1];
			states[0] = new int[] { android.R.attr.state_selected };
			states[1] = new int[] { android.R.attr.state_pressed };
			states[2] = new int[] {};
			ColorStateList textColorSelect = new ColorStateList(states, new int[] { selectedTextColor, selectedTextColor, normalTextColor });
			setTextColor(textColorSelect);
		}

		if (selectedTextColor != 0 || mSelectedSolidColor != 0) {
			setClickable(true);
		} else {
			setClickable(false);
		}
	}

	/**
	 * 设置填充图片
	 * 
	 * @param drawableId
	 *            drawable id
	 */
	public void setTextDrawable(int drawableId) {
		if (drawableId != 0) {
			Drawable textdrwable = getResources().getDrawable(drawableId);
			BitmapDrawable bd = (BitmapDrawable) textdrwable;
			ImageSpan imageSpan = new ImageSpan(getContext(), bd.getBitmap());

			String text = "[icon]";
			SpannableString ss = new SpannableString("[icon]");

			ss.setSpan(imageSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			setText(ss);
		}
	}

	/**
	 * 
	 * 设置填充颜色
	 * 
	 * @param colorId
	 *            颜色id
	 */
	public void setSolidColor(int colorId) {
		gradientDrawable.setColor(colorId);
		setBackgroundDrawable(gradientDrawable);
	}

	/**
	 * 设置圆角弧度
	 * 
	 * @param leftTopRadius
	 *            左上角弧度
	 * @param leftBottomRadius
	 *            左下角弧度
	 * @param rightTopRadius
	 *            右上角弧度
	 * @param rightBottomRadius
	 *            右下角弧度
	 */
	public void setRadius(int leftTopRadius, int leftBottomRadius, int rightTopRadius, int rightBottomRadius) {
		gradientDrawable.setCornerRadii(new float[] { leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius });
		setBackgroundDrawable(gradientDrawable);
	}

	/**
	 * 设置边框颜色及宽度
	 * 
	 * @param strokeWidth
	 *            边框宽度
	 * @param colorId
	 *            边框颜色 id
	 */
	public void setStrokeColorAndWidth(int strokeWidth, int colorId) {
		gradientDrawable.setStroke(strokeWidth, colorId);
	}

	/**
	 * 设置textView选中状态颜色
	 * 
	 * @param normalTextColor
	 *            正常状态颜色
	 * @param selectedTextColor
	 *            按下状态颜色
	 */
	public void setSelectedTextColor(int normalTextColor, int selectedTextColor) {

		if (normalTextColor != 0 && selectedTextColor != 0) {
			// 设置state_selected状态时，和正常状态时文字的颜色
			setClickable(true);
			int[][] states = new int[3][1];
			states[0] = new int[] { android.R.attr.state_selected };
			states[1] = new int[] { android.R.attr.state_pressed };
			states[2] = new int[] {};
			ColorStateList textColorSelect = new ColorStateList(states, new int[] { selectedTextColor, selectedTextColor, normalTextColor });
			setTextColor(textColorSelect);
		} else {
			setClickable(false);
		}

	}

	/**
	 * 设置textView选中状态颜色
	 * 
	 * @param normalTextColor
	 *            正常状态颜色
	 * @param selectedTextColor
	 *            按下状态颜色
	 */
	public void setSelectedSolidColor(int selectedSolidColor) {
		if (selectedSolidColor != 0) {
			setClickable(true);
			mSelectedSolidColor = selectedSolidColor;
		} else {
			setClickable(false);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (mSelectedSolidColor != Color.TRANSPARENT) {
				gradientDrawable.setColor(mSelectedSolidColor);
				setBackgroundDrawable(gradientDrawable);
				postInvalidate();
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
			if (mSelectedSolidColor != Color.TRANSPARENT) {
				gradientDrawable.setColor(mNormalSolidColor);
				setBackgroundDrawable(gradientDrawable);
			}
		}
		return super.onTouchEvent(event);
	}

}
