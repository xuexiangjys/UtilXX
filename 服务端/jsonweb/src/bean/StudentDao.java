package bean;
import java.util.List;
public interface StudentDao {
			public List<Student> getstudents();//获得学生列表
			public int add(Student studentbean); //添加学生
			public int delete(String sno); //删除学生
			public int update(Student studentbean); //修改学生信息
			public Student findBySno(String sno);//根据学号查询学生信息
}
