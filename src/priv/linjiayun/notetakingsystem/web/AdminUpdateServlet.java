package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.UserService;
@WebServlet("/adminUpdate")
public class AdminUpdateServlet extends HttpServlet {
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
		String password=req.getParameter("password");

		String nickName=req.getParameter("nickName");
		int user_id=Integer.parseInt(req.getParameter("user_id"));
		String sex=req.getParameter("sex");
		try {
			userService.adminUpdateCheckup(req,resp,password,nickName,sex,user_id);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("管理员修改信息异常");
		}
	}
    
}
