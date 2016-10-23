// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapProgressBarExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapProgressBarExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036167, "field 'defaultExample'");
    target.defaultExample = finder.castView(view, 2131036167, "field 'defaultExample'");
    view = finder.findRequiredView(source, 2131036169, "field 'animatedExample'");
    target.animatedExample = finder.castView(view, 2131036169, "field 'animatedExample'");
    view = finder.findRequiredView(source, 2131036171, "field 'stripedExample'");
    target.stripedExample = finder.castView(view, 2131036171, "field 'stripedExample'");
    view = finder.findRequiredView(source, 2131036173, "field 'stripedAnimExample'");
    target.stripedAnimExample = finder.castView(view, 2131036173, "field 'stripedAnimExample'");
    view = finder.findRequiredView(source, 2131036179, "field 'changeExample'");
    target.changeExample = finder.castView(view, 2131036179, "field 'changeExample'");
    view = finder.findRequiredView(source, 2131036175, "field 'sizeExample'");
    target.sizeExample = finder.castView(view, 2131036175, "field 'sizeExample'");
    view = finder.findRequiredView(source, 2131036176, "method 'onAlterProgressBarParameters'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onAlterProgressBarParameters();
        }
      });
    view = finder.findRequiredView(source, 2131036168, "method 'onAnimatedClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onAnimatedClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036172, "method 'onStripedAnimClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onStripedAnimClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036166, "method 'onDefaultClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onDefaultClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036174, "method 'onSizeExampleChangeClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSizeExampleChangeClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036178, "method 'onChangeRoundedProgressBar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onChangeRoundedProgressBar();
        }
      });
    view = finder.findRequiredView(source, 2131036170, "method 'onStripedClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onStripedClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036177, "method 'onAlterProgressBarColor'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onAlterProgressBarColor();
        }
      });
  }

  @Override public void unbind(T target) {
    target.defaultExample = null;
    target.animatedExample = null;
    target.stripedExample = null;
    target.stripedAnimExample = null;
    target.changeExample = null;
    target.sizeExample = null;
  }
}
