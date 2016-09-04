package com.xuexiang.view.expandableview.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.expandableview.entity.IconItem;

public class ClassifyMainAdapter extends BaseAdapter {

	private Context mContext;
	private List<IconItem> mList;
	private int position = 0;
	private boolean isloadingimg = true;
	Holder hold;

	public ClassifyMainAdapter(Context context, List<IconItem> list) {
		this.mContext = context;
		mList = list;
	}

	public ClassifyMainAdapter(Context context, List<IconItem> list,
			boolean islodingimg) {
		this.mContext = context;
		mList = list;
		this.isloadingimg = islodingimg;
	}

	public int getCount() {
		return mList.size();
	}

	public IconItem getItem(int position) {
		return mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int arg0, View view, ViewGroup viewGroup) {

		if (view == null) {
			view = View.inflate(mContext, RUtils.getLayout(mContext, "item_classify_mainlist"), null);
			hold = new Holder(view, mContext);
			view.setTag(hold);
		} else {
			hold = (Holder) view.getTag();
		}
		if (isloadingimg == true) {
			hold.img.setImageResource((getItem(arg0).mDrawableId));
		}
		hold.txt.setText(getItem(arg0).mTitle);
		hold.layout.setBackgroundColor(0xFFEBEBEB);
		if (arg0 == position) {
			hold.layout.setBackgroundColor(0xFFFFFFFF);
		}
		return view;
	}

	public void setSelectItem(int position) {
		this.position = position;
	}

	public int getSelectItem() {
		return position;
	}

	private static class Holder {
		LinearLayout layout;
		ImageView img;
		TextView txt;

		public Holder(View view, Context context) {
			txt = (TextView) view.findViewById(RUtils.getId(context, "mainitem_txt"));
			img = (ImageView) view.findViewById(RUtils.getId(context, "mainitem_img"));
			layout = (LinearLayout) view.findViewById(RUtils.getId(context, "mainitem_layout"));
		}
	}
}
