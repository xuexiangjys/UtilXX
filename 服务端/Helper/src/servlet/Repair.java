package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.RepairInfo;
import dao.RepairDao;
import daoimpl.RepairDaoImpl;

public class Repair extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Repair() {
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
		
		RepairInfo repairInfo = null;
		try {
			repairInfo = (RepairInfo)JsonUtil.fromRequest(Json, RepairInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RepairDao repairDao=new RepairDaoImpl();
		
		if(mode.equals("Add")){
			if(repairDao.Add(repairInfo)){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("Delete")){
			if(repairDao.Delete(repairInfo.getId())){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("GetOwnRepairInfo")){
			List<RepairInfo> list=repairDao.GetOwnRepairInfo(repairInfo.getAuthor().getLoginname(),repairInfo.getKind());
			RepairInfo repairInfos=null;
			for(int i=0;i<list.size();i++){
				repairInfos=list.get(i);
				System.out.println(JsonUtil.toJson(repairInfos));
			}
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetOwnRepairInfoByPage")){
			List<RepairInfo> list=repairDao.GetOwnRepairInfoByPage(repairInfo.getAuthor().getLoginname(),repairInfo.getPageNum(),repairInfo.getKind());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetAllRepairInfoByPage")){
			List<RepairInfo> list=repairDao.GetAllRepairInfoByPage(repairInfo.getPageNum(),repairInfo.getKind());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetTypeRepairInfoByPage")){
			List<RepairInfo> list=repairDao.GetTypeRepairInfoByPage(repairInfo.getRepairtype(),repairInfo.getPageNum());
			
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
