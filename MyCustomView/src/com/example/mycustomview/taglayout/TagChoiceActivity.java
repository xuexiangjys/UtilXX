package com.example.mycustomview.taglayout;

import com.example.mycustomview.R;
import com.example.mycustomview.taglayout.utils.TagWordFactory;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.tag.TagLayout;
import com.xuexiang.view.tag.TagView;

public class TagChoiceActivity extends BaseHeadActivity implements TagView.OnTagClickListener, TagView.OnTagLongClickListener {

    private TagLayout mTagLayout1;
    private TagLayout mTagLayout2;
    private TagLayout mTagLayout3;
    private TagView mTagDel;
    private TagView mTagAdd;

    @Override
	protected int getLayoutId() {
		return R.layout.activity_tag_choice;
	}

	@Override
	protected void init() {
		initView();
	}

    private void initView() {
        mTagLayout1 = (TagLayout) findViewById(R.id.tag_layout_1);
        mTagLayout2 = (TagLayout) findViewById(R.id.tag_layout_2);
        mTagLayout3 = (TagLayout) findViewById(R.id.tag_layout_3);
        mTagDel = (TagView) findViewById(R.id.tag_del);
        mTagAdd = (TagView) findViewById(R.id.tag_add);
        mTagLayout1.setTagClickListener(this);
        mTagLayout1.setTagLongClickListener(this);
        mTagLayout2.setTagClickListener(this);
        mTagLayout2.setTagLongClickListener(this);
        mTagLayout3.setTagClickListener(this);
        mTagLayout3.setTagLongClickListener(this);

        mTagAdd.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                String word = TagWordFactory.provideTagWord();
                mTagLayout1.addTag(word);
                mTagLayout2.addTag(word);
                mTagLayout3.addTag(word);
            }
        });
        mTagDel.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                mTagLayout1.deleteCheckedTags();
                mTagLayout2.deleteCheckedTags();
                mTagLayout3.deleteCheckedTags();
            }
        });
    }

    @Override
    public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
        Toast(text);
    }

    @Override
    public void onTagLongClick(int position, String text, @TagView.TagMode int tagMode) {
        Toast("长按:" + text);
    }

	
}
