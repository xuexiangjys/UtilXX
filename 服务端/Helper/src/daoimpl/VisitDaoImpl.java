package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.JsonUtil;
import bean.DBcon;
import bean.UserInfo;
import bean.VisitInfo;
import dao.UserInfoDao;
import dao.VisitDao;

public class VisitDaoImpl implements VisitDao {
	private Connection connection;
	private PreparedStatement visitQuery;
	private ResultSet results;
	
	public boolean Add(VisitInfo visitInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into visitinfo(authorname, address, title, description, piclist, love, createtime, price, visittype) values(?,?,?,?,?,?,?,?,?)";
			visitQuery = connection.prepareStatement(sql);
			visitQuery.setString(1, visitInfo.getAuthor().getLoginname());
			visitQuery.setString(2, visitInfo.getAddress());
			visitQuery.setString(3, visitInfo.getTitle());
			visitQuery.setString(4, visitInfo.getDescription());
			visitQuery.setString(5, visitInfo.getPiclist());
			visitQuery.setInt(6, visitInfo.getLove());
			visitQuery.setString(7, visitInfo.getCreatetime());
			visitQuery.setDouble(8, visitInfo.getPrice());
			visitQuery.setString(9, visitInfo.getVisittype());
			int SqlResult = visitQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from visitinfo where id='" + id + "'";
			visitQuery = connection.prepareStatement(sql);
			int SqlResult = visitQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<VisitInfo> GetAllVisitInfo() {
		// TODO Auto-generated method stub
		List<VisitInfo> visitInfoList = new ArrayList<VisitInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			visitQuery = connection.prepareStatement("SELECT * FROM visitinfo ORDER BY id DESC");
			ResultSet results = visitQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				VisitInfo visitInfo = new VisitInfo(); 
				visitInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				visitInfo.setAuthor(getUserInfo);
				visitInfo.setAddress(results.getString("address"));
				visitInfo.setTitle(results.getString("title"));
				visitInfo.setDescription(results.getString("description"));
				visitInfo.setPiclist(results.getString("piclist"));
				visitInfo.setLove(results.getInt("love"));
				visitInfo.setCreatetime(results.getString("createtime"));
				visitInfo.setPrice(results.getDouble("price"));
				visitInfo.setVisittype(results.getString("visittype"));
				visitInfoList.add(visitInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return visitInfoList;
	}

	public List<VisitInfo> GetAllVisitInfoByPage(int pagenum) {
		// TODO Auto-generated method stub
		List<VisitInfo> visitInfoList = new ArrayList<VisitInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			visitQuery = connection.prepareStatement("SELECT * FROM visitinfo ORDER BY id DESC");
			ResultSet results = visitQuery.executeQuery();
			
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				VisitInfo visitInfo = new VisitInfo(); 
				visitInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				visitInfo.setAuthor(getUserInfo);
				visitInfo.setAddress(results.getString("address"));
				visitInfo.setTitle(results.getString("title"));
				visitInfo.setDescription(results.getString("description"));
				visitInfo.setPiclist(results.getString("piclist"));
				visitInfo.setLove(results.getInt("love"));
				visitInfo.setCreatetime(results.getString("createtime"));
				visitInfo.setPrice(results.getDouble("price"));
				visitInfo.setVisittype(results.getString("visittype"));
				visitInfoList.add(visitInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return visitInfoList;
	}

	public List<VisitInfo> GetOwnVisitInfo(String loginname) {
		// TODO Auto-generated method stub
		List<VisitInfo> visitInfoList = new ArrayList<VisitInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			visitQuery = connection.prepareStatement("SELECT * FROM visitinfo where authorname='" + loginname + "' ORDER BY id DESC");
			ResultSet results = visitQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				VisitInfo visitInfo = new VisitInfo(); 
				visitInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(loginname);	
				
				visitInfo.setAuthor(getUserInfo);
				visitInfo.setAddress(results.getString("address"));
				visitInfo.setTitle(results.getString("title"));
				visitInfo.setDescription(results.getString("description"));
				visitInfo.setPiclist(results.getString("piclist"));
				visitInfo.setLove(results.getInt("love"));
				visitInfo.setCreatetime(results.getString("createtime"));
				visitInfo.setPrice(results.getDouble("price"));
				visitInfo.setVisittype(results.getString("visittype"));
				visitInfoList.add(visitInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return visitInfoList;
	}

	public List<VisitInfo> GetOwnVisitInfoByPage(String loginname, int pagenum) {
		// TODO Auto-generated method stub
		List<VisitInfo> visitInfoList = new ArrayList<VisitInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			visitQuery = connection.prepareStatement("SELECT * FROM visitinfo where authorname='" + loginname + "' ORDER BY id DESC");
			ResultSet results = visitQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				VisitInfo visitInfo = new VisitInfo(); 
				visitInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(loginname);	
				
				visitInfo.setAuthor(getUserInfo);
				visitInfo.setAddress(results.getString("address"));
				visitInfo.setTitle(results.getString("title"));
				visitInfo.setDescription(results.getString("description"));
				visitInfo.setPiclist(results.getString("piclist"));
				visitInfo.setLove(results.getInt("love"));
				visitInfo.setCreatetime(results.getString("createtime"));
				visitInfo.setPrice(results.getDouble("price"));
				visitInfo.setVisittype(results.getString("visittype"));
				visitInfoList.add(visitInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return visitInfoList;
	}

	public VisitInfo GetVisitInfoById(int id) {
		// TODO Auto-generated method stub
		VisitInfo visitInfo = null;
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM visitinfo where id='" + id + "'";
			visitQuery = connection.prepareStatement(sql);
			results = visitQuery.executeQuery();
			if (results.next()) {
				visitInfo = new VisitInfo(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				visitInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				visitInfo.setAuthor(getUserInfo);
				visitInfo.setAddress(results.getString("address"));
				visitInfo.setTitle(results.getString("title"));
				visitInfo.setDescription(results.getString("description"));
				visitInfo.setPiclist(results.getString("piclist"));
				visitInfo.setLove(results.getInt("love"));
				visitInfo.setCreatetime(results.getString("createtime"));
				visitInfo.setPrice(results.getDouble("price"));
				visitInfo.setVisittype(results.getString("visittype"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}

		return visitInfo;
	}

	public List<VisitInfo> GetVisitInfoByIdArray(int[] idArray) {
		// TODO Auto-generated method stub
		List<VisitInfo> visitInfoList = new ArrayList<VisitInfo>();
		for(int i=0;i<idArray.length;i++){
			visitInfoList.add(GetVisitInfoById(idArray[i]));
		}		
		return visitInfoList;
	}

	public boolean onClickLove(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		VisitInfo visitInfo = GetVisitInfoById(id);
		int love = visitInfo.getLove()+1;		
		try {
			connection = DBcon.getConnction();
			String sql = "update visitinfo set love=? where id=?";
			visitQuery = connection.prepareStatement(sql);
			visitQuery.setInt(1, love);
			visitQuery.setInt(2, id);
			int SqlResult = visitQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<VisitInfo> GetTypeVisitInfoByPage(String visittype, int pagenum) {
		List<VisitInfo> visitInfoList = new ArrayList<VisitInfo>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			visitQuery = connection.prepareStatement("SELECT * FROM visitinfo where visittype='" + visittype + "' ORDER BY id DESC");
			ResultSet results = visitQuery.executeQuery();
			
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				VisitInfo visitInfo = new VisitInfo(); 
				visitInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				visitInfo.setAuthor(getUserInfo);
				visitInfo.setAddress(results.getString("address"));
				visitInfo.setTitle(results.getString("title"));
				visitInfo.setDescription(results.getString("description"));
				visitInfo.setPiclist(results.getString("piclist"));
				visitInfo.setLove(results.getInt("love"));
				visitInfo.setCreatetime(results.getString("createtime"));
				visitInfo.setPrice(results.getDouble("price"));
				visitInfo.setVisittype(results.getString("visittype"));
				visitInfoList.add(visitInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(visitQuery);
			DBcon.closeConnection(connection);
		}
		return visitInfoList;
	}

}
