package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

public class DataSource {
	private static Connection connection;
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	/**
	 * 初始化数据源
	 * 
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 * @throws ClassNotFoundException
	 */
	public static void init(String driver, String url, String username, String password) throws ClassNotFoundException {
		DataSource.driver = driver;
		DataSource.url = url;
		DataSource.username = username;
		DataSource.password = password;
		Class.forName(driver);
	}

	/**
	 * 释放连接
	 */
	public static void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	private static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 执行更新SQL
	 * 
	 * @param sql
	 *            SQL
	 * @param params
	 *            参数列表
	 * @return
	 */
	public static int update(String sql) {
		Connection connection = getConnection();
		PreparedStatement ps = null;
		try {
			System.out.println(sql);
			ps = connection.prepareStatement(sql);// 获取预编译操作对象
//			for (int i = 0; i < params.length; i++) {
//				ps.setObject(i + 1, params[i]);
//			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 查询数据
	 * 
	 * @param sql
	 *            Sql
	 * @param params
	 *            Sql中需要使用的参数
	 * @return
	 */
	public static CachedRowSet select(String sql, Object... params) {
		Connection connection = getConnection();
		PreparedStatement ps = null;
		CachedRowSet crs = null;
		ResultSet rs = null;
		try {
			crs = new CachedRowSetImpl();
			ps = connection.prepareStatement(sql);// 获取预编译操作对象
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			crs.populate(rs);// 解析ResultSet
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return crs;
	}

}
