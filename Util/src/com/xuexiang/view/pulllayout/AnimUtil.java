package com.xuexiang.view.pulllayout;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AnimUtil {
  public static void collapse(final View view, final int minHeight) {
    final int initialHeight = view.getMeasuredHeight();

    final int offset = initialHeight - minHeight;
    Animation animation = new Animation() {
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (interpolatedTime == 1F) {
          view.getLayoutParams().height = minHeight;
          view.requestLayout();
        } else {
          view.getLayoutParams().height = (initialHeight - (int)(offset * interpolatedTime));
          view.requestLayout();
        }
      }

      public boolean willChangeBounds() {
        return true;
      }

    };
    animation.setDuration((int)(minHeight / view.getContext().getResources().getDisplayMetrics().density));
    view.startAnimation(animation);
  }
}
