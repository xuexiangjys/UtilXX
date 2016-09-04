package dao;

import java.util.List;

import bean.FeedBackInfo;

public interface FeedBackDao {
    /**
     * 增加用户反馈信息
     * @param feedbackinfo
     * @return
     */
    public boolean Add(FeedBackInfo feedbackinfo);
    
    /**
     * 获取所有用户反馈的信息集合
     * @param pagenum 页数
     * @return
     */
    public List<FeedBackInfo> GetAllFeedBackInfoByPage(int pagenum);
    
    /**
     * 获取指定用户反馈的所有信息的集合
     * @param loginname 用户名
     * @param pagenum 页数
     * @return
     */
    public List<FeedBackInfo> GetOwnFeedBackInfoByPage(String loginname,int pagenum);
    
    /**
	 * 删除反馈信息
	 * @param id Key
	 * @return true :删除成功 false:删除失败
	 */
	public boolean Delete(int id);
}
