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
		// API���� ������ ������ String�� ������ ����
		String source = jsontask.getjson();

		// System.out.println(source);

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
		// item�κ� �Ľ�
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

		System.out.println("����: " + title);
		System.out.println("�ּ�: " + addr1 + addr2);
		System.out.println("��з�: " + category1);
		System.out.println("�ߺз�: " + category2);
		System.out.println("�Һз�: " + category3);
		System.out.println("���� �ĺ� ID: " + contentid);
		System.out.println("������ Ÿ�� ID: " + contenttypeid);
		System.out.println("x��ǥ: " + mapx);
		System.out.println("y��ǥ: " + mapy);
		System.out.println("������: " + overview);
	}

}
