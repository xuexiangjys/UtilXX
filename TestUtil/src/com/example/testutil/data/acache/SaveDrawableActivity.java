package com.example.testutil.data.acache;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.data.ACache;

/**
 * 
 * @ClassName: SaveDrawableActivity
 * @Description: 缓存drawable
 * @Author Yoson Hao
 * @WebSite www.haoyuexing.cn
 * @Email haoyuexing@gmail.com
 * @Date 2013-8-8 上午10:40:47
 * 
 */
public class SaveDrawableActivity extends BaseHeadActivity {

	private ImageView mIv_drawable_res;

	private ACache mCache;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_save_drawable;
	}

	@Override
	protected void init() {
		initView();

		mCache = ACache.get(this);

	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mIv_drawable_res = (ImageView) findViewById(R.id.iv_drawable_res);
	}

	/**
	 * 点击save事件
	 * 
	 * @param v
	 */
	public void save(View v) {
		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.img_test);
		mCache.put("testDrawable", drawable);
	}

	/**
	 * 点击read事件
	 * 
	 * @param v
	 */
	public void read(View v) {
		Drawable testDrawable = mCache.getAsDrawable("testDrawable");
		if (testDrawable == null) {
			Toast.makeText(this, "Drawable cache is null ...", Toast.LENGTH_SHORT).show();
			mIv_drawable_res.setImageDrawable(null);
			return;
		}
		mIv_drawable_res.setImageDrawable(testDrawable);
	}

	/**
	 * 点击clear事件
	 * 
	 * @param v
	 */
	public void clear(View v) {
		mCache.remove("testDrawable");
	}

}