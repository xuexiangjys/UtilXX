package com.example.testutil.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.testutil.R;
import com.example.testutil.adapter.bean.ChatMessage;
import com.xuexiang.util.adapter.baseadapterhelper.BaseAdapterHelper;
import com.xuexiang.util.adapter.baseadapterhelper.MultiItemTypeSupport;
import com.xuexiang.util.adapter.baseadapterhelper.QuickAdapter;

public class MultiAdapterActivity extends Activity
{

	private ListView mListView;
	private ArrayList<ChatMessage> mDatas = new ArrayList<ChatMessage>();

	private QuickAdapter<ChatMessage> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapterlist);

		mListView = (ListView) findViewById(R.id.id_lv_main);
		
		MultiItemTypeSupport<ChatMessage> multiItemTypeSupport = new MultiItemTypeSupport<ChatMessage>()
		{
			@Override
			public int getLayoutId(int position, ChatMessage msg)
			{
				if (msg.isComMeg())
				{
					return R.layout.adapter_listview_chat_from_msg;
				}
				return R.layout.adapter_listview_chat_send_msg;
			}

			@Override
			public int getViewTypeCount()
			{
				return 2;
			}

			@Override
			public int getItemViewType(int postion, ChatMessage msg)
			{
				if (msg.isComMeg())
				{
					return ChatMessage.RECIEVE_MSG;
				}
				return ChatMessage.SEND_MSG;
			}
		};

		initDatas();

		
		mAdapter = new QuickAdapter<ChatMessage>(MultiAdapterActivity.this, mDatas,
				multiItemTypeSupport)
		{
			@Override
			protected void convert(BaseAdapterHelper helper, ChatMessage item)
			{
				switch (helper.mLayoutId)
				{
				case R.layout.adapter_listview_chat_from_msg:
					helper.setText(R.id.chat_from_content, item.getContent());
					helper.setText(R.id.chat_from_name, item.getName());
					helper.setImageResource(R.id.chat_from_icon, item.getIcon());
					break;
				case R.layout.adapter_listview_chat_send_msg:
					helper.setText(R.id.chat_send_content, item.getContent());
					helper.setText(R.id.chat_send_name, item.getName());
					helper.setImageResource(R.id.chat_send_icon, item.getIcon());
					break;
				}

			}
		};
//		mAdapter.showIndeterminateProgress(true);
		// …Ë÷√  ≈‰∆˜
		mListView.setAdapter(mAdapter);

	}

	private void initDatas()
	{
		ChatMessage msg = null;
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);

		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
	}

}
