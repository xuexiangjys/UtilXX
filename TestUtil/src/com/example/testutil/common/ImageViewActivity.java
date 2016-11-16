package com.example.testutil.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.view.BitmapUtil;

public class ImageViewActivity extends BaseActivity{
	private ImageView round1,round2,iv_Combine,blurImageView;
	private SeekBar mSeekBar;
	private Bitmap blurOriginBitmap;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_imageview);
		initTitleBar(TAG);
		init();
	}
	private void init() {
		initView();
		RoundPicture();
		CombinePicture();
	}
	
	private void initView() {
		round1 = (ImageView) findViewById(R.id.round1);
		round2 = (ImageView) findViewById(R.id.round2);
		iv_Combine = (ImageView) findViewById(R.id.iv_Combine);
		blurImageView = (ImageView) findViewById(R.id.blurImageView);
		
		mSeekBar = (SeekBar) findViewById(R.id.seekbar);
		blurOriginBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sea);
		blurImageView.setImageBitmap(blurOriginBitmap);
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
						
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				blurPicture(progress/4);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
		});
		
	}
	/**
	 * 圆形图片
	 */
	private void RoundPicture() {	
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beautiful);
        round1.setImageBitmap(BitmapUtil.getRoundBitmap(bitmap));
        bitmap.recycle();
        
        round2.setImageResource(R.drawable.beautiful);
    }
	
    /**
     * 图片合并
     */
    private void CombinePicture() {
       Bitmap bitmap = BitmapUtil.getRoundBitmap(BitmapFactory.decodeResource(getResources(),
               R.drawable.beautiful));
       Bitmap mask = BitmapFactory.decodeResource(getResources(),
               R.drawable.sea);

       iv_Combine.setImageBitmap(BitmapUtil.combineImagesToSameSize(mask, bitmap));
       bitmap.recycle();
       mask.recycle();
   }
    
    /**
     * 图片滤镜
     */
    private void blurPicture(int radius) {
    	if (radius > 25) {
          radius = 25;
          blurImageView.setImageBitmap(BitmapUtil.blur(this, blurOriginBitmap, radius));
          return;
        } else if (radius <= 0) {
          blurImageView.setImageResource(R.drawable.sea);
          return;
        }  	
    	blurImageView.setImageBitmap(BitmapUtil.blur(this, blurOriginBitmap, radius));
    }

}
