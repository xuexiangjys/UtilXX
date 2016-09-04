package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.Comment;
import bean.DBcon;
import bean.UserInfo;
import dao.CommentDao;
import dao.UserInfoDao;

public class CommentDaoImpl implements CommentDao {
	private Connection connection;
	private PreparedStatement commentQuery;
	private ResultSet results;
	
	public boolean Add(Comment commentinfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into comment(loginname, qyid, content) values(?,?,?)";
			commentQuery = connection.prepareStatement(sql);
			commentQuery.setString(1, commentinfo.getUser().getLoginname());
			commentQuery.setInt(2, commentinfo.getQyid());
			commentQuery.setString(3, commentinfo.getContent());
			int SqlResult = commentQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(commentQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from comment where id='" + id + "'";
			commentQuery = connection.prepareStatement(sql);
			int SqlResult = commentQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(commentQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<Comment> GetAllComment(int qyid) {
		// TODO Auto-generated method stub
		List<Comment> commentList = new ArrayList<Comment>();
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		try {
			connection = DBcon.getConnction();
			commentQuery = connection.prepareStatement("SELECT * FROM comment where qyid='" + qyid + "' ORDER BY id");
			ResultSet results = commentQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				Comment comment = new Comment(); 
				comment.setId(results.getInt("id"));
				
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(results.getString("loginname"));	
				
				comment.setUser(getUserInfo);
				comment.setQyid(results.getInt("qyid"));
				comment.setContent(results.getString("content"));	
				commentList.add(comment); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(commentQuery);
			DBcon.closeConnection(connection);
		}
		return commentList;
	}

}
