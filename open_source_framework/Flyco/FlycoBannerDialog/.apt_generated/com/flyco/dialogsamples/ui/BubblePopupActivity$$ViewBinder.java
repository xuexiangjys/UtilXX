// Generated code from Butter Knife. Do not modify!
package com.flyco.dialogsamples.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BubblePopupActivity$$ViewBinder<T extends com.flyco.dialogsamples.ui.BubblePopupActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361792, "field 'mTvTopLeft' and method 'clickTopLeftBtn'");
    target.mTvTopLeft = finder.castView(view, 2131361792, "field 'mTvTopLeft'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clickTopLeftBtn();
        }
      });
    view = finder.findRequiredView(source, 2131361796, "field 'mTvCenter' and method 'clickCenterBtn'");
    target.mTvCenter = finder.castView(view, 2131361796, "field 'mTvCenter'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clickCenterBtn();
        }
      });
    view = finder.findRequiredView(source, 2131361795, "field 'mTvBottomRight' and method 'clickBottomRightBtn'");
    target.mTvBottomRight = finder.castView(view, 2131361795, "field 'mTvBottomRight'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clickBottomRightBtn();
        }
      });
    view = finder.findRequiredView(source, 2131361794, "field 'mTvBottomLeft' and method 'clickBottomLeftBtn'");
    target.mTvBottomLeft = finder.castView(view, 2131361794, "field 'mTvBottomLeft'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clickBottomLeftBtn();
        }
      });
    view = finder.findRequiredView(source, 2131361793, "field 'mTvTopRight' and method 'clickTopRightBtn'");
    target.mTvTopRight = finder.castView(view, 2131361793, "field 'mTvTopRight'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clickTopRightBtn();
        }
      });
  }

  @Override public void unbind(T target) {
    target.mTvTopLeft = null;
    target.mTvCenter = null;
    target.mTvBottomRight = null;
    target.mTvBottomLeft = null;
    target.mTvTopRight = null;
  }
}
