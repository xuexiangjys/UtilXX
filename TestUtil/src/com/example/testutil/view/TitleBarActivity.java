package com.example.testutil.view;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.TitleBar;
import com.xuexiang.view.popwindow.ActionItem;
import com.xuexiang.view.popwindow.PopWindow;
import com.xuexiang.view.popwindow.PopWindow.OnItemSelectedListerner;
import com.xuexiang.view.popwindow.TitlePopup.OnItemOnClickListener;

/**  
 * 创建时间：2016-6-8 下午9:49:23  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：TitleBarActivity.java  
 **/
public class TitleBarActivity extends BaseActivity implements OnClickListener {

//	 private ImageView mCollectView;
//	 private boolean mIsSelected;
	 ArrayList<ActionItem> Itemlist = new ArrayList<ActionItem>();
	    
	 private Button btShare;
     private PopWindow mPopWindow;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_titlebar);
		
//		initTitleBar("TitleBarActivity");
		
		btShare = (Button) findViewById(R.id.bt_share);
        btShare.setOnClickListener(this);
        mPopWindow = new PopWindow(this,"分享");
		
		initActionItem();
		initTitleBarWithRightMenu("TitleBarActivity", Itemlist, new OnItemOnClickListener() {
			@Override
			public void onItemClick(ActionItem item, int position) {
				Toast("你点击了"+ item.mTitle);
			}
		});
		
//		initActionBar();
	}
	private void initActionItem() {
		Itemlist.add(new ActionItem(mContext, "首页", R.drawable.mm_title_btn_home_normal));
		Itemlist.add(new ActionItem(mContext, "购物车", R.drawable.mm_title_btn_cart_normal));
		Itemlist.add(new ActionItem(mContext, "扫一扫",  R.drawable.mm_title_btn_qrcode_normal));
		Itemlist.add(new ActionItem(mContext, "我的乐居", R.drawable.mm_title_btn_account_normal));
	}

	private void initActionBar() {
		  
//	        mCollectView = (ImageView) mTitleBar.addAction(new TitleBar.ImageAction(R.drawable.collect) {
//	            @Override
//	            public void performAction(View view) {
//	                Toast("点击了收藏");
//	                mCollectView.setImageResource(R.drawable.publish);
//	                mTitleBar.setTitle(mIsSelected ? "文章详情\n朋友圈" : "帖子详情");
//	                mIsSelected = !mIsSelected;
//	            }
//	        });

	        mTitleBar.addAction(new TitleBar.TextAction("发布") {
	            @Override
	            public void performAction(View view) {
	                Toast("点击了发布");
	            }
	        });
	}
	
	 private List<ActionItem> getShareInfo() {
	    	List<ActionItem> list = new ArrayList<ActionItem>();
	    	list.add(new ActionItem(mContext, "微信", R.drawable.share_weixin));
	    	list.add(new ActionItem(mContext, "朋友圈", R.drawable.share_momment));
	    	list.add(new ActionItem(mContext, "新浪微博", R.drawable.share_sina));
	    	list.add(new ActionItem(mContext, "QQ", R.drawable.share_qq));
	    	list.add(new ActionItem(mContext, "QQ空间", R.drawable.share_qzeon));
	    	list.add(new ActionItem(mContext, "短信", R.drawable.share_message));   	
	    	return list;
	 }
	@Override
	public void onClick(View v) {
		mPopWindow.setShareInfo(getShareInfo());
    	mPopWindow.setOnItemSelectedListerner(new OnItemSelectedListerner(){
			@Override
			public void onSelected(ActionItem shareInfo) {
				Toast.makeText(getApplicationContext(), "你点击了：" + shareInfo.mTitle, Toast.LENGTH_SHORT).show();
				//mPopWindow.hide();
			}
		});
    	mPopWindow.show();
	}

}
