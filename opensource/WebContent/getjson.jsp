<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page import="org.json.simple.*" %>
<%@page import="org.json.simple.parser.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		long todaytemp=0;
		long todaysky=0;
	
		String addr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey=";
		String servicekey = "LcH3i5zoCYPXGxjxAQ05YHXBL9bBcQ2WR3Oy968Ej6X%2Faue5ZxcZEuCMYMMkHA57dnkmr7uQhfb9e6euhWv58A%3D%3D";
		String parameter1 = "";
		String parameter2 = "";
		
		Calendar cal = Calendar.getInstance();

		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1;
		int date = cal.get(cal.DATE);
		String result = "";
		String smonth = "";
		String sdate = "";
		
		if (month < 10)
			smonth = "0" + month;
		else
			smonth = "" + month;

		if (date < 10)
			sdate = "0" + date;
		else
			sdate = "" + date;

		result = "" + year + smonth + sdate;
		String today = result;
		//System.out.println(date);
		parameter1 = "&base_date=" + today;
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
		
		int cnt = 0;

		JSONParser parser = new JSONParser();
		// 원본 데이터 JSONObject에 넣음
		JSONObject object = (JSONObject) parser.parse(json);
		// response부분 파싱
		JSONObject responseObject = (JSONObject) object.get("response");
		// body부분 파싱
		JSONObject bodyObejct = (JSONObject) responseObject.get("body");
		// items부분 파싱
		JSONObject itemsObject = (JSONObject) bodyObejct.get("items");
		// item부분 JSONArray형에 넣음
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

			//JudgeCategory judge = new JudgeCategory(category, fcstValue);
			//judge.printfInfo(category, fcstValue);
			//System.out.println("온도"+fcstValue);
			if(category.equals("T3H")){
				todaytemp=fcstValue;
			}
			if(category.equals("SKY")){
				todaysky=fcstValue;
			}
		}
	%>
	
	<%=todaytemp %>
	<%session.setAttribute("today", todaytemp); %>
	<%session.setAttribute("todaysky", todaysky); %>
	<%response.sendRedirect("final.jsp"); %>
</body>
</html>