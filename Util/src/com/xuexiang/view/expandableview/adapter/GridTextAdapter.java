package com.xuexiang.view.expandableview.adapter;

import java.util.ArrayList;
import java.util.List;

import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * 城市适配器
 * 
 * @author jayqiu
 * 
 */
public class GridTextAdapter extends BaseAdapter {

	private List<String> mChildTextArray = new ArrayList<String>();
	private Context mContext;

	public GridTextAdapter(Context context, List<String> child_text_array) {
		this.mContext = context;
		this.mChildTextArray = child_text_array;
	}

	@Override
	public int getCount() {
		return mChildTextArray.size();
	}

	@Override
	public Object getItem(int position) {
		return mChildTextArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler viewHodler = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					RUtils.getLayout(mContext, "item_gridview_txt"), null);
			viewHodler = new ViewHodler();
			viewHodler.mTvType = (TextView) convertView
					.findViewById(RUtils.getId(mContext, "tv_item_goods_type"));
			convertView.setTag(viewHodler);
		} else {
			viewHodler = (ViewHodler) convertView.getTag();
		}

		viewHodler.mTvType.setText(mChildTextArray.get(position));
		return convertView;
	}

	private class ViewHodler {
		private TextView mTvType;
	}

}
