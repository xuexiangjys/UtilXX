package com.xuexiang.view.ratingbar;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xuexiang.R;

/**
 * Created by nappannda on 2017/05/16.
 */

public class RotationRatingBar extends BaseRatingBar {

    public RotationRatingBar(Context context) {
        super(context);
    }

    public RotationRatingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RotationRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void emptyRatingBar() {
        int delay = 0;
        for (final ImageView view : mRatingViewStatus.keySet()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setImageDrawable(mEmptyDrawable);
                    mRatingViewStatus.put(view, false);
                }
            }, delay += 5);
        }
    }

    @Override
    protected void fillRatingBar(final int rating) {
        int delay = 0;
        for (final ImageView view : mRatingViewStatus.keySet()) {
            if (view.getId() <= rating) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setImageDrawable(mFilledDrawable);
                        mRatingViewStatus.put(view, true);
                        if (view.getId() == rating) {
                            Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rb_rotation);
                            view.startAnimation(rotation);
                        }
                    }
                }, delay += 15);
            } else {
                view.setImageDrawable(mEmptyDrawable);
                mRatingViewStatus.put(view, false);
            }
        }
    }
}