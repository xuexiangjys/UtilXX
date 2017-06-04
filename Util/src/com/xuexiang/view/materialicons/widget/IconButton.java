package com.xuexiang.view.materialicons.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.materialicons.Iconify;

public class IconButton extends Button {

    public IconButton(Context context) {
        super(context);
        init(null);
    }

    public IconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public IconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        boolean hackyPreview = false;
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, RUtils.getStyleable(getContext(), "IconButton"), 0, 0);
        try {
            hackyPreview = a.getBoolean(RUtils.getStyleableAttribute(getContext(), "IconButton_hacky_preview"), false);
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
