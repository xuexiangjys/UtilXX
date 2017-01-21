package com.xuexiang.view;

import java.util.ArrayList;

import com.xuexiang.R;



/**
 * 常量类
 *
 */
public class AppConsts {
	
	public static ArrayList<Integer> getUsertGuides() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(R.drawable.guide_img_1);
        list.add(R.drawable.guide_img_2);
        list.add(R.drawable.guide_img_3);
        list.add(R.drawable.guide_img_4);

        return list;
    }
	//=============weatherview=================//
	public static int UI_WIDTH = 720;
    public static int UI_HEIGHT = 1280;
    public static int UI_DENSITY = 4;
}
