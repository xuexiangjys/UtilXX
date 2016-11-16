package com.xuexiang.view.expandableview.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.MyView.MyGridView;
import com.xuexiang.view.expandableview.entity.IconItem;

public class ExpandableGridAdapter extends BaseExpandableListAdapter implements OnItemClickListener {

	private String[][] mChildTextArray;
	private Context mContext;
	private MyGridView mGridview;

	private List<IconItem> mList;
	private List<String> mChildArray;

	public ExpandableGridAdapter(Context context,
			List<IconItem> list, String[][] childtextarray) {
		mContext = context;
		mList = list;
		mChildTextArray = childtextarray;
	}

	/**
	 * 获取一级标签总数
	 */
	@Override
	public int getGroupCount() {
		return mList.size();
	}

	/**
	 * 获取一级标签下二级标签的总数
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// 这里返回1是为了让ExpandableListView只显示一个ChildView，否则在展开
		// ExpandableListView时会显示和ChildCount数量相同的GridView
		return 1;
	}

	/**
	 * 获取一级标签内容
	 */
	@Override
	public CharSequence getGroup(int groupPosition) {
		return mList.get(groupPosition).mTitle;
	}

	/**
	 * 获取一级标签下二级标签的内容
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mChildTextArray[groupPosition][childPosition];
	}

	/**
	 * 获取一级标签的ID
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * 获取二级标签的ID
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * 指定位置相应的组视图
	 */
	@Override
	public boolean hasStableIds() {
		return true;
	}

	/**
	 * 对一级标签进行设置
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		convertView = (LinearLayout) LinearLayout.inflate(mContext,
				RUtils.getLayout(mContext, "item_gridview_group_layout"), null);

		TextView group_title = (TextView) convertView
				.findViewById(RUtils.getId(mContext, "group_title"));
		if (isExpanded) {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					RUtils.getDrawable(mContext, "group_down"), 0);
		} else {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					RUtils.getDrawable(mContext, "group_up"), 0);
		}
		group_title.setText(mList.get(groupPosition).mTitle);
		return convertView;
	}

	/**
	 * 对一级标签下的二级标签进行设置
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = (RelativeLayout) RelativeLayout.inflate(mContext,
				RUtils.getLayout(mContext, "item_grid_child_layout"), null);
		mGridview = (MyGridView) convertView.findViewById(RUtils.getId(mContext, "gridview"));

		int size = mChildTextArray[groupPosition].length;
		mChildArray = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			mChildArray.add(mChildTextArray[groupPosition][i]);
		}
		mGridview.setAdapter(new GridTextAdapter(mContext, mChildArray));
		mGridview.setOnItemClickListener(this);
		return convertView;
	}

	/**
	 * 当选择子节点的时候，调用该方法
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(mContext, "当前选中的是:" + mChildArray.get(position),
				Toast.LENGTH_SHORT).show();
	}
}
