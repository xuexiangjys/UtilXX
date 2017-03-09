package com.example.mycustomview.custom;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.net.JsonUtil;
import com.xuexiang.util.resource.ResourceUtils;
import com.xuexiang.util.view.InputMethodUtils;
import com.xuexiang.view.addresswheel.model.AddressDtailsEntity;
import com.xuexiang.view.addresswheel.model.AddressModel;
import com.xuexiang.view.addresswheel.view.ChooseAddressWheel;
import com.xuexiang.view.addresswheel.view.listener.OnAddressChangeListener;

public class AddressWheelActivity extends BaseActivity implements OnAddressChangeListener {

	private TextView chooseAddress;

    private ChooseAddressWheel chooseAddressWheel = null;

    @Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_addresswheel);
		 initTitleBar(TAG);
	     init();
	}
    
    private void init() {
        initWheel();
        initData();
    }

    private void initWheel() {
        chooseAddressWheel = new ChooseAddressWheel(this);
        chooseAddressWheel.setOnAddressChangeListener(this);
        
        chooseAddress = (TextView) findViewById(R.id.choose_address);
        chooseAddress.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				InputMethodUtils.hideKeyboard(v);
		        chooseAddressWheel.show(v);
			}
		});
    }

    private void initData() {
        String address = ResourceUtils.readStringFromAssert(this, "address.txt");
        AddressModel model = JsonUtil.fromRequest(address, AddressModel.class);
        if (model != null) {
            AddressDtailsEntity data = model.Result;
            if (data == null) return;
            chooseAddress.setText(data.Province + " " + data.City + " " + data.Area);
            if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
                chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                chooseAddressWheel.defaultValue(data.Province, data.City, data.Area);
            }
        }
    }
    
    @Override
    public void onAddressChange(String province, String city, String district) {
        chooseAddress.setText(province + " " + city + " " + district);
    }

	
}
