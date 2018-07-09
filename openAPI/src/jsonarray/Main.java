package jsonarray;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) throws Exception {
		NatureJSONTask json = new NatureJSONTask();
		
		for (int j = 1; j <= 6; j++) {
			String source = json.getjson(j);
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
			// item�κ� JSONArray���� ����
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

				System.out.println("����: " + title);
				System.out.println("�ּ�: " + addr);
				System.out.println("��з�: " + category1);
				System.out.println("�ߺз�: " + category2);
				System.out.println("�Һз�: " + category3);
				System.out.println("���� �ĺ� ID: " + contentid);
				System.out.println("������ Ÿ�� ID: " + contenttypeid);
				System.out.println();
			}
		}	

	}

}
