package com.example.mycustomview.supertextview;

import android.view.Gravity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mycustomview.R;
import com.squareup.picasso.Picasso;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.supertextview.SuperTextView;

/**
 * Created by allen on 2016/10/19.
 */
public class SuperTypeActivity extends BaseHeadActivity {

	private int type;
	private SuperTextView superTextView, superTextView2, superTextView3, alipayStv, superTextView4;

	@Override
	protected int getLayoutId() {
		type = getIntent().getIntExtra("type", 0);
		int layoutId = 0;
		switch (type) {
		case 0:
			layoutId = R.layout.layout_0;
			break;
		case 1:
			layoutId = R.layout.layout_1;
			break;
		case 2:
			layoutId = R.layout.layout_2;
			break;
		case 3:
			layoutId = R.layout.layout_3;
			break;
		case 4:
			layoutId = R.layout.layout_4;
			break;
		case 5:
			layoutId = R.layout.layout_5;
			break;
		case 6:
			layoutId = R.layout.layout_6;
			break;
		case 7:
			layoutId = R.layout.layout_7;
			break;
		case 8:
			layoutId = R.layout.layout_8;
			break;

		}
		return layoutId;
	}

	@Override
	protected void init() {
		switch (type) {
		case 5:
			setData();
			break;
		case 6:
			superTextView4 = (SuperTextView) findViewById(R.id.super_tv4);
			superTextView4.setCenterTextGravity(Gravity.LEFT);
			break;
		}
	}


	private void setData() {
		superTextView = (SuperTextView) findViewById(R.id.super_tv1);
		superTextView2 = (SuperTextView) findViewById(R.id.super_tv2);
		superTextView3 = (SuperTextView) findViewById(R.id.super_tv3);
		alipayStv = (SuperTextView) findViewById(R.id.alipay_stv);

		String url1 = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3860616424,1789830124&fm=80&w=179&h=119&img.PNG";
		String url2 = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=219781665,3032880226&fm=80&w=179&h=119&img.JPEG";
		String url3 = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3860616424,1789830124&fm=80&w=179&h=119&img.PNG";
		String url4 = "http://osnoex6vf.bkt.clouddn.com/original_label.png";

		Picasso.with(this).load(url1).placeholder(R.drawable.head_default).into(superTextView.getLeftIconIV());
		Glide.with(this).load(url2).placeholder(R.drawable.head_default).fitCenter().into(superTextView2.getRightIconIV());

		Glide.with(this).load(url3).placeholder(R.drawable.head_default).into(new SimpleTarget<GlideDrawable>() {
			@Override
			public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
				superTextView3.setRightTvDrawableRight(resource);
			}
		});

		Glide.with(this).load(url4).into(new SimpleTarget<GlideDrawable>() {
			@Override
			public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
				alipayStv.setLeftTvDrawableRight(resource);
			}
		});
	}

}
