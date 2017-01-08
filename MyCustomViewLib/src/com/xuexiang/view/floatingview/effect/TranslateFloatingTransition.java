package com.xuexiang.view.floatingview.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import com.xuexiang.view.floatingview.spring.SimpleReboundListener;
import com.xuexiang.view.floatingview.spring.SpringHelper;
import com.xuexiang.view.floatingview.transition.FloatingTransition;
import com.xuexiang.view.floatingview.transition.YumFloating;


/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class TranslateFloatingTransition implements FloatingTransition {

    private float translateY;
    private long duration;

    public TranslateFloatingTransition() {
        translateY = -200f;
        duration = 1500;
    }


    public TranslateFloatingTransition(float translateY, long duration) {
        this.translateY = translateY;
        this.duration = duration;
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        
        ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, translateY);
        translateAnimator.setDuration(duration);
        translateAnimator.setStartDelay(50);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setTranslationY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setTranslationY(0);
                yumFloating.setAlpha(0f);

            }
        });
        
        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(1.0f, 0.0f);
        alphaAnimator.setDuration(duration);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });
        
        SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f,10, 15)
                .reboundListener(new SimpleReboundListener(){
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        yumFloating.setScaleX((float) currentValue);
                        yumFloating.setScaleY((float) currentValue);
                    }
                }).start(yumFloating);
        
        alphaAnimator.start();
        translateAnimator.start();
    }

}
