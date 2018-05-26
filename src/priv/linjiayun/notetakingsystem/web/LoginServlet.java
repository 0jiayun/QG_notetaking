package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import priv.linjiayun.notetakingsystem.service.UserService;
/*@WebServlet("/login")*/
public class LoginServlet extends HttpServlet { 
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
//		String userName=new String(req.getParameter("userName").getBytes("iso-8859-1"),"utf-8");//utf-8转换
		String password=req.getParameter("password");
		String userName=req.getParameter("userName");
		try {
			userService.loginCheckUp(req, resp, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}