package process;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class Total {

	public static void main(String[] args) throws Exception {
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

			// ���Ǹ� ���� ����ڸ� ������(ex. user356)
			// ������ �ϴ� user1�� ����
			String queryuser = "800user";
			String filePath = "C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\";
			String finalPath = filePath + queryuser + ".csv";
			System.out.println(finalPath);

			DataModel dm = new FileDataModel(new File(finalPath));
			//DataModel dm = new FileDataModel(new File("data/intro.csv"));
			ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);
			//ItemSimilarity sim = new EuclideanDistanceSimilarity(dm);
		
			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);

			
			
			
			
			
			
			
			int i = 1;
			for (LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
				long itemId = items.nextLong();
				// ���� ������ id�� �´� n���� ������ ��õ.
				// ��õ ������ ������ mostSimilarItems(itemId, 2) ���� ���ڸ� �ٲٸ� �ȴ�.
				List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId, 3);

				for (RecommendedItem recommendation : recommendations) {
					//System.out.println(recommendation);
					System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());
				}
				i++;
				/*if (i > 10)
					System.exit(i);*/
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

	}

}
