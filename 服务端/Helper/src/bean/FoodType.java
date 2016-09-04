package bean;

import java.util.List;

public class FoodType {
	/** Key */
	private int id;
	
	/** 商家ID */
	private int shopid;
	
	/** 食物类型名 */
	private String typename;
	
	private List<FoodInfo> foodinfoList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public List<FoodInfo> getFoodinfoList() {
		return foodinfoList;
	}

	public void setFoodinfoList(List<FoodInfo> foodinfoList) {
		this.foodinfoList = foodinfoList;
	}
}
