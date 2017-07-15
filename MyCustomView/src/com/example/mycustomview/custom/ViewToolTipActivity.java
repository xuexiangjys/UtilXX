package com.example.mycustomview.custom;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.ViewTooltip;

public class ViewToolTipActivity extends BaseHeadActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_viewtooltip;
	}

	@Override
	protected void init() {
		final EditText editText = (EditText) findViewById(R.id.editText);

		$(R.id.left).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewTooltip.on(editText).position(ViewTooltip.Position.LEFT).text("Some tooltip with long text").clickToHide(true).autoHide(false, 0)
						.animation(new ViewTooltip.FadeTooltipAnimation(500)).onDisplay(new ViewTooltip.ListenerDisplay() {
							@Override
							public void onDisplay(View view) {
								Toast("onDisplay");
							}
						}).onHide(new ViewTooltip.ListenerHide() {
							@Override
							public void onHide(View view) {
								Toast("onHide");
							}
						}).show();
			}
		});

		$(R.id.right).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewTooltip.on(editText).autoHide(true, 1000).position(ViewTooltip.Position.RIGHT).text("Some tooltip with long text").show();
			}
		});

		$(R.id.top).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewTooltip.on(editText).position(ViewTooltip.Position.TOP).text("Some tooltip with long text").show();
			}
		});

		$(R.id.bottom).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewTooltip.on(editText).color(Color.BLACK).position(ViewTooltip.Position.BOTTOM).text("Some tooltip with long text").show();
			}
		});
	}
}
