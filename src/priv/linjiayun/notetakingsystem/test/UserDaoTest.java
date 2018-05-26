package priv.linjiayun.notetakingsystem.test;

import java.util.List;

import priv.linjiayun.notetakingsystem.dao.RoleDao;
import priv.linjiayun.notetakingsystem.dao.UserDao;
import priv.linjiayun.notetakingsystem.entity.Privilege;
import priv.linjiayun.notetakingsystem.entity.User;

public class UserDaoTest {
	 UserDao userDao = new UserDao();
	 RoleDao roleDao=new RoleDao();

	   
	    public void add() {

	        User user = new User();
	       
	        user.setUserName("qqq2");
	        user.setPassword("12345");
	        user.setSex(1);
	        user.setNickName("哈哈哈");
	        userDao.addUser(user);


	    }

	
	    public void find() {

	        String userName = "qqq";
	        User user = userDao.findUser(userName);

	        System.out.println(user.getUserName());
	    }

	  
	    public void findALL() {

	        List<User> userList = userDao.getAll();

	        for (User user : userList) {

	            System.out.println(user.getUserName());
	        }

	    }
	    public void grp() {
	    	List<Privilege> privilegeList=roleDao.getPrivileges(1);
	    	for(Privilege p:privilegeList) {
	    		System.out.println(p.getName());
	    	}
	    }
	    public static void main(String[] args) {
	    	UserDaoTest userDaoTest=new UserDaoTest();
	    	
	    	
	    	userDaoTest.grp();
	    }

}
