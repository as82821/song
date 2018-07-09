package simuser;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/*
 * 유저간 유사도를 구하는 클래스
 *  usersigs파일에 저장된 데이터를 기반으로 유저간 유사도를 구해 DB에 저장한다.
 *  유사도 값이 0.8 이상인 유저를 저장한다.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		DataModel dm = new FileDataModel(new File("data/WEB_usersigs.csv"));
		// UserSimilarity sim = new EuclideanDistanceSimilarity(dm);
		UserSimilarity sim = new PearsonCorrelationSimilarity(dm);

		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String userid1 = "";
		String userid2 = "";
		int totalcnt = 0;


		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;

			for (int i = 1; i <= 10; i++) {
				userid1 = "user" + i;
				for (int j = 1; j <= 3000; j++) {
					if (i == j)
						continue;
					totalcnt = 0;
					userid2 = "user" + j;
					pstmt = conn.prepareStatement(
							"select score,visit,totalvisit,contentid,count(*) as jjb from (select * from significantscore where userid=(?) or userid=(?)) as A group by contentid having count(*)>1");
					pstmt.setString(1, userid1);
					pstmt.setString(2, userid2);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						totalcnt++;
					}

					if (totalcnt > 2) {
						double value = sim.userSimilarity(i, j);
						if (Double.isNaN(value))
							continue;
						if (value >= 0.8 & value<=0.96) {
							System.out.println("유저" + i + "와 " + j + "사이의 유사도 " + value);
							pstmt2=conn.prepareStatement("insert into simuser values (?,?,?)");
							pstmt2.setString(1, userid1);
							pstmt2.setString(2, userid2);
							pstmt2.setDouble(3, value);
							pstmt2.executeUpdate();
						}
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
