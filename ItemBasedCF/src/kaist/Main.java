package kaist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		int contentid;
		double score = 0;
		int visit = 0;
		int totalvisit = 0;
		String userid1 = "";
		String userid2 = "";
		int totalcnt = 0;

		Map<Integer, Double> imap = new HashMap<Integer, Double>();
		Map<Integer, Double> jmap = new HashMap<Integer, Double>();

		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;

			for (int i = 295; i <= 295; i++) {
				userid1 = "user" + i;
				for (int j = 2967; j <= 2967; j++) {
					totalcnt = 0;
					userid2 = "user" + j;
					pstmt = conn.prepareStatement(
							"select score,visit,totalvisit,contentid,count(*) as jjb from (select * from significantscore where userid=(?) or userid=(?)) as A group by contentid having count(*)>1");
					pstmt.setString(1, userid1);
					pstmt.setString(2, userid2);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						contentid = rs.getInt("contentid");
						cnt = rs.getInt("jjb");
						score = rs.getDouble("score");
						visit = rs.getInt("visit");
						totalvisit = rs.getInt("totalvisit");
						System.out.println(contentid + " " + cnt + " user" + j + " 점수 " + score + " 방문수 " + visit
								+ " 총 방문수 " + totalvisit);
						totalcnt++;
					}

					if (totalcnt > 2) {
						System.out.println();
						System.out.println("유저 " + i + "가" + "유저 " + j + "와 겹치는 관광지 수" + totalcnt);
						System.out.println();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
