package com.example.mycustomview.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.mycustomview.R;
import com.xuexiang.view.webview.WebViewActivity;

public class WebViewMainActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.bt_baidu)
    Button btBaidu;
    @Bind(R.id.bt_call)
    Button btCall;
    @Bind(R.id.bt_upload_photo)
    Button btUploadPhoto;
    @Bind(R.id.bt_movie)
    Button btMovie;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.bt_movie_full)
    Button btMovieFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewmain);
        ButterKnife.bind(this);

        btBaidu.setOnClickListener(this);
        btCall.setOnClickListener(this);
        btUploadPhoto.setOnClickListener(this);
        btMovie.setOnClickListener(this);
        btMovieFull.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_baidu:// 百度一下
                String baiDuUrl = "http://www.baidu.com";
                WebViewActivity.loadUrl(this, baiDuUrl, false);
                break;
            case R.id.bt_upload_photo:// 上传图片
                String uploadUrl = "file:///android_asset/upload_photo.html";
                WebViewActivity.loadUrl(this, uploadUrl, false);
                break;
            case R.id.bt_movie:// 网络视频(优酷链接跳到浏览器)
//                String movieUrl = "http://v.youku.com/v_show/id_XNzMxNzUyNzQ0.html?beta&#paction";
                String movieUrl = "http://www.tudou.com/albumplay/eu0K8vLTD48/aHeFLTBfzU0.html";
                WebViewActivity.loadUrl(this, movieUrl, false);
                break;
            case R.id.bt_movie_full:// 网络视频(全屏)
                String movieFullUrl = "http://player.youku.com/embed/XMTMxOTk1ODI4OA";
                WebViewActivity.loadUrl(this, movieFullUrl, true);
                break;
            case R.id.bt_call:// 打电话、发短信、发邮件、JS
                String callUrl = "file:///android_asset/callsms.html";
                WebViewActivity.loadUrl(this, callUrl, false);
                break;
            default:
                break;
        }
    }
}
