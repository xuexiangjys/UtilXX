package com.example.testutil.view.ninegridview;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.ninegridview.LGNineGrideView;

public class NinegridviewActivity extends BaseActivity {
	private LGNineGrideView LGNineGrideView;
	private List<String> urls = new ArrayList<String>();
	private int countIndex = -1;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_ninegridview);
		initTitleBar(TAG);
		for (int i = 0; i < 9; ++i) {
			urls.add(PictureData.urlsArray[i]);
		}

		LGNineGrideView = (LGNineGrideView) findViewById(R.id.grideView);
		assert LGNineGrideView != null;
		LGNineGrideView.setUrls(urls);

		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						urls.clear();
						++countIndex;
						if (countIndex >= 10) {
							countIndex = 1;
						}
						for (int i = 0; i < countIndex; ++i) {
							urls.add(PictureData.urlsArray[i]);
						}
						LGNineGrideView.setUrls(urls);
					}
				});

		findViewById(R.id.button2).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(NinegridviewActivity.this,
								NinegridviewListViewActivity.class));
					}
				});
	}
}
