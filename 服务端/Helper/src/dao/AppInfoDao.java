package dao;

import bean.AppInfo;

public interface AppInfoDao {
	

   /**
    * 判断当前版本是否需要更新
    * @param version 版本号
    * @return true：需要更新 false：不需要更新
    */
    public boolean isNeedUpdate(String versionname);
    
    /**
     * 获取最新app版本的主要信息
     * @return
     */
    public AppInfo getNewAppInfo();
    
    
}
