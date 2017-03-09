package com.example.testutil.system;

import java.io.File;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.data.sharedPreferences.SettingSharePreferenceUtil;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.log.LogHelper;
import com.xuexiang.util.view.DialogUtil;
import com.xuexiang.view.SlideSwitch;
import com.xuexiang.view.SlideSwitch.SlideListener;
/**  
 * 创建时间：2016-7-31 下午9:26:18  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：LogInfoActivity.java  
 **/
public class LogHelperActivity extends BaseActivity implements OnClickListener{
	private SlideSwitch switch_loghelper;
	private SettingSharePreferenceUtil mSettingSPUtil;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_loghelper);
		initTitleBar(TAG);
		
		initview();	
		
	}
	
	private void initview() {      
		initSlideSwitch();
    }
	
	private void initSlideSwitch() {
		mSettingSPUtil = SettingSharePreferenceUtil.getInstance(this);	
		switch_loghelper = (SlideSwitch) findViewById(R.id.switch_loghelper);
		switch_loghelper.setState(mSettingSPUtil.isDebugMode());
		switch_loghelper.setSlideListener(new SlideListener() {
			@Override
			public void open() {
				File pDebugLogDir = new File(LocalFileUtil.LOG_DIR + "debug_log/");
				if (pDebugLogDir.exists() && pDebugLogDir.listFiles().length > 0) {
					DialogUtil.showChangePrompt(mContext, "是否清空旧的日志？",
							new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									LogHelper.open(mContext, true);
								}}, new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									LogHelper.open(mContext, false);
								}
							});
				} else {
					LogHelper.open(mContext, false);
				}					
				LogHelper.trace("调试模式被:【打开】");
				mSettingSPUtil.setDebugMode(true);
			}
			@Override
			public void close() {			
				LogHelper.trace("调试模式被:【关闭】");
				LogHelper.close();
				mSettingSPUtil.setDebugMode(false);
		}});
		
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_commonloginfo:
			LogHelper.trace("这是一条普通的信息日志！");			
			break;
			
		case R.id.btn_errorloginfo:
			try {
				int i = 20 / 0;
			} catch (Exception e) {
				LogHelper.saveExceptionStackInfo(e);
			}	
//			LogHelper.saveExceptionStackInfo(new Throwable("错误信息"));
			break;
		case R.id.btn_crashloginfo:
			Button button = (Button)findViewById(R.id.textView1);
			break;

		default:
			break;
		}
		
	}

}
