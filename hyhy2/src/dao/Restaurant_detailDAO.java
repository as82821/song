package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class Restaurant_detailDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String title = "";
	public String image = "";
	public String lat="";
	public String lng="";
	public String cat1="";
	public String cat2="";
	public String cat3="";
	public String addr="";
	public String overview="";
	
	private static Restaurant_detailDAO detaildao = new Restaurant_detailDAO();

	public static Restaurant_detailDAO getInstance() {
		return detaildao;
	}

	private Restaurant_detailDAO() {
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
					lat=rs.getString("lat");
					lng=rs.getString("lng");
					cat1=rs.getString("cat1");
					cat2=rs.getString("cat2");
					cat3=rs.getString("cat3");
					addr=rs.getString("addr");
					overview=rs.getString("overview");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
