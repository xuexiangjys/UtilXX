
package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDaoImpl implements StudentDao {
	private Connection connection;
	private PreparedStatement studentsQuery;
	private ResultSet results;
	public List<Student> getstudents() {
		List<Student> studentsList = new ArrayList<Student>();
		// 获取书籍数据集ResultSet results
		try {
			connection = DBcon.getConnction();
			studentsQuery = connection.prepareStatement("SELECT sno, sname, ssex,sage,"
					+ " dno, contact, homeaddr " + " FROM student ORDER BY sno");
			ResultSet results = studentsQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				Student student = new Student(); // 对每行的图书数据创建一个图书封装类的实例
				// 循环将图书表中的每条记录封装为数据bean并添加到集合类中
				student.setSno(results.getString("sno"));
				student.setSname(results.getString("sname"));
				student.setSsex(results.getString("ssex"));
				student.setSage(results.getInt("sage"));
				student.setDno(results.getString("dno"));
				student.setContact(results.getString("contact"));
				student.setHomeaddr(results.getString("homeaddr"));
				studentsList.add(student); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return studentsList;
	}

	public int add(Student studentbean) { // 利用封装类的实例向表student中插入记录
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into student(sno, sname, ssex, ";
			sql += "sage, dno, contact, homeaddr) values(?,?,?,?,?,?,?)";
			studentsQuery = connection.prepareStatement(sql);
			studentsQuery.setString(1, studentbean.getSno());
			studentsQuery.setString(2, studentbean.getSname());
			studentsQuery.setString(3, studentbean.getSsex());
			studentsQuery.setInt(4, studentbean.getSage());
			studentsQuery.setString(5, studentbean.getDno());
			studentsQuery.setString(6, studentbean.getContact());
			studentsQuery.setString(7, studentbean.getHomeaddr());
			result = studentsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public int delete(String sno) { // 根据学号sno删除学生记录
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from student where sno='" + sno + "'";
			studentsQuery = connection.prepareStatement(sql);
			result = studentsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public int update(Student studentbean) { // 利用封装类的实例更新表student中记录
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "update student set sname=?, ssex=?, ";
			sql += "sage=?, dno=?, contact=?, homeaddr=?  where sno=?";
			studentsQuery = connection.prepareStatement(sql);
			studentsQuery.setString(1, studentbean.getSname());
			studentsQuery.setString(2, studentbean.getSsex());
			studentsQuery.setInt(3, studentbean.getSage());
			studentsQuery.setString(4, studentbean.getDno());
			studentsQuery.setString(5, studentbean.getContact());
			studentsQuery.setString(6, studentbean.getHomeaddr());
			studentsQuery.setString(7, studentbean.getSno());
			result = studentsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return result;

	}

	public Student findBySno(String sno) { // 根据学号sno查找学生信息
		Student student = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM student where sno='" + sno + "'";
			studentsQuery = connection.prepareStatement(sql);
			results = studentsQuery.executeQuery();
			if (results.next()) {
				student = new Student(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				student.setSno(results.getString("sno"));
				student.setSname(results.getString("sname"));
				student.setSsex(results.getString("ssex"));
				student.setSage(results.getInt("sage"));
				student.setDno(results.getString("dno"));
				student.setContact(results.getString("contact"));
				student.setHomeaddr(results.getString("homeaddr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}

		return student;
	}

}
