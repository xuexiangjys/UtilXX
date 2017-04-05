package com.example.mycustomview.custom;

import java.io.File;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.signature.SignatureDialog;
import com.xuexiang.view.signature.SignatureDialog.OnSubmitListener;

/**  
 * 创建时间：2017-3-22 下午10:51:35
 * 项目名称：MyCustomView  
 * @author xuexiang
 * 文件名称：SigntureActivity.java 
 **/
public class SignatureActivity extends BaseHeadActivity implements OnSubmitListener {
	private SignatureDialog mSignatureDialog;
	private ImageView mImageView;
	private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Signature" + File.separator + "test.png";
	@Override
	protected int getLayoutId() {
		return R.layout.activity_signature;
	}

	@Override
	protected void init() {
		mSignatureDialog = new SignatureDialog(this);
		mSignatureDialog.setOnSubmitListener(this);
		mSignatureDialog.setSavePath(path);
		findViewById(R.id.signature).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSignatureDialog.show();
			}
		});
		mImageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onSubmit(Bitmap bitmap, String path) {
		if (bitmap != null) {
			mImageView.setImageBitmap(bitmap);
		}
	}
	
	@Override
	protected void onDestroy() {
		if (mSignatureDialog != null) {
			mSignatureDialog.recycle();
			mSignatureDialog = null;
		}
		super.onDestroy();
	}
}
