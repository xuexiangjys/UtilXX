package com.xuexiang.view.signature;

import java.io.IOException;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xuexiang.R;

/**
 * 电子签名弹框
 * @author xx
 * @Date 2017-3-22 下午6:18:05
 */
public class SignatureDialog extends Dialog {
	/**
	 * 签名控件
	 */
	private SignatureView mSignatureView;
	/**
	 * 签名文件保存的路径
	 */
	private String mSavePath;

	/**
	 * 点击确认按钮的监听
	 */
	private OnSubmitListener mListener;

	public SignatureDialog(Context context) {
		super(context, R.style.custom_dialog);
	}
	
	public SignatureDialog(Context context, String savePath) {
		super(context, R.style.custom_dialog);
		mSavePath = savePath;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_signature);
		initView();

	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mSignatureView = (SignatureView) findViewById(R.id.signatureview);
		findViewById(R.id.btn_clear).setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSignatureView.clear();
			}
		});
		
		findViewById(R.id.btn_submit).setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onSubmit();
			}
		});
	}

	/**
	 * 点击确定按钮
	 */
	private void onSubmit() {
		if (mSignatureView.getTouched()) {
			Bitmap bitmap = null;
			try {
				bitmap = mSignatureView.save(mSavePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (mListener != null) {
				mListener.onSubmit(bitmap, mSavePath);
			}
			mSignatureView.clear();
			dismiss();
		} else {
			Toast.makeText(getContext(), "您没有签名~", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 资源回收
	 */
	public void recycle() {
		if (mSignatureView != null) {
			mSignatureView.recycle();
			mSignatureView = null;
		}
		mListener = null;
	}

	public void setOnSubmitListener(OnSubmitListener listener) {
		mListener = listener;
	}
	
	public String getSavePath() {
		return mSavePath;
	}

	public void setSavePath(String savePath) {
		mSavePath = savePath;
	}
	
	/**
	 * 点击确定按钮的监听
	 * 
	 * @author xx
	 */
	public interface OnSubmitListener {
		/**
		 * 点击确定按钮保存签名图片
		 * @param bitmap 签名图片
		 * @param path 签名图片保存路径
		 */
		void onSubmit(Bitmap bitmap, String path);
	}

}
