package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JsonUtil;
import bean.DBcon;
import bean.QiangYu;
import bean.UserInfo;
import dao.QiangYuDao;
import dao.UserInfoDao;

public class QiangYuDaoImpl implements QiangYuDao {

	private Connection connection;
	private PreparedStatement qiangyuQuery;
	private ResultSet results;
	
	
	public boolean Add(QiangYu qiangyu) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into qiangyu(authorname, content, pic, love, createtime) values(?,?,?,?,?)";
			qiangyuQuery = connection.prepareStatement(sql);
			qiangyuQuery.setString(1, qiangyu.getAuthor().getLoginname());
			qiangyuQuery.setString(2, qiangyu.getContent());
			qiangyuQuery.setString(3, qiangyu.getPic());
			qiangyuQuery.setInt(4, qiangyu.getLove());
			qiangyuQuery.setString(5, qiangyu.getCreatetime());
			int SqlResult = qiangyuQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from qiangyu where id='" + id + "'";
			qiangyuQuery = connection.prepareStatement(sql);
			int SqlResult = qiangyuQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<QiangYu> GetOwnQiangYu(String loginname) {
		// TODO Auto-generated method stub
		List<QiangYu> QiangYuList = new ArrayList<QiangYu>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			qiangyuQuery = connection.prepareStatement("SELECT id, content, pic, love, createtime FROM qiangyu where authorname='" + loginname + "' ORDER BY id DESC");
			ResultSet results = qiangyuQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				QiangYu qiangYu = new QiangYu(); 
				qiangYu.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(loginname);	
				
				qiangYu.setAuthor(getUserInfo);
				qiangYu.setContent(results.getString("content"));
				qiangYu.setPic(results.getString("pic"));
				qiangYu.setLove(results.getInt("love"));
				qiangYu.setCreatetime(results.getString("createtime"));
				QiangYuList.add(qiangYu); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}
		return QiangYuList;
	}

	public List<QiangYu> GetAllQiangYu() {
		// TODO Auto-generated method stub
		List<QiangYu> QiangYuList = new ArrayList<QiangYu>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			qiangyuQuery = connection.prepareStatement("SELECT id, authorname, content, pic, love, createtime FROM qiangyu ORDER BY id DESC");
			ResultSet results = qiangyuQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				QiangYu qiangYu = new QiangYu(); 
				qiangYu.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				qiangYu.setAuthor(getUserInfo);
				qiangYu.setContent(results.getString("content"));
				qiangYu.setPic(results.getString("pic"));
				qiangYu.setLove(results.getInt("love"));
				qiangYu.setCreatetime(results.getString("createtime"));
				QiangYuList.add(qiangYu); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}
		return QiangYuList;
	}

	public QiangYu GetQiangYuById(int id) {
		// TODO Auto-generated method stub
		QiangYu qiangyu = null;
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM qiangyu where id='" + id + "'";
			qiangyuQuery = connection.prepareStatement(sql);
			results = qiangyuQuery.executeQuery();
			if (results.next()) {
				qiangyu = new QiangYu(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				qiangyu.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				qiangyu.setAuthor(getUserInfo);
				qiangyu.setContent(results.getString("content"));
				qiangyu.setPic(results.getString("pic"));
				qiangyu.setLove(results.getInt("love"));
				qiangyu.setCreatetime(results.getString("createtime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}

		return qiangyu;
	}

	public List<QiangYu> GetQiangYuByIdArray(int[] idArray) {
		// TODO Auto-generated method stub
		List<QiangYu> QiangYuList = new ArrayList<QiangYu>();
		for(int i=0;i<idArray.length;i++){
			QiangYuList.add(GetQiangYuById(idArray[i]));
		}		
		return QiangYuList;
	}

	public boolean onClickLove(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		QiangYu qiangyu = GetQiangYuById(id);
		int love = qiangyu.getLove()+1;		
		try {
			connection = DBcon.getConnction();
			String sql = "update qiangyu set love=? where id=?";
			qiangyuQuery = connection.prepareStatement(sql);
			qiangyuQuery.setInt(1, love);
			qiangyuQuery.setInt(2, id);
			int SqlResult = qiangyuQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<QiangYu> GetOwnQiangYuByPage(String loginname, int pagenum) {
		// TODO Auto-generated method stub
		List<QiangYu> QiangYuList = new ArrayList<QiangYu>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			qiangyuQuery = connection.prepareStatement("SELECT id, content, pic, love, createtime FROM qiangyu where authorname='" + loginname + "' ORDER BY id DESC");
			ResultSet results = qiangyuQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				
				QiangYu qiangYu = new QiangYu(); 
				qiangYu.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(loginname);	
				
				qiangYu.setAuthor(getUserInfo);
				qiangYu.setContent(results.getString("content"));
				qiangYu.setPic(results.getString("pic"));
				qiangYu.setLove(results.getInt("love"));
				qiangYu.setCreatetime(results.getString("createtime"));
				QiangYuList.add(qiangYu); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}
		return QiangYuList;
	}

	public List<QiangYu> GetAllQiangYuByPage(int pagenum) {
		// TODO Auto-generated method stub
		List<QiangYu> QiangYuList = new ArrayList<QiangYu>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		int page = -1;
		try {
			connection = DBcon.getConnction();
			qiangyuQuery = connection.prepareStatement("SELECT id, authorname, content, pic, love, createtime FROM qiangyu ORDER BY id DESC");
			ResultSet results = qiangyuQuery.executeQuery();
			// 读取行数据
			for(int i = 0;results.next();i++) {               
				if((i % JsonUtil.ONE_PAGE_NUM) == 0){
					page++;
				}
				if(page != pagenum){
					continue;
				}
				QiangYu qiangYu = new QiangYu(); 
				qiangYu.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("authorname"));	
				
				qiangYu.setAuthor(getUserInfo);
				qiangYu.setContent(results.getString("content"));
				qiangYu.setPic(results.getString("pic"));
				qiangYu.setLove(results.getInt("love"));
				qiangYu.setCreatetime(results.getString("createtime"));
				QiangYuList.add(qiangYu); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(qiangyuQuery);
			DBcon.closeConnection(connection);
		}
		return QiangYuList;
	}

}
