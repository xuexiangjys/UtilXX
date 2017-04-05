package com.xuexiang.view.searchbox.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.R;
import com.xuexiang.view.searchbox.listener.IOnItemClickListener;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.MyViewHolder> {

	private Context mContext;

	private List<String> mHistorys = new ArrayList<String>();

	public SearchHistoryAdapter(Context context, ArrayList<String> historys) {
		mContext = context;
		mHistorys = historys;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_listview_item_search_history, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		holder.mHistoryInfo.setText(mHistorys.get(position));

		holder.mHistoryInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mOnItemClickListener.onItemClick(mHistorys.get(position));
			}
		});

		holder.mDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mOnItemClickListener.onItemDeleteClick(mHistorys.get(position));
			}
		});

	}

	@Override
	public int getItemCount() {
		return mHistorys.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		TextView mHistoryInfo;
		ImageView mDelete;

		public MyViewHolder(View view) {
			super(view);
			mHistoryInfo = (TextView) view.findViewById(R.id.tv_item_search_history);
			mDelete = (ImageView) view.findViewById(R.id.iv_item_search_delete);
		}
	}

	private IOnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(IOnItemClickListener listener) {
		mOnItemClickListener = listener;
	}

}
