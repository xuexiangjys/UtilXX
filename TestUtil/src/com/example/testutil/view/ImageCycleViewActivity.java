package com.example.testutil.view;

import java.util.ArrayList;

import android.view.View;
import android.widget.ImageView;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.net.NetImageUtil;
import com.xuexiang.view.ImageCycleView;
import com.xuexiang.view.ImageCycleView.ImageCycleViewListener;

public class ImageCycleViewActivity extends BaseActivity {
	private ImageCycleView mImageCycleView;
	ArrayList<String> imageUrlList = new ArrayList<String>();
	@Override
	public void onCreateActivity() {   
		setContentView(R.layout.activity_imagecycleview);
		initTitleBar(TAG);
		
		initView();
	}

	private void initView() {
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

}
