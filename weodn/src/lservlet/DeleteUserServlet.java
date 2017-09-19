package lservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import lbean.Luser;
import utils.DBHelper;
import utils.DataSource;

/**
 * Servlet implementation class UserListServlet
 */
//@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String userid=(String)request.getParameter("userid");
		System.out.println(userid);
		if(userid==null){
			System.out.println("值为空");
		}
		DBHelper.delete("and userid=?",userid);
		HttpSession session=request.getSession();
		session.removeAttribute("userlist");
		List<Luser> list0=null;
		String sql="select * from users where ?=?";
		list0=DBHelper.select(sql, Luser.class,1,1);					
		session.setAttribute("userlist",list0);
		Gson gson=new Gson();
		response.getWriter().print(gson.toJson(userid));
        response.getWriter().flush();
        response.getWriter().close();
		DBHelper.close();	}

	

}
