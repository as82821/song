package jsonarray;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) throws Exception {
		NatureJSONTask json = new NatureJSONTask();
		
		for (int j = 1; j <= 6; j++) {
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

				System.out.println("제목: " + title);
				System.out.println("주소: " + addr);
				System.out.println("대분류: " + category1);
				System.out.println("중분류: " + category2);
				System.out.println("소분류: " + category3);
				System.out.println("고유 식별 ID: " + contentid);
				System.out.println("컨텐츠 타입 ID: " + contenttypeid);
				System.out.println();
			}
		}	

	}

}
