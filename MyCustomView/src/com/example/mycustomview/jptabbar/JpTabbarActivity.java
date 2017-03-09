package com.example.mycustomview.jptabbar;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.jptabbar.BadgeDismissListener;
import com.xuexiang.view.jptabbar.JPTabBar;
import com.xuexiang.view.jptabbar.OnTabSelectListener;

public class JpTabbarActivity extends BaseActivity implements
		BadgeDismissListener, OnTabSelectListener {

	private List<Fragment> list = new ArrayList<Fragment>();
	// @Titles
	// private int[] titles =
	// {R.string.Tab1Pager,R.string.Tab2Pager,R.string.Tab3Pager,R.string.Tab4Pager};
	// @NorIcons
	// private int[] mNormalIcons =
	// {R.drawable.tab1_normal,R.drawable.tab2_normal,R.drawable.tab3_normal,R.drawable.tab4_normal};
	// @SeleIcons
	// private int[] mSelectedIcons =
	// {R.drawable.tab1_selected,R.drawable.tab2_selected,R.drawable.tab3_selected,R.drawable.tab4_selected};
	private NoScrollViewPager mPager;

	private JPTabBar mTabbar;

	private Tab1Pager mTab1;

	private Tab2Pager mTab2;

	private Tab3Pager mTab3;

	private Tab4Pager mTab4;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_jptabbar);
		initTitleBar(TAG);
		initView();
	}

	private void initView() {
		mPager = (NoScrollViewPager) findViewById(R.id.view_pager);
		mTabbar = (JPTabBar) findViewById(R.id.tabbar);
		mTabbar.setTitles(R.string.tab1, R.string.tab2, R.string.tab3,
				R.string.tab4)
				.setNormalIcons(R.drawable.tab1_normal, R.drawable.tab2_normal,
						R.drawable.tab3_normal, R.drawable.tab4_normal)
				.setSelectedIcons(R.drawable.tab1_selected,
						R.drawable.tab2_selected, R.drawable.tab3_selected,
						R.drawable.tab4_selected).generate();
		mPager.setNoScroll(false);
		mTab1 = new Tab1Pager();
		mTab2 = new Tab2Pager();
		list.add(mTab1);
		list.add(mTab2);
		list.add(new Tab3Pager());
		list.add(new Tab4Pager());
		// list.add(new Tab4Pager());
		// list.add(new Tab4Pager());
		mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
		// 显示圆点模式的徽章
		// 设置容器
		mTabbar.showBadge(0, 50);
		mTabbar.setContainer(mPager);
		// 设置Badge消失的代理
		mTabbar.setDismissListener(this);
		mTabbar.setTabListener(this);
	}

	@Override
	public void onDismiss(int position) {
		if (position == 0) {
			mTab1.clearCount();
		}
	}

	@Override
	public void onTabSelect(int index) {
		mToastUtil.showToast("点击了Tab" + (index + 1));
	}

	@Override
	public void onClickMiddle(View middleBtn) {
		mToastUtil.showToast("点击了中间的按钮！");

	}

	public JPTabBar getTabbar() {
		return mTabbar;
	}
}
