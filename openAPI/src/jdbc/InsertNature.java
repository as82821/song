package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tourAPI.jsontask.NatureJSONTask;

public class InsertNature extends JDBCInfo {
	NatureJSONTask json = new NatureJSONTask();
	int page=6;
	
	public void insert() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);
			pstmt = conn.prepareStatement("insert into apidb values (?,?,?,?,?,?,?)");

			for (int j = 1; j <= page; j++) {
				String source = json.getjson(j);
				// 가져온 원본 데이터를 파싱
				JSONParser parser = new JSONParser();
				// 원본 데이터 JSONObject에 넣음
				JSONObject object = (JSONObject) parser.parse(source);
				// response부분 파싱
				JSONObject responseObject = (JSONObject) object.get("response");
				// body부분 파싱
				JSONObject bodyObejct = (JSONObject) responseObject.get("body");
				// items부분 파싱
				JSONObject itemsObject = (JSONObject) bodyObejct.get("items");
				// item부분 JSONArray형에 넣음
				JSONArray arr = (JSONArray) itemsObject.get("item");

				for (int i = 0; i < arr.size(); i++) {
					JSONObject arrobj = (JSONObject) arr.get(i);

					String title = (String) arrobj.get("title");
					String addr = (String) arrobj.get("addr1");
					String category1 = (String) arrobj.get("cat1");
					String category2 = (String) arrobj.get("cat2");
					String category3 = (String) arrobj.get("cat3");
					long contentid = (long) arrobj.get("contentid");
					long contenttypeid = (long) arrobj.get("contenttypeid");

					pstmt.setString(1, title);
					pstmt.setString(2, addr);
					pstmt.setString(3, category1);
					pstmt.setString(4, category2);
					pstmt.setString(5, category3);
					pstmt.setLong(6, contentid);
					pstmt.setLong(7, contenttypeid);
					pstmt.executeUpdate();
				}
			}
			System.out.println("자연 삽입 완료");
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
