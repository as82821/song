package tourAPI_lat_lng;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parsing {

	// 위도
	public Object lat;
	public String slat;
	// 경도
	public Object lng;
	public String slng;

	/* 데이터가 있으면 1, 없으면 0 */
	public int parsing(int contenttypeid, int contentid) throws Exception {
		JSONTask json = new JSONTask();

		String source = json.getjson(contenttypeid, contentid);
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
		if (itemsObject == null)
			return 0;
		// item부분 JSONArray형에 넣음
		JSONObject itemObject = (JSONObject) itemsObject.get("item");

		// System.out.println("위도 타입 "+itemObject.get("mapy").getClass().getName());
		// System.out.println("경도 타입 "+itemObject.get("mapx").getClass().getName());

		if (itemObject.get("mapy") instanceof Double) {
			lat = (Double) itemObject.get("mapy");
		}
		if (itemObject.get("mapy") instanceof String) {
			lat = (String) itemObject.get("mapy");
		}

		if (itemObject.get("mapx") instanceof Double) {
			lng = (Double) itemObject.get("mapx");
		}
		if (itemObject.get("mapx") instanceof String) {
			lng = (String) itemObject.get("mapx");
		}

		slat = "" + lat;
		slng = "" + lng;
		return 1;

	}

}
