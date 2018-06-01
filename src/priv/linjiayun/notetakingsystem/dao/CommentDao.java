package priv.linjiayun.notetakingsystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import priv.linjiayun.notetakingsystem.entity.Comment;
import priv.linjiayun.notetakingsystem.util.JdbcUtil;

public class CommentDao {
	private JdbcUtil jdbcUtil=new JdbcUtil();
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public boolean addComment(Comment comment){
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(comment.getUser_id());
			params.add(comment.getBody());
			params.add(comment.getCreate_time());
			params.add(comment.getNote_id());
            String sql="insert into t_comment value(?,?,?,?,null)";
            return jdbcUtil.updateByPreparedStatement(sql, params);
            
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加评论异常");
		}finally {
			jdbcUtil.releaseConn();
		}

	}
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public boolean deleteComent(int id) {
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(id);
			String sql="delete from t_comment where id=?";
			return jdbcUtil.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除评论异常");
		}finally {
			jdbcUtil.releaseConn();
		}
	}
	/**
	 * 查找用户评论
	 * @param user_id
	 * @param note_id
	 * @return
	 */
	public List<Comment> findComments(int user_id,int note_id){
		try {
			 
					jdbcUtil.getConnection();
					List<Object> params=new ArrayList<Object>();
					params.add(user_id);
					params.add(note_id);
					String sql="select * from t_comment where user_id=? and note_id=?";
					List<Map<String,Object>> results=jdbcUtil.findResult(sql, params);
					List<Comment> comments=new ArrayList<Comment>();
					Comment c=new Comment();
					for(Map<String,Object>r:results) {
						c.setId(Integer.parseInt( r.get("id").toString()));
						c.setBody((String)r.get("body"));
						c.setCreate_time((String)r.get("creat_time"));
						c.setNote_id(Integer.parseInt( r.get("note_id").toString()));
						c.setUser_id(Integer.parseInt( r.get("user_id").toString()));
						comments.add(c);
					}
					return comments;
						
					}catch(Exception e) {
						e.printStackTrace();
						throw new RuntimeException("查找评论异常");
					}finally {
						jdbcUtil.releaseConn();
					}
				
		
		
	}
	/**
	 * 查找笔记评论
	 * @param note_id
	 * @return
	 */
	public List<Comment> findCommentBynote_id(int note_id){
		try {
			 
					jdbcUtil.getConnection();
					List<Object> params=new ArrayList<Object>();
					
					params.add(note_id);
					String sql="select * from t_comment where note_id=?";
					List<Map<String,Object>> results=jdbcUtil.findResult(sql, params);
					List<Comment> comments=new ArrayList<Comment>();
					
					for(Map<String,Object>r:results) {
						Comment c=new Comment();
						c.setId(Integer.parseInt( r.get("id").toString()));
						c.setBody((String)r.get("body"));
						c.setCreate_time((String)r.get("creat_time"));
						c.setNote_id(Integer.parseInt( r.get("note_id").toString()));
						c.setUser_id(Integer.parseInt( r.get("user_id").toString()));
						comments.add(c);
					}
					return comments;
						
					}catch(Exception e) {
						e.printStackTrace();
						throw new RuntimeException("查找评论异常");
					}finally {
						jdbcUtil.releaseConn();
					}
				
		
		
	}
	/**
	 * 删除用户评论
	 * @param user_id
	 * @return
	 */
	public boolean deleteByuser_id(int user_id) {
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(user_id);
			String sql="delete from t_comment where user_id=?";
			return jdbcUtil.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除用户评论异常");
		}finally {
			jdbcUtil.releaseConn();
		}
		
		
		
	}
	/**
	 * 删除笔记评论
	 * @param note_id
	 * @return
	 */
	public boolean deleteBynote_id(int note_id) {
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(note_id);
			String sql="delete from t_comment where note_id=?";
			return jdbcUtil.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除笔记评论异常");
		}finally {
			jdbcUtil.releaseConn();
		}
		
	}

}
