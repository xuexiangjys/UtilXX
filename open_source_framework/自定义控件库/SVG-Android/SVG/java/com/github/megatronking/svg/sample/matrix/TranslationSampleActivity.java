package com.github.megatronking.svg.sample.matrix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.megatronking.svg.sample.R;


public class TranslationSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_translation_sample);
        setTitle(getIntent().getStringExtra("title"));
    }
}
