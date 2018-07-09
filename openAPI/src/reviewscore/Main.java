package reviewscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";
		Random ran = new Random();
		int numofplace;
		int contentid = 0;
		int placecnt;
		int totalcnt = 1;
		String userid;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;

			for (int i = 1; i <= 3000; i++) {
				numofplace = ran.nextInt(10) + 40;
				userid = "user" + i;
				for (int j = 0; j < numofplace; j++) {
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT contentid FROM apidb ORDER BY RAND() LIMIT 1");
					while (rs.next()) {
						contentid = rs.getInt("contentid");
					}

					placecnt = ran.nextInt(11) + 1;
					if (placecnt >= 1 && placecnt <= 6) {
						placecnt = 1;
					} else if (placecnt >= 7 && placecnt <= 9) {
						placecnt = 2;
					} else if (placecnt >= 10 && placecnt <= 11) {
						placecnt = 3;
					}

					for (int k = 0; k < placecnt; k++) {
						Score sc = new Score();
						double score = sc.getScore();
						pstmt = conn.prepareStatement(
								"insert into review (reviewid,userid,contentid,score) values (?,?,?,?)");
						pstmt.setInt(1, totalcnt);
						pstmt.setString(2, userid);
						pstmt.setInt(3, contentid);
						pstmt.setDouble(4, score);
						pstmt.executeUpdate();
						totalcnt++;
					}
				}
			}

			end = System.currentTimeMillis();
			time = end - start;
			System.out.println("걸린 시간 " + time + "ms");
			minute = ((time / (1000 * 60)) % 60);
			System.out.println(minute + "분");
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
