package lservlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ldao.AppListDao;

/**
 * Servlet implementation class GetAppsServlet
 */
//@WebServlet("/GetAppsServlet")
public class GetAppsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppListDao dao=new AppListDao();
		Gson gson=new Gson();		
		response.setHeader("Content-type","text/html;charset=UTF-8");
		OutputStream stream=response.getOutputStream();
		stream.write(gson.toJson(dao.getapps()).getBytes("UTF-8"));
	}

}
