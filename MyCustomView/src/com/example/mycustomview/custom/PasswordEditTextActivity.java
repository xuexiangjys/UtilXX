package com.example.mycustomview.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.mycustomview.R;
import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.view.PasswordEditText;

public class PasswordEditTextActivity extends AppCompatActivity {

    Button submitButton;
    PasswordEditText pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordedittext);
        ActivityUtil.initTitleBarDynamic(this);
        
        submitButton =  (Button)findViewById(R.id.submit_button);
        pwText = (PasswordEditText)findViewById(R.id.input_password);
    }
    
}
