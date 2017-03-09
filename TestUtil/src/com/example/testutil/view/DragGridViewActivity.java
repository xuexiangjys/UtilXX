package com.example.testutil.view;

import java.util.ArrayList;
import java.util.Collections;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.adapter.commonadapter.CommonAdapter;
import com.xuexiang.util.adapter.commonadapter.ViewHolder;
import com.xuexiang.view.dragview.entity.GridCutItem;
import com.xuexiang.view.dragview.widget.ControlScrollView;
import com.xuexiang.view.dragview.widget.DragGridView;
import com.xuexiang.view.dragview.widget.ViewWithSign;

/**  
 * 创建时间：2016-8-2 下午8:28:58  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：DragGridViewActivity.java  
 **/
public class DragGridViewActivity extends BaseActivity {

	private DragGridView mGrid;
    private ControlScrollView mScroller;
    private ArrayList<GridCutItem> mDatas = new ArrayList<GridCutItem>();
    private CommonAdapter<GridCutItem> mAdapter;
    private ViewWithSign viewWithSign ;
    
    public String[][] gridDatas = {
        {"名称1",""},
        {"名称2",""},
        {"名称3",""},
        {"名称4",""},
        {"名称5",""},
        {"名称6","蚂蚁"},
        {"名称7",""},
        {"名称8","活动1"},
        {"名称9",""},
        {"名称10","活动2"},
        {"名称11",""},
        {"名称12",""},
        {"名称13",""},
        {"名称14",""},
        {"名称15",""},
        {"名称17",""},
        {"名称18",""},
        {"名称19",""},
        {"名称20",""},
        {"名称21",""},
        {"名称22",""},
        {"名称23",""},
        {"名称24",""},
        {"名称25",""},
        {"名称26",""},
        {"名称27",""},
        {"更多",""}};
    
    public int[] gridIconDatas = {R.drawable.ic_category_10, R.drawable.ic_category_20, R.drawable.ic_category_30, R.drawable.ic_category_40, R.drawable.ic_category_50, R.drawable.ic_category_60, R.drawable.ic_category_70, R.drawable.ic_category_80,
    		R.drawable.ic_category_10, R.drawable.ic_category_20, R.drawable.ic_category_30, R.drawable.ic_category_40, R.drawable.ic_category_50, R.drawable.ic_category_60, R.drawable.ic_category_70, R.drawable.ic_category_80,
    		R.drawable.ic_category_10, R.drawable.ic_category_20, R.drawable.ic_category_30, R.drawable.ic_category_40, R.drawable.ic_category_50, R.drawable.ic_category_60, R.drawable.ic_category_70, R.drawable.ic_category_80,
    		R.drawable.ktn, R.drawable.ic_launcher, R.drawable.ktn, R.drawable.ic_launcher};
    
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_draggridview);
		
        initView();
	}
	
	private void initView() {
		mGrid = (DragGridView) findViewById(R.id.grid);
        mScroller = (ControlScrollView) findViewById(R.id.scroller);
        mDatas.clear();
        for(int i = 0; i< gridDatas.length; i++) {
            GridCutItem gridCutItem = new GridCutItem(gridDatas[i][0], gridIconDatas[i], gridDatas[i][1]);
            mDatas.add(gridCutItem);
        }

        mGrid.setAdapter(mAdapter = new CommonAdapter<GridCutItem>(mContext, mDatas, R.layout.adapter_draggridview_item) {
            @Override
            public void convert(ViewHolder helper, final GridCutItem item, int position) {
                helper.setText(R.id.tv_item, item.getName());
                helper.setImageResource(R.id.iv_item, item.getDrawableId());
                viewWithSign = helper.getView(R.id.icon);
                viewWithSign.addDrawText(item.getTip());
                if (position == mAdapter.getCount() -1) {
                    helper.setImageResource(R.id.iv_item, R.drawable.add_more);
                }
            }
        });

        //设置拖拽数据交换
        mGrid.setOnChangeListener(new DragGridView.OnChangeListener() {
            @Override
            public void onChange(int from, int to) {
                GridCutItem temp = mDatas.get(from);
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(mDatas, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(mDatas, i, i - 1);
                    }
                }
                mDatas.set(to, temp);
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdapter.notifyDataSetChanged();
        mScroller.setScrollState(new ControlScrollView.ScrollState() {
            @Override
            public void stopTouch() {
                mGrid.stopDrag();
            }

            @Override
            public void isCanDrag(boolean isCanDrag) {
                mGrid.setCanDrag(isCanDrag);
            }
        });

        mGrid.setOnDragStartListener(new DragGridView.OnDragStartListener() {
            @Override
            public void onDragStart() {
                mScroller.requestDisallowInterceptTouchEvent(true);
                mScroller.setInControl(false);
            }
        });
        mGrid.setOnDragEndListener(new DragGridView.OnDragEndListener() {
            @Override
            public void onDragEnd() {
                mScroller.requestDisallowInterceptTouchEvent(false);
                mScroller.setInControl(true);
                mGrid.postInvalidate();
            }
        });
        mGrid.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast("点击了：" + mDatas.get(position).getName());
			}
		});

    }

}
