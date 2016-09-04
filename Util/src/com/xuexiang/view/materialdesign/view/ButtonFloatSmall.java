package com.xuexiang.view.materialdesign.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.view.DisplayUtils;

public class ButtonFloatSmall extends ButtonFloat {
	
	public ButtonFloatSmall(Context context, AttributeSet attrs) {
		super(context, attrs);
		sizeRadius = 20;
		sizeIcon = 20;
		setDefaultProperties();
		LayoutParams params = new LayoutParams(DisplayUtils.dip2px(getContext(), sizeIcon), DisplayUtils.dip2px(getContext(), sizeIcon));
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		icon.setLayoutParams(params);
	}
	
	protected void setDefaultProperties(){
		rippleSpeed = DisplayUtils.dip2px(getContext(), 2);
		rippleSize = 10;		
		// Min size
		setMinimumHeight(DisplayUtils.dip2px(getContext(), sizeRadius * 2));
		setMinimumWidth(DisplayUtils.dip2px(getContext(), sizeRadius * 2));
		// Background shape
		setBackgroundResource(RUtils.getDrawable(getContext(), "background_button_float"));
	}

}
