package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.DBcon;
import bean.Promotion;
import dao.PromotionDao;

public class PromotionDaoImpl implements PromotionDao {

	private Connection connection;
	private PreparedStatement promotionQuery;
	private ResultSet results;
	
	public boolean Add(Promotion promotion) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into promotion(shopid, firstorder, fullreduction, discount, vouchers, preorder, fullofgifts, freedistribution) values(?,?,?,?,?,?,?,?)";
			promotionQuery = connection.prepareStatement(sql);
			promotionQuery.setInt(1, promotion.getShopid());
			promotionQuery.setString(2, promotion.getFirstorder());
			promotionQuery.setString(3, promotion.getFullreduction());
			promotionQuery.setString(4, promotion.getDiscount());
			promotionQuery.setString(5, promotion.getVouchers());
			promotionQuery.setString(6, promotion.getPreorder());
			promotionQuery.setString(7, promotion.getFullofgifts());
			promotionQuery.setString(8, promotion.getFreedistribution());
			int SqlResult = promotionQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(promotionQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from promotion where id='" + id + "'";
			promotionQuery = connection.prepareStatement(sql);
			int SqlResult = promotionQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(promotionQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public Promotion getPromotionByShopId(int shopid) {
		// TODO Auto-generated method stub
		Promotion promotion = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM promotion where shopid='" + shopid + "'";
			promotionQuery = connection.prepareStatement(sql);
			results = promotionQuery.executeQuery();
			if (results.next()) {
				promotion = new Promotion(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				promotion.setId(results.getInt("id"));
				promotion.setShopid(results.getInt("shopid"));
				promotion.setFirstorder(results.getString("firstorder"));
				promotion.setFullreduction(results.getString("fullreduction"));
				promotion.setDiscount(results.getString("discount"));
				promotion.setVouchers(results.getString("vouchers"));
				promotion.setPreorder(results.getString("preorder"));
				promotion.setFullofgifts(results.getString("fullofgifts"));
				promotion.setFreedistribution(results.getString("freedistribution"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(promotionQuery);
			DBcon.closeConnection(connection);
		}

		return promotion;
	}

	public boolean updatePromotionInfo(Promotion promotion) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update promotion set firstorder=?, fullreduction=?, discount=?, vouchers=?, preorder=?, fullofgifts=?, freedistribution=? where id=?";
			promotionQuery = connection.prepareStatement(sql);
			promotionQuery.setString(1, promotion.getFirstorder());
			promotionQuery.setString(2, promotion.getFullreduction());
			promotionQuery.setString(3, promotion.getDiscount());
			promotionQuery.setString(4, promotion.getVouchers());
			promotionQuery.setString(5, promotion.getPreorder());
			promotionQuery.setString(6, promotion.getFullofgifts());
			promotionQuery.setString(7, promotion.getFreedistribution());
			promotionQuery.setInt(8, promotion.getId());
			int SqlResult = promotionQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(promotionQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

}
