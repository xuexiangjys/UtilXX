// Generated code from Butter Knife. Do not modify!
package com.example.mycustomview.custom;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WebViewMainActivity$$ViewBinder<T extends com.example.mycustomview.custom.WebViewMainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165390, "field 'btBaidu'");
    target.btBaidu = finder.castView(view, 2131165390, "field 'btBaidu'");
    view = finder.findRequiredView(source, 2131165392, "field 'btMovie'");
    target.btMovie = finder.castView(view, 2131165392, "field 'btMovie'");
    view = finder.findRequiredView(source, 2131165394, "field 'btCall'");
    target.btCall = finder.castView(view, 2131165394, "field 'btCall'");
    view = finder.findRequiredView(source, 2131165393, "field 'btMovieFull'");
    target.btMovieFull = finder.castView(view, 2131165393, "field 'btMovieFull'");
    view = finder.findRequiredView(source, 2131165389, "field 'activityMain'");
    target.activityMain = finder.castView(view, 2131165389, "field 'activityMain'");
    view = finder.findRequiredView(source, 2131165391, "field 'btUploadPhoto'");
    target.btUploadPhoto = finder.castView(view, 2131165391, "field 'btUploadPhoto'");
  }

  @Override public void unbind(T target) {
    target.btBaidu = null;
    target.btMovie = null;
    target.btCall = null;
    target.btMovieFull = null;
    target.activityMain = null;
    target.btUploadPhoto = null;
  }
}
