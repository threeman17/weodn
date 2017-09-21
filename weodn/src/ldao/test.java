package ldao;






public class test {

	public static void main(String[] args) {
		UserAppDao dao=new UserAppDao();
		System.out.println(dao.getApp("1"));
	}
}
