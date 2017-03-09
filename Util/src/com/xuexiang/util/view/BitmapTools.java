package com.xuexiang.util.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;


public class BitmapTools {
	// 保存 bitmap 到SD卡F
	public static boolean saveBitmapToSDCard(Bitmap bitmap, String filePath,
			String fileName) {
		boolean flag = false;
		if (null != bitmap) {
			try {
				fileName = fileName + ".jpg";
				File file = new File(filePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				File f = new File(filePath + fileName);
				if (f.exists()) {
					f.delete();
				}
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(f));
				bitmap.compress(CompressFormat.JPEG, 100, outputStream);
				outputStream.flush();
				outputStream.close();
				flag = true;
			} catch (FileNotFoundException e) {
				flag = false;
			} catch (IOException e) {
				flag = false;
			}
		}
		return flag;

	}

	/**
	 * 
	 * @param drawable
	 * @return bitmap
	 */
	public static Bitmap drawableToBitmap2(Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable) drawable;
		return bd.getBitmap();
	}

	/**
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmapTodrawable(Bitmap bitmap) {
		Drawable drawable = new BitmapDrawable(bitmap);
		drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
		return drawable;
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * @return 返回指定笔和指定字符串的长度
	 */
	public static float getFontlength(Paint paint, String str) {
		return paint.measureText(str);
	}

	/**
	 * @return 返回指定笔的文字高度
	 */
	public static float getFontHeight(Paint paint) {
		FontMetrics fm = paint.getFontMetrics();
		return fm.descent - fm.ascent;
	}

	/**
	 * @return 返回指定笔离文字顶部的基准距离
	 */
	public static float getFontLeading(Paint paint) {
		FontMetrics fm = paint.getFontMetrics();
		return fm.leading - fm.ascent;
	}

	/**
	 * 获取圆角图片
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * 保存图片到SD卡
	 * 
	 * @param bitmap
	 *            图片的bitmap对象
	 * @return
	 */
	public static String savePhotoToSDCard(Bitmap bitmap) {
		if (!FileUtils.isSdcardExist()) {
			return null;
		}
		FileOutputStream fileOutputStream = null;
		FileUtils.createDirFile(LocalFileUtil.PICTURE_COLLECT_DIR);

		String fileName = UUID.randomUUID().toString() + ".jpg";
		String newFilePath = LocalFileUtil.PICTURE_COLLECT_DIR + fileName;
		File file = FileUtils.createNewFile(newFilePath);
		if (file == null) {
			return null;
		}
		try {
			fileOutputStream = new FileOutputStream(newFilePath);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
		} catch (FileNotFoundException e1) {
			return null;
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.flush();
					fileOutputStream.close();
				}
			} catch (IOException e) {
				return null;
			}
		}
		return newFilePath;
	}
	

	/**
     * 清空图片的内存
     */
	public static void clearImgMemory(ImageView imageView) {
        Drawable d = imageView.getDrawable();
        if (d != null && d instanceof BitmapDrawable) {                        
            Bitmap bmp = ((BitmapDrawable)d).getBitmap();
            bmp.recycle();
            bmp = null;
        }
        imageView.setImageBitmap(null);
        if (d != null) {
            d.setCallback(null);
        }
    }
    

}
