package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.UserService;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private UserService userService=new UserService();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String userName=new String(req.getParameter("userName"));
		String nickName=new String(req.getParameter("nickName"));
		String password_1=new String(req.getParameter("password_1"));
		String password_2=new String(req.getParameter("password_2"));
		String role=req.getParameter("role");
		String controlpassword=req.getParameter("controlpassword");
		String sex=req.getParameter("sex");
		try {
			userService.registerCheckup(req, resp, userName, password_1, password_2,nickName,sex,role,controlpassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
