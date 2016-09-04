package com.xuexiang.view.materialdesign.view;

import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Card extends CustomView {
	
	TextView textButton;
	
	int paddingTop,paddingBottom, paddingLeft, paddingRight;
	int backgroundColor = Color.parseColor("#FFFFFF");
	
	public Card(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAttributes(attrs);
	}
	
	
	// Set atributtes of XML to View
	protected void setAttributes(AttributeSet attrs){
		
		setBackgroundResource(RUtils.getDrawable(getContext(), "background_button_rectangle"));
		//Set background Color
		// Color by resource
		int bacgroundColor = attrs.getAttributeResourceValue(ANDROIDXML,"background",-1);
		if(bacgroundColor != -1){
			setBackgroundColor(getResources().getColor(bacgroundColor));
		}else{
			// Color by hexadecimal
			String background = attrs.getAttributeValue(ANDROIDXML,"background");
			if(background != null)
				setBackgroundColor(Color.parseColor(background));
			else
				setBackgroundColor(this.backgroundColor);
		}
	}
	
	// Set color of background
	public void setBackgroundColor(int color){
		this.backgroundColor = color;
		if(isEnabled())
			beforeBackground = backgroundColor;
		LayerDrawable layer = (LayerDrawable) getBackground();
		GradientDrawable shape =  (GradientDrawable) layer.findDrawableByLayerId(RUtils.getId(getContext(), "shape_bacground"));
		shape.setColor(backgroundColor);
	}
	
}
