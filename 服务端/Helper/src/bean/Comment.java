package bean;


/**  
 * 评论
 * 创建时间：2016-2-19 下午5:52:04  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：Comment.java  
 **/
public class Comment {
	/** Key*/
	private int id;	
	/** 评论者*/
	private UserInfo user;
	/** 墙语的key*/
	private int qyid;	
	/** 评论内容*/
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public int getQyid() {
		return qyid;
	}
	public void setQyid(int qyid) {
		this.qyid = qyid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
