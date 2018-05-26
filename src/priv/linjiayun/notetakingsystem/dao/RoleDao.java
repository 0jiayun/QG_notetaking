package priv.linjiayun.notetakingsystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Exception;

import priv.linjiayun.notetakingsystem.entity.Privilege;
import priv.linjiayun.notetakingsystem.entity.Role;
import priv.linjiayun.notetakingsystem.util.JdbcUtil;

public class RoleDao {
	/*private JdbcUtil jdbcutil=new JdbcUtil();
	private List<Object> params=new ArrayList<Object>();*/
	
	/**
	 * 角色添加
	 * @param role
	 * @return
	 */
	public boolean add(Role role) {
		try {
			 JdbcUtil jdbcutil=new JdbcUtil();
			jdbcutil.getConnection();
			String sql="insert into t_role values(null,?,?)";
			List<Object> params=new ArrayList<Object>();
			params.add(role.getName());
			params.add(role.getDescription());
			return jdbcutil.updateByPreparedStatement(sql, params);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("角色添加异常");
		}
	}
	/**
	 * 角色查找
	 * @param id
	 * @return
	 */
	 public Role find(int id) {
		 try {
			 JdbcUtil jdbcutil=new JdbcUtil();
			 jdbcutil.getConnection();
			 String sql="select * from t_role where id=? ";
			 List<Object> params=new ArrayList<Object>();
			 Role role=null;
			 params.add(id);
			 List<Map<String, Object>> result=jdbcutil.findResult(sql, params);
			 for(Map<String,Object>m:result) {
				 role=new Role();
				 role.setName((String)m.get("name"));
				 role.setDescription((String)m.get("description"));
				 role.setId(id);
			 }
			 return role;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("角色查找异常");
		 }
	 }
	 
	 /**
	  * 得到所有角色
	  * @return
	  */
	 public List<Role> getAll(){
		 try {
			 JdbcUtil jdbcutil=new JdbcUtil();
			 jdbcutil.getConnection();
			 
			 List<Role> roles=new ArrayList<Role>();
			 String sql="select * from t_role";
			 List<Map<String, Object>> result=jdbcutil.findResult(sql, null);
				for(Map<String, Object>m:result) {
					Role role=new Role();
					role.setId(Integer.parseInt(m.get("id").toString()));
					role.setName((String)m.get("name"));
					role.setDescription((String)m.get("description"));
					roles.add(role);
				}
				return roles;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("角色异常");
		 } 
	 }
	 /**
	  * 角色所有权限查找
	  * @param role_id
	  * @return
	  */
	 public List<Privilege> getPrivileges(int role_id){
		 try {
			 JdbcUtil jdbcutil=new JdbcUtil();
			 jdbcutil.getConnection();
			 List<Object> params=new ArrayList<Object>();
			 params.add(role_id);
			 List<Privilege> privileges=new ArrayList<Privilege>();
			 
			 String sql="select p.* from t_privilege p,role_privilege rp where rp.role_id=? and p.id=rp.privilege_id";
			 List<Map<String, Object>> result=jdbcutil.findResult(sql, params);
				for(Map<String, Object>m:result) {
					Privilege privilege=new Privilege();
					privilege.setId(Integer.parseInt(m.get("id").toString()));
					privilege.setName((String)m.get("name"));
					privilege.setDescription((String)m.get("description"));
					privileges.add(privilege);
				}
			return privileges;
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("角色所有权限添加异常");
		 } 
	 }
	 /**
	  * 为角色添加权限
	  * @param role
	  * @param privileges
	  */
	 public void addPrivilege2Role(Role role, List<Privilege> privileges) {
		 try {
			 JdbcUtil jdbcutil=new JdbcUtil();
			 jdbcutil.getConnection();
			 
			 List<Object> params=new ArrayList<Object>();
			 params.add(role.getId());
			 String delete="delete from role_privilege where role_id=?";
			 jdbcutil.updateByPreparedStatement(delete, params);
			 String sql="insert into role_privilege values(?,?)";
			 for(Privilege p:privileges) {
				 List<Object> params2=new ArrayList<Object>();
				 params2.add(role.getId());
				 params2.add(p.getId());
				 jdbcutil.updateByPreparedStatement(sql, params2);
			 }
			 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("权限添加异常");
		 } 
	 }

}
