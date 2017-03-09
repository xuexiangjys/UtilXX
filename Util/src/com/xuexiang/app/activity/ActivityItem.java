package com.xuexiang.app.activity;


/**
 * 例子实体类
 * @author xx
 *
 */
public class ActivityItem {
	public final static String TITLE = "title";
    /**
     * 例子的标题
     */
    public String title;
    /**
     * 例子点击后加载的activity类名,例如：SampleActivity.class.getName()
     */
    public String page;

    public ActivityItem(String title, String page) {
        this.title = title;
        this.page = page;
    }
    
    public ActivityItem(String title, Class<?> page) {
        this.title = title;
        this.page = page.getName();
    }

    @Override
    public String toString() {
        return title;
    }
}
