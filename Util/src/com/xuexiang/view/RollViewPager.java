package com.xuexiang.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.util.net.NetImageUtil;
import com.xuexiang.util.resource.RUtils;

/**
 * @Description:自动播放的广告条，viewpager，带标题和滚动的小圆点，有点击事件
 */ 
public class RollViewPager extends ViewPager {
	private Context mContext;
	private int currentItem;
	private ArrayList<String> mUriList;
	private ArrayList<View> dots;
	private TextView title;
	private ArrayList<String> titles;
	private int[] mResImageIds;
	private int dot_focus_resId;
	private int dot_normal_resId;
	private OnPagerClickCallback mOnPagerClickCallback;
	private boolean isShowResImage = false;
	private MyOnTouchListener mMyOnTouchListener;
	private ViewPagerTask mViewPagerTask;
	private PagerAdapter mAdapter;

	/** 触摸时按下的点 **/
	PointF downP = new PointF();
	/** 触摸时当前的点 **/
	PointF curP = new PointF();
	private int abc = 1;
	private float mLastMotionX;
	private float mLastMotionY;
	
	private long start = 0;

	public class MyOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			curP.x = event.getX();
			curP.y = event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				start = System.currentTimeMillis();
				handler.removeCallbacksAndMessages(null);
				downP.x = event.getX();
				downP.y = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				handler.removeCallbacks(mViewPagerTask);
				break;
			case MotionEvent.ACTION_CANCEL:
				startRoll();
				break;
			case MotionEvent.ACTION_UP:
				downP.x = event.getX();
				downP.y = event.getY();
				long duration = System.currentTimeMillis() - start;
				if (duration <= 500 && downP.x == curP.x) {
					mOnPagerClickCallback.onPagerClick(currentItem);
				} else {
				}
				startRoll();
				break;
			}
			return true;
		}
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		final float x = ev.getX();
		final float y = ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);
			abc = 1;
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			if (abc == 1) {
				if (Math.abs(x - mLastMotionX) < Math.abs(y - mLastMotionY)) {
					abc = 0;
					getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					getParent().requestDisallowInterceptTouchEvent(true);
				}

			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	public class ViewPagerTask implements Runnable {
		@Override
		public void run() {
			currentItem = (currentItem + 1)
					% (isShowResImage ? mResImageIds.length : mUriList.size());
			handler.obtainMessage().sendToTarget();
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			RollViewPager.this.setCurrentItem(currentItem);
			startRoll();
		}
	};

	public RollViewPager(Context context, ArrayList<View> dots, int dot_focus_resId, int dot_normal_resId, OnPagerClickCallback onPagerClickCallback) {
		super(context);
		mContext = context;
		this.dots = dots;
		this.dot_focus_resId = dot_focus_resId;
		this.dot_normal_resId = dot_normal_resId;
	    mOnPagerClickCallback = onPagerClickCallback;
		mViewPagerTask = new ViewPagerTask();
		mMyOnTouchListener = new MyOnTouchListener();
	}

	public void setUriList(ArrayList<String> uriList) {
		isShowResImage = false;
		mUriList = uriList;
	}
	
	public void setDots(ArrayList<View> dots) {
		this.dots = dots;
	}

	public void setResImageIds(int[] resImageIds) {
		isShowResImage = true;
		mResImageIds = resImageIds;
	}

	public void setTitle(TextView title, ArrayList<String> titles) {
		this.title = title;
		this.titles = titles;
		if (title != null && titles != null && titles.size() > 0)
			title.setText(titles.get(0));//
	}

	public void notifyDataChange() {
		mAdapter.notifyDataSetChanged();
	}

	public ArrayList<View> getDots() {
		return dots;
	}

	
	private boolean hasSetAdapter = false;

	/**
	 * 开始滚动
	 */
	public void startRoll() {
		if (!hasSetAdapter) {
			hasSetAdapter = true;
			this.setOnPageChangeListener(new MyOnPageChangeListener());
			mAdapter = new ViewPagerAdapter();
			this.setAdapter(mAdapter);
		}
		handler.postDelayed(mViewPagerTask, 4000);
	}

	class MyOnPageChangeListener implements OnPageChangeListener {
		int oldPosition = 0;

		@Override
		public void onPageSelected(int position) {
			currentItem = position;
			if (title != null)
				title.setText(titles.get(position));
			if (dots != null && dots.size() > 0) {
				dots.get(position).setBackgroundResource(dot_focus_resId);
				dots.get(oldPosition).setBackgroundResource(dot_normal_resId);
			}
			oldPosition = position;
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
	}

	class ViewPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return isShowResImage ? mResImageIds.length : mUriList.size();
		}

		@Override
		public Object instantiateItem(View container, final int position) {
			View view = View.inflate(mContext, RUtils.getLayout(getContext(), "viewpager_roll_item"), null);
			((ViewPager) container).addView(view);
			view.setOnTouchListener(mMyOnTouchListener);
			ImageView imageView = (ImageView) view.findViewById(RUtils.getId(getContext(), "image"));
			if (isShowResImage) {
				imageView.setImageResource(mResImageIds[position]);
			} else {
				NetImageUtil.getImage(mUriList.get(position), imageView);
			}
			return view;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		handler.removeCallbacksAndMessages(null);
		super.onDetachedFromWindow();
	}

	public interface OnPagerClickCallback {
		public abstract void onPagerClick(int position);
	}

}
