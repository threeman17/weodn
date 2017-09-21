package ldao;

import java.util.ArrayList;
import java.util.List;

import lbean.UserWallpaper;
import utils.DBHelper;
import utils.DataSource;

public class UserWallpaperDao {
		public List<UserWallpaper> getwallpaper(String wallpaper){
			List<UserWallpaper> list=new ArrayList<>();
			try {
				DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String sql="select * from userwallpaper where wallpaper=?";
			list=DBHelper.select(sql, UserWallpaper.class,wallpaper);
			return list;
		}
}
