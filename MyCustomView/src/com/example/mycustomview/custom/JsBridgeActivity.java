package com.example.mycustomview.custom;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Button;

import com.example.mycustomview.R;
import com.google.gson.Gson;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.webview.jsbridge.BridgeHandler;
import com.xuexiang.webview.jsbridge.BridgeWebView;
import com.xuexiang.webview.jsbridge.CallBackFunction;
import com.xuexiang.webview.jsbridge.DefaultHandler;

public class JsBridgeActivity extends BaseHeadActivity implements OnClickListener {

	BridgeWebView webView;

	Button button;

	int RESULT_CODE = 0;

	ValueCallback<Uri> mUploadMessage;

	static class Location {
		String address;
	}

	static class User {
		String name;
		Location location;
		String testStr;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_jsbridge;
	}

	@Override
	protected void init() {
		webView = (BridgeWebView) findViewById(R.id.webView);

		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(this);

		webView.setDefaultHandler(new DefaultHandler());

		webView.setWebChromeClient(new WebChromeClient() {

			@SuppressWarnings("unused")
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
				this.openFileChooser(uploadMsg);
			}

			@SuppressWarnings("unused")
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
				this.openFileChooser(uploadMsg);
			}

			public void openFileChooser(ValueCallback<Uri> uploadMsg) {
				mUploadMessage = uploadMsg;
				pickFile();
			}
		});

		webView.loadUrl("file:///android_asset/demo.html");

		webView.registerHandler("submitFromWeb", new BridgeHandler() {

			@Override
			public void handler(String data, CallBackFunction function) {
				Toast("handler = submitFromWeb, data from web :" + data);
				function.onCallBack("submitFromWeb exe, response data 中文 from Java");
			}

		});

		User user = new User();
		Location location = new Location();
		location.address = "SDU";
		user.location = location;
		user.name = "大头鬼";

		webView.callHandler("functionInJs", new Gson().toJson(user), new CallBackFunction() {
			@Override
			public void onCallBack(String data) {

			}
		});

		webView.send("hello");
	}

	public void pickFile() {
		Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
		chooserIntent.setType("image/*");
		startActivityForResult(chooserIntent, RESULT_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == RESULT_CODE) {
			if (null == mUploadMessage) {
				return;
			}
			Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;
		}
	}

	@Override
	public void onClick(View v) {
		if (button.equals(v)) {
			webView.callHandler("functionInJs", "data from Java", new CallBackFunction() {
				@Override
				public void onCallBack(String data) {
					Toast("reponse data from js :" + data);
				}

			});
		}

	}

}
