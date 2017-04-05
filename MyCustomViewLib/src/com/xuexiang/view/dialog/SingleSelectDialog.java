package com.xuexiang.view.dialog;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.R;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.dialog.adapter.CommonAdapter;
import com.xuexiang.view.dialog.adapter.MyViewHolder;

/**
 * Created by jason on 2016/8/19. des:${des}
 */
public class SingleSelectDialog {

	private Activity mContext;

	private Dialog dialog;

	private String mTitle;
	private String[] mContent;
	private boolean mIsCancelable;

	private OnSubmitClickListener mSubmitClickListener;

	private int pos = -1;

	public SingleSelectDialog(Activity context, String title, String[] values) {
		mContext = context;
		mTitle = title;
		mContent = values;
	}

	public void setSelect(int position) {
		pos = position;
	}

	/**
	 * 显示对话框
	 */
	public void show() {
		if (mContext != null && mContent.length > 0) {
			View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_single_select, null);// 得到加载view

			dialog = createDialogByView(mContext, view, mIsCancelable);
			dialog.show();

			FrameLayout framedialog = (FrameLayout) view.findViewById(R.id.framedialog);
			framedialog.setLayoutParams(new FrameLayout.LayoutParams(DisplayUtils.getScreenW(mContext) * 4 / 5, FrameLayout.LayoutParams.MATCH_PARENT));

			TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
			tv_header.setText(mTitle);

			ListView lv_data = (ListView) view.findViewById(R.id.lv_data);
			lv_data.setAdapter(new MyAdapter(mContext, Arrays.asList(mContent), R.layout.adapter_listview_single_select_dialog));

			TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);
			tv_submit.setText("确定");
			tv_submit.setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (pos == -1) {
						Toast.makeText(mContext, "请选择一项！", Toast.LENGTH_SHORT).show();
						return;
					}
					hidden();
					if (mSubmitClickListener != null) {
						mSubmitClickListener.onSubmitClick(pos);
					}
				}
			});

			TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
			tv_cancel.setText("取消");
			tv_cancel.setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {
					hidden();
				}
			});
		}
	}

	/**
	 * 隐藏对话框
	 */
	private void hidden() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	private Dialog createDialogByView(Context context, View view, boolean isCancleable) {
		Dialog loadingDialog = new Dialog(context, R.style.BaseDialog);// 创建自定义样式dialog

		loadingDialog.setCancelable(isCancleable);// 返回键是否可用
		loadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;
	}

	public void setCancelable(boolean isCancelable) {
		mIsCancelable = isCancelable;
	}

	public void setOnSubmitClickListener(OnSubmitClickListener submitClickListener) {
		mSubmitClickListener = submitClickListener;
	}

	public interface OnSubmitClickListener {
		void onSubmitClick(int pos);
	}

	private class MyAdapter extends CommonAdapter<String> {
		private boolean[] flags;

		public MyAdapter(Context context, List<String> data, int layoutId) {
			super(context, data, layoutId);
			flags = new boolean[data.size()];
			flags[pos] = true;
		}

		@Override
		public void convert(MyViewHolder holder, String bean) {
			TextView tv_text = holder.getView(R.id.tv_text);
			ImageView img_status = holder.getView(R.id.img_status);

			final int position = holder.getPosition();
			tv_text.setText(bean);
			if (flags[position]) {
				img_status.setImageResource(R.drawable.single_select);
			} else {
				img_status.setImageResource(R.drawable.single_unselect);
			}

			holder.getConvertView().setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					for (int i = 0; i < flags.length; ++i) {
						flags[i] = i == position;
					}
					pos = position;
					notifyDataSetChanged();
				}
			});
		}

	}
}
