package com.example.testutil.view;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.AndroidSegmentedControlView;
import com.xuexiang.view.AndroidSegmentedControlView.OnSelectionChangedListener;
import com.xuexiang.view.BadgeView;
import com.xuexiang.view.ListEditText;
import com.xuexiang.view.ShoppingView;
import com.xuexiang.view.SlideSwitch;
import com.xuexiang.view.SlideSwitch.SlideListener;
import com.xuexiang.view.SmoothCheckBox;
import com.xuexiang.view.ThumbUpView;
import com.xuexiang.view.margicbutton.MagicButton;

/**
 * 创建时间：2016-5-29 下午6:37:39 项目名称：TestUtil
 * 
 * @author xuexiang 文件名称：AndroidSegmentedControlViewActivity.java
 **/
public class CustomViewActivity extends BaseActivity {
	private Button btnPosition, btnColour, btnAnim1, btnAnim2, btnCustom,
			btnClick, btnTab, btnIncrement;
	private BadgeView badge1, badge2, badge3, badge4, badge5, badge6, badge7,
			badge8;
	private ListEditText mListEditText;
	private ThumbUpView tpv1, tpv2, tpv3;
	private TextView tv1, tv2, tv3;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_customview);

		initActionBar("开关");

		initview();
	}

	private void initview() {
		initAndroidSegmentedControlView();

		initSlideSwitch();

		initSmoothCheckBox();

		initShoppingView();

		initViewBadge();

		initMargicButton();

		initListEditText();
		
		initThumbUpView();
	}

	private void initAndroidSegmentedControlView() {
		LinearLayout holder = (LinearLayout) findViewById(R.id.ascv_sample_holder);
		try {
			AndroidSegmentedControlView ascv = new AndroidSegmentedControlView(
					this);
			ascv.setColors(Color.parseColor("#0066CC"),
					Color.parseColor("#FFFFFF"));
			ascv.setItems(new String[] { "Test1", "Test2", "Test3" },
					new String[] { "1", "2", "3" });
			ascv.setDefaultSelection(0);
			holder.addView(ascv);
			ascv.setOnSelectionChangedListener(new OnSelectionChangedListener() {
				@Override
				public void newSelection(String identifier, String value) {
					mToastUtil.showToast("identifier:" + identifier
							+ ", value:" + value);
				}
			});

			AndroidSegmentedControlView ascv2 = new AndroidSegmentedControlView(
					this);
			ascv2.setColors(Color.parseColor("#D24E4E"),
					Color.parseColor("#FFFFFF"));
			ascv2.setStretch(true);
			ascv2.setItems(new String[] { "Test4", "Test5", "Test6" },
					new String[] { "4", "5", "6" });
			ascv2.setDefaultSelection(2);
			ascv2.setOnSelectionChangedListener(new OnSelectionChangedListener() {
				@Override
				public void newSelection(String identifier, String value) {
					mToastUtil.showToast("identifier:" + identifier
							+ ", value:" + value);
				}
			});

			holder.addView(ascv2);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initSlideSwitch() {
		SlideSwitch switch_notification = (SlideSwitch) findViewById(R.id.switch_notification);
		switch_notification.setSlideListener(new SlideListener() {
			@Override
			public void open() {
				Toast("消息推送：开");

			}

			@Override
			public void close() {

				Toast("消息推送：关");
			}
		});

	}

	private void initSmoothCheckBox() {
		SmoothCheckBox scb = (SmoothCheckBox) findViewById(R.id.scb);
		scb.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(SmoothCheckBox checkBox,
					boolean isChecked) {
				mToastUtil.showToast("isChecked:" + isChecked);
			}
		});
	}

	private void initShoppingView() {
		ShoppingView mSv1 = (ShoppingView) findViewById(R.id.sv_1);
		mSv1.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
			@Override
			public void onAddClick(int num) {
				mToastUtil.showToast("add.......num=> " + num);
			}

			@Override
			public void onMinusClick(int num) {
				mToastUtil.showToast("minus.......num=> " + num);
			}
		});

		ShoppingView mSv2 = (ShoppingView) findViewById(R.id.sv_2);
		mSv2.setTextNum(1);
	}

	private void initViewBadge() {
		btnPosition = (Button) findViewById(R.id.position_target);
		badge1 = new BadgeView(this, btnPosition);
		badge1.setText("12");
		badge1.setBadgePosition(BadgeView.POSITION_CENTER);
		btnPosition.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge1.toggle();
			}
		});

		// *** badge/text size & colour ***

		btnColour = (Button) findViewById(R.id.colour_target);
		badge2 = new BadgeView(this, btnColour);
		badge2.setText("New!");
		badge2.setTextColor(Color.BLUE);
		badge2.setBadgeBackgroundColor(Color.YELLOW);
		badge2.setTextSize(12);
		btnColour.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge2.toggle();
			}
		});

		// *** default animation ***

		btnAnim1 = (Button) findViewById(R.id.anim1_target);
		badge3 = new BadgeView(this, btnAnim1);
		badge3.setText("84");
		btnAnim1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge3.toggle(true);
			}
		});

		// *** custom animation ***

		btnAnim2 = (Button) findViewById(R.id.anim2_target);
		badge4 = new BadgeView(this, btnAnim2);
		badge4.setText("123");
		badge4.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
		badge4.setBadgeMargin(15, 10);
		badge4.setBadgeBackgroundColor(Color.parseColor("#A4C639"));
		btnAnim2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TranslateAnimation anim = new TranslateAnimation(-100, 0, 0, 0);
				anim.setInterpolator(new BounceInterpolator());
				anim.setDuration(1000);
				badge4.toggle(anim, null);
			}
		});

		// *** custom background ***

		btnCustom = (Button) findViewById(R.id.custom_target);
		badge5 = new BadgeView(this, btnCustom);
		badge5.setText("37");
		badge5.setBackgroundResource(R.drawable.badge_ifaux);
		badge5.setTextSize(16);
		btnCustom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge5.toggle(true);
			}
		});

		// *** clickable badge ***

		btnClick = (Button) findViewById(R.id.click_target);
		badge6 = new BadgeView(this, btnClick);
		badge6.setText("click me");
		badge6.setBadgeBackgroundColor(Color.BLUE);
		badge6.setTextSize(16);
		badge6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast("clicked badge");
			}
		});
		btnClick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge6.toggle();
			}
		});

		// *** increment ***

		btnIncrement = (Button) findViewById(R.id.increment_target);
		badge8 = new BadgeView(this, btnIncrement);
		badge8.setText("0");
		btnIncrement.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (badge8.isShown()) {
					badge8.increment(1);
				} else {
					badge8.show();
				}
			}
		});
	}

	private void initMargicButton() {
		MagicButton mMagicButton = (MagicButton) findViewById(R.id.magic_button_github);
		mMagicButton.setMagicButtonClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mToastUtil.showToast("点击了Github！");
			}
		});
	}

	private void initListEditText() {
		mListEditText = (ListEditText) findViewById(R.id.listedittext);
		String[] mValuesHuman = { "1", "2", "3" }; // 用于显示的值
		String[] mValuesMachine = { "a", "b", "c" }; // 最终点击后对应的值
		mListEditText
				.init(mContext, mValuesHuman, mValuesMachine,
						R.string.ListEditText_title,
						R.string.ListEditText_cancel, true);
	}
	
	private void initThumbUpView() {
		tpv1 = (ThumbUpView) findViewById(R.id.tpv1);
        tpv2 = (ThumbUpView) findViewById(R.id.tpv2);
        tpv3 = (ThumbUpView) findViewById(R.id.tpv3);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);


        tpv3.setUnLikeType(ThumbUpView.LikeType.broken);
        tpv3.setCracksColor(Color.WHITE);
        tpv3.setFillColor(Color.rgb(11, 200, 77));
        tpv3.setEdgeColor(Color.rgb(33, 3, 219));
        tpv3.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
                if (like) {
                    tv3.setText(String.valueOf(Integer.valueOf(tv3.getText().toString()) + 1));
                } else {

                    tv3.setText(String.valueOf(Integer.valueOf(tv3.getText().toString()) - 1));
                }
            }
        });

        tpv2.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
                if (like) {
                    tv2.setText(String.valueOf(Integer.valueOf(tv2.getText().toString()) + 1));
                } else {

                    tv2.setText(String.valueOf(Integer.valueOf(tv2.getText().toString()) - 1));
                }
            }
        });
        tpv1.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
                if (like) {
                    tv1.setText(String.valueOf(Integer.valueOf(tv1.getText().toString()) + 1));
                } else {
                    tv1.setText(String.valueOf(Integer.valueOf(tv1.getText().toString()) - 1));
                }
            }
        });
	}

	public void like(View v) {
		tpv1.Like();
		tpv2.Like();
		tpv3.Like();

	}

	public void unlike(View v) {
		tpv1.UnLike();
		tpv2.UnLike();
		tpv3.UnLike();
	}

}
