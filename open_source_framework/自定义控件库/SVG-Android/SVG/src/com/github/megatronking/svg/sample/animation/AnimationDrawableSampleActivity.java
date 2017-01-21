package com.github.megatronking.svg.sample.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.megatronking.svg.sample.R;


public class AnimationDrawableSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable_sample);
        setTitle(getIntent().getStringExtra("title"));

        ImageView imageView = (ImageView) findViewById(R.id.animation_drawable_image);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }
}
