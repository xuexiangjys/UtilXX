package com.xuexiang.view.margicbutton;

import android.view.View;

public class Magic {

  public void doWith(View button) {
    MagicAnimation animator = new MagicAnimation(button);
    animator.setDuration(200);
    button.startAnimation(animator);
  }
}
