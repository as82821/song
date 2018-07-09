package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JSONTask {
	GetDate gd = new GetDate();

	public String getjson() throws Exception {
		String addr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey=";
		String servicekey = "LcH3i5zoCYPXGxjxAQ05YHXBL9bBcQ2WR3Oy968Ej6X%2Faue5ZxcZEuCMYMMkHA57dnkmr7uQhfb9e6euhWv58A%3D%3D";
		String parameter1 = "";
		String parameter2 = "";
		String date = gd.getresult();	
		//System.out.println(date);
		parameter1 = "&base_date=" + date;
		parameter2 = "&base_time=0500&nx=68&ny=106&numOfRows=10&pageSize=10&pageNo=1&startPage=1&_type=json";
		addr = addr + servicekey + parameter1 + parameter2;
		//System.out.println(addr);
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
