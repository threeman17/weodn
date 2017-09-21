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
import lbean.UserApp;
import ldao.AppListDao;
import ldao.UserAppDao;

/**
 * Servlet implementation class GetUserWallpaperServlet
 */
//@WebServlet("/GetUserWallpaperServlet")
public class GetUserAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Luser user=(Luser)session.getAttribute("LOGIN_STATUS");
		String userid=user.getUserid();//从session中取得当前用户userid
		AppListDao app=new AppListDao();
		UserAppDao dao=new UserAppDao();
		List<UserApp> list=dao.getApp(userid);//获取userapp表中所有userid（列）等于userid的对象
		if(list==null){
			response.getWriter().write("您还没有添加应用！");
			return ;
		}
		List<Applist> ls=new ArrayList<>();
		for (UserApp UserApp : list) {
			ls.add(app.idgetapps(UserApp.getId()).get(0));			
		}
		Gson gson=new Gson();
		session.setAttribute("apps", gson.toJson(ls));
		response.setHeader("Content-type","text/html;charset=UTF-8");
		OutputStream stream=response.getOutputStream();
		stream.write(gson.toJson(ls).getBytes("UTF-8"));
	}

}
