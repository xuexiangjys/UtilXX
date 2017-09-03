package com.example.mycustomview.custom;

import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.validate.IValidateResult;
import com.xuexiang.util.validate.Validate;
import com.xuexiang.util.validate.ValidateAnimation;
import com.xuexiang.util.validate.annotation.Index;
import com.xuexiang.util.validate.annotation.MaxLength;
import com.xuexiang.util.validate.annotation.MinLength;
import com.xuexiang.util.validate.annotation.Money;
import com.xuexiang.util.validate.annotation.NotNull;
import com.xuexiang.util.validate.annotation.Password1;
import com.xuexiang.util.validate.annotation.Password2;
import com.xuexiang.util.validate.annotation.RE;

public class ValidateUIActivity extends BaseHeadActivity implements IValidateResult {

	@Index(1)
	@NotNull(msg = "不能为空！")
	@Bind(R.id.et_notnull)
	EditText etNotnull;

	@Index(3)
	@Bind(R.id.et_max)
	@MaxLength(length = 3, msg = "超出最大长度")
	EditText et_max;

	@Index(2)
	@Bind(R.id.et_min)
	@MinLength(length = 3, msg = "错误，字符数目不够")
	EditText et_min;

	@Index(4)
	@NotNull(msg = "两次密码验证->密码一不为能空！")
	@Password1()
	@Bind(R.id.et_pw1)
	EditText etPw1;

	@Index(5)
	@NotNull(msg = "两次密码验证->密码二不为能空！")
	@Password2(msg = "两次密码不一致！！！")
	@Bind(R.id.et_pw2)
	EditText etPw2;

	@Index(10)
	@Money(msg = "格式不正确，请重新输入", keey = 2)
	@Bind(R.id.et_money)
	EditText etMoney;

	@Index(11)
	@RE(re = RE.only_Chinese, msg = "仅可输入中文，请重新输入")
	@Bind(R.id.et_only_Chinese)
	EditText etOnlyChinese;

	@Index(12)
	@RE(re = RE.only_number, msg = "仅可输入数字，请重新输入")
	@Bind(R.id.et_only_number)
	EditText etOnlyNumber;

	@Index(13)
	@RE(re = RE.number_letter_underline, msg = "仅可输入 数字 字母 下划线")
	@Bind(R.id.et_number_letter_underline)
	EditText etNumberLetterUnderline;

	@Index(14)
	@RE(re = RE.email, msg = "请输入正确的邮箱")
	@Bind(R.id.et_email)
	EditText etEmail;

	@Index(15)
	@NotNull(msg = "tv不能为空")
	@Bind(R.id.tv_textview)
	TextView tvTextview;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_validateui;
	}

	@Override
	protected void init() {
		ButterKnife.bind(this);

		Validate.reg(this);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Validate.check(ValidateUIActivity.this, ValidateUIActivity.this);
			}
		});

		// initView();
	}

	private void initView() {
		etNotnull.setText("1209101049@qq.com");
		etPw1.setText("111");
		etPw2.setText("111");
		et_max.setText("11");
		et_min.setText("1111");
		etEmail.setText("1209101049@qq.com");
		etMoney.setText("10.21");
		etOnlyChinese.setText("重");
		etOnlyNumber.setText("1");
		etNumberLetterUnderline.setText("1");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Validate.unreg(this);
	}

	@Override
	public void onValidateSuccess() {
		Toast("验证成功");
	}

	@Override
	public void onValidateError(String msg, EditText editText) {
		if (editText != null)
			editText.setFocusable(true);

		Toast(msg);
	}

	@Override
	public Animation onValidateErrorAnno() {
		return ValidateAnimation.horizontalTranslate();
	}

	@OnClick({ R.id.btn_setTxt_tv, R.id.btn_clear_tv })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_setTxt_tv:
			tvTextview.setText("测试文字");
			break;
		case R.id.btn_clear_tv:
			tvTextview.setText("");
			break;
		}
	}

}
