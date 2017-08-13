package com.example.mycustomview.taglayout;

import android.content.Intent;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.tag.TagLayout;
import com.xuexiang.view.tag.TagView;

public class TagLayoutActivity extends BaseHeadActivity {

    private final String[] mTagWords = new String[]{
            "不同边框形状的标签", "单选和多选标签", "可编辑的标签", "动画效果的换一换标签",
            "TagView的一些其它用途", "水平反向排列(RTL)"
    };
    private TagLayout mTagLayout;

    @Override
	protected int getLayoutId() {
		return R.layout.activity_taglayout;
	}

	@Override
	protected void init() {
		initView();
	}

    private void initView() {
        mTagLayout = (TagLayout) findViewById(R.id.tag_layout);
        mTagLayout.setTags(mTagWords);
        mTagLayout.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                if (mTagWords[0].equals(text)) {
                    startActivity(new Intent(TagLayoutActivity.this, TagShapeActivity.class));
                } else if (mTagWords[1].equals(text)) {
                    startActivity(new Intent(TagLayoutActivity.this, TagChoiceActivity.class));
                } else if (mTagWords[2].equals(text)) {
                    startActivity(new Intent(TagLayoutActivity.this, TagEditActivity.class));
                } else if (mTagWords[3].equals(text)) {
                    startActivity(new Intent(TagLayoutActivity.this, TagChangeActivity.class));
                } else if (mTagWords[4].equals(text)) {
                    startActivity(new Intent(TagLayoutActivity.this, TagViewActivity.class));
                } else if (mTagWords[5].equals(text)) {
                    startActivity(new Intent(TagLayoutActivity.this, TagReverseActivity.class));
                }
            }
        });
    }

	
}
