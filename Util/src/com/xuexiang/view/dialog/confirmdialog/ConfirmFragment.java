package com.xuexiang.view.dialog.confirmdialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ConfirmFragment extends DialogFragment{
	private OnDialogClickListener mOnListener;
	
	public ConfirmFragment newInstance(String title, String content) {
		ConfirmFragment dialog = new ConfirmFragment();
		Bundle args = new Bundle();
		args.putString("Title", title);
		args.putString("Content", content);
		dialog.setArguments(args);
		return dialog;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String title = getArguments().getString("Title");
		String content = getArguments().getString("Content");
		Dialog mDialog = new ConfirmDialog(getActivity(), title, content, mOnListener);
		return mDialog;
	}
	
	// onAttach鏄叧鑱攁ctivity鐨�,鐢ㄦ帴鍙ｅ洖璋�
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnListener = (OnDialogClickListener) activity;
		} catch (ClassCastException e) {
			dismiss();
		}
	}
}
