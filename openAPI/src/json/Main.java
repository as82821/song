package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) throws Exception {
		JSONTask jsontask = new JSONTask();
		// API에서 가져온 원본을 String형 변수에 저장
		String source = jsontask.getjson();

		// System.out.println(source);

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
		// item부분 파싱
		JSONObject itemObject = (JSONObject) itemsObject.get("item");

		String addr1 = (String) itemObject.get("addr1");
		String addr2 = (String) itemObject.get("addr2");
		String category1 = (String) itemObject.get("cat1");
		String category2 = (String) itemObject.get("cat2");
		String category3 = (String) itemObject.get("cat3");
		long contentid = (long) itemObject.get("contentid");
		long contenttypeid = (long) itemObject.get("contenttypeid");
		double mapx = (double) itemObject.get("mapx");
		double mapy = (double) itemObject.get("mapy");
		String overview = (String) itemObject.get("overview");
		String title = (String) itemObject.get("title");

		System.out.println("제목: " + title);
		System.out.println("주소: " + addr1 + addr2);
		System.out.println("대분류: " + category1);
		System.out.println("중분류: " + category2);
		System.out.println("소분류: " + category3);
		System.out.println("고유 식별 ID: " + contentid);
		System.out.println("컨텐츠 타입 ID: " + contenttypeid);
		System.out.println("x좌표: " + mapx);
		System.out.println("y좌표: " + mapy);
		System.out.println("오버뷰: " + overview);
	}

}
