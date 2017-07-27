package com.nl.rxjava;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/** 
 * 提供简单的ListActivity，测试程序功能使用
 * @author xx
 * @Date 2017-2-22 下午4:09:04 
 */
public abstract class ListSimpleActivity extends ListActivity {
	public final String TAG = getClass().getSimpleName();
	protected List<String> mSimpleData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra(ActivityItem.TITLE);
        if (title != null) {
            setTitle(title);
        }
        mSimpleData = initSimpleData();
        getListView().setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mSimpleData));
        
        init();
    }
    
    protected abstract void init();

	@Override
	protected void onListItemClick(ListView listView, View v, int position, long id) {
    	onItemClick(position);
	}

    /**
     * 初始化例子
     * @return
     */
    protected abstract List<String> initSimpleData();
    
    /**
     * 条目点击
     * @param intent
     */
    protected abstract void onItemClick(int position);
    
    public void Toast(final String msg) {
    	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    public void logs(String msg) {
    	Log.e(TAG, msg);
    }
    
    public void print(String msg) {
    	System.out.print(msg);
    }
}
