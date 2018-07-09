package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONTask {
	public String getjson() throws Exception {
		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=";
		String servicekey = "urSSTMQCpERPRTGRF6f4MxX8Mey0khC91JRGa2Q%2FGfCXWkTeRdT24upQuQq2XqJUjMl4Y%2FcYSSOMgzz2UR3VuA%3D%3D";
		String parameter = "";

		parameter = "&contentTypeId=12&contentId=126480&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y";
		//xml형식을 json형식으로 변환
		parameter = parameter + "&_type=json";
		addr = addr + servicekey + parameter;
		// System.out.println(addr);

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
