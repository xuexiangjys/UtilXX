package dao;

import java.util.List;

import bean.FoodInfo;
import bean.FoodType;

public interface FoodInfoDao {

	/**
	 * 根据商家ID获取食品信息集合
	 * @param shopid  商家ID
	 * @return 食品信息集合
	 */
	public List<FoodInfo> GetFoodInfoByShopId(int shopid);
	
	/**
	 * 根据商家ID和食品类型获取食品信息集合
	 * @param shopid  商家ID
	 * @param tag  食品类型
	 * @return 食品信息集合
	 */
	public List<FoodInfo> GetFoodInfoByShopIdAndType(int shopid,String tag);
	
	/**
	 * 根据商家ID获取商家类型食品信息集合
	 * @param shopid  商家ID
	 * @return 商家类型食品信息集合
	 */
	public List<FoodType> GetFoodTypeByShopId(int shopid);
	
	/**
	 * 根据ID获取食品信息
	 * @param id
	 * @return
	 */
	public FoodInfo GetFoodInfoById(int id);
	
	/**
	 * 根据ID删除食品信息
	 * @param id
	 * @return
	 */
	public boolean Delete(int id);
	
	/**
	 * 添加食品信息
	 * @param foodInfo
	 * @return true：添加成功 ，false：添加失败
	 */
	public boolean Add(FoodInfo foodInfo);
	
}
