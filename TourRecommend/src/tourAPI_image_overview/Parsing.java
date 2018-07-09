package tourAPI_image_overview;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parsing {

	public String image;
	public String overview;
	public long mycontentid;
	public long mycontenttypeid;
	
	/*�����Ͱ� ������ 1, ������ 0*/
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
		if(itemsObject==null)
			return 0;
		// item�κ� JSONArray���� ����
		JSONObject itemObject = (JSONObject) itemsObject.get("item");

			
		if(itemObject.get("firstimage")==null)
			return 0;
		
		image = (String) itemObject.get("firstimage");
		overview = (String) itemObject.get("overview");
		mycontentid = (long) itemObject.get("contentid");
		mycontenttypeid = (long) itemObject.get("contenttypeid");
		
		return 1;

		/*System.out.println("�̹��� ��ũ: " + image);
		System.out.println("������: " + overview);
		System.out.println("���� �ĺ� ID: " + mycontentid);
		System.out.println("������ Ÿ�� ID: " + mycontenttypeid);
		System.out.println();*/

	}

}
