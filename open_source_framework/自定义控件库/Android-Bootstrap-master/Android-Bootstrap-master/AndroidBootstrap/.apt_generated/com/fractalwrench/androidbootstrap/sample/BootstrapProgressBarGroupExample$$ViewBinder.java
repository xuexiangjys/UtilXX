// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapProgressBarGroupExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapProgressBarGroupExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036186, "field 'bootstrapProgressBar1'");
    target.bootstrapProgressBar1 = finder.castView(view, 2131036186, "field 'bootstrapProgressBar1'");
    view = finder.findRequiredView(source, 2131036187, "field 'bootstrapProgressBar2'");
    target.bootstrapProgressBar2 = finder.castView(view, 2131036187, "field 'bootstrapProgressBar2'");
    view = finder.findRequiredView(source, 2131036183, "field 'groupRound'");
    target.groupRound = finder.castView(view, 2131036183, "field 'groupRound'");
    view = finder.findRequiredView(source, 2131036181, "field 'groupAdd'");
    target.groupAdd = finder.castView(view, 2131036181, "field 'groupAdd'");
    view = finder.findRequiredView(source, 2131036184, "method 'onClickProgressChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickProgressChange();
        }
      });
    view = finder.findRequiredView(source, 2131036182, "method 'onRoundClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onRoundClick();
        }
      });
    view = finder.findRequiredView(source, 2131036180, "method 'addToGroup'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.addToGroup();
        }
      });
  }

  @Override public void unbind(T target) {
    target.bootstrapProgressBar1 = null;
    target.bootstrapProgressBar2 = null;
    target.groupRound = null;
    target.groupAdd = null;
  }
}
