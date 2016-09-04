package bean;

/**
 * 应用信息
 */
public class AppInfo {
	/** Key*/
	private int id;	
	/** 版本号*/
	private String versionname;	
	/** 版本描述*/
	private String versiondescribe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVersionname() {
		return versionname;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public String getVersiondescribe() {
		return versiondescribe;
	}
	public void setVersiondescribe(String versiondescribe) {
		this.versiondescribe = versiondescribe;
	}
	
}
