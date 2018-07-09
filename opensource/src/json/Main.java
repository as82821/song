package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) throws Exception {
		JSONTask jsontask = new JSONTask();
		// API���� ������ ������ String�� ������ ����
		String source = jsontask.getjson();

		//System.out.println(source);
		int cnt = 0;

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

			String category = (String) arrobj.get("category");
			if (category.equals("POP") && cnt == 1)
				continue;
			if (category.equals("UUU") || category.equals("VEC") || category.equals("VVV") || category.equals("WSD"))
				continue;
			if (category.equals("POP"))
				cnt++;
			long baseDate = (Long) arrobj.get("baseDate");
			String baseTime = (String) arrobj.get("baseTime");
			long fcstDate = (Long) arrobj.get("fcstDate");
			String fcstTime = (String) arrobj.get("fcstTime");
			long fcstValue = (Long) arrobj.get("fcstValue");
			long nx = (Long) arrobj.get("nx");
			long ny = (Long) arrobj.get("ny");

			if (i == 0) {
				PrintDateTime pd = new PrintDateTime(baseDate, baseTime, fcstDate, fcstTime, nx, ny);
				pd.printDateTime();
				System.out.println();
			}

			JudgeCategory judge = new JudgeCategory(category, fcstValue);
			judge.printfInfo(category, fcstValue);
			//System.out.println("�µ�"+fcstValue);
		}
	}

}
