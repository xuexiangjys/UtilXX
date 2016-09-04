package bean;

public class Shop {
	private int id;
	private String name;
	private double price;//价格
	private int sale_num;//销量
	private String address;//商铺地址
	private String pic;//商铺图片
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSale_num() {
		return sale_num;
	}
	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
