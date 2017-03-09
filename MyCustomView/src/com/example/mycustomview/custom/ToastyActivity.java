package com.example.mycustomview.custom;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.ListSimpleActivity;
import com.xuexiang.view.toast.toasty.Toasty;


/**  
 * 创建时间：2017-3-2 上午12:13:10
 * 项目名称：MyCustomView  
 * @author xuexiang
 * 文件名称：ToastActivity.java 
 **/
public class ToastyActivity extends ListSimpleActivity {

	@Override
	protected List<String> initSimpleData() {
		List<String> list = new ArrayList<String>();
		list.add("ERROR TOAST");
		list.add("SUCCESS TOAST");
		list.add("INFO TOAST");
		list.add("WARNING TOAST");
		list.add("NORMAL TOAST (WITHOUT ICON)");
		list.add("NORMAL TOAST (WITH ICON)");
		return list;
	}

	@Override
	protected void onItemClick(int position) {
		switch (position) {
		case 0:
			Toasty.error(mContext, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
			break;
		case 1:
			Toasty.success(mContext, "Success!", Toast.LENGTH_SHORT, true).show();
			break;
		case 2:
            Toasty.info(mContext, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
			break;
		case 3:
            Toasty.warning(mContext, "Beware of the dog.", Toast.LENGTH_SHORT, true).show();
			break;
		case 4:
            Toasty.normal(mContext, "Normal toast w/o icon").show();
			break;
		case 5:
			 Drawable icon = getResources().getDrawable(R.drawable.ic_pets_white_48dp);
             Toasty.normal(mContext, "Normal toast w/ icon", icon).show();
			break;
		default:
			break;
		}
	}

}
