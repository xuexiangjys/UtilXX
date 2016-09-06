package com.example.testutil.view.circularfloatingactionmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.testutil.R;
import com.xuexiang.view.circularfloatingactionmenu.FloatingActionButton;
import com.xuexiang.view.circularfloatingactionmenu.FloatingActionMenu;
import com.xuexiang.view.circularfloatingactionmenu.SubActionButton;

public class MenuWithCustomAnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_custom);
        ImageView fabContent = new ImageView(this);
        fabContent.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_settings));

        FloatingActionButton darkButton = new FloatingActionButton.Builder(this)
                                              .setTheme(FloatingActionButton.THEME_DARK)
                                              .setContentView(fabContent)
                                              .setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER)
                                              .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this)
                                               .setTheme(SubActionButton.THEME_DARK);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        ImageView rlIcon5 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_chat));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_place));
        rlIcon5.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_headphones));

        // Set 4 SubActionButtons
        FloatingActionMenu centerBottomMenu = new FloatingActionMenu.Builder(this)
                .setStartAngle(0)
                .setEndAngle(-180)
                .setAnimationHandler(new SlideInAnimationHandler())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon5).build())
                .attachTo(darkButton)
                .build();

    }

}
