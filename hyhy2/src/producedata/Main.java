package producedata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/*
 * 유저간 유사도를 계산하기 위해 필요한 파일을 만드는 클래스
 * DB에 저장되어 있는 contentid, score,visit,totalvisit정보를 이용해 유저의 sigs를 계산하고 이를 파일에 저장한다.
 */
public class Main {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://aws-song.cdzl90330te3.us-east-2.rds.amazonaws.com/TourAPI?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "HYsong";
		String pass = "1q2w3e4r";
		BufferedWriter bw = new BufferedWriter(new FileWriter("data\\WEB_usersigs.csv"));
		DecimalFormat df = new DecimalFormat("#.####");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int contentid;
		double score = 0;
		int visit = 0;
		int totalvisit = 0;
		String userid = "";
		double sigs;
		String ssigs;
		double tmpvisit;
		String data = "";

		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;

			for (int i = 1; i <= 3000; i++) {
				userid = "user" + i;
				pstmt = conn.prepareStatement("select * from significantscore where userid=(?)");
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					contentid = rs.getInt("contentid");
					score = rs.getDouble("score");
					visit = rs.getInt("visit");
					totalvisit = rs.getInt("totalvisit");
					tmpvisit = (double) visit / totalvisit;
					sigs = (0.5 * (score / 5.0) + 0.5 * (tmpvisit)) * 10;
					ssigs = df.format(sigs);
					data = "" + i + "," + contentid + "," + ssigs;
					//System.out.println(data);
					bw.write(data + "\n");
				}

			}
			bw.close();
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
