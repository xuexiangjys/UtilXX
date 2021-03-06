package com.xuexiang.view.customprogressbar;

import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by C058 on 2016/5/26.
 */
public class MyRoundProgressBar extends MyHoriztalProgressBar {

    private static final int DEFAULT_PROGRESS_RADIUS = 30;
    private int mMaxPaintWidth;
    private int mRadius = dp2px(DEFAULT_PROGRESS_RADIUS);
    private RectF mRectf;

    public MyRoundProgressBar(Context context) {
        this(context, null);
    }

    public MyRoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, RUtils.getStyleable(context, "MyRoundProgressBar"));
        mRadius = (int) ta.getDimension(MResource.getIdByName(context, "styleable", "MyRoundProgressBar_progressbar_radius"), mRadius);
        ta.recycle();

        mReachHeight = mUnReachHeight * 2;
        mMaxPaintWidth = mReachHeight;
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int diameter = mRadius * 2 + getPaddingLeft() + getPaddingRight() + mMaxPaintWidth; //鎺т欢瀹藉害 榛樿鍥涗釜padding涓?嚧
        int width = resolveSize(diameter, widthMeasureSpec);
        int height = resolveSize(diameter, heightMeasureSpec);

        int realWidth = Math.min(width, height);//褰撳楂樿缃笉涓?嚧锛屽彇灏忕殑閭ｄ釜
        mRadius = (realWidth - getPaddingLeft() - getPaddingRight() - mMaxPaintWidth) / 2;
        mRectf = new RectF(0, 0, mRadius * 2, mRadius * 2);
        setMeasuredDimension(realWidth, realWidth);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        canvas.save();
        // mMaxPaintWidth / 鍔犱笂杩欎釜鍙槸涓轰簡鑳借宸插畬鎴愮殑bar瀹屾暣鏄剧ず鍑烘潵
        canvas.translate(getPaddingLeft() + mMaxPaintWidth / 2, getPaddingTop() + mMaxPaintWidth / 2);
        //draw unreachbar
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        //浠庡渾鐐瑰紑濮嬬敾鍦?        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        //draw reachbar
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(mRectf, 0, sweepAngle, false, mPaint);
        //draw text
        String text = getProgress() + "%";
        int textWidth = (int) mPaint.measureText(text);
        int textHeight = (int) ((mPaint.descent() + mPaint.ascent()) / 2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        canvas.drawText(text, mRadius - textWidth / 2, mRadius - textHeight, mPaint);
        canvas.restore();

    }
}
