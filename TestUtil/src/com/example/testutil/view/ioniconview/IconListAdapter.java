package com.example.testutil.view.ioniconview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.view.iconview.Icon;
import com.xuexiang.view.iconview.IconView;

/**
 * Created by Aper on 16/01/14.
 */
public class IconListAdapter extends BaseAdapter {

    private final Context context;
    private Icon[] icons = Icon.values();
    public IconListAdapter(Context ctx){
        this.context = ctx;
    }
    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Icon getItem(int position) {
        return icons[position];
    }

    @Override
    public long getItemId(int position) {
        return 0+position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  lf.inflate(R.layout.adapter_icon_list_item, parent, false);
        }
        IconView iv = (IconView) convertView.findViewById(R.id.icon_view);
        TextView tv = (TextView) convertView.findViewById(R.id.icon_name);

        iv.setIcon(getItem(position));
        tv.setText(getItem(position).toString());
        return convertView;
    }
}
