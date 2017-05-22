package com.example.mycustomview.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.squareup.picasso.Picasso;
import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.view.settingview.CircleTransform;
import com.xuexiang.view.settingview.LSettingItem;

public class SettingItemActivity extends AppCompatActivity {
	private LSettingItem mSettingItemOne;
	private LSettingItem mSettingItemFour;
	private ImageView mIvHead;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		ActivityUtil.initTitleBar(this, "SettingItemActivity");
		mSettingItemOne = (LSettingItem) findViewById(R.id.item_one);
		mSettingItemFour = (LSettingItem) findViewById(R.id.item_four);
		mIvHead = (ImageView) findViewById(R.id.headimage);
		mSettingItemOne.setOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
			@Override
			public void click() {
				Toast.makeText(getApplicationContext(), "我的消息", Toast.LENGTH_SHORT).show();
			}
		});
		mSettingItemFour.setOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
			@Override
			public void click() {
				Toast.makeText(getApplicationContext(), "选中开关", Toast.LENGTH_SHORT).show();
			}
		});
		Picasso.with(this).load(R.drawable.girl).transform(new CircleTransform()).into(mIvHead);
		
	}

}
