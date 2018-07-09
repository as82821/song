package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomUser {

	public static void main(String[] args) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";

		Random ran = new Random();
		int gendernum;
		int age;
		String randomname;
		String userid;

		List<String> lname = Arrays.asList("��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "Ȳ", "��", "��", "��", "��", "ȫ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ä", "��", "õ", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "ǥ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "Ź", "��", "��",

				"��", "��", "��", "��", "��", "��");

		List<String> fname = Arrays.asList("��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "â", "ä", "õ", "ö", "��",

				"��", "��", "ġ", "Ž", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ȣ", "ȫ", "ȭ", "ȯ", "ȸ", "ȿ",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "ȥ", "Ȳ", "��", "��", "��", "��", "��", "��", "��", "Ź", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "Ÿ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",

				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcDriver, user, pass);
			for (int i = 1; i <= 3000; i++) {
				userid = "user" + i;
				gendernum = ran.nextInt(2);
				age = ran.nextInt(70) + 10;
				Collections.shuffle(lname);
				Collections.shuffle(fname);
				randomname = lname.get(0) + fname.get(0) + fname.get(1);
				pstmt = conn.prepareStatement("insert into user values (?,?,?,?)");
				pstmt.setString(1, userid);
				if (gendernum == 0)
					pstmt.setString(2, "M");
				else if (gendernum == 1)
					pstmt.setString(2, "F");
				pstmt.setInt(3, age);
				pstmt.setString(4, randomname);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
