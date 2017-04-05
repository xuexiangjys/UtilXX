package com.example.mycustomview.custom;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.ListSimpleActivity;
import com.xuexiang.view.toast.styleabletoast.StyleableToast;

/**
 * 创建时间：2017-3-2 上午12:26:36 项目名称：MyCustomView
 * 
 * @author xuexiang 文件名称：StyleabletToast.java
 **/
public class StyleabletToastActivity extends ListSimpleActivity {

	@Override
	protected List<String> initSimpleData() {
		List<String> list = new ArrayList<String>();
		list.add("动画图标 TOAST");
		list.add("静态图标 TOAST");
		list.add("自定义字体 TOAST");
		list.add("自定义字体样式和圆角 TOAST");
		list.add("风格style TOAST");
		return list;
	}

	@Override
	protected void onItemClick(int position) {
		StyleableToast st;
		switch (position) {
		case 0:
			st = new StyleableToast(this, "Updating profile", Toast.LENGTH_LONG);
			st.setBackgroundColor(Color.parseColor("#ff5a5f"));
			st.setTextColor(Color.WHITE);
			st.setIcon(R.drawable.ic_autorenew_black_24dp);
			st.spinIcon();
			st.setMaxAlpha();
			st.show();
			break;
		case 1:
			st = new StyleableToast(this, "Turn off fly mode", Toast.LENGTH_LONG);
			st.setBackgroundColor(Color.parseColor("#865aff"));
			st.setTextColor(Color.WHITE);
			st.setIcon(R.drawable.ic_airplanemode_inactive_black_24dp);
			st.show();

			break;
		case 2:
			st = new StyleableToast(this, "Profile saved", Toast.LENGTH_LONG);
			st.setBackgroundColor(Color.parseColor("#3b5998"));
			st.setMaxAlpha();
			st.setTextFont(Typeface.createFromAsset(getAssets(), "fonts/jianti_songti.ttf"));
			st.show();
			break;
		case 3:
			st = new StyleableToast(this.getApplicationContext(), "PHONE IS OVERHEATING!", Toast.LENGTH_LONG);
			st.setCornerRadius(5);
			st.setBackgroundColor(Color.BLACK);
			st.setTextColor(Color.RED);
			st.setBoldText();
			st.show();
			break;
		case 4:
			StyleableToast.makeText(this, "Picture saved in gallery", Toast.LENGTH_LONG, R.style.StyleableToast).show();
			break;
		default:
			break;
		}

	}

}
