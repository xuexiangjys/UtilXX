// Generated code from Butter Knife. Do not modify!
package com.flyco.dialogsamples.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CustomPopupActivity$SimpleCustomPop$$ViewBinder<T extends com.flyco.dialogsamples.ui.CustomPopupActivity.SimpleCustomPop> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361812, "field 'mTvItem4'");
    target.mTvItem4 = finder.castView(view, 2131361812, "field 'mTvItem4'");
    view = finder.findRequiredView(source, 2131361810, "field 'mTvItem2'");
    target.mTvItem2 = finder.castView(view, 2131361810, "field 'mTvItem2'");
    view = finder.findRequiredView(source, 2131361811, "field 'mTvItem3'");
    target.mTvItem3 = finder.castView(view, 2131361811, "field 'mTvItem3'");
    view = finder.findRequiredView(source, 2131361809, "field 'mTvItem1'");
    target.mTvItem1 = finder.castView(view, 2131361809, "field 'mTvItem1'");
  }

  @Override public void unbind(T target) {
    target.mTvItem4 = null;
    target.mTvItem2 = null;
    target.mTvItem3 = null;
    target.mTvItem1 = null;
  }
}
