package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JsonUtil;
import bean.DBcon;
import bean.ShopInfo;
import dao.ShopDao;

public class ShopDaoImpl implements ShopDao {
	
	private Connection connection;
	private PreparedStatement shopQuery;
	private ResultSet results;

	public List<ShopInfo> GetShopByCityAndType(String city, String type) {
		// TODO Auto-generated method stub
		List<ShopInfo> shopinfoList = new ArrayList<ShopInfo>();
		try {
			connection = DBcon.getConnction();
			shopQuery = connection.prepareStatement("SELECT * FROM shopinfo where city='" + city + "' and type='" + type + "' ORDER BY id DESC");
			ResultSet results = shopQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				ShopInfo shopInfo = new ShopInfo(); 
				shopInfo.setId(results.getInt("id"));
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setPromotion(results.getString("promotion"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setMainbusiness(results.getString("mainbusiness"));
				shopInfo.setContact(results.getString("contact"));
				shopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return shopinfoList;
	}

	public List<ShopInfo> GetShopByType(String type) {
		// TODO Auto-generated method stub
		List<ShopInfo> shopinfoList = new ArrayList<ShopInfo>();
		try {
			connection = DBcon.getConnction();
			shopQuery = connection.prepareStatement("SELECT * FROM shopinfo where type='" + type + "' ORDER BY id DESC");
			ResultSet results = shopQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				ShopInfo shopInfo = new ShopInfo(); 
				shopInfo.setId(results.getInt("id"));
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setPromotion(results.getString("promotion"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setMainbusiness(results.getString("mainbusiness"));
				shopInfo.setContact(results.getString("contact"));
				shopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return shopinfoList;
	}

	public ShopInfo GetShopById(int id) {
		// TODO Auto-generated method stub
		ShopInfo shopInfo = null;
		try {
			connection = DBcon.getConnction();
			shopQuery = connection.prepareStatement("SELECT * FROM shopinfo where id='" + id + "'");
			ResultSet results = shopQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				shopInfo = new ShopInfo(); 
				shopInfo.setId(results.getInt("id"));
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setPromotion(results.getString("promotion"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setMainbusiness(results.getString("mainbusiness"));
				shopInfo.setContact(results.getString("contact"));
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return shopInfo;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from shopinfo where id='" + id + "'";
			shopQuery = connection.prepareStatement(sql);
			int SqlResult = shopQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Register(ShopInfo shopinfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into shopinfo(shopname, pic, promotion, address, city, type, mainbusiness, contact) values(?,?,?,?,?,?,?,?)";
			shopQuery = connection.prepareStatement(sql);
			shopQuery.setString(1, shopinfo.getShopname());
			shopQuery.setString(2, shopinfo.getPic());
			shopQuery.setString(3, shopinfo.getPromotion());
			shopQuery.setString(4, shopinfo.getAddress());
			shopQuery.setString(5, shopinfo.getCity());
			shopQuery.setString(6, shopinfo.getType());
			shopQuery.setString(7, shopinfo.getMainbusiness());
			shopQuery.setString(8, shopinfo.getContact());
			int SqlResult = shopQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean CheckShopName(String shopname) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM shopinfo where shopname=?";
			shopQuery = connection.prepareStatement(sql);
			shopQuery.setString(1, shopname);
			results = shopQuery.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<ShopInfo> GetShopByCityAndTypeAndPage(String city, String type,
			int pagenum) {
		// TODO Auto-generated method stub
		List<ShopInfo> shopinfoList = new ArrayList<ShopInfo>();
		int page = -1;
		try {
			connection = DBcon.getConnction();
			shopQuery = connection.prepareStatement("SELECT * FROM shopinfo where city='" + city + "' and type='" + type + "' ORDER BY id DESC");
			ResultSet results = shopQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {       
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				ShopInfo shopInfo = new ShopInfo(); 
				shopInfo.setId(results.getInt("id"));
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setPromotion(results.getString("promotion"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setMainbusiness(results.getString("mainbusiness"));
				shopInfo.setContact(results.getString("contact"));
				shopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return shopinfoList;
	}

	public List<ShopInfo> GetShopByTypeAndPage(String type, int pagenum) {
		// TODO Auto-generated method stub
		List<ShopInfo> shopinfoList = new ArrayList<ShopInfo>();
		int page = -1;
		try {
			connection = DBcon.getConnction();
			shopQuery = connection.prepareStatement("SELECT * FROM shopinfo where type='" + type + "' ORDER BY id DESC");
			ResultSet results = shopQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {       
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				ShopInfo shopInfo = new ShopInfo(); 
				shopInfo.setId(results.getInt("id"));
				shopInfo.setShopname(results.getString("shopname"));
				shopInfo.setPic(results.getString("pic"));
				shopInfo.setPromotion(results.getString("promotion"));
				shopInfo.setAddress(results.getString("address"));
				shopInfo.setCity(results.getString("city"));
				shopInfo.setType(results.getString("type"));
				shopInfo.setMainbusiness(results.getString("mainbusiness"));
				shopInfo.setContact(results.getString("contact"));
				shopinfoList.add(shopInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopQuery);
			DBcon.closeConnection(connection);
		}
		return shopinfoList;
	}

}
