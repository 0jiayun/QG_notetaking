package priv.linjiayun.notetakingsystem.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.linjiayun.notetakingsystem.dao.NoteDao;
import priv.linjiayun.notetakingsystem.entity.Note;
import priv.linjiayun.notetakingsystem.util.StringUtil;

public class NoteService {
	private NoteDao noteDao=new NoteDao();
	
    /**
     * 创建笔记
     * @param req
     * @param resp
     * @param user_id
     * @param title
     * @param body
     * @param purview
     */
	public void addNoteCheckup(HttpServletRequest req, HttpServletResponse resp, int user_id, String title,
			String body,String purview) {
		int purview_id=0;
		if(purview.equals("intimate")) {
			purview_id=1;			
		}
		Date date = new Date();  

	       SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制  

	       String create_time = sdformat.format(date);
	    try {
	    	   if(StringUtil.isEmpty(title)) {
	   	    	
	   			req.setAttribute("body", body);
	   			req.setAttribute("purview", purview);
	   			req.setAttribute("error1", "标题不能为空");
	   			req.getRequestDispatcher("addNewNote.jsp").forward(req, resp);
	   			return;
	   	       }
	    	   if(title.length()>30) {
	    		    req.setAttribute("title", title);
		   			req.setAttribute("body", body);
		   			req.setAttribute("purview", purview);
		   			req.setAttribute("error1", "标题长度小于等于30");
		   			req.getRequestDispatcher("addNewNote.jsp").forward(req, resp);
		   			return;
		   	       }
	    	   if(StringUtil.isEmpty(body)) {
	    		    req.setAttribute("title", title);
	    		   
		   			req.setAttribute("purview", purview);
		   			req.setAttribute("error1", "内容不能为空");
		   			req.getRequestDispatcher("addNewNote.jsp").forward(req, resp);
		   			return;
	    		   
	    	   }
	    	   if(body.length()>2500) {
	    		    req.setAttribute("title", title);
	    		    req.setAttribute("body", body);
		   			req.setAttribute("purview", purview);
		   			req.setAttribute("error1", "内容长度小于2500");
		   			req.getRequestDispatcher("addNewNote.jsp").forward(req, resp);
		   			return;
	    		   
	    	   }
            String title2=StringUtil.htmlReplace(title);
            String body2=StringUtil.htmlReplace(body);
	   		Note note=new Note(user_id, title2,body2,create_time,purview_id);
	   		
	   			if(noteDao.add(note)) {
	   				req.setAttribute("title", title);
	   				req.setAttribute("body", body);
	   				req.setAttribute("purview", purview);
	   				req.setAttribute("error2", "笔记创建成功，时间为："+create_time);
	   				req.getRequestDispatcher("addNewNote.jsp").forward(req, resp);
	   			}else {
	   				req.setAttribute("title", title);
	   				req.setAttribute("body", body);
	   				req.setAttribute("purview", purview);
	   				req.setAttribute("error1", "笔记创建失败");
	   				req.getRequestDispatcher("addNewNote.jsp").forward(req, resp);
	   				
	   			}
	 
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("笔记创建异常");
			
		}
		
		
		
		
	}
	/**
	 * 删除笔记
	 * @param req
	 * @param resp
	 * @param note_id
	 */

	public void deleteNote(HttpServletRequest req, HttpServletResponse resp, int note_id) {
		try {
			if(noteDao.delete(note_id)) {
				req.setAttribute("error2", "删除成功");
				req.getRequestDispatcher("noteAdmin.jsp").forward(req, resp);
			}else {
				req.setAttribute("error1", "删除失败");
				req.getRequestDispatcher("noteAdmin.jsp").forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("delete笔记异常");
		}
	}
/**
 * to查笔记
 * @param req
 * @param resp
 * @param note_id
 */
	public void toUpdateNote(HttpServletRequest req, HttpServletResponse resp, int note_id) {
		try {
			Note note=noteDao.findNote(note_id);
			req.setAttribute("note_id", note_id);
			int pv=note.getPurview();
			String purview=new String();
			if(pv==0) {
				purview="public";
			}else {
				purview="intimate";
			}
			req.setAttribute("title", note.getTitle());
			req.setAttribute("body", note.getBody());
			req.setAttribute("purview", purview);
			req.setAttribute("create_time", note.getCreate_time());
			req.getRequestDispatcher("updateNote.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("to查笔记异常");
		}
		
	}
	/**
	 * 获取所有公开笔记
	 * @return
	 */
	public List<Note> getPublicNotes(){
		List<Note> notes=noteDao.getAll();
		
		Iterator<Note> noteIter=notes.iterator();
		while(noteIter.hasNext()) {
			Note note=noteIter.next();
			int purview_id=note.getPurview();
			if(purview_id==1) {
				noteIter.remove();
			}
			
		}
		return notes;
		
	}
/**
 * 修改笔记
 * @param req
 * @param resp
 * @param note_id
 * @param title
 * @param body
 * @param purview
 */
public void updateNote(HttpServletRequest req, HttpServletResponse resp, int note_id, String title, String body,
		String purview,String create_time) {
	String noteid=String.valueOf(note_id);
	int purview_id=0;
	if(purview.equals("intimate")) {
		purview_id=1;			
	}
	Date date = new Date();  

       SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制  

       String last_modify_time = sdformat.format(date);
    try {
    	   if(StringUtil.isEmpty(title)) {
   	    	req.setAttribute("note_id", noteid);
   			req.setAttribute("body", body);
   			req.setAttribute("create_time", create_time);
   			req.setAttribute("purview", purview);
   			req.setAttribute("error1", "标题不能为空");
   			req.getRequestDispatcher("updateNote.jsp").forward(req, resp);
   			return;
   	       }
    	   if(title.length()>30) {
    		    req.setAttribute("note_id", noteid);
    		    req.setAttribute("title", title);
    		    req.setAttribute("create_time", create_time);
	   			req.setAttribute("body", body);
	   			req.setAttribute("purview", purview);
	   			req.setAttribute("error1", "标题长度小于等于30");
	   			req.getRequestDispatcher("updateNote.jsp").forward(req, resp);
	   			return;
	   	       }
    	   if(StringUtil.isEmpty(body)) {
    		    req.setAttribute("title", title);
    		    req.setAttribute("note_id", noteid);
    		    req.setAttribute("create_time", create_time);
	   			req.setAttribute("purview", purview);
	   			req.setAttribute("error1", "内容不能为空");
	   			req.getRequestDispatcher("updateNote.jsp").forward(req, resp);
	   			return;
    		   
    	   }
    	   if(body.length()>2500) {
    		    req.setAttribute("title", title);
    		    req.setAttribute("note_id", noteid);
    		    req.setAttribute("body", body);
    		    req.setAttribute("create_time", create_time);
	   			req.setAttribute("purview", purview);
	   			req.setAttribute("error1", "内容长度要小于2500");
	   			req.getRequestDispatcher("updateNote.jsp").forward(req, resp);
	   			return;
    		   
    	   }

    	String title2=StringUtil.htmlReplace(title);
        String body2=StringUtil.htmlReplace(body);
   		Note note=new Note();
   		note.setId(note_id);
   		note.setBody(body2);
   		note.setTitle(title2);
   		note.setLast_modify_time(last_modify_time);
   		note.setPurview(purview_id);
   		
   			if(noteDao.updateNote(note)) {
   				req.setAttribute("title", title);
   				req.setAttribute("note_id", noteid);
   				req.setAttribute("body", body);
   				req.setAttribute("create_time", create_time);
   				req.setAttribute("purview", purview);
   				req.setAttribute("error2", "笔记修改成功，时间为："+last_modify_time);
   				req.getRequestDispatcher("updateNote.jsp").forward(req, resp);
   			}else {
   				req.setAttribute("title", title);
   				req.setAttribute("note_id", noteid);
   				req.setAttribute("body", body);
   				req.setAttribute("create_time", create_time);
   				req.setAttribute("purview", purview);
   				req.setAttribute("error1", "笔记创建失败");
   				req.getRequestDispatcher("updateNote.jsp").forward(req, resp);
   				
   			}
 
	}catch(Exception e){
		e.printStackTrace();
		throw new RuntimeException("笔记创建异常");
		
	}
	
}
/**
 * 查看点击文章
 * @param req
 * @param resp
 * @param note_id
 */
public void seeOnclickNote(HttpServletRequest req, HttpServletResponse resp, int note_id) {
	try {
		Note note=noteDao.findNote(note_id);
		HttpSession session=req.getSession();
		session.setAttribute("note_title", note.getTitle());
		session.setAttribute("note_body", note.getBody());
		session.setAttribute("user_id", note.getUser_id());
		session.setAttribute("note_id", note.getId());
		req.getRequestDispatcher("main.jsp").forward(req, resp);
		
	}catch(Exception e) {
		e.printStackTrace();
		throw new RuntimeException("查看点击文章异常");
	}
	// TODO Auto-generated method stub
	
}
/**
 * 笔记查找
 * @param req
 * @param resp
 * @param note_name
 */
public void addFindNote(HttpServletRequest req, HttpServletResponse resp, String note_title) {
	try {
		String title2=StringUtil.htmlReplace(note_title);
		List<Note> noteList=noteDao.findNoteByName(title2);
		Iterator<Note> noteIter=noteList.iterator();
		while(noteIter.hasNext()) {
			Note n=noteIter.next();
			if(n.getPurview()==1) {
				noteIter.remove();
			}
		}
		req.setAttribute("noteList", noteList);
		req.setAttribute("search", note_title);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}catch(Exception e) {
		e.printStackTrace();
		throw new RuntimeException("笔记search异常");
	}
	// TODO Auto-generated method stub
	
}

}
