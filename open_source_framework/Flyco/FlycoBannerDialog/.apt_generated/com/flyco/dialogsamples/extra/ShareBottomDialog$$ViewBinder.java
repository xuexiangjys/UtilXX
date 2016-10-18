// Generated code from Butter Knife. Do not modify!
package com.flyco.dialogsamples.extra;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ShareBottomDialog$$ViewBinder<T extends com.flyco.dialogsamples.extra.ShareBottomDialog> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361802, "field 'mLlWechatFriendCircle'");
    target.mLlWechatFriendCircle = finder.castView(view, 2131361802, "field 'mLlWechatFriendCircle'");
    view = finder.findRequiredView(source, 2131361804, "field 'mLlQq'");
    target.mLlQq = finder.castView(view, 2131361804, "field 'mLlQq'");
    view = finder.findRequiredView(source, 2131361805, "field 'mLlSms'");
    target.mLlSms = finder.castView(view, 2131361805, "field 'mLlSms'");
    view = finder.findRequiredView(source, 2131361803, "field 'mLlWechatFriend'");
    target.mLlWechatFriend = finder.castView(view, 2131361803, "field 'mLlWechatFriend'");
  }

  @Override public void unbind(T target) {
    target.mLlWechatFriendCircle = null;
    target.mLlQq = null;
    target.mLlSms = null;
    target.mLlWechatFriend = null;
  }
}
