package com.xuexiang.view.circularprogressbar;

import android.graphics.Canvas;
import android.graphics.Paint;

interface PBDelegate {
  void draw(Canvas canvas, Paint paint);

  void start();

  void stop();

  void progressiveStop(CircularProgressDrawable.OnEndListener listener);
}
