package com.xuexiang.view.dragview.entity;

/**
 * Created by su on 2016/5/28.
 */
public class GridCutItem {

    private String name;

    private int drawableId;
    
	private String tip;    

    public GridCutItem( String name ,int drawableId, String tip) {
        this.name = name;
        this.drawableId = drawableId;
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getDrawableId() {
		return drawableId;
	}

	public void setDrawableId(int drawableId) {
		this.drawableId = drawableId;
	}

}
