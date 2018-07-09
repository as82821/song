<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
if(session.getAttribute("s_userid")==null){
	response.sendRedirect("signin.jsp");
}
%>