package lservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lbean.Luser;
import ldao.UserAppDao;

/**
 * Servlet implementation class AddAppServlet
 */
//@WebServlet("/AddAppServlet")
public class AddAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyid=request.getParameter("keyid");
		HttpSession session=request.getSession();
		Luser user=(Luser)session.getAttribute("LOGIN_STATUS");
		String userid=user.getUserid();
		UserAppDao dao=new UserAppDao();
		dao.insert(userid,keyid);
	}

}
