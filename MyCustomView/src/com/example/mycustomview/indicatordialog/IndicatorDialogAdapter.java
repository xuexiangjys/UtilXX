package com.example.mycustomview.indicatordialog;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.TextView;

import com.example.mycustomview.R;

/**
 * @author xx
 * @Date 2017-5-22 上午9:43:52
 */
public abstract class IndicatorDialogAdapter extends BaseAdapter {
	private List<ActionItem> mLists = new ArrayList<>();
	private Context mContext;

	public IndicatorDialogAdapter(Context context, List<ActionItem> lists) {
		mContext = context;
		mLists = lists;
	}

	@Override
	public void onBindView(BaseViewHolder holder, int position) {
		TextView tv = holder.getView(R.id.item_add);
		tv.setText(mLists.get(position).getTitle());
		tv.setCompoundDrawablesWithIntrinsicBounds(mLists.get(position).getDrawableId(), 0, 0, 0);

		if (position == mLists.size() - 1) {
			holder.setVisibility(R.id.item_line, BaseViewHolder.GONE);
		} else {
			holder.setVisibility(R.id.item_line, BaseViewHolder.VISIBLE);

		}
	}

	@Override
	public boolean clickable() {
		return true;
	}

	@Override
	public int getItemCount() {
		return mLists.size();
	}


}
