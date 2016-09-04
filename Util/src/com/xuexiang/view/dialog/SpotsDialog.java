package com.xuexiang.view.dialog;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.dialog.spotsloadingdialog.AnimatedView;
import com.xuexiang.view.dialog.spotsloadingdialog.AnimatorPlayer;
import com.xuexiang.view.dialog.spotsloadingdialog.HesitateInterpolator;
import com.xuexiang.view.dialog.spotsloadingdialog.ProgressLayout;


/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * on 13.01.15 at 14:22
 */
public class SpotsDialog extends AlertDialog {

    private static final int DELAY = 150;
    private static final int DURATION = 1500;

    private int size;
    private AnimatedView[] spots;
    private AnimatorPlayer animator;
    private TextView mTitle;
    private String mLoadingText;

    public void setLoadingText(String loadingText) {
		mLoadingText = loadingText;
		if (mTitle != null) {
			 mTitle.setText(mLoadingText);
		}
	} 

	public SpotsDialog(Context context) {
        this(context, RUtils.getStyle(context, "SpotsDialogDefault"));
    }
    
    public SpotsDialog(Context context,String title) {
        this(context, RUtils.getStyle(context, "SpotsDialogDefault"));
        mLoadingText = title;
    }

    public SpotsDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "dialog_spots"));
        mTitle = (TextView) findViewById(RUtils.getId(getContext(), "title"));
        mTitle.setText(mLoadingText);
        setCanceledOnTouchOutside(false);
        initProgress();
    }

    @Override
    protected void onStart() {
        super.onStart();

        animator = new AnimatorPlayer(createAnimations());
        animator.play();
    }

    @Override
    protected void onStop() {
        super.onStop();

        animator.stop();
    }

    //~

    private void initProgress() {
        ProgressLayout progress = (ProgressLayout) findViewById(RUtils.getId(getContext(), "progress"));
        size = progress.getSpotsCount();

        spots = new AnimatedView[size];
        int size = getContext().getResources().getDimensionPixelSize(RUtils.getDimen(getContext(), "spot_size"));
        int progressWidth = getContext().getResources().getDimensionPixelSize(RUtils.getDimen(getContext(), "progress_width"));
        for (int i = 0; i < spots.length; i++) {
            AnimatedView v = new AnimatedView(getContext());
            v.setBackgroundResource(RUtils.getDrawable(getContext(), "spot"));
            v.setTarget(progressWidth);
            v.setXFactor(-1f);
            progress.addView(v, size, size);
            spots[i] = v;
        }
    }

    private Animator[] createAnimations() {
        Animator[] animators = new Animator[size];
        for (int i = 0; i < spots.length; i++) {
            Animator move = ObjectAnimator.ofFloat(spots[i], "xFactor", 0, 1);
            move.setDuration(DURATION);
            move.setInterpolator(new HesitateInterpolator());
            move.setStartDelay(DELAY * i);
            animators[i] = move;
        }
        return animators;
    }
    
    private boolean mIsNeedCanceled;
	
	public void setCanceledByBackEvent(boolean isNeedCanceled) {
    	mIsNeedCanceled = isNeedCanceled;
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
            	if (mIsNeedCanceled) {
            		if (isShowing()) {
            			dismiss();
            		}
            	}
                break;
        }
        return true;
    }
}
