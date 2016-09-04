package dao;

import java.util.List;

import bean.FoodShop;

public interface FoodShopDao {
	/**
	 * 根据城市名和商铺类型获取商铺信息
	 * @param city  城市名
	 * @param type  商铺类型
	 * @return
	 */
	public List<FoodShop> GetFoodShopByCityAndType(String city,String type);
	
	/**
	 * 复杂查询商铺信息
	 * @param city  城市名
	 * @param type  商铺类型
	 * @param sortcondition  排序条件
	 * @param filtrate  筛选条件
	 * @param pagenum  页数
	 * @return
	 */
	public List<FoodShop> GetTypeFoodShopByComplexCondition(String city,String type,String sortcondition,String filtrate,int pagenum);
	
	
	 /**
	 * 根据城市名、商铺类型以及页数获取商铺信息
	 * @param city  城市名
	 * @param type  商铺类型
	 * @param pagenum  页数
	 * @return
	 */
	public List<FoodShop> GetFoodShopByCityAndTypeAndPage(String city,String type,int pagenum);
	
	 /**
	 * 根据城市名以及页数获取商铺信息
	 * @param city  城市名
	 * @param pagenum  页数
	 * @return
	 */
	public List<FoodShop> GetFoodShopByCityAndPage(String city,int pagenum);
	
	/**
	 * 根据商铺类型获取商铺信息
	 * @param type  商铺类型
	 * @return
	 */
	public List<FoodShop> GetFoodShopByType(String type);
	
	/**
	 * 根据商铺类型获取商铺信息
	 * @param pagenum  页数
	 * @return
	 */
	public List<FoodShop> GetFoodShopByPage(int pagenum);
	
	/**
	 * 根据商铺类型以及页数获取商铺信息
	 * @param type  商铺类型
	 * @param pagenum  页数
	 * @return
	 */
	public List<FoodShop> GetFoodShopByTypeAndPage(String type,int pagenum);
	
	/**
	 * 根据ID获取商铺信息
	 * @param id
	 * @return
	 */
	public FoodShop GetFoodShopById(int id);
	
	/**
	 * 根据ID删除商铺信息
	 * @param id
	 * @return
	 */
	public boolean Delete(int id);
	
	/**
	 * 检测商铺名是否存在
	 * @param shopname
	 * @return true : 该商铺名已存在 , false : 该商铺名不存在，可以注册
	 */
	public boolean CheckFoodShopName(String shopname);
	
	/**
	 * 注册商铺信息
	 * @param foodshop
	 * @return true：注册成功 ，false：注册失败
	 */
	public boolean Register(FoodShop foodshop);
}
