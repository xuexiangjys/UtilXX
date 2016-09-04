package com.example.testutil.view.swipelistview.adapter;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.view.swipelistview.entity.RecentItem;
import com.xuexiang.view.swipelistview.SwipeListView;

public class RecentAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private LinkedList<RecentItem> mData;
	private SwipeListView mListView;
	private Context mContext;

	public RecentAdapter(Context context, LinkedList<RecentItem> data,
			SwipeListView listview) {
		mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		mData = data;
		this.mListView = listview;
	}

	public void remove(int position) {
		if (position < mData.size()) {
			mData.remove(position);
			notifyDataSetChanged();
		}
	}

	public void remove(RecentItem item) {
		if (mData.contains(item)) {
			mData.remove(item);
			notifyDataSetChanged();
		}
	}

	public void addFirst(RecentItem item) {
		if (mData.contains(item)) {
			mData.remove(item);
		}
		mData.addFirst(item);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final RecentItem item = mData.get(position);
		ViewHolder vHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_listview_recentitem, null);
			vHolder = new ViewHolder();
			vHolder.nameTV = (TextView) convertView.findViewById(R.id.recent_list_item_name);
			vHolder.numTV = (TextView) convertView.findViewById(R.id.unreadmsg);
			vHolder.headIV = (ImageView) convertView.findViewById(R.id.icon);
			vHolder.deleteBtn = (Button) convertView.findViewById(R.id.recent_del_btn);
			
			convertView.setTag(vHolder);
		}else {
			vHolder  = (ViewHolder) convertView.getTag();
		}

		vHolder.nameTV.setText(item.getName());
		vHolder.headIV.setImageResource(item.getHeadImg());
		
		int num = item.getNewNum();
		if (num > 0) {
			vHolder.numTV.setVisibility(View.VISIBLE);
			vHolder.numTV.setText(num + "");
		} else {
			vHolder.numTV.setVisibility(View.GONE);
		}
		
		vHolder.deleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mData.remove(position);
				notifyDataSetChanged();
				if (mListView != null)
					mListView.closeOpenedItems();
			}
		});
		return convertView;
	}
	
	class ViewHolder{
		public TextView nameTV;
		public TextView numTV;
		public ImageView headIV;
		public Button deleteBtn;
	}
}
