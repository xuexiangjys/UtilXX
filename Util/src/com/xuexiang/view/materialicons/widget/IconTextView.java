package com.xuexiang.view.materialicons.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.materialicons.Iconify;

public class IconTextView extends TextView {

    public IconTextView(Context context) {
        super(context);
        init(null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        boolean hackyPreview = false;
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, RUtils.getStyleable(getContext(), "IconTextView"), 0, 0);
        try {
            hackyPreview = a.getBoolean(RUtils.getStyleableAttribute(getContext(), "IconTextView_hacky_preview"), false);
        } finally {
            a.recycle();
        }

        if (!isInEditMode())
            Iconify.addIcons(this);
        else if (hackyPreview) {
            Iconify.addIconsEditMode(this);
        } else {
            this.setText(this.getText());
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(Iconify.compute(text), type);
    }

}
