// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapEditTextExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapEditTextExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036160, "field 'changeEnabled'");
    target.changeEnabled = finder.castView(view, 2131036160, "field 'changeEnabled'");
    view = finder.findRequiredView(source, 2131036162, "field 'sizeExample'");
    target.sizeExample = finder.castView(view, 2131036162, "field 'sizeExample'");
    view = finder.findRequiredView(source, 2131036158, "field 'changeRound'");
    target.changeRound = finder.castView(view, 2131036158, "field 'changeRound'");
    view = finder.findRequiredView(source, 2131036156, "field 'changeTheme'");
    target.changeTheme = finder.castView(view, 2131036156, "field 'changeTheme'");
    view = finder.findRequiredView(source, 2131036159, "method 'onChangeEnabledExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onChangeEnabledExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036155, "method 'onChangeThemeExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onChangeThemeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036161, "method 'onSizeExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSizeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036157, "method 'onChangeRoundExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onChangeRoundExampleClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.changeEnabled = null;
    target.sizeExample = null;
    target.changeRound = null;
    target.changeTheme = null;
  }
}
