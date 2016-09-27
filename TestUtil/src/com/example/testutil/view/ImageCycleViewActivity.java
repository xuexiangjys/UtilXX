package com.example.testutil.view;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.net.NetImageUtil;
import com.xuexiang.view.ImageCycleView;
import com.xuexiang.view.ImageCycleView.ImageCycleViewListener;
import com.xuexiang.view.RollViewPager;
import com.xuexiang.view.RollViewPager.OnPagerClickCallback;

public class ImageCycleViewActivity extends BaseActivity {
	private ImageCycleView mImageCycleView;
	ArrayList<String> imageUrlList = new ArrayList<String>();
	
	private LinearLayout mAdvViewPagerLayout;
	private TextView mNewsTitle;
	private LinearLayout mDotsll;
	@Override
	public void onCreateActivity() {   
		setContentView(R.layout.activity_imagecycleview);
		initTitleBar(TAG);
		
		initView();
	}

	private void initView() {
		initImageCycleView();
		
		initRollViewPaper();
	}	

	private void initImageCycleView() {
		mImageCycleView = (ImageCycleView) findViewById(R.id.imagecycleview);
		imageUrlList.add("http://pic.58pic.com/58pic/12/13/51/88u58PICzTK.jpg");
		imageUrlList.add("http://pic2.ooopic.com/11/74/18/78bOOOPIC8d_1024.jpg");
		imageUrlList.add("http://pic23.nipic.com/20120918/2398086_092851558355_2.jpg");
		imageUrlList.add("http://pic.58pic.com/58pic/12/70/85/58258PICymH.jpg");
		mImageCycleView.setImageResources(imageUrlList, new ImageCycleViewListener(){
			@Override
			public void displayImage(String imageURL, ImageView imageView) {
				NetImageUtil.getImage(imageURL, imageView);
			}

			@Override
			public void onImageClick(int position, View view) {
				Toast("点击了第" + (position + 1) + "项!");
			}
		});
	}
	
    private void initRollViewPaper() {
    	mAdvViewPagerLayout = (LinearLayout) findViewById(R.id.top_news_viewpager);
    	mNewsTitle = (TextView) findViewById(R.id.top_news_title);
    	mDotsll = (LinearLayout) findViewById(R.id.dots_ll);
    	ArrayList<View> dots = new ArrayList<View>();
    	ArrayList<String> titles = new ArrayList<String>();
    	for (int i = 0; i < imageUrlList.size(); i++) {  
            titles.add("标题" + (i + 1));
            ImageView image = new ImageView(mContext);  
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);  //dot的宽高
            params.setMargins(3, 0, 3, 0);  
            image.setImageResource(R.drawable.home_img_ratio);
            mDotsll.addView(image, params);  
            dots.add(image);
        }  
    	RollViewPager rollViewPager = new RollViewPager(this, dots, R.drawable.home_img_ratio_selected, R.drawable.home_img_ratio, new OnPagerClickCallback(){
			@Override
			public void onPagerClick(int position) {
				Toast("点击了第" + (position + 1) + "项!");
			}
		});
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    	rollViewPager.setUriList(imageUrlList);
    	rollViewPager.setTitle(mNewsTitle, titles);
    	rollViewPager.startRoll();
    	mAdvViewPagerLayout.addView(rollViewPager, params);
    	
	}

}
