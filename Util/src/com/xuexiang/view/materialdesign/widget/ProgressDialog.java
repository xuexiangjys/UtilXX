package com.xuexiang.view.materialdesign.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.materialdesign.view.ProgressBarCircularIndeterminate;

public class ProgressDialog extends android.app.Dialog{
	
	Context context;
	View view;
	View backView;
	String title;
	TextView titleTextView;
	
	int progressColor = -1;
	
	public ProgressDialog(Context context,String title) {
		super(context, android.R.style.Theme_Translucent);
		this.title = title;
		this.context = context;
	}
	
	public ProgressDialog(Context context,String title, int progressColor) {
		super(context, android.R.style.Theme_Translucent);
		this.title = title;
		this.progressColor = progressColor;
		this.context = context;
	}
	
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    super.onCreate(savedInstanceState);
	    setContentView(RUtils.getLayout(getContext(), "dialog_progress"));
	    
		view = (RelativeLayout)findViewById(RUtils.getId(getContext(), "contentDialog"));
		backView = (RelativeLayout)findViewById(RUtils.getId(getContext(), "dialog_rootView"));
		backView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getX() < view.getLeft() 
						|| event.getX() >view.getRight()
						|| event.getY() > view.getBottom() 
						|| event.getY() < view.getTop()) {
					dismiss();
				}
				return false;
			}
		});
		
	    this.titleTextView = (TextView) findViewById(RUtils.getId(getContext(), "title"));
	    setTitle(title);
	    if(progressColor != -1){
	    	ProgressBarCircularIndeterminate progressBarCircularIndeterminate = (ProgressBarCircularIndeterminate) findViewById(RUtils.getId(getContext(), "progressBarCircularIndetermininate"));
	    	progressBarCircularIndeterminate.setBackgroundColor(progressColor);
	    }
	    
	    
	}
	
	@Override
	public void show() {
		super.show();
		// set dialog enter animations
		view.startAnimation(AnimationUtils.loadAnimation(context, RUtils.getAnim(getContext(), "dialog_main_show_amination")));
		backView.startAnimation(AnimationUtils.loadAnimation(context, RUtils.getAnim(getContext(), "dialog_root_show_amin")));
	}
	
	// GETERS & SETTERS

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		if(title == null)
			titleTextView.setVisibility(View.GONE);
		else{
			titleTextView.setVisibility(View.VISIBLE);
			titleTextView.setText(title);
		}
	}

	public TextView getTitleTextView() {
		return titleTextView;
	}

	public void setTitleTextView(TextView titleTextView) {
		this.titleTextView = titleTextView;
	}

	@Override
	public void dismiss() {
		Animation anim = AnimationUtils.loadAnimation(context, RUtils.getAnim(getContext(), "dialog_main_hide_amination"));
		anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				view.post(new Runnable() {
					@Override
					public void run() {
			        	ProgressDialog.super.dismiss();
			        }
			    });
				
			}
		});
		Animation backAnim = AnimationUtils.loadAnimation(context, RUtils.getAnim(getContext(), "dialog_root_hide_amin"));
		
		view.startAnimation(anim);
		backView.startAnimation(backAnim);
	}
	
	

}
