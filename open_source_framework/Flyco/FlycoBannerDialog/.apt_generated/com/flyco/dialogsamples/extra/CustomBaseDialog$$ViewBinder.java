// Generated code from Butter Knife. Do not modify!
package com.flyco.dialogsamples.extra;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CustomBaseDialog$$ViewBinder<T extends com.flyco.dialogsamples.extra.CustomBaseDialog> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361800, "field 'mTvCancel'");
    target.mTvCancel = finder.castView(view, 2131361800, "field 'mTvCancel'");
    view = finder.findRequiredView(source, 2131361801, "field 'mTvExit'");
    target.mTvExit = finder.castView(view, 2131361801, "field 'mTvExit'");
  }

  @Override public void unbind(T target) {
    target.mTvCancel = null;
    target.mTvExit = null;
  }
}
