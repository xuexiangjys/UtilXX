package com.example.testutil.view;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.flowtaglayout.FlowTagLayout;
import com.xuexiang.view.flowtaglayout.OnTagClickListener;
import com.xuexiang.view.flowtaglayout.OnTagSelectListener;
import com.xuexiang.view.flowtaglayout.TagAdapter;

public class FlowTagLayoutActivity extends BaseActivity {
	private FlowTagLayout mColorFlowTagLayout;
    private FlowTagLayout mSizeFlowTagLayout;
    private FlowTagLayout mMobileFlowTagLayout;
    private TagAdapter<String> mSizeTagAdapter;
    private TagAdapter<String> mColorTagAdapter;
    private TagAdapter<String> mMobileTagAdapter;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_flowtaglayout);
		initTitleBar(TAG);

		initview();
	}
	private void initview() {
	    mColorFlowTagLayout = (FlowTagLayout) findViewById(R.id.single_click_flow_layout);
        mSizeFlowTagLayout = (FlowTagLayout) findViewById(R.id.single_choose_flow_layout);
        mMobileFlowTagLayout = (FlowTagLayout) findViewById(R.id.multi_select_flow_layout);

        //���
        mColorTagAdapter = new TagAdapter<String>(this);
        mColorFlowTagLayout.setAdapter(mColorTagAdapter);
        mColorFlowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
            	Toast("����ˣ�" + parent.getAdapter().getItem(position));
            }
        });

        //��ѡ
        mSizeTagAdapter = new TagAdapter<String>(this);
        mSizeTagAdapter.setSelected(4);
        mSizeFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mSizeFlowTagLayout.setAdapter(mSizeTagAdapter);
        mSizeFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast(position + "��ϲ��" + sb.toString());
                } else {
                	Toast("û��ѡ���ǩ");
                }
            }
        });

        //��ѡ
        mMobileTagAdapter = new TagAdapter<String>(this);
        mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);
        mMobileFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int positon, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast("O(��_��)O������~:" + sb.toString());
                } else {
                	Toast("û��ѡ���ǩ");
                }
            }
        });

        initColorData();

        initSizeData();

        initMobileData();	
	}
	
	private void initMobileData() {
        List<String> dataSource = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            dataSource.add("��ѡ*-* "+i);

        }
        mMobileTagAdapter.onlyAddAll(dataSource);
    }

    private void initColorData() {
        List<String> dataSource = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            dataSource.add("����!+! "+i);

        }
        mColorTagAdapter.onlyAddAll(dataSource);
    }

    /**
     * ��ʼ������
     */
    private void initSizeData() {
        List<String> dataSource = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            dataSource.add("��ѡ^-^ "+i);

        }
        mSizeTagAdapter.onlyAddAll(dataSource);
    }

}