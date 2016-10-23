// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AwesomeTextViewExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.AwesomeTextViewExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036111, "field 'exampleChange' and method 'onChangeClicked'");
    target.exampleChange = finder.castView(view, 2131036111, "field 'exampleChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onChangeClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036112, "field 'exampleFlash'");
    target.exampleFlash = finder.castView(view, 2131036112, "field 'exampleFlash'");
    view = finder.findRequiredView(source, 2131036113, "field 'exampleRotate'");
    target.exampleRotate = finder.castView(view, 2131036113, "field 'exampleRotate'");
    view = finder.findRequiredView(source, 2131036114, "field 'exampleMultiChange' and method 'onMultiChangeClicked'");
    target.exampleMultiChange = finder.castView(view, 2131036114, "field 'exampleMultiChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onMultiChangeClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036116, "field 'mixAndMatch'");
    target.mixAndMatch = finder.castView(view, 2131036116, "field 'mixAndMatch'");
    view = finder.findRequiredView(source, 2131036115, "field 'exampleBuilder'");
    target.exampleBuilder = finder.castView(view, 2131036115, "field 'exampleBuilder'");
  }

  @Override public void unbind(T target) {
    target.exampleChange = null;
    target.exampleFlash = null;
    target.exampleRotate = null;
    target.exampleMultiChange = null;
    target.mixAndMatch = null;
    target.exampleBuilder = null;
  }
}
