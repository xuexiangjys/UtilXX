package com.example.testutil.view.materialdesign;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.view.materialdesign.view.ButtonFloat;



public class ButtonsActivity extends Activity {
	private ButtonFloat mButtonFloat;
	int backgroundColor = Color.parseColor("#1E88E5");

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        int color = getIntent().getIntExtra("BACKGROUND", Color.BLACK);
        findViewById(R.id.buttonflat).setBackgroundColor(color);
        findViewById(R.id.button).setBackgroundColor(color);
        findViewById(R.id.buttonFloatSmall).setBackgroundColor(color);
        findViewById(R.id.buttonIcon).setBackgroundColor(color);
        mButtonFloat =  (ButtonFloat) findViewById(R.id.buttonFloat);
        mButtonFloat.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Toast.makeText(ButtonsActivity.this, "µã»÷ÊÂ¼þ", Toast.LENGTH_SHORT).show();
			}
		});
    }  
    

}
