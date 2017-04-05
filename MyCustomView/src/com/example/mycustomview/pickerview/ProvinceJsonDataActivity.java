package com.example.mycustomview.pickerview;

import java.util.ArrayList;

import org.json.JSONArray;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.example.mycustomview.pickerview.bean.ProvinceJsonBean;
import com.google.gson.Gson;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.resource.ResourceUtils;
import com.xuexiang.view.pickerview.OptionsPickerView;

/**
 * @TODO<解析省市区数据示例>
 * @author 小嵩
 * @date 2017-3-16
 */
public class ProvinceJsonDataActivity extends BaseHeadActivity implements View.OnClickListener{


    private ArrayList<ProvinceJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;

    @Override
	protected int getLayoutId() {
		return R.layout.activity_city_json_data;
	}

	@Override
	protected void init() {
		initView();
	}
	
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread==null){//如果已创建就不再重新创建子线程了

                        Toast.makeText(ProvinceJsonDataActivity.this,"开始解析数据",Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    Toast.makeText(ProvinceJsonDataActivity.this,"解析数据成功",Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(ProvinceJsonDataActivity.this,"解析数据失败",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
    private void initView() {

        findViewById(R.id.btn_data).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_data:
                mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                break;
            case R.id.btn_show:
                if (isLoaded){
                    ShowPickerView();
                }else {
                    Toast.makeText(ProvinceJsonDataActivity.this,"数据暂未解析成功，请等待",Toast.LENGTH_SHORT).show();
                }

                break;

        }

    }


    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView  pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);

                Toast.makeText(ProvinceJsonDataActivity.this,tx,Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setTypeface(Typeface.DEFAULT_BOLD)//加粗字体
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/

        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = ResourceUtils.getFileFromAssets(mContext, "province.json", false);//获取assets目录下的json文件数据

        ArrayList<ProvinceJsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空数据，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                }
                for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                    String Area = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                    City_AreaList.add(Area);//添加该城市所有地区数据
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
            }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<ProvinceJsonBean> parseData(String result) {//Gson 解析
        ArrayList<ProvinceJsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceJsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceJsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

	
}
