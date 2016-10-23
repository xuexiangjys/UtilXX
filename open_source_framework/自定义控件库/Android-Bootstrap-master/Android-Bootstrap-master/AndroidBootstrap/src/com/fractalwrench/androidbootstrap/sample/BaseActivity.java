package com.fractalwrench.androidbootstrap.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.widget.ScrollView;
import butterknife.ButterKnife;

/**
 * Performs ButterKnife binding after adding example views to the root ScrollView
 */
abstract class BaseActivity extends Activity {

    @LayoutRes protected abstract int getContentLayoutId();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

        if (scrollView != null) {
            scrollView.addView(LayoutInflater.from(this).inflate(getContentLayoutId(), scrollView, false));
        }

        ButterKnife.bind(this);
    }

}
