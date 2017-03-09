package com.example.testutil.view.avloadingindicatorview;

import android.view.View;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.avloadingindicatorview.AVLoadingIndicatorView;

/**
 * Created by Jack Wang on 2016/8/5.
 */

public class IndicatorActivity extends BaseActivity{

    private AVLoadingIndicatorView avi;

    @Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_indicator);

        String indicator=getIntent().getStringExtra("indicator");
        initTitleBar(indicator);
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);
	}

    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }

	
}
