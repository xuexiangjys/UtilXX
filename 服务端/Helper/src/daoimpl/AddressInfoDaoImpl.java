package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressInfo;
import bean.DBcon;
import dao.AddressInfoDao;

public class AddressInfoDaoImpl implements AddressInfoDao {
	
	private Connection connection;
	private PreparedStatement addressinfoQuery;
	private ResultSet results;

	public boolean Add(AddressInfo addressinfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into addressinfo(loginname, consignee, contact, area, address, postcode, isdefault) values(?,?,?,?,?,?,?)";
			addressinfoQuery = connection.prepareStatement(sql);
			addressinfoQuery.setString(1, addressinfo.getLoginname());
			addressinfoQuery.setString(2, addressinfo.getConsignee());
			addressinfoQuery.setString(3, addressinfo.getContact());
			addressinfoQuery.setString(4, addressinfo.getArea());
			addressinfoQuery.setString(5, addressinfo.getAddress());
			addressinfoQuery.setString(6, addressinfo.getPostcode());
			addressinfoQuery.setString(7, addressinfo.getIsdefault());
			int SqlResult = addressinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(addressinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from addressinfo where id='" + id + "'";
			addressinfoQuery = connection.prepareStatement(sql);
			int SqlResult = addressinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(addressinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public boolean Update(AddressInfo addressinfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update addressinfo set consignee=?, contact=?, area=?, address=?, postcode=? where id=?";
			addressinfoQuery = connection.prepareStatement(sql);
			addressinfoQuery.setString(1, addressinfo.getConsignee());
			addressinfoQuery.setString(2, addressinfo.getContact());
			addressinfoQuery.setString(3, addressinfo.getArea());
			addressinfoQuery.setString(4, addressinfo.getAddress());
			addressinfoQuery.setString(5, addressinfo.getPostcode());
			addressinfoQuery.setInt(6, addressinfo.getId());
			int SqlResult = addressinfoQuery.executeUpdate();
			if(SqlResult>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(addressinfoQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}
	
	public List<AddressInfo> GetAllAddress(String loginname) {
		// TODO Auto-generated method stub
		List<AddressInfo> addressesList = new ArrayList<AddressInfo>();
		try {
			connection = DBcon.getConnction();
			addressinfoQuery = connection.prepareStatement("SELECT id, loginname, consignee, contact, area, address, postcode, isdefault FROM addressinfo where loginname='" + loginname + "' ORDER BY id");
			ResultSet results = addressinfoQuery.executeQuery();
			// 读取行数据
			while (results.next()) {
				AddressInfo addressinfo = new AddressInfo(); 
				addressinfo.setId(results.getInt("id"));
				addressinfo.setLoginname(results.getString("loginname"));
				addressinfo.setConsignee(results.getString("consignee"));
				addressinfo.setContact(results.getString("contact"));
				addressinfo.setArea(results.getString("area"));
				addressinfo.setAddress(results.getString("address"));
				addressinfo.setPostcode(results.getString("postcode"));
				addressinfo.setIsdefault(results.getString("isdefault"));
				addressesList.add(addressinfo); // 将封将类添加到数组中
			}
		}
		// 处理数据库异常
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(addressinfoQuery);
			DBcon.closeConnection(connection);
		}
		return addressesList;
	}

	public boolean UpdateIsdefault(int id, String loginname) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			String sql = "update addressinfo set isdefault=? where loginname=? and isdefault=?";
			addressinfoQuery = connection.prepareStatement(sql);
			addressinfoQuery.setString(1, "");
			addressinfoQuery.setString(2, loginname);
			addressinfoQuery.setString(3, "yes");
			int SqlResult = addressinfoQuery.executeUpdate();
			if(SqlResult>0){
				String sql1 = "update addressinfo set isdefault=? where id=?";
				addressinfoQuery = connection.prepareStatement(sql1);
				addressinfoQuery.setString(1, "yes");
				addressinfoQuery.setInt(2, id);
				int SqlResult1 = addressinfoQuery.executeUpdate();
				if(SqlResult1>0){
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(addressinfoQuery);
			DBcon.closeConnection(connection);
		}		
		return result;
	}

	public boolean isHasdefaultAddress(String loginname) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			connection = DBcon.getConnction();
			addressinfoQuery = connection.prepareStatement("SELECT * FROM addressinfo where loginname='" + loginname + "' and isdefault='" + "yes" + "'");
			ResultSet results = addressinfoQuery.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(addressinfoQuery);
			DBcon.closeConnection(connection);
		}		
		return result;
	}
}
