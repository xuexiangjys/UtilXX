package com.example.testutil.net;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.net.model.HttpConsts;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.app.CacheTools;
import com.xuexiang.util.app.UpdateManager;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.log.LogUtils;
import com.xuexiang.util.net.downloadfile.FileDownloadThread;
import com.xuexiang.util.net.downloadfile.FileDownloadThread.DownLoadFinishedListener;
import com.xuexiang.util.net.uploadfile.FileUpload;
import com.xuexiang.util.net.uploadfile.HttpClientUtil.ProgressListener;
import com.xuexiang.util.view.DialogUtil;
import com.xuexiang.view.dialog.HoriztalProgressBarDialog;

public class FileUpLoadAndDownLoadActivity extends BaseActivity implements OnClickListener{
	private EditText path;						//文件路径
	private ProgressBar uploadProgress;			//进度条
	private TextView progress;					//进度
	private Button upload;			
	
	private final int SELECT_FILE = 1;
	
	private HoriztalProgressBarDialog mHoriztalProgressBarDialog;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_upload_download_file);
		initTitleBar(TAG);
		initview();
	}

	private void initview() {
		path = (EditText) findViewById(R.id.filePath);
		upload = (Button) findViewById(R.id.uploadfile);
		uploadProgress = (ProgressBar) findViewById(R.id.uploadProgress);
		progress = (TextView) findViewById(R.id.progress);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.selectfile:
			selectFile();
			break;
        case R.id.uploadfile:
        	upload();
			break;
        case R.id.apkupdate:
        	showNewAppInfo();
			break;
        case R.id.downloadfile:
        	downLoadFile();
			break;
		
		default:
			break;
		}
	}
	
	private void selectFile(){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
	    intent.setType("*/*"); 
	    intent.addCategory(Intent.CATEGORY_OPENABLE);
	    try {
	        startActivityForResult( Intent.createChooser(intent, "选择上传文件"), SELECT_FILE);
	    } catch (android.content.ActivityNotFoundException ex) {
	       
	    }
	}
	
	
	/**
	 * Activity回调方法
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == SELECT_FILE && resultCode == RESULT_OK){
			handlerActivityResult(data);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void upload(){
		String filePath = path.getText().toString().trim();
		if(filePath.length() == 0 ){
			Toast("Please select a file");
			return;
		}
				
		List<File> list = new ArrayList<File>();
		String[] paths = path.getText().toString().split(",");		//根据逗号拆分文件的路径
		for(int i = 0; i < paths.length; i++){
			File file = FileUtils.createNewFile(paths[i]);
			if (file != null) {
				list.add(new File(paths[i]));		//创建File
			}								
		}
//		new UpLoadAsyncTask().execute(list);							//无参数上传文件
		
	/**************************************有参数上传文件******************************************/
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			params.put("file" + i, list.get(i));
		}
		params.put("content", "这是上传的参数:这是HttpClient上传的文件");
		new MyUpLoadAsyncTask().execute(params);	
		
	}
	
	
	/**
	 * 处理返回的文件
	 * @param data
	 */
	private void handlerActivityResult(Intent intent){
		String imagePath = getAbsolutePath(intent.getData());
		path.append(imagePath + ",");
	}
	
	// 取到绝对路径
	protected String getAbsolutePath(Uri uri) {
		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, proj, null, null, null); 
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
	
	
	/**
	 * 无参数文件上传
	 * @author Tercel
	 *
	 */
	private class UpLoadAsyncTask extends AsyncTask<List<File>, Long, String>{		
        @Override  
        protected void onPreExecute() {  
        	upload.setClickable(false);
        	Toast("开始上传");
        }
        
        
		@Override
		protected String doInBackground(List<File>... params) {
			ProgressListener listener = new ProgressListener() {	//上传进度监听器			
				@Override
				public void cumulative(long num) {
					ShowLog("上传量" + String.valueOf(num));		//上传量
				}
				
				@Override
				public void progress(int progress) {
					ShowLog("上传进度" + String.valueOf(progress));
					publishProgress((long)progress);			//进度
				}
			};
			
			try {				
				return FileUpload.post(HttpConsts.UPLPAD_URL, params[0], listener);
			} catch (Exception e) {				
				e.printStackTrace();
				//异常自己处理
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Long... values) {
			uploadProgress.setProgress((int)(long)values[0]);
			progress.setText(values[0] + "%");
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			upload.setClickable(true);
			Toast(result);
		}
	}
	
	
	/**
	 * 有参数文件上传
	 * @author Tercel
	 *
	 */
	private class MyUpLoadAsyncTask extends AsyncTask<Map<String, Object>, Long, String>{		
        @Override  
        protected void onPreExecute() {  
        	upload.setClickable(false);
        	Toast("开始上传");
        }
        
        
		@Override
		protected String doInBackground(Map<String, Object>... params) {
			ProgressListener listener = new ProgressListener() {	//上传进度监听器			
				@Override
				public void cumulative(long num) {
					ShowLog("上传量" + String.valueOf(num));		//上传量
				}
				
				@Override
				public void progress(int progress) {
					ShowLog("上传进度" + String.valueOf(progress));
					publishProgress((long)progress);			//进度
				}
			};
			
			try {				
				return FileUpload.post(HttpConsts.UPLPAD_URL, params[0], listener);
			} catch (Exception e) {				
				e.printStackTrace();
				//异常自己处理
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Long... values) {
			uploadProgress.setProgress((int)(long)values[0]);
			progress.setText(values[0] + "%");
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			upload.setClickable(true);
			Toast(result);
		}
	}
	
	
	/**
	 * 版本更新
	 */
	private void showNewAppInfo() {
		final String updatemsg = "最新版本为1.3, 是否需要更新？\n更新内容如下：增加了维修模块、外卖模块、上门服务模块。";
		DialogUtil.showDialog(mContext,"发现新版本！",
				updatemsg,
				getString(R.string.system_sure),
				getString(R.string.system_cancel) , 
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						UpdateManager mUpdateManager = new UpdateManager(mContext, "helper.apk", updatemsg, HttpConsts.APK_UPDATE_URL);
						mUpdateManager.showDownloadDialog();
					}
				}, null, true);	
	}	
	
    /**
     * 下载文件
     */
    private void downLoadFile() {
    	mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this, "正在下载文件：helper.apk");
		mHoriztalProgressBarDialog.show();
		FileDownloadThread download = new FileDownloadThread (HttpConsts.APK_UPDATE_URL, "helper.apk", new ProgressListener(){
			@Override
			public void cumulative(long arg0) {
				LogUtils.d("已下载文件大小：" + arg0);
			}

			@Override
			public void progress(int progress) {
				mHoriztalProgressBarDialog.setProgress(progress);
			}}, new DownLoadFinishedListener(){

			@Override
			public void onFinish(long fileS) {
				Toast("已下载完毕，文件大小：" + CacheTools.formatFileSize(fileS));
				mHoriztalProgressBarDialog.dismiss();
			}});		
		download.start();
	}


}
