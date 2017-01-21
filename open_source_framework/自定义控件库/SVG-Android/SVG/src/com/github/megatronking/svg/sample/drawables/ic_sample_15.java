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
public class ic_sample_15 extends SVGRenderer {

    public ic_sample_15(Context context) {
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
        
        mPath.moveTo(15.0f, 3.0f);
        mPath.rLineTo(2.3f, 2.3f);
        mPath.rLineTo(-2.89f, 2.87f);
        mPath.rLineTo(1.42f, 1.42f);
        mPath.lineTo(18.7f, 6.7f);
        mPath.lineTo(21.0f, 9.0f);
        mPath.lineTo(21.0f, 3.0f);
        mPath.close();
        mPath.moveTo(15.0f, 3.0f);
        mPath.moveTo(3.0f, 9.0f);
        mPath.rLineTo(2.3f, -2.3f);
        mPath.rLineTo(2.87f, 2.89f);
        mPath.rLineTo(1.42f, -1.42f);
        mPath.lineTo(6.7f, 5.3f);
        mPath.lineTo(9.0f, 3.0f);
        mPath.lineTo(3.0f, 3.0f);
        mPath.close();
        mPath.moveTo(3.0f, 9.0f);
        mPath.moveTo(9.0f, 21.0f);
        mPath.rLineTo(-2.3f, -2.3f);
        mPath.rLineTo(2.89f, -2.87f);
        mPath.rLineTo(-1.42f, -1.42f);
        mPath.lineTo(5.3f, 17.3f);
        mPath.lineTo(3.0f, 15.0f);
        mPath.rLineTo(0f, 6.0f);
        mPath.close();
        mPath.moveTo(9.0f, 21.0f);
        mPath.moveTo(21.0f, 15.0f);
        mPath.rLineTo(-2.3f, 2.3f);
        mPath.rLineTo(-2.87f, -2.89f);
        mPath.rLineTo(-1.42f, 1.42f);
        mPath.rLineTo(2.89f, 2.87f);
        mPath.lineTo(15.0f, 21.0f);
        mPath.rLineTo(6.0f, 0f);
        mPath.close();
        mPath.moveTo(21.0f, 15.0f);
        
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