// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapThumbnailExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapThumbnailExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036192, "field 'sizeChange' and method 'onSizeChangeExampleClicked'");
    target.sizeChange = finder.castView(view, 2131036192, "field 'sizeChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSizeChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036190, "field 'borderChange' and method 'onBorderChangeExampleClicked'");
    target.borderChange = finder.castView(view, 2131036190, "field 'borderChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onBorderChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036195, "field 'setResourceExample'");
    target.setResourceExample = finder.castView(view, 2131036195, "field 'setResourceExample'");
    view = finder.findRequiredView(source, 2131036191, "field 'roundedChange' and method 'onRoundedChangeExampleClicked'");
    target.roundedChange = finder.castView(view, 2131036191, "field 'roundedChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onRoundedChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036188, "field 'imageChange' and method 'onImageChangeExampleClicked'");
    target.imageChange = finder.castView(view, 2131036188, "field 'imageChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onImageChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036194, "field 'setDrawableExample'");
    target.setDrawableExample = finder.castView(view, 2131036194, "field 'setDrawableExample'");
    view = finder.findRequiredView(source, 2131036193, "field 'setBitmapExample'");
    target.setBitmapExample = finder.castView(view, 2131036193, "field 'setBitmapExample'");
    view = finder.findRequiredView(source, 2131036189, "field 'themeChange' and method 'onThemeChangeExampleClicked'");
    target.themeChange = finder.castView(view, 2131036189, "field 'themeChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onThemeChangeExampleClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.sizeChange = null;
    target.borderChange = null;
    target.setResourceExample = null;
    target.roundedChange = null;
    target.imageChange = null;
    target.setDrawableExample = null;
    target.setBitmapExample = null;
    target.themeChange = null;
  }
}
