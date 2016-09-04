package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.UserInfo;

import dao.UserInfoDao;
import daoimpl.UserInfoDaoImpl;

public class User extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public User() {
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
		UserInfo userinfo = null;
		try {
			userinfo = (UserInfo)JsonUtil.fromRequest(Json, UserInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		
		if(mode.equals("Register")){
		  if(!userInfoDao.CheckUser(userinfo.getLoginname())){
				if(userInfoDao.Register(userinfo)){
					out.write("注册成功！");
				} else{
					out.write("注册失败！");
				}
		  }else{
			  out.write("该用户已存在！");
		  }
			
		} else if(mode.equals("CheckUser")){			
			if(userInfoDao.CheckUser(userinfo.getLoginname())){
				out.write("fail");
			} else{
				out.write("success");
			}
			
		} else if(mode.equals("LoginCheck")){
			if(userInfoDao.LoginCheck(userinfo.getLoginname(),userinfo.getPassword())){
				UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(userinfo.getLoginname());				
				if(getUserInfo!=null){
					String JsonResult = JsonUtil.toJson(getUserInfo);
					out.write(JsonResult);
				} else{
					out.write("查询不到您的信息！");
				}
			} else{
				out.write("用户名或密码错误！");
			}
			
		} else if(mode.equals("GetUserInfoByLoginname")){
			UserInfo getUserInfo = userInfoDao.GetUserInfoByLoginname(userinfo.getLoginname());
			String JsonResult = JsonUtil.toJson(getUserInfo);
			if(getUserInfo!=null){
				out.write(JsonResult);
			} else{
				out.write("fail");
			}
			
		} else if(mode.equals("UpdateNickName")){
			if(userInfoDao.UpdateNickName(userinfo.getLoginname(),userinfo.getNickname())){
				out.write("success");
			} else{
				out.write("fail");
			}
			
		} else if(mode.equals("UpdateSex")){
			if(userInfoDao.UpdateSex(userinfo.getLoginname(),userinfo.getSex())){
				out.write("success");
			} else{
				out.write("fail");
			}
			
		} else if(mode.equals("UpdatePhone")){
			if(userInfoDao.UpdatePhone(userinfo.getLoginname(),userinfo.getPhone())){
				out.write("success");
			} else{
				out.write("fail");
			}
			
		} else if(mode.equals("UpdatePassword")){
			if(userInfoDao.UpdatePassword(userinfo.getLoginname(),userinfo.getPassword())){
				out.write("success");
			} else{
				out.write("fail");
			}
			
		} else if(mode.equals("UpdateSignature")){
			if(userInfoDao.UpdateSignature(userinfo.getLoginname(),userinfo.getSignature())){
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
