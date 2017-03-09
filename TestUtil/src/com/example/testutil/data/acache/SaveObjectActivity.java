package com.example.testutil.data.acache;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testutil.R;
import com.example.testutil.data.entity.User;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.data.ACache;

/**
 * 
 * @ClassName: SaveObjectActivity
 * @Description: 缓存jsonobject
 * @Author Yoson Hao
 * @WebSite www.haoyuexing.cn
 * @Email haoyuexing@gmail.com
 * @Date 2013-8-8 下午2:13:16
 * 
 */
public class SaveObjectActivity extends BaseHeadActivity {

	private TextView mTv_object_original, mTv_object_res;
	private User user;

	private ACache mCache;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_save_object;
	}

	@Override
	protected void init() {
		// 初始化控件
		initView();

		mCache = ACache.get(this);
		user = new User();
		user.setId(1111111L);
		user.setLoginname("薛翔");
		user.setPassword("是天才");
		mTv_object_original.setText(user.toString());
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mTv_object_original = (TextView) findViewById(R.id.tv_object_original);
		mTv_object_res = (TextView) findViewById(R.id.tv_object_res);
	}

	/**
	 * 点击save事件
	 * 
	 * @param v
	 */
	public void save(View v) {
		mCache.put("testObject", user);
	}

	/**
	 * 点击read事件
	 * 
	 * @param v
	 */
	public void read(View v) {
		User testObject = (User) mCache.getAsObject("testObject");
		if (testObject == null) {
			Toast.makeText(this, "Object cache is null ...", Toast.LENGTH_SHORT).show();
			mTv_object_res.setText(null);
			return;
		}
		mTv_object_res.setText(testObject.toString());
	}

	/**
	 * 点击clear事件
	 * 
	 * @param v
	 */
	public void clear(View v) {
		mCache.remove("testObject");
	}

}
