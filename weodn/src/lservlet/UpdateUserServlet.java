package lservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lbean.Luser;
import utils.DBHelper;
import utils.DataSource;

/**
 * Servlet implementation class UpdateUserServlet
 */
//@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session=request.getSession(false);
		Luser userinfo=(Luser)session.getAttribute("LOGIN_STATUS");
		String userid=userinfo.getUserid();
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		DBHelper.updateuser("userid='"+userid+"'", "nickname='"+nickname+"'","email='"+email+"'","password='"+password+"'");
		response.getWriter().write("提交成功");
		response.setHeader("refresh","1;url=../jsp/showuser.jsp");
		DBHelper.close();
	}

}
