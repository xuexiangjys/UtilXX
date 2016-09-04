package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JsonUtil;

import bean.DBcon;
import bean.FeedBackInfo;
import bean.UserInfo;
import dao.FeedBackDao;
import dao.UserInfoDao;

public class FeedBackDaoImpl implements FeedBackDao {

	private Connection connection;
	private PreparedStatement feedbackinfoQuery;
	private ResultSet results;
	
	public boolean Add(FeedBackInfo feedbackinfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into feedback(content, authorname, contact, submittime) values(?,?,?,?)";
			feedbackinfoQuery = connection.prepareStatement(sql);
			feedbackinfoQuery.setString(1, feedbackinfo.getContent());
			feedbackinfoQuery.setString(2, feedbackinfo.getAuthor().getLoginname());
			feedbackinfoQuery.setString(3, feedbackinfo.getContact());	
			feedbackinfoQuery.setString(4, feedbackinfo.getSubmittime());	
			int SqlResult = feedbackinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(feedbackinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<FeedBackInfo> GetAllFeedBackInfoByPage(int pagenum) {
		// TODO Auto-generated method stub
		List<FeedBackInfo> feedbackInfoList = new ArrayList<FeedBackInfo>();
		UserInfoDao userInfoDao = new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			feedbackinfoQuery = connection.prepareStatement("SELECT * FROM feedback ORDER BY id DESC");
			ResultSet results = feedbackinfoQuery.executeQuery();
			
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				FeedBackInfo feedBackInfo = new FeedBackInfo(); 
				feedBackInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				feedBackInfo.setAuthor(getUserInfo);
				feedBackInfo.setContent(results.getString("content"));
				feedBackInfo.setContact(results.getString("contact"));
				feedBackInfo.setSubmittime(results.getString("submittime"));
				feedbackInfoList.add(feedBackInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(feedbackinfoQuery);
			DBcon.closeConnection(connection);
		}
		return feedbackInfoList;
	}

	public List<FeedBackInfo> GetOwnFeedBackInfoByPage(String loginname,int pagenum) {
		// TODO Auto-generated method stub
		List<FeedBackInfo> feedbackInfoList = new ArrayList<FeedBackInfo>();
		UserInfoDao userInfoDao = new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			feedbackinfoQuery = connection.prepareStatement("SELECT * FROM feedback where authorname='" + loginname + "' ORDER BY id DESC");
			ResultSet results = feedbackinfoQuery.executeQuery();
			
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				FeedBackInfo feedBackInfo = new FeedBackInfo(); 
				feedBackInfo.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				feedBackInfo.setAuthor(getUserInfo);
				feedBackInfo.setContent(results.getString("content"));
				feedBackInfo.setContact(results.getString("contact"));
				feedBackInfo.setSubmittime(results.getString("submittime"));
				feedbackInfoList.add(feedBackInfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(feedbackinfoQuery);
			DBcon.closeConnection(connection);
		}
		return feedbackInfoList;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from feedback where id='" + id + "'";
			feedbackinfoQuery = connection.prepareStatement(sql);
			int SqlResult = feedbackinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(feedbackinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

}
