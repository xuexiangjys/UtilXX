/*
 * Copyright (C) 2013 Manuel Peinado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.testutil.view.fadingactionbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.testutil.R;
import com.xuexiang.view.fadingactionbar.extras.actionbarcompat.FadingActionBarHelper;

/**
 * @author:Jack Tony
 * @tips  :内部是fragment的activity
 * @date  :2014-8-9
 */
public class FadingActionbarSampleFragment extends Fragment {
    private FadingActionBarHelper mFadingHelper;
    private Bundle mArguments;

    public static final String ARG_IMAGE_RES = "image_source";
    public static final String ARG_ACTION_BG_RES = "image_action_bs_res";

    /* （非 Javadoc）
     * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
     * 在fragment和activity绑定的时候进行如下方法
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mArguments = getArguments();
        //初始化，如果接受的参数不为空那么就用设置好的图片，如果是null，那么就用 ab_background_light
        int actionBarBg = mArguments != null ? mArguments.getInt(ARG_ACTION_BG_RES) : R.drawable.ab_background_light;

        //设置helper
        mFadingHelper = new FadingActionBarHelper()
            .actionBarBackground(actionBarBg)
            .headerLayout(R.layout.fadingactionbar_header_light) //设置背景色
            .contentLayout(R.layout.activity_fadingactionbarscrollview) //设置顶部的布局
            .lightActionBar(actionBarBg == R.drawable.ab_background_light);
        
        mFadingHelper.initActionBar(activity);
    }
    
    /* （非 Javadoc）
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     * 在fragment中的回调方法中，用helper来设置视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mFadingHelper.createView(inflater);// 设置填充器

        if (mArguments != null){
            ImageView img = (ImageView) view.findViewById(R.id.image_header);
            img.setImageResource(mArguments.getInt(ARG_IMAGE_RES));
        }

        return view;
    }
}
