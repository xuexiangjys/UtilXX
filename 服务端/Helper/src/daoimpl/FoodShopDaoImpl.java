package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JsonUtil;
import bean.DBcon;
import bean.FoodShop;
import bean.Promotion;
import dao.FoodShopDao;
import dao.PromotionDao;

public class FoodShopDaoImpl implements FoodShopDao{

	private Connection connection;
	private PreparedStatement foodshopQuery;
	private ResultSet results;
	
	public boolean Register(FoodShop foodshop) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into foodshop(shopname, pic, salednum, startingprice, deliverprice, delivertime, type, city, dispatchtime, contact, address, serviceprovider, starnum) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			foodshopQuery = connection.prepareStatement(sql);
			foodshopQuery.setString(1, foodshop.getShopname());
			foodshopQuery.setString(2, foodshop.getPic());
			foodshopQuery.setInt(3, foodshop.getSalednum());
			foodshopQuery.setInt(4, foodshop.getStartingprice());
			foodshopQuery.setInt(5, foodshop.getDeliverprice());
			foodshopQuery.setInt(6, foodshop.getDelivertime());
			foodshopQuery.setString(7, foodshop.getType());
			foodshopQuery.setString(8, foodshop.getCity());
			foodshopQuery.setString(9, foodshop.getDispatchtime());
			foodshopQuery.setString(10, foodshop.getContact());
			foodshopQuery.setString(11, foodshop.getAddress());
			foodshopQuery.setString(12, foodshop.getServiceprovider());
			foodshopQuery.setFloat(13, foodshop.getStarnum());
			int SqlResult = foodshopQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}
	
	public boolean CheckFoodShopName(String shopname) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM foodshop where shopname=?";
			foodshopQuery = connection.prepareStatement(sql);
			foodshopQuery.setString(1, shopname);
			results = foodshopQuery.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from foodshop where id='" + id + "'";
			foodshopQuery = connection.prepareStatement(sql);
			int SqlResult = foodshopQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<FoodShop> GetFoodShopByCityAndPage(String city, int pagenum) {
		// TODO Auto-generated method stub
		List<FoodShop> foodshopinfoList = new ArrayList<FoodShop>();
		PromotionDao promotionDao=new PromotionDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			//foodshopQuery = connection.prepareStatement("SELECT * FROM foodshop where city='" + city + "' ORDER BY id DESC");
			foodshopQuery = connection.prepareStatement("SELECT * FROM foodshop where city='" + city + "'");
			ResultSet results = foodshopQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {       
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				FoodShop shopInfo = new FoodShop(); 
				shopInfo.setId(results.getInt("id"));
				
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
								
				foodshopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return foodshopinfoList;
	}

	public List<FoodShop> GetFoodShopByCityAndType(String city, String type) {
		// TODO Auto-generated method stub
		List<FoodShop> foodshopinfoList = new ArrayList<FoodShop>();
		PromotionDao promotionDao=new PromotionDaoImpl();  
		try {
			connection = DBcon.getConnction();
			foodshopQuery = connection.prepareStatement("SELECT * FROM foodshop where city='" + city + "' and type='" + type + "' ORDER BY id DESC");
			ResultSet results = foodshopQuery.executeQuery();
			// 读取行数据
			for(int i = 1;results.next();i++) {       				
				FoodShop shopInfo = new FoodShop(); 
				shopInfo.setId(results.getInt("id"));
				
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
								
				foodshopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return foodshopinfoList;

	}

	public List<FoodShop> GetFoodShopByCityAndTypeAndPage(String city,
			String type, int pagenum) {
		
		List<FoodShop> foodshopinfoList = new ArrayList<FoodShop>();
		PromotionDao promotionDao=new PromotionDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			foodshopQuery = connection.prepareStatement("SELECT * FROM foodshop where city='" + city + "' and type='" + type + "' ORDER BY id DESC");
			ResultSet results = foodshopQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {       
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				FoodShop shopInfo = new FoodShop(); 
				shopInfo.setId(results.getInt("id"));
				
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
								
				foodshopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return foodshopinfoList;
	}

	public List<FoodShop> GetFoodShopByPage(int pagenum) {
		
		List<FoodShop> foodshopinfoList = new ArrayList<FoodShop>();
		PromotionDao promotionDao=new PromotionDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			foodshopQuery = connection.prepareStatement("SELECT * FROM foodshop ORDER BY id DESC");
			ResultSet results = foodshopQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {       
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				FoodShop shopInfo = new FoodShop(); 
				shopInfo.setId(results.getInt("id"));
				
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
								
				foodshopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return foodshopinfoList;	
	}

	public List<FoodShop> GetFoodShopByType(String type) {
		
		List<FoodShop> foodshopinfoList = new ArrayList<FoodShop>();
		PromotionDao promotionDao=new PromotionDaoImpl();  
		try {
			connection = DBcon.getConnction();
			foodshopQuery = connection.prepareStatement("SELECT * FROM foodshop where type='" + type + "' ORDER BY id DESC");
			ResultSet results = foodshopQuery.executeQuery();
			// 读取行数据
			for(int i = 1;results.next();i++) {       				
				FoodShop shopInfo = new FoodShop(); 
				shopInfo.setId(results.getInt("id"));
				
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
								
				foodshopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return foodshopinfoList;

	}

	public FoodShop GetFoodShopById(int id) {
		
		FoodShop shopInfo = null;
		PromotionDao promotionDao=new PromotionDaoImpl();  
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM foodshop where id='" + id + "'";
			foodshopQuery = connection.prepareStatement(sql);
			results = foodshopQuery.executeQuery();
			if (results.next()) {
				shopInfo = new FoodShop(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				shopInfo.setId(results.getInt("id"));
				
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}

		return shopInfo;
	}

	public List<FoodShop> GetFoodShopByTypeAndPage(String type, int pagenum) {
		
		List<FoodShop> foodshopinfoList = new ArrayList<FoodShop>();
		PromotionDao promotionDao=new PromotionDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			foodshopQuery = connection.prepareStatement("SELECT * FROM foodshop where type='" + type + "' ORDER BY id DESC");
			ResultSet results = foodshopQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {       
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				FoodShop shopInfo = new FoodShop(); 
				shopInfo.setId(results.getInt("id"));
				
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
								
				foodshopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return foodshopinfoList;
	}

	public List<FoodShop> GetTypeFoodShopByComplexCondition(String city,String type, String sortcondition, String filtrate, int pagenum) {
		List<FoodShop> foodshopinfoList = new ArrayList<FoodShop>();
		List<FoodShop> tempList = new ArrayList<FoodShop>();
		PromotionDao promotionDao=new PromotionDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			foodshopQuery = connection.prepareStatement(getComplexSQLString(city,type,sortcondition));
			ResultSet results = foodshopQuery.executeQuery();
			// 读取行数据
			while(results.next()) {       
						
				Promotion foodPromotion = promotionDao.getPromotionByShopId(results.getInt("id"));	
				if(!filtratePromotion(foodPromotion,filtrate)) {
					continue;
				}
				
				FoodShop shopInfo = new FoodShop(); 
				shopInfo.setId(results.getInt("id"));
								
				shopInfo.setFoodpromotion(foodPromotion);
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setStarnum(results.getFloat("starnum"));
				shopInfo.setSalednum(results.getInt("salednum"));
				shopInfo.setStartingprice(results.getInt("startingprice"));
				shopInfo.setDeliverprice(results.getInt("deliverprice"));
				shopInfo.setDelivertime(results.getInt("delivertime"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setDispatchtime(results.getString("dispatchtime"));
				shopInfo.setContact(results.getString("contact"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setServiceprovider(results.getString("serviceprovider"));
								
				tempList.add(shopInfo); // 将封将类添加到数组中
			}
			int count = 0;
			for (FoodShop foodShop : tempList) {
				if((count % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					count++;
					continue;
				}
				foodshopinfoList.add(foodShop);
				count++;
			}
			
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodshopQuery);
			DBcon.closeConnection(connection);
		}
		return foodshopinfoList;
	}

	/**
	 * 获取复杂查询的sql语句
	 * @param city
	 * @param type
	 * @param sortcondition
	 * @return
	 */
	private String getComplexSQLString(String city,String type, String sortcondition) {
		String sql = "";
		if (type.equals("")) {    //无type
		   sql = "SELECT * FROM foodshop where city='" + city + getConditionString(sortcondition);
		} else {               //有type
           sql = "SELECT * FROM foodshop where city='" + city + "' and type='" + type + getConditionString(sortcondition);
		}	
		return sql;
	}
	
	/**
	 * 获取排序的sql语句
	 * @param sortcondition
	 * @return
	 */
	private String getConditionString(String sortcondition) {
		String str = "'";
		if (sortcondition.equals("salednum")) {
			str = "' ORDER BY salednum DESC";
		} else if (sortcondition.equals("delivertime")) {
			str = "' ORDER BY delivertime";
		} else if (sortcondition.equals("starnum")) {
			str = "' ORDER BY starnum DESC";
		} else if (sortcondition.equals("startingprice")) {
			str = "' ORDER BY startingprice";
		} else if (sortcondition.equals("deliverprice")) {
			str = "' ORDER BY deliverprice";
		}		
		return str;
	}
	
	/**
	 * 筛选促销条件
	 * @param foodPromotion
	 * @return true :符合要求的商铺，false:不符合的商铺
	 */
	private boolean filtratePromotion(Promotion foodPromotion, String filtrate) {
		boolean result = false;
		if(foodPromotion != null) {
			if (filtrate.equals("")) {
				result = true ;
			} else if (filtrate.equals("firstorder")) {
				result = IsExist(foodPromotion.getFirstorder());
			} else if (filtrate.equals("fullreduction")) {
				result = IsExist(foodPromotion.getFullreduction());
			} else if (filtrate.equals("discount")) {
				result = IsExist(foodPromotion.getDiscount());
			} else if (filtrate.equals("vouchers")) {
				result = IsExist(foodPromotion.getVouchers());
			} else if (filtrate.equals("preorder")) {
				result = IsExist(foodPromotion.getPreorder());
			} else if (filtrate.equals("fullofgifts")) {
				result = IsExist(foodPromotion.getFullofgifts());
			} else if (filtrate.equals("freedistribution")) {
				result = IsExist(foodPromotion.getFreedistribution());
			} 		
		}
		return result;
	}
	
	private boolean IsExist(String str){
		if(str == null ||str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
