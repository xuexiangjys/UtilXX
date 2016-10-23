// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapButtonExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapButtonExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036128, "field 'exampleSize' and method 'onSizeExampleClicked'");
    target.exampleSize = finder.castView(view, 2131036128, "field 'exampleSize'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSizeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036130, "field 'exampleCustomStyle'");
    target.exampleCustomStyle = finder.castView(view, 2131036130, "field 'exampleCustomStyle'");
    view = finder.findRequiredView(source, 2131036126, "field 'exampleCorners' and method 'onCornersExampleClicked'");
    target.exampleCorners = finder.castView(view, 2131036126, "field 'exampleCorners'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onCornersExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036127, "field 'exampleOutline' and method 'onOutlineExampleClicked'");
    target.exampleOutline = finder.castView(view, 2131036127, "field 'exampleOutline'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onOutlineExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036129, "field 'exampleTheme' and method 'onThemeExampleClicked'");
    target.exampleTheme = finder.castView(view, 2131036129, "field 'exampleTheme'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onThemeExampleClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.exampleSize = null;
    target.exampleCustomStyle = null;
    target.exampleCorners = null;
    target.exampleOutline = null;
    target.exampleTheme = null;
  }
}
