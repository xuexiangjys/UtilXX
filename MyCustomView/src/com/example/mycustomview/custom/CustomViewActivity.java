package com.example.mycustomview.custom;

import android.os.Handler;
import android.view.View;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.custom.dialog.LoadingAlertDialog;
import com.xuexiang.view.custom.toast.T;

public class CustomViewActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
	public void onCreateActivity() {
    	setContentView(R.layout.activity_customview);
    	initTitleBar(TAG);
    	
        findViewById(R.id.btn_ios_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LoadingAlertDialog dialog = new LoadingAlertDialog(CustomViewActivity.this);
                dialog.setTitleText("请稍候...");
                dialog.show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                }, 2000);
            }
        });

        findViewById(R.id.btn_ios_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showAnimToast(CustomViewActivity.this, "恭喜您，注册成功了！");
            }
        });

        findViewById(R.id.btn_ios_toast_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showAnimSuccessToast(CustomViewActivity.this, "保存成功");
            }
        });

        findViewById(R.id.btn_ios_toast_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showAnimErrorToast(CustomViewActivity.this, "修改失败");
            }
        });
	}
    

	
}
