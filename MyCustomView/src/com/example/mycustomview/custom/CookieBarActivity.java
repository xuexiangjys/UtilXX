package com.example.mycustomview.custom;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.cookiebar.CookieBar;
import com.xuexiang.view.cookiebar.OnActionClickListener;

@SuppressLint("ResourceAsColor") 
public class CookieBarActivity extends BaseHeadActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_cookiebar;
	}

	@Override
	protected void init() {
		Button btnTop = (Button) findViewById(R.id.btn_top);
	    btnTop.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View view) {
	        new CookieBar.Builder(CookieBarActivity.this)
	            .setTitle(R.string.cookie_title)
	            .setMessage(R.string.cookie_message)
	            .show();
	      }
	    });

	    Button btnBottom = (Button) findViewById(R.id.btn_bottom);
	    btnBottom.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View view) {
	        new CookieBar
	            .Builder(CookieBarActivity.this)
	            .setTitle(R.string.cookie_title)
	            .setIcon(R.drawable.ic_launcher)
	            .setMessage(R.string.cookie_message)
	            .setLayoutGravity(Gravity.BOTTOM)
	            .setAction(R.string.cookie_action, new OnActionClickListener() {
	              @Override
	              public void onClick() {
	                Toast.makeText(getApplicationContext(), "点击后，我更帅了!", Toast.LENGTH_LONG).show();
	              }
	            })
	            .show();
	      }
	    });

	    Button btnTopWithIcon = (Button) findViewById(R.id.btn_top_with_icon);
	    btnTopWithIcon.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View view) {
	        new CookieBar.Builder(CookieBarActivity.this)
//	            .setTitle(R.string.cookie_title)
	            .setIcon(R.drawable.ic_alert)
	            .setMessage(R.string.cookie_message)
	            .setDuration(3000)
	            .setAction(R.string.cookie_action, new OnActionClickListener() {
	              @Override
	              public void onClick() {
	                Toast.makeText(getApplicationContext(), "点击后，我更帅了!", Toast.LENGTH_LONG).show();
	              }
	            })
	            .show();
	      }
	    });

	    Button btnCustom = (Button) findViewById(R.id.btn_custom);
	    btnCustom.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View view) {
	        new CookieBar.Builder(CookieBarActivity.this)
	            .setTitle(R.string.cookie_title)
	            .setMessage(R.string.cookie_message)
	            .setDuration(3000)
	            .setBackgroundColor(R.color.colorPrimary)
	            .setActionColor(android.R.color.white)
	            .setTitleColor(R.color.colorAccent)
	            .setAction(R.string.cookie_action, new OnActionClickListener() {
	              @Override
	              public void onClick() {
	                Toast.makeText(getApplicationContext(), "点击后，我更帅了!", Toast.LENGTH_LONG).show();
	              }
	            })
	            .show();
	      }
	    });
	}



}
