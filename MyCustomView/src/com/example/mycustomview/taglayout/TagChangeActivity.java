package com.example.mycustomview.taglayout;

import com.example.mycustomview.R;
import com.example.mycustomview.taglayout.utils.TagWordFactory;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.tag.TagLayout;
import com.xuexiang.view.tag.TagView;

public class TagChangeActivity extends BaseHeadActivity implements TagView.OnTagLongClickListener {

    private TagLayout mTagLayout1;
    private TagLayout mTagLayout2;
    private TagLayout mTagLayout3;
    private TagView mTagDel;
    private TagView mTagAdd;

    @Override
	protected int getLayoutId() {
		return R.layout.activity_tag_change;
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
        mTagLayout1.setTagClickListener(new TagView.OnTagClickListener() {
            boolean isChange = false;

            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                if (tagMode == TagView.MODE_CHANGE) {
                    if (isChange) {
                        mTagLayout1.updateTags(TagWordFactory.TAG_WORD);
                    } else {
                        mTagLayout1.updateTags(TagWordFactory.TAG_WORD_2);
                    }
                    isChange = !isChange;
                } else {
                    Toast(text);
                }
            }
        });
        mTagLayout2.setTagClickListener(new TagView.OnTagClickListener() {
            boolean isChange = false;

            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                if (tagMode == TagView.MODE_CHANGE) {
                    if (isChange) {
                        mTagLayout2.updateTags(TagWordFactory.TAG_WORD);
                    } else {
                        mTagLayout2.updateTags(TagWordFactory.TAG_WORD_2);
                    }
                    isChange = !isChange;
                } else {
                    Toast(text);
                }
            }
        });
        mTagLayout3.setTagClickListener(new TagView.OnTagClickListener() {
            boolean isChange = false;

            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                if (tagMode == TagView.MODE_CHANGE) {
                    if (isChange) {
                        mTagLayout3.updateTags(TagWordFactory.TAG_WORD);
                    } else {
                        mTagLayout3.updateTags(TagWordFactory.TAG_WORD_2);
                    }
                    isChange = !isChange;
                } else {
                    Toast(text);
                }
            }
        });
        mTagLayout1.setTagLongClickListener(this);
        mTagLayout2.setTagLongClickListener(this);
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
                mTagLayout1.deleteTag(0);
                mTagLayout2.deleteTag(0);
                mTagLayout3.deleteTag(0);
            }
        });

        mTagLayout1.setTags(TagWordFactory.TAG_WORD);
        mTagLayout2.setTags(TagWordFactory.TAG_WORD);
        mTagLayout3.setTags(TagWordFactory.TAG_WORD);
    }

    @Override
    public void onTagLongClick(int position, String text, @TagView.TagMode int tagMode) {
        Toast("长按:" + text);
    }

	

}
