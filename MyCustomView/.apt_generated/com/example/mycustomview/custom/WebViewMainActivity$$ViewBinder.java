// Generated code from Butter Knife. Do not modify!
package com.example.mycustomview.custom;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WebViewMainActivity$$ViewBinder<T extends com.example.mycustomview.custom.WebViewMainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034278, "field 'btCall'");
    target.btCall = finder.castView(view, 2131034278, "field 'btCall'");
    view = finder.findRequiredView(source, 2131034276, "field 'btMovie'");
    target.btMovie = finder.castView(view, 2131034276, "field 'btMovie'");
    view = finder.findRequiredView(source, 2131034277, "field 'btMovieFull'");
    target.btMovieFull = finder.castView(view, 2131034277, "field 'btMovieFull'");
    view = finder.findRequiredView(source, 2131034273, "field 'activityMain'");
    target.activityMain = finder.castView(view, 2131034273, "field 'activityMain'");
    view = finder.findRequiredView(source, 2131034274, "field 'btBaidu'");
    target.btBaidu = finder.castView(view, 2131034274, "field 'btBaidu'");
    view = finder.findRequiredView(source, 2131034275, "field 'btUploadPhoto'");
    target.btUploadPhoto = finder.castView(view, 2131034275, "field 'btUploadPhoto'");
  }

  @Override public void unbind(T target) {
    target.btCall = null;
    target.btMovie = null;
    target.btMovieFull = null;
    target.activityMain = null;
    target.btBaidu = null;
    target.btUploadPhoto = null;
  }
}
