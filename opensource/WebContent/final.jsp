<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		long sky = (long) session.getAttribute("todaysky");
		if (sky == 1) {
	%>
	<img src="./imgs/맑음.jpg">
	<%
		} else if (sky == 2) {
	%>
	<img src="./imgs/구름조금.jpg" />
	<%
		} else if (sky == 3) {
	%>
	<img src="./imgs/구름많음.jpg" />
	<%
		} else if (sky == 4) {
	%>
	<img src="./imgs/흐림.jpg" />
	<%
		}
	%>

</body>
</html>