package com.example.mycustomview.pickers;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.mycustomview.R;
import com.xuexiang.view.pickers.util.LogUtils;

/**
 * Activity的基类
 * @author matt
 * blog: addapp.cn
 */
public abstract class BaseActivity extends FragmentActivity {
    protected Context context;
    protected BaseActivity activity;
    protected String className = getClass().getSimpleName();
    private List<LifeCycleListener> lifeCycleListeners = new ArrayList<LifeCycleListener>();

    protected abstract View getContentView();

    protected abstract void setContentViewAfter(View contentView);

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.verbose(className + " onCreate");
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onActivityCreated(this);
        }
        context = getApplicationContext();
        activity = this;
        //不显示标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= 21) {
            setTheme(android.R.style.Theme_Material_Light_NoActionBar);
        } else if (Build.VERSION.SDK_INT >= 13) {
            setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
        } else {
            setTheme(android.R.style.Theme_Light_NoTitleBar);
        }
        //被系统回收后重启恢复
        if (savedInstanceState != null) {
            LogUtils.verbose("savedInstanceState is not null");
            onStateRestore(savedInstanceState);
        }
        //显示界面布局
        View contentView = getContentView();
        if (contentView == null) {
            TextView textView = new TextView(this);
            textView.setBackgroundColor(Color.RED);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText("请先初始化内容视图");
            textView.setTextColor(Color.WHITE);
            contentView = textView;
        }
        LogUtils.verbose(className + " setContentView before");
        setContentViewBefore();
        setContentView(contentView);
        if (isTranslucentStatusBar()) {
            StatusBar.translucent(activity, ContextCompat.getColor(this,R.color.blue));
        }
        setContentViewAfter(contentView);
        LogUtils.verbose(className + " setContentView after");
    }

    protected void onStateRestore(@NonNull Bundle savedInstanceState) {

    }

    protected void setContentViewBefore() {
        LogUtils.verbose(className + " setContentView before");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.verbose(className + " onBackPressed");
    }

    @SuppressLint("NewApi") @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtils.verbose(className + " onSaveInstanceState");
    }

    @CallSuper
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.verbose(className + " onRestoreInstanceState");
    }

    @CallSuper
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.verbose(className + " onConfigurationChanged");
    }

    @CallSuper
    @Override
    public void onRestart() {
        super.onRestart();
        LogUtils.verbose(className + " onRestart");
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onActivityRestarted(this);
        }
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        LogUtils.verbose(className + " onStart");
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onActivityStarted(this);
        }
        //和removeActivity对应
        AppManager.getInstance().addActivity(this);
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        LogUtils.verbose(className + " onResume");
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onActivityResumed(this);
        }
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        LogUtils.verbose(className + " onPause");
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onActivityPaused(this);
        }
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        LogUtils.verbose(className + " onStop");
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onActivityStopped(this);
        }
        // 极端情况下，系统会杀死APP进程，并不执行onDestroy()，
        // 因此需要使用onStop()来释放资源，从而避免内存泄漏。
        AppManager.getInstance().removeActivity(this);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.verbose(className + " onSaveInstanceState");
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.verbose(className + " onDestroy");
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onActivityDestroyed(this);
        }
    }

    @CallSuper
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.verbose(className + " onActivityResult");
    }

    @CallSuper
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtils.verbose(className + " onLowMemory");
        AppManager.getInstance().removeActivity(this);
    }

    protected boolean isTranslucentStatusBar() {
        return true;
    }

    protected <T> T inflateView(@LayoutRes int layoutResource) {
        LogUtils.verbose(className + " inflate view by layout resource");
        //noinspection unchecked
        return (T) LayoutInflater.from(activity).inflate(layoutResource, null);
    }

    protected <T> T findView(@IdRes int id) {
        //noinspection unchecked
        return (T) findViewById(id);
    }

    public void addLifeCycleListener(LifeCycleListener listener) {
        if (lifeCycleListeners.contains(listener)) {
            return;
        }
        lifeCycleListeners.add(listener);
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {
        lifeCycleListeners.remove(listener);
    }

    /**
     * Activity生命周期监听
     */
    public interface LifeCycleListener {

        void onActivityCreated(BaseActivity activity);

        void onActivityResumed(BaseActivity activity);

        void onActivityStarted(BaseActivity activity);

        void onActivityRestarted(BaseActivity activity);

        void onActivityPaused(BaseActivity activity);

        void onActivityStopped(BaseActivity activity);

        void onActivityDestroyed(BaseActivity activity);

    }

}
