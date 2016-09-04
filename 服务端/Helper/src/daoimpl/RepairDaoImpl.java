package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.JsonUtil;
import bean.DBcon;
import bean.RepairInfo;
import bean.UserInfo;
import dao.RepairDao;
import dao.UserInfoDao;

public class RepairDaoImpl implements RepairDao {
	private Connection connection;
	private PreparedStatement repairQuery;
	private ResultSet results;
	
	public boolean Add(RepairInfo repairInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into repairinfo(authorname, address, title, description, piclist, createtime, price, repairtype, kind) values(?,?,?,?,?,?,?,?,?)";
			repairQuery = connection.prepareStatement(sql);
			repairQuery.setString(1, repairInfo.getAuthor().getLoginname());
			repairQuery.setString(2, repairInfo.getAddress());
			repairQuery.setString(3, repairInfo.getTitle());
			repairQuery.setString(4, repairInfo.getDescription());
			repairQuery.setString(5, repairInfo.getPiclist());
			repairQuery.setString(6, repairInfo.getCreatetime());
			repairQuery.setDouble(7, repairInfo.getPrice());
			repairQuery.setString(8, repairInfo.getRepairtype());
			repairQuery.setString(9, repairInfo.getKind());
			int SqlResult = repairQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from repairinfo where id='" + id + "'";
			repairQuery = connection.prepareStatement(sql);
			int SqlResult = repairQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<RepairInfo> GetAllRepairInfo(String kind) {
		// TODO Auto-generated method stub
		List<RepairInfo> repairInfoList = new ArrayList<RepairInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			repairQuery = connection.prepareStatement("SELECT * FROM repairinfo where kind='" + kind + "' ORDER BY id DESC");
			ResultSet results = repairQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				RepairInfo repairInfo = new RepairInfo(); 
				repairInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				repairInfo.setAuthor(getUserInfo);
				repairInfo.setAddress(results.getString("address"));
				repairInfo.setTitle(results.getString("title"));
				repairInfo.setDescription(results.getString("description"));
				repairInfo.setPiclist(results.getString("piclist"));
				repairInfo.setCreatetime(results.getString("createtime"));
				repairInfo.setPrice(results.getDouble("price"));
				repairInfo.setRepairtype(results.getString("repairtype"));
				repairInfoList.add(repairInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}
		return repairInfoList;
	}

	public List<RepairInfo> GetAllRepairInfoByPage(int pagenum,String kind) {
		// TODO Auto-generated method stub
		List<RepairInfo> repairInfoList = new ArrayList<RepairInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			repairQuery = connection.prepareStatement("SELECT * FROM repairinfo where kind='" + kind + "' ORDER BY id DESC");
			ResultSet results = repairQuery.executeQuery();
			
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				RepairInfo repairInfo = new RepairInfo(); 
				repairInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				repairInfo.setAuthor(getUserInfo);
				repairInfo.setAddress(results.getString("address"));
				repairInfo.setTitle(results.getString("title"));
				repairInfo.setDescription(results.getString("description"));
				repairInfo.setPiclist(results.getString("piclist"));
				repairInfo.setCreatetime(results.getString("createtime"));
				repairInfo.setPrice(results.getDouble("price"));
				repairInfo.setRepairtype(results.getString("repairtype"));
				repairInfoList.add(repairInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}
		return repairInfoList;
	}

	public List<RepairInfo> GetOwnRepairInfoByPage(String loginname, int pagenum, String kind) {
		// TODO Auto-generated method stub
		List<RepairInfo> repairInfoList = new ArrayList<RepairInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			repairQuery = connection.prepareStatement("SELECT * FROM repairinfo where authorname='" + loginname + "' and kind='" + kind + "' ORDER BY id DESC");
			ResultSet results = repairQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				

				RepairInfo repairInfo = new RepairInfo(); 
				repairInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				repairInfo.setAuthor(getUserInfo);
				repairInfo.setAddress(results.getString("address"));
				repairInfo.setTitle(results.getString("title"));
				repairInfo.setDescription(results.getString("description"));
				repairInfo.setPiclist(results.getString("piclist"));
				repairInfo.setCreatetime(results.getString("createtime"));
				repairInfo.setPrice(results.getDouble("price"));
				repairInfo.setRepairtype(results.getString("repairtype"));
				repairInfoList.add(repairInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}
		return repairInfoList;
	}

	public List<RepairInfo> GetOwnRepairInfo(String loginname, String kind) {
		// TODO Auto-generated method stub
		List<RepairInfo> repairInfoList = new ArrayList<RepairInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			repairQuery = connection.prepareStatement("SELECT * FROM repairinfo where authorname='" + loginname + "' and kind='" + kind + "' ORDER BY id DESC");
			ResultSet results = repairQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				RepairInfo repairInfo = new RepairInfo(); 
				repairInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				repairInfo.setAuthor(getUserInfo);
				repairInfo.setAddress(results.getString("address"));
				repairInfo.setTitle(results.getString("title"));
				repairInfo.setDescription(results.getString("description"));
				repairInfo.setPiclist(results.getString("piclist"));
				repairInfo.setCreatetime(results.getString("createtime"));
				repairInfo.setPrice(results.getDouble("price"));
				repairInfo.setRepairtype(results.getString("repairtype"));
				repairInfoList.add(repairInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}
		return repairInfoList;
	}

	public RepairInfo GetRepairInfoById(int id) {
		// TODO Auto-generated method stub
		RepairInfo repairInfo = null;
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM repairinfo where id='" + id + "'";
			repairQuery = connection.prepareStatement(sql);
			results = repairQuery.executeQuery();
			if (results.next()) {
				repairInfo = new RepairInfo(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				repairInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				repairInfo.setAuthor(getUserInfo);
				repairInfo.setAddress(results.getString("address"));
				repairInfo.setTitle(results.getString("title"));
				repairInfo.setDescription(results.getString("description"));
				repairInfo.setPiclist(results.getString("piclist"));
				repairInfo.setCreatetime(results.getString("createtime"));
				repairInfo.setPrice(results.getDouble("price"));
				repairInfo.setRepairtype(results.getString("repairtype"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}

		return repairInfo;
	}

	public List<RepairInfo> GetRepairInfoByIdArray(int[] idArray) {
		// TODO Auto-generated method stub
		List<RepairInfo> repairInfoList = new ArrayList<RepairInfo>();
		for(int i=0;i<idArray.length;i++){
			repairInfoList.add(GetRepairInfoById(idArray[i]));
		}		
		return repairInfoList;
	}

	public List<RepairInfo> GetTypeRepairInfoByPage(String repairtype,int pagenum) {
		List<RepairInfo> repairInfoList = new ArrayList<RepairInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			repairQuery = connection.prepareStatement("SELECT * FROM repairinfo where repairtype='" + repairtype + "' ORDER BY id DESC");
			ResultSet results = repairQuery.executeQuery();
			
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				

				RepairInfo repairInfo = new RepairInfo(); 
				repairInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				repairInfo.setAuthor(getUserInfo);
				repairInfo.setAddress(results.getString("address"));
				repairInfo.setTitle(results.getString("title"));
				repairInfo.setDescription(results.getString("description"));
				repairInfo.setPiclist(results.getString("piclist"));
				repairInfo.setCreatetime(results.getString("createtime"));
				repairInfo.setPrice(results.getDouble("price"));
				repairInfo.setRepairtype(results.getString("repairtype"));
				repairInfoList.add(repairInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(repairQuery);
			DBcon.closeConnection(connection);
		}
		return repairInfoList;
	}

}
