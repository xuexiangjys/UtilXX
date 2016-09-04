package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDaoImpl implements ShopDao {
	private Connection connection;
	private PreparedStatement shopsQuery;
	private ResultSet results;
	public List<Shop> getshops() {
		List<Shop> shopsList = new ArrayList<Shop>();
		// 获取书籍数据集ResultSet results
		try {
			connection = DBcon.getConnction();
			shopsQuery = connection.prepareStatement("SELECT id, name, price,sale_num,address,pic FROM shop ORDER BY id");
			ResultSet results = shopsQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				Shop shop = new Shop(); // 对每行的图书数据创建一个图书封装类的实例
				// 循环将图书表中的每条记录封装为数据bean并添加到集合类中
				shop.setId(results.getInt("id"));
				shop.setName(results.getString("name"));
				shop.setPrice(results.getDouble("price"));
				shop.setSale_num(results.getInt("sale_num"));
				shop.setAddress(results.getString("address"));
				shop.setPic(results.getString("pic"));
				shopsList.add(shop); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopsQuery);
			DBcon.closeConnection(connection);
		}
		return shopsList;
	}
	
	public int add(Shop shopbean) {
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into shop(name, price, sale_num, address, pic) values(?,?,?,?,?)";
			shopsQuery = connection.prepareStatement(sql);
			shopsQuery.setString(1, shopbean.getName());
			shopsQuery.setDouble(2, shopbean.getPrice());
			shopsQuery.setInt(3, shopbean.getSale_num());
			shopsQuery.setString(4, shopbean.getAddress());
			shopsQuery.setString(5, shopbean.getPic());
			result = shopsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopsQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public int delete(int id) {
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from shop where id='" + id + "'";
			shopsQuery = connection.prepareStatement(sql);
			result = shopsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopsQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}
	
	public int update(Shop shopbean) {
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "update shop set name=?, price=?, ";
			sql += "sale_num=?, address=?, pic=?  where id=?";
			shopsQuery = connection.prepareStatement(sql);
			shopsQuery.setString(1, shopbean.getName());
			shopsQuery.setDouble(2, shopbean.getPrice());
			shopsQuery.setInt(3, shopbean.getSale_num());
			shopsQuery.setString(4, shopbean.getAddress());
			shopsQuery.setString(5, shopbean.getPic());
			shopsQuery.setInt(6, shopbean.getId());
			result = shopsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopsQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public Shop findById(int id) {
		Shop shop = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM shop where id='" + id + "'";
			shopsQuery = connection.prepareStatement(sql);
			results = shopsQuery.executeQuery();
			if (results.next()) {
				shop = new Shop(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				shop.setId(results.getInt("id"));
				shop.setName(results.getString("name"));
				shop.setPrice(results.getDouble("price"));
				shop.setSale_num(results.getInt("sale_num"));
				shop.setAddress(results.getString("address"));
				shop.setPic(results.getString("pic"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(shopsQuery);
			DBcon.closeConnection(connection);
		}

		return shop;
	}

	

	

}
