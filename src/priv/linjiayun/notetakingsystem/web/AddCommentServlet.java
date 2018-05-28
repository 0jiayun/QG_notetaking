package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.CommentService;
@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
	private CommentService commentService=new CommentService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		int noteid=Integer.parseInt(req.getParameter("noteId").toString());
		int userid=Integer.parseInt(req.getParameter("currentUserId").toString());
		String body=req.getParameter("comment");
		commentService.addComment(req,resp,noteid,userid,body);
	}
	

}
