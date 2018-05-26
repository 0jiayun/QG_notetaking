package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.UserService;
/*@WebServlet("/userAdmin")*/
public class SeeAllUserServlet extends HttpServlet {
	private UserService userService=new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			userService.getAllUser(req,resp); 
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查看所有用户异常");
		}
		 
	}
	

}
