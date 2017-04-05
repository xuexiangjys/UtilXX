package com.example.testutil.view.residemenu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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

public class Fragment1 extends Fragment implements OnClickListener, IObserver, ITagObserver, BaseHandlerUpDate {

	private TextView mTvEvent;
	private Button btn_msg1, btn_msg2, btn_eventbus1, btn_eventbus2, btn_Tagmsg1, btn_Tagmsg2;
	
	private BaseHandlerOperate handler;
	public final static int message1 = 1000;
	public Fragment1() {
		EventManager.getSubject("msg1").register(this);

		List<String> eventTagList = new ArrayList<String>();
		eventTagList.add("Event1");
		TagEventManager.getTagSubject("msg1").register(this, eventTagList);

		EventBus.getDefault().register(this);
		
		handler =  BaseHandlerOperate.getBaseHandlerOperate();
		handler.addKeyHandler(getClass(), this);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.f1, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		mTvEvent = (TextView) view.findViewById(R.id.tv_event);
		btn_msg1 = (Button) view.findViewById(R.id.btn_msg1);
		btn_msg2 = (Button) view.findViewById(R.id.btn_msg2);
		btn_msg1.setOnClickListener(this);
		btn_msg2.setOnClickListener(this);

		btn_Tagmsg1 = (Button) view.findViewById(R.id.btn_Tagmsg1);
		btn_Tagmsg2 = (Button) view.findViewById(R.id.btn_Tagmsg2);
		btn_Tagmsg1.setOnClickListener(this);
		btn_Tagmsg2.setOnClickListener(this);

		btn_eventbus1 = (Button) view.findViewById(R.id.btn_eventbus1);
		btn_eventbus2 = (Button) view.findViewById(R.id.btn_eventbus2);
		btn_eventbus1.setOnClickListener(this);
		btn_eventbus2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_msg1:
			EventManager.getSubject("msg1").notifyObservers();
			handler.putMessageKey(getClass(), message1, "这是传给Fragment1的message1消息");
			handler.putMessageKey(Fragment2.class, message1, "这是传给Fragment2的message1消息");
			break;
		case R.id.btn_msg2:
			EventManager.getSubject("msg2").notifyObservers();
			handler.putMessageKey(Fragment2.class, Fragment2.message2, "这是传给Fragment2的message2消息");
			break;
		case R.id.btn_Tagmsg1:
			TagEventManager.getTagSubject("msg1").notify(new Event("Event2", "这个是要推送给Fragment3和Fragment4的消息"));
			break;
		case R.id.btn_Tagmsg2:
			TagEventManager.getTagSubject("msg2").notify(new Event("Event2", "这个是要推送给Fragment4的消息"));
			break;
		case R.id.btn_eventbus1:
			EventBus.getDefault().post(new Event1("Event1 btn clicked"));
			break;
		case R.id.btn_eventbus2:
			EventBus.getDefault().post(new Event2("Event2 btn clicked"));
			break;

		default:
			break;
		}
	}

	@Override
	public void onChanged() {
		Log.e("xx", "Fragment1收到消息1");
		mTvEvent.setText("收到消息1");
	}

	@Override
	public void onInvalidated() {

	}

	public void onEventMainThread(Event1 event) {

		String msg = "onEventMainThread收到了消息：" + event.getMsg();
//		mTvEvent.setText(msg);
		Log.e("xx", msg);
	}

	@Override
	public void onChanged(Event event) {
		Log.e("xx", "Fragment1收到消息, Event Tag:" + event.getTag() + ",消息内容：" + event.getMessage());
	}
	
	@Override
	public void handleMessage(Message message) {
		switch (message.what) {
		case message1:
			Log.e("xx", "Fragment1收到handle消息, message.what:" + message.what + ", message：" + message.obj);
			break;

		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventManager.getSubject("msg1").unregister(this);
		TagEventManager.getTagSubject("msg1").unregister(this);
		EventBus.getDefault().unregister(this);
		handler.removeKeyData(getClass());
	}

	

}
