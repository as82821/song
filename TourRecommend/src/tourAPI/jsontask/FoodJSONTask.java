package tourAPI.jsontask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FoodJSONTask {

	public String getjson(int pageNo) throws Exception {
		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=";
		String servicekey = "LcH3i5zoCYPXGxjxAQ05YHXBL9bBcQ2WR3Oy968Ej6X%2Faue5ZxcZEuCMYMMkHA57dnkmr7uQhfb9e6euhWv58A%3D%3D";
		String parameter = "";

		parameter = "&contentTypeId=&areaCode=1&sigunguCode=&cat1=A05&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=";
		// xml형식을 json형식으로 변환
		parameter = parameter + "" + pageNo + "&_type=json";
		addr = addr + servicekey + parameter;

		URL url = new URL(addr);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		String inLine;
		String json = "";

		while ((inLine = br.readLine()) != null) {
			json = inLine;
		}
		br.close();

		return json;

	}
}
