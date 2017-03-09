package com.example.testutil.net;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.CookieJar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.net.model.HttpConsts;
import com.example.testutil.net.model.HttpConsts.User;
import com.example.testutil.net.model.HttpConsts.VisitService;
import com.example.testutil.net.model.HttpConsts.VisitType;
import com.example.testutil.net.model.Student;
import com.example.testutil.net.model.UserInfo;
import com.example.testutil.net.model.VisitInfo;
import com.google.gson.reflect.TypeToken;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.net.JsonUtil;
import com.xuexiang.util.net.okhttp.OkHttpUtils;
import com.xuexiang.util.net.okhttp.callback.BitmapCallback;
import com.xuexiang.util.net.okhttp.callback.ClassCallBack;
import com.xuexiang.util.net.okhttp.callback.ClassListCallBack;
import com.xuexiang.util.net.okhttp.callback.FileCallBack;
import com.xuexiang.util.net.okhttp.callback.JSONObjectCallBack;
import com.xuexiang.util.net.okhttp.callback.StringCallback;
import com.xuexiang.util.net.okhttp.cookie.CookieJarImpl;
import com.xuexiang.view.dialog.HoriztalProgressBarDialog;

public class OkHttpActivity extends BaseActivity implements OnClickListener{
	private StringBuilder LogText = new StringBuilder("");
	private TextView content;
	private ImageView mImageView;
	private HoriztalProgressBarDialog mHoriztalProgressBarDialog;
	private EditText path;						//文件路径
	private final int SELECT_FILE = 1;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_okhttp);
		initTitleBar(TAG);
		initView();
	}
	
	private void initView() {
		content = (TextView) findViewById(R.id.content);
		mImageView = (ImageView) findViewById(R.id.id_imageview);
		path = (EditText) findViewById(R.id.filePath);
	}
	
/*******************************************自定义request，返回一个对象****************************************************************************************************************************/	
	
	private void classRequsetPost(){
		UserInfo userinfo = new UserInfo();
	    userinfo.setLoginname("xuexiang");
	    userinfo.setPassword("xuexiang");
	    String userinfoJson = JsonUtil.toJson(userinfo);
	    
	    OkHttpUtils.post()
	               .url(HttpConsts.app_user_url)
	               .addParams("mode", User.LOGINCHECK)
	               .addParams("Json", userinfoJson)
	               .build()
	               .execute(new ClassCallBack<UserInfo>(UserInfo.class){

					@Override
					public void onError(Call arg0, Exception error, int id) {
						addLog(error.getMessage());
					}

					@Override
					public void onResponse(UserInfo response, int id) {
						String Json = JsonUtil.toJson(response);
						Toast(Json);
					}});
	}

	private void classRequsetGet() {
		OkHttpUtils.get()
		           .url(HttpConsts.student_url)
		           .build()
		           .execute(new ClassListCallBack<Student>(){
					@Override
					public void onError(Call arg0, Exception error, int arg2) {
						addLog(error.getMessage());
					}
					@Override
					public void onResponse(List<Student> response, int arg1) {
						String Json = JsonUtil.toJson(response);
						Toast(Json);
					}});
	}
	
/*******************************************自定义request，返回一个string****************************************************************************************************************************/		
    private void stringRequsetPost() {    
    	VisitInfo visitInfo = new VisitInfo();
		visitInfo.setPageNum(0);
		visitInfo.setVisittype(VisitType.PRIVATE_TEACHER);
		String Json = JsonUtil.toJson(visitInfo);
		
		OkHttpUtils.post()
			       .url(HttpConsts.app_visitservice_url)
			       .addParams("mode", VisitService.GET_TYPE_VISITINFO_BY_PAGE)
	               .addParams("Json", Json)
			       .build()
			       .execute(new StringCallback(){
					@Override
					public void onError(Call arg0, Exception error, int id) {
						addLog(error.getMessage());
					}
					@Override
					public void onResponse(String response, int id) {
						Toast(response);
					}});
			
	}
	
    private void stringRequsetGet() {       
    	OkHttpUtils.get()
			       .url(HttpConsts.student_url)
			       .build()
			       .execute(new StringCallback(){
					@Override
					public void onError(Call arg0, Exception error, int arg2) {
						addLog(error.getMessage());
					}
					@Override
					public void onResponse(String response, int arg1) {
						Toast(response);
					}});
	}
	
/*******************************************自定义request，返回一个JSONObject****************************************************************************************************************************/			
   
    private void JSONObjectPost() {    
    	UserInfo userinfo = new UserInfo();
        userinfo.setLoginname("xuexiang");
        userinfo.setPassword("xuexiang");
        String userinfoJson = JsonUtil.toJson(userinfo); 
        
        OkHttpUtils.post()
                   .url(HttpConsts.app_user_url)
                   .addParams("mode", User.LOGINCHECK)
	               .addParams("Json", userinfoJson)
	               .build()
	               .execute(new JSONObjectCallBack() {
					
					@Override
					public void onResponse(JSONObject response, int arg1) {
						String Json = null;
						try {
							Json = response.getString(JSONObjectCallBack.JSONObjectData);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Toast(Json);
					}
					
					@Override
					public void onError(Call arg0, Exception error, int arg2) {
						addLog(error.getMessage());
					}
				});
    }
    
    
    
    private void JSONObjectGet() {       
    	OkHttpUtils.get()
			       .url(HttpConsts.student_url)
			       .build()
			       .execute(new JSONObjectCallBack() {
					
					@Override
					public void onResponse(JSONObject response, int arg1) {
						List<Student> dataList = new ArrayList<Student>();
						JSONArray array = null;
						try {
							array = new JSONArray(response.getString(JSONObjectCallBack.JSONObjectData));				
						    for(int i=0; i< array.length(); i++){
								JSONObject object = (JSONObject) array.get(i);
								Student student = JsonUtil.fromRequest(object.toString(), Student.class);
								dataList.add(student);
						    }
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Toast(JsonUtil.toJson(dataList));
					}
					
					@Override
					public void onError(Call arg0, Exception error, int arg2) {
						addLog(error.getMessage());			
					}
				});
    }
    

    private void JSONObjectGet2() {   
    	OkHttpUtils.get()
			       .url(HttpConsts.student_url)
			       .build()
			       .execute(new JSONObjectCallBack() {
					
					@Override
					public void onResponse(JSONObject response, int arg1) {
						List<Student> dataList = new ArrayList<Student>();
						try {
							String Json = response.getString(JSONObjectCallBack.JSONObjectData);
							Type listType = new TypeToken<List<Student>>(){}.getType(); 
							dataList = JsonUtil.fromRequest(Json, listType);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Toast(JsonUtil.toJson(dataList));
					}
					
					@Override
					public void onError(Call arg0, Exception error, int arg2) {
						addLog(error.getMessage());				
					}
				});
    }
    
    private void getWeatherData() {
    	OkHttpUtils.get()
	               .url(HttpConsts.TEST_URL)
	               .build()
	               .execute(new JSONObjectCallBack(){

					@Override
					public void onError(Call arg0, Exception error, int arg2) {
						addLog(error.getMessage());
					}

					@Override
					public void onResponse(JSONObject response, int arg1) {
						String Json = null;
						try {
							Json = response.getString(JSONObjectCallBack.JSONObjectData);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Toast(Json);
					}});
    }
 

    /**
     * 图片加载
     */
    private void getNetImage() {
    	 String url = "http://images.csdn.net/20150817/1.jpg";
         OkHttpUtils
                 .get()//
                 .url(url)//
                 .tag(this)//
                 .build()//
                 .connTimeOut(20000)
                 .readTimeOut(20000)
                 .writeTimeOut(20000)
                 .execute(new BitmapCallback() {
                     @Override
                     public void onError(Call call, Exception error, int id) {
                    	 addLog(error.getMessage());
                     }

                     @Override
                     public void onResponse(Bitmap bitmap, int id) {
                         mImageView.setImageBitmap(bitmap);
                     }
                 });
    }

    /**
     * 文件下载
     */
    private void downLoadFile() {
    	mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this, "正在下载文件：helper.apk");
		mHoriztalProgressBarDialog.show();
        OkHttpUtils
               .get()
               .url(HttpConsts.APK_UPDATE_URL)
               .build()
               .execute(new FileCallBack(LocalFileUtil.DOWNLOAD_PATH, "helper.apk") {

                   @Override
                   public void inProgress(float progress, long total, int id) {
                	   mHoriztalProgressBarDialog.setProgress((int) (100 * progress));
                   }

                   @Override
                   public void onError(Call call, Exception error, int id) {
                	   addLog(error.getMessage());
                   }

                   @Override
                   public void onResponse(File file, int id) {
                	   mHoriztalProgressBarDialog.dismiss();
                	   addLog("文件下载完毕！文件路径 :" + file.getAbsolutePath());
                   }
               });
  	}
    
    /**
     * 单文件上传
     */
    private void singleUpLoadFile() {
    	 File file = new File(LocalFileUtil.DOWNLOAD_PATH, "helper.apk");
         if (!file.exists()) {
             Toast("文件不存在，请修改文件路径");
             return;
         }
         mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this, "正在上传文件：helper.apk");
 		 mHoriztalProgressBarDialog.show();
    	 OkHttpUtils.post()
			        .addFile("file", "helper.apk", file)
			        .url(HttpConsts.UPLPAD_URL)
			        .build()
			        .execute(new StringCallback(){
		        	    @Override
	                    public void inProgress(float progress, long total, int id) {
	                	    mHoriztalProgressBarDialog.setProgress((int) (100 * progress));
	                    }	
		        	    
		        	    @Override
		                public void onError(Call call, Exception error, int id) {
		        	    	mHoriztalProgressBarDialog.dismiss();
		                	addLog(error.getMessage());
		                }
		        	    
						@Override
						public void onResponse(String response, int arg1) {
							mHoriztalProgressBarDialog.dismiss();
		                	addLog("文件上传完毕！返回结果:" + response);
						}});
	}
    
    
/*******************************************多文件上传****************************************************************************************************************************/			
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
	
	/**
	 * 处理返回的文件
	 * @param data
	 */
	private void handlerActivityResult(Intent intent){
		String filePath = getAbsolutePath(intent.getData());
		ShowLog(filePath);
		path.append(filePath + ",");
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
	
	
	private void upload(){
		String filePath = path.getText().toString().trim();
		if(filePath.length() == 0 ){
			Toast("Please select a file");
			return;
		}
				
		Map<String, File> files = new HashMap<String, File>();
		String[] paths = path.getText().toString().split(",");		//根据逗号拆分文件的路径
		for (int i = 0; i < paths.length; i++) {
			File file = FileUtils.createNewFile(paths[i]);
			if (file != null) {
				files.put(file.getName(), file);	
			}			
		}
		
		multiFileUpload(files);
	}
	
	/**
	 * 多文件上传
	 */
	public void multiFileUpload(Map<String, File> files) {
		Iterator<Map.Entry<String, File>> it = files.entrySet().iterator();
		   while (it.hasNext()) {
			   Map.Entry<String, File> entry = it.next();
			   File file = entry.getValue();
			   if (!file.exists()) {
				   files.remove(file);
			   }
		   }
        if (files.isEmpty()) {
            Toast("不存在有效文件，请修改文件路径");
            return;
        }
        mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this, "多文件上传中...");
		mHoriztalProgressBarDialog.show();
		Map<String, String> param = new HashMap<String, String>();
		param.put("content", "这是上传的参数:这是okhttp上传的文件");
        OkHttpUtils.post()
                .files("file", files)
                .url(HttpConsts.UPLPAD_URL)
                .params(param)
                .build()//
                .execute(new StringCallback(){
	        	    @Override
                    public void inProgress(float progress, long total, int id) {
                	    mHoriztalProgressBarDialog.setProgress((int) (100 * progress));
                    }	
	        	    
	        	    @Override
	                public void onError(Call call, Exception error, int id) {
	        	    	mHoriztalProgressBarDialog.dismiss();
	                	addLog(error.getMessage());
	                }
	        	    
					@Override
					public void onResponse(String response, int arg1) {
						mHoriztalProgressBarDialog.dismiss();
	                	addLog("文件上传完毕！返回结果:" + response);
					}});
    }
	
    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_classget:
			classRequsetGet();
			break;
        case R.id.btn_classpost:
        	classRequsetPost();
			break;
        case R.id.btn_stringget:
        	stringRequsetGet();
			break;
        case R.id.btn_stringpost:
        	stringRequsetPost();
			break;
        case R.id.btn_JSONObjectget:
        	//JSONObjectGet();
			JSONObjectGet2();
			break;
        case R.id.btn_JSONObjectpost:
        	JSONObjectPost();
			break;
        case R.id.btn_getWeatherData:
        	getWeatherData();
			break;
        case R.id.btn_getpicture:
        	getNetImage();
			break;		
        case R.id.btn_downloadfile:
        	downLoadFile();
			break;	
        case R.id.btn_singleuploadfile:
        	singleUpLoadFile();
			break;	
        case R.id.selectfile:
			selectFile();
			break;
        case R.id.uploadfile:
        	upload();
			break;
        case R.id.clearSession:
        	clearSession();
			break;
		default:
			break;
		}
	}	

	private void addLog(CharSequence mTitle) {
		 LogText.append(mTitle + "\n");
		 content.setText(LogText);
    }
	
	private void clearSession() {
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl) {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
