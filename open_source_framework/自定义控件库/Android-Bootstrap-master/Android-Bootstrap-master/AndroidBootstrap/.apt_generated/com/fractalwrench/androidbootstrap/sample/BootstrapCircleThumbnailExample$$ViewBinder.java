// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapCircleThumbnailExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapCircleThumbnailExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036149, "field 'themeChange' and method 'onThemeChangeExampleClicked'");
    target.themeChange = finder.castView(view, 2131036149, "field 'themeChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onThemeChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036154, "field 'setResourceExample'");
    target.setResourceExample = finder.castView(view, 2131036154, "field 'setResourceExample'");
    view = finder.findRequiredView(source, 2131036151, "field 'sizeChange' and method 'onSizeChangeExampleClicked'");
    target.sizeChange = finder.castView(view, 2131036151, "field 'sizeChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSizeChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036152, "field 'setBitmapExample'");
    target.setBitmapExample = finder.castView(view, 2131036152, "field 'setBitmapExample'");
    view = finder.findRequiredView(source, 2131036148, "field 'imageChange' and method 'onImageChangeExampleClicked'");
    target.imageChange = finder.castView(view, 2131036148, "field 'imageChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onImageChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036150, "field 'borderChange' and method 'onBorderChangeExampleClicked'");
    target.borderChange = finder.castView(view, 2131036150, "field 'borderChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onBorderChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036153, "field 'setDrawableExample'");
    target.setDrawableExample = finder.castView(view, 2131036153, "field 'setDrawableExample'");
  }

  @Override public void unbind(T target) {
    target.themeChange = null;
    target.setResourceExample = null;
    target.sizeChange = null;
    target.setBitmapExample = null;
    target.imageChange = null;
    target.borderChange = null;
    target.setDrawableExample = null;
  }
}
