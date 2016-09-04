package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.Shop;
import bean.ShopDao;
import bean.ShopDaoImpl;
import bean.Student;
import bean.StudentDao;
import bean.StudentDaoImpl;

public class taoBaoQuery extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public taoBaoQuery() {
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
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();
		
		ShopDao shopDao=new ShopDaoImpl();
		List<Shop> list=shopDao.getshops();
		
		Shop shops=null;
		for(int i=0;i<list.size();i++){
			shops=list.get(i);
			System.out.println(shops.toString());
		}

		/*//应用Gson获得json 内容
		Gson gson = new Gson();
		String json = gson.toJson(list, new TypeToken<List<Student>>() {
		}.getType());*/
		String json=JsonUtil.toJson(list);
		System.out.println(json);
		out.write(json);
		out.close();
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

				doGet(request,response);
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
