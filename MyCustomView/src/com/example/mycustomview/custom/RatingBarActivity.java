package com.example.mycustomview.custom;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.ratingbar.BaseRatingBar;
import com.xuexiang.view.ratingbar.RotationRatingBar;
import com.xuexiang.view.ratingbar.ScaleRatingBar;

public class RatingBarActivity extends BaseHeadActivity {

	public static final String TAG = "SimpleRatingBar";

	@Override
	protected int getLayoutId() {
		return R.layout.activity_ratingbar;
	}

	@Override
	protected void init() {
		final ScaleRatingBar scaleRatingBar = (ScaleRatingBar) findViewById(R.id.simpleRatingBar);
		final BaseRatingBar baseRatingBar = (BaseRatingBar) findViewById(R.id.baseRatingBar);
		final RotationRatingBar rotationRatingBar = (RotationRatingBar) findViewById(R.id.rotationRatingBar);

		scaleRatingBar.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
			@Override
			public void onRatingChange(BaseRatingBar ratingBar, int rating) {
				Log.e(TAG, "onRatingChange: " + rating);
			}
		});

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int currentRating = baseRatingBar.getRating();
				baseRatingBar.setRating(currentRating + 1);

				currentRating = scaleRatingBar.getRating();
				scaleRatingBar.setRating(currentRating + 1);

				currentRating = rotationRatingBar.getRating();
				rotationRatingBar.setRating(currentRating + 1);
			}
		});

	}

}
