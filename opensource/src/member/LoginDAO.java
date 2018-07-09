package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	private static LoginDAO instance;

	// 싱글톤 패턴
	private LoginDAO() {
	}

	public static LoginDAO getInstance() {
		if (instance == null)
			instance = new LoginDAO();
		return instance;
	}

	public int logincheck(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tmpvalue = 0;

		try {
			String tmppw = "";

			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/weatherfit?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "root";
			String pwd = "1234";
			Class.forName(driver);

			conn = DriverManager.getConnection(url, username, pwd);
			pstmt = conn.prepareStatement("select pw from signup where id=(?)");

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 성공이면 0, 비번오류 0, 아이디가 존재하지 않으면 -1
			if (rs.next()) {
				tmppw = rs.getString("pw");
				if (tmppw.equals(pw))
					tmpvalue = 1;
				else
					tmpvalue = 0;
			} else
				tmpvalue = -1;

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			System.out.println(ce.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

		}
		return tmpvalue;
	}
}
