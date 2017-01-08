package com.example.testutil.data.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.data.entity.Patient;
import com.xuexiang.util.adapter.BaseContentAdapter;
import com.xuexiang.util.data.db.ahibernate.DatabaseService;

public class PatientAdapter extends BaseContentAdapter<Patient> {

	private  DatabaseService<Patient> mDatabaseService;
	public void setData(List<Patient> data) {
		mDataList = data;
		notifyDataSetChanged();
	}
	public PatientAdapter(Context context,List<Patient> list,DatabaseService<Patient> databaseService) {
		super(context, list);
		mDatabaseService = databaseService;
	}

	@Override
	public View getConvertView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.data_item, null);
			holder=new ViewHolder();
			holder.tvName=(TextView) convertView.findViewById(R.id.tvName);
			holder.tvAge=(TextView) convertView.findViewById(R.id.tvAge);
			holder.tvUpdate=(TextView) convertView.findViewById(R.id.tvUpdate);
			holder.tvDelete=(TextView) convertView.findViewById(R.id.tvDelete);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		final Patient p=getItem(position);
		holder.tvName.setText(p.getName());
		holder.tvAge.setText(p.getAge());
		holder.tvUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				update(position);
			}

		});
		holder.tvDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				delete(position);
			}
		});
		
		return convertView;
	}

	private class ViewHolder{
		private TextView tvName,tvAge,tvUpdate,tvDelete;
	}

	public void update(int position) {
		
		Patient p=getItem(position);
		Map<String,String> map=new HashMap<String, String>();
		map.put("id", p.getId()+"");
		p.setName("hhaha");
		mDatabaseService.update(p, map);
		mDataList = mDatabaseService.getObjectsByWhere(null);
	    notifyDataSetChanged();
	}

	public void delete(int position) {
		
		Patient p=getItem(position);
//		Map<String,String> map=new HashMap<String, String>();
//		map.put("id", p.getId()+"");
//		mDatabaseService.delete(map);
		mDatabaseService.delete(p);
		mDataList = mDatabaseService.getObjectsByWhere(null);
	    notifyDataSetChanged();
	}
	
}
