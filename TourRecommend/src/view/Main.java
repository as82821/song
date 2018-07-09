package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		ResultSet rs2 = null;
		int contentid;

		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);
			/*
			 * pstmt = conn.
			 * prepareStatement("select contentid from significantscore where userid=(?)");
			 * pstmt.setString(1, "user1"); rs = pstmt.executeQuery(); while (rs.next()) {
			 * contentid = rs.getInt("contentid"); pstmt =
			 * conn.prepareStatement("select * from apidb where contentid=(?)");
			 * pstmt.setInt(1, contentid); rs2 = pstmt.executeQuery(); while (rs2.next()) {
			 * System.out.println(rs2.getString("title") + " " + rs2.getString("cat3") + " "
			 * + rs2.getString("contenttypeid") + " " + rs2.getInt("contentid")); } }
			 */

			BufferedReader br = new BufferedReader(new FileReader("data\\2TS.csv"));

			String line;
			while ((line = br.readLine()) != null) {
				String value = line;
				String id = value.substring(0, value.indexOf(","));
				String score = value.substring(value.indexOf(",") + 1);
				// System.out.println(id);
				pstmt = conn.prepareStatement("select * from apidb where contentid=(?)");
				pstmt.setInt(1, Integer.parseInt(id));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					String type = rs.getString("contenttypeid");
					if (Integer.parseInt(type) == 12)
						type = "°ü±¤Áö";
					else if (Integer.parseInt(type) == 14)
						type = "¹®È­";
					else if (Integer.parseInt(type) == 38)
						type = "¼îÇÎ";
					System.out.println(rs.getString("title") + "," + score + "," + type);
				}
			}
			br.close();

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
