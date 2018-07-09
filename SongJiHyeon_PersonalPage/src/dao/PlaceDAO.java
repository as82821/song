package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class PlaceDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String title = "";
	public String image = "";
	public String cat3 = "";
	public String addr = "";
	public int contentid;
	public ArrayList<Integer> contentidlist = new ArrayList<>();

	private static PlaceDAO rdao = new PlaceDAO();

	public static PlaceDAO getInstance() {
		return rdao;
	}

	private PlaceDAO() {
	}

	public void makerandomlist(String cat2) {
		//System.out.println(cat3+"호출");
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select contentid from apidb where cat2=(?) order by rand() limit 4");
			pstmt.setString(1, cat2);
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
			pstmt = conn.prepareStatement("select * from apidb where contentid=(?)");
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
	
	/*
	 * 관광지 검색을 처리하는 메소드
	 * 
	 * @param mytitle : 사용자가 입력한 관광지명
	 * 
	 * @return 1 : 검색 성공, -1 : 존재하지 않는 관광지
	 */
	public int search(String mytitle) {
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from apidb where title=(?)");
			pstmt.setString(1, mytitle);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				contentid=rs.getInt("contentid");
				return 1;
			}else 
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return 0;
	}
}
