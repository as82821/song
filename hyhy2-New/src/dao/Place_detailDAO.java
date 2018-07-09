package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;

public class Place_detailDAO {
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
	
	private static Place_detailDAO detaildao = new Place_detailDAO();

	public static Place_detailDAO getInstance() {
		return detaildao;
	}

	private Place_detailDAO() {
	}
	
	public void getinfo(int contentid) {
		try {
			conn = ConnectionProvider.getConnection();

				pstmt = conn.prepareStatement("select * from apidb where contentid=(?)");
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
		}
	}

}
