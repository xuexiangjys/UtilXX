package com.example.mycustomview.custom;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;

import com.xuexiang.app.activity.ListSimpleActivity;
import com.xuexiang.view.dialog.CommonDialog;
import com.xuexiang.view.dialog.ListSelectDialog;
import com.xuexiang.view.dialog.SingleSelectDialog;

/**
 * 创建时间：2017-4-3 下午7:23:02 项目名称：MyCustomView
 * 
 * @author xuexiang 文件名称：CustomDialogActivity.java
 **/
public class CustomDialogActivity extends ListSimpleActivity {
	CommonDialog.Builder builder;
	String[] values;

	@Override
	protected List<String> initSimpleData() {
		List<String> list = new ArrayList<>();
		list.add("hint");
		list.add("dialog");
		list.add("selelct_list_dialog");
		list.add("single_list_dialog");
		return list;
	}

	@Override
	protected void onItemClick(int i) {
		switch (i) {
		case 0:
			builder = new CommonDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("已经是最新版本");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});
			builder.create().show();
			break;
		case 1:
			builder = new CommonDialog.Builder(this);
			builder.setTitle("拨打电话");
			builder.setMessage("15112403565");
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast("取消");

				}
			});
			builder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast("确定");
				}
			});
			builder.create().show();
			break;
		case 2:
			// 设置标签
			values = new String[] { "今天", "明天", "后天" };
			final ListSelectDialog listSelectDialog = new ListSelectDialog(this, "标记为", values);
			listSelectDialog.setOnSubmitClickListener(new ListSelectDialog.OnSubmitClickListener() {
				@Override
				public void onSubmitClick(int pos) {
					Toast(values[pos]);
					listSelectDialog.hidden();
				}
			});
			listSelectDialog.show();
			break;
		case 3:
			// 取消发布
			values = new String[] { "太丑了", "没有钱" };
			SingleSelectDialog singleSelectDialog = new SingleSelectDialog(this, "选择原因", values);
			singleSelectDialog.setSelect(0);
			singleSelectDialog.setOnSubmitClickListener(new SingleSelectDialog.OnSubmitClickListener() {
				@Override
				public void onSubmitClick(int pos) {
					Toast(values[pos]);
				}
			});
			singleSelectDialog.show();
			break;

		default:
			break;
		}
	}

}
