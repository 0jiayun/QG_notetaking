package priv.linjiayun.notetakingsystem.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.linjiayun.notetakingsystem.dao.NoteDao;
import priv.linjiayun.notetakingsystem.dao.RoleDao;
import priv.linjiayun.notetakingsystem.dao.UserDao;
import priv.linjiayun.notetakingsystem.entity.Note;
import priv.linjiayun.notetakingsystem.entity.Role;
import priv.linjiayun.notetakingsystem.entity.User;
import priv.linjiayun.notetakingsystem.util.StringUtil;



public class UserService {
	
	private UserDao userDao=new UserDao();
	private RoleDao roleDao=new RoleDao();
	private NoteDao noteDao=new NoteDao();
	
	private NoteService noteService=new NoteService();
	
	
	/**
	 * 用户登陆检验
	 * @param req
	 * @param resp
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void loginCheckUp(HttpServletRequest req, HttpServletResponse resp,String userName,String password)throws Exception{
		if(StringUtil.isEmpty(userName)) {
			req.setAttribute("error1", "用户名不能为空");
			req.setAttribute("password", password);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		}
		if(!StringUtil.intergeAndletter(userName)) {
			req.setAttribute("error1", "用户名长只能由数字和字母组成");
			req.setAttribute("password", password);
			req.setAttribute("userName", userName); 
			
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		if(userName.length()<6||userName.length()>15) {
			req.setAttribute("error1", "用户名长度需在6-15位之间");
			req.setAttribute("password", password);
			req.setAttribute("userName", userName); 
			
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		
	
		if(StringUtil.isEmpty(password)) {
			req.setAttribute("error1", "密码不能为空");
			req.setAttribute("userName", userName);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
			
		}
		if(password.length()>15) {
			req.setAttribute("error1", "密码长度不能大于15");
			req.setAttribute("password", password);
			req.setAttribute("userName", userName);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
			
		}
		
		User user=new User(userName,password);
		User currentUser=userDao.login(user);
		
	    List<Note> noteList=noteService.getPublicNotes();
	    List<User> userList=new ArrayList<User>();
	    for(Note n:noteList) {
	    	User u=new User();
	    	u=userDao.findUserByid(n.getUser_id());
	    	userList.add(u);
	    }
		
		
		try {
			
			if(currentUser!=null) { 
				HttpSession session=req.getSession();
				session.setAttribute("currentUser",(User)currentUser);
				session.setAttribute("noteList", noteList);
				session.setAttribute("userList", userList);
				List<Role> roles=userDao.getRoles(currentUser.getId());
				String roleName=null;
				for(Role r:roles) {
					roleName=r.getName();
				}
				session.setAttribute("roleName", roleName);
				if(currentUser.getSex()==1) {
					session.setAttribute("sex", "男");
				}else {
					session.setAttribute("sex", "女");
					
				}
			    resp.sendRedirect("main.jsp");
			}else {
				req.setAttribute("userName", userName);
				req.setAttribute("password", password);
				req.setAttribute("error1", "用户名或密码错误");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	 }
	/**
	 * 注册检测
	 * @param req
	 * @param resp
	 * @param userName
	 * @param password_1
	 * @param password_2
	 * @param nickName
	 * @param sex
	 * @throws ServletException
	 * @throws IOException
	 */
	public void registerCheckup(HttpServletRequest req, HttpServletResponse resp, String userName, String password_1,
			String password_2, String nickName, String sex,String role,String controlpassword) throws ServletException, IOException {
		int role_id=3;
		if(role.equals("control")) {
			role_id=2;
		}else if(role.equals("surpcontrol")) {
			role_id=1;
		}
		if(StringUtil.isEmpty(userName)) { 
			req.setAttribute("error1", "用户名不能为空");
			req.setAttribute("password_1", password_1);
			req.setAttribute("role", role);
			req.setAttribute("nickName", nickName); 
			req.setAttribute("password_2", password_2);
			req.setAttribute("sex", sex);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		if(!StringUtil.intergeAndletter(userName)) {
			req.setAttribute("error1", "用户名只能是数字或字母");
			req.setAttribute("userName", userName); 
			req.setAttribute("nickName", nickName); 
			req.setAttribute("role", role);
			req.setAttribute("password_1", password_1);
			req.setAttribute("password_2", password_2);
			req.setAttribute("sex", sex);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		if(userName.length()<6||userName.length()>15) {
			req.setAttribute("error1", "用户名长度需在6-15位之间");
			req.setAttribute("nickName", nickName); 
			req.setAttribute("sex", sex);
			req.setAttribute("role", role);
			req.setAttribute("password_1", password_1);
			req.setAttribute("userName", userName); 
			req.setAttribute("nickName", nickName); 
			req.setAttribute("password_2", password_2);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		
		if(StringUtil.isEmpty(nickName)) { 
			req.setAttribute("error1", "昵称不能为空");
			req.setAttribute("userName", userName); 
			req.setAttribute("sex", sex);
			req.setAttribute("role", role);
			req.setAttribute("password_1", password_1);
			req.setAttribute("nickName", nickName); 
			req.setAttribute("password_2", password_2);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		if(!StringUtil.intergeAndletterOrchinese(nickName)) {
			req.setAttribute("error1", "昵称只能是中文，数字或字母");
			req.setAttribute("userName", userName); 
			req.setAttribute("sex", sex);
			req.setAttribute("role", role);
			req.setAttribute("nickName", nickName); 
			req.setAttribute("password_1", password_1);
			req.setAttribute("password_2", password_2);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
			
		}
		if(StringUtil.isEmpty(password_1)||StringUtil.isEmpty(password_1)) {
			req.setAttribute("error1", "密码不能为空");
			req.setAttribute("sex", sex);
			req.setAttribute("role", role);
			req.setAttribute("password_1", password_1);
			req.setAttribute("nickName", nickName); 
			req.setAttribute("userName", userName);
			req.setAttribute("password_2", password_2);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
			
		}
		if(!StringUtil.intergeAndletter(password_1)||!StringUtil.intergeAndletter(password_2)) {
			req.setAttribute("error1", "密码只能是数字或字母");
			req.setAttribute("userName", userName); 
			req.setAttribute("role", role);
			req.setAttribute("password_1", password_1);
			req.setAttribute("password_2", password_2);
			req.setAttribute("nickName", nickName); 
			req.setAttribute("sex", sex);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		
		if(!password_1.equals(password_2)) {
			req.setAttribute("error1", "前后密码不一致");
			req.setAttribute("password_1", password_1);
			req.setAttribute("sex", sex);
			req.setAttribute("role", role);
			req.setAttribute("userName", userName);
			req.setAttribute("password_2", password_2);
			req.setAttribute("nickName", nickName); 
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		if(password_1.length()<6||password_1.length()>15) {
			req.setAttribute("error1", "密码长度需在6-15位之间");
			req.setAttribute("password_1", password_1);
			req.setAttribute("sex", sex);
			req.setAttribute("role", role);
			req.setAttribute("userName", userName);
			req.setAttribute("nickName", nickName); 
			req.setAttribute("password_2", password_2);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		int sexint;
		if(sex.equals("male")) {
			 sexint=1;
		}else {
			sexint=0;
		}
		User user=new User(userName,password_1,nickName,sexint);
		try {
			if(null!=userDao.findUser(userName)) {
				req.setAttribute("error1", "用户已已存在");
				req.setAttribute("password_1", password_1);
				req.setAttribute("sex", sex);
				req.setAttribute("role", role);
				req.setAttribute("userName", userName);
				req.setAttribute("nickNmae", nickName);
				req.setAttribute("password_2", password_2);
				req.getRequestDispatcher("register.jsp").forward(req, resp);
				return;
			}
			if(role_id==2) {
				if(!controlpassword.equals("123456")) {
					
					req.setAttribute("error1", "管理员注册密码错误");
					req.setAttribute("role", role);
					req.setAttribute("password_1", password_1);
					req.setAttribute("sex", sex);
					req.setAttribute("userName", userName);
					req.setAttribute("nickName", nickName);
					req.setAttribute("password_2", password_2);
					req.getRequestDispatcher("register.jsp").forward(req, resp);
					return;
				}
			}
			if(role_id==1) {
				if(!controlpassword.equals("jiayun666")) {
					
					req.setAttribute("error1", "超级管理员注册密码错误");
					req.setAttribute("role", role);
					req.setAttribute("password_1", password_1);
					req.setAttribute("sex", sex);
					req.setAttribute("userName", userName);
					req.setAttribute("nickName", nickName);
					req.setAttribute("password_2", password_2);
					req.getRequestDispatcher("register.jsp").forward(req, resp);
					return;
				}
			}
			userDao.addUser(user);
			int user_id=userDao.catchid();
			if(userDao.user_role(user_id, role_id)) {
				req.setAttribute("error2", "注册成功");
				req.setAttribute("password_1", password_1); 
				req.setAttribute("sex", sex);
				req.setAttribute("role", role);
				req.setAttribute("nickName", nickName);
				req.setAttribute("userName", userName);
				req.setAttribute("password_2", password_2);
				req.getRequestDispatcher("register.jsp").forward(req, resp);
				return;
			}else {
				req.setAttribute("error1", "注册失败");
				req.setAttribute("password_1", password_1);
				req.setAttribute("userName", userName);
				req.setAttribute("sex", sex);
				req.setAttribute("role", role);
				req.setAttribute("nickName", nickName);
				req.setAttribute("password_2", password_2);
				req.getRequestDispatcher("register.jsp").forward(req, resp);
				return;
				
			}
			// TODO Auto-generated method stub
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("注册异常");
		}
		
		
		

}
	/**
	 * 自己信息修改检测
	 * @param req
	 * @param resp
	 * @param userName
	 * @param nickName
	 * @param sex
	 * @param password_1
	 * @param password_2
	 * @param password_0
	 */
	public void updateCheckUp(HttpServletRequest req, HttpServletResponse resp, String nickName, String sex, String password_1, String password_2,
			String password_0) {
		User user_0=(User)req.getSession().getAttribute("currentUser");
		User user=new User();
		user.setId(user_0.getId());
		user.setUserName(user_0.getUserName());
		user.setNickName(nickName);
		user.setPassword(password_1);
		try {
			
			if(StringUtil.isEmpty(nickName)) {
				req.setAttribute("error1", "昵称不能空");
				req.setAttribute("sex", sex);
				req.setAttribute("nickName", nickName); 
				req.setAttribute("password_1", password_1);
				req.setAttribute("password_2", password_2);
				req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
				return;
				
				
			}
			if(!StringUtil.intergeAndletterOrchinese(nickName)) {
					req.setAttribute("error1", "昵称只能是中文，数字或字母");
					req.setAttribute("sex", sex);
					req.setAttribute("nickName", nickName); 
					req.setAttribute("password_1", password_1);
					req.setAttribute("password_2", password_2);
					req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
					return;
					
			} 
			
			
			if(!password_1.equals("")&&!password_2.equals("")) {
				if(!StringUtil.intergeAndletter(password_1)||!StringUtil.intergeAndletter(password_2)) {
					req.setAttribute("error1", "密码只能是数字或字母");
					
					req.setAttribute("password_1", password_1);
					req.setAttribute("password_2", password_2);
					req.setAttribute("nickName", nickName); 
					req.setAttribute("sex", sex);
					req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
				}
				
				if(!password_1.equals(password_2)) {
					req.setAttribute("error1", "前后密码不一致");
					req.setAttribute("password_1", password_1);
					req.setAttribute("sex", sex);
					
					req.setAttribute("password_2", password_2);
					req.setAttribute("nickName", nickName); 
					req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
					return;
				}
				if(password_1.length()<6||password_1.length()>15) {
					req.setAttribute("error1", "密码长度需在6-15位之间");
					req.setAttribute("password_1", password_1);
					req.setAttribute("sex", sex);
					
					req.setAttribute("nickName", nickName); 
					req.setAttribute("password_2", password_2);
					req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
					return;
				}
				
			}else {
				
				user.setPassword(user_0.getPassword());
				req.setAttribute("nickName", nickName); 
				req.setAttribute("sex", sex);
			}
			if(!password_0.equals(user_0.getPassword())){
				req.setAttribute("error1", "原密码不正确");
				req.setAttribute("password_1", password_1);
				req.setAttribute("password_2", password_2);
				req.setAttribute("sex", sex);
				
				req.setAttribute("nickName", nickName); 
				req.setAttribute("password_2", password_2);
				req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
				return;
				
			}
			int sexint;
			if(sex.equals("male")) {
				 sexint=1;
			}else {
				sexint=0;
			}
			user.setSex(sexint);
			
			if(userDao.update(user_0.getId(), user)){
				HttpSession session=req.getSession();
				session.setAttribute("currentUser",user);
				if(sexint==1) {
					session.setAttribute("sex", "男");
				}else {
					session.setAttribute("sex", "女");
				}
				req.setAttribute("error2", "修改成功");
				req.setAttribute("password_1", password_1);
				req.setAttribute("password_2", password_2);
				req.setAttribute("sex", sex);
				req.setAttribute("nickName", nickName); 
				req.setAttribute("password_2", password_2);
				req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
				
				
			}else {
				req.setAttribute("error1", "修改失败");
				req.setAttribute("password_1", password_1);
				req.setAttribute("password_2", password_2);
				req.setAttribute("sex", sex);
				
				req.setAttribute("nickName", nickName); 
				req.setAttribute("password_2", password_2);
				req.getRequestDispatcher("perMessageManeger.jsp").forward(req, resp);
				
				
			}
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("修改异常");
			}

	}
	/**
	 * 获取所有用户
	 * @param req
	 * @param resp
	 */
	public void getAllUser(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.setCharacterEncoding("UTF-8");
		
		int user_id=Integer.parseInt(req.getParameter("user_id"));
		User user=userDao.findUserByid(user_id);
		List<Role> roleList=new ArrayList<Role>();
		List<Role> roles=userDao.getRoles(user_id);//获得登录用户角色
		
		String roleName=null;
		
		
		for(Role r:roles) {
			roleName=r.getName();
		}
		List<User> users=userDao.getAll();
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getId()==user_id) {
				users.remove(users.get(i));//去除本身
				break;
			}
		}
		Iterator<User> userIter=users.iterator();
		if(roleName.equals("管理员")) {
			 while (userIter.hasNext()) {
				User useri=userIter.next();//不能直接用for遍历循环删除users
				String roleName2=new String();
				List<Role> roles2=userDao.getRoles(useri.getId());		
				for(Role r:roles2) {
					roleName2=new String();
					roleName2=r.getName();
				}
				if(roleName2.equals("管理员")||roleName2.equals("超级管理员")) {
					userIter.remove();
				}
				
			}
		}else if(roleName.equals("超级管理员")) {
			 while (userIter.hasNext()) {
					User useri=userIter.next();
					String roleName2=new String();
					List<Role> roles2=userDao.getRoles(useri.getId());		
					for(Role r:roles2) {
						roleName2=new String();
						roleName2=r.getName();
					}
					if(roleName2.equals("超级管理员")) {
						userIter.remove();
					}
					
				}
			
		}
		for(int i=0;i<users.size();i++) {
			List<Role> roles3=userDao.getRoles(users.get(i).getId());
			for(Role r:roles3) {
				roleList.add(r);
			}
		}
		req.setAttribute("userList", users);
	    req.setAttribute("roleList", roleList);
	    req.getRequestDispatcher("userAdmin.jsp").forward(req, resp);
	}
	/**
	 * 跳转管理用户页面
	 * @param req
	 * @param resp
	 * @param user_id
	 */
	public void userChange(HttpServletRequest req, HttpServletResponse resp, int user_id) {
		User user=userDao.findUserByid(user_id);
		req.getSession().setAttribute("user", user);
		try {
			req.getSession().setAttribute("user_id", user_id);
			resp.sendRedirect("userChange.jsp");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("跳转userChange.jsp异常");
		}
		
		// TODO Auto-generated method stub
		
	}
	/**
	 * 删除用户
	 * @param req
	 * @param resp
	 * @param user_id
	 */
	public void userDelete(HttpServletRequest req, HttpServletResponse resp, int user_id) {
		try {
			if(noteDao.findNoteByuser_id(user_id).getTitle()!=null) {
				if(noteDao.deleteByuser_id(user_id)&&userDao.deleteUserByid(user_id)) {
					req.setAttribute("error2", "删除成功");
					req.getRequestDispatcher("userChange.jsp").forward(req, resp);
				}else {
					req.setAttribute("error1", "删除失败");
					req.getRequestDispatcher("userChange.jsp").forward(req, resp);
				}
					
			}else {
				if(userDao.deleteUserByid(user_id)) {
					req.setAttribute("error2", "删除成功");
					req.getRequestDispatcher("userChange.jsp").forward(req, resp);
				}else {
					req.setAttribute("error1", "删除失败");
					req.getRequestDispatcher("userChange.jsp").forward(req, resp);
				}
			}
		
				
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException("userdelete异常");
		}
		
	
		
	}
	/**
	 * 修改用户信息
	 * @param req
	 * @param resp
	 * @param password
	 * @param nickName
	 * @param sex
	 */
	public void adminUpdateCheckup(HttpServletRequest req, HttpServletResponse resp, String password, String nickName,
			String sex,int user_id) {
		try {
		int sexint;
		if(sex.equals("male")) {
			 sexint=1;
		}else {
			sexint=0;
		}
		if(!StringUtil.intergeAndletterOrchinese(nickName)) {
			req.setAttribute("password", password);
			req.setAttribute("nickName", nickName);
			req.setAttribute("sex", sex);
			req.setAttribute("error1", "昵称只由数字和字母，中文组成");
			req.getRequestDispatcher("userChange.jsp").forward(req, resp);
			return;
		}
		if(StringUtil.isEmpty(nickName)) {
			req.setAttribute("password", password);
	
			req.setAttribute("sex", sex);
			req.setAttribute("error1", "昵称不能为空");
			req.getRequestDispatcher("userChange.jsp").forward(req, resp);
			return;
		}
		User user=new User(password,nickName,sexint);
		if(userDao.update(user_id, user)) {
			req.setAttribute("password", password);
			req.setAttribute("nickName", nickName);
			req.setAttribute("sex", sex);
			if(password.equals("111111")) {
				req.setAttribute("error2", "修改成功,密码重置为111111");
			}else {
				req.setAttribute("error2", "修改成功");
			}
			req.getRequestDispatcher("userChange.jsp").forward(req, resp);
			
		}else {
			req.setAttribute("password", password);
			req.setAttribute("nickName", nickName);
			req.setAttribute("sex", sex);
			req.setAttribute("error1", "修改失败");
			req.getRequestDispatcher("userChange.jsp").forward(req, resp);
		}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户信息修改异常");
		}
		
	
	}
	/**
	 * 获取登录用户所有笔记
	 * @param req
	 * @param resp
	 * @param user_id
	 */
	public void getUserNotes(HttpServletRequest req, HttpServletResponse resp, int user_id) {
		try {
			List<Note> notes=userDao.getNotes(user_id);
			req.getSession().setAttribute("notes", notes);
			resp.sendRedirect("noteAdmin.jsp");
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取用户笔记异常");
		}
		
	}
	/**
	 * 游客登录
	 * @param req
	 * @param resp
	 */
	public void vistor(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Note> noteList=noteService.getPublicNotes();
			List<User> userList=new ArrayList<User>();
			for(Note n:noteList) {
				User u=userDao.findUserByid(n.getUser_id());
				userList.add(u);
			}
			HttpSession session=req.getSession();
			session.setAttribute("currentUser", "欢迎参观");
			session.setAttribute("noteList", noteList);
		    session.setAttribute("userList", userList);
			resp.sendRedirect("main.jsp");
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("游客登录异常");
		}
		
		
		
	}
	/**
	 * 通过用户名查询用户
	 * @param req
	 * @param resp
	 * @param userName
	 * @param user_id
	 */
	public void findUser(HttpServletRequest req, HttpServletResponse resp, String userName,int user_id) {
		try {
			String userName2=StringUtil.htmlReplace(userName);
			List<User> users=userDao.findUserByName(userName2);
			List<Role> roles=userDao.getRoles(user_id);
			List<Role> roleList=new ArrayList<Role>();
			String roleName=new String();
			for(Role r:roles) {
				roleName=r.getName();				
			}
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getId()==user_id) {
					users.remove(users.get(i));//去除本身
					break;
				}
			}
			Iterator<User> userIter=users.iterator();
			if(roleName.equals("管理员")) {
				 while (userIter.hasNext()) {
					User useri=userIter.next();//不能直接用for遍历循环删除users
					String roleName2=new String();
					List<Role> roles2=userDao.getRoles(useri.getId());		
					for(Role r:roles2) {
						roleName2=new String();
						roleName2=r.getName();
					}
					if(roleName2.equals("管理员")||roleName2.equals("超级管理员")) {
						userIter.remove();
					}
					
				}
			}else if(roleName.equals("超级管理员")) {
				 while (userIter.hasNext()) {
						User useri=userIter.next();
						String roleName2=new String();
						List<Role> roles2=userDao.getRoles(useri.getId());		
						for(Role r:roles2) {
							roleName2=new String();
							roleName2=r.getName();
						}
						if(roleName2.equals("超级管理员")) {
							userIter.remove();
						}
						
					}
				
			}
			for(int i=0;i<users.size();i++) {
				List<Role> roles3=userDao.getRoles(users.get(i).getId());
				for(Role r:roles3) {
					roleList.add(r);
				}
			}
			req.setAttribute("userList", users);
		    req.setAttribute("roleList", roleList);
		    req.getRequestDispatcher("userAdmin.jsp").forward(req, resp);
				
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("search异常");
		}
		// TODO Auto-generated method stub
		
	}
}
