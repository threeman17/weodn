package ldao;

import java.util.ArrayList;
import java.util.List;

import lbean.UserApp;
import utils.DBHelper;
import utils.DataSource;

public class UserAppDao {
		public List<UserApp> getApp(String userid){
			List<UserApp> list=new ArrayList<>();
			try {
				DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String sql="select * from userapp where userid=?";
			list=DBHelper.select(sql, UserApp.class,userid);
			return list;
		}
		
		public void insert(String userid,String id){
			try {
				DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String sql="insert into userapp (userid,id) values (?,?)";
			DataSource.updatede(sql.toString(), userid,id);
		}
}
