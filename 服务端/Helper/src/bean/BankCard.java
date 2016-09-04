package bean;

/**  
 * 银行卡信息类
 * 创建时间：2016-3-2 下午10:28:21  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：BankCard.java  
 **/
public class BankCard {
	/** Key*/
	private int id;	
	/** 银行卡号*/
	private String cardId;	
	/** 户名*/
	private String name;	
	/** 身份证*/
	private String identitycard;
	/** 银行卡密码*/
	private String password;
	/** 卡内金额*/
	private double money;
	/** 开户行*/
	private String banktype;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
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
	public String getBanktype() {
		return banktype;
	}
	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}
	
	

}
