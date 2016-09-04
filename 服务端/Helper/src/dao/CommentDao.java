package dao;

import java.util.List;
import bean.Comment;

public interface CommentDao {
	
	/**
	 * 根据墙语id获取某一墙语的全部评论
	 * @param qyid 墙语的id
	 * @return 评论的集合
	 */
	public List<Comment> GetAllComment(int qyid);
	
	/**
	 * 删除评论
	 * @param id Key
	 * @return true :删除成功 false:删除失败
	 */
	public boolean Delete(int id);
	
	
	/**
	 * 添加评论
	 * @param commentinfo
	 * @return true :添加评论成功 false:添加评论失败
	 */
	public boolean Add(Comment commentinfo);

}
