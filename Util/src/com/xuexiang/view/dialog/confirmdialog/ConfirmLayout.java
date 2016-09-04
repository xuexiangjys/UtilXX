package com.xuexiang.view.dialog.confirmdialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;

public class ConfirmLayout extends FrameLayout{
	protected final static long durationMillis = 200;
	protected WindowManager windowManager;
	protected View background;
	protected boolean canCancel = true;
	protected boolean isShowing;
	protected OnDialogClickListener mDialogClickListener;
	
	private TextView titleTv,contentTv;
	private Button okBtn,cancelBtn;

	public ConfirmLayout(Context context) {
		super(context);
		initView("", "");
	}
	
	public ConfirmLayout(Context context, String title, String content, OnDialogClickListener clickListener) {
		super(context);
		mDialogClickListener = clickListener;
		initView(title, content);
	}

	protected void initView(String title, String content) {
		initBackground();
		initContentView(title, content);
		windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (KeyEvent.KEYCODE_BACK == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
					hide();
					return true;
				}
				return false;
			}
		});
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	
	protected void initContentView(String title, String content) {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(RUtils.getLayout(getContext(), "confirm_dialog"), null);
		
		titleTv = (TextView) view.findViewById(RUtils.getId(getContext(), "title_name"));
		contentTv = (TextView) view.findViewById(RUtils.getId(getContext(), "text_view"));	
		init(title, content);
		
		okBtn = (Button) view.findViewById(RUtils.getId(getContext(), "btn_ok"));
		cancelBtn = (Button) view.findViewById(RUtils.getId(getContext(), "btn_cancel"));
		okBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hide();
				if(mDialogClickListener != null){
					mDialogClickListener.onSubmit();
				}
			}
		});
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hide();
				if(mDialogClickListener != null){
					mDialogClickListener.onCancel();
				}
			}
		});
		DisplayMetrics d = getResources().getDisplayMetrics();
		FrameLayout.LayoutParams flp = new LayoutParams((int) (d.widthPixels * 0.5), LayoutParams.WRAP_CONTENT,Gravity.CENTER);
		flp.setMargins(20, 0, 20, 0);
		addView(view, flp) ;
	}

	protected void initBackground() {
		background = new View(getContext());
        background.setBackgroundColor(0x88000000);
        background.setVisibility(View.GONE);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(background, lp);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canCancel) {
                    hide();
                }
            }
        });
    }
	
	public void init(String title, String content) {
		initTitle(title);
		initContent(content);
	}
	
	public void initTitle(String title) {
		if (title != null && !TextUtils.isEmpty(title)) {
			titleTv.setText(title);
		}
	}
	
	public void initContent(String content) {
		if (content != null && !TextUtils.isEmpty(content)) {
			contentTv.setText(content);
		}
	}
	
	 public void setCanceledOnTouchOutside(boolean cancel) {
		 if (cancel && !canCancel) {
			 canCancel = true;
		 }
	 }

	public void show() {
		((Activity) getContext()).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (getParent() == null) {
					WindowManager.LayoutParams wlp = new WindowManager.LayoutParams();
					wlp.type = WindowManager.LayoutParams.TYPE_APPLICATION;
					wlp.format = PixelFormat.TRANSPARENT;
					wlp.gravity = Gravity.LEFT | Gravity.TOP;
					wlp.width = LayoutParams.MATCH_PARENT;
					wlp.height = LayoutParams.MATCH_PARENT;
					windowManager.addView(ConfirmLayout.this, wlp);
				}
				showBackGround();
			}
		});
	}

	public void hide() {
		((Activity) getContext()).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				hideBackGround();
				if (getParent() != null)
					windowManager.removeView(ConfirmLayout.this);
			}
		});
	}
	
	protected void showBackGround() {
        if (isShowing)
            return;
        isShowing = true;
        background.clearAnimation();
        background.setVisibility(View.VISIBLE);
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(durationMillis);
        background.startAnimation(an);
    }

	protected void hideBackGround() {
        if (!isShowing)
            return;
        isShowing = false;
        background.clearAnimation();
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(durationMillis);
        an.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            	background.setVisibility(View.GONE);
            }
        });
        background.startAnimation(an);
    }
	
	public void setOnDialogClickListener(OnDialogClickListener clickListener){
		mDialogClickListener = clickListener;
	}

}
