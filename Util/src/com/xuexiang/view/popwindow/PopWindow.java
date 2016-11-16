package com.xuexiang.view.popwindow;

import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;


/**
 * 屏幕底部弹出菜单中是一个个可以点击的图片gird item
 * Created by GuDong on 2014/11/24.
 */
public class PopWindow implements View.OnClickListener, View.OnTouchListener {
    /**
     * 设置要分享的内容，不同分享内容 自己实现分享内容的格式
     *
     * @param activity
     * @param shareInfo 具体分享的内容
     */
	private List<ActionItem> mActionItems;
	private OnItemSelectedListerner mListerner;
	private View shareView;
    private View fullMaskView;
    private ViewStub mViewStub;
    private LinearLayout llContent;
    private GridView mGirdView;
    private TextView mTvClose;
    private int mShareItemTextColor;
    private int mHeight = 0;
    private long duration;
    private boolean isShowing = false;
    private Context mContext;

    public PopWindow(Activity context) {
        this.mContext = context;
        setUpView(context);
        mShareItemTextColor = RUtils.getColor(mContext, "black");
        duration = 300;
    }

    public PopWindow(Activity context, String title) {
        this(context);
        setTitle(title);
    }

    private void setUpView(Activity context) {
        shareView = View.inflate(context, RUtils.getLayout(mContext, "layout_share_view"), null);
        fullMaskView = shareView.findViewById(RUtils.getId(context, "full_mask"));
        mViewStub = (ViewStub) shareView.findViewById(RUtils.getId(context, "view_stub"));
        mViewStub.inflate();
        llContent = (LinearLayout) shareView.findViewById(RUtils.getId(context, "ll_content"));
        mGirdView = (GridView) shareView.findViewById(RUtils.getId(context, "gv_share"));
        mTvClose = (TextView) shareView.findViewById(RUtils.getId(context, "tv_close"));
        // add shareView to activity's root view
        ((ViewGroup) context.getWindow().getDecorView()).addView(shareView);
        initListener();
    }

    private void initListener() {
        shareView.setOnTouchListener(this);
        fullMaskView.setOnTouchListener(this);
        llContent.setOnTouchListener(this);
        mTvClose.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == RUtils.getId(mContext, "full_mask")) {
            if (isShowing && event.getAction() == KeyEvent.ACTION_DOWN) {
                hide();
            }
        }
        return true;
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) shareView.findViewById(RUtils.getId(mContext, "tv_share_title"));
        tvTitle.setText(title);
        tvTitle.setVisibility(!TextUtils.isEmpty(title) ? View.VISIBLE : View.GONE);
    }

    public void setTitleColor(int titleColor) {
        TextView tvTitle = (TextView) shareView.findViewById(RUtils.getId(mContext, "tv_share_title"));
        tvTitle.setTextColor(tvTitle.getResources().getColor(titleColor));
    }

    public void show() {
        isShowing = true;
        shareView.setVisibility(View.VISIBLE);
        shareViewAnimator(mHeight, 0, 0.0f, 0.5f);
    }

    public void hide() {
        isShowing = false;
        shareViewAnimator(0, mHeight, 0.5f, 0.0f);
    }

    private void shareViewAnimator(final float start, final float end, final float startAlpha, final float endAlpha) {
        shareViewTranslationAnimator(start, end);
        shareViewAlphaAnimator(startAlpha, endAlpha);
        //shareViewRotationAnimator(0f,30f);
    }

    private void shareViewTranslationAnimator(final float start, final float end) {
        if (Build.VERSION.SDK_INT >= 11) {
            ObjectAnimator bottomAnim = ObjectAnimator.ofFloat(llContent, "translationY", start, end);
            bottomAnim.setDuration(duration);
            bottomAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (start < end) {//close ShareView
                        shareView.setVisibility(View.GONE);
                    } else {//show ShareView
                    }
                }
            });
            bottomAnim.start();
        }
    }

    private void shareViewAlphaAnimator(final float startAlpha, final float endAlpha) {
        if (Build.VERSION.SDK_INT >= 11) {
            ObjectAnimator bottomAnimAlpha = ObjectAnimator.ofFloat(fullMaskView, "alpha", startAlpha, endAlpha);
            bottomAnimAlpha.setDuration(duration);
            bottomAnimAlpha.start();
        }
    }

    public void setAnimatorDuration(long duration) {
        this.duration = duration;
    }

    public void setOnItemSelectedListerner(OnItemSelectedListerner mListerner) {
		this.mListerner = mListerner;
	}

    public void setShareInfo(List<ActionItem> shareInfolist) {
    	mActionItems = shareInfolist;
        mGirdView.setAdapter(new ShareAdapter(mContext, shareInfolist));
        mGirdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListerner.onSelected(mActionItems.get(position));
                hide();
            }
        });
        initHeight();

    }

    private void initHeight() {
        mHeight = getViewHeight(llContent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == RUtils.getId(mContext, "tv_close")) {
            hide();
        }
    }

    /**
     * 设置分享的背景图片
     *
     * @param shareBackground
     */
    public void setShareBackground(int shareBackground) {
        shareView.setBackgroundResource(shareBackground);
    }

    public void setShareItemTextColor(int shareItemTextColor) {
        this.mShareItemTextColor = shareItemTextColor;
    }


    private int getViewHeight(View mView) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        mView.measure(w, h);
        return mView.getMeasuredHeight();
    }

    class ShareAdapter extends BaseAdapter {
        private List<ActionItem> mData;
        private Context mContext;
        private LayoutInflater mInflater;

        public ShareAdapter(Context context, List<ActionItem> data) {
            mContext = context;
            mData = data;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tView = (TextView) mInflater.inflate(RUtils.getLayout(mContext, "share_item"), null);
            ActionItem info = (ActionItem) getItem(position);
            tView.setText(info.mTitle);
            tView.setCompoundDrawablesWithIntrinsicBounds(null, info.mDrawable, null, null);
            tView.setTextColor(mContext.getResources().getColor(mShareItemTextColor));
            return tView;
        }
    }
    public interface OnItemSelectedListerner {
         public void onSelected(ActionItem actionItem);
    }
}
