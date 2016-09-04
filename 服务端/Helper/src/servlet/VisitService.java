package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JsonUtil;
import bean.VisitInfo;
import dao.VisitDao;
import daoimpl.VisitDaoImpl;

public class VisitService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public VisitService() {
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
		
		VisitInfo visitInfo = null;
		try {
			visitInfo = (VisitInfo)JsonUtil.fromRequest(Json, VisitInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VisitDao visitDao=new VisitDaoImpl();
		
		if(mode.equals("Add")){
			if(visitDao.Add(visitInfo)){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("Delete")){
			if(visitDao.Delete(visitInfo.getId())){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("onClickLove")){
			if(visitDao.onClickLove(visitInfo.getId())){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("GetOwnVisitInfo")){
			List<VisitInfo> list=visitDao.GetOwnVisitInfo(visitInfo.getAuthor().getLoginname());
			VisitInfo visitInfos=null;
			for(int i=0;i<list.size();i++){
				visitInfos=list.get(i);
				System.out.println(JsonUtil.toJson(visitInfos));
			}
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetOwnVisitInfoByPage")){
			List<VisitInfo> list=visitDao.GetOwnVisitInfoByPage(visitInfo.getAuthor().getLoginname(),visitInfo.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetAllVisitInfoByPage")){
			List<VisitInfo> list=visitDao.GetAllVisitInfoByPage(visitInfo.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetTypeVisitInfoByPage")){
			List<VisitInfo> list=visitDao.GetTypeVisitInfoByPage(visitInfo.getVisittype(),visitInfo.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
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
