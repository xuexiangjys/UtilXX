package bean;

/**  
 * 身份验证的请求类
 * 创建时间：2016-3-6 下午12:12:02  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：IdentificationReq.java  
 **/
public class IdentificationReq {

	/** 用户名*/
	private String loginname;
	/** 真实姓名*/
	private String realname;
	/** 身份证*/
	private String identitycard;
	/** 银行卡号*/
	private String bankCardId;	
	/** 银行卡密码*/
	private String bankCardPassword;
	/** 开户行*/
	private String banktype;
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getBankCardPassword() {
		return bankCardPassword;
	}
	public void setBankCardPassword(String bankCardPassword) {
		this.bankCardPassword = bankCardPassword;
	}
	public String getBanktype() {
		return banktype;
	}
	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}
	
}
