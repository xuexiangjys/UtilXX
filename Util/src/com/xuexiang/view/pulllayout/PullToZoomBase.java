package com.xuexiang.view.pulllayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**  
 * 创建时间：2016-6-12 下午11:18:09  
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：PullToZoomBase.java  
 **/
public abstract class PullToZoomBase extends PullBase {
	  private float downY = 0F;

	  public PullToZoomBase(Context context, AttributeSet attrs) {
	    super(context, attrs);
	  }

	  public abstract void move(int paramInt, boolean paramBoolean1, boolean paramBoolean2);

	  public boolean onInterceptTouchEvent(MotionEvent ev) {
	    boolean result = false;
	    if (this.contentView == null)
	      return true;

	    int action = ev.getAction();
	    if (action == 0)
	      this.downY = ev.getRawY();
	    else if ((action == 2) && 
	      (this.headerShowing)) {
	      result = true;
	    }

	    return result;
	  }

	  public boolean onTouchEvent(MotionEvent event) {
	    int action = event.getAction();
	    if (action == 0)
	      return true;
	    if (action == 2) {
	      computeTravel(event, false);
	      this.downY = event.getRawY();
	      return true; }
	    if (action == 1)
	      computeTravel(event, true);

	    return super.onTouchEvent(event);
	  }

	  private void computeTravel(MotionEvent ev, boolean actionUp) {
	    float movingY = ev.getRawY();
	    int travel = (int)(this.downY - movingY);
	    boolean up = travel > 0;
	    travel = Math.abs(travel);

	    move(travel, up, actionUp);
	  }
}
