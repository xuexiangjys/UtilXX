package com.xuexiang.view.pulllayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;

/**  
 * 创建时间：2016-6-12 下午11:16:16  
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：PullToShowBase.java  
 **/
public abstract class PullToShowBase extends PullBase {
	  private float downY = 0F;

	  public PullToShowBase(Context context, AttributeSet attrs) {
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
	    else if (action == 2)
	      if ((this.containAbsListView) && (((AbsListView)this.contentView).getFirstVisiblePosition() == 0))
	      {
	        if ((((this.headerShowing) || (this.downY - ev.getRawY() < 0F))) && (Math.abs(this.downY - ev.getRawY()) > 10.0F))
	          result = true;
	      }
	      else
	        this.headerShowing = false;
	    return result;
	  }

	  public boolean onTouchEvent(MotionEvent event) {
	    int action = event.getAction();
	    if ((action == 0) && (this.contentView.getHeight() < getHeight()))
	      return true;
	    if (action == 2) {
	      computeTravel(event, false);
	      this.downY = event.getRawY();
	      return true; 
	    }
	    if (action == 1) {
	      computeTravel(event, true);
	    }

	    return super.onTouchEvent(event);
	  }

	  private void computeTravel(MotionEvent ev, boolean actionUp) {
	    float movingY = ev.getRawY();
	    int travel = (int)((this.downY - movingY) / 2F);
	    boolean up = travel > 0;
	    travel = Math.abs(travel);

	    if ((this.containAbsListView) && (((AbsListView)this.contentView).getChildCount() != 0) && (((AbsListView)this.contentView).getChildAt(0).getTop() != 0)) {
	      ((AbsListView)this.contentView).setSelection(0);
	    }

	    move(travel, up, actionUp);
	  }
}
