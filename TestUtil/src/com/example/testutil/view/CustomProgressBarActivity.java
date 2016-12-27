package com.example.testutil.view;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.LoadingButton;
import com.xuexiang.view.customprogressbar.CBProgressBar;
import com.xuexiang.view.customprogressbar.MyHoriztalProgressBar;
import com.xuexiang.view.customprogressbar.MyHoriztalProgressBar2;
import com.xuexiang.view.customprogressbar.MyRoundProgressBar;
import com.xuexiang.view.customprogressbar.MyRoundProgressBar2;
import com.xuexiang.view.customprogressbar.ZYDownloading;

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
    
    private Button mStartBtn;
    private LoadingButton mDefaultLButton;
    
    private static final int UPDATE_CBPROGRESSBAR = 0;
	boolean isDownloading;
	boolean stop;
	private Button btnDownload;
	private CBProgressBar cbProgress,cbProgress2,cbProgress3 ;
	
	private ZYDownloading zyDownloading;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_CBPROGRESSBAR:
				cbProgress.setProgress(msg.arg1);
				cbProgress2.setProgress(msg.arg1);
				cbProgress3.setProgress(msg.arg1);
				if(msg.arg1==100){
					isDownloading = false;
					btnDownload.setText("下载");
				}
				break;
			default:
				break;
			}
		};
	};

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_customprogressbar);
		
		initTitleBar(TAG);
		
		initView();
		
		initLoadingButton();
		
		initCBProgressBar();
		
		initZYDownloading();
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
	

	private void initLoadingButton() {
		mDefaultLButton = (LoadingButton) findViewById(R.id.lbtn_default);	
		mDefaultLButton.setCallback(new LoadingButton.Callback() {
            @Override
            public void complete() {
                Toast("下载完成,可以在这里写完成的回调方法");
            }
        });
		 
	    mStartBtn = (Button) findViewById(R.id.btn_start);	
		mStartBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDefaultLButton.setTargetProgress(360);
			}
		});
	}

	
	private void initCBProgressBar() {
		cbProgress = (CBProgressBar) this.findViewById(R.id.my_progress);
		cbProgress2 = (CBProgressBar) this.findViewById(R.id.my_progress2);
		cbProgress3 = (CBProgressBar) this.findViewById(R.id.my_progress3);
		cbProgress.setMax(100);
		cbProgress2.setMax(100);
		cbProgress3.setMax(100);
		btnDownload =  (Button) this.findViewById(R.id.btn_download);
		btnDownload.setText("下载");
		btnDownload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(!isDownloading){
					stop = false;
					isDownloading = true;
					btnDownload.setText("停止");
					downloading(cbProgress);
					downloading(cbProgress2);
					downloading(cbProgress3);
				}else{
					isDownloading = false;
					stop = true;
					btnDownload.setText("下载");
				}
			}
		});
	}
	
	private void downloading(CBProgressBar cbProgress){
	    new Thread(new Runnable() {
			@Override
			public void run() {
				int progress = 0;
				while(!stop){
					if(progress>=100){
						break;
					}
					Message msg = handler.obtainMessage();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					progress+=1;
					msg.what= UPDATE_CBPROGRESSBAR;
					msg.arg1 = progress;
					msg.sendToTarget();
				}
				progress = 0;
			}
		}).start();
		
	}

	private void initZYDownloading() {
		zyDownloading = (ZYDownloading) findViewById(R.id.acd_zydownloading);
        zyDownloading.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			    if (!zyDownloading.isDownloading()) {
                    zyDownloading.startDownload();
				    new Timer().schedule(new TimerTask() {
			            int i = 0;
			            @Override
			            public void run() {
			            	runOnUiThread(new Runnable() {
								public void run() {
									zyDownloading.setProgress(++i);
								}
							});
			            }
			        }, 1500, 100);
                }
			}
		});
	}

}
