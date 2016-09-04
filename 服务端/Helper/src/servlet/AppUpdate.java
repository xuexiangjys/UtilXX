package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AppInfoDao;
import daoimpl.AppInfoDaoImpl;
import util.JsonUtil;
import bean.AppInfo;

public class AppUpdate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AppUpdate() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		String mode=request.getParameter("mode");  
		String Json=request.getParameter("Json");
		System.out.println("mode:"+mode+",Json:"+Json);
		AppInfo appinfo = null;
		try {
			appinfo = (AppInfo)JsonUtil.fromRequest(Json, AppInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppInfoDao appInfoDao=new AppInfoDaoImpl();  
		
		if(mode.equals("getNewAppInfo")){
			AppInfo getAppInfo = appInfoDao.getNewAppInfo();				
			if(getAppInfo!=null){
				String JsonResult = JsonUtil.toJson(getAppInfo);
				out.write(JsonResult);
			} else{
				out.write("当前已是最新版本，无需更新！");
			}
		} else if(mode.equals("isNeedUpdate")){			
			if(appInfoDao.isNeedUpdate(appinfo.getVersionname())){
				out.write("success");
			} else{
				out.write("fail");
			}			
		} else {
			out.write("fail");
		}
		
		
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
