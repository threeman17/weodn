package lservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lbean.Luser;
import ldao.LuserDao;

/**
 * Servlet implementation class UpdateWallpaperServlet
 */
//@WebServlet("/UpdateWallpaperServlet")
public class UpdateWallpaperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWallpaperServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Luser user=(Luser)session.getAttribute("LOGIN_STATUS");
		String userid=user.getUserid();
		String wallpaper=request.getParameter("src");
		LuserDao dao=new LuserDao();
		dao.updatewallpaper(userid, wallpaper);
	}

}
