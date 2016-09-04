package bean;

/**
 * 促销活动类
 * @author xx
 */
public class Promotion {
	/** Key*/
	private int id;
	
	/** 商铺ID*/
	private int shopid;
	
	/** 首单立减——首*/     
	private String firstorder;
	
	/** 满减优惠——减*/
	private String fullreduction;
	
	/** 折扣优惠——折*/
	private String discount;

	/** 满返代金券——返*/
	private String vouchers;
	
	/** 提前下单优惠优惠——订*/
	private String preorder;
	
	/** 满赠活动——赠*/
	private String fullofgifts;
	
	/** 满免配送费——免*/
	private String freedistribution;

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

	public String getFirstorder() {
		return firstorder;
	}

	public void setFirstorder(String firstorder) {
		this.firstorder = firstorder;
	}

	public String getFullreduction() {
		return fullreduction;
	}

	public void setFullreduction(String fullreduction) {
		this.fullreduction = fullreduction;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getVouchers() {
		return vouchers;
	}

	public void setVouchers(String vouchers) {
		this.vouchers = vouchers;
	}

	public String getPreorder() {
		return preorder;
	}

	public void setPreorder(String preorder) {
		this.preorder = preorder;
	}

	public String getFullofgifts() {
		return fullofgifts;
	}

	public void setFullofgifts(String fullofgifts) {
		this.fullofgifts = fullofgifts;
	}

	public String getFreedistribution() {
		return freedistribution;
	}

	public void setFreedistribution(String freedistribution) {
		this.freedistribution = freedistribution;
	}
	
}
