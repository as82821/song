<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="dao.PlaceDAO" %>
    <%@ page import="java.io.PrintWriter" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	PlaceDAO dao=PlaceDAO.getInstance();
	int result = dao.search(request.getParameter("pname"));
	System.out.println(result+request.getParameter("pname"));
	String url="";
	if(result == 1){
		url="place_detail.jsp?contentid="+dao.contentid;
		response.sendRedirect(url);
	}else if(result == -1){
		url="place_searchFail.jsp";
		response.sendRedirect(url);
	} 
	//out.print(-1);	//확인용
%>
</body>
</html>