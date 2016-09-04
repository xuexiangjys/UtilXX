package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bean.DBcon;
import bean.UserInfo;
import dao.UserInfoDao;

public class UserInfoDaoImpl implements UserInfoDao {
	
	private Connection connection;
	private PreparedStatement userinfoQuery;
	private ResultSet results;

	public boolean CheckUser(String loginname) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM userinfo where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, loginname);
			results = userinfoQuery.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean LoginCheck(String loginname,String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM userinfo where loginname=? and password=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, loginname);
			userinfoQuery.setString(2, password);
			results = userinfoQuery.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Register(UserInfo userbean) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into userinfo(loginname, phone, password, headphoto, nickname, sex, signature, realname, identitycard, ischeck) values(?,?,?,?,?,?,?,?,?,?)";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, userbean.getLoginname());
			userinfoQuery.setString(2, userbean.getPhone());
			userinfoQuery.setString(3, userbean.getPassword());
			userinfoQuery.setString(4, userbean.getHeadphoto());
			userinfoQuery.setString(5, userbean.getNickname());
			userinfoQuery.setString(6, userbean.getSex());
			userinfoQuery.setString(7, userbean.getSignature());
			userinfoQuery.setString(8, userbean.getRealname());
			userinfoQuery.setString(9, userbean.getIdentitycard());
			userinfoQuery.setString(10, userbean.getIscheck());		
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateHeadPhoto(String loginname, String headphoto) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set headphoto=? where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, headphoto);
			userinfoQuery.setString(2, loginname);
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateNickName(String loginname, String nickname) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set nickname=? where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, nickname);
			userinfoQuery.setString(2, loginname);
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdatePassword(String loginname, String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set password=? where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, password);
			userinfoQuery.setString(2, loginname);
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdatePhone(String loginname, String phone) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set phone=? where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, phone);
			userinfoQuery.setString(2, loginname);
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateSex(String loginname, String sex) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set sex=? where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, sex);
			userinfoQuery.setString(2, loginname);
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public UserInfo GetUserInfoByLoginname(String loginname) {
		// TODO Auto-generated method stub
		UserInfo userinfo = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM userinfo where loginname='" + loginname + "'";
			userinfoQuery = connection.prepareStatement(sql);
			results = userinfoQuery.executeQuery();
			if (results.next()) {
				userinfo = new UserInfo(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				userinfo.setLoginname(results.getString("loginname"));
				userinfo.setPhone(results.getString("phone"));
				userinfo.setPassword(results.getString("password"));
				userinfo.setHeadphoto(results.getString("headphoto"));
				userinfo.setNickname(results.getString("nickname"));
				userinfo.setSex(results.getString("sex"));
				userinfo.setSignature(results.getString("signature"));
				userinfo.setRealname(results.getString("realname"));
				userinfo.setIdentitycard(results.getString("identitycard"));
				userinfo.setIscheck(results.getString("ischeck"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}

		return userinfo;
	}

	public boolean UpdateSignature(String loginname, String signature) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set signature=? where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, signature);
			userinfoQuery.setString(2, loginname);
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateIdentificationStatus(String loginname,String realname, String identitycard) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set realname=?, identitycard=?, ischeck=? where loginname=?";
			userinfoQuery = connection.prepareStatement(sql);
			userinfoQuery.setString(1, realname);
			userinfoQuery.setString(2, identitycard);
			userinfoQuery.setString(3, "yes");
			userinfoQuery.setString(4, loginname);
			int SqlResult = userinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(userinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

}
