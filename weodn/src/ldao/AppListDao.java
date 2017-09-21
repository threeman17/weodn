package ldao;

import java.util.ArrayList;
import java.util.List;

import lbean.Applist;
import utils.DBHelper;
import utils.DataSource;

public class AppListDao {
	public List<Applist> getapps(){
		List<Applist> list=new ArrayList<Applist>();
		try {
			DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="select * from applist where ?=?";
		list=DBHelper.select(sql, Applist.class, 1,1);
		return list;
	}
	
	
	public List<Applist> idgetapps(String id){
		List<Applist> list=new ArrayList<Applist>();
		try {
			DataSource.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="select * from applist where id=?";
		list=DBHelper.select(sql, Applist.class,id);
		
		
		
		return list;
	}
}
