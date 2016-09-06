package com.example.testutil.view.circularfloatingactionmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testutil.R;

public class SystemOverlayMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_overlay);

        Button button = (Button) findViewById(R.id.startOverlayServiceButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start an unbound service.
                Intent is = new Intent(SystemOverlayMenuActivity.this, SystemOverlayMenuService.class);
                startService(is);
                // We need to be able to stop it later though.
                // This is currently done by the red button of the topCenterMenu in SystemOverlayMenuService
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
