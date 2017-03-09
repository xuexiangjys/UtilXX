package com.example.testutil.net;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
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
import com.xuexiang.util.net.JsonUtil;
import com.xuexiang.util.net.asynchttp.AsyncHttpRequestManager;
import com.xuexiang.util.net.asynchttp.HttpJSONObjectRequest;
import com.xuexiang.util.net.asynchttp.toolbox.HttpError;
import com.xuexiang.util.net.asynchttp.toolbox.HttpRequest;
import com.xuexiang.util.net.asynchttp.toolbox.HttpSuccess;
import com.xuexiang.view.dialog.CircularProgressDialog;

public class AsyncHttpActivity extends BaseActivity implements OnClickListener {
	private AsyncHttpRequestManager mHttpRequest;
	private StringBuilder LogText = new StringBuilder("");
	private TextView content;
	
	private AlertDialog mAlertDialog;
	private EditText path;						//鏂囦欢璺緞
	private final int SELECT_FILE = 1;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_asynchttp);
		initTitleBar(TAG);
		initView();
	}
	
	private void initView() {
    	mHttpRequest = AsyncHttpRequestManager.getInstance(this);
		content = (TextView) findViewById(R.id.content);
		path = (EditText) findViewById(R.id.filePath);
	}
	
/*******************************************鑷畾涔塺equest锛岃繑鍥炰竴涓璞�****************************************************************************************************************************/	
	
	private void classRequsetPost(){
		UserInfo userinfo = new UserInfo();
	    userinfo.setLoginname("xuexiang");
	    userinfo.setPassword("xuexiang");
	    String userinfoJson=JsonUtil.toJson(userinfo);
	    
	    HttpRequest userRequest = mHttpRequest.getClassRequest(UserInfo.class, HttpConsts.app_user_url, User.LOGINCHECK, userinfoJson, new HttpSuccess<UserInfo>(){
			@Override
			public void onSuccess(UserInfo result) {
				String Json = JsonUtil.toJson(result);
				Toast(Json);
			}}, new HttpError(){
	
			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
	    mHttpRequest.doPost(userRequest);
	}

	private void classRequsetGet() {
		HttpRequest userRequest = mHttpRequest.getClassRequest(Student.class, HttpConsts.student_url, new HttpSuccess<Student>() {
			@Override
			public void onSuccess(Student result) {
				String Json = JsonUtil.toJson(result);
				Toast(Json);
			}}, new HttpError(){

			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
		mHttpRequest.doGet(userRequest);
	}
	
/*******************************************鑷畾涔塺equest锛岃繑鍥炰竴涓猻tring****************************************************************************************************************************/		
    private void stringRequsetPost() {    
    	VisitInfo visitInfo = new VisitInfo();
		visitInfo.setPageNum(0);
		visitInfo.setVisittype(VisitType.PRIVATE_TEACHER);
		String Json = JsonUtil.toJson(visitInfo);
		
		HttpRequest userRequest = mHttpRequest.getStringRequest(HttpConsts.app_visitservice_url, VisitService.GET_TYPE_VISITINFO_BY_PAGE, Json, new HttpSuccess<String>(){
			@Override
			public void onSuccess(String result) {
				List<VisitInfo> dataList = new ArrayList<VisitInfo>();
				Type listType = new TypeToken<List<VisitInfo>>(){}.getType(); 
				dataList = JsonUtil.fromRequest(result, listType);
				
				String Json = JsonUtil.toJson(dataList);
				Toast(Json);
			}}, new HttpError(){
	
			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
	    mHttpRequest.doPost(userRequest);		
	}
	
    private void stringRequsetGet() {       
    	HttpRequest userRequest = mHttpRequest.getStringRequest(HttpConsts.student_url, new HttpSuccess<String>(){
			@Override
			public void onSuccess(String result) {
				List<Student> dataList = new ArrayList<Student>();
				Type listType = new TypeToken<List<Student>>(){}.getType(); 
				dataList = JsonUtil.fromRequest(result, listType);
				
				String Json = JsonUtil.toJson(dataList);
				Toast(Json);
			}}, new HttpError(){
	
			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
	    mHttpRequest.doGet(userRequest);		
	}
	
/*******************************************鑷畾涔塺equest锛岃繑鍥炰竴涓狫SONObject****************************************************************************************************************************/			
   
    private void JSONObjectPost() {    
    	UserInfo userinfo = new UserInfo();
        userinfo.setLoginname("xuexiang");
        userinfo.setPassword("xuexiang");
        String userinfoJson = JsonUtil.toJson(userinfo); 
        
    	HttpRequest userRequest = mHttpRequest.getJsonObjectRequest(HttpConsts.app_user_url, User.LOGINCHECK, userinfoJson, new HttpSuccess<JSONObject>(){
			@Override
			public void onSuccess(JSONObject result) {
				String Json = null;
				try {
					Json = result.getString(HttpJSONObjectRequest.JSONObjectData);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(Json);
			}}, new HttpError(){
	
			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
	    mHttpRequest.doPost(userRequest);	
    }
    
    
    
    private void JSONObjectGet() {       
    	HttpRequest userRequest = mHttpRequest.getJsonObjectRequest(HttpConsts.student_url, new HttpSuccess<JSONObject>(){
			@Override
			public void onSuccess(JSONObject result) {
				List<Student> dataList = new ArrayList<Student>();
				JSONArray array = null;
				try {
					array = new JSONArray(result.getString(HttpJSONObjectRequest.JSONObjectData));				
				    for(int i=0; i< array.length(); i++){
						JSONObject object = (JSONObject) array.get(i);
						Student student = JsonUtil.fromRequest(object.toString(), Student.class);
						dataList.add(student);
				    }
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(JsonUtil.toJson(dataList));
			}}, new HttpError(){
	
			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
	    mHttpRequest.doGet(userRequest);	
    }
    

    private void JSONObjectGet2() {   
    	HttpRequest userRequest = mHttpRequest.getJsonObjectRequest(HttpConsts.student_url, new HttpSuccess<JSONObject>(){
			@Override
			public void onSuccess(JSONObject result) {
				List<Student> dataList = new ArrayList<Student>();
				try {
					String Json = result.getString(HttpJSONObjectRequest.JSONObjectData);
					Type listType = new TypeToken<List<Student>>(){}.getType(); 
					dataList = JsonUtil.fromRequest(Json, listType);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(JsonUtil.toJson(dataList));
			}}, new HttpError(){
	
			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
	    mHttpRequest.doGet(userRequest);	
    }
    
    private void getWeatherData() {
    	HttpRequest userRequest = mHttpRequest.getJsonObjectRequest(HttpConsts.TEST_URL, new HttpSuccess<JSONObject>(){
			@Override
			public void onSuccess(JSONObject result) {
				String Json = null;
				try {
					Json = result.getString(HttpJSONObjectRequest.JSONObjectData);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast(Json);
			}}, new HttpError(){
	
			@Override
			public void onError(Throwable error) {
				addLog(error.getMessage());
			}});
	    mHttpRequest.doGet(userRequest);	
    }
 
    private void getCustomRequestData() {
    	
    }
    
    /*******************************************澶氭枃浠朵笂浼�****************************************************************************************************************************/			
   	private void selectFile(){
   		Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
   	    intent.setType("*/*"); 
   	    intent.addCategory(Intent.CATEGORY_OPENABLE);
   	    try {
   	        startActivityForResult( Intent.createChooser(intent, "閫夋嫨涓婁紶鏂囦欢"), SELECT_FILE);
   	    } catch (android.content.ActivityNotFoundException ex) {
   	       
   	    }
   	}
   		
   	/**
   	 * Activity鍥炶皟鏂规硶
   	 */
   	@Override
   	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   		if(requestCode == SELECT_FILE && resultCode == RESULT_OK){
   			handlerActivityResult(data);
   		}
   	}
   	
   	/**
   	 * 澶勭悊杩斿洖鐨勬枃浠�
   	 * @param data
   	 */
   	private void handlerActivityResult(Intent intent){
   		String filePath = getAbsolutePath(intent.getData());
   		if (filePath != null) {
   	   		ShowLog(filePath);
   		}
   		path.append(filePath + ",");
   	}
   	
   	// 鍙栧埌缁濆璺緞
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
   		
   		List<File> list = new ArrayList<File>();
		String[] paths = path.getText().toString().split(",");		//鏍规嵁閫楀彿鎷嗗垎鏂囦欢鐨勮矾寰�
		for(int i = 0; i < paths.length; i++) {
			File file = FileUtils.createNewFile(paths[i]);
			if (file != null) {
				list.add(new File(paths[i]));		//鍒涘缓File
			}			
		} 		
   		multiFileUpload(list);
   	}
   	
   	/**
   	 * 澶氭枃浠朵笂浼�
   	 */
   	public void multiFileUpload(List<File> files) {
        if (files.isEmpty()) {
            Toast("涓嶅瓨鍦ㄦ湁鏁堟枃浠讹紝璇蜂慨鏀规枃浠惰矾寰�");
            return;
        }
        
        mAlertDialog = new CircularProgressDialog(this, "姝ｅ湪涓婁紶鏂囦欢锛�");
	    mAlertDialog.show();
	    HashMap<String, String> param = new HashMap<String, String>();
	    param.put("content", "杩欐槸涓婁紶鐨勫弬鏁�:杩欐槸AsyncHttp涓婁紶鐨勬枃浠�");
	    mHttpRequest.executeFileReques(HttpConsts.UPLPAD_URL, files, param, new HttpSuccess<String>(){

			@Override
			public void onSuccess(String response) {
				mAlertDialog.dismiss();
				addLog("鏂囦欢涓婁紶瀹屾瘯锛佽繑鍥炵粨鏋�:" + response);
			}}, new HttpError(){

			@Override
			public void onError(Throwable error) {
				mAlertDialog.dismiss();
				addLog(error.getMessage());
			}
		});
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
	

}
