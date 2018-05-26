package priv.linjiayun.notetakingsystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import priv.linjiayun.notetakingsystem.entity.Note;
import priv.linjiayun.notetakingsystem.entity.Role;
import priv.linjiayun.notetakingsystem.entity.User;
import priv.linjiayun.notetakingsystem.util.JdbcUtil;

public class UserDao {
	private JdbcUtil jdbcUtil=new JdbcUtil();
	
	
/**
 * 用户增添
 * @param user
 * @return
 * @throws Exception
 */
	public boolean addUser(User user){
		try {
			
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(user.getUserName());
			params.add(user.getPassword());
			params.add(user.getSex());
			params.add(user.getNickName());
			
			String sql="insert into t_user values(null,?,?,?,?)";
			
			return jdbcUtil.updateByPreparedStatement(sql, params);	
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户添加链接异常");
		}finally {
			try {
				jdbcUtil.releaseConn();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	/**
	 * 用户模糊查找
	 * @param userName
	 * @return
	 */
	public List<User> findUserByName(String userName){
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			List<User> users=new ArrayList<User>();
			params.add(userName);
			String sql="select * from t_user where userName like concat('%',?,'%')";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String,Object>m:result) {
				User user=new User();
				user.setId(Integer.parseInt(m.get("id").toString()));//错误写法：user.setId(Integer.parseInt((String)m.get("id")));
				user.setUserName((String)m.get("userName"));
				user.setPassword((String)m.get("password"));
				user.setNickName((String)m.get("nickName"));
				user.setSex(Integer.parseInt(m.get("sex").toString()));
				users.add(user);
				
			}
			return users;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户模糊查找异常");
		}finally {
			jdbcUtil.releaseConn();
		}
	}
	/**
	 * 删除用户
	 * @param user_id
	 * @return
	 */
	public boolean deleteUserByid(int user_id) {
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(user_id);
			List<Object> params2=new ArrayList<Object>();
			params2.add(user_id);
			String sql="delete from t_user where id=?";
			String sql2="delete from role_user where user_id=?";
			if(jdbcUtil.updateByPreparedStatement(sql2, params2)) {
				return jdbcUtil.updateByPreparedStatement(sql, params);
			}else {
				throw new RuntimeException("用户删除异常1");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户删除异常");
		} 
	}
	/**
	 * 获取当前用户id
	 * @return
	 * @throws Exception
	 */
	public int catchid() throws Exception{
		int userid=0;
		List<User> users=new ArrayList<User>();
		users=this.getAll();
		for(User u:users) {
			userid=u.getId();
		}
			
		
		return userid;
		
		
	}
	/**
	 * 注册时确定用户角色
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	public boolean user_role(int user_id,int role_id) {
		try {
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(user_id);
			params.add(role_id);
			String sql="insert into role_user values(?,?)";
			return jdbcUtil.updateByPreparedStatement(sql, params);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("角色用户异常");
		}
	}
	/**
	 * 用户个人信息修改
	 * @param id
	 * @param user
	 * @return
	 */
	public boolean update(int id,User user) {
		try {
			
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(user.getPassword());
			params.add(user.getSex());
			params.add(user.getNickName());
			params.add(id);
			String sql="update t_user set password=?,sex=?,nickName=? where id=?";
			return jdbcUtil.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户信息修改错误");
		}finally {
			try {
				jdbcUtil.releaseConn();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}
 
	/**
	 * 用户登陆
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(User user) {
		try {
			
			User currentUser=null;
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(user.getUserName());
			params.add(user.getPassword());
			String sql="select * from t_user where userName=? and password=?";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String, Object>m:result) {
				currentUser=new User();
				currentUser.setId(Integer.parseInt(m.get("id").toString()));
				currentUser.setUserName((String)m.get("userName"));
				currentUser.setPassword((String)m.get("password"));
				currentUser.setSex(Integer.parseInt(m.get("sex").toString()));
				currentUser.setNickName((String)m.get("nickName"));
			}
			return currentUser;	
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("登陆链接异常");
		}finally {
			try {
				jdbcUtil.releaseConn();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}
	public User findUserByid(int user_id) {
		try {jdbcUtil.getConnection();
		User currentUser=null;
		List<Object> params=new ArrayList<Object>();
		params.add(user_id);
		
		String sql="select * from t_user where id=?";
		List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
		for(Map<String, Object>m:result) {
			currentUser=new User();
			currentUser.setId(Integer.parseInt(m.get("id").toString()));
			currentUser.setUserName((String)m.get("userName"));
			currentUser.setNickName((String)m.get("nickName"));
			currentUser.setSex(Integer.parseInt(m.get("sex").toString()));
			currentUser.setPassword((String)m.get("password"));
		}
		return currentUser;			
	}catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("通过id用户查找链接异常");
	}finally {
		try {
			jdbcUtil.releaseConn();;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
	/**
	 * 根据用户名用户查找
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public User findUser(String userName){
		try {
			
			jdbcUtil.getConnection();
			User currentUser=null;
			List<Object> params=new ArrayList<Object>();
			params.add(userName);
			
			String sql="select * from t_user where userName=?";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String, Object>m:result) {
				currentUser=new User();
				currentUser.setId(Integer.parseInt(m.get("id").toString()));
				currentUser.setUserName((String)m.get("userName"));
				currentUser.setPassword((String)m.get("password"));
				currentUser.setSex(Integer.parseInt(m.get("sex").toString()));
				currentUser.setPassword((String)m.get("nickName"));
			}
			return currentUser;			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户查找链接异常");
		}finally {
			try {
				jdbcUtil.releaseConn();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}
	/**
	 * 获取用户所有笔记
	 * @param user_id
	 * @return
	 */
	public List<Note> getNotes(int user_id){
		try {

			jdbcUtil.getConnection();
			
			List<Note> notes=new ArrayList<Note>();
			List<Object> params=new ArrayList<Object>();
			params.add(user_id);
			String sql="select * from t_note where user_id=?";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String, Object>m:result) {
				Note note=new Note();//循环一次new一次，否则都是同个储存地址，最终为最后一个
				note.setId(Integer.parseInt(m.get("id").toString()));
				note.setTitle(m.get("title").toString());
				note.setBody(m.get("body").toString());
				note.setLast_modify_time(m.get("last_modify_time").toString());
				note.setCreate_time(m.get("create_time").toString());
				note.setPurview(Integer.parseInt(m.get("purview").toString()));
				notes.add(note);
			} 
			return notes;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("笔记获取异常");
		}finally {
			try {
				jdbcUtil.releaseConn();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}
	/**
	 * 用户所有角色获取
	 * @param user_id
	 * @return
	 */
	public List<Role> getRoles(int user_id){
		try {
			jdbcUtil.getConnection();
			Role role=new Role();
			List<Role> roles=new ArrayList<Role>();
			List<Object> params=new ArrayList<Object>();
			params.add(user_id);
			String sql="select r.* from t_role r,role_user ru where ru.user_id=? and r.id=ru.role_id";
			List<Map<String, Object>> result=jdbcUtil.findResult(sql, params);
			for(Map<String, Object>m:result) {
				role.setId(Integer.parseInt(m.get("id").toString()));
				role.setName((String)m.get("name"));
				role.setDescription(m.get("description").toString());
				roles.add(role);
			} 
			return roles;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("角色获取异常");
		}finally {
			try {
				jdbcUtil.releaseConn();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	/**
	 * 用户角色添加
	 * @param user
	 * @param roles
	 */
	public void updateRole(User user,List<Role> roles) {
		try {
			
		
			List<Object> params=new ArrayList<Object>();
			params.add(user.getId());
			String delete="delete from role_user where user_id=?";
			if(jdbcUtil.updateByPreparedStatement(delete, params)) {
				System.out.println("用户权限清空");
			}else {
				System.out.println("用户权限清空失败");
				return;
			}
			String add="insert into role_user values(?,?)";
			for(Role role:roles) {
				params.add(role.getId());
				if(!jdbcUtil.updateByPreparedStatement(add, params)) {
					System.out.println("用户权限添加失败");
					return;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("角添加异常");
		}finally {
			try {
				jdbcUtil.releaseConn();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	/**
	 * 查看所有用户
	 * @return
	 */

	public List<User> getAll() {
		try {
			 
			 jdbcUtil.getConnection();
			 
			 List<User> users=new ArrayList<User>();
			 String sql="select * from t_user";
			 List<Map<String, Object>> result=jdbcUtil.findResult(sql, null);
				for(Map<String, Object>m:result) {
					User user=new User();
					user.setId(Integer.parseInt(m.get("id").toString()));//错误写法：user.setId(Integer.parseInt((String)m.get("id")));
					user.setUserName((String)m.get("userName"));
					user.setPassword((String)m.get("password"));
					user.setNickName((String)m.get("nickName"));
					user.setSex(Integer.parseInt(m.get("sex").toString()));
					users.add(user);
				}
				return users;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("查看所有用户异常");
		 }finally {
				try {
					jdbcUtil.releaseConn();;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	 
	 }


}
