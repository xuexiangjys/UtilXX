package com.github.megatronking.svg.sample.drawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;

import com.github.megatronking.svg.support.SVGRenderer;

/**
 * AUTO-GENERATED FILE.  DO NOT MODIFY.
 * 
 * This class was automatically generated by the
 * SVG-Generator. It should not be modified by hand.
 */
public class ic_sample_18 extends SVGRenderer {

    public ic_sample_18(Context context) {
        super(context);
        mAlpha = 1.0f;
        mWidth = dip2px(48.0f);
        mHeight = dip2px(48.0f);
    }

    @Override
    public void render(Canvas canvas, int w, int h, ColorFilter filter) {
        
        final float scaleX = w / 24.0f;
        final float scaleY = h / 24.0f;
        
        mPath.reset();
        mRenderPath.reset();
        
        mFinalPathMatrix.setValues(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        mFinalPathMatrix.postScale(scaleX, scaleY);
        
        mPath.moveTo(19.0f, 6.41f);
        mPath.lineTo(17.59f, 5.0f);
        mPath.lineTo(12.0f, 10.59f);
        mPath.lineTo(6.41f, 5.0f);
        mPath.lineTo(5.0f, 6.41f);
        mPath.lineTo(10.59f, 12.0f);
        mPath.lineTo(5.0f, 17.59f);
        mPath.lineTo(6.41f, 19.0f);
        mPath.lineTo(12.0f, 13.41f);
        mPath.lineTo(17.59f, 19.0f);
        mPath.lineTo(19.0f, 17.59f);
        mPath.lineTo(13.41f, 12.0f);
        mPath.close();
        mPath.moveTo(19.0f, 6.41f);
        
        mRenderPath.addPath(mPath, mFinalPathMatrix);
        if (mFillPaint == null) {
            mFillPaint = new Paint();
            mFillPaint.setStyle(Paint.Style.FILL);
            mFillPaint.setAntiAlias(true);
        }
        mFillPaint.setColor(applyAlpha(-16777216, 1.0f));
        mFillPaint.setColorFilter(filter);
        canvas.drawPath(mRenderPath, mFillPaint);

    }

}