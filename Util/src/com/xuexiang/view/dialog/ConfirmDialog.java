package com.xuexiang.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
/**
 * 鑷畾涔夐��鍑哄脊鍑烘
 * @author xx
 */
public class ConfirmDialog {
	public static int CONFIRM = 1;
	public static int CANCEL = 0;
	private Context context;
	private AlertDialog dialog;
	private String title;
	onConfirmDialogClickListener onClickListener;

	public void setOnClickListener(onConfirmDialogClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public ConfirmDialog(Context context,String title) {
		this.context = context;
		this.title = title;
	}

	public void showDialog(){
		dialog=new AlertDialog.Builder(context).create();
		//鐐瑰嚮澶栭儴鍖哄煙涓嶈兘鍙栨秷dialog 
		dialog.setCanceledOnTouchOutside(false);
		dialog.setOnKeyListener(keylistener);
		dialog.show();

		Window window = dialog.getWindow();
		window.setContentView(RUtils.getLayout(context, "my_confirmdialog"));
		TextView tv_title = (TextView) window.findViewById(RUtils.getId(context, "dialog_title"));
		tv_title.setText(title);
		TextView tv_confirm = (TextView) window.findViewById(RUtils.getId(context, "tv_confirm"));
		TextView tv_cancel = (TextView) window.findViewById(RUtils.getId(context, "tv_cancel"));
		tv_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				onClickListener.onClick(CONFIRM);
			}
		});

		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				onClickListener.onClick(CANCEL);
			}
		});

		window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		window.setGravity(Gravity.CENTER);
	}

	public static OnKeyListener keylistener = new DialogInterface.OnKeyListener(){
		public boolean onKey(DialogInterface dialog,  int keyCode, KeyEvent event) {
			if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0) {
				return true;
			}
			else {
				return false;
			}
		}
	} ;
	
	public interface onConfirmDialogClickListener{
		public void onClick(int mode);
	}
}
