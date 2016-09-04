package com.xuexiang.view.dialog;

import com.xuexiang.util.resource.RUtils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by zzz40500 on 15/6/15.
 */
public class ShapeLoadingDialog {



    private Context mContext;
    private Dialog mDialog;
    private LoadingView mLoadingView;
    private View mDialogContentView;


    public ShapeLoadingDialog(Context context) {
        mContext = context;
        init();
    }
    
    public ShapeLoadingDialog(Context context, CharSequence loadingText) {
        mContext = context;
        init();
        setLoadingText(loadingText);
    }

    private void init() {
        mDialog = new Dialog(mContext, RUtils.getStyle(mContext, "custom_dialog"));
        mDialogContentView= LayoutInflater.from(mContext).inflate(RUtils.getLayout(mContext, "dialog_shapeloading"),null);


        mLoadingView= (LoadingView) mDialogContentView.findViewById(RUtils.getId(mContext, "loadView"));
        mDialog.setContentView(mDialogContentView);
    }

    public void setBackground(int color){
        GradientDrawable gradientDrawable= (GradientDrawable) mDialogContentView.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence){
        mLoadingView.setLoadingText(charSequence);
    }

    public void show(){
        mDialog.show();

    }

    public void dismiss(){
        mDialog.dismiss();
    }

    public Dialog getDialog(){
        return  mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel){
        mDialog.setCanceledOnTouchOutside(cancel);
    }
}
