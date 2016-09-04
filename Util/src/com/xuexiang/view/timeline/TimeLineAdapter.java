package com.xuexiang.view.timeline;

import java.util.ArrayList;

import com.xuexiang.util.adapter.BaseContentAdapter;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.resource.ResourceUtils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeLineAdapter extends BaseContentAdapter<TimeItem> {

	public TimeLineAdapter(Activity context, ArrayList<TimeItem> data) {
		super(context, data);
	}

	private class ViewHolder {
		private LinearLayout uplevel_item_left_linear,
				uplevel_item_right_linear;

		private TextView uplevel_time_left_tv, uplevel_level_left_tv,
				uplevel_time_right_tv, uplevel_level_right_tv;
		
		private ImageView mImageView;
	}

	@Override
	public View getConvertView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		TimeItem item = dataList.get(position);		
		
		if (convertView == null) {
			convertView = mInflater.inflate(
					RUtils.getLayout(mContext, "adapter_timeline_item"), null);
			holder = new ViewHolder();
			holder.mImageView = (ImageView) convertView
					.findViewById(RUtils.getId(mContext, "tuicker_time"));
			holder.mImageView.setImageBitmap(ResourceUtils.getImageFromAssets(mContext, "img_yuan.png"));
			
			holder.uplevel_item_left_linear = (LinearLayout) convertView
					.findViewById(RUtils.getId(mContext, "uplevel_item_left_linear"));

			holder.uplevel_item_right_linear = (LinearLayout) convertView
					.findViewById(RUtils.getId(mContext, "uplevel_item_right_linear"));

			holder.uplevel_time_left_tv = (TextView) convertView
					.findViewById(RUtils.getId(mContext, "uplevel_time_left_tv"));

			holder.uplevel_level_left_tv = (TextView) convertView
					.findViewById(RUtils.getId(mContext, "uplevel_level_left_tv"));

			holder.uplevel_time_right_tv = (TextView) convertView
					.findViewById(RUtils.getId(mContext, "uplevel_time_right_tv"));
			holder.uplevel_level_right_tv = (TextView) convertView
					.findViewById(RUtils.getId(mContext, "uplevel_level_right_tv"));
			
		    convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		switch (position % 2) {
		case 0:
			holder.uplevel_item_left_linear.setVisibility(View.VISIBLE);
			holder.uplevel_item_right_linear.setVisibility(View.INVISIBLE);

			holder.uplevel_time_left_tv.setText(item.mTime);
			holder.uplevel_level_left_tv.setText(item.mContent);;
			break;

		case 1:
			holder.uplevel_item_left_linear.setVisibility(View.INVISIBLE);
			holder.uplevel_item_right_linear.setVisibility(View.VISIBLE);
			holder.uplevel_time_right_tv.setText(item.mTime);
			holder.uplevel_level_right_tv.setText(item.mContent);
			break;
		}
		return convertView;
	}

}
