package com.example.testutil.view;

import java.util.ArrayList;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.loopviewpager.LoopViewPagerLayout;
import com.xuexiang.view.loopviewpager.LoopViewPagerLayout.BannerInfo;
import com.xuexiang.view.loopviewpager.LoopViewPagerLayout.OnBannerItemClickListener;

/**  
 * 创建时间：2016-6-26 下午2:02:52  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：ViewpaperAdvActivity.java  
 **/
public class ViewpaperAdvActivity extends BaseActivity implements OnBannerItemClickListener{
	private LoopViewPagerLayout mLoopViewPager1;
    private LoopViewPagerLayout mLoopViewPager2;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_viewpaperadv);
	    initTitleBar(TAG);
	    
		initView();
	    initData();
	}

	 private void initView() {
        mLoopViewPager1 = (LoopViewPagerLayout) findViewById(R.id.view_main_loopViewPager1);
        mLoopViewPager2 = (LoopViewPagerLayout) findViewById(R.id.view_main_loopViewPager2);
    }

    private void initData() {
        ArrayList<BannerInfo> bannerInfos = new ArrayList<BannerInfo>(4);
        bannerInfos.add(new LoopViewPagerLayout.BannerInfo(R.drawable.a, "a"));
        bannerInfos.add(new LoopViewPagerLayout.BannerInfo(R.drawable.b, "b"));
        bannerInfos.add(new LoopViewPagerLayout.BannerInfo(R.drawable.d, "c"));
        bannerInfos.add(new LoopViewPagerLayout.BannerInfo(R.drawable.c, "d"));
        mLoopViewPager1.setLoopData(bannerInfos, this);
        mLoopViewPager2.setLoopData(bannerInfos, this);
    }


    @Override
    protected void onStart() {
        mLoopViewPager1.startLoop();
        mLoopViewPager2.startLoop();
        super.onStart();


    }

    @Override
    protected void onStop() {
        mLoopViewPager1.stopLoop();
        mLoopViewPager2.stopLoop();
        super.onStop();
    }


    @Override
    public void onBannerClick(int index, ArrayList<BannerInfo> banner) {
        BannerInfo bannerInfo = banner.get(index);
        Toast(bannerInfo.title);
    }

}
