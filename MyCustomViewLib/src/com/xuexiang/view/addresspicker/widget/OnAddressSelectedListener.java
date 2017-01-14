package com.xuexiang.view.addresspicker.widget;


import com.xuexiang.view.addresspicker.bean.City;
import com.xuexiang.view.addresspicker.bean.County;
import com.xuexiang.view.addresspicker.bean.Province;
import com.xuexiang.view.addresspicker.bean.Street;

public interface OnAddressSelectedListener {
    void onAddressSelected(Province province, City city, County county, Street street);
}
