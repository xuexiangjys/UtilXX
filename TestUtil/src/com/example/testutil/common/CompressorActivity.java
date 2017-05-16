package com.example.testutil.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.compressor.Compressor;
import com.xuexiang.util.compressor.FileUtil;

public class CompressorActivity extends BaseHeadActivity {

	private static final int PICK_IMAGE_REQUEST = 1;

	private ImageView actualImageView;
	private ImageView compressedImageView;
	private TextView actualSizeTextView;
	private TextView compressedSizeTextView;
	private File actualImage;
	private File compressedImage;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_compressor;
	}

	@Override
	protected void init() {
		actualImageView = (ImageView) findViewById(R.id.actual_image);
		compressedImageView = (ImageView) findViewById(R.id.compressed_image);
		actualSizeTextView = (TextView) findViewById(R.id.actual_size);
		compressedSizeTextView = (TextView) findViewById(R.id.compressed_size);

		actualImageView.setBackgroundColor(getRandomColor());
		clearImage();
	}

	public void chooseImage(View view) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, PICK_IMAGE_REQUEST);
	}

	public void compressImage(View view) {
		if (actualImage == null) {
			showError("Please choose an image!");
		} else {

			// Compress image in main thread
			// compressedImage =
			// Compressor.getDefault(this).compressToFile(actualImage);
			// setCompressedImage();

			// Compress image to bitmap in main thread
			/*
			 * compressedImageView.setImageBitmap(Compressor.getDefault(this).
			 * compressToBitmap(actualImage));
			 */

			// Compress image using RxJava in background thread
			Compressor.getDefault(this).compressToFileAsObservable(actualImage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<File>() {
				@Override
				public void call(File file) {
					compressedImage = file;
					setCompressedImage();
				}
			}, new Action1<Throwable>() {
				@Override
				public void call(Throwable throwable) {
					showError(throwable.getMessage());
				}
			});
		}
	}

	public void customCompressImage(View view) {
		if (actualImage == null) {
			showError("Please choose an image!");
		} else {
			// Compress image in main thread using custom Compressor
			compressedImage = new Compressor.Builder(this).setMaxWidth(640).setMaxHeight(480).setQuality(75).setCompressFormat(Bitmap.CompressFormat.WEBP)
					.setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()).build().compressToFile(actualImage);
			setCompressedImage();

			// Compress image using RxJava in background thread with custom
			// Compressor
			/*
			 * new Compressor.Builder(this) .setMaxWidth(640) .setMaxHeight(480)
			 * .setQuality(75) .setCompressFormat(Bitmap.CompressFormat.WEBP)
			 * .setDestinationDirectoryPath
			 * (Environment.getExternalStoragePublicDirectory(
			 * Environment.DIRECTORY_PICTURES).getAbsolutePath()) .build()
			 * .compressToFileAsObservable(actualImage)
			 * .subscribeOn(Schedulers.io())
			 * .observeOn(AndroidSchedulers.mainThread()) .subscribe(new
			 * Action1<File>() {
			 * 
			 * @Override public void call(File file) { compressedImage = file;
			 * setCompressedImage(); } }, new Action1<Throwable>() {
			 * 
			 * @Override public void call(Throwable throwable) {
			 * showError(throwable.getMessage()); } });
			 */
		}
	}

	private void setCompressedImage() {
		compressedImageView.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));
		compressedSizeTextView.setText(String.format("Size : %s", getReadableFileSize(compressedImage.length())));

		Toast.makeText(this, "Compressed image save in " + compressedImage.getPath(), Toast.LENGTH_LONG).show();
		Log.d("Compressor", "Compressed image save in " + compressedImage.getPath());
	}

	private void clearImage() {
		actualImageView.setBackgroundColor(getRandomColor());
		compressedImageView.setImageDrawable(null);
		compressedImageView.setBackgroundColor(getRandomColor());
		compressedSizeTextView.setText("Size : -");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
			if (data == null) {
				showError("Failed to open picture!");
				return;
			}
			try {
				actualImage = FileUtil.from(this, data.getData());
				actualImageView.setImageBitmap(BitmapFactory.decodeFile(actualImage.getAbsolutePath()));
				actualSizeTextView.setText(String.format("Size : %s", getReadableFileSize(actualImage.length())));
				clearImage();
			} catch (IOException e) {
				showError("Failed to read picture data!");
				e.printStackTrace();
			}
		}
	}

	public void showError(String errorMessage) {
		Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
	}

	private int getRandomColor() {
		Random rand = new Random();
		return Color.argb(100, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}

	public String getReadableFileSize(long size) {
		if (size <= 0) {
			return "0";
		}
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

}
