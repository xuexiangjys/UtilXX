package com.example.mycustomview;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.example.mycustomview.corepage.TestFragment4;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.xuexiang.app.BaseApplication;
import com.xuexiang.view.corepage.core.CoreConfig;
import com.xuexiang.view.corepage.core.PageItem;
import com.xuexiang.view.imagepicker.ImagePicker;
import com.xuexiang.view.imagepicker.view.CropImageView;

/**
 * 创建时间：2017-1-18 下午11:01:21 项目名称：MyCustomView
 * 
 * @author xuexiang 文件名称：MyApplication.java
 **/
public class MyApplication extends BaseApplication {
//	private String pageJson = "[" + "  {" + "    'name': 'test4'," + "    'classPath': 'com.example.mycustomview.corepage.TestFragment4'," + "    'params': ''" + "  }]";

	@Override
	public void onCreate() {
		super.onCreate();
		initImagePicker();
		Map<String, Object> map = new HashMap<>();
		map.put("param1", "这是参数1");
		map.put("param2", "这是参数2");
		CoreConfig.init(this, new PageItem().setName("test4").setClassPath(TestFragment4.class).setParams(map));
	}

	/**
	 * 初始化仿微信控件ImagePicker
	 */
	private void initImagePicker() {
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
		ImagePicker imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new com.xuexiang.view.imagepicker.loader.ImageLoader() {
			@Override
			public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
				ImageSize size = new ImageSize(width, height);
				ImageLoader.getInstance().displayImage(Uri.parse("file://" + path).toString(), imageView, size);
			}

			@Override
			public void clearMemoryCache() {

			}
		}); // 设置图片加载器
		imagePicker.setShowCamera(true); // 显示拍照按钮
		imagePicker.setCrop(true); // 允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true); // 是否按矩形区域保存
		imagePicker.setSelectLimit(9); // 选中数量限制
		imagePicker.setStyle(CropImageView.Style.RECTANGLE); // 裁剪框的形状
		imagePicker.setFocusWidth(800); // 裁剪框的宽度。单位像素（圆形自动取宽高最小值）
		imagePicker.setFocusHeight(800); // 裁剪框的高度。单位像素（圆形自动取宽高最小值）
		imagePicker.setOutPutX(1000);// 保存文件的宽度。单位像素
		imagePicker.setOutPutY(1000);// 保存文件的高度。单位像素
	}

}
