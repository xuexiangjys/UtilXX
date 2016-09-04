package bean;
import java.util.List;

public interface UserinfoDao {
	public List<Userinfo> getUsers();//获得用户列表
	public int add(Userinfo userbean); //添加用户
	public int update(Userinfo userbean); //修改用户信息
	public Userinfo Checkuser(Userinfo userbean);//检查用户是否存在
	public Userinfo LoginCheck(Userinfo userbean);//用户登录检验

}
