package tourAPI_image_overview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws Exception {
		Parsing myparsing = new Parsing();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";
		int contentid;
		int contenttypeid;
		String image = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT contentid,contenttypeid,image FROM apidb");
			while (rs.next()) {
				image = "";
				contentid = rs.getInt("contentid");
				contenttypeid = rs.getInt("contenttypeid");
				image = rs.getString("image");
				// 데이터가 들어가 있는 행은 건너뜀
				if (image != null)
					continue;

				System.out.println(contentid + " " + contenttypeid);
				if (myparsing.parsing(contenttypeid, contentid) == 1) {
					pstmt = conn.prepareStatement(
							"update apidb set image=(?), overview=(?) where contentid=(?) and contenttypeid=(?)");
					pstmt.setString(1, myparsing.image);
					pstmt.setString(2, myparsing.overview);
					pstmt.setLong(3, myparsing.mycontentid);
					pstmt.setLong(4, myparsing.mycontenttypeid);
					pstmt.executeUpdate();
				} else
					continue;
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
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
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
