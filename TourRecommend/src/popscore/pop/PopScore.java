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
 * PopScore ���� Ŭ����. ���߼� ���� PS ����� ���� (�� �� )�����ؾ� �ϴ� ��������� �����ϴ� ���, �������� PS�� ����ϴ�
 * ���. static �޼ҵ常�� �����Ѵ�.
 */
public class PopScore {

	/**
	 * PopScore ����� ���� ��������� �����ϴ� �޼ҵ�. ������ ���� �Ѵ޵��� ���� ���並 ������� ��������� �����Ѵ�. 1-1. ���䰡
	 * �ִ� �������鸸���� Ʃ�õ��� ����. 1-2. �������� ������ ���� ��, ���� ��, reviewdate ����. 2. ������ ���並 ���ɴ뺰��
	 * ���� 6�׷�(10��, 20��, 30��, 40��, 50��, 60�� �̻�)���� �з��Ͽ� ���� ��, ���� �� ����. 3. ������ ���並 ��������
	 * ���� 2�׷�(��, ��)���� �з��Ͽ� ���� ��, ���� �� ����.
	 * 
	 * @param String
	 *            date - ���糯¥, �� ������� ������(�ſ� 1��) [yyyy-MM-01]
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
			Date startDate = startDateFormat.parse(date); // String date�� yyyy-MM-01, Date ������ ��ȯ

			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, -1);
			// System.out.println(startDateFormat.format(cal.getTime()));
			String dateMonthAgo = startDateFormat.format(cal.getTime());
			System.out.println(dateMonthAgo);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("MySQL connection!");

			/* ...����� DB ����... */
			pstmt = conn.prepareStatement("USE hyhy");
			pstmt.executeQuery();
			System.out.println("database changed!");

			/* ...�������� Ʃ�� ����, �ش� �� ���� ����... */
			StringBuffer insertQ = new StringBuffer(
					"INSERT INTO hyhy.popscore (contentid, statdate, sumscore, sumreview) "
							+ " SELECT contentid, date_format(reviewdate, '%y-%m-01') m, SUM(score), COUNT(*) "
							+ " FROM review " + " WHERE year(?)=year(reviewdate) AND month(?) = month(reviewdate) "
							+ " GROUP BY contentid, m");
			pstmt = conn.prepareStatement(insertQ.toString());
			pstmt.setDate(1, java.sql.Date.valueOf(dateMonthAgo)); // ������ ������ ����
			pstmt.setDate(2, java.sql.Date.valueOf(dateMonthAgo));
			int rows = pstmt.executeUpdate();
			System.out.println(rows + " rows are inserted!");
			System.out.println("update MonS complete!");

			/* ...���ɴ뺰 ����... */
			StringBuffer ageQ = new StringBuffer("SELECT contentid, sum(score), count(*) " + "FROM review r, user u "
					+ "WHERE year( ? )=year(reviewdate) AND month( ? ) = month(reviewdate) "
					+ "AND r.userid = u.userid AND ? <= u.age AND u.age < ? " + "GROUP BY contentid");
			StringBuffer updateAgeQ = new StringBuffer(
					"UPDATE popscore p SET sums00 = ?, sumr00 = ? WHERE p.contentid = ?");
			for (int i = 1; i <= 6; i++) { // [10�� ~ 60��]
				pstmt = conn.prepareStatement(ageQ.toString());
				pstmt.setDate(1, java.sql.Date.valueOf(dateMonthAgo)); // ������ ������
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
					pstmt.addBatch(); // ������ �Ķ���� ���� ��ġ�� �߰�
					pstmt.clearParameters(); // �Ķ���͸� �Ҵ��ϰ� ������ ������ �Ķ���͸� Ŭ����
				}
				int result[] = pstmt.executeBatch(); // �߰��� ��ġ�� �Ѳ����� ����.
				// for(int r : result) System.out.print(r + " ");
				System.out.println("");
				System.out.println("update AgeScore" + i * 10 + " complete!");
			}

			/* ...���� ����... */
			StringBuffer genderQ = new StringBuffer("SELECT contentid, sum(score), count(*) " + "FROM review r, user u "
					+ "WHERE year( ? )=year(reviewdate) AND month( ? ) = month(reviewdate) "
					+ "AND r.userid = u.userid AND u.gender = ? " + "GROUP BY contentid");
			StringBuffer updateGenderQ = new StringBuffer(
					"UPDATE popscore p SET sums0 = ?, sumr0 = ? WHERE p.contentid = ?");
			for (int i = 1; i <= 2; i++) { // [���� ~ ����]
				pstmt = conn.prepareStatement(genderQ.toString());
				pstmt.setDate(1, java.sql.Date.valueOf(dateMonthAgo)); // ������ ������
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
			// ���� ����� ó���κ�
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
	 * PS�� ����ϴ� �޼ҵ�. ����� i�� ���ɴ�, ����, �湮 �ñ⸦ �м��Ͽ� ���䰡 ������ ��� �������� ���߼������� ����Ѵ�.
	 * 
	 * @param String
	 *            userid - ������ id
	 * @param String
	 *            contentid - ������ id
	 * @param String
	 *            tripDate - ���� �ñ�(��) [yyyy-MM]
	 * 
	 * @return HashMap<Integer, Double> hashPS - ������ID, ���� PS�� ������ hashMap
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

			/* ...����� DB ����... */
			//pstmt = conn.prepareStatement("use hyhy");
			//pstmt.executeQuery();

			/* ...������ ���� ������... */
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
			System.out.println(userid + "�� ���ɴ�:" + userAge + ", ����: " + userGen + ", ����ñ�: " + tripDate);
			System.out.println("getting user info complete!");

			HashMap<Integer, Scores> scores = new HashMap<>(); // contentid : Scores ���� HashMap ����

			/* ...AgeScore �м�... */
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
				// contentid�� ������ ���ϱ�
				if (scores.containsKey(rs.getInt(1))) {
					scores.get(rs.getInt(1)).addAge(TimeWeight.t1(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4));
				} // ������ ����
				else {
					scores.put(rs.getInt(1),
							new Scores(TimeWeight.t1(rs.getDouble(3), rs.getInt(5)), rs.getDouble(4), 0, 0, 0, 0));
				}
				// System.out.print(rs.getInt(1)+"'s AgeInfo: ");
				// scores.get(rs.getInt(1)).printAgeInfo(); System.out.println(",
				// "+rs.getInt(5));
			}
			System.out.println("uploading AgeScore complete!");

			/* ...GenScore �м�... */
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

			/* ...MonScore �м�... */
			// ���� ���� ���纸�� �ڿ� �ִ� ��� true
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
				if (isLastYearStart) { // �۳����
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
				} else { // �ݳ����
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

			/* ...PS ���... */

			/* ...��ȯ�� HashMap ����... */
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
			// ���� ����� ó���κ�
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
