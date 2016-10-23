// Generated code from Butter Knife. Do not modify!
package com.fractalwrench.androidbootstrap.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BootstrapButtonGroupExample$$ViewBinder<T extends com.fractalwrench.androidbootstrap.sample.BootstrapButtonGroupExample> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131036138, "field 'roundedChange'");
    target.roundedChange = finder.castView(view, 2131036138, "field 'roundedChange'");
    view = finder.findRequiredView(source, 2131036134, "field 'sizeChange'");
    target.sizeChange = finder.castView(view, 2131036134, "field 'sizeChange'");
    view = finder.findRequiredView(source, 2131036132, "field 'orientationChange'");
    target.orientationChange = finder.castView(view, 2131036132, "field 'orientationChange'");
    view = finder.findRequiredView(source, 2131036136, "field 'outlineChange'");
    target.outlineChange = finder.castView(view, 2131036136, "field 'outlineChange'");
    view = finder.findRequiredView(source, 2131036144, "field 'checkedText'");
    target.checkedText = finder.castView(view, 2131036144, "field 'checkedText'");
    view = finder.findRequiredView(source, 2131036145, "field 'radioButton1'");
    target.radioButton1 = finder.castView(view, 2131036145, "field 'radioButton1'");
    view = finder.findRequiredView(source, 2131036143, "field 'childChange'");
    target.childChange = finder.castView(view, 2131036143, "field 'childChange'");
    view = finder.findRequiredView(source, 2131036146, "field 'radioButton2'");
    target.radioButton2 = finder.castView(view, 2131036146, "field 'radioButton2'");
    view = finder.findRequiredView(source, 2131036140, "field 'brandChange'");
    target.brandChange = finder.castView(view, 2131036140, "field 'brandChange'");
    view = finder.findRequiredView(source, 2131036147, "field 'radioButton3'");
    target.radioButton3 = finder.castView(view, 2131036147, "field 'radioButton3'");
    view = finder.findRequiredView(source, 2131036142, "method 'onChildRemoveExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onChildRemoveExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036137, "method 'onRoundedChangeExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onRoundedChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036141, "method 'onChildAddExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onChildAddExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036139, "method 'onBrandChangeExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onBrandChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036133, "method 'onSizeChangeExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSizeChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036135, "method 'onOutlineChangeExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onOutlineChangeExampleClicked();
        }
      });
    view = finder.findRequiredView(source, 2131036131, "method 'onOrientationChangeExampleClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onOrientationChangeExampleClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.roundedChange = null;
    target.sizeChange = null;
    target.orientationChange = null;
    target.outlineChange = null;
    target.checkedText = null;
    target.radioButton1 = null;
    target.childChange = null;
    target.radioButton2 = null;
    target.brandChange = null;
    target.radioButton3 = null;
  }
}
