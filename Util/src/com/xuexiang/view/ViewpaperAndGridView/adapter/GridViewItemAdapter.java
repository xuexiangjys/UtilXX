package com.xuexiang.view.ViewpaperAndGridView.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.ViewpaperAndGridView.bean.ChannelItem;

public class GridViewItemAdapter extends BaseAdapter {

	private List<ChannelItem> mList;  
    private Context mContext;  
    /** ViewPager页码 */  
    private int index;  
    /** 根据屏幕大小计算得到的每页item个数 */  
    private int pageItemCount;  
    /** 传进来的List的总长度 */  
    private int totalSize;  
  
    public GridViewItemAdapter(Context context, List<ChannelItem> list) {  
        mContext = context;  
        mList = list;  
    }  
  
    public GridViewItemAdapter(Context context, List<ChannelItem> list, int index, int pageItemCount) {  
        this.mContext = context;  
        this.index = index;  
        this.pageItemCount = pageItemCount;  
        mList = new ArrayList<ChannelItem>();  
        totalSize = list.size();  
        int list_index = index * pageItemCount;  
        for (int i = list_index; i < list.size(); i++) {  
            mList.add(list.get(i));  
        }  
  
    }  
  
    @Override  
    public int getCount() {  
        int size = totalSize / pageItemCount;  
        if (index == size)  
            return totalSize - pageItemCount * index;  
        else  
            return pageItemCount;  
    }  
  
    @Override  
    public Object getItem(int position) {  
       // return null;  
    	return mList.get(position);
    }  
  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
  
        ViewHolder holder;  
        if (convertView == null) { 
            holder = new ViewHolder();  
            convertView = LayoutInflater.from(mContext).inflate(RUtils.getLayout(mContext, "channel_gridview_item"), null);
            holder.gridview_layout =  (LinearLayout) convertView.findViewById(RUtils.getId(mContext, "gridview_layout"));  
        	holder.iv_icon = (ImageView) convertView.findViewById(RUtils.getId(mContext, "iv_gv_item_icon"));  
        	holder.tv_name = (TextView) convertView.findViewById(RUtils.getId(mContext, "tv_gv_item_Name"));  
        	
        	
        	convertView.setTag(holder);
        } else { 
            holder = (ViewHolder) convertView.getTag();  
        }
        holder.updateViews(position, null);
        return convertView;  
    }  
  
    class ViewHolder{  
    	LinearLayout gridview_layout;
        ImageView iv_icon;  
        TextView tv_name;  
      
        protected void updateViews(int position, Object inst) {  
            iv_icon.setImageResource(mList.get(position).getIconID());  
            tv_name.setText(mList.get(position).getName());  
        }  
    } 
 

}
