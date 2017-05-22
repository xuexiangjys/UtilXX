package com.xuexiang.view.pickers.listeners;

import com.xuexiang.view.pickers.entity.City;
import com.xuexiang.view.pickers.entity.County;
import com.xuexiang.view.pickers.entity.Province;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province province, City city, County county);
}
