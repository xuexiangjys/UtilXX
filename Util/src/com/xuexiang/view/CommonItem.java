package com.xuexiang.view;

import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by zhen on 2016/8/23.
 */
public class CommonItem extends RelativeLayout {

    //left icon
    private Drawable mIconImg;

	//text
    private String mText;

    //textcolor
    private int mTextColor;

    //textsize
    private float mTextSize;

    //itme background
    private int mBackgroud;

    //right
    private Drawable mArrowRes;
    //root
    private RelativeLayout mRootView;

    //img -icon
    private ImageView mImageView;

    //arrow
    private ImageView mArrow;

	//text-img
    private TextView mTextView;

	public CommonItem(Context context) {
        super(context);
    }

    public CommonItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRootView = (RelativeLayout) LayoutInflater.from(context).inflate(RUtils.getLayout(context, "layout_common_item"), this);
        initView(context);
        initAttrs(context, attrs);
    }

    /**
     * get Attrs
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "CommonItem"));

        int mImgResid = a.getResourceId(RUtils.getStyleableAttribute(context, "CommonItem_img_item"), 0);
        int mArrowResid = a.getResourceId(RUtils.getStyleableAttribute(context, "CommonItem_arrow_item"), 0);
        int mTextResid = a.getResourceId(RUtils.getStyleableAttribute(context, "CommonItem_text_item"), 0);
        int mTextColorResid = a.getResourceId(RUtils.getStyleableAttribute(context, "CommonItem_textcolor_item"), 0);
        int mTextSizeResid = a.getResourceId(RUtils.getStyleableAttribute(context, "CommonItem_textsize_item"), 0);
        int mBackgroundResid = a.getResourceId(RUtils.getStyleableAttribute(context, "CommonItem_background_item"), 0);

        if (mImgResid != 0) {
            mIconImg = context.getResources().getDrawable(mImgResid);
        }

        if (mTextResid != 0) {
            mText = context.getResources().getString(mTextResid);
        }

        if (mTextColorResid != 0) {
            mTextColor = context.getResources().getColor(mTextColorResid);
        }

        if (mTextSizeResid != 0) {
            mTextSize = context.getResources().getDimension(mTextSizeResid);
        }

        if (mArrowResid != 0) {
            mArrowRes = context.getResources().getDrawable(mArrowResid);
        }

        if (mBackgroundResid != 0) {
            mBackgroud = context.getResources().getColor(mBackgroundResid);
        }

        a.recycle();

        mTextView.setText(mText);
        mTextView.setTextColor(mTextColor);
        mTextView.setTextSize(mTextSize);

        mImageView.setBackground(mIconImg);

        mArrow.setBackground(mArrowRes);

        setBackgroundColor(mBackgroud);
    }

    /**
     * init views
     *
     * @param context
     */
    private void initView(Context context) {
        mImageView = (ImageView) mRootView.findViewById(RUtils.getId(context, "image"));
        mArrow = (ImageView) mRootView.findViewById(RUtils.getId(context, "icon"));
        mTextView = (TextView) mRootView.findViewById(RUtils.getId(context, "text"));
    }
    
    public Drawable getIconImg() {
		return mIconImg;
	}

	public void setIconImg(Drawable iconImg) {
		this.mIconImg = iconImg;
	}
    
    public Drawable getArrowRes() {
		return mArrowRes;
	}

	public void setArrowRes(Drawable arrowRes) {
		this.mArrowRes = arrowRes;
	}

	public ImageView getImageView() {
		return mImageView;
	}

	public void setImageView(ImageView imageView) {
		this.mImageView = imageView;
	}

	public ImageView getArrow() {
		return mArrow;
	}

	public void setArrow(ImageView arrow) {
		this.mArrow = arrow;
	}
	
    public TextView getTextView() {
		return mTextView;
	}

	public void setTextView(TextView textView) {
		this.mTextView = textView;
	}

}
