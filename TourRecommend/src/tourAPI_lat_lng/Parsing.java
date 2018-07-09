package tourAPI_lat_lng;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parsing {

	// ����
	public Object lat;
	public String slat;
	// �浵
	public Object lng;
	public String slng;

	/* �����Ͱ� ������ 1, ������ 0 */
	public int parsing(int contenttypeid, int contentid) throws Exception {
		JSONTask json = new JSONTask();

		String source = json.getjson(contenttypeid, contentid);
		// ������ ���� �����͸� �Ľ�
		JSONParser parser = new JSONParser();
		// ���� ������ JSONObject�� ����
		JSONObject object = (JSONObject) parser.parse(source);
		// response�κ� �Ľ�
		JSONObject responseObject = (JSONObject) object.get("response");
		// body�κ� �Ľ�
		JSONObject bodyObejct = (JSONObject) responseObject.get("body");
		// items�κ� �Ľ�
		JSONObject itemsObject = (JSONObject) bodyObejct.get("items");
		if (itemsObject == null)
			return 0;
		// item�κ� JSONArray���� ����
		JSONObject itemObject = (JSONObject) itemsObject.get("item");

		// System.out.println("���� Ÿ�� "+itemObject.get("mapy").getClass().getName());
		// System.out.println("�浵 Ÿ�� "+itemObject.get("mapx").getClass().getName());

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
