package hybridscore;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;


public class Main implements User {

	public static void main(String[] args) throws Exception{
		//String queryuser = "user1";
		String userfile = "data\\" + queryuser + ".csv";

		DataModel dm = new FileDataModel(new File(userfile));
		ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);
		// ItemSimilarity sim = new EuclideanDistanceSimilarity(dm);
		DecimalFormat df = new DecimalFormat("#.####");

		//Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://aws-song.cdzl90330te3.us-east-2.rds.amazonaws.com/TourAPI?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "HYsong";
		String pass = "1q2w3e4r";
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		int contentid;
		double score = 0;
		int visit = 0;
		int totalvisit = 0;
		double sigs;
		String ssigs;
		double tmpvisit;
		double sumsim = 0;
		double sumRsim = 0;

		Map<Integer, Double> queryserlist = new HashMap<Integer, Double>();
		Map<Integer, Double> otherlist = new HashMap<Integer, Double>();

		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;

			// 질의를 요청한 사용자가 방문한 관광지 리스트
			pstmt = conn.prepareStatement("select * from significantscore where userid=(?)");
			pstmt.setString(1, queryuser);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				contentid = rs.getInt("contentid");
				score = rs.getDouble("score");
				visit = rs.getInt("visit");
				totalvisit = rs.getInt("totalvisit");
				tmpvisit = (double) visit / totalvisit;
				sigs = (0.5 * (score / 5.0) + 0.5 * (tmpvisit)) * 10;
				ssigs = df.format(sigs);
				queryserlist.put(contentid, Double.parseDouble(ssigs));
			}

			// 질의를 요청한 사용자가 방문하지 않은 관광지 리스트 생성
			pstmt2 = conn.prepareStatement("select * from simuser where userid=(?)");
			pstmt2.setString(1, queryuser);
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				String simuserid = rs2.getString("simuserid");
				pstmt = conn.prepareStatement("select contentid from significantscore where userid=(?)");
				pstmt.setString(1, simuserid);
				rs3 = pstmt.executeQuery();
				while (rs3.next()) {
					int id = rs3.getInt("contentid");
					if (!queryserlist.containsKey(id))
						otherlist.put(id, 0.0);
				}
			}

			// 질의를 요청한 사용자가 방문하지 않은 관광지 예측 점수 계산
			Set<Integer> keySet2 = otherlist.keySet();
			Iterator<Integer> keyIterator2 = keySet2.iterator();
			while (keyIterator2.hasNext()) {
				// 유사도 합
				sumsim = 0.0;
				// 유사도*쿼리 유저 점수의 합
				sumRsim = 0;
				Integer otherkey = keyIterator2.next();

				Set<Integer> keySet = queryserlist.keySet();
				Iterator<Integer> keyIterator = keySet.iterator();
				while (keyIterator.hasNext()) {
					Integer queryuserkey = keyIterator.next();
					double tmp = sim.itemSimilarity(otherkey, queryuserkey);

					if (Double.isNaN(tmp))
						continue;

					sumsim += tmp;
					sumRsim += tmp * queryserlist.get(queryuserkey);
					// System.out.println(key+" "+value);
				}

				double finalvalue = sumRsim / sumsim;
				otherlist.put(otherkey, finalvalue);
				double finalscore = otherlist.get(otherkey);
				// System.out.println(otherkey+" "+otherlist.get(otherkey));
				if (!Double.isNaN(finalscore))
					System.out.println(otherkey + " " + finalscore);
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
