package com.github.megatronking.svg.support.extend;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.github.megatronking.svg.support.R;
import com.github.megatronking.svg.support.SVGDrawable;

/**
 * Support width, height, alpha, tint color for svg images.<br>
 *
 * @author Megatron King
 * @since 2016/10/10 19:11
 */
public class SVGImageView extends ImageView {

    private ColorStateList mSvgColor;
    private float mSvgAlpha;
    private int mSvgWidth;
    private int mSvgHeight;
    private float mSvgRotation;

    public SVGImageView(Context context) {
        this(context, null);
    }

    public SVGImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SVGImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SVGView);
        mSvgColor = a.getColorStateList(R.styleable.SVGView_svgColor);
        mSvgAlpha = a.getFloat(R.styleable.SVGView_svgAlpha, 1.0f);
        mSvgWidth = a.getDimensionPixelSize(R.styleable.SVGView_svgWidth, -1);
        mSvgHeight = a.getDimensionPixelSize(R.styleable.SVGView_svgHeight, -1);
        mSvgRotation = a.getFloat(R.styleable.SVGView_svgRotation, 0) % 360;
        a.recycle();
        resetImageDrawable();
    }

    public void setSvgColor(ColorStateList svgColor) {
        this.mSvgColor = svgColor;
        resetImageDrawable();
    }

    public void setSvgColor(int color) {
        setSvgColor(ColorStateList.valueOf(color));
    }

    public ColorStateList getSvgColor() {
        return mSvgColor;
    }

    public void setSvgWidth(int width) {
        this.mSvgWidth = width;
        resetImageDrawable();
    }

    public int getSvgWidth() {
        return mSvgWidth;
    }

    public void setSvgHeight(int height) {
        this.mSvgHeight = height;
        resetImageDrawable();
    }

    public int getSvgHeight() {
        return mSvgHeight;
    }

    public void setSvgSize(int width, int height) {
        this.mSvgWidth = width;
        this.mSvgHeight = height;
        resetImageDrawable();
    }

    public void setSvgAlpha(float alpha) {
        this.mSvgAlpha = alpha;
        resetImageDrawable();
    }

    public float getSvgAlpha() {
        return mSvgAlpha;
    }

    public void setSvgRotation(float rotation) {
        this.mSvgRotation = rotation;
        resetImageDrawable();
    }

    public float getSvgRotation() {
        return mSvgRotation;
    }

    private void resetImageDrawable() {
        Drawable drawable = getDrawable();
        boolean isNeedReset = drawable != null && (drawable.getIntrinsicWidth() != mSvgWidth
                || drawable.getIntrinsicHeight() != mSvgHeight);
        resetDrawable(drawable);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && isNeedReset) {
            super.setImageDrawable(null);
            super.setImageDrawable(drawable);
        } else {
            invalidate();
        }
    }

    private void resetDrawable(Drawable drawable) {
        if (drawable != null && drawable instanceof SVGDrawable) {
            drawable.mutate();
            ((SVGDrawable)drawable).setTintList(mSvgColor);
            if (mSvgAlpha > 0 && mSvgAlpha <= 1.0f) {
                ((SVGDrawable)drawable).setAlpha((int) (mSvgAlpha * 0xFF));
            }
            if (mSvgWidth > 0) {
                ((SVGDrawable)drawable).setWidth(mSvgWidth);
            }
            if (mSvgHeight > 0) {
                ((SVGDrawable)drawable).setHeight(mSvgHeight);
            }
            if (mSvgRotation != 0) {
                ((SVGDrawable)drawable).setRotation(mSvgRotation);
            }
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        resetImageDrawable();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        resetImageDrawable();
    }
}
