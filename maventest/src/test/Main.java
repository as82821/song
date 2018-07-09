package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?" +
			// "useUnicode=true&characterEncoding=utf8";
			String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
					+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
			String user = "root";
			String pass = "wlgus132!!";
			String query = "select * from apidb";

			conn = DriverManager.getConnection(jdbcDriver, user, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				System.out.println("제목: " + rs.getString("title"));
				System.out.println("주소: " + rs.getString("addr"));
				System.out.println("대분류: " + rs.getString("cat1"));
				System.out.println("중분류: " + rs.getString("cat2"));
				System.out.println("소분류: " + rs.getString("cat3"));
				System.out.println("고유 식별 ID: " + rs.getString("contentid"));
				System.out.println("컨텐츠 타입 ID: " + rs.getString("contenttypeid"));
				System.out.println();
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
			if (stmt != null) {
				try {
					stmt.close();
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
