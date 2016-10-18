// Generated code from Butter Knife. Do not modify!
package com.flyco.dialogsamples.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DialogHomeActivity$$ViewBinder<T extends com.flyco.dialogsamples.ui.DialogHomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361797, "field 'mElv'");
    target.mElv = finder.castView(view, 2131361797, "field 'mElv'");
  }

  @Override public void unbind(T target) {
    target.mElv = null;
  }
}
