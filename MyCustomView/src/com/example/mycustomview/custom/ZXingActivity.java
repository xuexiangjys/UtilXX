package com.example.mycustomview.custom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.zxing.activity.CaptureActivity;
import com.xuexiang.view.zxing.encoding.EncodingUtils;

public class ZXingActivity extends BaseHeadActivity {

	private TextView resultTextView;
	private EditText qrStrEditText;
	private ImageView qrImgImageView;
	private CheckBox mCheckBox;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_zxing;
	}

	@Override
	protected void init() {
		resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
		qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
		qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
		mCheckBox = (CheckBox) findViewById(R.id.logo);

		Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
		scanBarCodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 打开扫描界面扫描条形码或二维码
				Intent openCameraIntent = new Intent(ZXingActivity.this, CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});

		Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
		generateQRCodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String contentString = qrStrEditText.getText().toString();
				if (!contentString.equals("")) {
					// 根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
					Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350, mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher) : null);
					qrImgImageView.setImageBitmap(qrCodeBitmap);
				} else {
					Toast.makeText(ZXingActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);
		}
	}

}