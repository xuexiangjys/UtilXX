package com.example.mycustomview.easyindicator;

import android.support.v4.view.ViewPager;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.adapter.FragmentAdapter;
import com.xuexiang.view.EasyIndicator;

public class EasyIndicatorActivity extends BaseHeadActivity {
	private ViewPager viewpager;
	private EasyIndicator easy_indicator;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_viewpage_view;
	}

	@Override
	protected void init() {
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		easy_indicator = (EasyIndicator) findViewById(R.id.easy_indicator);
		easy_indicator.setTabTitles(new String[] { "Tab1", "Tab2", "Tab3", "Tab4" });

		// 自定义设置
		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
		adapter.addFragment(TestFragment.newInstance("这是第一个界面"));
		adapter.addFragment(TestFragment.newInstance("这是第二个界面"));
		adapter.addFragment(TestFragment.newInstance("这是第三个界面"));
		adapter.addFragment(TestFragment.newInstance("这是第四个界面"));
		easy_indicator.setViewPage(viewpager, adapter);

		easy_indicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
			@Override
			public void onTabClick(String title, int position) {
				Toast("标题:" + title + ", 索引：" + position);
			}
		});

	}
}
