package utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import lbean.Luser;



public class DBHelper {

	public static void init(String driver, String url, String username, String password) throws ClassNotFoundException {
		DataSource.init(driver, url, username, password);
	}

	public static void close() {
		DataSource.close();
	}

//	public static int insert(Object obj) {
//		// insert into tableName(column1,column2...) values(?,?...)
//		// 1.表名 => 通过类名转换获得
//		// 2.字段名 => 通过属性名转换获得
//		// 3.顺序 => 通过集合保存顺序
//		// 4.有值才插入 => 判断属性是否不为null
//
//		Class<?> type = obj.getClass();
//		List<String> fieldNames = ClassUtils.getFieldNames(type);
//		List<String> names = new ArrayList<>();
//		List<Object> values = new ArrayList<>();
//		for (String fieldName : fieldNames) {
//			try {
//				// 从待新增对象中获取属性的值
//				Object value = ClassUtils.get(obj, fieldName);
//				if (value != null) {
//					// 保存列的顺序
//					names.add(DataUtil.toColumnByFieldName(fieldName));
//					// 保存值的顺序
//					values.add(value);
//				}
//			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//				e.printStackTrace();
//			}
//		}
//		// 拼接SQL
//		StringBuffer sb = new StringBuffer();
//		sb.append("insert into ");
//		// 获取表名并追加到SQL中
//		sb.append(DataUtil.toColumnByFieldName(type.getSimpleName()));
//		sb.append("(");
//		for (String name : names) {
//			sb.append(name).append(",");
//		}
//		// 删除最后多余的逗号
//		sb.deleteCharAt(sb.length() - 1);
//		sb.append(") values(");
//		for (Object object : values) {
//			sb.append("?,");
//		}
//		// 删除最后多余的逗号
//		sb.deleteCharAt(sb.length() - 1);
//		sb.append(")");
//		System.out.println(sb);
//		return DataSource.update(sb.toString(), values.toArray());
//	}

//	public static int update(Object obj, String where, Object... params) throws SQLException {
//		// update tableName set column=?,column2=?... where
//		// and ...
//		// or ...
//		// 1.表名
//		// 2.字段名
//		// 3.顺序
//		Class<? extends Object> type = obj.getClass();
//		StringBuffer sql = new StringBuffer();
//		sql.append("update ");
//		sql.append(DataUtil.toColumnByFieldName(type.getSimpleName()));
//		sql.append(" set ");
//		List<String> fieldNames = ClassUtils.getFieldNames(type);
//		List<Object> values = new ArrayList<>();
//		for (String fieldName : fieldNames) {
//			try {
//				Object value = ClassUtils.get(obj, fieldName);
//				if (value != null) {
//					sql.append(" ").append(DataUtil.toColumnByFieldName(fieldName)).append("=?,");
//					values.add(value);
//				}
//			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//				e.printStackTrace();
//			}
//		}
//		if (values.isEmpty()) {
//			throw new SQLException("没有需要更新的数据");
//		}
//		for (Object object : params) {
//			values.add(object);
//		}
//		// 删除最后一个多余的逗号
//		sql.deleteCharAt(sql.length() - 1);
//		sql.append(" where 1=1 ").append(where);
//		return DataSource.update(sql.toString(), values.toArray());
//	}

	public static int updateuser(String where,String... params){
//		update users set column=?,column2=?... where  and ...
		StringBuffer update = new StringBuffer();
		update.append("update users set ");				
		List<String> values = new ArrayList<>();
		for (String string : params) {
			values.add(string);
			update.append(string);
			update.append(",");
		}
		update.deleteCharAt(update.length() - 1);
		update.append(" where 1=1 and ");
		update.append(where);
//		System.out.println(update.toString());
		return DataSource.update(update.toString());
		} 
	
	
	
//	public static int delete(Class<?> type, String where, Object... params) {
//		return DataSource.update(
//				"delete from " + DataUtil.toColumnByFieldName(type.getSimpleName()) + " where 1=1 " + where, params);
//	}

	public static <T> List<T> select(String sql, Class<T> type, Object... params) {
		// select ... from tablename where ....
		// select count(1) from (select ... from tablename where ....)
		try {
			ThreadLocal<Integer> pageLocal = ClassUtils.get(PageInfo.class, "pageLocal");
			if (pageLocal.get() != null) {//如果当前页不为null,则表示分页
				ThreadLocal<Integer> pageSizeLocal = ClassUtils.get(PageInfo.class, "pageSizeLocal");
				//去数据总数
				System.out.println("select count(1) total from (" + sql + ") d");
				CachedRowSet totalRs = DataSource.select("select count(1) total from (" + sql + ") d", params);
				totalRs.next();
				int total = totalRs.getInt(1);
				ThreadLocal<Integer> totalLocal = ClassUtils.get(PageInfo.class, "totalLocal");
				totalLocal.set(total);
				sql+="limit "+pageLocal.get()+","+pageSizeLocal.get();
			}
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
				| SecurityException e2) {
			e2.printStackTrace();
		}

		CachedRowSet rs = DataSource.select(sql, params);
		List<T> list = new ArrayList<>();
		try {
			// 获取查询出来的虚拟表的元数据
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();// 获取虚拟表中的列的数量
			String[] columnNames = new String[count];
			// 获取所有的列名
			for (int i = 0; i < columnNames.length; i++) {
				columnNames[i] = md.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				try {
					T e = ClassUtils.newInstance(type);
					for (String columnName : columnNames) {
						// 从结果集中取出数据
						Object value = rs.getObject(columnName);
						// 将列名转换为属性名
						String fieldName = DataUtil.toFieldByColumnLable(columnName);
						// 检查属性在类中是否存在
						if (ClassUtils.isFiled(type, fieldName)) {
							try {
								// 将数据设置到属性中
								ClassUtils.set(e, fieldName, value);
							} catch (NoSuchFieldException e1) {
								e1.printStackTrace();
							}
						}
					}
					list.add(e);
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static void startPage(int page, int pageSize) {
		try {
			ThreadLocal<Integer> pageLocal = ClassUtils.get(PageInfo.class, "pageLocal");
			ThreadLocal<Integer> pageSizeLocal = ClassUtils.get(PageInfo.class, "pageSizeLocal");
			pageLocal.set(page);
			pageSizeLocal.set(pageSize);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		DBHelper.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/weod", "root", "root");
//		DBHelper.delete(Student.class, "and id=?", 3);
//		DBHelper.startPage(3, 2);
//		List<Luser> list = DBHelper.select("select * from users where userid=? and password=?", Luser.class,"1","2");
//		System.out.println(list.get(0));
//		PageInfo<Student> stus=new PageInfo<>(list);
//		System.out.println(stus);

//		 Luser user = new Luser();
//		 user.setNickname("张三");
//		 DBHelper.update(user, "and id=?", 1);
//		DBHelper.updateuser("userid='1'","nickname='张三'","password='3'");
//		// System.out.println(DBHelper.insert(stu));
//		DBHelper.close();
//	}
}
