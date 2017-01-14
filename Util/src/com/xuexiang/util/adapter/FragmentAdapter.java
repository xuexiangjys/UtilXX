package com.xuexiang.util.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**  
 * 
 * 创建时间：2017-1-11 下午10:40:26
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：FragmentAdapter.java 
 **/
public class FragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
    	if (fragment != null) {
    		mFragmentList.add(fragment);
    	}
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
