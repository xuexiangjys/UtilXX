package com.example.mycustomview.custom;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.addresspicker.bean.City;
import com.xuexiang.view.addresspicker.bean.County;
import com.xuexiang.view.addresspicker.bean.Province;
import com.xuexiang.view.addresspicker.bean.Street;
import com.xuexiang.view.addresspicker.utils.LogUtil;
import com.xuexiang.view.addresspicker.widget.AddressSelector;
import com.xuexiang.view.addresspicker.widget.BottomDialog;
import com.xuexiang.view.addresspicker.widget.OnAddressSelectedListener;


/**
 * Created by smartTop on 2016/12/6.
 */

public class AddressPickerActivity extends BaseActivity implements View.OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {
    private TextView tv_selector_area;
    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private LinearLayout content;
    
    @Override
	public void onCreateActivity() {
    	setContentView(R.layout.activity_address_picker);
    	initTitleBar(TAG);
    	initView();
	}
    

    private void initView() {
    	tv_selector_area = (TextView) findViewById(R.id.tv_selector_area);
        content = (LinearLayout) findViewById(R.id.content);
        tv_selector_area.setOnClickListener(this);
        AddressSelector selector = new AddressSelector(this);
        selector.setTextSize(14);//设置字体的大小
//        selector.setIndicatorBackgroundColor("#00ff00");
        selector.setIndicatorBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));//设置指示器的颜色
//        selector.setBackgroundColor(android.R.color.holo_red_light);//设置字体的背景

        selector.setTextSelectedColor(getResources().getColor(android.R.color.holo_orange_light));//设置字体获得焦点的颜色

        selector.setTextUnSelectedColor(getResources().getColor(android.R.color.holo_blue_light));//设置字体没有获得焦点的颜色

//        //获取数据库管理
//        AddressDictManager addressDictManager = selector.getAddressDictManager();
//        AdressBean.ChangeRecordsBean changeRecordsBean = new AdressBean.ChangeRecordsBean();
//        changeRecordsBean.parentId = 0;
//        changeRecordsBean.name = "测试省";
//        changeRecordsBean.id = 35;
//        addressDictManager.inserddress(changeRecordsBean);//对数据库里增加一个数据
        selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {

            }
        });
        View view = selector.getView();
        content.addView(view);
    }

    @Override
    public void onClick(View view) {
        if (dialog != null) {
            dialog.show();
        } else {
            dialog = new BottomDialog(this);
            dialog.setOnAddressSelectedListener(this);
            dialog.setDialogDismisListener(this);
            dialog.setTextSize(14);//设置字体的大小
            dialog.setIndicatorBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));//设置指示器的颜色
            dialog.setTextSelectedColor(getResources().getColor(android.R.color.holo_orange_light));//设置字体获得焦点的颜色
            dialog.setTextUnSelectedColor(getResources().getColor(android.R.color.holo_blue_light));//设置字体没有获得焦点的颜色
            dialog.show();
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
        LogUtil.d("数据", "街道id=" + streetCode);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        tv_selector_area.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dialogclose() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }
	
}
