package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.AppInfo;
import bean.DBcon;
import dao.AppInfoDao;

public class AppInfoDaoImpl implements AppInfoDao {
	
	private Connection connection;
	private PreparedStatement appinfoQuery;
	private ResultSet results;

	public AppInfo getNewAppInfo() {
		AppInfo appinfo = null;
		try {
			connection = DBcon.getConnction();
			appinfoQuery = connection.prepareStatement("SELECT id, versionname, versiondescribe FROM appinfo ORDER BY id DESC");
		    results = appinfoQuery.executeQuery();
			// 读取行数据
			if (results.next()) {
				appinfo = new AppInfo(); 
				appinfo.setId(results.getInt("id"));
				appinfo.setVersionname(results.getString("versionname"));
				appinfo.setVersiondescribe(results.getString("versiondescribe"));
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(appinfoQuery);
			DBcon.closeConnection(connection);
		}
		return appinfo;
	}

	public boolean isNeedUpdate(String versionname) {
		// TODO Auto-generated method stub
		boolean result = false;
		AppInfo appinfo = null;
		try {
			connection = DBcon.getConnction();
			appinfoQuery = connection.prepareStatement("SELECT id, versionname, versiondescribe FROM appinfo ORDER BY id DESC");
			results = appinfoQuery.executeQuery();
			// 读取行数据
			if (results.next()) {
				appinfo = new AppInfo(); 
				appinfo.setId(results.getInt("id"));
				appinfo.setVersionname(results.getString("versionname"));
				appinfo.setVersiondescribe(results.getString("versiondescribe"));
				if(!appinfo.getVersionname().equals(versionname)){
					result = true;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(appinfoQuery);
			DBcon.closeConnection(connection);
	    }
		return result;
	}

}
