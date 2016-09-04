package com.xuexiang.view.pulllayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

/**  
 * 创建时间：2016-6-12 下午11:14:52  
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：PullBase.java  
 **/
public class PullBase extends LinearLayout {

	  private View headerView;
	  private int headerViewHeight;
	  protected View contentView;
	  protected boolean headerShowing = false;
	  private int currentHeaderMargin = 0;
	  private int maxMargin;
	  protected boolean containAbsListView = false;

	  public PullBase(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    setOrientation(1);
	  }

	  protected void addHeaderView(View headerView) {
	    addHeaderView(headerView, 0);
	  }

	  protected void addHeaderView(View headerView, int height) {
	    this.headerView = headerView;
	    addView(headerView, 0);
	    measureHeader(headerView, height);
	    this.headerViewHeight = headerView.getMeasuredHeight();
	    LinearLayout.LayoutParams p = (LinearLayout.LayoutParams)headerView.getLayoutParams();

	    this.currentHeaderMargin = (-this.headerViewHeight);
	  }

	  private void measureHeader(View headerView, int height) {
	    int childHeightSpec;
	    LinearLayout.LayoutParams p = (LinearLayout.LayoutParams)headerView.getLayoutParams();
	    if (p == null) {
	      int h = (height == 0) ? -2 : height;
	      p = new LinearLayout.LayoutParams(-1, h);
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

	  public void hideHeader() {
	    LinearLayout.LayoutParams p = (LinearLayout.LayoutParams)this.headerView.getLayoutParams();
	    p.topMargin = (-this.headerViewHeight);
	    this.headerView.setLayoutParams(p);
	    this.currentHeaderMargin = (-this.headerViewHeight);
	    this.headerShowing = false;
	  }

	  public void showHeader() {
	    LinearLayout.LayoutParams p = (LinearLayout.LayoutParams)this.headerView.getLayoutParams();
	    p.topMargin = 0;
	    this.headerView.setLayoutParams(p);
	    this.currentHeaderMargin = 0;
	    this.headerShowing = true;
	  }

	  protected void setMaxMargin(int maxMargin) {
	    this.maxMargin = maxMargin;
	  }

	  public int getHeaderHeight() {
	    return this.headerViewHeight;
	  }

	  public View getHeaderView() {
	    return this.headerView;
	  }

	  protected void addContentView(View contentView) {
	    this.contentView = contentView;
	    if (contentView instanceof AbsListView)
	      this.containAbsListView = true;

	    addView(contentView);
	  }

	  protected View getContentView() {
	    return this.contentView;
	  }

	  public final void adjustHeader(int margin, boolean up) {
	    if (up) {
	      this.currentHeaderMargin -= margin;
	      if ((this.currentHeaderMargin < -this.headerViewHeight) || (Math.abs(this.currentHeaderMargin + this.headerViewHeight) < 20))
	        this.currentHeaderMargin = (-this.headerViewHeight);
	    }
	    else {
	      this.currentHeaderMargin += margin;
	      if ((this.currentHeaderMargin > this.maxMargin) || (Math.abs(this.currentHeaderMargin) < 20))
	        this.currentHeaderMargin = this.maxMargin;
	    }

	    LinearLayout.LayoutParams p = (LinearLayout.LayoutParams)this.headerView.getLayoutParams();
	    p.topMargin = this.currentHeaderMargin;
	    this.headerView.setLayoutParams(p);
	    this.headerShowing = (this.currentHeaderMargin >= -this.headerViewHeight);
	  }

	  public int getCurrentHeaderMargin() {
	    return this.currentHeaderMargin;
	  }
}
