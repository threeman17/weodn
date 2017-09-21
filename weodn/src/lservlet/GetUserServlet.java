package lservlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import lbean.Luser;

/**
 * Servlet implementation class GetUserServlet
 */
//@WebServlet("/GetUserServlet")
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
	Luser userinfo=(Luser)session.getAttribute("LOGIN_STATUS");
	Gson gson=new Gson();
	String user =gson.toJson(userinfo);
	response.setHeader("Content-type","text/html;charset=UTF-8");
	OutputStream stream=response.getOutputStream();
	stream.write(user.getBytes("UTF-8"));
	}

}
