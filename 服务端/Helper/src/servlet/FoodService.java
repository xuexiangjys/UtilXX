package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.FoodInfo;
import bean.FoodType;
import dao.FoodInfoDao;
import daoimpl.FoodInfoDaoImpl;

public class FoodService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public FoodService() {
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
		
		FoodInfo foodinfo = null;
		try {
			foodinfo = (FoodInfo)JsonUtil.fromRequest(Json, FoodInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FoodInfoDao foodinfoDao = new FoodInfoDaoImpl();
		
		if(mode.equals("Add")){
			if(foodinfoDao.Add(foodinfo)){
				  out.write("success");
			}else{
				  out.write("fail");
			}			
			
		} else if(mode.equals("Delete")){
			if(foodinfoDao.Delete(foodinfo.getId())){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
			
		} else if(mode.equals("GetFoodInfoById")){
			FoodInfo getFoodInfo = foodinfoDao.GetFoodInfoById(foodinfo.getId());
			String JsonResult = JsonUtil.toJson(getFoodInfo);
			if(getFoodInfo != null){
				out.write(JsonResult);
			} else{
				out.write("fail");
			}
			
		} else if(mode.equals("GetFoodInfoByShopId")){
			List<FoodInfo> list=foodinfoDao.GetFoodInfoByShopId(foodinfo.getShopid());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetFoodTypeByShopId")){
			List<FoodType> list=foodinfoDao.GetFoodTypeByShopId(foodinfo.getShopid());
			
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
