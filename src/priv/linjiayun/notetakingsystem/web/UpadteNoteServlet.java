package priv.linjiayun.notetakingsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.linjiayun.notetakingsystem.service.NoteService;
@WebServlet("/updateNote")
public class UpadteNoteServlet extends HttpServlet {
	private NoteService noteService=new NoteService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String create_time=req.getParameter("create_time");
		int note_id=Integer.parseInt(req.getParameter("note_id"));
		String title=req.getParameter("title");
		String body=req.getParameter("body");
		String purview=req.getParameter("purview");
		noteService.updateNote(req,resp,note_id,title,body,purview,create_time);
	}
	

}
