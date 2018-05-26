package priv.linjiayun.notetakingsystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest requst=(HttpServletRequest)servletRequest;
		HttpSession session=requst.getSession();
		Object o=session.getAttribute("currentUser");
		String path=requst.getServletPath();
		if(o==null&&(path.indexOf("login")<0&&path.indexOf("vistor")<0)&&path.indexOf("register")<0) {
			requst.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
		}else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
