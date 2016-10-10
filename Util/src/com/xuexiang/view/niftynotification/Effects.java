package com.xuexiang.view.niftynotification;

import com.xuexiang.view.niftynotification.effects.BaseEffect;
import com.xuexiang.view.niftynotification.effects.Flip;
import com.xuexiang.view.niftynotification.effects.Jelly;
import com.xuexiang.view.niftynotification.effects.Scale;
import com.xuexiang.view.niftynotification.effects.SlideIn;
import com.xuexiang.view.niftynotification.effects.SlideOnTop;
import com.xuexiang.view.niftynotification.effects.Standard;
import com.xuexiang.view.niftynotification.effects.ThumbSlider;

/*
 * Copyright 2014 gitonway
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public enum Effects {
    standard(Standard.class),
    slideOnTop(SlideOnTop.class),
    flip(Flip.class),
    slideIn(SlideIn.class),
    jelly(Jelly.class),
    thumbSlider(ThumbSlider.class),
    scale(Scale.class);


    private Class<? extends BaseEffect> effectsClazz;

    private Effects(Class<? extends BaseEffect> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffect getAnimator() {
        BaseEffect bEffects=null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
