// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapLabelExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapLabelExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036164, "field 'lblChangeHeading' and method 'onHeadingChangeClicked'");
    target.lblChangeHeading = finder.castView(view, 2131036164, "field 'lblChangeHeading'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onHeadingChangeClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036163, "field 'lblChangeColor' and method 'onColorChangeClicked'");
    target.lblChangeColor = finder.castView(view, 2131036163, "field 'lblChangeColor'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onColorChangeClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036165, "field 'lblChangeRounded' and method 'onRoundedChangeClicked'");
    target.lblChangeRounded = finder.castView(view, 2131036165, "field 'lblChangeRounded'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onRoundedChangeClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.lblChangeHeading = null;
    target.lblChangeColor = null;
    target.lblChangeRounded = null;
  }
}
