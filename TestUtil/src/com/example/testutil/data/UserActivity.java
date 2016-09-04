package com.example.testutil.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testutil.R;
import com.example.testutil.data.adapter.UserDataAdapter;
import com.example.testutil.data.entity.User;
import com.xuexiang.util.data.db.ahibernate.DatabaseService;

/**  
 * 创建时间：2016-2-5 下午3:55:04  
 * 项目名称：OrmDemo  
 * @author xuexiang
 * 文件名称：UserActivity.java  
 **/
public class UserActivity extends Activity implements OnClickListener {
	private EditText loginname,password;
	private Button add,update,query;
	private ListView userlist;
	private Context context;
	
	DatabaseService<User> mDatabaseService;
    private UserDataAdapter adapter;
    private List<User> data=new  ArrayList<User>();
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_user);
	        context = this;
	        initview();
	        
	        
	 }
	 
	private void initview() {
		// TODO Auto-generated method stub
		loginname = (EditText) findViewById(R.id.loginname);
		password = (EditText) findViewById(R.id.password);
		add = (Button) findViewById(R.id.add);
		update = (Button) findViewById(R.id.update);
		query = (Button) findViewById(R.id.query);
		userlist=(ListView) findViewById(R.id.userlist);
		add.setOnClickListener(this);
		update.setOnClickListener(this);
		query.setOnClickListener(this);
		
		mDatabaseService = new DatabaseService<User>(context,User.class);
			
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.add:
			addUser();
			break;
		case R.id.update:
			update();
			break;
		case R.id.query:	
			query();
			break;
		}
	}
	
	private void query() {
		// TODO Auto-generated method stub
		data=mDatabaseService.getObjectsByWhere(null);
    	adapter = new UserDataAdapter(context, data,mDatabaseService);
    	userlist.setAdapter(adapter);
	}

	public void addUser() {
		if(TextUtils.isEmpty(loginname.getText().toString())){
			Toast("用户名不能为空！");
			return;
		}
        if(TextUtils.isEmpty(password.getText().toString())){
        	Toast("密码不能为空！");
        	return;
		}
        User user = new User();
        user.setLoginname(loginname.getText().toString());
        user.setPassword(password.getText().toString());
        int row=mDatabaseService.add(user);
        if(row>0){
       	 Toast("插入成功");
       }else{
    	 Toast("插入失败");    
       }
		
	}
	
	public void update(){
		if(TextUtils.isEmpty(loginname.getText().toString())){
			Toast("用户名不能为空！");
			return;
		}
        if(TextUtils.isEmpty(password.getText().toString())){
        	Toast("密码不能为空！");
        	return;
		}
        
        User user = new User();
        user.setLoginname(loginname.getText().toString());
        user.setPassword(password.getText().toString());
        
        Map<String,String> map=new HashMap<String, String>();
		map.put("loginname", user.getLoginname());
		mDatabaseService.update(user, map);		
		if(!data.isEmpty()){
			data = mDatabaseService.getObjectsByWhere(null);			
			adapter.setData(data);
		}
	}
	
	
	
	
	public void Toast(CharSequence hint){
	    Toast.makeText(this, hint , Toast.LENGTH_SHORT).show();
	}	

}
