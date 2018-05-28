package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.CommentService;
//@WebServlet("/seeCommets")

public class SeeAllCommentsServlet extends HttpServlet {
	private CommentService commentService=new CommentService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int noteid=Integer.parseInt(req.getParameter("noteid").toString());
	
		commentService.seeAllComments(req,resp,noteid);
		
	}
	

}
