package com.example.testutil.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.view.DialogUtil;
import com.xuexiang.util.view.DialogUtil.LoadingStyle;
import com.xuexiang.view.dialog.ConfirmDialog;
import com.xuexiang.view.dialog.ConfirmDialog.onConfirmDialogClickListener;
import com.xuexiang.view.dialog.CustomDialog;
import com.xuexiang.view.dialog.HoriztalProgressBarDialog;
import com.xuexiang.view.dialog.LoadingAnimatorDialog;
import com.xuexiang.view.dialog.LoadingView;
import com.xuexiang.view.dialog.RoundProgressBarDialog;
import com.xuexiang.view.dialog.confirmdialog.ConfirmFragment;
import com.xuexiang.view.dialog.confirmdialog.ConfirmLayout;
import com.xuexiang.view.dialog.confirmdialog.ConfirmPopWindow;
import com.xuexiang.view.dialog.confirmdialog.OnDialogClickListener;


/**  
 * 创建时间：2016-5-31 下午9:21:32  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：LoadingDialog.java  
 **/
public class LoadingDialogActivity extends BaseActivity implements OnClickListener, OnDialogClickListener{

	private LoadingView loadingview;
	private boolean flag = false;
	
	private ConfirmDialog mConfirmDialog;
	private Dialog mDialog;
	private ConfirmPopWindow mPopupWindow;
	private ConfirmLayout mConfirmLayout;
	private ConfirmFragment mConfirmFragment;
	
	private AlertDialog mAlertDialog;
	private RoundProgressBarDialog mRoundProgressBarDialog;
	private HoriztalProgressBarDialog mHoriztalProgressBarDialog;
	private int progress;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_loadingdialog);
        
	    initActionBar("加载框主界面");
	    
	    initView();
	}

	private void initView() {
		loadingview = (LoadingView) findViewById(R.id.loadView);
		loadingview.setVisibility(View.GONE);
		
		//mShapeLoadingDialog.setLoadingText("加载中..");
		mHandler = new Handler();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirmdialog:
			mConfirmDialog = new ConfirmDialog(mContext, "确定退出系统");
			mConfirmDialog.showDialog();
			
			mConfirmDialog.setOnClickListener(new onConfirmDialogClickListener(){
				@Override
				public void onClick(int mode) {
					if(mode == ConfirmDialog.CONFIRM){
						Toast("确定");
					} else{
						Toast( "取消");
					}
			}});
			break;
		case R.id.btn_confirmdialog1:
			mDialog = new com.xuexiang.view.dialog.confirmdialog.ConfirmDialog(mContext, "标题", "这里是ConfirmDialog的内容", new OnDialogClickListener(){					
				@Override
				public void onSubmit() {
					Toast("点击确定");
				}
				@Override
				public void onCancel() {
					Toast("点击取消");						
				}
			});
			mDialog.show();
			break;
			
		case R.id.btn_confirmpopwindowdialog:
			mPopupWindow = new ConfirmPopWindow(mContext, "标题", "这里是ConfirmPopWindow的内容", new OnDialogClickListener(){
				
				@Override
				public void onSubmit() {
					Toast("点击确定");
				}
				@Override
				public void onCancel() {
					Toast("点击取消");						
				}
			});
			mPopupWindow.showAtBottom(v);
			break;
			
		case R.id.btn_confirmlayoutdialog:
			mConfirmLayout = new ConfirmLayout(mContext, "标题", "这里是ConfirmLayout的内容", new OnDialogClickListener(){					
				@Override
				public void onSubmit() {
					Toast("点击确定");
				}
				@Override
				public void onCancel() {
					Toast("点击取消");						
				}
			});
			mConfirmLayout.show();
			break;
			
		case R.id.btn_confirmfragmentdialog:
			mConfirmFragment = new ConfirmFragment().newInstance("标题", "这里是ConfirmFragment的内容");
			mConfirmFragment.show(getSupportFragmentManager(), "ConfirmFragment");
			break;
			
		case R.id.btn_loading_dialog:	
//			mAlertDialog = new ShapeLoadingDialog(this, "加载中..");
//			mAlertDialog.show();
			mAlertDialog = DialogUtil.createLoadingDialog(mContext, "加载中..", LoadingStyle.ShapeLoading);
			mAlertDialog.show();
			break;
			
		case R.id.btn_loading_view:
			flag = !flag;
			loadingview.setVisibility(flag?View.VISIBLE:View.INVISIBLE);			
			break;
			
		case R.id.btn_loading_Animator_dialog:
			final LoadingAnimatorDialog dialog = new LoadingAnimatorDialog(this, "正在疯狂加载中...");
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();			
			ThreadPoolManager.getInstance().addTask(new Runnable() {  				  
		            @Override  
		            public void run() {  
		                try {  
		                    Thread.sleep(10000);  //10秒后关闭  
		                    mActivityManager.currentActivity().runOnUiThread(new Runnable() {    
		                        public void run() {    
		                        	dialog.dismiss();  
		                        }    		                
		                    });    		                     
		                } catch (InterruptedException e) {  
		                    e.printStackTrace();  
		                }  		  
		            }  
		        });  
			break;
			
		case R.id.btn_SpotsDialog:
//			mAlertDialog = new SpotsDialog(this,"数据正在疯狂加载中！");
//			mAlertDialog.show();
			mAlertDialog = DialogUtil.createLoadingDialog(mContext, "数据正在疯狂加载中！", LoadingStyle.Spots);
			mAlertDialog.show();
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					mAlertDialog.dismiss();
				}
			}, 5000);
			break;
			
		case R.id.btn_MonIndicatorDialog:
//			mAlertDialog = new MonIndicatorDialog(this);
//			mAlertDialog = new MonIndicatorDialog(this, "正在疯狂加载中！");
//			mAlertDialog = new MonIndicatorDialog(this, "正在疯狂加载中！", new int[]{Colors.BLACK, Colors.GOLD, Colors.GREEN_LIGHT, Colors.YELLOW, Colors.RED_DARK});
//			mAlertDialog.show();
			mAlertDialog = DialogUtil.createLoadingDialog(mContext, "数据正在疯狂加载中！", LoadingStyle.MonIndicator);
			mAlertDialog.show();
            mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					mAlertDialog.dismiss();
				}
			}, 5000);
			break;
		case R.id.btn_CircularProgressDialog:
//			mAlertDialog = new CircularProgressDialog(this);
//			mAlertDialog = new CircularProgressDialog(this, "正在疯狂加载中！");
//			mAlertDialog.show();
			mAlertDialog = DialogUtil.createLoadingDialog(mContext, "数据正在疯狂加载中！", LoadingStyle.Circular);
			mAlertDialog.show();
            mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					mAlertDialog.dismiss();
				}
			}, 5000);
			break;
		case R.id.btn_RoundProgressBarDialog:
			mRoundProgressBarDialog = new RoundProgressBarDialog(this);
			mRoundProgressBarDialog.show();
			progress = 0;
			ThreadPoolManager.getInstance().addTask(new Runnable() {
				@Override
				public void run() {
					while (progress <= 100) {
						progress += 2;
						mRoundProgressBarDialog.setProgress(progress);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					mRoundProgressBarDialog.dismiss();
				}
			});
			break;
			
		case R.id.btn_HoriztalProgressBarDialog:
			mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this);
			mHoriztalProgressBarDialog.show();
			progress = 0;
			ThreadPoolManager.getInstance().addTask(new Runnable() {
				@Override
				public void run() {
					while (progress <= 100) {
						progress += 2;
						mHoriztalProgressBarDialog.setProgress(progress);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					mHoriztalProgressBarDialog.dismiss();
				}
			});
			break;
			
		case R.id.btn_CustomDialog:
//			mAlertDialog = new CustomDialog(this, "我是透明的xxxxxxxxxxxxxxxxxxxxxxx");
//			mAlertDialog = new CustomDialog(this);
//			mAlertDialog.show();
			mAlertDialog = DialogUtil.createLoadingDialog(mContext, "数据正在疯狂加载中！", LoadingStyle.Transparent);
			mAlertDialog.show();
			
			if (mAlertDialog instanceof CustomDialog) {
				((CustomDialog) mAlertDialog).setCanceledByBackEvent(true);	     
//		        ((CustomDialog) mAlertDialog).setLoadingText("我正在疯狂加载...");
			}		   

			break;
			
		default:
			break;
		}
		
	}

	@Override
	public void onCancel() {
		Toast("点击取消");
	}

	@Override
	public void onSubmit() {
		Toast("点击确定");
	}

}
