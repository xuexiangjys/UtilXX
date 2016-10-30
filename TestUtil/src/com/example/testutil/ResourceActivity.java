package com.example.testutil;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testutil.resource.PlugLoadActivity;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.app.PackageUtils;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.resource.ResourceUtils;

/**  
 * 创建时间：2016-5-30 下午6:57:17  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：ResourceActivity.java  
 **/
public class ResourceActivity extends BaseActivity implements OnClickListener{

	private ImageView assets_scaleViewimg, assets_zoomimageviewimg;
	private ImageView assets_FlippingImageView, assets_ScaleImageView;
	private TextView contenttv;
//	private AssetsManager mAssetsManager;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_resource);
		initActionBar("资源操作界面");
		
		initView();
	}

	private void initView() {
		assets_scaleViewimg = (ImageView) findViewById(R.id.assets_scaleViewimg);
		assets_scaleViewimg.setImageBitmap(ResourceUtils.getImageFromAssetsFile(mContext, "drawable/beautiful.jpg"));
		
		assets_zoomimageviewimg = (ImageView) findViewById(R.id.assets_zoomimageviewimg);
		assets_zoomimageviewimg.setImageBitmap(ResourceUtils.getImageFromAssetsFile(mContext, "drawable/beautiful.jpg"));
		
		assets_FlippingImageView = (ImageView) findViewById(R.id.assets_FlippingImageView);
		assets_FlippingImageView.setImageBitmap(ResourceUtils.getImageFromAssetsFile(mContext, "drawable/beautiful.jpg"));
		
		assets_ScaleImageView = (ImageView) findViewById(R.id.assets_ScaleImageView);
		assets_ScaleImageView.setImageBitmap(ResourceUtils.getImageFromAssetsFile(mContext, "drawable/beautiful.jpg"));
		
		contenttv = (TextView)findViewById(R.id.content);
//		mAssetsManager = AssetsManager.getInstance(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_copyfilefromasset:
     		ResourceUtils.copyFilesFromAssets(mContext, "drawable", LocalFileUtil.LOCAL_DATA_PATH  + "drawable");
//			mAssetsManager.copyFileFromAssetToSD("drawable", Environment.getExternalStorageDirectory().toString() + "/"  + "drawable");
			if(FileUtils.isFolderExist(LocalFileUtil.LOCAL_DATA_PATH  + "drawable")) {
				mToastUtil.showToast("复制成功！");
			} else {
				mToastUtil.showToast("复制失败！");
			}
			break;
		case R.id.btn_custom_intall_apk:  //自定义安装
			PackageUtils.intallAPKFromAssetByCustom(mContext, "com.example.saolei", "天天扫雷", "saolei.apk" );
            break;
            
		case R.id.btn_system_intall_apk:
			PackageUtils.intallAPKFromAssetBySystem(mContext, "com.way.ftp", "FTP服务器", "FTP.apk");
			break;
			
		case R.id.btn_plugload:
			mToastUtil.showToast("点击了PlugLoadActivity");
			startActivity(PlugLoadActivity.class);
			break;	
			
		case R.id.btn_getfilestring:
			contenttv.setText(ResourceUtils.getFileFromAssets(mContext, "user.txt", true));
			break;	

		default:
			break;
		}
		
	}
	
	
	

}
