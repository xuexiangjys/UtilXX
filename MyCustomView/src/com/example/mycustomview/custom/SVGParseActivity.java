package com.example.mycustomview.custom;

import android.graphics.Path;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.svgparse.PathView;

public class SVGParseActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_svgparse);

		initTitleBar(TAG);
		final PathView pathView = (PathView) findViewById(R.id.pathView);
		// final Path path = makeConvexArrow(50, 100);
		// pathView.setPath(path);
		pathView.setFillAfter(true);
		pathView.useNaturalColors();
		pathView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pathView.getPathAnimator().delay(100).duration(1500)
						.interpolator(new AccelerateDecelerateInterpolator())
						.start();
			}
		});
	}

	private Path makeConvexArrow(float length, float height) {
		final Path path = new Path();
		path.moveTo(0.0f, 0.0f);
		path.lineTo(length / 4f, 0.0f);
		path.lineTo(length, height / 2.0f);
		path.lineTo(length / 4f, height);
		path.lineTo(0.0f, height);
		path.lineTo(length * 3f / 4f, height / 2f);
		path.lineTo(0.0f, 0.0f);
		path.close();
		return path;
	}
}
