package com.xuexiang.view.materialdesign.view;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.view.DisplayUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ButtonRectangle extends Button {
	
	TextView textButton;
	
	int paddingTop,paddingBottom, paddingLeft, paddingRight;
	
	
	public ButtonRectangle(Context context, AttributeSet attrs) {
		super(context, attrs);
		setDefaultProperties();
	}
	@Override
	protected void setDefaultProperties(){

		super.minWidth = 80;
		super.minHeight = 36;
		super.background = RUtils.getDrawable(getContext(), "background_button_rectangle");
		super.setDefaultProperties();
	}
	
	
	// Set atributtes of XML to View
	protected void setAttributes(AttributeSet attrs){
		
		//Set background Color
		// Color by resource
		int bacgroundColor = attrs.getAttributeResourceValue(ANDROIDXML,"background",-1);
		if(bacgroundColor != -1){
			setBackgroundColor(getResources().getColor(bacgroundColor));
		}else{
			// Color by hexadecimal
			// Color by hexadecimal
			background = attrs.getAttributeIntValue(ANDROIDXML, "background", -1);
			if (background != -1)
				setBackgroundColor(background);
		}
		
		// Set Padding
		String value = attrs.getAttributeValue(ANDROIDXML,"padding");		
		
		// Set text button
		String text = null;
		int textResource = attrs.getAttributeResourceValue(ANDROIDXML,"text",-1);
		if(textResource != -1){
			text = getResources().getString(textResource);
		}else{
			text = attrs.getAttributeValue(ANDROIDXML,"text");
		}
		if(text != null){
			textButton = new TextView(getContext());
			textButton.setText(text);
			textButton.setTextColor(Color.WHITE);
			textButton.setTypeface(null, Typeface.BOLD);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			params.setMargins(DisplayUtils.dip2px(getContext(), 5), DisplayUtils.dip2px(getContext(), 5), DisplayUtils.dip2px(getContext(), 5), DisplayUtils.dip2px(getContext(), 5));
			textButton.setLayoutParams(params);			
			addView(textButton);
//					FrameLayout.LayoutParams params = (LayoutParams) textView.getLayoutParams();
//					params.width = getWidth();
//					params.gravity = Gravity.CENTER_HORIZONTAL;
////					params.setMargins(paddingLeft, paddingTop, paddingRight, paddingRight);
//					textView.setLayoutParams(params);textColor
			int textColor = attrs.getAttributeResourceValue(ANDROIDXML,"textColor",-1);
			if(textColor != -1){
				textButton.setTextColor(textColor);
			}else{
				// Color by hexadecimal
				// Color by hexadecimal
				textColor = attrs.getAttributeIntValue(ANDROIDXML, "textColor", -1);
				if (textColor != -1)
					textButton.setTextColor(textColor);
			}
			int[] array = {android.R.attr.textSize};
			TypedArray values = getContext().obtainStyledAttributes(attrs, array);
	        float textSize = values.getDimension(0, -1);
	        values.recycle();
	        if(textSize != -1)
	        	textButton.setTextSize(textSize);
			
		}
		
		rippleSpeed = attrs.getAttributeFloatValue(MATERIALDESIGNXML,
				"rippleSpeed", DisplayUtils.dip2px(getContext(), 6));
	}
	
	
	Integer height;
	Integer width;
	@Override
	protected void onDraw(Canvas canvas) {
//		if(!txtCenter)
//		centrarTexto();
		super.onDraw(canvas);
		if (x != -1) {
			Rect src = new Rect(0, 0, getWidth()-DisplayUtils.dip2px(getContext(), 6), getHeight() - DisplayUtils.dip2px(getContext(), 7));
			Rect dst = new Rect(DisplayUtils.dip2px(getContext(), 6), DisplayUtils.dip2px(getContext(), 6), getWidth() - DisplayUtils.dip2px(getContext(), 6), getHeight() - DisplayUtils.dip2px(getContext(), 7));
			canvas.drawBitmap(makeCircle(), src, dst, null);
			invalidate();
		}
	}
	
}
