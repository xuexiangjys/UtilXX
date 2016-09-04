package bean;

/**  
 * 商品类
 * 创建时间：2016-2-28 下午5:10:24  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：Commodity.java  
 **/
public class Commodity {

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
	
	/** 库存总量 */
	private int stocknum;
	
	/** 点赞数量 */
	private int love;
	
	/** 商品描述 */
	private String description;
	
	/** 颜色分类 */
	private String color;
	
	/** 商品规格 */
	private String version;
	
	/** 商品标签 */
	private String tag;
	
	/** 商品评价 */
	private String evaluation;

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

	public int getStocknum() {
		return stocknum;
	}

	public void setStocknum(int stocknum) {
		this.stocknum = stocknum;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	
	
}
