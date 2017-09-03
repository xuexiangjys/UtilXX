// Generated code from Butter Knife. Do not modify!
package com.example.mycustomview.custom;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ValidateUIActivity$$ViewBinder<T extends com.example.mycustomview.custom.ValidateUIActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231226, "field 'et_min'");
    target.et_min = finder.castView(view, 2131231226, "field 'et_min'");
    view = finder.findRequiredView(source, 2131231224, "field 'etNotnull'");
    target.etNotnull = finder.castView(view, 2131231224, "field 'etNotnull'");
    view = finder.findRequiredView(source, 2131231225, "field 'et_max'");
    target.et_max = finder.castView(view, 2131231225, "field 'et_max'");
    view = finder.findRequiredView(source, 2131231228, "field 'etPw2'");
    target.etPw2 = finder.castView(view, 2131231228, "field 'etPw2'");
    view = finder.findRequiredView(source, 2131231232, "field 'etNumberLetterUnderline'");
    target.etNumberLetterUnderline = finder.castView(view, 2131231232, "field 'etNumberLetterUnderline'");
    view = finder.findRequiredView(source, 2131231233, "field 'etEmail'");
    target.etEmail = finder.castView(view, 2131231233, "field 'etEmail'");
    view = finder.findRequiredView(source, 2131231227, "field 'etPw1'");
    target.etPw1 = finder.castView(view, 2131231227, "field 'etPw1'");
    view = finder.findRequiredView(source, 2131231229, "field 'etMoney'");
    target.etMoney = finder.castView(view, 2131231229, "field 'etMoney'");
    view = finder.findRequiredView(source, 2131231230, "field 'etOnlyChinese'");
    target.etOnlyChinese = finder.castView(view, 2131231230, "field 'etOnlyChinese'");
    view = finder.findRequiredView(source, 2131231231, "field 'etOnlyNumber'");
    target.etOnlyNumber = finder.castView(view, 2131231231, "field 'etOnlyNumber'");
    view = finder.findRequiredView(source, 2131231221, "field 'tvTextview'");
    target.tvTextview = finder.castView(view, 2131231221, "field 'tvTextview'");
    view = finder.findRequiredView(source, 2131231222, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131231223, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.et_min = null;
    target.etNotnull = null;
    target.et_max = null;
    target.etPw2 = null;
    target.etNumberLetterUnderline = null;
    target.etEmail = null;
    target.etPw1 = null;
    target.etMoney = null;
    target.etOnlyChinese = null;
    target.etOnlyNumber = null;
    target.tvTextview = null;
  }
}
