package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.FoodShop;
import dao.FoodShopDao;
import daoimpl.FoodShopDaoImpl;

public class FoodShopService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public FoodShopService() {
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
		
		FoodShop foodshop = null;
		try {
			foodshop = (FoodShop)JsonUtil.fromRequest(Json, FoodShop.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FoodShopDao foodshopDao = new FoodShopDaoImpl();
		
		if(mode.equals("Register")){
			if(foodshopDao.CheckFoodShopName(foodshop.getShopname())){
				 out.write("该商铺名已存在！");
			} else {
				if(foodshopDao.Register(foodshop)){
					  out.write("success");
				}else{
					  out.write("fail");
				}
			}
			
		} else if(mode.equals("Delete")){
			if(foodshopDao.Delete(foodshop.getId())){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
			
		} else if(mode.equals("GetFoodShopById")){
			FoodShop getFoodShop = foodshopDao.GetFoodShopById(foodshop.getId());
			String JsonResult = JsonUtil.toJson(getFoodShop);
			if(getFoodShop!=null){
				out.write(JsonResult);
			} else{
				out.write("fail");
			}
			
		} else if(mode.equals("GetFoodShopByTypeAndPage")){
			List<FoodShop> list=foodshopDao.GetFoodShopByTypeAndPage(foodshop.getType(),foodshop.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetTypeFoodShopByComplexCondition")){
			List<FoodShop> list=foodshopDao.GetTypeFoodShopByComplexCondition(foodshop.getCity(),foodshop.getType(),foodshop.getSortCondition(),foodshop.getFiltrate(),foodshop.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetFoodShopByPage")){
			List<FoodShop> list=foodshopDao.GetFoodShopByPage(foodshop.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);			
		} else if(mode.equals("GetFoodShopByType")){
			List<FoodShop> list=foodshopDao.GetFoodShopByType(foodshop.getType());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
			
		} else if(mode.equals("GetFoodShopByCityAndPage")){
			List<FoodShop> list=foodshopDao.GetFoodShopByCityAndPage(foodshop.getCity(),foodshop.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
			
		} else if(mode.equals("GetFoodShopByCityAndTypeAndPage")){
			List<FoodShop> list=foodshopDao.GetFoodShopByCityAndTypeAndPage(foodshop.getCity(),foodshop.getType(),foodshop.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
			
		}  else if(mode.equals("GetFoodShopByCityAndType")){
			List<FoodShop> list=foodshopDao.GetFoodShopByCityAndType(foodshop.getCity(),foodshop.getType());
			
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
