package com.xuexiang.util.view;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


public class AnimationController {
    public static final int rela1 = Animation.RELATIVE_TO_SELF;
    public static final int rela2 = Animation.RELATIVE_TO_PARENT;

    public static final int Default = -1;
    public static final int Linear = 0;
    public static final int Accelerate = 1;
    public static final int Decelerate = 2;
    public static final int AccelerateDecelerate = 3;
    public static final int Bounce = 4;
    public static final int Overshoot = 5;
    public static final int Anticipate = 6;
    public static final int AnticipateOvershoot = 7;

    private static void setEffect(Animation animation, int interpolatorType, long durationMillis, long delayMillis) {
        switch (interpolatorType) {
            case Linear:
                animation.setInterpolator(new LinearInterpolator());
                break;
            case Accelerate:
                animation.setInterpolator(new AccelerateInterpolator());
                break;
            case Decelerate:
                animation.setInterpolator(new DecelerateInterpolator());
                break;
            case AccelerateDecelerate:
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case Bounce:
                animation.setInterpolator(new BounceInterpolator());
                break;
            case Overshoot:
                animation.setInterpolator(new OvershootInterpolator());
                break;
            case Anticipate:
                animation.setInterpolator(new AnticipateInterpolator());
                break;
            case AnticipateOvershoot:
                animation.setInterpolator(new AnticipateOvershootInterpolator());
                break;
            default:
                break;
        }
        animation.setDuration(durationMillis);
        animation.setStartOffset(delayMillis);
    }

    private static void baseIn(View view, Animation animation, long durationMillis, long delayMillis, AnimationListener animationListener) {
        setEffect(animation, Default, durationMillis, delayMillis);
        view.setVisibility(View.VISIBLE);
        animation.setAnimationListener(animationListener);
        view.startAnimation(animation);
    }

    private static void baseOut(View view, Animation animation, long durationMillis, long delayMillis, AnimationListener animationListener) {
        setEffect(animation, Default, durationMillis, delayMillis);
        animation.setAnimationListener(animationListener);
        view.startAnimation(animation);
    }

    public static void fadeIn(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void fadeOut(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        baseOut(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void slideIn(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 1, rela2, 0, rela2, 0, rela2, 0);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void viticalIn(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 0, rela2, 0, rela2, 1, rela2, 0);
        animation.setFillAfter(true);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void viticalOut(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 0, rela2, 0, rela2, 0, rela2, 1);
        animation.setFillAfter(true);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void slideOut(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 0, rela2, -1, rela2, 0, rela2, 0);
        baseOut(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void scaleIn(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1, rela2, 0.5f, rela2, 0.5f);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void scaleOut(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        ScaleAnimation animation = new ScaleAnimation(1, 0, 1, 0, rela2, 0.5f, rela2, 0.5f);
        baseOut(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void rotateIn(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        RotateAnimation animation = new RotateAnimation(-90, 0, rela1, 0, rela1, 1);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void rotateOut(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        RotateAnimation animation = new RotateAnimation(0, 90, rela1, 0, rela1, 1);
        baseOut(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void scaleRotateIn(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        ScaleAnimation animation1 = new ScaleAnimation(0, 1, 0, 1, rela1, 0.5f, rela1, 0.5f);
        RotateAnimation animation2 = new RotateAnimation(0, 360, rela1, 0.5f, rela1, 0.5f);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void scaleRotateOut(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        ScaleAnimation animation1 = new ScaleAnimation(1, 0, 1, 0, rela1, 0.5f, rela1, 0.5f);
        RotateAnimation animation2 = new RotateAnimation(0, 360, rela1, 0.5f, rela1, 0.5f);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseOut(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void slideFadeIn(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 1, rela2, 0, rela2, 0, rela2, 0);
        AlphaAnimation animation2 = new AlphaAnimation(0, 1);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseIn(view, animation, durationMillis, delayMillis, animationListener);
    }

    public static void slideFadeOut(View view, long durationMillis, long delayMillis, AnimationListener animationListener) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 0, rela2, -1, rela2, 0, rela2, 0);
        AlphaAnimation animation2 = new AlphaAnimation(1, 0);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseOut(view, animation, durationMillis, delayMillis, animationListener);
    }

    public void show(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public void hide(View view) {
        view.setVisibility(View.GONE);
    }

    public void transparent(View view) {
        view.setVisibility(View.INVISIBLE);
    }
}
