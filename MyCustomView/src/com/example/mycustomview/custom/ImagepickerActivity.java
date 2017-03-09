package com.example.mycustomview.custom;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.imagepicker.ImagePicker;
import com.xuexiang.view.imagepicker.bean.ImageItem;
import com.xuexiang.view.imagepicker.ui.ImageGridActivity;
import com.xuexiang.view.imagepicker.ui.ImagePreviewActivity;

public class ImagepickerActivity extends BaseActivity {

    public static final int IMAGE_PICKER = 100;

    private TextView mResult;
    
    private StringBuilder mPicturePath = new StringBuilder();
    @Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_imagepicker);
		initTitleBar(TAG);
		mResult = (TextView) findViewById(R.id.result);
	}

    public void pickImage(View view) {
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//返回多张照片
            if (data != null) {
                //是否发送原图
                boolean isOrig = data.getBooleanExtra(ImagePreviewActivity.ISORIGIN, false);
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                showResult(isOrig, images);
            }
        }
    }

    /**
     * 显示图片选择的结果
     * @param isOrig
     * @param images
     */
    private void showResult(boolean isOrig, ArrayList<ImageItem> images) {
    	mPicturePath.delete(0, mPicturePath.length());
    	mPicturePath.append("是否发送原图：");
		mPicturePath.append(isOrig ? "发原图" : "不发原图");
		mPicturePath.append("\n");
		mPicturePath.append("图片路径：");
		mPicturePath.append("\n");
		for (int i = 0; i < images.size(); i++) {
			mPicturePath.append("图片" + (i + 1) + "路径：");
			mPicturePath.append(images.get(i).path);
			mPicturePath.append("\n");
		}
		mResult.setText(mPicturePath);
    }
    
    
	
}
