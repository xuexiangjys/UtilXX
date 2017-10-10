package com.example.mycustomview.supertextview;

import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.supertextview.CommonTextView;

/**
 * Created by allen on 2016/11/22.
 */

public class SuperCommonTextViewActivity extends BaseHeadActivity {
	CommonTextView commonTextView;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_super_common_textview;
	}

	@Override
	protected void init() {
		commonTextView = (CommonTextView) findViewById(R.id.common_tv);
		commonTextView.setOnCommonTextViewClickListener(new CommonTextView.OnCommonTextViewClickListener() {
			@Override
			public void onCommonTextViewClick() {
				super.onCommonTextViewClick();
				Toast.makeText(SuperCommonTextViewActivity.this, "onCommonTextViewClick", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onLeftViewClick() {
				super.onLeftViewClick();
				Toast.makeText(SuperCommonTextViewActivity.this, "onLeftViewClick", Toast.LENGTH_LONG).show();
			}

		});
		
	}
}
