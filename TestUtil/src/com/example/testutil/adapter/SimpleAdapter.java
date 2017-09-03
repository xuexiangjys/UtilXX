package com.example.testutil.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.adapter.SimpleAdapter.ViewHolder;
import com.example.testutil.adapter.bean.ItemBean;
import com.xuexiang.util.adapter.xadapter.BaseListAdapter;

/**
 * @author xx
 * @Date 2017-8-21 下午1:45:29
 */
public class SimpleAdapter extends BaseListAdapter<ItemBean, ViewHolder> {

	public SimpleAdapter(Context context, List<ItemBean> data) {
		super(context, data);
	}

	public class ViewHolder {
		private TextView tv_title, tv_describe, tv_phone, tv_time;
	}

	@Override
	protected void convert(ViewHolder holder, ItemBean item, int position) {
		holder.tv_title.setText(item.getTitle());
		holder.tv_describe.setText(item.getDesc());
		holder.tv_phone.setText(item.getPhone());
		holder.tv_time.setText(item.getTime());
	}

	@Override
	protected ViewHolder newViewHolder(View view) {
		ViewHolder holder = new ViewHolder();
		holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
		holder.tv_describe = (TextView) view.findViewById(R.id.tv_describe);
		holder.tv_phone = (TextView) view.findViewById(R.id.tv_phone);
		holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
		return holder;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.adapter_listview_simpleitem;
	}
}
