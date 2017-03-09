package com.example.testutil.view;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.coolanim.CoolAnimView;

public class CoolAnimActivity extends BaseActivity {

    private Button mBtnDlgShow;
    private CoolAnimView mCoolAnimView;

    @Override
	public void onCreateActivity() {
    	setContentView(R.layout.activity_coolanim);

    	initTitleBar(TAG);
        mBtnDlgShow = (Button) findViewById(R.id.btn_dialog);
        mCoolAnimView = (CoolAnimView) findViewById(R.id.cool_view);
        mCoolAnimView.setOnCoolAnimViewListener(new CoolAnimView.OnCoolAnimViewListener() {
            @Override
            public void onAnimEnd() {
                Toast.makeText(CoolAnimActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }
        });
        // 1s后结束动画
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 调用此方法,使动画进入结束阶段
                mCoolAnimView.stopAnim();
            }
        }).start();

        RelativeLayout layout = new RelativeLayout(CoolAnimActivity.this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(new CoolAnimView(CoolAnimActivity.this), params);
        final AlertDialog dialog = new AlertDialog.Builder(CoolAnimActivity.this, R.style.custom_dialog)
                .setView(layout)
                .create();

        mBtnDlgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
	}
    
}
