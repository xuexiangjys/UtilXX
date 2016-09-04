package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import bean.BankCard;
import bean.DBcon;
import bean.HelperPay;
import dao.BankCardDao;
import dao.HelperPayDao;

public class HelperPayDaoImpl implements HelperPayDao {
	private Connection connection;
	private PreparedStatement helperPayQuery;
	private ResultSet results;
	
	public boolean CheckPay(String username, String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM helperpay where username=? and password=?";
			helperPayQuery = connection.prepareStatement(sql);
			helperPayQuery.setString(1, username);
			helperPayQuery.setString(2, password);
			results = helperPayQuery.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public HelperPay GetHelperPayInfoByUsername(String username) {
		// TODO Auto-generated method stub
		HelperPay helperPay = null;
		BankCardDao bankCardDao=new BankCardDaoImpl();  
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM helperpay where username='" + username + "'";
			helperPayQuery = connection.prepareStatement(sql);
			results = helperPayQuery.executeQuery();
			if (results.next()) {
				helperPay = new HelperPay(); // 每次创建一个封装类的实例
				// 将数据表中的一条记录数据添加到封装类中
				helperPay.setId(results.getInt("id"));
				helperPay.setUsername(results.getString("username"));
				helperPay.setPassword(results.getString("password"));
				helperPay.setMoney(results.getDouble("money"));
				
				String bankcardIdStr = results.getString("bankcardlist");   //获取绑定银行卡的卡号集合
				List <String> bankcardIdlist = StringToList(bankcardIdStr,";");
				
				List <BankCard> bankcardlist = new ArrayList <BankCard> (); //根据银行卡的卡号集合获取银行卡集合
				for(String cardId : bankcardIdlist){ 
					bankcardlist.add(bankCardDao.GetCardInfoByCardId(cardId));
				} 
				helperPay.setBankcardlist(bankcardlist);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}

		return helperPay;
	}

	public double GetPayMoney(String username) {
		// TODO Auto-generated method stub
		double payMoney = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT money FROM helperpay where username='" + username + "'";
			helperPayQuery = connection.prepareStatement(sql);
			results = helperPayQuery.executeQuery();
			if (results.next()) {
				payMoney = results.getDouble("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}

		return payMoney;
	}

	public boolean Register(HelperPay helperPay) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into helperpay(username, password, money, bankcardlist) values(?,?,?,?)";
			helperPayQuery = connection.prepareStatement(sql);
			helperPayQuery.setString(1, helperPay.getUsername());
			helperPayQuery.setString(2, helperPay.getPassword());
			helperPayQuery.setDouble(3, helperPay.getMoney());
			helperPayQuery.setString(4, helperPay.getBankcardlist().get(0).getCardId());
			int SqlResult = helperPayQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateMoney(String username, double money) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update helperpay set money=? where username=?";
			helperPayQuery = connection.prepareStatement(sql);
			helperPayQuery.setDouble(1, money);
			helperPayQuery.setString(2, username);
			int SqlResult = helperPayQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean UpdateMoneyChange(String username, double moneyChange) {
		// TODO Auto-generated method stub
		boolean result = false;
		double money = GetPayMoney(username) + moneyChange;
		result = UpdateMoney(username,money);		
		return result;
	}

	public boolean UpdatePassword(String username, String newpassword) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update bankcard set password=? where username=?";
			helperPayQuery = connection.prepareStatement(sql);
			helperPayQuery.setString(1, newpassword);
			helperPayQuery.setString(2, username);
			int SqlResult = helperPayQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}
	
	public boolean AddBankCardBind(String username,String newCardid) {
		// TODO Auto-generated method stub
		boolean result = false;		
		String oldbankcardIdStr = GetBankcardListStr(username);
		List<String> oldBankcardIdlist = StringToList(oldbankcardIdStr,";");
		
		List <String> newBankcardIdlist = new ArrayList <String> (); 
		for(String cardId : oldBankcardIdlist){
			if(!cardId.equals("")){
			  newBankcardIdlist.add(cardId);
			}	
		} 
		newBankcardIdlist.add(newCardid);
		String newbankcardlistStr = ListToString(newBankcardIdlist,";");
		result = UpdateBankcardlist(username,newbankcardlistStr);
			
		return result;
	}
	
	public List<BankCard> GetBankcardList(String username) {
		// TODO Auto-generated method stub
		List <String> bankcardIdlist = StringToList(GetBankcardListStr(username),";");	
		
		List <BankCard> bankcardlist = new ArrayList <BankCard> (); //根据银行卡的卡号集合获取银行卡集合
		BankCardDao bankCardDao=new BankCardDaoImpl();  
		for(String cardId : bankcardIdlist){ 
			bankcardlist.add(bankCardDao.GetCardInfoByCardId(cardId));
		} 
		return bankcardlist;
	}

	public String GetBankcardListStr(String username) {
		// TODO Auto-generated method stub
		String bankcardIdStr = "";
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT bankcardlist FROM helperpay where username='" + username + "'";
			helperPayQuery = connection.prepareStatement(sql);
			results = helperPayQuery.executeQuery();
			if (results.next()) {
				bankcardIdStr = results.getString("bankcardlist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}

		return bankcardIdStr;
	}

	
	public boolean UpdateBankcardlist(String username, String newbankcardlist) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update bankcard set bankcardlist=? where username=?";
			helperPayQuery = connection.prepareStatement(sql);
			helperPayQuery.setString(1, newbankcardlist);
			helperPayQuery.setString(2, username);
			int SqlResult = helperPayQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(helperPayQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}
	
	
	/**
	 * 根据分隔符将String转换为List
	 * @param str
	 * @param separator 分隔符
	 * @return
	 */
	private List<String> StringToList(String str, String separator){
		List <String> list = new ArrayList <String> (); 
		list = Arrays.asList(str.split(separator)); 
		return list;
	}
	
	/**
	 * 根据分隔符将List转换为String
	 * @param list
	 * @param separator
	 * @return
	 */
	private String ListToString(List<String> list, String separator) {    
		StringBuilder sb = new StringBuilder();    
		for (int i = 0; i < list.size(); i++) {        
			sb.append(list.get(i)).append(separator);    
			}    
		return sb.toString().substring(0,sb.toString().length()-1);
	}

	
	

	

}
