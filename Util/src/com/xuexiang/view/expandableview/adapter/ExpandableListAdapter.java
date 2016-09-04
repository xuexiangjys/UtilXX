package com.xuexiang.view.expandableview.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.expandableview.entity.IconItem;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private String[][] mChildTextArray;
	private Context mContext;

	private List<IconItem> mList;

	public ExpandableListAdapter(Context context,
			List<IconItem> list, String[][] child_text_array) {
		this.mContext = context;
		mList = list;
		this.mChildTextArray = child_text_array;
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
		return mChildTextArray[groupPosition].length;
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
				RUtils.getLayout(mContext, "item_group_layout"), null);

		ImageView group_icon = (ImageView) convertView
				.findViewById(RUtils.getId(mContext, "img_icon"));
		TextView group_title = (TextView) convertView
				.findViewById(RUtils.getId(mContext, "group_title"));
		if (isExpanded) {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					RUtils.getDrawable(mContext, "group_down"), 0);
		} else {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					RUtils.getDrawable(mContext, "group_up"), 0);
		}
		group_icon.setImageResource(mList.get(groupPosition).mDrawableId);
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
				RUtils.getLayout(mContext, "item_child_layout"), null);
		TextView child_text = (TextView) convertView
				.findViewById(RUtils.getId(mContext, "child_text"));

		child_text.setText(mChildTextArray[groupPosition][childPosition]);

		return convertView;
	}

	/**
	 * 当选择子节点的时候，调用该方法
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
