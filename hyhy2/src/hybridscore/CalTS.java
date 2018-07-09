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

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

/*
 * ���̺긮�� ���� ���͸� ���� TS�� ����ϴ� Ŭ����
 * ���Ǹ� ��û�� ���� (?)�� (?)�� ������ �������� sigs�� ����Ǿ� �ִ� user(?).csv������ �о��
 * �о�� ���Ͽ��� �������� ���絵�� ����ϰ� ���� ���絵�� �̿��� ���Ǹ� ��û�� ������ �湮���� ���� �������� ���� ���� ����
 * HashMap ���
 * ������(Key):��������(Value) �� �������� ����
 */
public class CalTS implements User {

	public HashMap<Integer, Double> calTS(Connection conn) throws Exception {
		String userfile = "C:/userdata/" + queryuser + ".csv";

		DataModel dm = new FileDataModel(new File(userfile));
		ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);
		// ItemSimilarity sim = new EuclideanDistanceSimilarity(dm);
		DecimalFormat df = new DecimalFormat("#.####");

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

		HashMap<Integer, Double> queryserlist = new HashMap<Integer, Double>();
		HashMap<Integer, Double> otherlist = new HashMap<Integer, Double>();

		try {
			// ���Ǹ� ��û�� ����ڰ� �湮�� ������ ����Ʈ
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

			// ���Ǹ� ��û�� ����ڰ� �湮���� ���� ������ ����Ʈ ����
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

			// ���Ǹ� ��û�� ����ڰ� �湮���� ���� ������ ���� ���� ���
			Set<Integer> keySet2 = otherlist.keySet();
			Iterator<Integer> keyIterator2 = keySet2.iterator();
			while (keyIterator2.hasNext()) {
				// ���絵 ��
				sumsim = 0.0;
				// ���絵*���� ���� ������ ��
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
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return otherlist;
	}
}