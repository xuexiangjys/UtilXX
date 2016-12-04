package com.example.testutil.net;


import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.ProgressListener;
import com.android.volley.toolbox.Volley;
import com.example.testutil.R;
import com.example.testutil.net.model.HttpConsts;
import com.example.testutil.net.model.HttpConsts.User;
import com.example.testutil.net.model.HttpConsts.VisitService;
import com.example.testutil.net.model.HttpConsts.VisitType;
import com.example.testutil.net.model.Student;
import com.example.testutil.net.model.UserInfo;
import com.example.testutil.net.model.VisitInfo;
import com.google.gson.reflect.TypeToken;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.app.SampleApplication;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.net.JsonUtil;
import com.xuexiang.util.net.NetImageUtil;
import com.xuexiang.util.net.volleyhttp.HttpClientRequest;
import com.xuexiang.util.net.volleyhttp.MyJsonObjectRequest;
import com.xuexiang.util.net.volleyhttp.VolleyHttpManager;
import com.xuexiang.util.net.volleyhttp.uploadfile.MultiPartStack;
import com.xuexiang.util.net.volleyhttp.uploadfile.MultiPartStringRequest;
import com.xuexiang.view.dialog.CircularProgressDialog;

public class VolleyHttpActivity extends BaseActivity implements OnClickListener{
	private HttpClientRequest mHttpClientRequest;
	private StringBuilder LogText = new StringBuilder("");
	private TextView content;
    private ImageView imageView1;
    private NetworkImageView network_image_view;
    
//    private HoriztalProgressBarDialog mHoriztalProgressBarDialog;
    private AlertDialog mAlertDialog;
	private EditText path;						//文件路径
	private final int SELECT_FILE = 1;
	private RequestQueue mSingleQueue;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_volleyhttp);
		initTitleBar(TAG);
		initView();
	}
	private void initView() {
		content = (TextView) findViewById(R.id.content);
		imageView1=(ImageView)findViewById(R.id.imageView1);
		network_image_view=(NetworkImageView)findViewById(R.id.network_image_view);
		mHttpClientRequest = HttpClientRequest.getInstance(this);
		path = (EditText) findViewById(R.id.filePath);
		mSingleQueue = Volley.newRequestQueue(this, new MultiPartStack());
	}

/*******************************************自定义request，返回一个对象****************************************************************************************************************************/	
	
	private void classRequsetPost(){
		UserInfo userinfo = new UserInfo();
        userinfo.setLoginname("xuexiang");
        userinfo.setPassword("xuexiang");
        String userinfoJson = JsonUtil.toJson(userinfo);
        
        VolleyHttpManager<UserInfo> httpManager = new VolleyHttpManager<UserInfo>(User.LOGINCHECK, userinfoJson, UserInfo.class);
        httpManager.httpClassRequest(Method.POST, HttpConsts.app_user_url, new Listener<UserInfo>(){

			@Override
			public void onResponse(UserInfo response) {
				String Json=JsonUtil.toJson(response);
				Toast(Json);
				Log.e("xx", Json);
			}}, new Response.ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
		}});		
	}

	private void classRequsetGet() {
        
//		ClassListRequest<Student> request = new ClassListRequest.RequestBuilder<Student>()
//				                            .url(HttpConsts.student_url)
//				                            .successListener(new Listener<List<Student>>(){
//
//												@Override
//												public void onResponse(List<Student> response) {
//													String Json = JsonUtil.toJson(response);
//													Toast(Json);
//												}})
//				                            .errorListener(new ErrorListener() {
//
//												@Override
//												public void onErrorResponse(VolleyError error) {
//													addLog(error.getMessage());
//												}
//											})
//				                            .build();
//		
//		mHttpClientRequest.getRequestQueue().add(request);
		mHttpClientRequest.executeClassListRequest(HttpConsts.student_url, new Listener<List<Student>>(){

			@Override
			public void onResponse(List<Student> response) {
				String Json = JsonUtil.toJson(response);
				Toast(Json);
			}}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
			}});
	
	}
	
/*******************************************自定义request，返回一个string****************************************************************************************************************************/		
    private void stringRequsetPost() {    
    	VisitInfo visitInfo = new VisitInfo();
		visitInfo.setPageNum(0);
		visitInfo.setVisittype(VisitType.PRIVATE_TEACHER);
		String Json = JsonUtil.toJson(visitInfo);
		
//		VolleyHttpManager<VisitInfo> httpManager = new VolleyHttpManager<VisitInfo>(VisitService.GET_TYPE_VISITINFO_BY_PAGE, Json, VisitInfo.class);
//        httpManager.httpStringRequest(Method.POST, HttpConsts.app_visitservice_url, new Listener<String>(){
//			@Override
//			public void onResponse(String response) {
//				 Toast(response);
//				 Log.e("xx", response);
//			}}, new Response.ErrorListener(){
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				addLog(error.getMessage());
//		}});
        
        mHttpClientRequest.executeStringRequest(HttpConsts.app_visitservice_url, VisitService.GET_TYPE_VISITINFO_BY_PAGE, Json, new Listener<String>(){
			@Override
			public void onResponse(String response) {
				 Toast(response);
				 Log.e("xx", response);
			}}, new Response.ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
		}});
	}
	
    private void stringRequsetGet() {       
        VolleyHttpManager<Student> httpManager = new VolleyHttpManager<Student>(Student.class);
        httpManager.httpStringRequest(Method.GET, HttpConsts.student_url, new Listener<String>(){
			@Override
			public void onResponse(String response) {
				 Toast(response);
				 Log.e("xx", response);
			}}, new Response.ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
		}});		
	}
	
/*******************************************自定义request，返回一个JSONObject****************************************************************************************************************************/			
   
    private void JSONObjectPost() {       
    	UserInfo userinfo = new UserInfo();
        userinfo.setLoginname("xuexiang");
        userinfo.setPassword("xuexiang");
        String userinfoJson = JsonUtil.toJson(userinfo);        
        
//        VolleyHttpManager<UserInfo> httpManager = new VolleyHttpManager<UserInfo>(User.LOGINCHECK, userinfoJson, UserInfo.class);
//        httpManager.httpJsonObjectRequest(Method.POST, HttpConsts.app_user_url, new Listener<JSONObject>(){
//
//			@Override
//			public void onResponse(JSONObject response) {				
//				String Json = null;
//				try {
//					Json = response.getString(MyJsonObjectRequest.JSONObjectData);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				Toast(Json);
//				Log.e("xx", Json);
//			}}, new Response.ErrorListener(){
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				addLog(error.getMessage());
//		}});	
        
        mHttpClientRequest.executeJsonObjectRequest(HttpConsts.app_user_url, User.LOGINCHECK, userinfoJson,  new Listener<JSONObject>(){
			@Override
			public void onResponse(JSONObject response) {				
				String Json = null;
				try {
					Json = response.getString(MyJsonObjectRequest.JSONObjectData);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(Json);
				Log.e("xx", Json);
			}}, new Response.ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
		}});		
    }
    
    
    
    private void JSONObjectGet() {       
    	 VolleyHttpManager<Student> httpManager = new VolleyHttpManager<Student>(Student.class);
         httpManager.httpJsonObjectRequest(Method.GET, HttpConsts.student_url, new Listener<JSONObject>(){
 			@Override
 			public void onResponse(JSONObject response) {
				List<Student> dataList = new ArrayList<Student>();
				JSONArray array = null;
				try {
					array = new JSONArray(response.getString(MyJsonObjectRequest.JSONObjectData));				
				    for(int i=0; i< array.length(); i++){
						JSONObject object = (JSONObject) array.get(i);
						Student student = JsonUtil.fromRequest(object.toString(), Student.class);
						dataList.add(student);
				    }
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(JsonUtil.toJson(dataList));
				for (Student student : dataList) {
					Log.e("xx", student.getSname());
				}
				
 			}}, new Response.ErrorListener(){

 			@Override
 			public void onErrorResponse(VolleyError error) {
 				addLog(error.getMessage());
 		}});		
    }
    

    private void JSONObjectGet2() {       
   	 VolleyHttpManager<Student> httpManager = new VolleyHttpManager<Student>(Student.class);
        httpManager.httpJsonObjectRequest(Method.GET, HttpConsts.student_url, new Listener<JSONObject>(){
			@Override
			public void onResponse(JSONObject response) {
				List<Student> dataList = new ArrayList<Student>();
				try {
					String Json = response.getString(MyJsonObjectRequest.JSONObjectData);
					Type listType = new TypeToken<List<Student>>(){}.getType(); 
					dataList = JsonUtil.fromRequest(Json, listType);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(JsonUtil.toJson(dataList));
				for (Student student : dataList) {
					Log.e("xx", student.getSname());
				}
				
			}}, new Response.ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
		}});		
   }
    
    private void getWeatherData() {
    	mHttpClientRequest.executeJsonObjectRequest(HttpConsts.TEST_URL, new Listener<JSONObject>(){

			@Override
			public void onResponse(JSONObject response) {
				String Json = null;
				try {
					Json = response.getString(MyJsonObjectRequest.JSONObjectData);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(Json);
			}}, new ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
			}
		});
    }
 
    private void getCustomRequestData() {
    	UserInfo userinfo = new UserInfo();
        userinfo.setLoginname("xuexiang");
        userinfo.setPassword("xuexiang");
        String userinfoJson = JsonUtil.toJson(userinfo);
        
        mHttpClientRequest.executeClassRequest(UserInfo.class, HttpConsts.app_user_url, User.LOGINCHECK, userinfoJson, new Listener<UserInfo>(){
			@Override
			public void onResponse(UserInfo response) {
				String Json=JsonUtil.toJson(response);
				Toast(Json);
				Log.e("xx", Json);
			}}, new Response.ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				addLog(error.getMessage());
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
		if (filePath != null) {
   	   		ShowLog(filePath);
   		}
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
				files.put("file" + i, file);	
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
//        mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this, "多文件上传中...");
//		mHoriztalProgressBarDialog.show();
//		
//		MultiPartUploadFileRequest(HttpConsts.UPLPAD_URL, files, null, new ProgressListener(){
//
//			@Override
//			public void onProgress(long transferredBytes, long totalSize) {
//				ShowLog("transferredBytes：" + transferredBytes);
//				mHoriztalProgressBarDialog.setProgress((int) ((transferredBytes / (float) totalSize) * 100));
//			}}, new Listener<String>(){
//
//			@Override
//			public void onResponse(String response) {
//				mHoriztalProgressBarDialog.dismiss();
//            	addLog("文件上传完毕！返回结果:" + response);
//			}}, new ErrorListener(){
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				addLog(error.getMessage());
//			}});
//        
        mAlertDialog = new CircularProgressDialog(this, "正在上传文件！");
	    mAlertDialog.show();
	    
	    MultiPartUploadFileRequest(HttpConsts.UPLPAD_URL, files, null, null, new Listener<String>(){

			@Override
			public void onResponse(String response) {
				mAlertDialog.dismiss();
            	addLog("文件上传完毕！返回结果:" + response);
			}}, new ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				mAlertDialog.dismiss();
				addLog(error.getMessage());
			}});
	    
	    
    }
	
	private void MultiPartUploadFileRequest(String url, Map<String, File> files, Map<String, String> params, ProgressListener progressListener, Listener<String> responseListener, ErrorListener errorListener) {
		MultiPartStringRequest multiPartRequest = new MultiPartStringRequest.RequestBuilder()
		                                       .post()
		                                       .url(url)
		                                       .Files(files)
		                                       .params(params)
		                                       .progressListener(progressListener)
		                                       .successListener(responseListener)
		                                       .errorListener(errorListener)
		                                       .build();		                                       
		mSingleQueue.add(multiPartRequest);
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
        case R.id.btn_customrequest:
        	getCustomRequestData();
			break;
        case R.id.VolleyImageView:
        	NetImageUtil.getImage(HttpConsts.visitservice_pic_bath_url + "image_1456049128874.jpg", imageView1);
			NetImageUtil.getNetImage(HttpConsts.visitservice_pic_bath_url + "image_1456049128874.jpg", network_image_view);			
			break;	
        case R.id.FinalImage:
        	NetImageUtil.getFinalImage(HttpConsts.visitservice_pic_bath_url + "m2w690hq92lt_large_b9zq_5fbf0000b7a2125d.jpg", imageView1);
			break;						
        case R.id.ImageViewTest:
        	NetImageUtil.getFinalImage(HttpConsts.visitservice_pic_bath_url + "m2w690hq92lt_large_bGik_2b0b00002ef71190.jpg", imageView1);
			NetImageUtil.getNetImage(HttpConsts.visitservice_pic_bath_url + "m2w690hq92lt_large_bGik_2b0b00002ef71190.jpg", network_image_view);	
			break;	
        case R.id.selectfile:
			selectFile();
			break;
        case R.id.uploadfile:
        	upload();
			break;
		default:
			break;
		}
	}	
	
    private void addLog(CharSequence mTitle) {
		 LogText.append(mTitle + "\n");
		 content.setText(LogText);
    }
		
	@Override
	protected void onStop() {
		SampleApplication.getVolleyRequestQueue().cancelAll("strReqGet");
		super.onStop();
	}
}
