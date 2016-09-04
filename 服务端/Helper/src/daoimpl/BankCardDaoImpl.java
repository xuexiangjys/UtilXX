package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bean.BankCard;
import bean.DBcon;
import dao.BankCardDao;

public class BankCardDaoImpl implements BankCardDao {

	private Connection connection;
	private PreparedStatement bankCardQuery;
	private ResultSet results;
	
	public boolean CheckCard(String cardid, String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM bankcard where cardid=? and password=?";
			bankCardQuery = connection.prepareStatement(sql);
			bankCardQuery.setString(1, cardid);
			bankCardQuery.setString(2, password);
			results = bankCardQuery.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(bankCardQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public BankCard GetCardInfoByCardId(String cardid) {
		// TODO Auto-generated method stub
		BankCard bankCard = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM bankcard where cardid='" + cardid + "'";
			bankCardQuery = connection.prepareStatement(sql);
			results = bankCardQuery.executeQuery();
			if (results.next()) {
				bankCard = new BankCard(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				bankCard.setId(results.getInt("id"));
				bankCard.setCardId(results.getString("cardid"));
				bankCard.setName(results.getString("name"));				
				bankCard.setIdentitycard(results.getString("identitycard"));
				bankCard.setPassword(results.getString("password"));
				bankCard.setMoney(results.getDouble("money"));
				bankCard.setBanktype(results.getString("banktype"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(bankCardQuery);
			DBcon.closeConnection(connection);
		}

		return bankCard;
	}

	public double GetCardMoney(String cardid) {
		// TODO Auto-generated method stub
		double cardMoney = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT money FROM bankcard where cardid='" + cardid + "'";
			bankCardQuery = connection.prepareStatement(sql);
			results = bankCardQuery.executeQuery();
			if (results.next()) {
				cardMoney = results.getDouble("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(bankCardQuery);
			DBcon.closeConnection(connection);
		}

		return cardMoney;
	}

	public boolean Register(BankCard bankCard) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into bankcard(cardid, name, identitycard, password, money, banktype) values(?,?,?,?,?,?)";
			bankCardQuery = connection.prepareStatement(sql);
			bankCardQuery.setString(1, bankCard.getCardId());
			bankCardQuery.setString(2, bankCard.getName());
			bankCardQuery.setString(3, bankCard.getIdentitycard());
			bankCardQuery.setString(4, bankCard.getPassword());
			bankCardQuery.setDouble(5, bankCard.getMoney());
			bankCardQuery.setString(6, bankCard.getBanktype());
			int SqlResult = bankCardQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(bankCardQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateMoney(String cardid, double money) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update bankcard set money=? where cardid=?";
			bankCardQuery = connection.prepareStatement(sql);
			bankCardQuery.setDouble(1, money);
			bankCardQuery.setString(2, cardid);
			int SqlResult = bankCardQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(bankCardQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateMoneyChange(String cardid, double moneyChange) {
		// TODO Auto-generated method stub
		boolean result = false;
		double money = GetCardMoney(cardid) + moneyChange;
		result = UpdateMoney(cardid,money);		
		return result;
	}

	public boolean UpdatePassword(String cardid, String newpassword) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update bankcard set password=? where cardid=?";
			bankCardQuery = connection.prepareStatement(sql);
			bankCardQuery.setString(1, newpassword);
			bankCardQuery.setString(2, cardid);
			int SqlResult = bankCardQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(bankCardQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

}
