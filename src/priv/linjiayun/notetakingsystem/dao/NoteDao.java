package priv.linjiayun.notetakingsystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import priv.linjiayun.notetakingsystem.entity.Note;
import priv.linjiayun.notetakingsystem.util.JdbcUtil;

public class NoteDao {
	private JdbcUtil jdbcUtil=new JdbcUtil();
	/**
	 * 笔记增添
	 * @param note
	 * @return
	 */
	public boolean add(Note note) {
		try {
			
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(note.getUser_id());
			params.add(note.getTitle());
			params.add(note.getBody());
			params.add(note.getCreate_time());
			params.add(note.getLast_modify_time());
			params.add(note.getPurview());
			String sql="insert into t_note values(null,?,?,?,?,?,?)";
			return(jdbcUtil.updateByPreparedStatement(sql, params));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		    throw new RuntimeException("增添笔记异常");
		}finally {
			jdbcUtil.releaseConn();
		}
	}
	/**
	 * 模糊查找异常
	 * @param note_name
	 * @return
	 */
	public List<Note> findNoteByName(String note_title){
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			List<Note> notes=new ArrayList<Note>();
			params.add(note_title);
			String sql="select * from t_note where title like concat('%',?,'%') ";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String,Object>m:result) {
				 Note note=new Note();
				 note.setId(Integer.parseInt(m.get("id").toString()));
				 note.setPurview(Integer.parseInt(m.get("purview").toString()));
				 note.setUser_id(Integer.parseInt(m.get("user_id").toString()));
				 note.setTitle((String)m.get("title"));
				 note.setBody((String)m.get("body"));
				 note.setCreate_time((String)m.get("create_time"));
				 note.setLast_modify_time((String)m.get("last_modify_time"));
				 notes.add(note);
			}
			return notes;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("模糊查找异常");
		}finally {
			jdbcUtil.releaseConn();
		}
	}
	/**
	 * 查找笔记
	 * @param note_id
	 * @return
	 */
	public Note findNote(int note_id) {
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			
			Note note=new Note();
			params.add(note_id);
			String sql="select * from t_note where id=?";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String,Object>m:result) {
				 
				 note.setId(Integer.parseInt(m.get("id").toString()));
				 note.setPurview(Integer.parseInt(m.get("purview").toString()));
				 note.setUser_id(Integer.parseInt(m.get("user_id").toString()));
				 note.setTitle((String)m.get("title"));
				 note.setBody((String)m.get("body"));
				 note.setCreate_time((String)m.get("create_time"));
				 note.setLast_modify_time((String)m.get("last_modify_time"));
				 			 
			 }
			return note; 
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查找笔记异常");
		}finally {
			jdbcUtil.releaseConn();
		}
	}
	/**
	 * 查找笔记By uer_id
	 * @param user_id
	 * @return
	 */
	public Note findNoteByuser_id(int user_id) {
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			
			Note note=new Note();
			params.add(user_id);
			String sql="select * from t_note where user_id=?";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String,Object>m:result) {
				 
				 note.setId(Integer.parseInt(m.get("id").toString()));
				 note.setPurview(Integer.parseInt(m.get("purview").toString()));
				 note.setUser_id(Integer.parseInt(m.get("user_id").toString()));
				 note.setTitle((String)m.get("title"));
				 note.setBody((String)m.get("body"));
				 note.setCreate_time((String)m.get("create_time"));
				 note.setLast_modify_time((String)m.get("last_modify_time"));
				 			 
			 }
			return note; 
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查找笔记异常Byuser_id");
		}finally {
			jdbcUtil.releaseConn();
		}
	}
	/**
	 * 删除笔记
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		try {
			
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(id);
			String sql="delete from t_note where id=?";
            return 	jdbcUtil.updateByPreparedStatement(sql, params);	
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("笔记删除异常");
		}
	}
	/**
	 * 通过用户id删除笔记
	 * @param user_id
	 * @return
	 */
	public boolean deleteByuser_id(int user_id) {
		try {
			
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(user_id);
			String sql="delete from t_note where user_id=?";
            return 	jdbcUtil.updateByPreparedStatement(sql, params);	
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("笔记删除异常Byuser_id");
		}
	}
	/**
	 * 笔记修改
	 * @param id
	 * @param note
	 * @return
	 */
	public boolean updateNote(Note note) {
		try {
			
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(note.getTitle());
			params.add(note.getBody());
			params.add(note.getLast_modify_time());
			params.add(note.getPurview());
			params.add(note.getId());
			String sql="update t_note set title=?,body=?,last_modify_time=?,purview=? where id=?";
			return jdbcUtil.updateByPreparedStatement(sql, params); 
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("笔记修改异常");
		}finally {
			jdbcUtil.releaseConn();
		}
	}
	/**
	 * 获取所有笔记
	 * @return
	 */
	public List<Note> getAll(){
		try {
			 
			 jdbcUtil.getConnection();
			 List<Note> notes=new ArrayList<Note>();
			 String sql="select * from t_note";
			 List<Map<String, Object>> result=jdbcUtil.findResult(sql, null);
			 for(Map<String,Object>m:result) {
				 Note note=new Note();
				 note.setPurview(Integer.parseInt(m.get("purview").toString()));
				 note.setId(Integer.parseInt(m.get("id").toString()));
				 note.setUser_id(Integer.parseInt(m.get("user_id").toString()));
				 note.setTitle((String)m.get("title"));
				 note.setBody((String)m.get("body"));
				 note.setCreate_time((String)m.get("create_time"));
				 note.setLast_modify_time((String)m.get("last_modify_time"));
				 notes.add(note);				 
			 }
			 return notes;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取笔记异常");
		}finally {
			jdbcUtil.releaseConn();
		}
	}

}
