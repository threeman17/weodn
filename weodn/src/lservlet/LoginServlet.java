package lservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lbean.Luser;
import utils.DBHelper;
import utils.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
// @WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");
		System.out.println(userid);
		String password = request.getParameter("password");
		try {
			DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		List<Luser> list=null;
		list = DBHelper.select("select * from users where userid=? and password=?", Luser.class, userid,
				password);
		System.out.println(list);
		if (list.isEmpty()||list==null) {
			response.getWriter().write("登陆失败！");
//			response.sendRedirect(request.getContextPath() + "/jsp/login.html");
		}
		Luser userinfo = list.get(0);
		HttpSession session = request.getSession();
		session.setAttribute("LOGIN_STATUS", userinfo);
		System.out.println(userinfo);
		DBHelper.close();
		response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
	}

}
