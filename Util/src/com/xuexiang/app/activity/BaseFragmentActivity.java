package com.xuexiang.app.activity;



/**
 * activity的基类
 * 
 * @ClassName: BaseActivity
 * @Description: TODO
 * @author xx
 */
public abstract class BaseFragmentActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		initView();

		initListener();

		initData();
	}

	/**
	 * 初始化控件
	 */
	public abstract void initView();

	/**
	 * 初始化监听事件
	 */
	public abstract void initListener();

	/**
	 * 初始化数据
	 */
	public abstract void initData();

}
