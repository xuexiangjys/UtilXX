// Generated code from Butter Knife. Do not modify!
package com.example.mycustomview.custom;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WebViewMainActivity$$ViewBinder<T extends com.example.mycustomview.custom.WebViewMainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034259, "field 'btCall'");
    target.btCall = finder.castView(view, 2131034259, "field 'btCall'");
    view = finder.findRequiredView(source, 2131034254, "field 'activityMain'");
    target.activityMain = finder.castView(view, 2131034254, "field 'activityMain'");
    view = finder.findRequiredView(source, 2131034256, "field 'btUploadPhoto'");
    target.btUploadPhoto = finder.castView(view, 2131034256, "field 'btUploadPhoto'");
    view = finder.findRequiredView(source, 2131034257, "field 'btMovie'");
    target.btMovie = finder.castView(view, 2131034257, "field 'btMovie'");
    view = finder.findRequiredView(source, 2131034255, "field 'btBaidu'");
    target.btBaidu = finder.castView(view, 2131034255, "field 'btBaidu'");
    view = finder.findRequiredView(source, 2131034258, "field 'btMovieFull'");
    target.btMovieFull = finder.castView(view, 2131034258, "field 'btMovieFull'");
  }

  @Override public void unbind(T target) {
    target.btCall = null;
    target.activityMain = null;
    target.btUploadPhoto = null;
    target.btMovie = null;
    target.btBaidu = null;
    target.btMovieFull = null;
  }
}
