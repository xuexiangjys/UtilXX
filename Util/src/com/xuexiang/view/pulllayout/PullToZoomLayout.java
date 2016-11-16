package com.xuexiang.view.pulllayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xuexiang.util.resource.MResource;

/**  
 * 创建时间：2016-6-12 下午11:19:30  
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：PullToZoomLayout.java  
 **/
public class PullToZoomLayout extends PullToZoomBase {
	  private View headerView;
	  private int headerHeight;
	  private int maxHeight;
	  private int currentHeight;
	  private boolean zooming = false;

	  public PullToZoomLayout(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    TypedArray a = context.obtainStyledAttributes(attrs, MResource.getIdsByName(context, "styleable", "pull"));
	    int layout = a.getResourceId(MResource.getIdByName(context, "styleable", "pull_header"), 0);
	    this.maxHeight = a.getLayoutDimension(MResource.getIdByName(context, "styleable", "pull_maxHeaderHeight"), 0);
	    int minHeight = a.getLayoutDimension(MResource.getIdByName(context, "styleable", "pull_minHeaderHeight"), 0);
	    a.recycle();
	    if (layout == 0)
	      throw new RuntimeException("PullToZoomLayout haven't header view.");

	    if (this.maxHeight == 0) {
	      throw new RuntimeException("PullToZoomLayout maxHeight must be set.");
	    }

	    this.headerView = LayoutInflater.from(context).inflate(layout, null);
	    addHeaderView(this.headerView, minHeight);
	    this.headerHeight = getHeaderHeight();
	    this.currentHeight = this.headerHeight;
	    this.headerShowing = true;
	  }

	  public void move(int distance, boolean upwards, boolean release) {
	    if (distance > 30) return;

	    if (release)
	    {
	      if (this.headerView.getHeight() > this.headerHeight) {
	        AnimUtil.collapse(this.headerView, this.headerHeight);
	        this.currentHeight = this.headerHeight;
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
	      int tmpHeight = this.currentHeight - distance;
	      if (tmpHeight < this.headerHeight)
	        tmpHeight = this.headerHeight;

	      this.currentHeight = tmpHeight;
	      resizeHeight(this.currentHeight);
	    }
	    if ((!(upwards)) && (this.headerView.getHeight() >= this.headerHeight))
	    {
	      this.currentHeight += distance;
	      if (this.currentHeight > this.maxHeight)
	        this.currentHeight = this.maxHeight;

	      resizeHeight(this.currentHeight);
	    }
	  }

	  private void resizeHeight(int resizeHeight) {
	    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)this.headerView.getLayoutParams();
	    if (params == null)
	      params = new LinearLayout.LayoutParams(-1, resizeHeight);
	    else
	      params.height = resizeHeight;

	    this.headerView.setLayoutParams(params);
	  }

	  protected boolean isHeaderZooming() {
	    return this.zooming;
	  }
}
