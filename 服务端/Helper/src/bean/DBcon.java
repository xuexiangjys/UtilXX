//数据库连接类，方法：public static Connection getConnction()
//注意：检查数据库名、用户名、密码 需正确
package bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBcon {

	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private static final String DATABASE_URL =
		"jdbc:mysql://localhost:3306/helper?useUnicode=true&characterEncoding=UTF-8";

	private static final String DATABASE_USRE = "root";

	private static final String DATABASE_PASSWORD = "xuexiang";
	// 返回连接
	public static Connection getConnction() {
		Connection dbConnection = null;
		try {
			Class.forName(DRIVER_CLASS);
			dbConnection = DriverManager.getConnection(DATABASE_URL,
					DATABASE_USRE, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dbConnection;
	}

	
	 // 关闭连接
	public static void closeConnection(Connection dbConnection) {
		try {
			if (dbConnection != null && (!dbConnection.isClosed())) {
				dbConnection.close();
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

	}

	 // 关闭结果集
	public static void closeResultSet(ResultSet res) {
		try {
			if (res != null) {
				res.close();
				res = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStatement(PreparedStatement pStatement) {
		try {
			if (pStatement != null) {
				pStatement.close();
				pStatement = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
