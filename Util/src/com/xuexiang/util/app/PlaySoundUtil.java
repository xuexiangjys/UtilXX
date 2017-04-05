package com.xuexiang.util.app;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * @Description:声音播放的工具类，可以使用meidaplayer，也可以使用SoundPool播放池来播放更有效率
 */ 
public class PlaySoundUtil {
	private MediaPlayer mMediaPlayer;
	private Context mContext;
	private static SoundPool sp;
	public static SoundPool getSoundPool() {
        if (sp == null) {
        	synchronized (PlaySoundUtil.class) {
        		if (sp == null) {
        			sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
//        			notifySoundId = sp.load(context, R.raw.notify, 0);
        		//	waitSoundId = sp.load(context, R.raw.tone_wait, 0);
        		}
			}
        }
        return sp;
    }
	
	public PlaySoundUtil(Context context) {
		mMediaPlayer = new MediaPlayer();
		mContext = context;
		sp = getSoundPool();
	}
	
	public void playSoundsByPool(int sid) {
		int soundid = sp.load(mContext, sid, 0);
		sp.play(soundid, 1, 1, 0, 0, 1);
	}
	
	public void playSounds(int sid) {

		if (mMediaPlayer != null) {
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
			}
			mMediaPlayer.release();
			mMediaPlayer = null;
		}

		mMediaPlayer = MediaPlayer.create(mContext, sid);
		/* 准备播放 */
		 try {
			mMediaPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* 开始播放 */
		mMediaPlayer.start();
	}
}
