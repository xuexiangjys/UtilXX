// Generated code from Butter Knife. Do not modify!
package com.example.mycustomview.custom;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SearchBoxDialogActivity$$ViewBinder<T extends com.example.mycustomview.custom.SearchBoxDialogActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231031, "field 'searchInfo'");
    target.searchInfo = finder.castView(view, 2131231031, "field 'searchInfo'");
    view = finder.findRequiredView(source, 2131230864, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131230864, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.searchInfo = null;
    target.toolbar = null;
  }
}
