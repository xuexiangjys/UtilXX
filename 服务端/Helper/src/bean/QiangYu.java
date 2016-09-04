package bean;

/**  
 * 墙语
 * 创建时间：2016-2-19 下午5:34:48  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：QiangYu.java  
 **/
public class QiangYu extends HttpConnect{
	/** Key*/
	private int id;
	/** 作者*/
	private UserInfo author;
	/** 墙语内容*/
	private String content;
	/** 墙语图片*/
	private String pic;
	/** 赞的个数*/
	private int love;
	/** 发布时间*/
	private String createtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getAuthor() {
		return author;
	}
	public void setAuthor(UserInfo author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
