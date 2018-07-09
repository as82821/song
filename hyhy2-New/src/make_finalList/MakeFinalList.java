package make_finalList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import hybridscore.CalTS;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import popscore.pop.PopScore;
import popscore.ranking.Ranking;

public class MakeFinalList {
	public ArrayList<String> imagelist = new ArrayList<>();
	public ArrayList<String> titlelist = new ArrayList<>();
	public ArrayList<Integer> placelist = new ArrayList<>();
	public ArrayList<String> addrlist = new ArrayList<>();

	public void makelist() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionProvider.getConnection();
			CalTS ts = new CalTS();

			// PS 계산, 결과셋 확인
			HashMap<Integer, Double> ps = PopScore.calcPS(conn, "user1", "2017-01");
			Iterator<Entry<Integer, Double>> hashPS = ps.entrySet().iterator();
			System.out.println("메소드 호출 종료");

			long start = System.currentTimeMillis();
			long end = 0;
			long time = 0;
			long minute;
			// RS 계산, 최종 결과셋 확인
			Ranking rank = new Ranking(ts.calTS(conn), ps);
			System.out.println("생성자 종료");
			end = System.currentTimeMillis();
			time = end - start;
			System.out.println("랭킹 걸린 시간 " + time + "ms");
			
			placelist = rank.getTopK(8);
		
			String title = "";
			String image = "";
			String addr="";

			for (int i = 0; i < placelist.size(); i++) {
				pstmt = conn.prepareStatement("select title,image,addr from apidb where contentid=(?)");
				pstmt.setInt(1, placelist.get(i));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					title = rs.getString("title");
					image = rs.getString("image");
					addr = rs.getString("addr");
					titlelist.add(title);
					imagelist.add(image);
					addrlist.add(addr);
				}
			}
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			System.out.println(ce.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}

}
