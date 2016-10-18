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

public class FadingActionbarNoParallaxActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FadingActionBarHelper helper = new FadingActionBarHelper()
            .actionBarBackground(R.drawable.ab_background)
            .headerLayout(R.layout.fadingactionbar_header)
            .contentLayout(R.layout.activity_fadingactionbarscrollview)
            .parallax(false);//设置没有视差效果，就是说移动的时候顶部的图片随内容上移，无层次感
        
        setContentView(helper.createView(this));
        helper.initActionBar(this);
    }

}
