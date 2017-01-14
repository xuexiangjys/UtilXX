package com.example.mycustomview.pathanim;

import java.text.ParseException;

import android.graphics.Path;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mycustomview.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.pathanim.PathAnimView;
import com.xuexiang.view.pathanim.util.SvgPathParser;

public class SvgActivity extends BaseActivity implements OnClickListener {
    private PathAnimView mPathAnimView;
    private String tieta, del, toys, testGIMP, testGIMP2, tempVector, faildVector, settings, qrcode2, qianbihua;
    @Override
	public void onCreateActivity() {
    	setContentView(R.layout.activity_svg);
    	initTitleBar(TAG);
        mPathAnimView = (PathAnimView) findViewById(R.id.storeView);
        
        tieta = getString(R.string.tieta);
        del = getString(R.string.del);
        toys = getString(R.string.toys);
        testGIMP = getString(R.string.testGIMP);
        testGIMP2 = "\"M 90.00,11.00\n" +
                "           C 90.00,11.00 119.00,11.00 119.00,11.00\n" +
                "             121.18,11.00 124.85,10.79 126.69,12.02\n" +
                "             129.62,13.98 129.43,18.71 125.85,20.83\n" +
                "             123.52,22.21 119.67,22.00 117.00,22.00\n" +
                "             117.00,22.00 83.00,22.00 83.00,22.00\n" +
                "             79.27,21.99 73.44,22.62 72.33,17.89\n" +
                "             71.68,15.12 72.58,13.88 74.31,12.02" +
                "             78.99,10.24 84.99,11.00 90.00,11.00 Z";
        tempVector = "M928.2,828.4L130,828.4c-32.8,0 -59.1,-26.3 -59.1,-59.1L70.9,190.4c0,-32.8 26.3,-59.1 59.1,-59.1h798.2c32.8,0 59.1,26.3 59.1,59.1v579c0,32.8 -26.3,59.1 -59.1,59.1zM136.5,762.7h785.1L921.6,196.9L136.5,196.9v565.8z";
        faildVector = "M6,18c0,0.55 0.45,1 1,1h1v3.5c0,0.83 0.67,1.5 1.5,1.5s1.5,-0.67 1.5,-1.5L11,19h2v3.5c0,0.83 0.67,1.5 1.5,1.5s1.5,-0.67 1.5,-1.5L16,19h1c0.55,0 1,-0.45 1,-1L18,8L6,8v10zM3.5,8C2.67,8 2,8.67 2,9.5v7c0,0.83 0.67,1.5 1.5,1.5S5,17.33 5,16.5v-7C5,8.67 4.33,8 3.5,8zM20.5,8c-0.83,0 -1.5,0.67 -1.5,1.5v7c0,0.83 0.67,1.5 1.5,1.5s1.5,-0.67 1.5,-1.5v-7c0,-0.83 -0.67,-1.5 -1.5,-1.5zM15.53,2.16l1.3,-1.3c0.2,-0.2 0.2,-0.51 0,-0.71 -0.2,-0.2 -0.51,-0.2 -0.71,0l-1.48,1.48C13.85,1.23 12.95,1 12,1c-0.96,0 -1.86,0.23 -2.66,0.63L7.85,0.15c-0.2,-0.2 -0.51,-0.2 -0.71,0 -0.2,0.2 -0.2,0.51 0,0.71l1.31,1.31C6.97,3.26 6,5.01 6,7h12c0,-1.99 -0.97,-3.75 -2.47,-4.84zM10,5L9,5L9,4h1v1zM15,5h-1L14,4h1v1z";
        settings = getResources().getString(R.string.settings);
        qrcode2 = getString(R.string.qrcode2);
        qianbihua = getString(R.string.qianbihua);
        
        SVGToPath(qianbihua);
	}
    
    /**
     * SVG转-》path
     * @param res svg路径资源
     */
    private void SVGToPath(String res) {
    	mPathAnimView.clearAnim();
         try {
             Path path = new SvgPathParser().parsePath(res);
             mPathAnimView.setSourcePath(path);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         mPathAnimView.getPathAnimHelper().setAnimTime(5000);
         mPathAnimView.startAnim();
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view1:
			SVGToPath(tieta);
			break;
		case R.id.view2:
			SVGToPath(del);	
			break;
		case R.id.view3:
			SVGToPath(toys);
			break;
		case R.id.view4:
			SVGToPath(testGIMP);
			break;
		case R.id.view5:
			SVGToPath(testGIMP2);
			break;
		case R.id.view6:
			SVGToPath(tempVector);
			break;
		case R.id.view7:
			SVGToPath(faildVector);
			break;
		case R.id.view8:
			SVGToPath(settings);
			break;
		case R.id.view9:
			SVGToPath(qrcode2);	
			break;
		case R.id.view10:
			SVGToPath(qianbihua);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		mPathAnimView.clearAnim();
		super.onDestroy();
	}

	
	
}
