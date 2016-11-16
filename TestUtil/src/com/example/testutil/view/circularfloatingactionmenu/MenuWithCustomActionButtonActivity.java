package com.example.testutil.view.circularfloatingactionmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.view.circularfloatingactionmenu.FloatingActionMenu;
import com.xuexiang.view.circularfloatingactionmenu.SubActionButton;

public class MenuWithCustomActionButtonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_custom_action_button);
        // Our action button is this time just a regular view!
        Button centerActionButton = (Button) findViewById(R.id.centerActionButton);

        // Add some items to the menu. They are regular views as well!
        TextView a = new TextView(this); a.setText("a"); a.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView b = new TextView(this); b.setText("b"); b.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView c = new TextView(this); c.setText("c"); c.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView d = new TextView(this); d.setText("d"); d.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView e = new TextView(this); e.setText("e"); e.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView f = new TextView(this); f.setText("f"); f.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView g = new TextView(this); g.setText("g"); g.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView h = new TextView(this); h.setText("h"); h.setBackgroundResource(android.R.drawable.btn_default_small);
        FrameLayout.LayoutParams tvParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        a.setLayoutParams(tvParams);
        b.setLayoutParams(tvParams);
        c.setLayoutParams(tvParams);
        d.setLayoutParams(tvParams);
        e.setLayoutParams(tvParams);
        f.setLayoutParams(tvParams);
        g.setLayoutParams(tvParams);
        h.setLayoutParams(tvParams);

        SubActionButton.Builder subBuilder = new SubActionButton.Builder(this);

        FloatingActionMenu circleMenu = new FloatingActionMenu.Builder(this)
                .setStartAngle(0) // A whole circle!
                .setEndAngle(360)
                .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
                .addSubActionView(a)
                .addSubActionView(b)
                .addSubActionView(c)
                .addSubActionView(d)
                .addSubActionView(e)
                .addSubActionView(f)
                .addSubActionView(g)
                .addSubActionView(h)
                .attachTo(centerActionButton)
                .build();

    }
}
