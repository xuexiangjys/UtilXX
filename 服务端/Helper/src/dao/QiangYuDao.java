package dao;

import java.util.List;

import bean.QiangYu;

public interface QiangYuDao {
	
	/**
	 * 发布墙语
	 * @param qiangyu
	 * @return true :增加墙语成功 false:增加墙语失败
	 */
	public boolean Add(QiangYu qiangyu);
	

	/**
	 * 删除墙语
	 * @param id Key
	 * @return true :删除墙语成功 false:删除墙语失败
	 */
	public boolean Delete(int id);
		
	/**
	 * 获取指定用户的发布的所有墙语
	 * @param loginname
	 * @return 指定用户的所有墙语
	 */
	public List<QiangYu> GetOwnQiangYu(String loginname);
	
	/**
	 * 获取指定用户的发布的所有墙语
	 * @param loginname
	 * @param page
	 * @return 指定用户的所有墙语
	 */
	public List<QiangYu> GetOwnQiangYuByPage(String loginname,int pagenum);
	
	/**
	 * 根据id获取指定的墙语
	 * @param id
	 * @return 指定的墙语
	 */
	public QiangYu GetQiangYuById(int id);
	
	
	/**
	 * 根据id数组获取指定的墙语集合
	 * @param idArray
	 * @return 墙语集合
	 */
	public List<QiangYu> GetQiangYuByIdArray(int[] idArray);
	
	
	/**
	 * 获取所有用户的发布的墙语
	 * @return 所有用户的发布的墙语
	 */
	public List<QiangYu> GetAllQiangYu();
	
	/**
	 * 获取所有用户的发布的墙语
	 * @param pagenum
	 * @return 所有用户的发布的墙语
	 */
	public List<QiangYu> GetAllQiangYuByPage(int pagenum);
	
	/**
	 * 点赞数据更新：love数量+1
	 * @param id
	 * @return 
	 */
	public boolean onClickLove(int id);
	

}
