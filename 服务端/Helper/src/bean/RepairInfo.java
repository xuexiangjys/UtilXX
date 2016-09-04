package bean;

import java.io.Serializable;

/**  
 * 创建时间：2016-3-26 上午11:31:29  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：RepairInfo.java  
 **/
public class RepairInfo extends HttpConnect implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Key*/
	private int id;
	/** 作者*/
	private UserInfo author;
	/** 服务标题*/
	private String title;
	/** 服务内容描述*/
	private String description;
	/** 介绍图片*/
	private String piclist;		
	/** 发布时间*/
	private String createtime;
	/** 发布人地址*/
	private String address;
	/** 服务费用*/
	private double price;	
	/** 服务类型*/
	private String repairtype;
	/** 类型：是服务提供者——provider，还是服务需求者——customer*/
	private String kind;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getAuthor() {
		return author;
	}
	public void setAuthor(UserInfo author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPiclist() {
		return piclist;
	}
	public void setPiclist(String piclist) {
		this.piclist = piclist;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRepairtype() {
		return repairtype;
	}
	public void setRepairtype(String repairtype) {
		this.repairtype = repairtype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

}
