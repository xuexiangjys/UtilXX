package dao;

import java.util.List;

import push.PushMessage;

public interface PushServiceDao {
	/**
	 * 获得推送类型
	 * @param usercode 用户唯一标识
	 * @param pushtime 需要推送的时间
	 * @return
	 */
	public List<String> getPushMode(String usercode,String pushtime);//获得推送类型
	
	/**
	 * 获得推送信息
	 * @param createtime 推送消息的推送时间
	 * @return
	 */
	public List<PushMessage> getPushMessages(String createtime);//获得推送信息
	
}
