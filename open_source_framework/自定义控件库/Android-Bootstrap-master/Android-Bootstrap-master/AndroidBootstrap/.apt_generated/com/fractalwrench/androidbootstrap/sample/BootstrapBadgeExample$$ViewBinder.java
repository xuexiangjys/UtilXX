// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapBadgeExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapBadgeExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036123, "field 'lonelyBadge' and method 'onLonelyButtonClicked'");
    target.lonelyBadge = finder.castView(view, 2131036123, "field 'lonelyBadge'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onLonelyButtonClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036125, "field 'javaBadgeButton' and method 'onJavaButtonClicked'");
    target.javaBadgeButton = finder.castView(view, 2131036125, "field 'javaBadgeButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onJavaButtonClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036124, "field 'xmlBadgeButton' and method 'onXmlButtonClicked'");
    target.xmlBadgeButton = finder.castView(view, 2131036124, "field 'xmlBadgeButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onXmlButtonClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.lonelyBadge = null;
    target.javaBadgeButton = null;
    target.xmlBadgeButton = null;
  }
}
