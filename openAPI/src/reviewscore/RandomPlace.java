package reviewscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class RandomPlace {
	Random ran = new Random();
	int placeid;

	public int getcontentid() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			for (;;) {
				placeid = ran.nextInt(2535347);
				// ���� ���������� 125432������ ����� db�� �������� �ʴ� ������
				if (placeid < 125452)
					continue;
				// 125432~2535347������ contentid�� �����ٸ�
				else {
					// ���� placeid�� ������ �־� ��ȸ�Ѵ�
					pstmt = conn.prepareStatement("select * from apidb where contentid=(?)");
					pstmt.setInt(1, placeid);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						int tmp = rs.getInt("contenttypeid");
						if (tmp == 12 || tmp == 14 || tmp == 28 || tmp == 38 || tmp == 15)
							return placeid;
						else
							continue;
					}
				}
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
		return placeid;
	}
}
