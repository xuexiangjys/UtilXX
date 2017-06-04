package com.example.testutil.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.util.adapter.BaseArrayAdapter;
import com.xuexiang.view.materialicons.IconDrawable;
import com.xuexiang.view.materialicons.Iconify;
import com.xuexiang.view.materialicons.Iconify.IconValue;

public class IconsAdapter extends BaseArrayAdapter<IconValue> {

	public IconsAdapter(Context context) {
		super(context, IconValue.values());
	}
	
	@Override
	public View getConvertView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_material_icon_list_item, null);
			viewHolder.IconView = (ImageView) convertView.findViewById(R.id.icon_view);
			viewHolder.IconName = (TextView) convertView.findViewById(R.id.icon_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Iconify.IconValue iconValue = Iconify.IconValue.values()[position];
		viewHolder.IconView.setImageDrawable(new IconDrawable(mContext, iconValue).sizeRes(R.dimen.icon_detailed_size).colorRes(R.color.grey));
		viewHolder.IconName.setText(iconValue.name());
		viewHolder.IconName.setTextColor(mContext.getResources().getColor(R.color.grey));
		return convertView;
	}

	private class ViewHolder {
		/**
		 * 图标
		 */
		public ImageView IconView;
		/**
		 * 图标名称
		 */
		public TextView IconName;
	}

}
