package bean;

/**  
 * 商铺类
 * 创建时间：2016-2-27 下午5:33:40  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：ShopInfo.java  
 **/
public class ShopInfo extends HttpConnect{
	
	/** Key*/
	private int id;
	
	/** 商铺名*/
	private String shopname;
	
	/** 商铺图片*/
	private String pic;
	
	/** 促销活动*/
	private String promotion;
	
	/** 商铺地址*/
	private String address;
	
	/** 商铺所在城市*/
	private String city;
	
	/** 商铺类型*/
	private String type;
	
	/** 主营业务*/
	private String mainbusiness;
	
	/** 商家联系电话*/
	private String contact;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMainbusiness() {
		return mainbusiness;
	}

	public void setMainbusiness(String mainbusiness) {
		this.mainbusiness = mainbusiness;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
