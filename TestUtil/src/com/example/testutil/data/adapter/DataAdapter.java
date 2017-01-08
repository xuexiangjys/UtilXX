package com.example.testutil.data.adapter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.data.entity.Student;
import com.xuexiang.util.adapter.BaseContentAdapter;
import com.xuexiang.util.data.db.ormlite.default_.DBService;

public class DataAdapter extends BaseContentAdapter<Student> {

	private  DBService<Student> mDatabaseService;
	public void setData(List<Student> data) {
		mDataList = data;
		notifyDataSetChanged();
	}
	public DataAdapter(Context context, List<Student> list, DBService<Student> databaseService) {
		super(context, list);
		mDatabaseService = databaseService;
	}

	@Override
	public View getConvertView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.data_item, null);
			holder=new ViewHolder();
			holder.tvId=(TextView) convertView.findViewById(R.id.tvId);
			holder.tvName=(TextView) convertView.findViewById(R.id.tvName);
			holder.tvAge=(TextView) convertView.findViewById(R.id.tvAge);
			holder.tvSex=(TextView) convertView.findViewById(R.id.tvSex);
			holder.tvUpdate=(TextView) convertView.findViewById(R.id.tvUpdate);
			holder.tvDelete=(TextView) convertView.findViewById(R.id.tvDelete);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		Student student = getItem(position);
		holder.tvId.setText(String.valueOf(student.getId())); 
		holder.tvName.setText(student.getUsername());
		holder.tvAge.setText(String.valueOf(student.getAge()));
		holder.tvSex.setText(student.getSex());
		holder.tvUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {			
				try {
					update(position);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		holder.tvDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				try {
					delete(position);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});		
		return convertView;
	}

	private class ViewHolder{
		private TextView tvId,tvName,tvAge,tvSex,tvUpdate,tvDelete;
	}

	public void update(int position) throws IOException, SQLException {		
		Student student = getItem(position);        
		student.setUsername("xxxx");
		student.setAge(19);
		student.setSex("女");
		mDatabaseService.updateData(student);
		mDataList = mDatabaseService.queryAllData();
	    notifyDataSetChanged();
	}

	public void delete(int position) throws IOException, SQLException {		
		Student student = getItem(position);
		mDatabaseService.deleteData(student);
		mDataList = mDatabaseService.queryAllData();	
	    notifyDataSetChanged();
	}
	
}
