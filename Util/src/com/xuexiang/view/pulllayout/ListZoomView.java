package com.xuexiang.view.pulllayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.xuexiang.util.resource.MResource;

/**  
 * 创建时间：2016-6-12 下午11:20:41  
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：ListZoomView.java  
 **/
public class ListZoomView extends ListView {
	  private int maxHeaderHeight = 0;
	  private int headerHeight;
	  private int currentHeaderHeight;
	  private boolean zooming = false;
	  private float downY = 0F;
	  private View headerView;

	  public ListZoomView(Context context, AttributeSet attrs)
	  {
	    super(context, attrs);

	    TypedArray a = context.obtainStyledAttributes(attrs, MResource.getIdsByName(context, "styleable", "pull"));
	    int layout = a.getResourceId(MResource.getIdByName(context, "styleable", "pull_header"), 0);
	    this.maxHeaderHeight = a.getLayoutDimension(MResource.getIdByName(context, "styleable", "pull_maxHeaderHeight"), 0);
	    int minHeaderHeight = a.getLayoutDimension(MResource.getIdByName(context, "styleable", "pull_minHeaderHeight"), 0);
	    a.recycle();
	    if (layout == 0)
	      throw new RuntimeException("ListZoomView haven't header view.");

	    if (this.maxHeaderHeight == 0) {
	      throw new RuntimeException("ListZoomView maxHeaderHeight must be set.");
	    }

	    this.headerView = LayoutInflater.from(context).inflate(layout, null);
	    addHeaderView(this.headerView, minHeaderHeight);
	    this.currentHeaderHeight = this.headerHeight;
	  }

	  protected void addHeaderView(View headerView, int height) {
	    this.headerView = headerView;
	    addHeaderView(headerView);
	    measureHeader(headerView, height);
	    this.headerHeight = headerView.getMeasuredHeight();
	    AbsListView.LayoutParams p = (AbsListView.LayoutParams)headerView.getLayoutParams();
	  }

	  private void measureHeader(View headerView, int height)
	  {
	    int childHeightSpec;
	    AbsListView.LayoutParams p = (AbsListView.LayoutParams)headerView.getLayoutParams();
	    if (p == null) {
	      int h = (height == 0) ? -2 : height;
	      p = new AbsListView.LayoutParams(-1, h);
	    } else if (height != 0) {
	      p.height = height;
	    }
	    headerView.setLayoutParams(p);
	    int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
	    int lpHeight = p.height;

	    if (lpHeight > 0)
	      childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, 1073741824);
	    else
	      childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, 0);

	    headerView.measure(childWidthSpec, childHeightSpec);
	  }

	  public boolean onInterceptTouchEvent(MotionEvent event)
	  {
	    int action = event.getAction();
	    boolean r = false;
	    if ((action == 0) && (this.headerView.getTop() == getTop())) {
	      this.downY = event.getRawY();
	    } else if ((action == 2) && (this.headerView.getTop() == getTop())) {
	      boolean upwards = this.downY - event.getRawY() > 0F;
	      if (this.headerView.getHeight() >= this.headerHeight)
	        r = (!(upwards)) || (this.headerView.getTop() != getTop());
	    }

	    return r;
	  }

	  public boolean onTouchEvent(MotionEvent event) {
	    int action = event.getAction();
	    if (action == 2) {
	      boolean upwards = this.downY - event.getRawY() > 0F;
	      if ((this.headerView.getHeight() < this.headerHeight) || (this.headerView.getTop() != getTop()) || (
	        (upwards) && (this.headerView.getHeight() == this.headerHeight))) return super.onTouchEvent(event);
	      computeTravel(event, false);
	      this.downY = event.getRawY();
	      return true;
	    }

	    if ((action == 1) && (this.headerView.getTop() == getTop()))
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

	  public void move(int distance, boolean upwards, boolean release) {
	    if (distance > 30) return;

	    if (release) {
	      if (this.headerView.getHeight() > this.headerHeight) {
	        AnimUtil.collapse(this.headerView, this.headerHeight);
	        this.currentHeaderHeight = this.headerHeight;
	      }
	      this.zooming = false;
	      return;
	    }
	    this.zooming = true;
	    resizeHeader(distance, upwards);
	  }

	  private void resizeHeader(int distance, boolean upwards) {
	    distance = (int)(distance / 1.5F);

	    if ((upwards) && (this.headerView.getHeight() > this.headerHeight)) {
	      int tmpHeight = this.currentHeaderHeight - distance;
	      if (tmpHeight < this.headerHeight)
	        tmpHeight = this.headerHeight;

	      this.currentHeaderHeight = tmpHeight;
	      resizeHeight(this.currentHeaderHeight);
	    }
	    if ((!(upwards)) && (this.headerView.getHeight() >= this.headerHeight))
	    {
	      this.currentHeaderHeight += distance;
	      if (this.currentHeaderHeight > this.maxHeaderHeight)
	        this.currentHeaderHeight = this.maxHeaderHeight;

	      resizeHeight(this.currentHeaderHeight);
	    }
	  }

	  private void resizeHeight(int resizeHeight) {
	    AbsListView.LayoutParams params = (AbsListView.LayoutParams)this.headerView.getLayoutParams();
	    if (params == null)
	      params = new AbsListView.LayoutParams(-1, resizeHeight);
	    else
	      params.height = resizeHeight;

	    this.headerView.setLayoutParams(params);
	  }
}
