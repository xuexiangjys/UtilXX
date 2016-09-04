<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<script type="text/javascript">
function reloadImage(t){
   t.src="./servlet/ImageServlet?flag="+Math.random();   
}
</script>
<head>
    <base href="<%=basePath%>">
  </head>
	<body>
		<center>
          <tr><td><img SRC=images/top.jpg style=""></img></td></tr>
		<form  action="checkUser.jsp" name="Regsiter" method="post">
			<table>
	            <tr style="background-color: rgb(192, 192, 192);"><td colspan="2" align="center"><font color="red" size="5"  style="font-family:simhei" face="华文行楷"><font face="楷体">学<font color="#ff8000">生</font><font color="#ffff00">信</font><font color="#00ff00">息</font><font color="#0000ff">管</font><font color="#ff80c0">理</font><font color="#800080">系</font><font color="#808000">统</font><font color="#400040">登</font><font color="#ff80ff">陆</font></font></td></tr>
    			<tr><td style="background-color: rgb(255, 255, 0);"><font face="楷体">登录名</font>:</td><td style="background-color: rgb(192, 192, 192);"><input type="text" name="loginName"></td></tr>
				<tr><td style="background-color: rgb(255, 128, 0);"><font face="楷体" >密码: </font></td><td style="background-color: rgb(192, 192, 192);"><input type="password" name="password">	</td></tr>
                  <tr ><td align="left" style="background-color: rgb(0, 255, 0);"><font face="楷体">用户类型</font>:</td> 
        <td style="background-color: rgb(192, 192, 192);"> <select name="userkind"> 
              <option value="student">学生</option> 
              <option value="teacher">老师</option> 
              <option value="admin">管理员</option>             
             </select>
        </td> </tr> 
                <tr><td style="background-color: rgb(255, 128, 255);"><font face="楷体">验证码:</font></td>
                    <td style="background-color: rgb(192, 192, 192);"><input type="text" name="checkcode">
                        <img src="./servlet/ImageServlet" align="middle" alt="看不清，请点击这里！" onclick="reloadImage(this)" >
                       <%//session中生成的正确验证码保存在“piccode”属性中 %>
					</td>
				</tr>
                <tr align="right" style="background-color: rgb(192, 192, 192);"><td colspan="2"><input type="submit" value="登录">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; <font face="楷体" color="#00ff00" style="background-color: rgb(255, 255, 128);"><a href="usercreate.jsp">注册</a></font> </td></tr>
			</table>
			</form>
		</center>
	</body>
</html>
