package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.AddressInfo;
import dao.AddressInfoDao;
import daoimpl.AddressInfoDaoImpl;

public class UserAddress extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserAddress() {
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

	    AddressInfo addressInfo = null;
		try {
			addressInfo = (AddressInfo)JsonUtil.fromRequest(Json, AddressInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AddressInfoDao addressInfoDao=new AddressInfoDaoImpl();  
		
		if(mode.equals("GetAllAddress")){
			List<AddressInfo> list=addressInfoDao.GetAllAddress(addressInfo.getLoginname());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
				
		} else if(mode.equals("Add")){
			if(addressInfoDao.isHasdefaultAddress(addressInfo.getLoginname())){
				addressInfo.setIsdefault("");
			} else{
				addressInfo.setIsdefault("yes");
			}
			if(addressInfoDao.Add(addressInfo)){
				  out.write("success");
			}else{
				  out.write("fail");
			}
				
		} else if(mode.equals("Delete")){			
			if(addressInfoDao.Delete(addressInfo.getId())){
				out.write("success");
			} else{
				out.write("fail");
			}
				
		} else if(mode.equals("Update")){			
			if(addressInfoDao.Update(addressInfo)){
				out.write("success");
			} else{
				out.write("fail");
			}
				
		} else if(mode.equals("UpdateIsdefault")){			
			if(addressInfoDao.UpdateIsdefault(addressInfo.getId(),addressInfo.getLoginname())){
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
