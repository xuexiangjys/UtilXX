// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapAlertExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapAlertExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036117, "field 'alert'");
    target.alert = finder.castView(view, 2131036117, "field 'alert'");
    view = finder.findRequiredView(source, 2131036121, "method 'onInteractiveButtonClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onInteractiveButtonClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.alert = null;
  }
}
