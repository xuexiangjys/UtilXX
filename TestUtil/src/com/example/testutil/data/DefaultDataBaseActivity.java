package com.example.testutil.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.testutil.R;
import com.example.testutil.data.adapter.DataAdapter;
import com.example.testutil.data.dao.default_.DBManager;
import com.example.testutil.data.entity.Student;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.data.db.SQLiteToExcel;
import com.xuexiang.util.data.db.ormlite.default_.DBService;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.view.DialogUtil;
import com.xuexiang.util.view.DialogUtil.LoadingStyle;

public class DefaultDataBaseActivity extends BaseActivity implements OnClickListener{
    private DBService<Student> mDatabaseService;   
    private DataAdapter adapter;
    private List<Student> data = new ArrayList<Student>();
    private ListView lvData;
    
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_database);
		initTitleBar(TAG);
		
		lvData = (ListView) findViewById(R.id.lvData);
		mDatabaseService = DBManager.getInstance(this).getDataBase(Student.class);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add:
			Student student = new Student();
			student.setUsername("xuexiang");
			student.setSex("男");
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
				adapter = new DataAdapter(this, data, mDatabaseService);
            	lvData.setAdapter(adapter);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case R.id.update:
			try {
				mDatabaseService.updateDataByColumn("username", "xuexiang", "username", "xxxx");
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
		case R.id.export:
			final Dialog loadingDialog = DialogUtil.createLoadingDialog(mContext, "正在导出数据库", LoadingStyle.Spots);
			SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), mDatabaseService.getDatabaseHelper().getDatabase().getPath(), LocalFileUtil.DATABASE_PATH);
            sqliteToExcel.exportAllTables("Default_Students.xls", new SQLiteToExcel.ExportListener() {
                @Override
                public void onStart() {
                	loadingDialog.show();
                }

                @Override
                public void onCompleted(String filePath) {
                	loadingDialog.dismiss();
                    Toast("导出成功！，文件路径：" + filePath);
                }

                @Override
                public void onError(Exception e) {
                	loadingDialog.dismiss();
                }
            });
			break;
		default:
			break;
		}
	}


	
}
