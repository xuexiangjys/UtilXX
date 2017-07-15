package com.example.testutil.common;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.xuexiang.app.activity.ListSimpleActivity;
import com.xuexiang.app.crash.nocrash.Cockroach;

/**
 * @author xx
 * @Date 2017-7-15 下午3:56:39
 */
public class NoCrashActivity extends ListSimpleActivity {

	@Override
	protected List<String> initSimpleData() {
		List<String> list = new ArrayList<String>();
		list.add("安装 Cockroach");
		list.add("卸载 Cockroach");
		list.add("new thread exception...");
		list.add("handler exception...");
		list.add("click exception...");
		return list;
	}

	@Override
	protected void onItemClick(int position) {
		switch (position) {
		case 0:
			install();
			break;
		case 1:
			Cockroach.uninstall();
			break;
		case 2:
			new Thread() {
				@Override
				public void run() {
					super.run();
					throw new RuntimeException("new thread exception...");
				}
			}.start();
			break;
		case 3:
			new Handler().post(new Runnable() {
				@Override
				public void run() {
					throw new RuntimeException("handler exception...");
				}
			});
			break;
		case 4:
			throw new RuntimeException("click exception...");
		default:
			break;
		}
	}

	private void install() {
		Cockroach.install(new Cockroach.ExceptionHandler() {
			@Override
			public void handlerException(final Thread thread, final Throwable throwable) {
				Log.d("Cockroach", "MainThread: " + Looper.getMainLooper().getThread() + "  curThread: " + Thread.currentThread());
				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						try {
							Log.e("AndroidRuntime", "--->CockroachException:" + thread + "<---", throwable);
							Toast("Exception Happend\n" + thread + "\n" + throwable.toString());
							// throw new RuntimeException("..."+(i++));
						} catch (Throwable e) {

						}
					}
				});
			}
		});
	}

}
