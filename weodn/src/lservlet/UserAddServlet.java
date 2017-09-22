package lservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ldao.LuserDao;

/**
 * Servlet implementation class UserAddServlet
 */
//@WebServlet("/UserAddServlet")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		String nickname=request.getParameter("nickname");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		LuserDao dao=new LuserDao();
		dao.add(userid, nickname, password, email);
		response.setHeader("Content-type: text/html", "charset=utf-8"); 
		response.getWriter().write("注册成功!");
		response.setHeader("refresh","1;url=../jsp/login.html");
		
	}

}
