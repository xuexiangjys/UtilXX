package com.example.mycustomview.smoothprogressbar;

import android.content.Intent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ProgressBar;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.smoothprogressbar.SmoothProgressBar;
import com.xuexiang.view.smoothprogressbar.SmoothProgressBarUtils;
import com.xuexiang.view.smoothprogressbar.SmoothProgressDrawable;

public class SmoothProgressBarActivity extends BaseHeadActivity {

	private ProgressBar mProgressBar1;
	private SmoothProgressBar mGoogleNow;
	private SmoothProgressBar mPocketBar;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_smoothprogressbar;
	}

	@Override
	protected void init() {
		mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar2);
		mPocketBar = (SmoothProgressBar) findViewById(R.id.pocket);

		mProgressBar1.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(this).interpolator(new AccelerateInterpolator()).build());

		mGoogleNow = (SmoothProgressBar) findViewById(R.id.google_now);
		mPocketBar.setSmoothProgressDrawableBackgroundDrawable(SmoothProgressBarUtils.generateDrawableWithColors(getResources().getIntArray(R.array.pocket_background_colors),
				((SmoothProgressDrawable) mPocketBar.getIndeterminateDrawable()).getStrokeWidth()));

		findViewById(R.id.button_make).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SmoothProgressBarActivity.this, MakeCustomActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPocketBar.progressiveStart();
			}
		});

		findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPocketBar.progressiveStop();
			}
		});
	}
}
