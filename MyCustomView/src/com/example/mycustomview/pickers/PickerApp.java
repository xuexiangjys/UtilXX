package com.example.mycustomview.pickers;

import android.app.Application;

import com.example.mycustomview.BuildConfig;
import com.xuexiang.view.pickers.common.AppConfig;
import com.xuexiang.view.pickers.util.LogUtils;

/**
 * @author matt
 * blog: addapp.cn
 */
public class PickerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.setIsDebug(BuildConfig.DEBUG);
        if (!LogUtils.isDebug()) {
            android.util.Log.d(AppConfig.DEBUG_TAG, "logcat is disabled");
        }
    }

}
