package dao;

import java.util.List;
import bean.RepairInfo;

public interface RepairDao {
	/**
	 * 发布维修信息
	 * @param visitInfo
	 * @return true :增加维修信息成功 false:增加维修信息失败
	 */
	public boolean Add(RepairInfo repairInfo);
	

	/**
	 * 删除维修信息
	 * @param id Key
	 * @return true :删除维修信息成功 false:删除维修信息失败
	 */
	public boolean Delete(int id);
		
	/**
	 * 获取指定用户发布的所有维修信息
	 * @param loginname
	 * @param kind
	 * @return 指定用户发布的所有维修信息
	 */
	public List<RepairInfo> GetOwnRepairInfo(String loginname,String kind);
	
	
	/**
	 * 根据id获取指定的维修信息
	 * @param id
	 * @return 指定的维修信息
	 */
	public RepairInfo GetRepairInfoById(int id);
	
	
	/**
	 * 根据id数组获取指定的维修信息
	 * @param idArray
	 * @return 维修信息的集合
	 */
	public List<RepairInfo> GetRepairInfoByIdArray(int[] idArray);
	
	
	/**
	 * 获取所有用户的发布的维修信息的集合
	 * @param kind
	 * @return 所有用户发布的维修信息的集合
	 */
	public List<RepairInfo> GetAllRepairInfo(String kind);
	
	/**
	 * 获取指定用户发布的所有维修信息
	 * @param loginname
	 * @param page
	 * @param kind
	 * @return 指定用户发布的所有维修信息
	 */
	public List<RepairInfo> GetOwnRepairInfoByPage(String loginname,int pagenum,String kind);
	
	/**
	 * 获取所有用户发布的维修信息的集合
	 * @param pagenum
	 * @param kind
	 * @return 所有用户发布的维修信息的集合
	 */
	public List<RepairInfo> GetAllRepairInfoByPage(int pagenum,String kind);
	
	/**
	 * 获取指定类型的维修信息的集合
	 * @param repairtype
	 * @param pagenum
	 * @param kind
	 * @return 指定类型的维修信息的集合
	 */
	public List<RepairInfo> GetTypeRepairInfoByPage(String repairtype,int pagenum);
}
