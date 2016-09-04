package com.example.testutil.view;

import java.util.Timer;
import java.util.TimerTask;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.customprogressbar.MyHoriztalProgressBar;
import com.xuexiang.view.customprogressbar.MyHoriztalProgressBar2;
import com.xuexiang.view.customprogressbar.MyRoundProgressBar;
import com.xuexiang.view.customprogressbar.MyRoundProgressBar2;

/**  
 * 创建时间：2016-6-26 下午5:29:17  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：CustomProgressBarActivity.java  
 **/
public class CustomProgressBarActivity extends BaseActivity {
	private MyHoriztalProgressBar progressBar1, progressBar2, progressBar3;
    private MyRoundProgressBar progressBar4, progressBar5;
    private MyRoundProgressBar2 progressBar6;
    private MyHoriztalProgressBar2 progressBar7;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_customprogressbar);
		
		initTitleBar(TAG);
		
		initView();
	}

	private void initView() {
		progressBar1 = (MyHoriztalProgressBar) findViewById(R.id.progressbar1);
        progressBar2 = (MyHoriztalProgressBar) findViewById(R.id.progressbar2);
        progressBar3 = (MyHoriztalProgressBar) findViewById(R.id.progressbar3);
        progressBar4 = (MyRoundProgressBar) findViewById(R.id.progressbar4);
        progressBar5 = (MyRoundProgressBar) findViewById(R.id.progressbar5);
        progressBar6 = (MyRoundProgressBar2) findViewById(R.id.progressbar6);
        progressBar7 = (MyHoriztalProgressBar2) findViewById(R.id.progressbar7);
        
        
        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                progressBar1.setProgress(++i);
            }
        }, 0, 150);
        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                progressBar2.setProgress(++i);
            }
        }, 100, 180);
        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                progressBar3.setProgress(++i);
            }
        }, 0, 250);
        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                progressBar4.setProgress(++i);
            }
        }, 0, 150);
        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                progressBar5.setProgress(++i);
            }
        }, 0, 200);
        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                progressBar6.setProgress(++i);
            }
        }, 200, 200);
        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                progressBar7.setProgress(++i);
            }
        },200,100);
	}

}
