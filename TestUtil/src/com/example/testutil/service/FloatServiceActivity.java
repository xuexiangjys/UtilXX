package com.example.testutil.service;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.service.FloatViewService;

public class FloatServiceActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_floatservice);
		initTitleBar(TAG);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(this, FloatViewService.class);
		switch (v.getId()) {
		case R.id.start_id_1:
			
            i.putExtra(FloatViewService.FLOATVIEW_TYPE, 0);
            startService(i);
			break;
		case R.id.start_id_2:
            i.putExtra(FloatViewService.FLOATVIEW_TYPE, 1);
            startService(i);
			break;
        case R.id.remove_id:        	
			stopService(new Intent(mContext, FloatViewService.class));
			break;

		default:
			break;
		}
		
	}
	
	
	/**
	 * 音量调节
	 * @param context
	 * @param isAdjustLower  是否是调低音量
	 */
	public static void volumeAdjustment(Context context, boolean isAdjustLower) {
		AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); 
		if (isAdjustLower) {    //降低音量，调出系统音量控制    
		  mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);    
		} else {    //增加音量，调出系统音量控制    
		  mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);    
		}   
	}
	

}
