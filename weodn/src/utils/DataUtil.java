package utils;

public class DataUtil {
	/**
	 * 将数据库字段标签转换为类的属性名称
	 * 
	 * @param columnLable
	 * @return
	 */
	public static String toFieldByColumnLable(String columnLable) {
		// user_info userInfo
		char[] cs = columnLable.toLowerCase().toCharArray();
		String fieldName = "" + cs[0];// 将第一个字符赋值给属性名称字符串
		for (int i = 1; i < cs.length; i++) {
			if (cs[i] == '_') {
				i++;
				fieldName += Character.toUpperCase(cs[i]);
			} else {
				fieldName += cs[i];
			}
		}
		return fieldName;
	}

	/**
	 * 将指定的属性名称转换为字段名称.
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String toColumnByFieldName(String fieldName) {
		// userName => user_info
		char[] cs = fieldName.toCharArray();
		String columnName = "" + cs[0];
		for (int i = 1; i < cs.length; i++) {
			if (Character.isUpperCase(cs[i])) {
				columnName += "_";
			}
			columnName += cs[i];
		}
		return columnName.toUpperCase();
	}

//	public static void main(String[] args) {
////		System.out.println(toFieldByColumnLable("user_name"));
//		System.out.println(toColumnByFieldName("userName"));
//	}
}
