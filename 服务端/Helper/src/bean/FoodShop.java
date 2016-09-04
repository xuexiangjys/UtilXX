package bean;

/**
 * 美食商铺类
 * @author xx
 */
public class FoodShop extends ShopInfo {
	
	/** 评价星数 */
	private float starnum;
	
	/** 已销数量 */
	private int salednum;

	/** 起送价 */
	private int startingprice;
	
	/** 送配费 */
	private int deliverprice;
	
	/** 平均送餐时间 */
	private int delivertime;
	
	/** 促销活动 */
	private Promotion foodpromotion;

	/** 配送时间 */
	private String dispatchtime;
	
	/** 配送服务者 */
	private String serviceprovider;

	public float getStarnum() {
		return starnum;
	}

	public void setStarnum(float starnum) {
		this.starnum = starnum;
	}

	public int getSalednum() {
		return salednum;
	}

	public void setSalednum(int salednum) {
		this.salednum = salednum;
	}

	public int getStartingprice() {
		return startingprice;
	}

	public void setStartingprice(int startingprice) {
		this.startingprice = startingprice;
	}

	public int getDeliverprice() {
		return deliverprice;
	}

	public void setDeliverprice(int deliverprice) {
		this.deliverprice = deliverprice;
	}

	public int getDelivertime() {
		return delivertime;
	}

	public void setDelivertime(int delivertime) {
		this.delivertime = delivertime;
	}
	
	public Promotion getFoodpromotion() {
		return foodpromotion;
	}

	public void setFoodpromotion(Promotion foodpromotion) {
		this.foodpromotion = foodpromotion;
	}

	public String getDispatchtime() {
		return dispatchtime;
	}

	public void setDispatchtime(String dispatchtime) {
		this.dispatchtime = dispatchtime;
	}

	public String getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(String serviceprovider) {
		this.serviceprovider = serviceprovider;
	}
	
}
