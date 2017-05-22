package com.example.mycustomview.pickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.mycustomview.R;
import com.xuexiang.view.pickers.common.LineConfig;
import com.xuexiang.view.pickers.entity.City;
import com.xuexiang.view.pickers.entity.County;
import com.xuexiang.view.pickers.entity.Province;
import com.xuexiang.view.pickers.listeners.OnItemPickListener;
import com.xuexiang.view.pickers.listeners.OnLinkageListener;
import com.xuexiang.view.pickers.listeners.OnMoreItemPickListener;
import com.xuexiang.view.pickers.listeners.OnSingleWheelListener;
import com.xuexiang.view.pickers.picker.AddressPicker;
import com.xuexiang.view.pickers.picker.DatePicker;
import com.xuexiang.view.pickers.picker.DateTimePicker;
import com.xuexiang.view.pickers.picker.LinkagePicker;
import com.xuexiang.view.pickers.picker.NumberPicker;
import com.xuexiang.view.pickers.picker.SinglePicker;
import com.xuexiang.view.pickers.picker.TimePicker;
import com.xuexiang.view.pickers.util.ConvertUtils;
import com.xuexiang.view.pickers.util.DateUtils;
import com.xuexiang.view.pickers.util.LogUtils;
/**
 * @author matt
 * blog: addapp.cn
 */
public class PickersActivity extends BaseActivity {

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_pickers);
    }

    @Override
    protected void setContentViewAfter(View contentView) {

    }

//    @Override
//    public void onBackPressed() {
//        AppManager.getInstance().exitApp();
//    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onNestView(View view) {
        startActivity(new Intent(this, NextActivity.class));
    }

    public void onAnimationStyle(View view) {
        NumberPicker picker = new NumberPicker(this);
        picker.setAnimationStyle(R.style.Animation_CustomPopup);
        picker.setCanLoop(false);
        picker.setOffset(2);//偏移量
        picker.setRange(10.5, 20, 1.5);//数字范围
        picker.setSelectedItem(18.0);
        picker.setLabel("℃");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                showToast("index=" + index + ", item=" + item.doubleValue());
            }
        });
        picker.show();
    }

    public void onAnimator(View view) {
        CustomPicker picker = new CustomPicker(this);
        picker.setOffset(1);//显示的条目的偏移量，条数为（offset*2+1）
        picker.setGravity(Gravity.CENTER);//居中
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int position, String option) {
                showToast("index=" + position + ", item=" + option);
            }
        });
        picker.show();
    }

    public void onYearMonthDayPicker(View view) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(2016, 8, 29);
        picker.setRangeEnd(2111, 1, 11);
        picker.setSelectedItem(2050, 10, 14);
        picker.setWeightEnable(true);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                showToast(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }


    public void onYearMonthDayTimePicker(View view) {
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        picker.setDateRangeStart(2017, 1, 1);
        picker.setDateRangeEnd(2025, 11, 11);
        picker.setTimeRangeStart(9, 0);
        picker.setTimeRangeEnd(20, 30);
        picker.setWeightEnable(true);
        picker.setWheelModeEnable(true);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                showToast(year + "-" + month + "-" + day + " " + hour + ":" + minute);
            }
        });
        picker.show();
    }


    public void onYearMonthPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        picker.setWidth((int) (picker.getScreenWidthPixels() * 0.6));
        picker.setRangeStart(2016, 10, 14);
        picker.setRangeEnd(2020, 11, 11);
        picker.setSelectedItem(2017, 9);
        picker.setWeightEnable(true);
        picker.setWheelModeEnable(true);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                showToast(year + "-" + month);
            }
        });
        picker.show();
    }

    public void onMonthDayPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.MONTH_DAY);
        picker.setGravity(Gravity.CENTER);//弹框居中
        picker.setWeightEnable(true);
        picker.setRangeStart(5, 1);
        picker.setRangeEnd(12, 31);
        picker.setSelectedItem(10, 14);
        picker.setWheelModeEnable(false);
        picker.setOnDatePickListener(new DatePicker.OnMonthDayPickListener() {
            @Override
            public void onDatePicked(String month, String day) {
                showToast(month + "-" + day);
            }
        });
        picker.show();
    }

    public void onTimePicker(View view) {
        TimePicker picker = new TimePicker(this, TimePicker.HOUR_24);
        picker.setRangeStart(9, 0);//09:00
        picker.setRangeEnd(18, 0);//18:30
        picker.setTopLineVisible(false);
        picker.setLineVisible(false);
        picker.setWheelModeEnable(true);
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                showToast(hour + ":" + minute);
            }
        });
        picker.show();
    }

    public void onOptionPicker(View view) {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0;i<60; i++){
            String s = "";
            if(i<10){
                s = "0"+i;
            }else{
                s = i+"";
            }
            list.add(s);
        }
//        String[] ss = (String[]) list.toArray();
        SinglePicker<String> picker = new SinglePicker<>(this, list);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(1);
        picker.setWheelModeEnable(true);
        //启用权重 setWeightWidth 才起作用
        picker.setLabel("分");
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(0xFF279BAA);//前四位值是透明度
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            @Override
            public void onWheeled(int index, String item) {
                showToast("index=" + index + ", item=" + item);
            }
        });
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                showToast("index=" + index + ", item=" + item);
            }
        });
        picker.show();
    }

    public void onLinkagePicker(View view) {
        LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

            @Override
            public boolean isOnlyTwo() {
                return true;
            }

            @Override
            public List<String> provideFirstData() {
                ArrayList<String> firstList = new ArrayList<>();
                firstList.add("12");
                firstList.add("24");
                return firstList;
            }

            @Override
            public List<String> provideSecondData(int firstIndex) {
                ArrayList<String> secondList = new ArrayList<>();
                for (int i = 1; i <= (firstIndex == 0 ? 12 : 24); i++) {
                    String str = DateUtils.fillZero(i);
                    if (firstIndex == 0) {
                        str += "￥";
                    } else {
                        str += "$";
                    }
                    secondList.add(str);
                }
                return secondList;
            }

            @Override
            public List<String> provideThirdData(int firstIndex, int secondIndex) {
                return null;
            }

        };
        LinkagePicker picker = new LinkagePicker(this, provider);
        picker.setCanLoop(true);
        picker.setLabel("小时制", "点");
        picker.setSelectedIndex(0, 8);
        //picker.setSelectedItem("12", "9");
        picker.setOnMoreItemPickListener(new OnMoreItemPickListener<String>() {

            @Override
            public void onItemPicked(String first, String second, String third) {
                showToast(first + "-" + second + "-" + third);
            }
        });
        picker.show();
    }

    public void onConstellationPicker(View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(this,
                isChinese ? new String[]{
                        "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
                        "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"
                } : new String[]{
                        "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"
                });
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFFEE0000);
        picker.setUnSelectedTextColor(0xFF999999);
        LineConfig config = new LineConfig();
        config.setColor(0xFFEE0000);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                showToast("index=" + index + ", item=" + item);
            }
        });
        picker.show();
    }

    public void onNumberPicker(View view) {
        NumberPicker picker = new NumberPicker(this);
        picker.setWidth(picker.getScreenWidthPixels() / 2);
        picker.setCanLoop(false);
        picker.setLineVisible(false);
        picker.setWheelModeEnable(true);
        picker.setOffset(2);//偏移量
        picker.setRange(145, 200, 1);//数字范围
        picker.setSelectedItem(172);
        picker.setLabel("厘米");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                showToast("index=" + index + ", item=" + item.intValue());
            }
        });
        picker.show();
    }

    public void onAddressPicker(View view) {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    showToast(province.getAreaName() + city.getAreaName());
                } else {
                    showToast(province.getAreaName() + city.getAreaName() + county.getAreaName());
                }
            }
        });
        task.execute("贵州", "毕节", "纳雍");
    }

    public void onAddress2Picker(View view) {
        try {
            ArrayList<Province> data = new ArrayList<>();
            String json = ConvertUtils.toString(getAssets().open("city2.json"));
            data.addAll(JSON.parseArray(json, Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            picker.setHideProvince(true);
            picker.setCanLoop(true);
            picker.setWheelModeEnable(true);
            picker.setSelectedItem("贵州", "贵阳", "花溪");
            picker.setOnLinkageListener(new OnLinkageListener() {
                @Override
                public void onAddressPicked(Province province, City city, County county) {
                    showToast("province : " + province + ", city: " + city + ", county: " + county);
                }
            });
            picker.show();
        } catch (Exception e) {
            showToast(LogUtils.toStackTraceString(e));
        }
    }


    public void onAddress3Picker(View view) {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                showToast(province.getAreaName() + " " + city.getAreaName());
            }
        });
        task.execute("四川", "阿坝");
    }

}
