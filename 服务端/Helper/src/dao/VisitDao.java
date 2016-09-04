package dao;

import java.util.List;

import bean.VisitInfo;

public interface VisitDao {

	/**
	 * 发布上门服务信息
	 * @param visitInfo
	 * @return true :增加上门服务成功 false:增加上门服务失败
	 */
	public boolean Add(VisitInfo visitInfo);
	

	/**
	 * 删除上门服务
	 * @param id Key
	 * @return true :删除上门服务成功 false:删除上门服务失败
	 */
	public boolean Delete(int id);
		
	/**
	 * 获取指定用户发布的所有上门服务的信息
	 * @param loginname
	 * @return 指定用户发布的所有上门服务的信息
	 */
	public List<VisitInfo> GetOwnVisitInfo(String loginname);
	
	/**
	 * 获取指定用户发布的所有上门服务的信息
	 * @param loginname
	 * @param page
	 * @return 指定用户发布的所有上门服务的信息
	 */
	public List<VisitInfo> GetOwnVisitInfoByPage(String loginname,int pagenum);
	
	/**
	 * 根据id获取指定的上门服务的信息
	 * @param id
	 * @return 指定的上门服务的信息
	 */
	public VisitInfo GetVisitInfoById(int id);
	
	
	/**
	 * 根据id数组获取指定的上门服务信息的集合
	 * @param idArray
	 * @return 上门服务信息的集合
	 */
	public List<VisitInfo> GetVisitInfoByIdArray(int[] idArray);
	
	
	/**
	 * 获取所有用户的发布的上门服务信息的集合
	 * @return 所有用户发布的上门服务信息的集合
	 */
	public List<VisitInfo> GetAllVisitInfo();
	
	/**
	 * 获取所有用户发布的上门服务信息的集合
	 * @param pagenum
	 * @return 所有用户发布的上门服务信息的集合
	 */
	public List<VisitInfo> GetAllVisitInfoByPage(int pagenum);
	
	/**
	 * 获取指定类型的上门服务信息的集合
	 * @param visittype
	 * @param pagenum
	 * @return 指定类型的上门服务信息的集合
	 */
	public List<VisitInfo> GetTypeVisitInfoByPage(String visittype,int pagenum);
	
	/**
	 * 点赞数据更新：love数量+1
	 * @param id
	 * @return 
	 */
	public boolean onClickLove(int id);
}
