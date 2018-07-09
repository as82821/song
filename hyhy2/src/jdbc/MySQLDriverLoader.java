package jdbc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

//웹 서버가 시작될 때 init메소드가 실행된다(web.xml에서 설정). mysql 연결시 Class.forName 사용할 필요가 없어진다.
public class MySQLDriverLoader extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
