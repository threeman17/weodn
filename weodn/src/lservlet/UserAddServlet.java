package lservlet;

import java.io.IOException;
import java.io.PrintWriter;

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
		response.setHeader("text/html", "charset=utf-8"); 
		LuserDao dao=new LuserDao();
		PrintWriter out = response.getWriter();
		if(userid.isEmpty()||nickname.isEmpty()||password.isEmpty()||email.isEmpty()){			   
			    out.flush();
			    out.println("<script>");
			    out.println("alert('请将信息填写完整！');");
			    out.println("history.back();");
			    out.println("</script>");
			return;
		}
		dao.add(userid, nickname, password, email);					   
	    out.write("注册成功！");
	    response.setHeader("refresh","1;url=../jsp/login.html");
	    out.close();
	
	}

}
