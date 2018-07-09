package simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;

public class Main {

	public static void main(String[] args) throws Exception {
		// String addr =
		// "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=";
		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=";
		String servicekey = "urSSTMQCpERPRTGRF6f4MxX8Mey0khC91JRGa2Q%2FGfCXWkTeRdT24upQuQq2XqJUjMl4Y%2FcYSSOMgzz2UR3VuA%3D%3D";
		String parameter = "";

		parameter = "&contentTypeId=12&contentId=126480&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y";
		// addr = addr + servicekey + parameter;
		addr = addr + servicekey;
		System.out.println(addr);
		URL url = new URL(addr);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		String inLine;
		String xml = "";

		while ((inLine = br.readLine()) != null) {
			xml = inLine;
			System.out.println(xml);
		}

		br.close();
	}

}
