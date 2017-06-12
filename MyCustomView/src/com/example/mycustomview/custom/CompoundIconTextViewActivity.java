package com.example.mycustomview.custom;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.CompoundIconTextView;

public class CompoundIconTextViewActivity extends BaseHeadActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_compoundicontextview;
	}

	@Override
	protected void init() {
		CompoundIconTextView tv1 = (CompoundIconTextView) findViewById(R.id.compoundIconTextView1);
        CompoundIconTextView tv2 = (CompoundIconTextView) findViewById(R.id.compoundIconTextView2);

        // set icon
        tv1.setVectorDrawableTop(R.drawable.ic_android_black_24dp);
        tv1.setIconColorResource(R.color.colorPrimary);
        tv1.setIconSizeResource(R.dimen.ct_icon_size, R.dimen.ct_icon_size);

        // clear icon
        tv2.setVectorDrawableRight(CompoundIconTextView.UNDEFINED_RESOURCE);
	}

}
