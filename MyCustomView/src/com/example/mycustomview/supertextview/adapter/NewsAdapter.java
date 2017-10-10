package com.example.mycustomview.supertextview.adapter;

import java.util.List;

import android.content.Context;

import com.example.mycustomview.R;
import com.example.mycustomview.supertextview.bean.NewsBean;
import com.squareup.picasso.Picasso;
import com.xuexiang.view.supertextview.SuperTextView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by allen on 2016/10/31.
 */

public class NewsAdapter extends CommonAdapter<NewsBean> {
	private Context mContext;

	public NewsAdapter(Context context, List<NewsBean> datas) {
		super(context, R.layout.layout_super_item, datas);
		mContext = context;
	}

	@Override
	protected void convert(ViewHolder holder, NewsBean newsBean, int position) {

		((SuperTextView) holder.getView(R.id.super_tv)).setLeftTopString(newsBean.getTitle()).setLeftBottomString(newsBean.getTime());
		Picasso.with(mContext).load(newsBean.getImgUrl()).placeholder(R.drawable.head_default).into(((SuperTextView) holder.getView(R.id.super_tv)).getLeftIconIV());
	}
}
