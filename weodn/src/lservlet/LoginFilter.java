package lservlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lbean.Luser;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter extends HttpServlet implements Filter {
       
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;	
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String uri=req.getRequestURI();
		HttpSession session=req.getSession();		
		if(uri.contains("/userlist")){
			Luser userinfo=(Luser)session.getAttribute("LOGIN_STATUS");
			String rootid=userinfo.getUserid();
			String rootpassword=userinfo.getPassword();
			if(rootid.equals("root")&&rootpassword.equals("root")){
				chain.doFilter(request, response);
				return;
			}else
			{
				res.getWriter().write("进入失败！");
				return;
			}
		}
		
		
		
		if(uri.contains("/login")){
			chain.doFilter(request, response);
			return;
		}
		if(uri.contains("Login")){
			chain.doFilter(request, response);
			return;
		}		
		Object status=session.getAttribute("LOGIN_STATUS");		
		if(status==null){
			System.out.println(req.getContextPath());
			res.sendRedirect(req.getContextPath()+"/jsp/login.html");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	
	
}
