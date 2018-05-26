package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.UserService;
@WebServlet("/userUpdate")
public class UserUpdateServlet extends HttpServlet {
	private UserService userService=new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
	
		String nickName=req.getParameter("nickName");
	 
		String sex=req.getParameter("sex");
		String password_1=req.getParameter("password_1");
		String password_2=req.getParameter("password_2");
		String password_0=req.getParameter("password_0");
		try {
			userService.updateCheckUp( req, resp,nickName,sex,password_1,password_2,password_0);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改异常");
		} 
		
	}
	

}
