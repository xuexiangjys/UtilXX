package bean;

/**  
 * 创建时间：2016-2-28 下午7:26:12  
 * 项目名称：helpertest  
 * @author xuexiang
 * 文件名称：HttpConnect.java  
 **/
public class HttpConnect {
	/** 请求页数*/
	private int pageNum;
	/** 排序条件*/
	private String sortCondition;
	/** 筛选条件*/
	private String filtrate;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getSortCondition() {
		return sortCondition;
	}

	public void setSortCondition(String sortCondition) {
		this.sortCondition = sortCondition;
	}

	public String getFiltrate() {
		return filtrate;
	}

	public void setFiltrate(String filtrate) {
		this.filtrate = filtrate;
	}
}
