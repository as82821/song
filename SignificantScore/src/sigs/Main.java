package sigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		List<Integer> placelist = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String userid = "";
		int placeid = 0;
		double tmpscore;
		int placevisit;
		int totalvisit;
		double latestscore;
		String tmpdate;
		String tmpdate_forcal = "";
		String latestdate = "19700101";
		StringBuilder sb;
		// Date insertdate;

		try {
			String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
					+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
			String user = "root";
			String pass = "wlgus132!!";
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			for (int j = 1; j <= 3000; j++) {
				userid = "user" + j;
				totalvisit = 0;
				placelist.clear();

				pstmt = conn.prepareStatement("select distinct(contentid) from review where userid=(?)");
				pstmt.setString(1, userid);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					placeid = rs.getInt("contentid");
					// System.out.println(placeid);
					placelist.add(placeid);
				}

				for (int i = 0; i < placelist.size(); i++) {
					placevisit = 0;
					latestscore = 0;
					latestdate = "19700101";
					// System.out.println(placelist.get(i));
					pstmt = conn
							.prepareStatement("select score,reviewdate from review where contentid=(?) and userid=(?)");
					pstmt.setInt(1, placelist.get(i));
					pstmt.setString(2, userid);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						tmpdate_forcal = "";
						tmpscore = rs.getDouble("score");
						tmpdate = rs.getString("reviewdate");

						/* 2015-06-06의 날짜형식을 20150606으로 바꿈 */
						StringTokenizer st = new StringTokenizer(tmpdate, "-");
						while (st.hasMoreTokens()) {
							tmpdate_forcal += st.nextToken();
						}

						if (Integer.parseInt(tmpdate_forcal) > Integer.parseInt(latestdate)) {
							latestdate = tmpdate_forcal;
							latestscore = tmpscore;
						}
						placevisit++;
						// System.out.println(tmpscore + " " + tmpdate + " " + tmpdate_forcal);
					}
					// System.out.println("최종 넣을 데이터 " + latestscore + " " + latestdate + " " +
					// placevisit);
					pstmt2 = conn.prepareStatement(
							"insert into significantscore (userid,contentid,score,visit) values (?,?,?,?)");
					pstmt2.setString(1, userid);
					pstmt2.setInt(2, placelist.get(i));
					pstmt2.setDouble(3, latestscore);
					pstmt2.setInt(4, placevisit);
					// System.out.println();
					pstmt2.executeUpdate();
				}
				pstmt3 = conn.prepareStatement("select count(*) from review where userid=(?)");
				pstmt3.setString(1, userid);
				rs2 = pstmt3.executeQuery();
				while (rs2.next()) {
					totalvisit = rs2.getInt("count(*)");
				}
				pstmt4 = conn.prepareStatement("update significantscore set totalvisit=(?) where userid=(?)");
				pstmt4.setInt(1, totalvisit);
				pstmt4.setString(2, userid);
				pstmt4.executeUpdate();
			}

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
			if (rs2 != null) {
				try {
					rs2.close();
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
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt3 != null) {
				try {
					pstmt3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt4 != null) {
				try {
					pstmt3.close();
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
