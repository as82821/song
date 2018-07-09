package tourAPI_restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main {

	public static void main(String[] args) throws Exception {
		FoodJSONTask json = new FoodJSONTask();
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/tourdb?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";
		int page=81;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(jdbcDriver, user, pass);
			pstmt = conn.prepareStatement("insert into apidb_restaurant values (?,?,?,?,?,?,?,?,?,?,?)");

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
					String image=(String)arrobj.get("image");
					String overview=(String)arrobj.get("overview");
					// 위도
					Object lat = null;
					String slat;
					// 경도
					Object lng = null;
					String slng;
					
					if (arrobj.get("mapy") instanceof Double) {
						lat = (Double) arrobj.get("mapy");
					}
					if (arrobj.get("mapy") instanceof String) {
						lat = (String) arrobj.get("mapy");
					}

					if (arrobj.get("mapx") instanceof Double) {
						lng = (Double) arrobj.get("mapx");
					}
					if (arrobj.get("mapx") instanceof String) {
						lng = (String) arrobj.get("mapx");
					}

					slat = "" + lat;
					slng = "" + lng;

					pstmt.setString(1, title);
					pstmt.setString(2, addr);
					pstmt.setString(3, category1);
					pstmt.setString(4, category2);
					pstmt.setString(5, category3);
					pstmt.setLong(6, contentid);
					pstmt.setLong(7, contenttypeid);
					pstmt.setString(8, image);
					pstmt.setString(9, overview);
					pstmt.setString(10, slat);
					pstmt.setString(11, slng);
					pstmt.executeUpdate();
				}
			}
			System.out.println("음식점 삽입 완료");
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
