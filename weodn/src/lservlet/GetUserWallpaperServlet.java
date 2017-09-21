package lservlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import lbean.Applist;
import lbean.Luser;
import lbean.UserWallpaper;
import ldao.AppListDao;
import ldao.UserWallpaperDao;

/**
 * Servlet implementation class GetUserWallpaperServlet
 */
//@WebServlet("/GetUserWallpaperServlet")
public class GetUserWallpaperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserWallpaperServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Luser user=(Luser)session.getAttribute("LOGIN_STATUS");
		String wallpaper=user.getWallpaper();
		AppListDao app=new AppListDao();
		UserWallpaperDao dao=new UserWallpaperDao();
		List<UserWallpaper> list=dao.getwallpaper(wallpaper);
		if(list==null){
			response.getWriter().write("还没有添加应用！");
			return ;
		}
		List<Applist> ls=new ArrayList<>();
		for (UserWallpaper userWallpaper : list) {
			ls.add(app.idgetapps(userWallpaper.getid()).get(0));
			
		}
		Gson gson=new Gson();
		session.setAttribute("PAPER", gson.toJson(ls));
		response.setHeader("Content-type","text/html;charset=UTF-8");
		OutputStream stream=response.getOutputStream();
		stream.write(gson.toJson(ls).getBytes("UTF-8"));
	}

}
