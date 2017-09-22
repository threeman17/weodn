package ldao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.DBHelper;
import utils.DataSource;

public class LuserDao {
	public void add(String userid,String nickname,String password,String email){
		try {
			DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Date date=new Date();     //获取一个Date对象
		DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
		String punchTime = simpleDateFormat.format(date); 
		String sql="insert into users (userid,nickname,password,email,createtime,updatetime,headpic,wallpaper) values (?,?,?,?,?,?,?,?)";
		DataSource.updatede(sql.toString(),userid,nickname,password,email,punchTime,punchTime,"/himg/11.png","./img/wallpapers/main.jpg");
		DBHelper.close();
	}
	public void updatewallpaper(String userid,String wallpaper){
		try {
			DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update users set wallpaper=? where userid=?";
		
		DataSource.updatede(sql.toString(),wallpaper,userid);
		
	}
	
	
}
