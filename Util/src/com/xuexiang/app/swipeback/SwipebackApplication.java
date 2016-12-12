package com.xuexiang.app.swipeback;

import android.app.Application;

/**
 * Created by fhf11991 on 2016/7/18.
 */

public class SwipebackApplication extends Application{

    private ActivityLifecycleHelper mActivityLifecycleHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityLifecycleHelper = new ActivityLifecycleHelper();
        registerActivityLifecycleCallbacks(mActivityLifecycleHelper);
    }

    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }
}
