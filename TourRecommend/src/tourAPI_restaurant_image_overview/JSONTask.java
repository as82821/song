package tourAPI_restaurant_image_overview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JSONTask {
	public String getjson(int contenttypeid, int contentid) throws Exception {
		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=";
		String servicekey = "LcH3i5zoCYPXGxjxAQ05YHXBL9bBcQ2WR3Oy968Ej6X%2Faue5ZxcZEuCMYMMkHA57dnkmr7uQhfb9e6euhWv58A%3D%3D";
		String parameter1 = "";
		String parameter2 = "";
		String parameter3 = "";

		parameter1 = "&contentTypeId=" + contenttypeid;
		parameter2 = "&contentId=" + contentid;
		// parameter3 =
		// "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y";
		parameter3 = "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y";
		// xml형식을 json형식으로 변환
		addr = addr + servicekey + parameter1 + parameter2 + parameter3 + "&_type=json";

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
