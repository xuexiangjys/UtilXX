package com.xuexiang.view;

import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * CustomTextView
 *
 * @author Qiugang & jiaowenzheng
 */
public class CustomTextView extends TextView {

    private GradientDrawable drawable;

    public CustomTextView(Context context) {
        super(context);

    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributeSet(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributeSet(context, attrs);
    }


    private void setAttributeSet(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "customTextView"));
        int normalSolidColor = a.getColor(MResource.getIdByName(context, "styleable", "customTextView_textSolidColor"), Color.TRANSPARENT);
        int selectedSolidColor = a.getColor(MResource.getIdByName(context, "styleable", "customTextView_textSelectedSolidColor"), Color.TRANSPARENT);
        int strokeColor = a.getColor(MResource.getIdByName(context, "styleable", "customTextView_textStrokeColor"), Color.TRANSPARENT);
        int radius = a.getDimensionPixelSize(MResource.getIdByName(context, "styleable", "customTextView_textRadius"), 0);
        int leftTopRadius = a.getDimensionPixelSize(MResource.getIdByName(context, "styleable", "customTextView_textLeftTopRadius"), 0);
        int leftBottomRadius = a.getDimensionPixelSize(MResource.getIdByName(context, "styleable", "customTextView_textLeftBottomRadius"), 0);
        int rightTopRadius = a.getDimensionPixelSize(MResource.getIdByName(context, "styleable", "customTextView_textRightTopRadius"), 0);
        int rightBottomRadius = a.getDimensionPixelSize(MResource.getIdByName(context, "styleable", "customTextView_textRightBottomRadius"), 0);
        int strokeWidth = a.getDimensionPixelSize(MResource.getIdByName(context, "styleable", "customTextView_textStrokeWidth"), 0);
        Drawable textDrawable = a.getDrawable(MResource.getIdByName(context, "styleable", "customTextView_textDrawable"));
        int normalTextColor = a.getColor(MResource.getIdByName(context, "styleable", "customTextView_textNormalTextColor"), Color.TRANSPARENT);
        int selectedTextColor = a.getColor(MResource.getIdByName(context, "styleable", "customTextView_textSelectedTextColor"), Color.TRANSPARENT);


        a.recycle();

        drawable = new GradientDrawable();
        drawable.setStroke(strokeWidth, strokeColor);
        
        if (normalSolidColor != 0 && selectedSolidColor != 0) {
            //璁剧疆state_selected鐘舵�佹椂锛屽拰姝ｅ父鐘舵�佹椂鏂囧瓧鐨勯鑹�
            int[][] states = new int[3][1];
            states[0] = new int[]{android.R.attr.state_selected};
            states[1] = new int[]{android.R.attr.state_pressed};
            states[2] = new int[]{};
            ColorStateList textColorSelect = new ColorStateList(states, new int[]{selectedSolidColor, selectedSolidColor, normalSolidColor});
            drawable.setColor(textColorSelect);
        } else if (normalSolidColor != 0 ) {
        	drawable.setColor(normalSolidColor);
        }
        
        if (radius > 0) {
            drawable.setCornerRadius(radius);
        } else if (leftTopRadius > 0 || leftBottomRadius > 0 || rightTopRadius > 0 || rightBottomRadius > 0) {
            drawable.setCornerRadii(new float[]{leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius});
        }

        setBackgroundDrawable(drawable);


        if (textDrawable != null) {
            BitmapDrawable bd = (BitmapDrawable) textDrawable;
            ImageSpan imageSpan = new ImageSpan(getContext(), bd.getBitmap());

            String text = "[icon]";
            SpannableString ss = new SpannableString("[icon]");

            ss.setSpan(imageSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(ss);
        }


        if (normalTextColor != 0 && selectedTextColor != 0) {
            //璁剧疆state_selected鐘舵�佹椂锛屽拰姝ｅ父鐘舵�佹椂鏂囧瓧鐨勯鑹�
            int[][] states = new int[3][1];
            states[0] = new int[]{android.R.attr.state_selected};
            states[1] = new int[]{android.R.attr.state_pressed};
            states[2] = new int[]{};
            ColorStateList textColorSelect = new ColorStateList(states, new int[]{selectedTextColor, selectedTextColor, normalTextColor});
            setTextColor(textColorSelect);
        } 
        
        if (selectedTextColor != 0 || selectedSolidColor != 0) {
        	setClickable(true);
        } else {
        	setClickable(false);
        }
    }


    /**
     * 璁剧疆濉厖鍥剧墖
     *
     * @param drawableId  drawable id
     */
    public void setTextDrawable(int drawableId) {
        if (drawableId != 0) {
            Drawable textdrwable = getResources().getDrawable(drawableId);
            BitmapDrawable bd = (BitmapDrawable) textdrwable;
            ImageSpan imageSpan = new ImageSpan(getContext(), bd.getBitmap());

            String text = "[icon]";
            SpannableString ss = new SpannableString("[icon]");

            ss.setSpan(imageSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(ss);
        }
    }

    /**
     *
     * 璁剧疆濉厖棰滆壊
     *
     * @param colorId   棰滆壊id
     */
    public void setSolidColor(int colorId) {
        drawable.setColor(colorId);
        setBackgroundDrawable(drawable);
    }

    /**
     * 璁剧疆鍦嗚寮у害
     *
     * @param leftTopRadius         宸︿笂瑙掑姬搴�
     * @param leftBottomRadius      宸︿笅瑙掑姬搴�
     * @param rightTopRadius        鍙充笂瑙掑姬搴�
     * @param rightBottomRadius     鍙充笅瑙掑姬搴�
     */
    public void setRadius(int leftTopRadius, int leftBottomRadius, int rightTopRadius, int rightBottomRadius) {
        drawable.setCornerRadii(new float[]{leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius});
        setBackgroundDrawable(drawable);
    }

    /**
     * 璁剧疆杈规棰滆壊鍙婂搴�
     *
     * @param strokeWidth      杈规瀹藉害
     * @param colorId          杈规棰滆壊 id
     */
    public void setStrokeColorAndWidth(int strokeWidth,int colorId){
        drawable.setStroke(strokeWidth, colorId);
    }



    /**
     * 璁剧疆textView閫変腑鐘舵�侀鑹�
     *
     * @param normalTextColor     姝ｅ父鐘舵�侀鑹�
     * @param selectedTextColor   鎸変笅鐘舵�侀鑹�
     */
    public void setSelectedTextColor(int normalTextColor,int selectedTextColor) {
    	
        if (normalTextColor != 0 && selectedTextColor != 0) {
            //璁剧疆state_selected鐘舵�佹椂锛屽拰姝ｅ父鐘舵�佹椂鏂囧瓧鐨勯鑹�
            setClickable(true);
            int[][] states = new int[3][1];
            states[0] = new int[]{android.R.attr.state_selected};
            states[1] = new int[]{android.R.attr.state_pressed};
            states[2] = new int[]{};
            ColorStateList textColorSelect = new ColorStateList(states, new int[]{selectedTextColor, selectedTextColor, normalTextColor});
            setTextColor(textColorSelect);
        }else{
            setClickable(false);
        }

    }
    
    /**
     * 璁剧疆textView閫変腑鐘舵�侀鑹�
     *
     * @param normalTextColor     姝ｅ父鐘舵�侀鑹�
     * @param selectedTextColor   鎸変笅鐘舵�侀鑹�
     */
    public void setSelectedSolidColor(int normalSolidColor,int selectedSolidColor) {
    	if (normalSolidColor != 0 && selectedSolidColor != 0) {
            //璁剧疆state_selected鐘舵�佹椂锛屽拰姝ｅ父鐘舵�佹椂鏂囧瓧鐨勯鑹�
            setClickable(true);
            int[][] states = new int[3][1];
            states[0] = new int[]{android.R.attr.state_selected};
            states[1] = new int[]{android.R.attr.state_pressed};
            states[2] = new int[]{};
            ColorStateList textColorSelect = new ColorStateList(states, new int[]{selectedSolidColor, selectedSolidColor, normalSolidColor});
            drawable.setColor(textColorSelect);
        } else {
            setClickable(false);
        }
    }

}
