package com.example.testutil.net;


import android.widget.ImageView;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.imageloader.ImageLoaderUtil;
import com.xuexiang.util.view.DisplayUtils;

public class ImageLoaderActivity extends BaseHeadActivity {

	private ImageView ivNormal;
	private ImageView ivGif;
	private ImageView ivCircle;
	private ImageView ivCircle1;
	@Override
	protected int getLayoutId() {
		return R.layout.activity_imageloader;
	}

	@Override
	protected void init() {
		ivNormal = $(R.id.iv_normal);
		ivGif = $(R.id.iv_gif);
		ivCircle = $(R.id.iv_circle);
		ivCircle1 = $(R.id.iv_circle1);
		
		ImageLoaderUtil.getInstance().loadImage("http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be", R.drawable.bg_default_video_common_small, ivNormal);
		ImageLoaderUtil.getInstance().loadImage("http://image.sports.baofeng.com/19ce5d6ac3b4fff255196f200b1d3079", R.drawable.bg_default_video_common_small, ivGif);
		ImageLoaderUtil.getInstance().loadCircleBorderImage("http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be", R.drawable.avata_default, ivCircle, 2,
				this.getResources().getColor(R.color.de0b02), DisplayUtils.dip2px(this, 38), DisplayUtils.dip2px(this, 38));
		ImageLoaderUtil.getInstance().loadCircleImage("http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be", R.drawable.avata_default, ivCircle1);
	}

}
