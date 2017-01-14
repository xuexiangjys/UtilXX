/*
 *  Copyright 2010 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.xuexiang.view.addresswheel.adapter;

import java.util.List;

import android.content.Context;

import com.xuexiang.view.addresswheel.model.AddressDtailsEntity.ProvinceEntity.CityEntity;

public class CityWheelAdapter extends BaseWheelAdapter<CityEntity> {
    public CityWheelAdapter(Context context, List<CityEntity> list) {
        super(context, list);
    }

    @Override
    protected CharSequence getItemText(int index) {
        CityEntity data = getItemData(index);
        if (data != null) {
            return data.Name;
        }
        return null;
    }
}
