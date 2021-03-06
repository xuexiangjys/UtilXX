/**
 * Copyright 2014 Zhenguo Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.util.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.view.BitmapTools;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * ResourceUtils
 *
 * @author jingle1267@163.com
 */
public final class ResourceUtils {

	 public static final String APK = "apk";
    /**
     * Don't let anyone instantiate this class.
     */
    private ResourceUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 读取assert下的txt文件
     * @param context
     * @param fileName 文件名
     * @return
     */
    public static String readStringFromAssert(Context context, String fileName) {
        String resultString = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString = new String(buffer, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    /**
     * 读取assert下的txt文件
     * @param context
     * @param fileName 文件名
     * @param encodingCode 字符编码
     * @return
     */
    public static String readStringFromAssert(Context context, String fileName, String encodingCode) {
        String resultString = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString = new String(buffer, encodingCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    /**
     * get an asset using ACCESS_STREAMING mode. This provides access to files that have been bundled with an
     * application as assets -- that is, files placed in to the "assets" directory.
     *
     * @param context
     * @param fileName The name of the asset to open. This name can be hierarchical.
     * @return
     */
    public static String getFileFromAssets(Context context, String fileName, boolean isNeedAddLine) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            if(isNeedAddLine) {
              while ((line = br.readLine()) != null) {
            	  s.append(line + "\n");
  	          }
            } else {
	    	  while ((line = br.readLine()) != null) {
	              s.append(line);
	          }
            }
          
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get content from a raw resource. This can only be used with resources whose value is the name of an asset files
     * -- that is, it can be used to open drawable, sound, and raw resources; it will fail on string and color
     * resources.
     *
     * @param context
     * @param resId   The resource identifier to open, as generated by the appt tool.
     * @return
     */
    public static String getFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * same to {@link ResourceUtils#geFileFromAssets(Context, String)}, but return type is List<String>
     *
     * @param context
     * @param fileName
     * @return
     */
    public static List<String> geFileToListFromAssets(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            br.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * same to {@link ResourceUtils#geFileFromRaw(Context, int)}, but return type is List<String>
     *
     * @param context
     * @param resId
     * @return
     */
    public static List<String> geFileToListFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
    * 从Assets中读取图片  
    */  
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {  
       Bitmap image = null;  
       AssetManager am = context.getResources().getAssets();  
       try  {  
           InputStream is = am.open(fileName);  
           image = BitmapFactory.decodeStream(is);  
           is.close();  
       }  catch (IOException e) {  
           e.printStackTrace();  
       }    
       return image;    
   }  
    
    
    /**
     * 从Assets中读取图片  
     */  
     public static Bitmap getImageFromAssets(Context context, String fileName) {  
        Bitmap image = null;  
        AssetManager am = context.getResources().getAssets();  
        try  {  
            InputStream is = am.open(RUtils.DRAWABLE + "/" + fileName);  
            image = BitmapFactory.decodeStream(is);  
            is.close();  
        }  catch (IOException e) {  
            e.printStackTrace();  
        }    
        return image;    
    }  
     
    /**
     * 从Assets中读取图片  
     */  
    public static Drawable getImageDrawableFromAssets(Context context, String fileName) {  
        Drawable drawable = null;
        AssetManager am = context.getResources().getAssets();  
        try  {  
            InputStream is = am.open(RUtils.DRAWABLE + "/" + fileName);  
            drawable = BitmapTools.bitmapTodrawable(BitmapFactory.decodeStream(is));
            is.close();  
        }  catch (IOException e) {  
            e.printStackTrace();  
        }    
        return drawable;    
     }  
    
    /** 
     *  从assets目录中复制整个文件夹内容 
     *  @param  context  Context 使用CopyFiles类的Activity
     *  @param  oldPath  String  原文件路径  如：/aa 
     *  @param  newPath  String  复制后路径  如：xx:/bb/cc 
     */
    public static void copyFilesFromAssets(Context context,String oldPath,String newPath) {                    
           try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                   copyFilesFromAssets(context,oldPath + "/" + fileName,newPath+"/"+fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount=0;               
                while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节        
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * 从Assets中读取apk文件  
     */  
     public static boolean copyAPKFromAssets(Context context, String fileName) {  
    	String apkfilepath =  APK + "/" + fileName;
    	if(!FileUtils.isFolderExist(LocalFileUtil.APK_PATH)) {
    		File file = new File(LocalFileUtil.APK_PATH);
            file.mkdirs();
    	}
    	copyFilesFromAssets(context, apkfilepath, LocalFileUtil.LOCAL_DATA_PATH + apkfilepath);
    	if(FileUtils.isFileExist(LocalFileUtil.LOCAL_DATA_PATH + apkfilepath)) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }  
}
