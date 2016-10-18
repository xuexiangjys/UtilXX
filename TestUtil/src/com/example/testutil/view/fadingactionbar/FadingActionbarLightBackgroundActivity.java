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

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.testutil.R;
import com.xuexiang.view.fadingactionbar.extras.actionbarcompat.FadingActionBarHelper;

public class FadingActionbarLightBackgroundActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FadingActionBarHelper helper = new FadingActionBarHelper()
            .actionBarBackground(R.drawable.ab_background) //设置actionbar的背景
            .headerLayout(R.layout.fadingactionbar_header_light) // 设置顶部的布局文件
            .contentLayout(R.layout.activity_fadingactionbarscrollview);//设置布局文件，这里是scroolview。可以自己随便设置
        //初始化
        setContentView(helper.createView(this));
        helper.initActionBar(this);
    }

}
