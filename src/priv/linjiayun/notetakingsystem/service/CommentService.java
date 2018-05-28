package priv.linjiayun.notetakingsystem.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.linjiayun.notetakingsystem.dao.CommentDao;
import priv.linjiayun.notetakingsystem.dao.UserDao;
import priv.linjiayun.notetakingsystem.entity.Comment;
import priv.linjiayun.notetakingsystem.entity.User;
import priv.linjiayun.notetakingsystem.util.StringUtil;


public class CommentService {
	private CommentDao commentDao=new CommentDao();
	private UserDao userDao=new UserDao();
/**
 * 获取用户评论
 * @param req
 * @param resp
 * @param note_id
 * @param user_id
 */
	public void seeAllUserComments(HttpServletRequest req, HttpServletResponse resp, int note_id, int user_id) {
		try {
			List<Comment> comments=commentDao.findComments(user_id, note_id);
			List<User> users=new ArrayList<User>();
			for(Comment c:comments) {
				User user=new User();
				user=userDao.findUserByid(c.getUser_id());
				users.add(user);				
			}
			
			HttpSession session=req.getSession();
			session.setAttribute("comments", comments);
			session.setAttribute("users", users);
			resp.sendRedirect("mian.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("所有评论获取异常");
		}
		
		
		
		// TODO Auto-generated method stub
		
	}
	public void seeAllComments(HttpServletRequest req, HttpServletResponse resp, int note_id) {
		try {
			List<Comment> comments=commentDao.findCommentBynote_id(note_id);
			List<User> users=new ArrayList<User>();
			for(Comment c:comments) {
				User user=new User();
				user=userDao.findUserByid(c.getUser_id());
				users.add(user);				
			}
			
			if(comments.isEmpty()) {
				req.setAttribute("comments", "暂时还没有评论，快抢沙发...");
				req.getRequestDispatcher("main.jsp").forward(req, resp);
			}else {
				req.setAttribute("comments", comments);
				req.setAttribute("users", users);
				req.getRequestDispatcher("main.jsp").forward(req, resp);
//				resp.sendRedirect("main.jsp");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("所有评论获取异常");
		}
		
		
		
		// TODO Auto-generated method stub
		
	}
	/**
	 * 发表评论
	 * @param req
	 * @param resp
	 * @param noteid
	 * @param userid
	 * @param body
	 */
	public void addComment(HttpServletRequest req, HttpServletResponse resp, int noteid, int userid, String body) {
		try {
			if(StringUtil.isEmpty(body)) {
				req.getRequestDispatcher("main.jsp").forward(req, resp);
				req.getSession().setAttribute("currentUser", userDao.findUserByid(userid));
				
				return;
			}
			Date date = new Date();  

		       SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制  

		       String create_time = sdformat.format(date);
		    String body2=StringUtil.htmlReplace(body);
		    Comment comment=new Comment(userid, body2, create_time, noteid);
		   if( commentDao.addComment(comment)) {
			   req.getRequestDispatcher("main.jsp").forward(req, resp);
			   req.getSession().setAttribute("currentUser", userDao.findUserByid(userid));
			   
		   }
		   
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加评论异常");
		}
		
		
	    
		
	}
	
	

}
