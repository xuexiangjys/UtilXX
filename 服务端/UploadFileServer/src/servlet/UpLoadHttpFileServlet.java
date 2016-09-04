package servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UpLoadHttpFileServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpLoadHttpFileServlet() {
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

		request.setCharacterEncoding("UTF-8"); // 设置处理请求参数的编码格式
		response.setContentType("text/html;charset=UTF-8"); // 设置Content-Type字段值
		PrintWriter responseout = response.getWriter();
		  try {  
	            System.out.println("IP:" + request.getRemoteAddr());  
	            // 1、创建工厂类：DiskFileItemFactory  
	            DiskFileItemFactory facotry = new DiskFileItemFactory();  
	            String tempDir = System.getProperty("java.io.tmpdir");
	            facotry.setRepository(new File(tempDir));//设置临时文件存放目录  
	            // 2、创建核心解析类：ServletFileUpload  
	            ServletFileUpload upload = new ServletFileUpload(facotry);  
	            upload.setHeaderEncoding("UTF-8");// 解决上传的文件名乱码  
	            upload.setFileSizeMax(1024 * 1024 * 1024);// 单个文件上传最大值是1M  
	            upload.setSizeMax(2048 * 1024 * 1024);//文件上传的总大小限制  
	  
	            // 3、判断用户的表单提交方式是不是multipart/form-data  
	            boolean bb = upload.isMultipartContent(request);  
	            if (!bb) {  
	                return;  
	            }  
	            // 4、是：解析request对象的正文内容List<FileItem>  
	            List<FileItem> items = upload.parseRequest(request);  
	            String uploadPath = this.getServletContext().getRealPath("/uploadHttpFile/");
	            System.out.println("上传服务器的根目录:" + uploadPath);  
	            File file = new File(uploadPath);
				if (!file.exists())
				{
					file.mkdir();
				}
	            for (FileItem item : items) {  
	                if (item.isFormField()) {  
	                    // 5、判断是否是普通表单：打印看看  
	                    String fieldName = item.getFieldName();// 请求参数名  
	                    String fieldValue = item.getString("UTF-8");// 请求参数值  
	                    System.out.println(fieldName + "=" + fieldValue);  
	                } else {  
	                    // 6、上传表单：得到输入流，处理上传：保存到服务器的某个目录中，保存时的文件名是啥？  
	                    String fileName = item.getName();// 得到上传文件的名称 C:\Documents  
	                                                        // and  
	                                                        // Settings\shc\桌面\a.txt  
	                                                        // a.txt  
	                    //解决用户没有选择文件上传的情况  
	                    if(fileName==null||fileName.trim().equals("")){  
	                        continue;  
	                    }  
	                    fileName = fileName  
	                            .substring(fileName.lastIndexOf("\\") + 1);  
	                    String newFileName =uploadPath +"\\"+ fileName;
	                    System.out.println("上传的文件名是：" + fileName);
	                    if (new File(newFileName).exists())
	        			{
	        				new File(newFileName).delete();
	        				System.out.println("文件已存在！");
	        			}
	                    InputStream in = item.getInputStream();  
	                    /*String savePath = makeDir(storePath, fileName) + "\\"  
	                            + newFileName;  */
	                    OutputStream out = new FileOutputStream(newFileName);  
	                    byte b[] = new byte[1024];  
	                    int len = -1;  
	                    while ((len = in.read(b)) != -1) {  
	                        out.write(b, 0, len);  
	                    }  
	                    in.close();  
	                    out.close();  
	                    item.delete();//删除临时文件  
	                    responseout.write("success");
	                }  
	            }  
	        }catch(FileUploadBase.FileSizeLimitExceededException e){  
	           /* request.setAttribute("message", "单个文件大小不能超出5M");  
	            request.getRequestDispatcher("/message.jsp").forward(request,  
	                    response);  */
	        	  responseout.write("failure");
	        }catch(FileUploadBase.SizeLimitExceededException e){  
	           /* request.setAttribute("message", "总文件大小不能超出7M");  
	            request.getRequestDispatcher("/message.jsp").forward(request,  
	                    response);  */
	        	  responseout.write("failure");
	    }catch (Exception e) {  
	           /* e.printStackTrace();  
	            request.setAttribute("message", "上传失败");  
	            request.getRequestDispatcher("/message.jsp").forward(request,  
	                    response);  */
	    	  responseout.write("failure");
	        }  
	    }  
	  
/*	    // WEB-INF/upload/1/3 打散存储目录  
	    private String makeDir(String storePath, String fileName) {  
	        int hashCode = fileName.hashCode();// 得到文件名的hashcode码  
	        int dir1 = hashCode & 0xf;// 取hashCode的低4位 0~15  
	        int dir2 = (hashCode & 0xf0) >> 4;// 取hashCode的高4位 0~15  
	        String path = storePath + "\\" + dir1 + "\\" + dir2;  
	        File file = new File(path);  
	        if (!file.exists())  
	            file.mkdirs();  
	        System.out.println("存储路径是"+path);  
	        return path;  
	}*/

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
