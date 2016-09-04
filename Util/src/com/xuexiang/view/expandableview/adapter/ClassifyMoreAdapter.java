package com.xuexiang.view.expandableview.adapter;

import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassifyMoreAdapter extends BaseAdapter {

	private Context mContext;
	private String[] mTextList;
	private int position = 0;
	Holder hold;

	public ClassifyMoreAdapter(Context context, String[] text_list) {
		this.mContext = context;
		this.mTextList = text_list;
	}

	public int getCount() {
		return mTextList.length;
	}

	public String getItem(int position) {
		return mTextList[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int arg0, View view, ViewGroup viewGroup) {

		if (view == null) {
			view = View.inflate(mContext, RUtils.getLayout(mContext, "item_classify_morelist"), null);
			hold = new Holder(view, mContext);
			view.setTag(hold);
		} else {
			hold = (Holder) view.getTag();
		}
		hold.txt.setText(mTextList[arg0]);
		hold.txt.setTextColor(0xFF666666);
		if (arg0 == position) {
			hold.txt.setTextColor(0xFFFF8C00);
		}
		return view;
	}

	public void setSelectItem(int position) {
		this.position = position;
	}

	private static class Holder {
		TextView txt;

		public Holder(View view, Context context) {
			txt = (TextView) view.findViewById(RUtils.getId(context, "moreitem_txt"));
		}
	}
}
