package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBcon;
import bean.FoodInfo;
import bean.FoodType;
import dao.FoodInfoDao;

public class FoodInfoDaoImpl implements FoodInfoDao {
	
	private Connection connection;
	private PreparedStatement foodinfoQuery;
	private ResultSet results;
	

	public boolean Add(FoodInfo foodInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into foodinfo(shopid, name, pic, price, salednum, love, description, tag) values(?,?,?,?,?,?,?,?)";
			foodinfoQuery = connection.prepareStatement(sql);
			foodinfoQuery.setInt(1, foodInfo.getShopid());
			foodinfoQuery.setString(2, foodInfo.getName());
			foodinfoQuery.setString(3, foodInfo.getPic());
			foodinfoQuery.setDouble(4, foodInfo.getPrice());
			foodinfoQuery.setInt(5, foodInfo.getSalednum());
			foodinfoQuery.setInt(6, foodInfo.getLove());
			foodinfoQuery.setString(7, foodInfo.getDescription());
			foodinfoQuery.setString(8, foodInfo.getTag());
			int SqlResult = foodinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}
	
	public List<FoodInfo> GetFoodInfoByShopId(int shopid) {
		// TODO Auto-generated method stub
		List<FoodInfo> foodinfoList = new ArrayList<FoodInfo>();
		try {
			connection = DBcon.getConnction();
			foodinfoQuery = connection.prepareStatement("SELECT * FROM foodinfo where shopid='" + shopid + "' ORDER BY id DESC");
			ResultSet results = foodinfoQuery.executeQuery();
			// 读取行数据
			for(int i = 1;results.next();i++) {       				
				FoodInfo foodInfo = new FoodInfo(); 
				foodInfo.setId(results.getInt("id"));				
				foodInfo.setShopid(results.getInt("shopid"));
				foodInfo.setName(results.getString("name"));
				foodInfo.setPic(results.getString("pic"));
				foodInfo.setPrice(results.getDouble("price"));
				foodInfo.setSalednum(results.getInt("salednum"));
				foodInfo.setLove(results.getInt("love"));
				foodInfo.setDescription(results.getString("description"));
				foodInfo.setTag(results.getString("tag"));							
				foodinfoList.add(foodInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodinfoQuery);
			DBcon.closeConnection(connection);
		}
		return foodinfoList;	
	}


	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from foodinfo where id='" + id + "'";
			foodinfoQuery = connection.prepareStatement(sql);
			int SqlResult = foodinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public FoodInfo GetFoodInfoById(int id) {
		// TODO Auto-generated method stub
		FoodInfo foodInfo = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM foodinfo where id='" + id + "'";
			foodinfoQuery = connection.prepareStatement(sql);
			results = foodinfoQuery.executeQuery();
			if (results.next()) {
				foodInfo = new FoodInfo(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				foodInfo.setId(results.getInt("id"));				
				foodInfo.setShopid(results.getInt("shopid"));
				foodInfo.setName(results.getString("name"));
				foodInfo.setPic(results.getString("pic"));
				foodInfo.setPrice(results.getDouble("price"));
				foodInfo.setSalednum(results.getInt("salednum"));
				foodInfo.setLove(results.getInt("love"));
				foodInfo.setDescription(results.getString("description"));
				foodInfo.setTag(results.getString("tag"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodinfoQuery);
			DBcon.closeConnection(connection);
		}

		return foodInfo;
	}

	public List<FoodInfo> GetFoodInfoByShopIdAndType(int shopid, String tag) {
		// TODO Auto-generated method stub
		List<FoodInfo> foodinfoList = new ArrayList<FoodInfo>();
		try {
			connection = DBcon.getConnction();
			foodinfoQuery = connection.prepareStatement("SELECT * FROM foodinfo where shopid='" + shopid + "' and tag='" + tag + "' ORDER BY id DESC");
			ResultSet results = foodinfoQuery.executeQuery();
			// 读取行数据
			for(int i = 1;results.next();i++) {       				
				FoodInfo foodInfo = new FoodInfo(); 
				foodInfo.setId(results.getInt("id"));				
				foodInfo.setShopid(results.getInt("shopid"));
				foodInfo.setName(results.getString("name"));
				foodInfo.setPic(results.getString("pic"));
				foodInfo.setPrice(results.getDouble("price"));
				foodInfo.setSalednum(results.getInt("salednum"));
				foodInfo.setLove(results.getInt("love"));
				foodInfo.setDescription(results.getString("description"));
				foodInfo.setTag(results.getString("tag"));							
				foodinfoList.add(foodInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodinfoQuery);
			DBcon.closeConnection(connection);
		}
		return foodinfoList;	
	}

	public List<FoodType> GetFoodTypeByShopId(int shopid) {
		// TODO Auto-generated method stub
		List<FoodType> foodtypeList = new ArrayList<FoodType>();
		try {
			connection = DBcon.getConnction();
			foodinfoQuery = connection.prepareStatement("SELECT distinct tag FROM foodinfo where shopid='" + shopid + "'");
			ResultSet results = foodinfoQuery.executeQuery();
			// 读取行数据
			for(int i = 1;results.next();i++) {       				
				FoodType foodtype = new FoodType(); 
				String typename = results.getString("tag");				
				foodtype.setTypename(typename);
				foodtype.setId(i);	
				foodtype.setShopid(shopid);			
				foodtype.setFoodinfoList(GetFoodInfoByShopIdAndType(shopid,typename));		
				foodtypeList.add(foodtype); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(foodinfoQuery);
			DBcon.closeConnection(connection);
		}
		return foodtypeList;	
	}

}
