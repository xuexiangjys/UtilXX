package bean;

/**
 *反馈信息类
 */
public class FeedBackInfo extends HttpConnect{
	/** Key*/
	private int id;	
	/** 反馈意见*/
	private String content;
	/** 反馈的用户*/
	private UserInfo author;	
	/** 联系方式*/
	private String contact;
	/** 反馈时间*/
	private String submittime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserInfo getAuthor() {
		return author;
	}
	public void setAuthor(UserInfo author) {
		this.author = author;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getSubmittime() {
		return submittime;
	}
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	
}
