package com.xuexiang.view.magicindicator.buildins.circlenavigator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.xuexiang.view.magicindicator.NavigatorHelper;
import com.xuexiang.view.magicindicator.UIUtil;
import com.xuexiang.view.magicindicator.abs.IPagerNavigator;

/**
 * 圆圈式的指示器
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class CircleNavigator extends View implements IPagerNavigator, NavigatorHelper.OnNavigatorScrollListener {
    private int mRadius;
    private int mCircleColor;
    private int mStrokeWidth;
    private int mCircleSpacing;
    private Interpolator mStartInterpolator = new LinearInterpolator();
    private Paint mPaint;

    private NavigatorHelper mNavigatorHelper;
    private List<PointF> mCirclePoints = new ArrayList<PointF>();
    private float mIndicatorX;

    // 事件回调
    private boolean mTouchable;
    private OnCircleClickListener mCircleClickListener;
    private float mDownX;
    private float mDownY;
    private int mTouchSlop;

    public CircleNavigator(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mNavigatorHelper = new NavigatorHelper();
        mNavigatorHelper.setNavigatorScrollListener(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mRadius = UIUtil.dip2px(context, 3);
        mCircleSpacing = UIUtil.dip2px(context, 8);
        mStrokeWidth = UIUtil.dip2px(context, 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircles(canvas);
        drawIndicator(canvas);
    }

    private void drawCircles(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(mCircleColor);
        for (int i = 0, j = mCirclePoints.size(); i < j; i++) {
            PointF pointF = mCirclePoints.get(i);
            canvas.drawCircle(pointF.x, pointF.y, mRadius, mPaint);
        }
    }

    private void drawIndicator(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        if (mCirclePoints.size() > 0) {
            canvas.drawCircle(mIndicatorX, getHeight() / 2, mRadius, mPaint);
        }
    }

    private void prepareCirclePoints() {
        mCirclePoints.clear();
        int count = mNavigatorHelper.getTotalCount();
        if (count > 0) {
            int y = getHeight() / 2;
            int measureWidth = count * mRadius * 2 + (count - 1) * mCircleSpacing;
            int centerSpacing = mRadius * 2 + mCircleSpacing;
            int startX = (getWidth() - measureWidth) / 2 + mRadius;
            for (int i = 0; i < count; i++) {
                PointF pointF = new PointF(startX, y);
                mCirclePoints.add(pointF);
                startX += centerSpacing;
            }
            mIndicatorX = mCirclePoints.get(mNavigatorHelper.getCurrentIndex()).x;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mNavigatorHelper.onPageScrolled(position, positionOffset, positionOffsetPixels);

        if (mCirclePoints.isEmpty()) {
            return;
        }

        int currentPosition = Math.min(mCirclePoints.size() - 1, position);
        int nextPosition = Math.min(mCirclePoints.size() - 1, position + 1);
        PointF current = mCirclePoints.get(currentPosition);
        PointF next = mCirclePoints.get(nextPosition);

        mIndicatorX = current.x + (next.x - current.x) * mStartInterpolator.getInterpolation(positionOffset);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mTouchable) {
                    mDownX = x;
                    mDownY = y;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mCircleClickListener != null) {
                    if (Math.abs(x - mDownX) <= mTouchSlop && Math.abs(y - mDownY) <= mTouchSlop) {
                        float max = Float.MAX_VALUE;
                        int index = 0;
                        for (int i = 0; i < mCirclePoints.size(); i++) {
                            PointF pointF = mCirclePoints.get(i);
                            float offset = Math.abs(pointF.x - x);
                            if (offset < max) {
                                max = offset;
                                index = i;
                            }
                        }
                        mCircleClickListener.onClick(index);
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onPageSelected(int position) {
        mNavigatorHelper.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mNavigatorHelper.onPageScrollStateChanged(state);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        prepareCirclePoints();
    }

    @Override
    public void onAttachToMagicIndicator() {
    }

    @Override
    public void notifyDataSetChanged() {
        prepareCirclePoints();
        invalidate();
    }

    @Override
    public void onDetachFromMagicIndicator() {
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        mRadius = radius;
        invalidate();
    }

    public int getCircleColor() {
        return mCircleColor;
    }

    public void setCircleColor(int circleColor) {
        mCircleColor = circleColor;
        invalidate();
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        mStrokeWidth = strokeWidth;
        invalidate();
    }

    public int getCircleSpacing() {
        return mCircleSpacing;
    }

    public void setCircleSpacing(int circleSpacing) {
        mCircleSpacing = circleSpacing;
        invalidate();
    }

    public Interpolator getStartInterpolator() {
        return mStartInterpolator;
    }

    public void setStartInterpolator(Interpolator startInterpolator) {
        mStartInterpolator = startInterpolator;
        if (mStartInterpolator == null) {
            mStartInterpolator = new LinearInterpolator();
        }
    }

    public int getCircleCount() {
        return mNavigatorHelper.getTotalCount();
    }

    public void setCircleCount(int count) {
        mNavigatorHelper.setTotalCount(count);  // 此处不调用invalidate，让外部调用notifyDataSetChanged
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
    }

    @Override
    public void onSelected(int index, int totalCount) {
    }

    @Override
    public void onDeselected(int index, int totalCount) {
    }

    public boolean isTouchable() {
        return mTouchable;
    }

    public void setTouchable(boolean touchable) {
        mTouchable = touchable;
    }

    public OnCircleClickListener getCircleClickListener() {
        return mCircleClickListener;
    }

    public void setCircleClickListener(OnCircleClickListener circleClickListener) {
        if (!mTouchable) {
            mTouchable = true;
        }
        mCircleClickListener = circleClickListener;
    }

    public interface OnCircleClickListener {
        void onClick(int index);
    }
}
