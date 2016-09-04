package bean;

import java.util.List;

public interface ShopDao {
	public List<Shop> getshops();//获得商品列表
	public int add(Shop shopbean); //添加商品
	public int delete(int id); //删除商品
	public int update(Shop shopbean); //修改商品信息
	public Shop findById(int id);//根据序号查询商品信息
}
