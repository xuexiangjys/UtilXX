package com.example.mycustomview.custom;

import android.graphics.Typeface;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.smilerating.BaseRating;
import com.xuexiang.view.smilerating.SmileRating;

public class SmileRatingActivity extends BaseHeadActivity implements SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {

	private SmileRating mSmileRating;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_smilerating;
	}

	@Override
	protected void init() {
		mSmileRating = (SmileRating) findViewById(R.id.ratingView);
		mSmileRating.setOnSmileySelectionListener(this);
		mSmileRating.setOnRatingSelectedListener(this);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "MetalMacabre.ttf");
		mSmileRating.setTypeface(typeface);
	}

	@Override
	public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
		switch (smiley) {
		case SmileRating.BAD:
			Toast("Bad");
			break;
		case SmileRating.GOOD:
			Toast("Good");
			break;
		case SmileRating.GREAT:
			Toast("Great");
			break;
		case SmileRating.OKAY:
			Toast("Okay");
			break;
		case SmileRating.TERRIBLE:
			Toast("Terrible");
			break;
		case SmileRating.NONE:
			Toast("None");
			break;
		}
	}

	@Override
	public void onRatingSelected(int level, boolean reselected) {
		Toast("Rated as: " + level + " - " + reselected);
	}

}
