package bean;

/**
 * 美食商品类
 * @author xx
 */
public class FoodInfo {

	/** Key */
	private int id;
	
	/** 商家ID */
	private int shopid;
	
	/** 商品名 */
	private String name;
	
	/** 图片详情 */
	private String pic;
	
	/** 商品价格 */
	private double price;
	
	/** 已销数量 */
	private int salednum;
	
	/** 点赞数量 */
	private int love;
	
	/** 商品描述 */
	private String description;
	
	/** 商品标签 */
	private String tag;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSalednum() {
		return salednum;
	}

	public void setSalednum(int salednum) {
		this.salednum = salednum;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
