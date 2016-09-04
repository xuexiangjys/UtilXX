package com.example.testutil.data.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.data.entity.User;
import com.xuexiang.util.adapter.BaseContentAdapter;
import com.xuexiang.util.data.db.ahibernate.DatabaseService;

/**  
 * 创建时间：2016-2-5 下午3:42:49  
 * 项目名称：OrmDemo  
 * @author xuexiang
 * 文件名称：UserDataAdapter.java  
 **/
public class UserDataAdapter extends  BaseContentAdapter<User>{
	
	private  DatabaseService<User> mDatabaseService;
	
	public void setData(List<User> data) {
		dataList = data;
		notifyDataSetChanged();
	}
	public UserDataAdapter(Context context,List<User> list,DatabaseService<User> databaseService) {
		super(context, list);
		mDatabaseService = databaseService;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public User getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getConvertView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.user_item, null);	
			holder = new ViewHolder();
			holder.tvName=(TextView) convertView.findViewById(R.id.name_item);
			holder.tvPassword=(TextView) convertView.findViewById(R.id.password_item);
			holder.tvDelete=(TextView) convertView.findViewById(R.id.delete_item);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(getItem(position).getLoginname());
		holder.tvPassword.setText(getItem(position).getPassword());
		holder.tvDelete.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				delete(position);
			}
		});
		
		return convertView;
	}

	
	public void delete(int position) {
		// TODO Auto-generated method stub
		User user = getItem(position);
		/*Map<String,String> map=new HashMap<String, String>();
		map.put("id", user.getId().toString());
		mDatabaseService.delete(map);*/
		mDatabaseService.delete(user);
		dataList = mDatabaseService.getObjectsByWhere(null);
	    notifyDataSetChanged();
	}

	private class ViewHolder{
		private TextView tvName,tvPassword,tvDelete;
	}

	
}
