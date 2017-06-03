package com.example.mycustomview.indicatordialog;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.indicatordialog.IndicatorBuilder;
import com.xuexiang.view.indicatordialog.IndicatorDialog;

public class IndicatorDialogActivity extends BaseHeadActivity {

    private List<ActionItem> mLists = new ArrayList<>();
    private ImageView mAdd;
    private ImageView mAddLeft;
    private ImageView mAddCenter;
    private ImageView mBottom3;
    private ImageView mBottom2;
    private ImageView mBottom1;

    IndicatorDialog topDialog;
    IndicatorDialog bottomDialog;
    
	@Override
	protected int getLayoutId() {
		return R.layout.activity_indicatordialog;
	}

	@Override
	protected void init() {
		initView();

        mAddLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTopDialog(v, 0.1f, IndicatorBuilder.GRAVITY_LEFT);
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTopDialog(v, 0.9f, IndicatorBuilder.GRAVITY_RIGHT);
            }
        });

        mAddCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopDialog(v, 0.5f, IndicatorBuilder.GRAVITY_CENTER);
            }
        });


        mBottom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v, 0.1f, IndicatorBuilder.GRAVITY_LEFT);

            }
        });
        mBottom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v, 0.5f, IndicatorBuilder.GRAVITY_CENTER);

            }
        });
        mBottom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v, 0.9f, IndicatorBuilder.GRAVITY_RIGHT);

            }
        });
	}

    private void showBottomDialog(View v, float v1, int gravityCenter) {
        mLists.clear();
        mLists.add(new ActionItem("创建群聊", R.drawable.ic_chat_bubble_outline_black_24dp_white));
        mLists.add(new ActionItem("加好友", R.drawable.ic_child_friendly_black_24dp_white));
        mLists.add(new ActionItem("扫一扫", R.drawable.ic_settings_bluetooth_black_24dp_white));
        mLists.add(new ActionItem("面对面快传", R.drawable.ic_autorenew_black_24dp_white));
        mLists.add(new ActionItem("付款", R.drawable.ic_monetization_on_black_24dp_white));
        mLists.add(new ActionItem("拍摄", R.drawable.ic_camera_black_24dp_white));
        mLists.add(new ActionItem("面对面红包", R.drawable.ic_attach_money_black_24dp_white));
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        bottomDialog = new IndicatorBuilder(this)
                .width(400)
                .height((int) (height * 0.5))
                .ArrowDirection(IndicatorBuilder.BOTTOM)
                .bgColor(Color.parseColor("#49484b"))
                .gravity(gravityCenter)
                .ArrowRectage(v1)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(new IndicatorDialogAdapter(this, mLists) {
					@Override
					public int getLayoutID(int position) {
						return R.layout.adapter_indicator_dialog_item2;
					}
					
					@Override
					public void onItemClick(View v, int position) {
						bottomDialog.dismiss();
						Toast.makeText(getApplicationContext(), "点击了第" + (position + 1) + "个！", Toast.LENGTH_SHORT).show();
					}
				}).create();
        
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.show(v);
    }


    private void showTopDialog(View v, float v1, int gravityCenter) {
    	mLists.add(new ActionItem("创建群聊", R.drawable.ic_chat_bubble_outline_black_24dp));
        mLists.add(new ActionItem("加好友", R.drawable.ic_child_friendly_black_24dp));
        mLists.add(new ActionItem("扫一扫", R.drawable.ic_settings_bluetooth_black_24dp));
        mLists.add(new ActionItem("面对面快传", R.drawable.ic_autorenew_black_24dp));
        mLists.add(new ActionItem("付款", R.drawable.ic_monetization_on_black_24dp));
        mLists.add(new ActionItem("拍摄", R.drawable.ic_camera_black_24dp));
        mLists.add(new ActionItem("面对面红包", R.drawable.ic_attach_money_black_24dp));
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        topDialog = new IndicatorBuilder(this)
                .width(400)
                .height((int) (height * 0.5))
                .ArrowDirection(IndicatorBuilder.TOP)
                .bgColor(Color.WHITE)
                .gravity(gravityCenter)
                .ArrowRectage(v1)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(new IndicatorDialogAdapter(this, mLists) {
                    @Override
                    public int getLayoutID(int position) {
                        return R.layout.adapter_indicator_dialog_item;
                    }
                    @Override
                    public void onItemClick(View v, int position) {
                    	topDialog.dismiss();
                    	Toast.makeText(getApplicationContext(), "点击了第" + (position + 1) + "个！", Toast.LENGTH_SHORT).show();
                    }

                }).create();

        topDialog.setCanceledOnTouchOutside(true);
        topDialog.show(v);

    }

    private void initView() {
        mAdd = $(R.id.activity_add);
        mAddLeft = $(R.id.activity_add_left);
        mAddCenter = $(R.id.activity_add_center);
        mBottom1 = $(R.id.activity_add_bottom_1);
        mBottom2 = $(R.id.activity_add_bottom_2);
        mBottom3 = $(R.id.activity_add_bottom_3);
        mAdd.setClickable(true);
        mAddLeft.setClickable(true);
        mAddCenter.setClickable(true);
        mBottom1.setClickable(true);
        mBottom2.setClickable(true);
        mBottom3.setClickable(true);
    }



}
