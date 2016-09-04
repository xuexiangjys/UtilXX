package dao;

import java.util.List;

import bean.AddressInfo;


public interface AddressInfoDao {
	
	/**
	 * 添加地址
	 * @return true :增加地址成功 false:增加地址失败
	 */
	public boolean Add(AddressInfo addressinfo);
	

	/**
	 * 删除地址
	 * @param id Key
	 * @return true :删除地址成功 false:删除地址失败
	 */
	public boolean Delete(int id);
	
	/**
	 * 修改地址
	 * @param addressinfo
	 * @return true :修改地址成功 false:修改地址失败
	 */
	public boolean Update(AddressInfo addressinfo);
	
	/**
	 * 获取指定用户的所有地址
	 * @param loginname
	 * @return 指定用户的所有地址
	 */
	public List<AddressInfo> GetAllAddress(String loginname);
	
	/**
	 * 修改默认地址
	 * @param id
	 * @param loginname
	 * @return true :修改地址成功 false:修改地址失败
	 */
	public boolean UpdateIsdefault(int id,String loginname);
	
	/**
	 * 判断改用户当前是否设有默认地址
	 * @param loginname
	 * @return true :有 false:无
	 */
	public boolean isHasdefaultAddress(String loginname);

}
