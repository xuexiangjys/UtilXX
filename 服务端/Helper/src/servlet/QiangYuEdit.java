package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QiangYuDao;
import daoimpl.QiangYuDaoImpl;
import util.JsonUtil;
import bean.QiangYu;

public class QiangYuEdit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public QiangYuEdit() {
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
		
		QiangYu qiangYu = null;
		try {
			qiangYu = (QiangYu)JsonUtil.fromRequest(Json, QiangYu.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QiangYuDao qiangyuDao=new QiangYuDaoImpl();
		
		if(mode.equals("Add")){
			if(qiangyuDao.Add(qiangYu)){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("Delete")){
			if(qiangyuDao.Delete(qiangYu.getId())){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("onClickLove")){
			if(qiangyuDao.onClickLove(qiangYu.getId())){
				  out.write("success");
			  }else{
				  out.write("fail");
			  }
		} else if(mode.equals("GetOwnQiangYu")){
			List<QiangYu> list=qiangyuDao.GetOwnQiangYu(qiangYu.getAuthor().getLoginname());
			QiangYu qiangYus=null;
			for(int i=0;i<list.size();i++){
				qiangYus=list.get(i);
				System.out.println(qiangYus.toString());
			}
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetOwnQiangYuByPage")){
			List<QiangYu> list=qiangyuDao.GetOwnQiangYuByPage(qiangYu.getAuthor().getLoginname(),qiangYu.getPageNum());
			
			String json=JsonUtil.toJson(list);
			out.write(json);
		} else if(mode.equals("GetAllQiangYuByPage")){
			List<QiangYu> list=qiangyuDao.GetAllQiangYuByPage(qiangYu.getPageNum());
			
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
