package push;

/**  
 * 轮询服务请求类
 * 创建时间：2016-2-3 上午11:20:03  
 * 项目名称：pollingService  
 * @author xuexiang
 * 文件名称：PushServiceReq.java  
 **/
public class PushServiceReq {
	/** 请求数据的时间*/
	private String RepCreateDate;
	/** 请求类型*/
	private String RequestType;
	/** 用户设备唯一号*/
	private String UserCode;
	public String getRepCreateDate() {
		return RepCreateDate;
	}
	public void setRepCreateDate(String repCreateDate) {
		RepCreateDate = repCreateDate;
	}
	public String getRequestType() {
		return RequestType;
	}
	public void setRequestType(String requestType) {
		RequestType = requestType;
	}
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
}
