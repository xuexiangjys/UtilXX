package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import daoimpl.CommentDaoImpl;

import util.JsonUtil;
import bean.Comment;

public class CommentService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CommentService() {
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
		
		Comment commentinfo = null;
		
		try {
			commentinfo = (Comment)JsonUtil.fromRequest(Json, Comment.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CommentDao commmentDao = new CommentDaoImpl();
		
		if(mode.equals("GetAllComment")){
			List<Comment> list=commmentDao.GetAllComment(commentinfo.getQyid());		
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("Add")){
			 if(commmentDao.Add(commentinfo)){
				  out.write("success");
			 }else{
				  out.write("fail");
			 }
			 
		} else if(mode.equals("Delete")){
			if(commmentDao.Delete(commentinfo.getId())){
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
