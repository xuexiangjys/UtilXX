package com.xuexiang.view.ViewpaperAndGridView.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {

	private List<View> lists;
	public ViewPagerAdapter(List<View> data){
		lists = data;
	}
	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		
		//return false;
		return arg0 == (arg1);
	}

	public Object instantiateItem(View arg0, int arg1) {
		try {
		ViewGroup parent = (ViewGroup) lists.get(arg1).getParent();
		 if (parent != null) {
			 parent.removeAllViews();
		 } 
		((ViewPager) arg0).addView(lists.get(arg1),0);
		} catch (Exception e) {
		e.printStackTrace();
		}

		return lists.get(arg1);
		}


		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			try {
			    ((ViewPager) arg0).removeView(lists.get(arg1));
			} catch (Exception e) {
			e.printStackTrace();
			}
		}

}
