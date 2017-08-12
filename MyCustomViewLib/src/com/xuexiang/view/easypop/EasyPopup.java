package com.xuexiang.view.easypop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.widget.PopupWindowCompat;
import android.transition.Transition;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/**
 * Created by zyyoona7 on 2017/8/3.
 * <p>
 * PopupWindow封装
 */

public class EasyPopup implements PopupWindow.OnDismissListener {
    private static final String TAG = "EasyPopup";

    private static final float DEFAULT_DIM = 0.7f;

    //PopupWindow对象
    private PopupWindow mPopupWindow;

    //context
    private Context mContext;
    //contentView
    protected View mContentView;
    //布局id
    protected int mLayoutId;
    //获取焦点
    protected boolean mFocusable = true;
    //是否触摸之外dismiss
    protected boolean mOutsideTouchable = true;

    //宽高
    protected int mWidth;
    protected int mHeight;

    protected int mAnimationStyle;

    private PopupWindow.OnDismissListener mOnDismissListener;

    //弹出pop时，背景是否变暗
    protected boolean isBackgroundDim;

    //背景变暗时透明度
    protected float mDimValue = DEFAULT_DIM;
    //背景变暗颜色
    @ColorInt
    protected int mDimColor = Color.BLACK;

    //背景变暗的view
    @NonNull
    protected ViewGroup mDimView;

    protected Transition mEnterTransition;
    protected Transition mExitTransition;

    private boolean mFocusAndOutsideEnable;

    private View mAnchorView;
    @VerticalGravity
    private int mVerticalGravity = VerticalGravity.BELOW;
    @HorizontalGravity
    private int mHorizontalGravity = HorizontalGravity.LEFT;
    private int mOffsetX;
    private int mOffsetY;

    //是否只是获取宽高
    //getViewTreeObserver监听时
    private boolean isOnlyGetWH = true;


    public EasyPopup(Context context) {
        this.mContext = context;
    }

    public <T extends EasyPopup> T createPopup() {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow();
        }

        onPopupWindowCreated();


        if (mContentView == null) {
            if (mLayoutId != 0) {
                mContentView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
            } else {
                throw new IllegalArgumentException("The content view is null");
            }
        }
        mPopupWindow.setContentView(mContentView);

        if (mWidth != 0) {
            mPopupWindow.setWidth(mWidth);
        } else {
            mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if (mHeight != 0) {
            mPopupWindow.setHeight(mHeight);
        } else {
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        onPopupWindowViewCreated(mContentView);

        if (mAnimationStyle != 0) {
            mPopupWindow.setAnimationStyle(mAnimationStyle);
        }

        if (!mFocusAndOutsideEnable) {
            //from https://github.com/pinguo-zhouwei/CustomPopwindow
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(false);
            mPopupWindow.setBackgroundDrawable(null);
            //注意下面这三个是contentView 不是PopupWindow，响应返回按钮事件
            mPopupWindow.getContentView().setFocusable(true);
            mPopupWindow.getContentView().setFocusableInTouchMode(true);
            mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        mPopupWindow.dismiss();

                        return true;
                    }
                    return false;
                }
            });
            //在Android 6.0以上 ，只能通过拦截事件来解决
            mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    final int x = (int) event.getX();
                    final int y = (int) event.getY();

                    if ((event.getAction() == MotionEvent.ACTION_DOWN)
                            && ((x < 0) || (x >= mWidth) || (y < 0) || (y >= mHeight))) {
                        //outside
                        return true;
                    } else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        //outside
                        return true;
                    }
                    return false;
                }
            });
        } else {
            mPopupWindow.setFocusable(mFocusable);
            mPopupWindow.setOutsideTouchable(mOutsideTouchable);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mPopupWindow.setOnDismissListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mEnterTransition != null) {
                mPopupWindow.setEnterTransition(mEnterTransition);
            }

            if (mExitTransition != null) {
                mPopupWindow.setExitTransition(mExitTransition);
            }
        }

        return (T) this;
    }

    /****自定义生命周期方法****/

    /**
     * PopupWindow对象创建完成
     */
    protected void onPopupWindowCreated() {
    }

    protected void onPopupWindowViewCreated(View contentView) {
    }

    protected void onPopupWindowDismiss() {
    }

    /****设置属性方法****/

    public <T extends EasyPopup> T setContentView(View contentView) {
        this.mContentView = contentView;
        this.mLayoutId = 0;
        return (T) this;
    }

    public <T extends EasyPopup> T setContentView(@LayoutRes int layoutId) {
        this.mContentView = null;
        this.mLayoutId = layoutId;
        return (T) this;
    }

    public <T extends EasyPopup> T setContentView(View contentView, int width, int height) {
        this.mContentView = contentView;
        this.mLayoutId = 0;
        this.mWidth = width;
        this.mHeight = height;
        return (T) this;
    }

    public <T extends EasyPopup> T setContentView(@LayoutRes int layoutId, int width, int height) {
        this.mContentView = null;
        this.mLayoutId = layoutId;
        this.mWidth = width;
        this.mHeight = height;
        return (T) this;
    }

    public <T extends EasyPopup> T setWidth(int width) {
        this.mWidth = width;
        return (T) this;
    }

    public <T extends EasyPopup> T setHeight(int height) {
        this.mHeight = height;
        return (T) this;
    }

    public <T extends EasyPopup> T setAnchorView(View view) {
        this.mAnchorView = view;
        return (T) this;
    }

    public <T extends EasyPopup> T setVerticalGravity(@VerticalGravity int verticalGravity) {
        this.mVerticalGravity = verticalGravity;
        return (T) this;
    }

    public <T extends EasyPopup> T setHorizontalGravity(@VerticalGravity int horizontalGravity) {
        this.mHorizontalGravity = horizontalGravity;
        return (T) this;
    }

    public <T extends EasyPopup> T setOffsetX(int offsetX) {
        this.mOffsetX = offsetX;
        return (T) this;
    }

    public <T extends EasyPopup> T setOffsetY(int offsetY) {
        this.mOffsetY = offsetY;
        return (T) this;
    }

    public <T extends EasyPopup> T setAnimationStyle(@StyleRes int animationStyle) {
        this.mAnimationStyle = animationStyle;
        return (T) this;
    }

    public <T extends EasyPopup> T setFocusable(boolean focusable) {
        this.mFocusable = focusable;
        return (T) this;
    }

    public <T extends EasyPopup> T setOutsideTouchable(boolean outsideTouchable) {
        this.mOutsideTouchable = outsideTouchable;
        return (T) this;
    }

    /**
     * 是否可以点击PopupWindow之外的地方dismiss
     *
     * @param focusAndOutsideEnable
     * @return
     */
    public <T extends EasyPopup> T setFocusAndOutsideEnable(boolean focusAndOutsideEnable) {
        this.mFocusAndOutsideEnable = focusAndOutsideEnable;
        return (T) this;
    }

    /**
     * 背景变暗支持api>=18
     *
     * @param isDim
     * @return
     */
    public <T extends EasyPopup> T setBackgroundDimEnable(boolean isDim) {
        this.isBackgroundDim = isDim;
        return (T) this;
    }

    public <T extends EasyPopup> T setDimValue(@FloatRange(from = 0.0f, to = 1.0f) float dimValue) {
        this.mDimValue = dimValue;
        return (T) this;
    }

    public <T extends EasyPopup> T setDimColor(@ColorInt int color) {
        this.mDimColor = color;
        return (T) this;
    }

    public <T extends EasyPopup> T setDimView(@NonNull ViewGroup dimView) {
        this.mDimView = dimView;
        return (T) this;
    }

    public <T extends EasyPopup> T setEnterTransition(Transition enterTransition) {
        this.mEnterTransition = enterTransition;
        return (T) this;
    }

    public <T extends EasyPopup> T setExitTransition(Transition exitTransition) {
        this.mExitTransition = exitTransition;
        return (T) this;
    }

    /**
     * 使用此方法需要在创建的时候调用setAnchorView()等属性设置{@see setAnchorView()}
     */
    public void showAsDropDown() {
        if (mAnchorView == null) {
            return;
        }

        showAsDropDown(mAnchorView, mOffsetX, mOffsetY);
    }

    /**
     * PopupWindow自带的显示方法
     *
     * @param anchor
     * @param offsetX
     * @param offsetY
     */
    public void showAsDropDown(View anchor, int offsetX, int offsetY) {
        if (mPopupWindow != null) {
            isOnlyGetWH = true;
            handleBackgroundDim();
            mAnchorView = anchor;
            mOffsetX = offsetX;
            mOffsetY = offsetY;
            addGlobalLayoutListener(mPopupWindow.getContentView());
            mPopupWindow.showAsDropDown(anchor, offsetX, offsetY);
        }
    }

    public void showAsDropDown(View anchor) {
        if (mPopupWindow != null) {
            handleBackgroundDim();
            mAnchorView = anchor;
            isOnlyGetWH = true;
            addGlobalLayoutListener(mPopupWindow.getContentView());
            mPopupWindow.showAsDropDown(anchor);
        }
    }

    public void showAsDropDown(View anchor, int offsetX, int offsetY, int gravity) {
        if (mPopupWindow != null) {
            handleBackgroundDim();
            mAnchorView = anchor;
            mOffsetX = offsetX;
            mOffsetY = offsetY;
            isOnlyGetWH = true;
            addGlobalLayoutListener(mPopupWindow.getContentView());
            PopupWindowCompat.showAsDropDown(mPopupWindow, anchor, offsetX, offsetY, gravity);
        }
    }

    public void showAtLocation(View parent, int gravity, int offsetX, int offsetY) {
        if (mPopupWindow != null) {
            handleBackgroundDim();
            mAnchorView = parent;
            mOffsetX = offsetX;
            mOffsetY = offsetY;
            isOnlyGetWH = true;
            addGlobalLayoutListener(mPopupWindow.getContentView());
            mPopupWindow.showAtLocation(parent, gravity, offsetX, offsetY);
        }
    }

    /**
     * 相对anchor view显示
     * <p>
     * 使用此方法需要在创建的时候调用setAnchorView()等属性设置{@see setAnchorView()}
     */
    public void showAtAnchorView() {
        if (mAnchorView == null) {
            return;
        }
        showAtAnchorView(mAnchorView, mVerticalGravity, mHorizontalGravity);
    }

    /**
     * 相对anchor view显示，适用 宽高不为match_parent
     *
     * @param anchor
     * @param vertGravity
     * @param horizGravity
     */
    public void showAtAnchorView(@NonNull View anchor, @VerticalGravity int vertGravity, @HorizontalGravity int horizGravity) {
        showAtAnchorView(anchor, vertGravity, horizGravity, 0, 0);
    }

    /**
     * 相对anchor view显示，适用 宽高不为match_parent
     *
     * @param anchor
     * @param vertGravity  垂直方向的对齐方式
     * @param horizGravity 水平方向的对齐方式
     * @param x            水平方向的偏移
     * @param y            垂直方向的偏移
     */
    public void showAtAnchorView(@NonNull View anchor, @VerticalGravity final int vertGravity, @HorizontalGravity int horizGravity, int x, int y) {
        if (mPopupWindow == null) {
            return;
        }
        mAnchorView = anchor;
        mOffsetX = x;
        mOffsetY = y;
        mVerticalGravity = vertGravity;
        mHorizontalGravity = horizGravity;
        isOnlyGetWH = false;
        //处理背景变暗
        handleBackgroundDim();
        final View contentView = getContentView();
        addGlobalLayoutListener(contentView);
        contentView.measure(0, View.MeasureSpec.UNSPECIFIED);
        final int measuredW = contentView.getMeasuredWidth();
        final int measuredH = contentView.getMeasuredHeight();

        x = calculateX(anchor, horizGravity, measuredW, x);
        y = calculateY(anchor, vertGravity, measuredH, y);
        PopupWindowCompat.showAsDropDown(mPopupWindow, anchor, x, y, Gravity.NO_GRAVITY);
    }

    /**
     * 根据垂直gravity计算y偏移
     *
     * @param anchor
     * @param vertGravity
     * @param measuredH
     * @param y
     * @return
     */
    private int calculateY(View anchor, int vertGravity, int measuredH, int y) {
        switch (vertGravity) {
            case VerticalGravity.ABOVE:
                //anchor view之上
                y -= measuredH + anchor.getHeight();
                break;
            case VerticalGravity.ALIGN_BOTTOM:
                //anchor view底部对齐
                y -= measuredH;
                break;
            case VerticalGravity.CENTER:
                //anchor view垂直居中
                y -= anchor.getHeight() / 2 + measuredH / 2;
                break;
            case VerticalGravity.ALIGN_TOP:
                //anchor view顶部对齐
                y -= anchor.getHeight();
                break;
            case VerticalGravity.BELOW:
                //anchor view之下
                // Default position.
                break;
        }

        return y;
    }

    /**
     * 根据水平gravity计算x偏移
     *
     * @param anchor
     * @param horizGravity
     * @param measuredW
     * @param x
     * @return
     */
    private int calculateX(View anchor, int horizGravity, int measuredW, int x) {
        switch (horizGravity) {
            case HorizontalGravity.LEFT:
                //anchor view左侧
                x -= measuredW;
                break;
            case HorizontalGravity.ALIGN_RIGHT:
                //与anchor view右边对齐
                x -= measuredW - anchor.getWidth();
                break;
            case HorizontalGravity.CENTER:
                //anchor view水平居中
                x += anchor.getWidth() / 2 - measuredW / 2;
                break;
            case HorizontalGravity.ALIGN_LEFT:
                //与anchor view左边对齐
                // Default position.
                break;
            case HorizontalGravity.RIGHT:
                //anchor view右侧
                x += anchor.getWidth();
                break;
        }

        return x;
    }

    /**
     * 更新PopupWindow位置，校验PopupWindow位置
     * 修复高度或者宽度写死时或者内部有ScrollView时，弹出的位置不准确问题
     *
     * @param width
     * @param height
     * @param anchor
     * @param vertGravity
     * @param horizGravity
     * @param x
     * @param y
     */
    private void updateLocation(int width, int height, @NonNull View anchor, @VerticalGravity final int vertGravity, @HorizontalGravity int horizGravity, int x, int y) {
        final int measuredW = width;
        final int measuredH = height;
        x = calculateX(anchor, horizGravity, measuredW, x);
        y = calculateY(anchor, vertGravity, measuredH, y);
        mPopupWindow.update(anchor, x, y, width, height);
    }

    //监听器，用于PopupWindow弹出时获取准确的宽高
    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            mWidth = getContentView().getWidth();
            mHeight = getContentView().getHeight();
            //只获取宽高时，不执行更新操作
            if (isOnlyGetWH) {
                removeGlobalLayoutListener();
                return;
            }
            if (mPopupWindow == null) {
                return;
            }
            updateLocation(mWidth, mHeight, mAnchorView, mVerticalGravity, mHorizontalGravity, mOffsetX, mOffsetY);
            removeGlobalLayoutListener();
        }
    };

    /**
     * 设置监听器
     *
     * @param listener
     */
    public <T extends EasyPopup> T setOnDismissListener(PopupWindow.OnDismissListener listener) {
        this.mOnDismissListener = listener;
        return (T) this;
    }

    /**
     * 处理背景变暗
     * https://blog.nex3z.com/2016/12/04/%E5%BC%B9%E5%87%BApopupwindow%E5%90%8E%E8%AE%A9%E8%83%8C%E6%99%AF%E5%8F%98%E6%9A%97%E7%9A%84%E6%96%B9%E6%B3%95/
     */
    private void handleBackgroundDim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (isBackgroundDim) {
                if (mDimView != null) {
                    applyDim(mDimView);
                } else {
                    if (getContentView() != null) {
                        Activity activity = (Activity) getContentView().getContext();
                        if (activity != null) {
                            applyDim(activity);
                        }
                    }
                }
            }
        }

    }

    private void applyDim(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        //activity跟布局
//        ViewGroup parent = (ViewGroup) parent1.getChildAt(0);
        Drawable dim = new ColorDrawable(mDimColor);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * mDimValue));
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    private void applyDim(ViewGroup dimView) {
        ViewGroup parent = dimView;
        Drawable dim = new ColorDrawable(mDimColor);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * mDimValue));
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    /**
     * 清除背景变暗
     */
    private void clearBackgroundDim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (isBackgroundDim) {
                if (mDimView != null) {
                    clearDim(mDimView);
                } else {
                    if (getContentView() != null) {
                        Activity activity = (Activity) getContentView().getContext();
                        if (activity != null) {
                            clearDim(activity);
                        }
                    }
                }
            }
        }
    }

    private void clearDim(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        //activity跟布局
//        ViewGroup parent = (ViewGroup) parent1.getChildAt(0);
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    private void clearDim(ViewGroup dimView) {
        ViewGroup parent = dimView;
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    /**
     * 获取PopupWindow中加载的view
     *
     * @return
     */
    public View getContentView() {
        if (mPopupWindow != null) {
            return mPopupWindow.getContentView();
        } else {
            return null;
        }
    }

    /**
     * 获取context
     *
     * @return
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取PopupWindow对象
     *
     * @return
     */
    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    /**
     * 获取view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = null;
        if (getContentView() != null) {
            view = getContentView().findViewById(viewId);
        }
        return (T) view;
    }

    /**
     * 消失
     */
    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    @Override
    public void onDismiss() {
        handleDismiss();
    }

    /**
     * PopupWindow消失后处理一些逻辑
     */
    private void handleDismiss() {
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss();
        }

        removeGlobalLayoutListener();
        //清除背景变暗
        clearBackgroundDim();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        onPopupWindowDismiss();
    }

    private void addGlobalLayoutListener(View contentView) {
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
    }

    private void removeGlobalLayoutListener() {
        if (getContentView() != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                getContentView().getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
            } else {
                getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
            }
        }
    }

}
