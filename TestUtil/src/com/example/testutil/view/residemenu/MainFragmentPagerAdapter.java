package com.example.testutil.view.residemenu;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
	
	ArrayList<Fragment> datas;

	public MainFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> datas) {
		super(fm);
		setDatas(datas);
	}

	public void setDatas(ArrayList<Fragment> datas) {
		if(datas==null){
			this.datas=new ArrayList<Fragment>();
		}else{
			this.datas=datas;
		}
	}
	
	
	@Override
	public Fragment getItem(int position) {
		return datas.get(position);
	}

	@Override
	public int getCount() {
		return datas.size();
	}



}
