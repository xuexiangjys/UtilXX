package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.JsonUtil;
import bean.VisitInfo;
import dao.VisitDao;
import daoimpl.VisitDaoImpl;

public class UpLoadVisitServicePicture extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	/**
	 * Constructor of the object.
	 */
	public UpLoadVisitServicePicture() {
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String jsonstr = null; 
		try
		{	
			// 下面的代码开始使用Commons-UploadFile组件处理上传的文件数据
			FileItemFactory factory = new DiskFileItemFactory(); // 建立FileItemFactory对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");// 解决上传的文件名乱码  
	        upload.setFileSizeMax(1024 * 1024 * 1024);// 单个文件上传最大值是1M  
	        upload.setSizeMax(2048 * 1024 * 1024);//文件上传的总大小限制  
			// 分析请求，并得到上传文件的FileItem对象
			List<FileItem> items = upload.parseRequest(request);
			System.out.println("request:"+JsonUtil.toJson(items));
			// 从web.xml文件中的参数中得到上传文件的路径
			// 设置文件上传路径
			String uploadPath = this.getServletContext().getRealPath("/User/VisitService/");
			File file = new File(uploadPath);
			if (!file.exists())
			{
				file.mkdirs();
			}
			String filename = ""; // 上传文件保存到服务器的文件名
			InputStream is = null; // 当前上传文件的InputStream对象
			// 循环处理上传文件
			for (FileItem item : items)
			{
				// 处理普通的表单域
				if (item.isFormField())					
				{
					if (item.getFieldName().equals("Json")){
						jsonstr = JsonUtil.inputStream2String(item.getInputStream());
						System.out.println("jsonstr:"+jsonstr);
					} 
					continue;
				}
				// 处理上传文件
				else if (item.getName() != null && !item.getName().equals(""))
				{
					// 从客户端发送过来的上传文件路径中截取文件名
					filename = item.getName().substring(
							item.getName().lastIndexOf("\\") + 1);
					System.out.println("filename:"+filename);
					is = item.getInputStream(); // 得到上传文件的InputStream对象
				}
				// 将路径和上传文件名组合成完整的服务端路径
				filename = uploadPath +"\\"+ filename;
				// 如果服务器已经存在和上传文件同名的文件，则输出提示信息
				if (new File(filename).exists())
				{
					new File(filename).delete();
				}
				// 开始上传文件
				if (!filename.equals(""))
				{
					// 用FileOutputStream打开服务端的上传文件
					FileOutputStream fos = new FileOutputStream(filename);
					byte[] buffer = new byte[8192]; // 每次读8K字节
					int count = 0;
					// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
					while ((count = is.read(buffer)) > 0)
					{
						fos.write(buffer, 0, count); // 向服务端文件写入字节流
						
					}
					fos.close(); // 关闭FileOutputStream对象
					is.close(); // InputStream对象
					//将头像的地址保存到数据库中
				}
			}
		} catch (FileUploadException e)
		{
			e.printStackTrace();
			out.write("fail");
		}
		
		VisitInfo visitInfo = null;
		try {
			visitInfo = (VisitInfo)JsonUtil.fromRequest(jsonstr, VisitInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VisitDao visitDao=new VisitDaoImpl();
		if(visitInfo!=null){
			if(visitDao.Add(visitInfo)){
				out.write("success");
			} else{
				out.write("fail");
			}	
		} else{
			out.write("fail");
		}
		
		out.flush();
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
