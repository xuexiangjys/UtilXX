package com.example.mycustomview.supertextview;

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.supertextview.SuperTextView;

public class SuperClickActivity extends BaseHeadActivity {

	private SuperTextView superTextView, superTextView_cb, superTextView_switch;

	private String string;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_super_click;
	}

	@Override
	protected void init() {
		superTextView = (SuperTextView) findViewById(R.id.super_tv);
		superTextView_cb = (SuperTextView) findViewById(R.id.super_cb_tv);
		superTextView_switch = (SuperTextView) findViewById(R.id.super_switch_tv);

		/**
		 * 根据实际需求对需要的View设置点击事件
		 */
		superTextView.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
			@Override
			public void onClickListener(SuperTextView superTextView) {
				string = "整个item的点击事件";
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setLeftTopTvClickListener(new SuperTextView.OnLeftTopTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getLeftTopString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setLeftTvClickListener(new SuperTextView.OnLeftTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getLeftString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setLeftBottomTvClickListener(new SuperTextView.OnLeftBottomTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getLeftBottomString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setCenterTopTvClickListener(new SuperTextView.OnCenterTopTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getCenterTopString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setCenterTvClickListener(new SuperTextView.OnCenterTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getCenterString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setCenterBottomTvClickListener(new SuperTextView.OnCenterBottomTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getCenterBottomString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setRightTopTvClickListener(new SuperTextView.OnRightTopTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getRightTopString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getRightString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setRightBottomTvClickListener(new SuperTextView.OnRightBottomTvClickListener() {
			@Override
			public void onClickListener() {
				string = superTextView.getRightBottomString();
				Toast.makeText(SuperClickActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		}).setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
			@Override
			public void onClickListener(ImageView imageView) {
			}
		}).setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
			@Override
			public void onClickListener(ImageView imageView) {
			}
		});

		superTextView_cb.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
			@Override
			public void onClickListener(SuperTextView superTextView) {
				superTextView.setCbChecked(!superTextView.getCbisChecked());
			}
		}).setCheckBoxCheckedChangeListener(new SuperTextView.OnCheckBoxCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(SuperClickActivity.this, "" + isChecked, Toast.LENGTH_SHORT).show();
			}
		});

		superTextView_switch.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
			@Override
			public void onClickListener(SuperTextView superTextView) {
				superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
			}
		}).setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(SuperClickActivity.this, "" + isChecked, Toast.LENGTH_SHORT).show();
			}
		});

	}
}
