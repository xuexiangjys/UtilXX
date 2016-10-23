package com.example.testutil.view.morphingbutton;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.morphingbutton.MorphingButton;

public class MorphingButtonIconActivity extends BaseActivity {

    private int mMorphCounter1 = 1;
    private int mMorphCounter2 = 1;

    public static void startThisActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, MorphingButtonIconActivity.class));
    }
    
    @Override
	public void onCreateActivity() {
    	setContentView(R.layout.activity_morphingbuttonicon);

    	initTitleBar(TAG);
        final MorphingButton btnMorph1 = (MorphingButton) findViewById(R.id.btnMorph1);
        btnMorph1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMorphButton1Clicked(btnMorph1);
            }
        });

        final MorphingButton btnMorph2 = (MorphingButton) findViewById(R.id.btnMorph2);
        btnMorph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMorphButton2Clicked(btnMorph2);
            }
        });

        morphToSquare(btnMorph1, 0);
        morphToFailure(btnMorph2, 0);
	}

    private void onMorphButton1Clicked(final MorphingButton btnMorph) {
        if (mMorphCounter1 == 0) {
            mMorphCounter1++;
            morphToSquare(btnMorph, integer(R.integer.mb_animation));
        } else if (mMorphCounter1 == 1) {
            mMorphCounter1 = 0;
            morphToSuccess(btnMorph);
        }
    }

    private void onMorphButton2Clicked(final MorphingButton btnMorph) {
        if (mMorphCounter2 == 0) {
            mMorphCounter2++;
            morphToFailure(btnMorph,  integer(R.integer.mb_animation));
        } else if (mMorphCounter2 == 1) {
            mMorphCounter2 = 0;
            morphToSquare(btnMorph, integer(R.integer.mb_animation));
        }
    }

    private void morphToSquare(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius((int) dimen(R.dimen.mb_corner_radius_2))
                .width((int) dimen(R.dimen.mb_width_200))
                .height((int) dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_blue))
                .colorPressed(color(R.color.mb_blue_dark))
                .text(getString(R.string.mb_button));
        btnMorph.morph(square);
    }

    private void morphToSuccess(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(integer(R.integer.mb_animation))
                .cornerRadius((int) dimen(R.dimen.mb_height_56))
                .width((int) dimen(R.dimen.mb_height_56))
                .height((int) dimen(R.dimen.mb_height_56))
                .color(color(R.color.green))
                .colorPressed(color(R.color.mb_green_dark))
                .icon(R.drawable.ic_done);
        btnMorph.morph(circle);
    }

    private void morphToFailure(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius((int) dimen(R.dimen.mb_height_56))
                .width((int) dimen(R.dimen.mb_height_56))
                .height((int) dimen(R.dimen.mb_height_56))
                .color(color(R.color.red))
                .colorPressed(color(R.color.mb_red_dark))
                .icon(R.drawable.ic_lock);
        btnMorph.morph(circle);
    }

	

}
