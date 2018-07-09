package tourAPI_image_overview;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parsing {

	public String image;
	public String overview;
	public long mycontentid;
	public long mycontenttypeid;
	
	/*데이터가 있으면 1, 없으면 0*/
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
		if(itemsObject==null)
			return 0;
		// item부분 JSONArray형에 넣음
		JSONObject itemObject = (JSONObject) itemsObject.get("item");

			
		if(itemObject.get("firstimage")==null)
			return 0;
		
		image = (String) itemObject.get("firstimage");
		overview = (String) itemObject.get("overview");
		mycontentid = (long) itemObject.get("contentid");
		mycontenttypeid = (long) itemObject.get("contenttypeid");
		
		return 1;

		/*System.out.println("이미지 링크: " + image);
		System.out.println("오버뷰: " + overview);
		System.out.println("고유 식별 ID: " + mycontentid);
		System.out.println("컨텐츠 타입 ID: " + mycontenttypeid);
		System.out.println();*/

	}

}
