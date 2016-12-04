package com.example.mycustomview.jptabbar;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.mycustomview.R;
import com.xuexiang.view.jptabbar.JPTabBar;
import com.xuexiang.view.jptabbar.animate.FlipAnimater;
import com.xuexiang.view.jptabbar.animate.JumpAnimater;
import com.xuexiang.view.jptabbar.animate.RotateAnimater;
import com.xuexiang.view.jptabbar.animate.ScaleAnimater;

/**
 * Created by jpeng on 16-11-14.
 */
public class Tab2Pager extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mGroup;

    private JPTabBar mTabBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =inflater.inflate(R.layout.fragment_tab2,null);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mTabBar = ((JpTabbarActivity)getActivity()).getTabbar();
        mGroup = (RadioGroup) layout.findViewById(R.id.radioGroup);
        mGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.radioButton1:
                mTabBar.setCustomAnimate(new ScaleAnimater());
                break;
            case R.id.radioButton2:
                mTabBar.setCustomAnimate(new JumpAnimater());
                break;
            case R.id.radioButton3:
                mTabBar.setCustomAnimate(new FlipAnimater());
                break;
            case R.id.radioButton4:
                mTabBar.setCustomAnimate(new RotateAnimater());
                break;
        }
    }

}
