package com.example.testutil.view.pulllayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.testutil.R;
import com.xuexiang.view.pulllayout.PullToZoomLayout;

public class ZoomLayoutActivity extends Activity {

    private PullToZoomLayout zoomLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_layout_activity);

        zoomLayout = (PullToZoomLayout) findViewById(R.id.zoom_layout);
        ((ImageView)(zoomLayout.getHeaderView())).setImageResource(R.drawable.head);
    }
}
