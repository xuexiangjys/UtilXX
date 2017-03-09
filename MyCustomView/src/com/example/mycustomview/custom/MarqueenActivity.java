package com.example.mycustomview.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.marqueen.ComplexItemEntity;
import com.xuexiang.view.marqueen.ComplexViewMF;
import com.xuexiang.view.marqueen.MarqueeFactory;
import com.xuexiang.view.marqueen.MarqueeView;
import com.xuexiang.view.marqueen.NoticeMF;

public class MarqueenActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_marqueen);
		initTitleBar(TAG);
		
        List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");
        MarqueeView marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        MarqueeView marqueeView2 = (MarqueeView) findViewById(R.id.marqueeView2);
        MarqueeView marqueeView3 = (MarqueeView) findViewById(R.id.marqueeView3);
        MarqueeView marqueeView4 = (MarqueeView) findViewById(R.id.marqueeView4);
        MarqueeView marqueeView5 = (MarqueeView) findViewById(R.id.marqueeView5);

        MarqueeFactory<TextView, String> marqueeFactory1 = new NoticeMF(this);
        marqueeView1.setMarqueeFactory(marqueeFactory1);
        marqueeView1.startFlipping();
        marqueeFactory1.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MarqueenActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory1.setData(datas);

        MarqueeFactory<TextView, String> marqueeFactory2 = new NoticeMF(this);
        marqueeFactory2.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MarqueenActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory2.setData(datas);
        marqueeView2.setMarqueeFactory(marqueeFactory2);
        marqueeView2.startFlipping();

        MarqueeFactory<TextView, String> marqueeFactory3 = new NoticeMF(this);
        marqueeFactory3.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MarqueenActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory3.setData(datas);
        marqueeView3.setMarqueeFactory(marqueeFactory3);
        marqueeView3.setAnimInAndOut(R.anim.marquee_left_in, R.anim.marquee_right_out);
        marqueeView3.setAnimDuration(2000);
        marqueeView3.setInterval(2500);
        marqueeView3.startFlipping();

        MarqueeFactory<TextView, String> marqueeFactory4 = new NoticeMF(this);
        marqueeFactory4.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MarqueenActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory4.setData(datas);
        marqueeView4.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out);
        marqueeView4.setMarqueeFactory(marqueeFactory4);
        marqueeView4.startFlipping();

        List<ComplexItemEntity> complexDatas = new ArrayList<ComplexItemEntity>();
        for (int i = 0; i < 5; i++) {
            complexDatas.add(new ComplexItemEntity("标题 " + i, "副标题 " + i, "时间 " + i));
        }
        MarqueeFactory<RelativeLayout, ComplexItemEntity> marqueeFactory5 = new ComplexViewMF(this);
        marqueeFactory5.setData(complexDatas);
        marqueeView5.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out);
        marqueeView5.setMarqueeFactory(marqueeFactory5);
        marqueeView5.startFlipping();
	}
	
}
