package com.xuexiang.view.dropdownmenu.view;

import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Administrator on 2015/5/28.
 */
public class DropdownButton extends RelativeLayout {
    TextView textView;
    View bottomLine;

    public DropdownButton(Context context) {
        this(context, null);
    }

    public DropdownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view =  LayoutInflater.from(getContext()).inflate(RUtils.getLayout(getContext(), "dropdown_tab_button"),this, true);
        textView = (TextView) view.findViewById(RUtils.getId(getContext(), "textView"));
        bottomLine = view.findViewById(RUtils.getId(getContext(), "bottomLine"));
    }


    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setChecked(boolean checked) {
        Drawable icon;
        if (checked) {
            icon = getResources().getDrawable(RUtils.getDrawable(getContext(), "ic_dropdown_actived"));
            textView.setTextColor(getResources().getColor(RUtils.getColor(getContext(), "green")));
            bottomLine.setVisibility(VISIBLE);
        } else {
            icon = getResources().getDrawable(RUtils.getDrawable(getContext(), "ic_dropdown_normal"));
            textView.setTextColor(getResources().getColor(RUtils.getColor(getContext(), "font_black_content")));
            bottomLine.setVisibility(GONE);
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }


}
