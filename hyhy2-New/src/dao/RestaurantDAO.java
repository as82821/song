package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class RestaurantDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String title = "";
	public String image = "";
	public String cat3 = "";
	public String addr = "";
	public int contentid;
	public ArrayList<Integer> contentidlist = new ArrayList<>();

	private static RestaurantDAO rdao = new RestaurantDAO();

	public static RestaurantDAO getInstance() {
		return rdao;
	}

	private RestaurantDAO() {
	}

	public void makerandomlist(String cat3) {
		//System.out.println(cat3+"»£√‚");
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select contentid from apidb_restaurant where cat3=(?) order by rand() limit 4");
			pstmt.setString(1, cat3);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				contentidlist.add(rs.getInt("contentid"));
			}
			
		/*	for(int i=0;i<contentidlist.size();i++) {
				System.out.println(contentidlist.get(i));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public void getinfo(int contentid) {
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from apidb_restaurant where contentid=(?)");
			pstmt.setInt(1, contentid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				title = rs.getString("title");
				image = rs.getString("image");
				cat3 = rs.getString("cat3");
				addr = rs.getString("addr");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
	}

}
