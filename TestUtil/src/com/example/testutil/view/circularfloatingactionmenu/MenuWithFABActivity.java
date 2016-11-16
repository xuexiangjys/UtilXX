package com.example.testutil.view.circularfloatingactionmenu;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.view.circularfloatingactionmenu.FloatingActionButton;
import com.xuexiang.view.circularfloatingactionmenu.FloatingActionMenu;
import com.xuexiang.view.circularfloatingactionmenu.FloatingActionMenu.MenuItemOnClickListener;
import com.xuexiang.view.circularfloatingactionmenu.SubActionButton;

public class MenuWithFABActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_custom);

        // Set up the white button on the lower right corner
        // more or less with default parameter
        final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new_light));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        
        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_chat_light));
        SubActionButton rlSub1 = rLSubBuilder.setContentView(rlIcon1).build();  
        
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera_light));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video_light));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_place_light));

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                                                .addSubActionView(rlSub1, rlSub1.getLayoutParams().width, rlSub1.getLayoutParams().height)
                                                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                                                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                                                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                                                .attachTo(rightLowerButton)
                                                .build();

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });
        
        rlSub1.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "点击了按钮1", Toast.LENGTH_SHORT).show();
			}
		});

        // Set up the large red button on the center right side
        // With custom button and content sizes and margins
        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_size);
        int redActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_size);
        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_margin);
        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.red_action_menu_radius);
        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_size);
        int blueSubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_content_margin);
            
        ImageView fabIconStar = new ImageView(this);
        fabIconStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_important));

        FloatingActionButton.LayoutParams starParams = new FloatingActionButton.LayoutParams(redActionButtonSize, redActionButtonSize);
        starParams.setMargins(redActionButtonMargin,
                              redActionButtonMargin,
                              redActionButtonMargin,
                              redActionButtonMargin);
        fabIconStar.setLayoutParams(starParams);

        FloatingActionButton.LayoutParams fabIconStarParams = new FloatingActionButton.LayoutParams(redActionButtonContentSize, redActionButtonContentSize);
        fabIconStarParams.setMargins(redActionButtonContentMargin,
                                    redActionButtonContentMargin,
                                    redActionButtonContentMargin,
                                    redActionButtonContentMargin);

        final FloatingActionButton leftCenterButton = new FloatingActionButton.Builder(this)
                                                .setContentView(fabIconStar, fabIconStarParams)
                                                .setBackgroundDrawable(R.drawable.button_action_red_selector)
                                                .setPosition(FloatingActionButton.POSITION_LEFT_CENTER)
                                                .setLayoutParams(starParams)
                                                .build();

        // Set up customized SubActionButtons for the right center menu
        SubActionButton.Builder lCSubBuilder = new SubActionButton.Builder(this);
        lCSubBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_action_blue_selector));

        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        blueContentParams.setMargins(blueSubActionButtonContentMargin,
                          blueSubActionButtonContentMargin,
                          blueSubActionButtonContentMargin,
                          blueSubActionButtonContentMargin);
        lCSubBuilder.setLayoutParams(blueContentParams);
        // Set custom layout params
        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
        lCSubBuilder.setLayoutParams(blueParams);

        ImageView lcIcon1 = new ImageView(this);
        ImageView lcIcon2 = new ImageView(this);
        ImageView lcIcon3 = new ImageView(this);
        ImageView lcIcon4 = new ImageView(this);
        ImageView lcIcon5 = new ImageView(this);

        lcIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera));
        lcIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_picture));
        lcIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video));
        lcIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_location_found));
        lcIcon5.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_headphones));

        SubActionButton tcSub1 = lCSubBuilder.setContentView(lcIcon1, blueContentParams).build();  
        SubActionButton tcSub2 = lCSubBuilder.setContentView(lcIcon2, blueContentParams).build();  
        SubActionButton tcSub3 = lCSubBuilder.setContentView(lcIcon3, blueContentParams).build();  
        SubActionButton tcSub4 = lCSubBuilder.setContentView(lcIcon4, blueContentParams).build();  
        SubActionButton tcSub5 = lCSubBuilder.setContentView(lcIcon5, blueContentParams).build();  
        
        // Build another menu with custom options
        final FloatingActionMenu leftCenterMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(tcSub1, tcSub1.getLayoutParams().width, tcSub1.getLayoutParams().height)
                .addSubActionView(tcSub2, tcSub2.getLayoutParams().width, tcSub2.getLayoutParams().height)
                .addSubActionView(tcSub3, tcSub3.getLayoutParams().width, tcSub3.getLayoutParams().height)
                .addSubActionView(tcSub4, tcSub4.getLayoutParams().width, tcSub4.getLayoutParams().height)
                .addSubActionView(tcSub5, tcSub5.getLayoutParams().width, tcSub5.getLayoutParams().height)
                .setRadius(redActionMenuRadius)
                .setStartAngle(70)
                .setEndAngle(-70)
                .attachTo(leftCenterButton)
                .build();
        leftCenterMenu.setMenuItemOnClickListener(new MenuItemOnClickListener() {			
			@Override
			public void onMenuItemClick(View v, int position) {
				Toast.makeText(getApplicationContext(), "点击了第" + (position + 1) + "个按钮", Toast.LENGTH_SHORT).show();
			}
		});
        
    }

}
