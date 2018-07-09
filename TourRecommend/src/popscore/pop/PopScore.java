package popscore.pop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * PopScore 관련 클래스. 대중성 점수 PS 계산을 위해 (매 달 )갱신해야 하는 통계점수를 갱신하는 기능, 관광지별 PS를 계산하는
 * 기능. static 메소드만을 포함한다.
 */
public class PopScore {

	/**
	 * PopScore 계산을 위한 통계정보를 갱신하는 메소드. 갱신일 이전 한달동안 남긴 리뷰를 기반으로 통계정보를 갱신한다. 1-1. 리뷰가
	 * 있는 관광지들만으로 튜플들을 생성. 1-2. 관광지에 남겨진 평점 합, 평점 수, reviewdate 갱신. 2. 남겨진 리뷰를 연령대별로
	 * 나눈 6그룹(10대, 20대, 30대, 40대, 50대, 60대 이상)으로 분류하여 평점 합, 평점 수 갱신. 3. 남겨진 리뷰를 성별별로
	 * 나눈 2그룹(남, 녀)으로 분류하여 평점 합, 평점 수 갱신.
	 * 
	 * @param String
	 *            date - 현재날짜, 즉 통계정보 갱신일(매월 1일) [yyyy-MM-01]
	 */
	public static void update(String date) {

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/test?allowMultiQueries=true&autoReconnect=true&useSSL=false";
		final String USER_NAME = "root";
		final String PASSWORD = "1234";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-01");

		try {
			Date startDate = startDateFormat.parse(date); // String date를 yyyy-MM-01, Date 형으로 변환

			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, -1);
			// System.out.println(startDateFormat.format(cal.getTime()));
			String dateMonthAgo = startDateFormat.format(cal.getTime());
			System.out.println(dateMonthAgo);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("MySQL connection!");

			/* ...사용할 DB 선택... */
			pstmt = conn.prepareStatement("USE hyhy");
			pstmt.executeQuery();
			System.out.println("database changed!");

			/* ...관광지별 튜플 생성, 해당 월 총합 갱신... */
			StringBuffer insertQ = new StringBuffer(
					"INSERT INTO hyhy.popscore (contentid, statdate, sumscore, sumreview) "
							+ " SELECT contentid, date_format(reviewdate, '%y-%m-01') m, SUM(score), COUNT(*) "
							+ " FROM review " + " WHERE year(?)=year(reviewdate) AND month(?) = month(reviewdate) "
							+ " GROUP BY contentid, m");
			pstmt = conn.prepareStatement(insertQ.toString());
			pstmt.setDate(1, java.sql.Date.valueOf(dateMonthAgo)); // 저번달 데이터 갱신
			pstmt.setDate(2, java.sql.Date.valueOf(dateMonthAgo));
			int rows = pstmt.executeUpdate();
			System.out.println(rows + " rows are inserted!");
			System.out.println("update MonS complete!");

			/* ...연령대별 갱신... */
			StringBuffer ageQ = new StringBuffer("SELECT contentid, sum(score), count(*) " + "FROM review r, user u "
					+ "WHERE year( ? )=year(reviewdate) AND month( ? ) = month(reviewdate) "
					+ "AND r.userid = u.userid AND ? <= u.age AND u.age < ? " + "GROUP BY contentid");
			StringBuffer updateAgeQ = new StringBuffer(
					"UPDATE popscore p SET sums00 = ?, sumr00 = ? WHERE p.contentid = ?");
			for (int i = 1; i <= 6; i++) { // [10대 ~ 60대]
				pstmt = conn.prepareStatement(ageQ.toString());
				pstmt.setDate(1, java.sql.Date.valueOf(dateMonthAgo)); // 저번달 데이터
				pstmt.setDate(2, java.sql.Date.valueOf(dateMonthAgo));
				if (i == 1) {
					pstmt.setInt(3, 0);
					pstmt.setInt(4, 20);
				} else if (i == 6) {
					pstmt.setInt(3, 60);
					pstmt.setInt(4, 100);
				} else {
					pstmt.setInt(3, i * 10);
					pstmt.setInt(4, (i + 1) * 10);
				}
				rs = pstmt.executeQuery();

				updateAgeQ.replace(26, 28, String.valueOf(i * 10));
				updateAgeQ.replace(38, 40, String.valueOf(i * 10));
				pstmt = conn.prepareStatement(updateAgeQ.toString());
				double sumScore, sumReview;
				int contentid;
				while (rs.next()) {
					sumScore = rs.getDouble(2);
					sumReview = rs.getDouble(3);
					contentid = rs.getInt(1);
					// System.out.printf("%.1f %.1f %d\n",sumScore, sumReview, contentid);
					pstmt.setDouble(1, sumScore); // sums value
					pstmt.setDouble(2, sumReview); // sumr value
					pstmt.setInt(3, contentid); // contentid
					pstmt.addBatch(); // 쿼리와 파라미터 셋을 배치에 추가
					pstmt.clearParameters(); // 파라미터를 할당하고 실행한 다음에 파라미터를 클리어
				}
				int result[] = pstmt.executeBatch(); // 추가된 배치를 한꺼번에 실행.
				// for(int r : result) System.out.print(r + " ");
				System.out.println("");
				System.out.println("update AgeScore" + i * 10 + " complete!");
			}

			/* ...성별 갱신... */
			StringBuffer genderQ = new StringBuffer("SELECT contentid, sum(score), count(*) " + "FROM review r, user u "
					+ "WHERE year( ? )=year(reviewdate) AND month( ? ) = month(reviewdate) "
					+ "AND r.userid = u.userid AND u.gender = ? " + "GROUP BY contentid");
			StringBuffer updateGenderQ = new StringBuffer(
					"UPDATE popscore p SET sums0 = ?, sumr0 = ? WHERE p.contentid = ?");
			for (int i = 1; i <= 2; i++) { // [남자 ~ 여자]
				pstmt = conn.prepareStatement(genderQ.toString());
				pstmt.setDate(1, java.sql.Date.valueOf(dateMonthAgo)); // 저번달 데이터
				pstmt.setDate(2, java.sql.Date.valueOf(dateMonthAgo));
				if (i == 1) {
					pstmt.setString(3, "M");
				} else {
					pstmt.setString(3, "F");
				}
				rs = pstmt.executeQuery();

				if (i == 1) {
					updateGenderQ.replace(26, 27, "M");
					updateGenderQ.replace(37, 38, "M");
				} else {
					updateGenderQ.replace(26, 27, "F");
					updateGenderQ.replace(37, 38, "F");
				}
				pstmt = conn.prepareStatement(updateGenderQ.toString());
				double sumScore, sumReview;
				int contentid;
				while (rs.next()) {
					sumScore = rs.getDouble(2);
					sumReview = rs.getDouble(3);
					contentid = rs.getInt(1);
					// System.out.printf("%.1f %.1f %d\n",sumScore, sumReview, contentid);
					pstmt.setDouble(1, sumScore); // sums value
					pstmt.setDouble(2, sumReview); // sumr value
					pstmt.setInt(3, contentid); // contentid
					pstmt.addBatch();
					pstmt.clearParameters();
				}
				int result[] = pstmt.executeBatch();
				/*
				 * for(int r : result) System.out.print(r + " "); System.out.println("");
				 */
				if (i == 1) {
					System.out.println("update GenScoreM complete!");
				} else {
					System.out.println("update GenScoreF complete!");
				}
			}

			pstmt.close();
			System.out.println("pstmt closed...");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB Driver Error!");
		} catch (Exception e) {
			// 예외 방생시 처리부분
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("DB PreparedStatement Error...");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("DB Connection Error...");
			}
		}
	}

	/**
	 * PS를 계산하는 메소드. 사용자 i의 연령대, 성별, 방문 시기를 분석하여 리뷰가 남겨진 모든 관광지의 대중성점수를 계산한다.
	 * 
	 * @param String
	 *            userid - 여행자 id
	 * @param String
	 *            contentid - 관광지 id
	 * @param String
	 *            tripDate - 여행 시기(월) [yyyy-MM]
	 * 
	 * @return HashMap<Integer, Double> hashPS - 관광지ID, 계산된 PS로 구성된 hashMap
	 */
	public static HashMap<Integer, Double> calcPS(String userid, String tripDate) {

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		final String USER_NAME = "root";
		final String PASSWORD = "wlgus132!!";

		int userAge = 0;
		String userGen = null;
		double coefAge = 1 / 3f;
		double coefGen = 1 / 3f;
		double coefMon = 1 / 3f;
		HashMap<Integer, Double> hashPS = new HashMap<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			tripDate = tripDate + "-01";

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("MySQL connection!");

			/* ...사용할 DB 선택... */
			//pstmt = conn.prepareStatement("use hyhy");
			//pstmt.executeQuery();

			/* ...여행자 정보 가져옴... */
			StringBuffer userQ = new StringBuffer("SELECT age, gender from user where userid = ?");
			pstmt = conn.prepareStatement(userQ.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			rs.next();
			userAge = rs.getInt("age");
			userGen = rs.getString("gender");
			switch (userAge / 10) {
			case 0:
			case 1:
				userAge = 10;
				break;
			case 2:
				userAge = 20;
				break;
			case 3:
				userAge = 30;
				break;
			case 4:
				userAge = 40;
				break;
			case 5:
				userAge = 50;
				break;
			default:
				userAge = 60;
				break;
			}
			System.out.println(userid + "의 연령대:" + userAge + ", 성별: " + userGen + ", 여행시기: " + tripDate);
			System.out.println("getting user info complete!");

			HashMap<Integer, Scores> scores = new HashMap<>(); // contentid : Scores 쌍의 HashMap 생성

			/* ...AgeScore 분석... */
			StringBuffer ageDataQ = new StringBuffer(
					"SELECT contentid, statdate, sums00, sumr00, period_diff(date_format(date(now()), '%y%m'), date_format(statdate, '%y%m')) k "
							+ "FROM popscore " + "WHERE sums00 IS NOT NULL " + "ORDER BY k ASC, contentid DESC");
			ageDataQ.replace(32, 34, String.valueOf(userAge));
			ageDataQ.replace(40, 42, String.valueOf(userAge));
			ageDataQ.replace(147, 149, String.valueOf(userAge));
			// System.out.println(ageDataQ);
			pstmt = conn.prepareStatement(ageDataQ.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// contentid가 있으면 더하기
				if (scores.containsKey(rs.getInt(1))) {
					scores.get(rs.getInt(1)).addAge(TimeWeight.t1(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4));
				} // 없으면 생성
				else {
					scores.put(rs.getInt(1),
							new Scores(TimeWeight.t1(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4), 0, 0, 0, 0));
				}
				// System.out.print(rs.getInt(1)+"'s AgeInfo: ");
				// scores.get(rs.getInt(1)).printAgeInfo(); System.out.println(",
				// "+rs.getInt(5));
			}
			System.out.println("uploading AgeScore complete!");

			/* ...GenScore 분석... */
			StringBuffer genDataQ = new StringBuffer(
					"SELECT contentid, statdate, sums0, sumr0, period_diff(date_format(date(now()), '%y%m'), date_format(statdate, '%y%m')) k "
							+ "FROM popscore " + "WHERE sums0 IS NOT NULL " + "ORDER BY k ASC, contentid DESC");
			if (userGen.equals("M")) {
				genDataQ.replace(32, 33, "M");
				genDataQ.replace(39, 40, "M");
				genDataQ.replace(145, 146, "M");
			} else {

				genDataQ.replace(32, 33, "F");
				genDataQ.replace(39, 40, "F");
				genDataQ.replace(145, 146, "F");
			}
			// System.out.println(genDataQ);
			pstmt = conn.prepareStatement(genDataQ.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (scores.containsKey(rs.getInt(1))) {
					scores.get(rs.getInt(1)).addGen(TimeWeight.t1(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4));
				} else {
					scores.put(rs.getInt(1),
							new Scores(0, 0, TimeWeight.t1(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4), 0, 0));
				}
				// System.out.print(rs.getInt(1)+"'s GenInfo: ");
				// scores.get(rs.getInt(1)).printGenInfo(); System.out.println(",
				// "+rs.getInt(5));
			}
			System.out.println("uploading GenScore complete!");

			/* ...MonScore 분석... */
			// 여행 월이 현재보다 뒤에 있는 경우 true
			boolean isLastYearStart = (Calendar.getInstance().get(Calendar.MONTH) < Integer
					.valueOf(tripDate.substring(5, 7)));
			StringBuffer monDataQ = new StringBuffer(
					"SELECT contentid, statdate, sums0, sumr0, period_diff(date_format(statdate, '%y%m'), date_format( ? , '%y%m')) k "
							+ "FROM popscore " + "WHERE sums0 IS NOT NULL AND month( ? ) = month(statdate) "
							+ "ORDER BY k ASC, contentid DESC");
			if (userGen.equals("M")) {
				monDataQ.replace(32, 33, "M");
				monDataQ.replace(39, 40, "M");
				monDataQ.replace(137, 138, "M");
			} else {

				monDataQ.replace(32, 33, "F");
				monDataQ.replace(39, 40, "F");
				monDataQ.replace(137, 138, "F");
			}
			// System.out.println(monDataQ);
			pstmt = conn.prepareStatement(monDataQ.toString());
			pstmt.setString(1, tripDate);
			pstmt.setString(2, tripDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (isLastYearStart) { // 작년기준
					if (scores.containsKey(rs.getInt(1))) {
						scores.get(rs.getInt(1)).addMon(TimeWeight.t2(rs.getDouble(3), rs.getInt(5) / 12 - 1),
								rs.getDouble(4));
					} else {
						scores.put(rs.getInt(1),
								new Scores(0, 0, TimeWeight.t2(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4), 0, 0));
					}
					// System.out.print(rs.getInt(1)+"'s GenInfo: ");
					// scores.get(rs.getInt(1)).printGenInfo(); System.out.println(",
					// "+rs.getInt(5));
				} else { // 금년기준
					if (scores.containsKey(rs.getInt(1))) {
						scores.get(rs.getInt(1)).addMon(TimeWeight.t2(rs.getDouble(3), rs.getInt(5) / 12),
								rs.getDouble(4));
					} else {
						scores.put(rs.getInt(1),
								new Scores(0, 0, TimeWeight.t2(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4), 0, 0));
					}
					// System.out.print(rs.getInt(1)+"'s MonInfo: ");
					// scores.get(rs.getInt(1)).printGenInfo(); System.out.println(",
					// "+rs.getInt(5));
				}
			}
			System.out.println("uploading MonScore complete!");

			/* ...PS 계산... */

			/* ...반환할 HashMap 생성... */
			Iterator<Scores> iterator = scores.values().iterator();
			Iterator<Integer> scoreKeys = scores.keySet().iterator();
			while (iterator.hasNext()) {
				Scores temp = iterator.next();
				// System.out.println(temp.ageScore/temp.ageVisit+"/
				// "+temp.genScore/temp.genVisit+"/ "+temp.monScore/temp.monVisit);
				// System.out.println(temp.calcPS(coefAge, coefGen, coefMon));
				hashPS.put(scoreKeys.next(), temp.calcPS(coefAge, coefGen, coefMon));
			}
			System.out.println("calculating PS complete!");
			pstmt.close();
			System.out.println("pstmt closed...");
			conn.close();
			System.out.println("conn closed...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB Driver Error!");
		} catch (Exception e) {
			// 예외 방생시 처리부분
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("DB PreparedStatement Error...");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("DB Connection Error...");
			}
		}
		return hashPS;
	}

}
