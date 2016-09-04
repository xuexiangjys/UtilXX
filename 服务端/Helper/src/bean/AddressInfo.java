package bean;

/**
 * 地址管理类
 * @author xx
 */
public class AddressInfo {
	
	/** Key*/
	private int id;	
	/** 用户名*/
	private String loginname;
	/** 收货人*/
	private String consignee;
	/** 联系方式*/
	private String contact;
	/** 所在地区*/
	private String area;
	/** 详细地址*/
	private String address;
	/** 邮政编码*/
	private String postcode;
	/** 是否为默认地址*/
	private String isdefault;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

}
