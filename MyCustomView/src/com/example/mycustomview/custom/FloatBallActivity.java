package com.example.mycustomview.custom;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.floatball.AccessibilityUtil;
import com.xuexiang.view.floatball.FloatBallService;

public class FloatBallActivity extends BaseActivity {

    private Button mBtnStart;
    private Button mBtnQuit;

    @Override
	public void onCreateActivity() {
    	setContentView(R.layout.activity_floatball);
    	initTitleBar(TAG);
        initView();
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (!Settings.canDrawOverlays(this)) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivityForResult(intent, 1);
//                Toast.makeText(this, "请先允许FloatBall出现在顶部", Toast.LENGTH_SHORT).show();
//            }
//        }
	}

    private void initView() {
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnQuit = (Button) findViewById(R.id.btn_quit);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAccessibility();
                Intent intent = new Intent(FloatBallActivity.this, FloatBallService.class);
                Bundle data = new Bundle();
                data.putInt("type", FloatBallService.TYPE_ADD);
                intent.putExtras(data);
                startService(intent);
            }
        });
        mBtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloatBallActivity.this, FloatBallService.class);
                Bundle data = new Bundle();
                data.putInt("type", FloatBallService.TYPE_DEL);
                intent.putExtras(data);
                startService(intent);
            }
        });
    }

    private void checkAccessibility() {
        // 判断辅助功能是否开启
        if (!AccessibilityUtil.isAccessibilitySettingsOn(this)) {
            // 引导至辅助功能设置页面
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            Toast.makeText(this, "请先开启FloatBall辅助功能", Toast.LENGTH_SHORT).show();
        }
    }

	
}
