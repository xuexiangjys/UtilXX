package bean;

import java.util.List;

/**  
 * 乐居宝类
 * 创建时间：2016-3-2 下午10:26:59  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：helperpay.java  
 **/
public class HelperPay {
	/** Key*/
	private int id;	
	/** 用户名*/
	private String username;
	/** 支付密码*/
	private String password;
	/** 零钱*/
	private double money;
	/** 绑定银行卡*/
	private List<BankCard> bankcardlist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public List<BankCard> getBankcardlist() {
		return bankcardlist;
	}
	public void setBankcardlist(List<BankCard> bankcardlist) {
		this.bankcardlist = bankcardlist;
	}	
}
