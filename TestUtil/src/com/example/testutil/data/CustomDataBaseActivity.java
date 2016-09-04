package com.example.testutil.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.testutil.R;
import com.example.testutil.data.adapter.CustomDataAdapter;
import com.example.testutil.data.dao.custom.CustomDBManager;
import com.example.testutil.data.entity.Student;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.data.db.ormlite.custom.CustomDBService;

public class CustomDataBaseActivity extends BaseActivity implements OnClickListener{
    private CustomDBService<Student> mDatabaseService;
	private CustomDataAdapter adapter;
	private List<Student> data = new ArrayList<Student>();
	private ListView lvData;
	

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_database);
		initTitleBar(TAG);
		
		lvData = (ListView) findViewById(R.id.lvData);
		mDatabaseService = CustomDBManager.getInstance(this).getCustomDataBase(Student.class);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add:
			Student student = new Student();
			student.setUsername("xuexiang");
			student.setSex("ÄÐ");
			student.setAge(23);
//			student.setScore(100);
			
			try {
				mDatabaseService.insert(student);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		case R.id.query:
			try {
				data = mDatabaseService.queryAllData();
				adapter = new CustomDataAdapter(this, data, mDatabaseService);
            	lvData.setAdapter(adapter);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case R.id.update:
			try {
				mDatabaseService.updateDataByColumn("age", 25, "age", 19);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
			
		case R.id.delete:
			try {
				mDatabaseService.deleteAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
			break;
		default:
			break;
		}
	}

}
