package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.UserService;
//@WebServlet("/searchUser")
public class SearchUserServlet extends HttpServlet {
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
		int user_id=Integer.parseInt(req.getParameter("user_id"));
		String userName=req.getParameter("searchUser");
		userService.findUser(req,resp,userName,user_id);
	}
	

}
