package com.example.mycustomview.custom;

import android.graphics.Color;
import android.view.Menu;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.circlemenu.CircleMenu;
import com.xuexiang.view.circlemenu.OnMenuSelectedListener;
import com.xuexiang.view.circlemenu.OnMenuStatusChangeListener;

public class CircleMenuActivity extends BaseActivity {

    private CircleMenu circleMenu;
    
    @Override
	public void onCreateActivity() {
    	setContentView(R.layout.activity_circlemenu);

    	initTitleBar(TAG);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.icon_menu, R.drawable.icon_cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.icon_home)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.icon_search)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.icon_notify)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.icon_setting)
                .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.icon_gps)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                    	mToastUtil.showToast("点击菜单" + index);
                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                    @Override
                    public void onMenuOpened() {}

                    @Override
                    public void onMenuClosed() {}

                });
	}

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        circleMenu.openMenu();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onBackPressed() {
        circleMenu.closeMenu();
    }
}
