package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.JsonResult;
import bean.Userinfo;
import bean.UserinfoDao;
import bean.UserinfoDaoImpl;

public class Register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Register() {
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
		
		//获取post参数 
		StringBuffer sb = new StringBuffer() ; 
		InputStream is = request.getInputStream(); 
		InputStreamReader isr = new InputStreamReader(is);   
		BufferedReader br = new BufferedReader(isr); 
		String s = "" ; 
		while((s=br.readLine())!=null){ 
		sb.append(s) ; 
		} 
		String str =sb.toString();
		System.out.println(str);
		
        Userinfo userinfo =(Userinfo)JsonUtil.fromRequest(str, Userinfo.class);
        
        UserinfoDao userinfoDao=new UserinfoDaoImpl();       
        Userinfo usercheck=userinfoDao.Checkuser(userinfo);
        JsonResult jsonResult=new JsonResult();
        if(usercheck==null){
	        int result=userinfoDao.add(userinfo);
	        
	        if(result>0){
	        	jsonResult.setCode(JsonResult.CODE_SUCCESS);
	        }
	        else{
	        	jsonResult.setCode(JsonResult.CODE_ERROR);
	        	jsonResult.setInfo("fail");
	        }
        }
        else{
        	jsonResult.setCode(JsonResult.CODE_ERROR);
        	jsonResult.setInfo("该用户已存在！");
        }
        String responsecode=JsonUtil.toJson(jsonResult);
        out.write(responsecode);
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
