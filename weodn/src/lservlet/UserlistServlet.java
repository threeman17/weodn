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
 * Servlet implementation class HoutaiServlet
 */
//@WebServlet("/UserlistServlet")
public class UserlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		HttpSession session = request.getSession();
		List<Luser> list0=null;
		String sql="select * from users where ?=?";
		list0=DBHelper.select(sql, Luser.class,1,1);					
		session.setAttribute("userlist",list0);
		DBHelper.close();
		System.out.println(list0);
		response.sendRedirect(request.getContextPath() + "/jsp/userlist.jsp");	
		}

}
