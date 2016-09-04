package com.xuexiang.view.expandableview.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.expandableview.entity.Type;

public class GridViewAdapter extends BaseAdapter {

	private List<Type> mList;
	private Type mType;
	private Context mContext;
	Holder view;

	public GridViewAdapter(Context context, List<Type> list) {
		mList = list;
		mContext = context;
	}

	@Override
	public int getCount() {
		if (mList != null && mList.size() > 0)
			return mList.size();
		else
			return 0;
	}

	@Override
	public Type getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(mContext, RUtils.getLayout(mContext, "item_gridview"), null);
			view = new Holder(convertView, mContext);
			convertView.setTag(view);
		} else {
			view = (Holder) convertView.getTag();
		}
		if (mList != null && mList.size() > 0) {
			mType = mList.get(position);
			view.icon.setBackgroundResource(mType.getIcon());
			view.name.setText(mType.getTypename());
		}

		return convertView;
	}

	private class Holder {
		private ImageView icon;
		private TextView name;

		public Holder(View view, Context context) {
			icon = (ImageView) view.findViewById(RUtils.getId(context, "typeicon"));
			name = (TextView) view.findViewById(RUtils.getId(context, "typename"));
		}
	}

}
