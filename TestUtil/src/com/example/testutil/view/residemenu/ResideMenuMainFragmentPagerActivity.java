package com.example.testutil.view.residemenu;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.view.menu.ResideMenu;
import com.xuexiang.view.menu.ResideMenuInfo;
import com.xuexiang.view.menu.ResideMenuItem;



public class ResideMenuMainFragmentPagerActivity extends FragmentActivity implements View.OnClickListener {

	private ResideMenu resideMenu;
	private ResideMenuItem itemHuiyuan;
	private ResideMenuItem itemQianbao;
	private ResideMenuItem itemZhuangban;
	private ResideMenuItem itemShoucang;
	private ResideMenuItem itemXiangce;
	private ResideMenuItem itemFile;
	
	private ResideMenuInfo info;
	
	private boolean is_closed = true;
	private long mExitTime;
		
	ViewPager viewpager;	
	Fragment1 f1=null;
	Fragment2 f2=null;
	Fragment3 f3=null;
	Fragment4 f4=null;	
	Button[] btnArray;
	TextView[] TextViewArray;
	int[] BackgroundResource_unselected;
	int[] BackgroundResource_selected;
	MainFragmentPagerAdapter adapter;
	private int currentPageIndex = 0;
	private ArrayList<Fragment> datas;
	
	ImageButton leftMenu;
	


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.mainfragmentpager);
		
		

		
		viewpager = (ViewPager)this.findViewById(R.id.viewPager);
		
		adapter = new MainFragmentPagerAdapter(this.getSupportFragmentManager(), null);
		viewpager.setAdapter(adapter);
				
		
		setupView();
		setUpMenu();
		setListener();
		setButtonColor();
		viewpager.setCurrentItem(currentPageIndex);

	}

	
	
	private void setButtonColor() {
		for (int i = 0; i < btnArray.length; i++) {
			if (i == currentPageIndex) {
				btnArray[i].setBackgroundResource(BackgroundResource_selected[i]);
				TextViewArray[i].setTextColor(Color.WHITE);
				
			} else {
				btnArray[i].setBackgroundResource(BackgroundResource_unselected[i]);
				TextViewArray[i].setTextColor(Color.parseColor("#82858b"));
			}
			
		}
	}

	

	
	
	

	private void setListener() {
		
		resideMenu.addMenuInfo(info);

		itemHuiyuan.setOnClickListener(this);
		itemQianbao.setOnClickListener(this);
		itemZhuangban.setOnClickListener(this);
		itemShoucang.setOnClickListener(this);
		itemXiangce.setOnClickListener(this);
		itemFile.setOnClickListener(this);


		info.setOnClickListener(this);

		leftMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		
		
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int index) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int pageIndex) {
				currentPageIndex = pageIndex;
				setButtonColor();
			}
		});

		for (Button btn : btnArray) {
			btn.setOnClickListener(this);
		}
	}
	
	
	

	private void setupView() {
		
		
		datas = new ArrayList<Fragment>();
		datas.add(f1 = new Fragment1());
		datas.add(f2 = new Fragment2());
		datas.add(f3 = new Fragment3());
		datas.add(f4 = new Fragment4());
		
		
		adapter.setDatas(datas);
		adapter.notifyDataSetChanged();

		
		btnArray=new Button[]{
				(Button)findViewById(R.id.btnFriendList),
				(Button)findViewById(R.id.btnGroupChat),
				(Button)findViewById(R.id.btnTopicList),
				(Button)findViewById(R.id.btnSetList),
		};
		
		BackgroundResource_unselected=new int[]{R.drawable.message_unselected,R.drawable.contacts_unselected,R.drawable.news_unselected,R.drawable.setting_unselected};
		BackgroundResource_selected=new int[]{R.drawable.message_selected,R.drawable.contacts_selected,R.drawable.news_selected,R.drawable.setting_selected};
			
		TextViewArray=new TextView[]{
				(TextView)findViewById(R.id.message_text),
				(TextView)findViewById(R.id.contacts_text),
				(TextView)findViewById(R.id.news_text),
				(TextView)findViewById(R.id.setting_text),
		};
	}


	@SuppressWarnings("deprecation")
	private void setUpMenu() {

		leftMenu=(ImageButton)findViewById(R.id.leftMenu);
		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);
		// 禁止使用右侧菜单
		resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// create menu items;
		itemHuiyuan = new ResideMenuItem(this, R.drawable.lzf, "开通会员");
		itemQianbao = new ResideMenuItem(this, R.drawable.lze, "QQ钱包");
		itemZhuangban = new ResideMenuItem(this, R.drawable.lzc, "个性装扮");
		itemShoucang = new ResideMenuItem(this, R.drawable.lzg, "我的收藏");
		itemXiangce = new ResideMenuItem(this, R.drawable.lzd, "我的相册");
		itemFile = new ResideMenuItem(this, R.drawable.ktn, "我的文件");

		resideMenu.addMenuItem(itemHuiyuan, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemQianbao, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemZhuangban, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemShoucang, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemXiangce, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemFile, ResideMenu.DIRECTION_LEFT);
		
		info = new ResideMenuInfo(this, R.drawable.icon_profile, "开心鬼",
				"32 级");

	}
	
	
	@Override
	public void onClick(View v) {
		try {
			switch (v.getId()) {
				case R.id.btnFriendList:
					currentPageIndex = 0;
					break;
				case R.id.btnGroupChat:
					currentPageIndex = 1;
					break;
				case R.id.btnTopicList:
					currentPageIndex = 2;
					break;
				case R.id.btnSetList:
					currentPageIndex = 3;
					break;
			}
			viewpager.setCurrentItem(currentPageIndex);
			
			if (v == itemHuiyuan) {
				Intent intent = new Intent();
				intent.putExtra("flog", "开通会员界面");
				intent.setClass(getApplicationContext(), ClickActivity.class);
				startActivity(intent);
			} else if (v == itemQianbao) {
				Intent intent = new Intent();
				intent.putExtra("flog", "QQ钱包界面");
				intent.setClass(getApplicationContext(), ClickActivity.class);
				startActivity(intent);
			} else if (v == itemZhuangban) {
				Intent intent = new Intent();
				intent.putExtra("flog", "个性装扮界面");
				intent.setClass(getApplicationContext(), ClickActivity.class);
				startActivity(intent);
			} else if (v == itemShoucang) {
				Intent intent = new Intent();
				intent.putExtra("flog", "我的收藏界面");
				intent.setClass(getApplicationContext(), ClickActivity.class);
				startActivity(intent);
			} else if (v == itemXiangce) {
				Intent intent = new Intent();
				intent.putExtra("flog", "我的相册界面");
				intent.setClass(getApplicationContext(), ClickActivity.class);
				startActivity(intent);
			} else if (v == itemFile) {
				Intent intent = new Intent();
				intent.putExtra("flog", "我的文件界面");
				intent.setClass(getApplicationContext(), ClickActivity.class);
				startActivity(intent);
			} else if (v == info) {
				Intent intent = new Intent();
				intent.putExtra("flog", "我的个人信息界面");
				intent.setClass(getApplicationContext(), ClickActivity.class);
				startActivity(intent);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			is_closed = false;
			leftMenu.setVisibility(View.GONE);
		}

		@Override
		public void closeMenu() {
			is_closed = true;
			leftMenu.setVisibility(View.VISIBLE);
		}
	};
	
	// What good method is to access resideMenu？
		public ResideMenu getResideMenu() {
			return resideMenu;
		}

		// 监听手机上的BACK键
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				// 判断菜单是否关闭
				if (is_closed) {
					// 判断两次点击的时间间隔（默认设置为2秒）
					if ((System.currentTimeMillis() - mExitTime) > 3000) {
						Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

						mExitTime = System.currentTimeMillis();
					} else {
						mExitTime=0;
						finish();
						//System.exit(0);
					}
				} else {
					resideMenu.closeMenu();
				}
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	


	
}




