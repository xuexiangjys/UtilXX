package dao;

import bean.Promotion;

public interface PromotionDao {
	
	/**
	 * 注册促销信息
	 * @param promotion
	 * @return true：注册成功 ，false：注册失败
	 */
	public boolean Add(Promotion promotion);
	
	/**
	 * 删除促销信息（取消促销活动）
	 * @param id
	 * @return true：删除成功 ，false：删除失败
	 */
	public boolean delete(int id);
	
	/**
	 * 更新促销信息（修改促销信息，取消某一个促销活动就设置为""）
	 * @param promotion
	 * @return true：更新成功 ，false：更新失败
	 */
	public boolean updatePromotionInfo(Promotion promotion);

	/**
	 * 获取促销信息
	 * @param shopid
	 * @return true：获取成功 ，false：获取失败
	 */
	public Promotion getPromotionByShopId(int shopid);

	

}
