package dao;

import java.util.List;

import bean.ShopInfo;

public interface ShopDao {
     
	 /**
	 * 根据城市名和商铺类型获取商铺信息
	 * @param city  城市名
	 * @param type  商铺类型
	 * @return
	 */
	public List<ShopInfo> GetShopByCityAndType(String city,String type);
	
	 /**
	 * 根据城市名、商铺类型以及页数获取商铺信息
	 * @param city  城市名
	 * @param type  商铺类型
	 * @param pagenum  页数
	 * @return
	 */
	public List<ShopInfo> GetShopByCityAndTypeAndPage(String city,String type,int pagenum);
	
	/**
	 * 根据商铺类型获取商铺信息
	 * @param type  商铺类型
	 * @return
	 */
	public List<ShopInfo> GetShopByType(String type);
	
	/**
	 * 根据商铺类型以及页数获取商铺信息
	 * @param type  商铺类型
	 * @param pagenum  页数
	 * @return
	 */
	public List<ShopInfo> GetShopByTypeAndPage(String type,int pagenum);
	
	/**
	 * 根据ID获取商铺信息
	 * @param id
	 * @return
	 */
	public ShopInfo GetShopById(int id);
	
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
	public boolean CheckShopName(String shopname);
	
	/**
	 * 注册商铺信息
	 * @param shopinfo
	 * @return true：注册成功 ，false：注册失败
	 */
	public boolean Register(ShopInfo shopinfo);
	
}
