package com.example.mycustomview.taglayout;

import android.os.Handler;
import android.os.Message;
import android.util.SparseIntArray;

import com.example.mycustomview.R;
import com.example.mycustomview.taglayout.drawable.CircleDrawable;
import com.example.mycustomview.taglayout.drawable.MultiCircleDrawable;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.tag.TagView;

public class TagViewActivity extends BaseHeadActivity {

    private TagView mTagGoodOrBad;
    private TagView mTagRightOrError;
    private TagView mTagSmileOrCry;
    private TagView mTagGetCode;
    private TagView mTagSkip;
    private TagView mTagSkip2;
    private TagView mTagSingleCircle;
    private TagView mTagMultiCircle;

    private int mCountNum = 5;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mTagSkip2.setText("跳过 " + mCountNum);
            mTagSkip.setText((mCountNum--) + " 跳过");
            if (mCountNum == 0) {
                mCountNum = 5;
            }
            mHandler.postDelayed(this, 1000);
        }
    };
    private SparseIntArray mTimeSparse = new SparseIntArray();

    @Override
	protected int getLayoutId() {
		return R.layout.activity_tag_view;
	}

	@Override
	protected void init() {
		 initView();
	}

    private void initView() {
        mTagGoodOrBad = (TagView) findViewById(R.id.tag_good_or_bad);
        mTagRightOrError = (TagView) findViewById(R.id.tag_right_or_error);
        mTagSmileOrCry = (TagView) findViewById(R.id.tag_smile_or_cry);
        mTagGetCode = (TagView) findViewById(R.id.tag_get_code);
        mTagSkip = (TagView) findViewById(R.id.tag_skip);
        mTagSkip2 = (TagView) findViewById(R.id.tag_skip_2);
        mTagGoodOrBad.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_good_or_bad)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagGoodOrBad.setChecked(!mTagGoodOrBad.isChecked());
                    }
                }, 2000);
            }
        });
        mTagRightOrError.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_right_or_error)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagRightOrError.setChecked(!mTagRightOrError.isChecked());
                    }
                }, 2000);
            }
        });
        mTagSmileOrCry.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_smile_or_cry)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagSmileOrCry.setChecked(!mTagSmileOrCry.isChecked());
                    }
                }, 2000);
            }
        });

//        mTagGetCode.setTagClickListener(new TagView.OnTagClickListener() {
//            @Override
//            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
//                if (!mTagGetCode.isChecked()) {
//                    mTagGetCode.setChecked(true);
//                    RxHelper.countdown(10)
//                            .subscribe(new Subscriber<Integer>() {
//                                @Override
//                                public void onCompleted() {
//                                    mTagGetCode.setChecked(false);
//                                    mTagGetCode.setText("重新获取");
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    mTagGetCode.setChecked(false);
//                                    mTagGetCode.setText("重新获取");
//                                }
//
//                                @Override
//                                public void onNext(Integer time) {
//                                    mTagGetCode.setTextChecked("已发送(" + time + ")");
//                                }
//                            });
//                }
//            }
//        });

        mTagSkip.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                Toast(text);
            }
        });
        mTagSkip2.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                Toast(text);
            }
        });
        mHandler.postDelayed(mRunnable, 1000);
        //
        mTagSingleCircle = (TagView) findViewById(R.id.tag_single_circle);
        mTagSingleCircle.setDecorateIcon(new CircleDrawable());
        mTagMultiCircle = (TagView) findViewById(R.id.tag_multi_circle);
        mTagMultiCircle.setDecorateIcon(new MultiCircleDrawable());
    }

    private boolean _isClickedNow(int id) {
        int lastTime = mTimeSparse.get(id);
        boolean isClickedNow = false;
        int curTime = (int) (System.currentTimeMillis() % 1000000);
        if (curTime - lastTime < 2000) {
            isClickedNow = true;
        }
        mTimeSparse.put(id, curTime);
        return isClickedNow;
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

	
}
