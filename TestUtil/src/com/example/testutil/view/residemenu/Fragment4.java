package com.example.testutil.view.residemenu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.util.observer.normal.EventManager;
import com.xuexiang.util.observer.normal.IObserver;
import com.xuexiang.util.observer.tag.Event;
import com.xuexiang.util.observer.tag.ITagObserver;
import com.xuexiang.util.observer.tag.TagEventManager;

import de.greenrobot.event.EventBus;

public class Fragment4 extends Fragment implements IObserver, ITagObserver {
	private TextView mTvEvent;

	public Fragment4() {
		EventManager.getSubject("msg2").register(this);

		List<String> eventTagList = new ArrayList<String>();
		eventTagList.add("Event2");
		TagEventManager.getTagSubject("msg1").register(this, eventTagList);
		TagEventManager.getTagSubject("msg2").register(this, eventTagList);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.f4, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		mTvEvent = (TextView) view.findViewById(R.id.tv_event);
	}

	@Override
	public void onChanged() {
		Log.e("xx", "Fragment4收到消息2");
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
		Log.e("xx", "Fragment4收到消息, Event Tag:" + event.getTag() + ",消息内容：" + event.getMessage());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventManager.getSubject("msg2").unregister(this);
		TagEventManager.getTagSubject("msg2").unregister(this);
		EventBus.getDefault().unregister(this);
	}

}
