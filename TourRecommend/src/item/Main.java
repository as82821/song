package item;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import hybridscore.User;

/*
 * ���� sigs�� �̿��Ͽ� �������� ���絵�� ����Ѵ�.
 * �������� ���絵�� ����Ͽ� user(?)�̶�� csv������ �����Ѵ�.
 */

public class Main  implements User {

	public static void main(String[] args) throws Exception {
		//String queryuser = "user2";
		String userfile = "data\\" + queryuser + ".csv";
		BufferedReader br = new BufferedReader(new FileReader("data\\WEB_usersigs.csv"));
		BufferedWriter bw = new BufferedWriter(new FileWriter(userfile));
		DecimalFormat df = new DecimalFormat("#.####");

		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String queryuseridx;

		int contentid;
		double score = 0;
		int visit = 0;
		int totalvisit = 0;
		double sigs;
		String ssigs;
		double tmpvisit;
		String data = "";

		List<String> simuserlist = new ArrayList<String>();

		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;

			// ���Ǹ� ��û�� ����ڿ� simuser�� ����� ������ ����ڵ��� ���䰡 ��� ������ �����.

			// ���Ǹ� ��û�� �����,����Ʈid,sigs ���� ����
			String line;
			queryuseridx = queryuser.substring(4);
			//System.out.println(queryuseridx);
			// System.out.println(queryuser);
			while ((line = br.readLine()) != null) {
				//System.out.println("����?");
				String value = line;
				user = value.substring(0, value.indexOf(","));
				if (queryuseridx.equals(user))
					bw.write(value + "\n");
				// System.out.println(user);
				//bw.write(value + "\n");
			}

			// user1�� ������ ����ڸ� simuserlist�� ����
			pstmt = conn.prepareStatement("select simuserid from simuser where userid=(?)");
			pstmt.setString(1, queryuser);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String tmp = rs.getString("simuserid");
				simuserlist.add(tmp);
			}

			// simuserlist�� ����ִ� ����ڵ��� ���������� ���Ͽ� ����
			for (int i = 0; i < simuserlist.size(); i++) {
				pstmt = conn.prepareStatement("select * from significantscore where userid=(?)");
				String simuser = simuserlist.get(i);
				System.out.println(simuser);
				pstmt.setString(1, simuser);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					contentid = rs.getInt("contentid");
					score = rs.getDouble("score");
					visit = rs.getInt("visit");
					totalvisit = rs.getInt("totalvisit");
					tmpvisit = (double) visit / totalvisit;
					sigs = (0.5 * (score / 5.0) + 0.5 * (tmpvisit)) * 10;
					ssigs = df.format(sigs);
					data = "" + simuser.substring(4) + "," + contentid + "," + ssigs;
					// System.out.println(data);
					bw.write(data + "\n");
				}
			}
			// ���� ����� ��

			// ���� ������ �̿��� �����۰� ���絵�� ����Ѵ�

			end = System.currentTimeMillis();
			time = end - start;
			System.out.println("�ɸ� �ð� " + time + "ms");
			minute = ((time / (1000 * 60)) % 60);
			System.out.println(minute + "��");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			br.close();
			bw.close();

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
