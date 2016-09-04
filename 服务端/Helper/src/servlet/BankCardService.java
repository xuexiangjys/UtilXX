package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JsonUtil;
import bean.BankCard;
import dao.BankCardDao;
import daoimpl.BankCardDaoImpl;

public class BankCardService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public BankCardService() {
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
		
		BankCard bankCardinfo = null;
		
		try {
			bankCardinfo = (BankCard)JsonUtil.fromRequest(Json, BankCard.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BankCardDao bankCardDao = new BankCardDaoImpl();
		
		if(mode.equals("CheckCard")){
			if(bankCardDao.CheckCard(bankCardinfo.getCardId(),bankCardinfo.getPassword())){
				out.write("success");
			} else{
				out.write("fail");
			}
		} else if(mode.equals("UpdateMoneyChange")){
			 if(bankCardDao.UpdateMoneyChange(bankCardinfo.getCardId(),bankCardinfo.getMoney())){
				  out.write("success");
			 }else{
				  out.write("fail");
			 }
			 
		} else if(mode.equals("UpdatePassword")){
			if(bankCardDao.UpdatePassword(bankCardinfo.getCardId(),bankCardinfo.getPassword())){
				out.write("success");
			} else{
				out.write("fail");
			}
		} else if(mode.equals("GetCardInfoByCardId")){
			BankCard bankCard = bankCardDao.GetCardInfoByCardId(bankCardinfo.getCardId());
			if(bankCard!=null){
				String json=JsonUtil.toJson(bankCard);
				out.write(json);
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
