package com.example.testutil.view.residemenu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.util.observer.handler.BaseHandlerOperate;
import com.xuexiang.util.observer.handler.BaseHandlerUpDate;
import com.xuexiang.util.observer.normal.EventManager;
import com.xuexiang.util.observer.normal.IObserver;
import com.xuexiang.util.observer.tag.Event;
import com.xuexiang.util.observer.tag.ITagObserver;
import com.xuexiang.util.observer.tag.TagEventManager;

import de.greenrobot.event.EventBus;

public class Fragment2 extends Fragment implements IObserver, ITagObserver, BaseHandlerUpDate {

	private TextView mTvEvent;
	private BaseHandlerOperate handler;
	public final static int message2 = 1001;
	public Fragment2() {
		EventManager.getSubject("msg2").register(this);

		List<String> eventTagList = new ArrayList<String>();
		eventTagList.add("Event1");
		TagEventManager.getTagSubject("msg2").register(this, eventTagList);

		EventBus.getDefault().register(this);
		
		handler = BaseHandlerOperate.getBaseHandlerOperate();
		handler.addKeyHandler(getClass(), this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.f2, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		mTvEvent = (TextView) view.findViewById(R.id.tv_event);
	}

	@Override
	public void onChanged() {
		Log.e("xx", "Fragment2收到消息2");
//		mTvEvent.setText("收到消息2");
	}

	@Override
	public void onInvalidated() {

	}

	public void onEventMainThread(Event2 event) {

		String msg = "onEventMainThread收到了消息：" + event.getMsg();
//		mTvEvent.setText(msg);
		Log.e("xx", msg);
	}

	@Override
	public void onChanged(Event event) {
		Log.e("xx", "Fragment2收到消息, Event Tag:" + event.getTag() + ",消息内容：" + event.getMessage());

	}
	
	@Override
	public void handleMessage(Message message) {
		switch (message.what) {
		case Fragment1.message1:
			Log.e("xx", "Fragment2收到handle消息, message.what:" + message.what + ", message：" + message.obj);
			break;
		case message2:
			Log.e("xx", "Fragment2收到handle消息, message.what:" + message.what + ", message：" + message.obj);
			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventManager.getSubject("msg2").unregister(this);
		TagEventManager.getTagSubject("msg2").unregister(this);
		EventBus.getDefault().unregister(this);
		
		handler.removeKeyData(getClass());
	}

	

}
