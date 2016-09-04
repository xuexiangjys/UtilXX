package dao;

import bean.BankCard;


public interface BankCardDao {

	/**
	 * 银行卡开户
	 * @param bankCard
	 */
	public boolean Register(BankCard bankCard);
	
	/**
	 * 银行卡密码验证
	 * @param cardid  银行卡卡号
	 * @param password 密码
	 */
	public boolean CheckCard(String cardid,String password);
	
	/**
	 * 银行卡金额变动更新
	 * @param cardid  银行卡卡号
	 * @param moneyChange 金额变动
	 */
	public boolean UpdateMoneyChange(String cardid,double moneyChange);
	
	/**
	 * 银行卡金额更新
	 * @param cardid  银行卡卡号
	 * @param money 金额
	 */
	public boolean UpdateMoney(String cardid,double money);
	
	/**
	 * 获取某张银行卡的金额
	 * @param cardid  银行卡卡号
	 */
	public double GetCardMoney(String cardid);
	
	
	/**
	 * 银行卡密码修改
	 * @param cardid  银行卡卡号
	 * @param newpassword 新密码
	 */
	public boolean UpdatePassword(String cardid,String newpassword);
	
	/**
	 * 获取银行卡信息
	 * @param cardid  银行卡卡号
	 */
	public BankCard GetCardInfoByCardId(String cardid);
	
	
	
}
