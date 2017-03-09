package com.example.mycustomview.custom;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.view.Colors;
import com.xuexiang.view.TitleBar;
import com.xuexiang.view.shareview.FlipShareView;
import com.xuexiang.view.shareview.ShareItem;
import com.xuexiang.view.snow.SnowView;

/**
 * Created by xx on 16-12-1.
 */
public class SnowActivity extends BaseActivity {

    private SnowView mSnowView;
    
    @Override
	public void onCreateActivity() {
	    setContentView(R.layout.activity_snow);
	    initTitleBarWithRightMenu(TAG, new TitleBar.ImageAction(R.drawable.ic_more) {
			@Override
			public void performAction(View view) {
				  FlipShareView shareBottom = new FlipShareView.Builder((Activity) mContext, view)
                  .addItem(new ShareItem("小雪"))
                  .addItem(new ShareItem("中雪"))
                  .addItem(new ShareItem("大雪"))
                  .setSeparateLineColor(Colors.BLACK)
                  .setBackgroundColor(0x60000000)
                  .setAnimType(FlipShareView.TYPE_SLIDE)
                  .create();
		          shareBottom.setOnFlipClickListener(new FlipShareView.OnFlipClickListener() {
		              @Override
		              public void onItemClick(int position) {
		            	  switch (position) {
						case 0:
							mSnowView.changeSnowLevel(SnowView.SNOW_LEVEL_SMALL);
							break;
						case 1:
							mSnowView.changeSnowLevel(SnowView.SNOW_LEVEL_MIDDLE);					
							break;
						case 2:
							mSnowView.changeSnowLevel(SnowView.SNOW_LEVEL_HEAVY);
							break;
						default:
							break;
						}
		              }
					  @Override
					  public void dismiss() {}
		          });
			}
		});
        mSnowView = (SnowView) findViewById(R.id.snow_view);
        mSnowView.startSnowAnim(SnowView.SNOW_LEVEL_MIDDLE);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_snow, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.level_small:
                mSnowView.changeSnowLevel(SnowView.SNOW_LEVEL_SMALL);
                return true;
            case R.id.level_middle:
                mSnowView.changeSnowLevel(SnowView.SNOW_LEVEL_MIDDLE);
                return true;
            case R.id.level_heavy:
                mSnowView.changeSnowLevel(SnowView.SNOW_LEVEL_HEAVY);
                return true;

        }
        return super.onMenuItemSelected(featureId, item);
    }

	
}
