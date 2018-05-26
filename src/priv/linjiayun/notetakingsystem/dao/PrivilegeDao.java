package priv.linjiayun.notetakingsystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import priv.linjiayun.notetakingsystem.entity.Privilege;
import priv.linjiayun.notetakingsystem.util.JdbcUtil;

public class PrivilegeDao {
	/**
	 * 新增权限
	 * @param privilege
	 * @return
	 */
	public boolean addPrivilege(Privilege privilege) {
		try {
			JdbcUtil jdbcUtil=new JdbcUtil();
			jdbcUtil.getConnection();
			List<Object> params=new ArrayList<Object>();
			params.add(privilege.getName());
			params.add(privilege.getDescription());
			String sql="insert into privilege values(null,?,?)";
			return jdbcUtil.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("权限增加异常");
		 } 
	}
	/**
	 * 查找权限
	 * @param id
	 * @return
	 */
	public Privilege findPrivilege(int id) {
		try {
			 JdbcUtil jdbcutil=new JdbcUtil();
			 jdbcutil.getConnection();
			 String sql="select * from t_privilege where id=? ";
			 List<Object> params=new ArrayList<Object>();
			 Privilege privilege=null;
			 params.add(id);
			 List<Map<String, Object>> result=jdbcutil.findResult(sql, params);
			 for(Map<String,Object>m:result) {
				 privilege=new Privilege();
				 privilege.setName((String)m.get("name"));
				 privilege.setDescription((String)m.get("description"));
				 privilege.setId(id);
			 }
			 return privilege;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("权限查找异常");
		 }
	}
	/**
	 * 查看所有权限
	 * @return
	 */
	 public List<Privilege> getAll(){
		 try {
			 JdbcUtil jdbcutil=new JdbcUtil();
			 jdbcutil.getConnection();
			
			 List<Privilege> privileges=new ArrayList<Privilege>();
			 String sql="select * from t_privilege";
			 List<Map<String, Object>> result=jdbcutil.findResult(sql, null);
				for(Map<String, Object>m:result) {
				    Privilege privilege=new Privilege();
					privilege.setId(Integer.parseInt((String)m.get("id")));
					privilege.setName((String)m.get("name"));
					privilege.setDescription((String)m.get("description"));
					privileges.add(privilege);
				}
				return privileges;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("查看所有角色异常");
		 } 
	 }

}
